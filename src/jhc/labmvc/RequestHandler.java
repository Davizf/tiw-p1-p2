package jhc.labmvc;
/*
 * RequestHandler.java
 *
 * Created on 13 de diciembre de 2005, 14:42
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author telmoz
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public interface RequestHandler {
  /**
   * (probably a JSP), or null to indicate that the response has been
   * generated already and processing is complete.
   */
  String handleRequest(HttpServletRequest request,
                       HttpServletResponse response)
         throws ServletException, IOException;
}
