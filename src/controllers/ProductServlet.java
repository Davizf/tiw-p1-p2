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
			
			List<Product> products = null;
			List<Product> productsToCompare = null;
			List<Product> productsToElimine =  new ArrayList<>();
			
			String price = req.getParameter("chk_filter_price"), 
					stock = req.getParameter("chk_filter_stock"), shipPrice = req.getParameter("chk_filter_ship_price");
			boolean filterPrice = (price!=null && price.equals("on")),
					filterStock = (stock!=null && stock.equals("on")),
					filterShipPrice = (shipPrice!=null && shipPrice.equals("on"));

			if (filterPrice) {
				int filterMinimun=Integer.parseInt(req.getParameter("filter_price_minimun")),
						filterMaximum=Integer.parseInt(req.getParameter("filter_price_maximum"));
				products = ProductController.getProductsBetweenSalePrices(filterMinimun,filterMaximum);
			}
		

			if (filterStock) {
				int filterStockMinumun=Integer.parseInt(req.getParameter("filter_stock_minimun"));
				if(products != null) {
					productsToCompare = ProductController.getProductsByStock(filterStockMinumun);
					for(Product product : products) {
						if(!productsToCompare.contains(product)) {	// always return true
							productsToElimine.add(product);
						}
					}
					
					for(Product product : productsToElimine) {
						products.remove(product);
					}	
					productsToCompare = null;
					productsToElimine.clear();
					
				}else {
					products = ProductController.getProductsByStock(filterStockMinumun);
				}
				
			}
			

			if (filterShipPrice) {
				String freeShipping = req.getParameter("filter_free_shipping");
				boolean filterFreeShipping = (freeShipping!=null && freeShipping.equals("on"));
				if(filterFreeShipping) {
					if(products != null) {
						productsToCompare = ProductController.getProductsFreeShipment();
						for(Product product : products) {
							//System.out.println("Product->"+product.getName());
							if(!productsToCompare.contains(product)) {		// always return true
								//System.out.println("Compare not Contains->"+product.getName());
								productsToElimine.add(product);
							}
						}
						
						for(Product product : productsToElimine) {
							products.remove(product);
						}	
						productsToCompare = null;
						productsToElimine.clear();
						
					}else {
						products = ProductController.getProductsFreeShipment();
					}
					
				}
			}

			
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