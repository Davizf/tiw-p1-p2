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
			List<Product> products = ProductController.getProductByName(nameToQuery);
			req.setAttribute("foundProducts", products);
			req.setAttribute("resultType", "foundByKey");

		}else if(req.getParameter("op").equalsIgnoreCase("category")) {
			String category = req.getParameter("category");
			String[] categories = category.split(",");

			List<Product> products;
			if (categories.length == 1) {
				products = ProductController.getProductsByCategory(Integer.parseInt(category));
			} else {
				List<Integer> idCategories=new ArrayList<Integer>(); 
				for (int i = 0; i < categories.length; i++)
					idCategories.add(Integer.parseInt(categories[i]));
				// TODO juanjo
				products = ProductController.getProductsByCategories(idCategories);
				System.out.println("----------------------products encontrados size="+products.size());
				System.out.println(products);
			}
			req.setAttribute("foundProducts", products);
			// TODO david

		}

		RequestDispatcher rd = req.getRequestDispatcher("products.jsp");
		rd.forward(req, res);

	}	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}