package eu.strasbourg.portlet.formassembly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class FormAssemblyUtil {
	private String formAssemblyToken;
	private String baseURL;
	private String jsonAdminEndpoint;
	private String htmlEndpoint;
	
	public FormAssemblyUtil(String path, String token) {
		this.baseURL = path;
		this.formAssemblyToken = token;
		this.jsonAdminEndpoint = baseURL + "admin/api_v1/";
		this.htmlEndpoint = baseURL + "rest";
	}

	// Si jamais on souhaite travailler avec les réponses données au formulaire
	// private static String jsonEndpoint = baseUrl + "api_v1/";

	/**
	 * Retourne l'HTML d'un formulaire à partir de son id
	 * 
	 * @param formId
	 *            id du formulaire
	 * @param requestParameters
	 * @return L'HTML du formulaire
	 */
	public String getHTML(Integer formId, String requestParameters) {
		String html = null;
		String urlStr;

		try {
			if (requestParameters != null && requestParameters.length() > 0) {
				urlStr = htmlEndpoint + requestParameters;
			} else {
				urlStr = htmlEndpoint + "/forms/view/" + formId;
			}

			HttpURLConnection con = (HttpURLConnection) new URL(urlStr)
				.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.connect();

			BufferedReader rd = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			html = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}

	/**
	 * Récupère la liste de tous les formulaires
	 * 
	 * @return La liste de tous les formulaires
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject getFormsListJson()
		throws IOException, JSONException {
		InputStream is = new URL(jsonAdminEndpoint
			+ "forms/index.json?access_token=" + formAssemblyToken)
				.openStream();
		try {
			BufferedReader rd = new BufferedReader(
				new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = JSONFactoryUtil.createJSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	/**
	 * Permet d'ajouter les formulaires à la liste à partir d'un array JSON de
	 * "Form"
	 * 
	 * @param formList
	 * @param jsonArray
	 */
	public void addFormsToListFromJsonArray(
		List<FormAssemblyForm> formList, JSONArray jsonArray) {
		if (formList == null || jsonArray == null)
			return;
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonFormWrapper = jsonArray.getJSONObject(i);
			if (!jsonFormWrapper.isNull("Form")) {
				JSONObject jsonForm = jsonFormWrapper.getJSONObject("Form");
				// On n'ajoute que les formulaires publiés
				int displayStatus = jsonForm.getInt("display_status");
				if (displayStatus == 2) {
					FormAssemblyForm form = new FormAssemblyForm(
						jsonForm.getInt("id"), jsonForm.getString("name"));
					formList.add(form);
				}
			}
		}
	}

	/**
	 * Lit tout ce qui est "dans" le reader et le transforme en String
	 * 
	 * @param rd
	 * @return
	 * @throws IOException
	 */
	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * Récupère la ServletRequest originale afin de pouvoir récupérer des
	 * attributs non-préfixés
	 * 
	 * @param request
	 *            ServletRequest provenant de la renderRequest
	 * @return ServletRequest originale
	 */
	public HttpServletRequest getOriginalServletRequest(
		HttpServletRequest request) {

		HttpServletRequest originalRequest = request;

		while (originalRequest.getClass().getName()
			.startsWith("com.liferay.")) {
			originalRequest = (HttpServletRequest) ((HttpServletRequestWrapper) originalRequest)
				.getRequest();
		}

		return originalRequest;
	}

}
