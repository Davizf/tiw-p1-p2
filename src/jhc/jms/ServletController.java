package jhc.jms;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletController", urlPatterns = "/jms-controller")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int op = Integer.parseInt(req.getParameter("op")) ;	// 1 = send message &&	2 = read message
		String sender = req.getParameter("sender") ;	
		InteractionJMS mq=new InteractionJMS();
		String correlationId = req.getParameter("correlationId");	

		if (op==1){
			mq.writeJMS(req.getParameter("message"),correlationId,sender);
			RequestDispatcher miR=req.getRequestDispatcher("admin-send-message.jsp");
			miR.forward(req, resp);

		}if (op==2) {
			req.setAttribute("messages", mq.readJMS(correlationId));
			RequestDispatcher miR=req.getRequestDispatcher("mymessages-page.jsp");
			miR.forward(req, resp);

		}else if(op == 3){
			mq.writeJMS(req.getParameter("message"),correlationId,sender);
			RequestDispatcher miR=req.getRequestDispatcher("mymessages-page.jsp");
			miR.forward(req, resp);
		}


	}

}