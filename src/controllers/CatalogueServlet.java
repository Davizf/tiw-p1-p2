package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Category;
import model.Product;
import model.User;

@WebServlet(name = "Catalogue", urlPatterns = "/Catalogue")
@MultipartConfig
public class CatalogueServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private static final String IMAGE_FOLDER=File.separator+"images";

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession sesion = req.getSession();
		String user = (String) sesion.getAttribute("user");

		if(req.getParameter("type").equalsIgnoreCase("change-stock")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = ProductController.getProduct(id);
			int newStock = Integer.parseInt(req.getParameter("newStock"));
			if (ProductController.verifyStock(newStock)) {
				p.setStock(newStock);
				if (p.getUserBean().getEmail().equals(user))
					ProductController.modifyProduct(p);
			} else {
				req.setAttribute("msg_error", "Error, invalid stock value.");
			}

			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);

		}else if(req.getParameter("type").equalsIgnoreCase("add")) {
			req.setAttribute("operation", "add");
			req.setAttribute("seller_user", req.getParameter("seller_user"));
			RequestDispatcher rd = req.getRequestDispatcher("product-page-seller.jsp");
			rd.forward(req, res);

		}else if(req.getParameter("type").equalsIgnoreCase("modify")) {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("productId", id);
			req.setAttribute("seller_user", req.getParameter("seller_user"));
			Product p = ProductController.getProduct(id);
			req.setAttribute("product", p);
			req.setAttribute("operation", "modify");
			RequestDispatcher rd = req.getRequestDispatcher("product-page-seller.jsp");
			rd.forward(req, res);

		}else if(req.getParameter("type").equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = ProductController.getProduct(id);
			if (p.getUserBean().getEmail().equals(user))
				ProductController.deleteProduct(id);

			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("type").equalsIgnoreCase("add_product")) {
			// TODO
			String product_name=req.getParameter("product_name");
			double product_price=Double.parseDouble(req.getParameter("product_price"));
			double product_sale_price=Double.parseDouble(req.getParameter("product_sale_price"));
			double product_ship_price=Double.parseDouble(req.getParameter("product_ship_price"));
			int product_stock=Integer.parseInt(req.getParameter("product_stock"));
			String product_short_description=req.getParameter("product_short_description");
			String product_description=req.getParameter("product_description");
			String product_category=req.getParameter("product_category");
			String product_user=req.getParameter("product_user");
			
			String product_image_path;
			Part product_image=req.getPart("product_image_file");
			String image_fileName=extractFileName(product_image);
			String image_parentPath=getServletContext().getRealPath(IMAGE_FOLDER), image_filePath="newProduct_"+new Date().getTime()+".png";
			File image_file;

			if (image_fileName.equals("")) {// There isnt new image
				req.setAttribute("msg_error", "Error, select an image.");
				RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
				rd.forward(req, res);
				return;
			} else {
				//fileName=getServletContext().getRealPath(IMAGE_FOLDER) + File.separator + product_id+".png";
				image_file = new File(new File(image_parentPath), image_filePath);

				if (image_file.exists()) image_file.delete();
				try (InputStream input = product_image.getInputStream()) {
					Files.copy(input, image_file.toPath());
				}
				product_image_path=image_file.getAbsolutePath();
			}

			Product p=new Product();
			p.setName(product_name);
			p.setPrice(new BigDecimal(product_price));
			p.setSalePrice(new BigDecimal(product_sale_price));
			p.setShipPrice(new BigDecimal(product_ship_price));
			p.setStock(product_stock);
			p.setShortDescription(product_short_description);
			Category c = new Category();
			c.setName(product_category);
			p.setCategoryBean(c);
			p.setDescription(product_description);
			p.setImagePath(product_image_path);
			User u=new User();
			u.setEmail(product_user);
			p.setUserBean(u);

			int newId=ProductController.addProduct(p);
			//Change name of file to id
			product_image_path=image_parentPath+File.separator+newId+".png";
			File image_file2=new File(product_image_path);
			image_file.renameTo(image_file2);
			p.setImagePath(product_image_path);
			ProductController.modifyProduct(p);
			
			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}else if(req.getParameter("type").equalsIgnoreCase("modify_product")) {
			int product_id=Integer.parseInt(req.getParameter("product_id"));
			String product_name=req.getParameter("product_name");
			double product_price=Double.parseDouble(req.getParameter("product_price"));
			double product_sale_price=Double.parseDouble(req.getParameter("product_sale_price"));
			double product_ship_price=Double.parseDouble(req.getParameter("product_ship_price"));
			int product_stock=Integer.parseInt(req.getParameter("product_stock"));
			String product_short_description=req.getParameter("product_short_description");
			String product_description=req.getParameter("product_description");
			String product_category=req.getParameter("product_category");
			String product_user=req.getParameter("product_user");

			String product_image_path;
			Part product_image=req.getPart("product_image_file");
			String image_fileName=extractFileName(product_image);
			String image_parentPath=getServletContext().getRealPath(IMAGE_FOLDER), image_filePath=product_id+".png";
			File image_file;

			if (image_fileName.equals("")) {// There isnt new image
				product_image_path=req.getParameter("product_image_path");
			} else {
				//fileName=getServletContext().getRealPath(IMAGE_FOLDER) + File.separator + product_id+".png";
				image_file = new File(new File(image_parentPath), image_filePath);

				if (image_file.exists()) image_file.delete();
				try (InputStream input = product_image.getInputStream()) {
					Files.copy(input, image_file.toPath());
				}
				product_image_path=image_file.getAbsolutePath();
			}

			Product p=new Product();
			p.setId(product_id);
			p.setName(product_name);
			p.setPrice(new BigDecimal(product_price));
			p.setSalePrice(new BigDecimal(product_sale_price));
			p.setShipPrice(new BigDecimal(product_ship_price));
			p.setStock(product_stock);
			p.setShortDescription(product_short_description);
			Category c = new Category();
			c.setName(product_category);
			p.setCategoryBean(c);
			p.setDescription(product_description);
			p.setImagePath(product_image_path);
			User u=new User();
			u.setEmail(product_user);
			p.setUserBean(u);
			
			ProductController.modifyProduct(p);
			RequestDispatcher rd = req.getRequestDispatcher("catalogue.jsp");
			rd.forward(req, res);
		}
	}

	private static String extractFileName(Part part) {
		String content=part.getHeader("content-disposition");
		String[] items=content.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=")+2, s.length()-1);
			}
		}
		return null;
	}

}