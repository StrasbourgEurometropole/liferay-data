package eu.strasbourg.utils.client.soap.tools;

import org.apache.commons.codec.Charsets;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ResponseParserTool {

    /**
     * Renvoie la valeur du Noeud demandé dans le document XML fourni
     */
    public static String getTextContentFromXML(Document doc, String nodeTagName) {
        String textContent = "";

        NodeList nodeList = doc.getElementsByTagName(nodeTagName);
        textContent = nodeList.item(0).getTextContent();

        return textContent;
    }

    /**
     * Converti un XML au format String en Document
     */
    public static Document parseResponseToDocument(String stringXml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        ByteArrayInputStream input = new ByteArrayInputStream(stringXml.getBytes(Charsets.UTF_8));

        return dBuilder.parse(input);
    }

}
