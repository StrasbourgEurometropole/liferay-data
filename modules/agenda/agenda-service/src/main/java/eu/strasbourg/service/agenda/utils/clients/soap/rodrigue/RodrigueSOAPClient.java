package eu.strasbourg.service.agenda.utils.clients.soap.rodrigue;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.agenda.custom.beans.RodrigueEventSession;
import eu.strasbourg.service.agenda.utils.clients.soap.rodrigue.constants.RodrigueSOAPConstants;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.client.soap.BaseSOAPClient;
import eu.strasbourg.utils.client.soap.beans.SOAPEnvelope;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RodrigueSOAPClient extends BaseSOAPClient {

    private static final Log log = LogFactoryUtil.getLog(RodrigueSOAPClient.class.getName());

    public RodrigueSOAPClient() {
        String rodrigueAPIURL = StrasbourgPropsUtil.getRodrigueAPIURL();
        this.setWebServiceURL(rodrigueAPIURL);
    }

    /**
     * Renvoie les séances et ses informations (disponibilités) d'une manifestation.
     * DataSet manif/seance/lieu + contrainte
     */
    public List<RodrigueEventSession> getSessionListOfEvent(String eventID) {
        List<RodrigueEventSession> results = new ArrayList<>();
        try {
            this.initGetSessionListOfEventEnvelope(eventID);
            Document docResult = this.executeRequest();
            results = this.parseGetSessionListOfEventResults(docResult);
        } catch (ParseException e) {
            log.error("Error during session date parsing ", e);
        } catch (Exception e) {
            log.error("Error during session Soap request for event " + eventID, e);
        }
        return results;
    }

    /**
     * Initilise la l'envelope SOAP
     * Notes : utilisation ici d'un XML puis d'un toString mais rien n'empèche d'utiliser directement un String
     */
    private void initGetSessionListOfEventEnvelope(String eventID) {
        // Attributs de l'enveloppe
        Map<String, String> envelopeAttributes = new HashMap<>();
        envelopeAttributes.put(RodrigueSOAPConstants.ATTRIBUTE_ENV_NAME, RodrigueSOAPConstants.ATTRIBUTE_ENV_VALUE);
        envelopeAttributes.put(RodrigueSOAPConstants.NAMESPACE, RodrigueSOAPConstants.NAMESPACE_URI);

        // Body (paramètres) de l'enveloppe
        String structureID = StrasbourgPropsUtil.getRodrigueOPSStructureID();
        String languageId = "1";
        String enveloppeBody =
                "<rod:GetSessionListOfEvent>\n" +
                "   <rod:StructureID>" + structureID + "</rod:StructureID>\n" +
                "   <rod:LanguageID>" + languageId + "</rod:LanguageID>\n" +
                "   <rod:EventID>" + eventID + "</rod:EventID>\n" +
                "</rod:GetSessionListOfEvent>\n";

        // Création de l'enveloppe
        SOAPEnvelope soapEnvelope = new SOAPEnvelope(envelopeAttributes, enveloppeBody);
        this.setSoapEnvelope(soapEnvelope);
    }

    /**
     * Récupère depuis une réponse XML les sessions au format RodrigueEventSession
     * @throws ParseException Erreur durant le parsage de la date d'une session
     */
    public List<RodrigueEventSession> parseGetSessionListOfEventResults(Document xmlResults) throws ParseException {
        List<RodrigueEventSession> sessionsList = new ArrayList<>();

        NodeList sessionsNodes = xmlResults.getElementsByTagName("MyDS");
        Node sessionNode;
        RodrigueEventSession session;

        for (int i = 0; i < sessionsNodes.getLength(); i++) {
            session = new RodrigueEventSession();

            sessionNode = sessionsNodes.item(i);
            NodeList varNodes = sessionNode.getChildNodes();

            // Parcours des variables d'une session
            for (int j = 0; j < varNodes.getLength(); j++) {
                Node varNode = varNodes.item(j);
                String varName = varNode.getNodeName();

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
        }

        return sessionsList;
    }

}
