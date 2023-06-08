package eu.strasbourg.utils.client.soap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.utils.client.soap.beans.SOAPEnvelope;
import eu.strasbourg.utils.client.soap.tools.ResponseParserTool;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public abstract class BaseSOAPClient {

    private static final Log log = LogFactoryUtil.getLog(BaseSOAPClient.class.getName());

    private String webServiceURL;
    private SOAPEnvelope soapEnvelope;

    protected final Document executeRequest() {
        String responseString;
        StringBuilder outputString = new StringBuilder();
        Document responseDocument = null;

        try {
            URL url = new URL(this.webServiceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            String xmlInput = this.soapEnvelope.toString();

            byte[] buffer;
            buffer = xmlInput.getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();

            // Set the appropriate HTTP parameters.
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
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
            InputStreamReader isr;
            if (httpConn.getResponseCode() == 200) {
                isr = new InputStreamReader(httpConn.getInputStream());
            } else {
                isr = new InputStreamReader(httpConn.getErrorStream());
            }

            BufferedReader in = new BufferedReader(isr);

            //Write the SOAP message response to a String.
            while ((responseString = in.readLine()) != null) {
                outputString.append(responseString);
            }

            // Parse de la réponse en document XML
            responseDocument = ResponseParserTool.parseResponseToDocument(outputString.toString());
        } catch (MalformedURLException e) {
            log.error("Given SOAP URL is not recognize as proper URL ", e);
        } catch (ParserConfigurationException | SAXException e) {
            log.error("Error during response parsing occurs" , e);
        }  catch (IOException e) {
            log.error(e);
        }

        return responseDocument;
    }

    public void setWebServiceURL(String webServiceURL) {
        this.webServiceURL = webServiceURL;
    }

    public void setSoapEnvelope(SOAPEnvelope soapEnvelope) {
        this.soapEnvelope = soapEnvelope;
    }

}
