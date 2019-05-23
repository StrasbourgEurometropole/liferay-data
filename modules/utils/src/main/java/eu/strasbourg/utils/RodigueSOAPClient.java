package eu.strasbourg.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.soap.MessageFactory;
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
			SOAPElement paramElement1 = methodElement.addChildElement("StructureID", NAMESPACE);
			paramElement1.addTextNode(structureID);
			
			SOAPElement paramElement2 = methodElement.addChildElement("LanguageID", NAMESPACE);
			paramElement2.addTextNode("1");
			
			SOAPElement paramElement3 = methodElement.addChildElement("EventID", NAMESPACE);
			paramElement3.addTextNode(eventID);
			
			/**
			 * 2# Envoie de la requete
			 */
			SOAPMessage response = RodigueSOAPClient.getResponse(soapRequest);
			
			/**
			 * 3# Interpretation de la reponse
			 */
			List<RodrigueEventSession> sessionsList = new ArrayList<RodrigueEventSession>();
			
			soapBody = response.getSOAPBody();
			// --> Body
			NodeList bodyNodes = soapBody.getChildNodes();
			// --> GetSessionListOfEventResponse || Fault
			Node responseNode = bodyNodes.item(0);
			
			if (!responseNode.getLocalName().equals("Fault")) {
				
				// --> GetSessionListOfEventResult
				Node resultNode = responseNode.getChildNodes().item(0);
				
				// --> diffgr:diffgram --> NewDataSet
				NodeList dataNodes = resultNode.getChildNodes().item(1).getChildNodes().item(0).getChildNodes();
				
				if (true) {
					// Parcour des reponses du webservce
					for (int i = 0; i < dataNodes.getLength(); i++) {
						// Instantiation d'une session vide
						RodrigueEventSession session = new RodrigueEventSession();
						
						Node dataNode = dataNodes.item(i);
						
						NodeList varNodes = dataNode.getChildNodes();
						
						// Parcours des variables d'une session
						for (int j = 0; j < varNodes.getLength(); j++) {
							
							Node varNode = varNodes.item(j);
							String varName = varNode.getLocalName();
							
							// Mapage des donnees
							switch (varName) {
								case "EventID":
									session.setEventID(Integer.parseInt(varNode.getTextContent()));
									break;
								case "EventName":
									session.setEventName(varNode.getTextContent());
									break;
								case "EventCode":
									session.setEventCode(varNode.getTextContent());
									break;
								case "EventDescription1":
									session.setEventDescription1(varNode.getTextContent());
									break;
								case "EventDescription2":
									session.setEventDescription2(varNode.getTextContent());
									break;
								case "EventDescription3":
									session.setEventDescription3(varNode.getTextContent());
									break;
								case "SessionID":
									session.setSessionID(Integer.parseInt(varNode.getTextContent()));
									break;
								case "SessionDate":
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
									Date date = sdf.parse(varNode.getTextContent());
									session.setSessionDate(date);
									break;
								case "PlaceID":
									session.setPlaceID(Integer.parseInt(varNode.getTextContent()));
									break;
								case "PlaceName":
									session.setPlaceName(varNode.getTextContent());
									break;
								case "PlaceCode":
									session.setPlaceCode(varNode.getTextContent());
									break;
								case "NbSeat":
									session.setNbSeat(Integer.parseInt(varNode.getTextContent()));
									break;
								case "NbSeatMin":
									session.setNbSeatMax(Integer.parseInt(varNode.getTextContent()));
									break;
								case "NbSeatMax":
									session.setNbSeatMax(Integer.parseInt(varNode.getTextContent()));
									break;
							}
						} // Fin de parcours des variables
						
						if (session.isComplete()) {
							sessionsList.add(session);
						}
						
					} // Fin de parcours des sessions
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
