package filters;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.UserManager;

@WebFilter(
		urlPatterns = "/catalogue.jsp",
		filterName = "CatalogueFilter"
		)
public class CatalogueFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;

		String email = (String) ((HttpServletRequest) request).getSession().getAttribute("user");

		if (email == null) {
			res.sendRedirect("index.jsp");
			return;
		} else {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("tiw-p1-buyer-seller");
			UserManager um = new UserManager();
			um.setEntityManagerFactory(factory);

			int type = um.getUser(email).getType();
			factory.close();
			if (type != 1)
				res.sendRedirect("products.jsp");

			chain.doFilter(request, response);
		}
	}

}