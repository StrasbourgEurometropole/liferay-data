package eu.strasbourg.service.place;

import eu.strasbourg.utils.StrasbourgPropsUtil;
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

public class MairieStateSOAPClient {

	public static long getWaitingTime(String codeMairie) throws Exception {

		// Convert Response To PoolSchedule Object
		long waitingTime = -1;

		String outputString = getWaitingSoap(codeMairie);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		ByteArrayInputStream input = new ByteArrayInputStream(
				outputString.getBytes("UTF-8"));
		Document doc = dBuilder.parse(input);

		NodeList waitingNodeList = doc.getElementsByTagName("ns2:realAvgWaitingTime");
		String waitingString = waitingNodeList.item(0).getTextContent();

		if (waitingString.equals("-")) {
			waitingTime = 0;
		} else {
			try {
				waitingTime = Long.parseLong(waitingString.split(":")[1]);
				waitingTime += Long.parseLong(waitingString.split(":")[0]) * 60;
			} catch (Exception ex) {
			}
		}

		return waitingTime;
	}

	/**
	 * Appelle le service SOAP de l'occupation des piscines, récupère la réponse sous forme de XML
	 */
	private static String getWaitingSoap(String codeMairie) throws IOException {
		//Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "";
		String wsURL = StrasbourgPropsUtil.getWaintingURL();
		URL url = new URL(wsURL);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		httpConn.setConnectTimeout(5000);
		httpConn.setReadTimeout(5000);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.esii.com/esirius/sitewaitingindicator/v1.0\">\n" +
				"   <soapenv:Header/>\n" +
				"   <soapenv:Body>\n" +
				"      <v1:getSitesIndicatorsBySites>\n" +
				"         <v1:siteCodeArray>\n" +
				"            <!--Zero or more repetitions:-->\n" +
				"            <v1:string>"+codeMairie+"</v1:string>\n" +
				"         </v1:siteCodeArray>\n" +
				"      </v1:getSitesIndicatorsBySites>\n" +
				"   </soapenv:Body>\n" +
				"</soapenv:Envelope> ";

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
	private String getTextContentFromXML(Document doc, String nodeTagName) {
		String textContent = "";

		NodeList nodeList = doc.getElementsByTagName(nodeTagName);
		textContent = nodeList.item(0).getTextContent();

		return textContent;
	}
}
