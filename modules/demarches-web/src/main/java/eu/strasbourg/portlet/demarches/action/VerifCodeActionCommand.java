/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.strasbourg.portlet.demarches.action;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.io.IOException;
import java.net.HttpURLConnection;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.DEMARCHES_WEB,
		"mvc.command.name=verifCode" },
	service = MVCActionCommand.class)
public class VerifCodeActionCommand
	implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		String codeSuivi = ParamUtil.getString(request, "codeSuivi");
		String url = StrasbourgPropsUtil.getDemarcheSuiviURL().replace("[CODE_SUIVI]", codeSuivi.trim().replaceAll("\\s", ""));
		try {
			// On récupère le JSON
			HttpURLConnection httpConn = PasserelleHelper.readFromURL(url);
			if(httpConn.getResponseCode() == 200) {
				try {
					response.sendRedirect(url);
				} catch (IOException e) {
					_log.error(e.getMessage() + " : " + url);
				}
			}else{
				request.setAttribute("codeSuivi", codeSuivi);
				if(httpConn.getResponseCode() == 404) {
					request.setAttribute("errorCodeSuivi", "Ce code de suivi n'existe pas");
				}else {
					request.setAttribute("errorCodeSuivi", "Service momentanément indisponible");
				}
			}
		} catch (IOException | JSONException ex) {
			_log.error(ex.getMessage(), ex);
		}
		return true;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass());
}