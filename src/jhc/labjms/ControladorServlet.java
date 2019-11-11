package jhc.labjms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorServlet
 * Jes�s Hernnado Corrochano
 */
public class ControladorServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 2920899322727130776L;

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		doPost(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		/*
		int intSelector=Integer.parseInt(req.getParameter("selector"));
		int intMetodo = Integer.parseInt(req.getParameter("metodo"));
		int intOperacion = Integer.parseInt(req.getParameter("operacion"));
		*/
		
		//	Para comprador
		int intMetodo = 1;	// 1 = comprador		
		int intSelector=Integer.parseInt(req.getParameter("selector"));	// qué vendedor enviar el mensaje
		int intOperacion = Integer.parseInt(req.getParameter("op")) ;	// 1 = mandar mensaje	2 = leer mensaje
		
		
		
		/*
		int intMetodo = 2;	// 2 = vendedor	(escritura cola asincrona)
		int intSelector=Integer.parseInt(req.getParameter("selector"));	// qué vendedor enviar el mensaje
		int intOperacion = Integer.parseInt(req.getParameter("op")) ;	// 1 = mandar mensaje broadcast	2 = leer mensaje del comprador
		*/

		
		//PrintWriter out = resp.getWriter();
		

		InteraccionJMS mq=new InteraccionJMS();
		
		/*					VENDEDOR				*/
		String strAux="";
		switch (intSelector) {
		case 1:
			strAux=Selectores.SelecrtorA;
			break;
		case 2:
			strAux=Selectores.SelecrtorB;
			break;
		default:
			strAux=Selectores.SelecrtorA;
			break;
		}

		if (intOperacion==1){
			mq.escrituraJMS(req.getParameter("mensaje"),intMetodo,strAux);
			RequestDispatcher miR=req.getRequestDispatcher("message-to-seller.jsp");
			miR.forward(req, resp);

		}else{
			
			intMetodo = 1;	// leer mensajes del comprador
			strAux=mq.lecturaJMS(intMetodo, strAux);
			//out.print(strAux);
			req.setAttribute("mensajes", strAux);
			RequestDispatcher miR=req.getRequestDispatcher("mymessages-page.jsp");
			miR.forward(req, resp);
		}

	}


}
