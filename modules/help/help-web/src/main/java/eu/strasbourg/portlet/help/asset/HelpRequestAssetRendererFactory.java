package eu.strasbourg.portlet.help.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.ResourceBundle;


@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.HELP_WEB},
        service = AssetRendererFactory.class
)
public class HelpRequestAssetRendererFactory extends BaseAssetRendererFactory<HelpRequest> {

    public static final String TYPE = "helpRequest";

    public HelpRequestAssetRendererFactory() {
        setClassName(HelpRequest.class.getName());
        setLinkable(true);
        setPortletId(StrasbourgPortletKeys.HELP_WEB);
        setSearchable(true);
    }

    @Override
    public AssetRenderer<HelpRequest> getAssetRenderer(long classPK, int type)
            throws PortalException {
        HelpRequest entry = _helpRequestLocalService.getHelpRequest(classPK);

        HelpRequestAssetRenderer helpRequestAssetRenderer =
                new HelpRequestAssetRenderer(entry);

        helpRequestAssetRenderer.setAssetRendererType(type);

        return helpRequestAssetRenderer;

    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Notes : surcharge de la méthode pour enlever le préfix du className "model.resource" non présent sur les modules
     * 			custom en 7.0
     */
    // Toujours necessaire en 7.2 ?
    @Override
    public String getTypeName(Locale locale) {
        String key = getClassName();

        String value = LanguageUtil.get(locale, key, null);

        String portletId = getPortletId();

        if ((value == null) && (portletId != null)) {
            PortletBag portletBag = PortletBagPool.get(portletId);

            ResourceBundle resourceBundle = portletBag.getResourceBundle(
                    locale);

            if (resourceBundle != null) {
                value = ResourceBundleUtil.getString(resourceBundle, key);
            }
        }

        if (value == null) {
            value = getClassName();
        }

        return value;
    }

    private HelpRequestLocalService _helpRequestLocalService;

    @Reference(unbind = "-")
    protected void setInterestLocalService(HelpRequestLocalService helpRequestLocalService) {
        _helpRequestLocalService = helpRequestLocalService;
    }
}