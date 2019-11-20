package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		if(req.getParameter("op").equalsIgnoreCase("view")) {
			req.setAttribute("resultType", "showAll");

		}else if(req.getParameter("op").equalsIgnoreCase("search")) {
			String nameToQuery = req.getParameter("query");
			List<Product> products;
			if (nameToQuery.equals(""))
				products = ProductController.getAllProducts();
			else
				products = ProductController.getProductByName(nameToQuery);

			req.setAttribute("foundProducts", products);
			req.setAttribute("resultType", "foundByKey");

		}else if(req.getParameter("op").equalsIgnoreCase("category")) {
			String category = req.getParameter("category");
			String[] categories = category.split(",");

			List<Product> products;
			if (categories.length == 1) {
				System.out.println(category);
				products = ProductController.getProductsByCategory(Integer.parseInt(category));
			} else {
				List<Integer> idCategories=new ArrayList<Integer>(); 
				for (int i = 0; i < categories.length; i++)
					idCategories.add(Integer.parseInt(categories[i]));

				products = ProductController.getProductsByCategories(idCategories);
			}
			req.setAttribute("foundProducts", products);
			req.setAttribute("resultType", "foundByKey");

		}else if(req.getParameter("op").equalsIgnoreCase("filter")) {
			String price = req.getParameter("chk_filter_price"), category = req.getParameter("chk_filter_category"), shipPrice = req.getParameter("chk_filter_ship_price");
			boolean filterPrice = (price!=null && price.equals("on")),
					filterCategory = (category!=null && category.equals("on")),
					filterShipPrice = (shipPrice!=null && shipPrice.equals("on"));

			//TODO
			System.out.println("-----------------------");
			System.out.println("filterPrice "+filterPrice);
			if (filterPrice) {
				int filterMinimun=Integer.parseInt(req.getParameter("filter_price_minimun")),
						filterMaximum=Integer.parseInt(req.getParameter("filter_price_maximum"));
				System.out.println("minimun "+filterMinimun);
				System.out.println("maximum "+filterMaximum);
			}
			System.out.println("filterCategory "+filterCategory);
			if (filterCategory) {
				int filterCategoryId=Integer.parseInt(req.getParameter("filter_category"));
				System.out.println("CategoryId "+filterCategoryId);
			}
			System.out.println("filterShipPrice "+filterShipPrice);
			if (filterShipPrice) {
				String freeShipping = req.getParameter("filter_free_shipping");
				boolean filterFreeShipping = (freeShipping!=null && freeShipping.equals("on"));
				System.out.println("FreeShipping "+filterFreeShipping);
			}
			System.out.println("----------------------");

			List<Product> products;
			products = ProductController.getAllProducts();
			req.setAttribute("foundProducts", products);
			req.setAttribute("resultType", "foundByKey");
		}

		RequestDispatcher rd = req.getRequestDispatcher("products.jsp");
		rd.forward(req, res);

	}	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}