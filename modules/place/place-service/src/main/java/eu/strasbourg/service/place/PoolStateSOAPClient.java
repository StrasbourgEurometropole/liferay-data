package eu.strasbourg.service.place;

import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.place.model.Place;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

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

	/**
	 * Renvoie la valeur d'occupation de la piscine/patinoire demandée
	 */
	private static long getOccupation(String poolCode) throws Exception {

		// Convert Response To PoolSchedule Object
		long occupation = -1;
		boolean exist = false;

		String outputString = getOccupationSoap(poolCode);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		ByteArrayInputStream input = new ByteArrayInputStream(
				outputString.getBytes("UTF-8"));
		Document doc = dBuilder.parse(input);

		NodeList jaugeNode = doc.getElementsByTagName("JaugeOccupation");
		String occupationString = jaugeNode.item(0).getTextContent();

		NodeList libelleNode = doc.getElementsByTagName("LibelleSalle");
		String libelleSalle = libelleNode.item(0).getTextContent();

		if(Validator.isNotNull(occupationString)) {
			occupation = Long.parseLong(occupationString);
		}
		if(Validator.isNotNull(libelleSalle)) {
			exist = true;
		}
		if(!exist){
			return -1;
		}

		return occupation;
	}


	/**
	 * Appelle le service SOAP de l'occupation des piscines, récupère la réposne sous forme de XML
	 */
	private static String getOccupationSoap(String poolCode) throws IOException {
		//Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "";
		String wsURL = "http://regal.strasbourg.eu/WSFrequentation/BCA_Webservice.asmx";
		URL url = new URL(wsURL);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection)connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		String xmlInput = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\">\n" +
				"   <soap:Header/>\n" +
				"   <soap:Body>\n" +
				"      <tem:BCA_WS_SALLE>\n" +
				"         <!--Optional:-->\n" +
				"         <tem:CodeSalle>"+poolCode+"</tem:CodeSalle>\n" +
				"      </tem:BCA_WS_SALLE>\n" +
				"   </soap:Body>\n" +
				"</soap:Envelope> \n";

		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length",
				String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		httpConn.setRequestMethod("GET");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		//Write the content of the request to the outputstream of the HTTP Connection.
		out.write(b);
		out.close();
		//Ready with sending the request.

		//Read the response.
		InputStreamReader isr = null;
		if (httpConn.getResponseCode() == 200) {
			isr = new InputStreamReader(httpConn.getInputStream());
		} else {
			isr = new InputStreamReader(httpConn.getErrorStream());
		}

		BufferedReader in = new BufferedReader(isr);

		//Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}

		return outputString;
	}

	/**
	 * Renvoie la valeur du Noeud demandé dnas le document XML fourni
	 */
	private static String getTextContentFromXML(Document doc, String nodeTagName) {
		String textContent = "";

		NodeList nodeList = doc.getElementsByTagName(nodeTagName);
		textContent = nodeList.item(0).getTextContent();

		return textContent;
	}
}
