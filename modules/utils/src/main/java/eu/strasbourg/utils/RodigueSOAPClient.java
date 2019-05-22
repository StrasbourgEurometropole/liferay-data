package eu.strasbourg.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.List;

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

import eu.strasbourg.utils.models.RodrigueEventSession;

public class RodigueSOAPClient {
	
	public static final String NAMESPACE = "rod";
	public static final String NAMESPACE_URI = "http://www.rodrigue.fr/";
	
	/**
	 * Renvoie les séances et ses informations (disponibilités) d'une manifestation.
	 * DataSet manif/seance/lieu + contrainte
	 * 
	 * @param structureID
	 * @param languageID
	 * @param eventID
	 * @return
	 * @throws Exception
	 */
	public static List<RodrigueEventSession> getSessionListOfEvent(String eventID) {
		
		try {
			/**
			 * 1# Initilisation de la requete
			 */
			SOAPMessage soapRequest = RodigueSOAPClient.initRequest();
			SOAPBody soapBody = soapRequest.getSOAPBody();
			
			// Methode
			SOAPElement methodElement = soapBody.addChildElement("GetSessionListOfEvent", NAMESPACE);
			
			// Parametres
			String structureID = StrasbourgPropsUtil.getRodrigueOPSStructureID();
			SOAPElement paramElement = methodElement.addChildElement("StructureID", NAMESPACE);
			SOAPElement paramValueElement = paramElement.addChildElement("string", NAMESPACE);
			paramValueElement.addTextNode(structureID);
			
			paramElement = methodElement.addChildElement("LanguageID", NAMESPACE);
			paramValueElement = paramElement.addChildElement("string", NAMESPACE);
			paramValueElement.addTextNode("1");
			
			paramElement = methodElement.addChildElement("EventID", NAMESPACE);
			paramValueElement = paramElement.addChildElement("string", NAMESPACE);
			paramValueElement.addTextNode(eventID);
			
			// Ajout de la requete
			soapRequest.getSOAPBody().addChildElement(methodElement);
			
			/**
			 * 2# Envoie de la requete
			 */
			SOAPMessage response = RodigueSOAPClient.getResponse(soapRequest);
			
			/**
			 * 3# Interpretation de la reponse
			 */
			List<RodrigueEventSession> sessionsList = new ArrayList<RodrigueEventSession>();
			
			soapBody = response.getSOAPBody();
			NodeList bodyNodes = soapBody.getChildNodes();
			Node responseNode = bodyNodes.item(0);
			Node resultNode = responseNode.getChildNodes().item(0);
			NodeList dataNodes = resultNode.getChildNodes().item(0).getChildNodes();
			
			for (int i = 0; i < dataNodes.getLength(); i++) {
				Node dataNode = dataNodes.item(i);
				String dataName = dataNode.getLocalName();
				switch (dataName) {
					case "test":
						String data = dataNode.getTextContent();
				}
	
			}
			
			return sessionsList;
		} catch (Exception e) {
			_log.error(e);
			return new ArrayList<RodrigueEventSession>();
		}
	}
	
	/**
	 * Initilise la requête SOAP
	 * @return
	 * @throws Exception
	 */
	private static SOAPMessage initRequest() throws Exception{
		// Create SOAP Request
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapRequest = messageFactory.createMessage();
		SOAPPart soapPart = soapRequest.getSOAPPart();
		
		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(NAMESPACE, NAMESPACE_URI);
		
		// Headers
		MimeHeaders headers = soapRequest.getMimeHeaders();
		headers.addHeader("Content-type", "text/xml");
		headers.addHeader("SOAPAction", "");
		
		return soapRequest;
	}
	
	/**
	 * Envoi de la requete au webservice SOAP Rodrigue
	 * @param soapRequest
	 * @return
	 * @throws Exception
	 */
	private static SOAPMessage getResponse(SOAPMessage soapRequest) throws Exception {
		// Properties
		String rodrigueAPIURL = StrasbourgPropsUtil.getRodrigueAPIURL();
		
		// SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		URL endpoint = new URL(null, rodrigueAPIURL, new URLStreamHandler() {
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
	
	private static final Log _log = LogFactoryUtil.getLog(FileEntryHelper.class.getName());
	
}
