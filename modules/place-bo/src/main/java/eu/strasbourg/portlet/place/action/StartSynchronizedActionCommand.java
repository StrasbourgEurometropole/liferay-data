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
package eu.strasbourg.portlet.place.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(immediate = true, property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
        "mvc.command.name=startSynchronized" }, service = MVCActionCommand.class)
public class StartSynchronizedActionCommand implements MVCActionCommand {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response)
            throws PortletException {
        try {
            //on vérifi qu'on a le droit de faire la synchronisation
            Boolean isAutorized = Boolean.parseBoolean(StrasbourgPropsUtil.getGMBActivated());
            if(isAutorized) {
                // Changement du groupId du contexte de la requête pour effectuer les actions dans Global
                ServiceContext sc = ServiceContextFactory.getInstance(request);
                sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());

                // Creation de l'entree d'historique de synchronisation
                GoogleMyBusinessHistoric googleMyBusinessHistoric = _googleMyBusinessHistoricLocalService.createGoogleMyBusinessHistoric(sc);

                // Effectue la synchronisation
                this._googleMyBusinessHistoricLocalService.doSynchronisation(sc, googleMyBusinessHistoric);

                // Sauvegarde de l'entree
                _googleMyBusinessHistoricLocalService.updateGoogleMyBusinessHistoric(googleMyBusinessHistoric, sc);

                // Envoie du mail de rapport
                googleMyBusinessHistoric.sendMail();
            }else{
                SessionErrors.add(request, "synchronise-forbidden");
                return false;
            }

        } catch (PortalException e) {
            _log.info(e);
        }

        return true;
    }

    @Reference(unbind = "-")
    protected void setGoogleMyBusinessHistoricLocalService(
            GoogleMyBusinessHistoricLocalService googleMyBusinessHistoricLocalService) {

        _googleMyBusinessHistoricLocalService = googleMyBusinessHistoricLocalService;
    }

    private GoogleMyBusinessHistoricLocalService _googleMyBusinessHistoricLocalService;
}