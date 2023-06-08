package eu.strasbourg.portlet.resid.dossier;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.net.HttpURLConnection;

public class DossiersWebService {

	private static final Log _log = LogFactoryUtil.getLog(DossiersWebService.class);

	public static DossiersResponse getResponse(String identifiantOpenId) {
		return getResponse(identifiantOpenId, StrasbourgPropsUtil.getWebServiceDefaultTimeout());
	}

	public static DossiersResponse getResponse(String identifiantOpenId, int timeOut) {
		DossiersResponse dossiersResponse = null;
		try {
			// On récupère le JSON
			String url = StrasbourgPropsUtil.getResidantWebServiceURL() + identifiantOpenId;
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url, timeOut);

			if(Validator.isNull(httpConn.getContentType())) {
				_log.error("RESID : Appel au webservice ne renvoie pas de Content-Type, possible Time Out");
			}
			else if (httpConn.getContentType().contains("application/json")) {
				dossiersResponse = new DossiersResponse(PasserelleHelper.readJson(httpConn));
			}
		} catch (Exception ex) {
			_log.error("identifiantOpenId : " + identifiantOpenId + ", timeOut : " + timeOut, ex);
		}

		return dossiersResponse;
	}

}
