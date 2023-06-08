package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.utils.PrintPDF;
import eu.strasbourg.service.council.constants.ProcurationModeEnum;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.ZipHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=reloadProcurations"
        },
        service = MVCResourceCommand.class
)
public class ReloadProcurationsResourceCommand implements MVCResourceCommand {

    /**
     * Log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /**
     * Service
     */
    private CouncilSessionLocalService councilSessionLocalService;

    /**
     * Params
     */
    private long councilSessionId;

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {
        // Creation du JSON de reponse
        JSONObject associatedProcuration = JSONFactoryUtil.createJSONObject();
        // Recuperation du parametre du portlet
        this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");

        try {
            // Recuperation du themeDisplay
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            // Recuperation du conseil
            CouncilSession councilSession = CouncilSessionLocalServiceUtil.getCouncilSession(councilSessionId);
            // Initialisation de la liste des elus et des procurations
            List<Official> officials = new ArrayList<>();
            List<Procuration> procurations = new ArrayList<>();

            if (councilSession != null) {
                // Recuperation des procurations du conseil + des elus actifs qui ont pour type ce conseil
                procurations = councilSession.getProcurations();
                officials = OfficialLocalServiceUtil.findByGroupIdAndIsActive(themeDisplay.getSiteGroupId(), true)
                        .stream()
                        .filter(o -> o.getCouncilTypes().contains(TypeLocalServiceUtil.fetchType(councilSession.getTypeId())))
                        .collect(Collectors.toList());
            }

            JSONArray officialsJSON = JSONFactoryUtil.createJSONArray();
            if (officials.size() != 0) {
                for (Official official : officials) {
                    // Recuperation de la procuration ouverte de l'elu sinon null
                    Procuration procuration = procurations.stream()
                            .filter(p -> p.getOfficialUnavailableId() == official.getOfficialId() && p.getEndHour() == null)
                            .findFirst()
                            .orElse(null);
                    if (procuration != null) {
                        // Si procuration ouverte on recupere les informations dont on a besoin
                        JSONObject officialJSON = JSONFactoryUtil.createJSONObject();
                        officialJSON.put("officialId", official.getOfficialId());
                        officialJSON.put("procurationId", procuration.getProcurationId());
                        officialJSON.put("hasProcuration", true);
                        officialJSON.put("officialFullName", official.getFullName());
                        officialJSON.put("procurationMode", procuration.getProcurationMode());
                        if (procuration.getProcurationMode() == ProcurationModeEnum.AUTRE.getId()) {
                            officialJSON.put("otherProcurationMode", procuration.getOtherProcurationMode());
                        }
                        officialJSON.put("presential", procuration.getPresential());
                        officialJSON.put("officialVoter", procuration.getOfficialVotersFullName());
                        officialsJSON.put(officialJSON);
                    } else {
                        // Sinon on envoie juste l'id de l'elu
                        JSONObject officialJSON = JSONFactoryUtil.createJSONObject();
                        officialJSON.put("officialId", official.getOfficialId());
                        officialJSON.put("hasProcuration", false);
                        officialsJSON.put(officialJSON);
                    }
                }
            }
            associatedProcuration.put("official", officialsJSON);

        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;

        try {
            writer = response.getWriter();
        } catch (IOException e) {
            _log.error(e);
        }

        // On passe le JSON dans la reponse pour l'utiliser dans le JS
        writer.print(associatedProcuration.toString());

        return false;
    }

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

}
