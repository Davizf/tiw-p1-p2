package jhc.labmvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet( urlPatterns ={"/enterName","/showInfo"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Hash table of Request stHandler instances, keyed by request URL
	private HashMap<String, RequestHandler> handlerHash = new HashMap<String, RequestHandler>();

	// Initialize mappings: not implemented here
	public void init() throws ServletException {

		// This will read mapping definitions and populate handlerHash
		handlerHash.put("/enterName", new jhc.labmvc.ShowRecordRequestHandler());
		handlerHash.put("/showInfo", new jhc.labmvc.ShowRecordRequestHandler());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Complete. Retrieve from the HashMap the instance of the class which
		// implements the logic of the requested url
		RequestHandler rh = (RequestHandler) handlerHash.get(request.getServletPath());
		System.out.println(request.getServletPath());
		// Complete. If no instance is retrived redirects to error
		if (rh == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			// Complete. Call the method handleRequsest of the instance in order
			// to obtain the url
			String viewURL = rh.handleRequest(request, response);

			// Complete. Dispatch the request to the url obtained

			if (viewURL == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				request.getRequestDispatcher(viewURL).forward(request, response);
			}
		}
	}
}
