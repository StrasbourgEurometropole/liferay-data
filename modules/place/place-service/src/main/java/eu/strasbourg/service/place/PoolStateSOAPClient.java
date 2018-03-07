package eu.strasbourg.service.place;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eu.strasbourg.service.place.model.Place;

/**
 * @author 01i454
 */
public class PoolStateSOAPClient {

	public static long getOccupation(Place pool) {
		long occupation = -1;

		try {
			String poolCode = pool.getRTExternalId();
			occupation = PoolStateSOAPClient.getOccupation(poolCode);
		} catch (Exception ex) {
			occupation = -1;
		}
		return occupation;
	}

	public static long getOccupation(String poolCode) throws Exception {
		SOAPMessage response = getResponse(poolCode);

		// Convert Response To PoolSchedule Object
		long occupation = -1;

		SOAPBody body = response.getSOAPBody();
		NodeList bodyNodes = body.getChildNodes();
		Node responseNode = bodyNodes.item(0);
		Node resultNode = responseNode.getChildNodes().item(0);
		NodeList dataNodes = resultNode.getChildNodes();

		boolean exist = false;
		for (int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			String dataName = dataNode.getLocalName();
			String data = dataNode.getTextContent();
			switch (dataName) {
			case "JaugeOccupation":
				occupation = Long.parseLong(data);
				break;
			case "LibelleSalle":
				exist = true;
				break;
			}

		}
		if(!exist){
			return -1;
		}

		return occupation;

	}

	private static SOAPMessage getResponse(String poolCode) throws Exception {
		/**
		 * Create SOAP Request
		 */
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapRequest = messageFactory.createMessage();
		SOAPPart soapPart = soapRequest.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();

		QName roomName = new QName("http://tempuri.org/", "BCA_WS_SALLE");
		SOAPElement roomElement = soapBody.addChildElement(roomName);

		SOAPElement roomCodeElement = roomElement.addChildElement("CodeSalle");
		roomCodeElement.addTextNode(poolCode);

		// Headers
		MimeHeaders headers = soapRequest.getMimeHeaders();
		headers.addHeader("Content-type", "text/xml");
		headers.addHeader("SOAPAction", "http://tempuri.org/BCA_WS_SALLE");

		/**
		 * Get SOAP Response
		 */
		// SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();
		String url = "http://regal.strasbourg.eu/WSFrequentation/BCA_Webservice.asmx";
		URL endpoint = new URL(null, url, new URLStreamHandler() {
			@Override
			protected URLConnection openConnection(URL url) throws IOException {
				URL clone_url = new URL(url.toString());
				HttpURLConnection clone_urlconnection = (HttpURLConnection) clone_url
						.openConnection();
				clone_urlconnection.setConnectTimeout(5000);
				clone_urlconnection.setReadTimeout(5000);
				return (clone_urlconnection);
			}
		});

		// Send SOAP Message
		SOAPMessage soapResponse = soapConnection.call(soapRequest, endpoint);

		return soapResponse;
	}
}
