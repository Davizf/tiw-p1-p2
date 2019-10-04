/*
 * ShowRecordRequestHandler.java
 *
 * Created on 13 de diciembre de 2005, 14:55
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package jhc.labmvc;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowRecordRequestHandler implements RequestHandler {

	private DataStore dataStore;

	public ShowRecordRequestHandler() {
		dataStore = new DataStore();
	}

	/**
	 * @return the the URL of the view that should render the response (probably
	 *         a JSP), or null to indicate that the response has been output
	 *         already and processing is complete.
	 */
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");

		if (name == null) {
			return "enterName.html";
		} else {

			// Do database lookup for the user

			DataModelBean dataBean = dataStore.getInfo(name);

			if (dataBean == null) {
				return "sorryNotFound.jsp";
			} else {
				/**
				 * We have data for this user Create model object based on
				 * retrieved data DataModelBean dataBean = retrieve data and
				 * create object
				 */

				request.setAttribute("dataModelBean", dataBean);

				return "showInfo.jsp";
			}
		}

	}
}
