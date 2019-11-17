package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.UserController;

@WebFilter(
		urlPatterns = "/product-page-seller.jsp",
		filterName = "ProductPageSellerFilter"
		)
public class ProductPageSellerFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;

		String email = (String) ((HttpServletRequest) request).getSession().getAttribute("user");

		if (email == null) {
			res.sendRedirect("index.jsp");
			return;
		} else {
			int type = UserController.getUserInformation(email).getType();
			if (type != 1)
				res.sendRedirect("products.jsp");

			chain.doFilter(request, response);
		}
	}

}