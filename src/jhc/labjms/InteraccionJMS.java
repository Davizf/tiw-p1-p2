/*
 * Creado el 11-nov-14
 * https://docs.oracle.com/javaee/5/tutorial/doc/bncfa.html#bncfl
 */
package jhc.labjms;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import jhc.info.InformacionProperties;

/**
 * @author Jes�s Hernando Corrochano
 */

public class InteraccionJMS {

	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	private javax.jms.Destination cola = null;
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	private javax.jms.MessageProducer Mpro = null;
	private javax.jms.MessageConsumer Mcon = null;

	public void escrituraJMS(String mensaje, int opcion, String selector) {

		try {

			contextoInicial = new javax.naming.InitialContext();

			switch (opcion) {
			case 1:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			case 2:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial.
						lookup(InformacionProperties.getQueueAsincrona());
				break;
			case 3:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQCF() + "Ref");
				cola = (javax.jms.Destination) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQueue() + "Ref");
				break;
			default:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			}
			Qcon = factory.createConnection();
			QSes = Qcon
					.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			Mpro = QSes.createProducer(cola);

			javax.jms.TextMessage men = QSes.createTextMessage();

			men.setText(mensaje);
			men.setJMSCorrelationID(selector);
			Qcon.start();
			Mpro.send(men);

			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

	}

	public String lecturaJMS(int opcion, String strSelectorPasado) {

		StringBuffer mSB = new StringBuffer(64);
		try {
			contextoInicial = new javax.naming.InitialContext();

			switch (opcion) {
			case 1:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			case 2:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQCF() + "Ref");
				cola = (javax.jms.Destination) contextoInicial
						.lookup("java:comp/env/"
								+ InformacionProperties.getQueue() + "Ref");
				break;
			default:
				factory = (javax.jms.ConnectionFactory) contextoInicial
						.lookup(InformacionProperties.getQCF());
				cola = (javax.jms.Destination) contextoInicial
						.lookup(InformacionProperties.getQueue());
				break;
			}

			Qcon = factory.createConnection();

			QSes = Qcon
					.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			String sSelector = "JMSCorrelationID = '"
					+ strSelectorPasado.trim() + "'";

			if (strSelectorPasado.equals("")) {
				Mcon = QSes.createConsumer(cola);
			} else {
				Mcon = QSes.createConsumer(cola, sSelector);

			}
			Qcon.start();
			Message mensaje = null;
			mSB.append("</br>Estos son los mensajes leidos con el selector "
					+ strSelectorPasado + " </br>");
			while (true) {
				mensaje = Mcon.receive(100);
				if (mensaje != null) {
					if (mensaje instanceof TextMessage) {
						TextMessage m = (TextMessage) mensaje;
						mSB.append("       Mensaje: " + m.getText() + " </br>");
					} else {
						// JHC ************ No es del tipo correcto
						break;
					}
				} else // NO existe mensaje, mensaje es null
				{
					mSB.append("TIdW 2013-14: No hay Mensajes en la Queue</br>");
					break;
				}

			}
			this.Mcon.close();
			this.QSes.close();
			this.Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

		return mSB.toString();

	}

	@Resource(mappedName = "jms/cf1.1")
	private static ConnectionFactory _connectionFactory;
	@Resource(mappedName = "jms/queue1.1")
	private static Queue _queue;

	public String lecturaBrowser() {

		Connection _connection = null;
		StringBuffer _sB = new StringBuffer(32);
		_sB.append("<br>");

		try {
			_connection = _connectionFactory.createConnection();

			Session session = _connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			QueueBrowser browser = session.createBrowser(_queue);

			@SuppressWarnings("rawtypes")
			Enumeration msgs = browser.getEnumeration();

			while (msgs.hasMoreElements()) {
				Message tempMsg = (Message) msgs.nextElement();
				_sB.append(tempMsg);
			}

		} catch (JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} finally {
			if (_connection != null) {
				try {
					_connection.close();
				} catch (JMSException e) {
					System.out
							.println(".....JHC *************************************** Error de JMS: "
									+ e.getLinkedException().getMessage());
					System.out
							.println(".....JHC *************************************** Error de JMS: "
									+ e.getLinkedException().toString());
				}
			}
		}
		return _sB.toString();

	}
}
