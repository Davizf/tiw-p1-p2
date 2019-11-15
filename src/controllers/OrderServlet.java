package controllers;

import java.io.IOException;
import jhc.jms.*;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		
		//InteractionJMS mq=new InteractionJMS();
		//mq.confirmPurchase("123412124214", req.getParameter("total-price"));
		
		
		// El otro proceso que lee el mensaje y generar confirmacion de compra está en mi local, no sé si es un proyecto aparte o en este mismo, estoy esperando respuesta del profe
		
		// Guardar la compra en la pagina de mis pedidos
		
		RequestDispatcher rd = req.getRequestDispatcher("confirm-page.jsp");
		rd.forward(req, res);
		
		
		
		
	}
	

}
