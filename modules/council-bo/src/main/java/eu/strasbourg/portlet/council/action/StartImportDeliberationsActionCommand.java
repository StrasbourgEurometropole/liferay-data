/**
 * Copyright 2000-present Liferay, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.constants.DeliberationDataConstants;
import eu.strasbourg.service.council.service.DeliberationLocalService;
import eu.strasbourg.utils.ImportCsvHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(immediate = true, property = {"javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
        "mvc.command.name=startImportDeliberations"}, service = MVCActionCommand.class)
public class  StartImportDeliberationsActionCommand implements MVCActionCommand {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    private final String ERROR_INFO = "</br></br></br>  Le fichier doit avoir pour header exact  : ORDER;TITLE " +
            "</br> Il doit y avoir deux colonnes s\u00e9par\u00e9es par un point-virgule et le fichier doit \u00eatre encod\u00e9 en UTF8 et l'extension doit \u00eatre csv";

    private DeliberationLocalService deliberationLocalService;

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(DeliberationLocalService deliberationLocalService) {

        this.deliberationLocalService = deliberationLocalService;
    }

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

        String extension = StringUtils.EMPTY;
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        try {
            UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
            File deliberationsCsv = uploadRequest.getFile("deliberations-file");

            // Vérification du header du CSV et de l'extension
            String filename = deliberationsCsv.getName();
            int pos = filename.lastIndexOf('.');
            extension = pos > 0 ? filename.substring(pos + 1) : "";

            boolean isValid = validate(request, extension, deliberationsCsv);
            if (!isValid) {
                prepareErrorResponse(request, response, themeDisplay);
                return false;
            }

            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(DeliberationDataConstants.DELIBERATIONS_HEADER_MAPPING).withDelimiter(';');
            CSVParser csvFileParser = CSVParser.parse(deliberationsCsv, StandardCharsets.UTF_8, csvFileFormat);

            List<Map<String, String>> recordsListMap = new ArrayList<>();
            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            if (csvRecords.size() > 0) {
                for (int i = 1; i < csvRecords.size(); i++) {
                    CSVRecord record = csvRecords.get(i);
                    recordsListMap.add(record.toMap());
                }
            }

            long councilSessionId = ParamUtil.getLong(request, "councilSessionId");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(request);

            // Import des données du fichier et gestion en base de données
            String errorParse = deliberationLocalService.importData(recordsListMap, serviceContext, councilSessionId, themeDisplay);
            if (Validator.isNull(errorParse)) {
                SessionMessages.add(request, "import-successful");
            } else {
                errorParse += ERROR_INFO;
                SessionErrors.add(request, "error-parse-order");
                request.setAttribute("errorParse", errorParse);
                prepareErrorResponse(request, response, themeDisplay);
                return false;
            }

        } catch (IOException | PortalException e) {
            _log.error(e);
            return false;
        }
        return true;
    }

    /**
     * Permet de préparer la réponse dans le cas d'une erreur et de rester sur la même page
     */
    private void prepareErrorResponse(ActionRequest request, ActionResponse response, ThemeDisplay themeDisplay) {

        // Si pas valide : on reste sur la page d'édition
        PortalUtil.copyRequestParameters(request, response);

        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
        PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                PortletRequest.RENDER_PHASE);

        response.setRenderParameter("returnURL", returnURL.toString());
        response.setRenderParameter("cmd", "startImportDeliberations");
        response.setRenderParameter("mvcPath", "/council-bo-import-deliberation.jsp");
    }

    /**
     * Effectue les vérifications sur le header
     */
    private boolean validate(ActionRequest actionRequest, String extension, File deliberationsCsv) throws IOException {

        String errorCsvCheck = ImportCsvHelper.csvCheckHeader(deliberationsCsv, DeliberationDataConstants.DELIBERATIONS_HEADER_MAPPING);
        if (Validator.isNotNull(errorCsvCheck) || !extension.equals("csv")) {
            errorCsvCheck += ERROR_INFO;
            SessionErrors.add(actionRequest, "error-import-deliberations");
            actionRequest.setAttribute("error", errorCsvCheck);

            _log.error(errorCsvCheck);
            return false;
        }
        return true;
    }
}
