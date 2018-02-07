package eu.strasbourg.service.place;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

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

public class MairieStateSOAPClient {
	
	public static long getWaitingTime(String codeMairie) throws Exception {
		SOAPMessage response = getResponse(codeMairie);

		// Convert Response To PoolSchedule Object
		long waitingTime = -1;

		SOAPBody body = response.getSOAPBody();
		NodeList bodyNodes = body.getChildNodes();
		Node responseNode = bodyNodes.item(0);
		Node resultNode = responseNode.getChildNodes().item(0);
		NodeList dataNodes = resultNode.getChildNodes().item(0).getChildNodes();

		for (int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			String dataName = dataNode.getLocalName();
			switch (dataName) {
			case "estimatedAvgWaitingTime":
				String data = dataNode.getTextContent();
				try {
					waitingTime = Long.parseLong(data.split(":")[1]);
				} catch (Exception ex) {
				}
				break;
			}

		}

		return waitingTime;
	}

	private static SOAPMessage getResponse(String codeMairie) throws Exception {
		/**
		 * Create SOAP Request
		 */
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapRequest = messageFactory.createMessage();
		SOAPPart soapPart = soapRequest.getSOAPPart();
        String namespace = "v1";
        String namespaceURI = "http://www.esii.com/esirius/sitewaitingindicator/v1.0";
		
		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(namespace, namespaceURI);

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();

		SOAPElement methodElement = soapBody.addChildElement("getSitesIndicatorsBySites", namespace);
		SOAPElement paramElement = methodElement.addChildElement("siteCodeArray", namespace);
		SOAPElement paramValueElement = paramElement.addChildElement("string", namespace);
		paramValueElement.addTextNode(codeMairie);

		// Headers
		MimeHeaders headers = soapRequest.getMimeHeaders();
		headers.addHeader("Content-type", "text/xml");
		headers.addHeader("SOAPAction", "");

		/**
		 * Get SOAP Response
		 */
		// SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		String url = "https://webservices.strasbourg.eu/filat/eSirius/webservices/sitewaitingindicator/v1.0?wsdl";
		URL endpoint = new URL(null, url, new URLStreamHandler() {
			@Override
			protected URLConnection openConnection(URL url) throws IOException {
				URL clone_url = new URL(url.toString());
				HttpURLConnection clone_urlconnection = (HttpURLConnection) clone_url.openConnection();
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
