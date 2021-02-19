package eu.strasbourg.portlet.help.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
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
public class HelpProposalAssetRendererFactory extends BaseAssetRendererFactory<HelpProposal> {

    public static final String TYPE = "helpProposal";

    public HelpProposalAssetRendererFactory() {
        setClassName(HelpProposal.class.getName());
        setLinkable(true);
        setPortletId(StrasbourgPortletKeys.HELP_WEB);
        setSearchable(true);
    }

    @Override
    public AssetRenderer<HelpProposal> getAssetRenderer(long classPK, int type)
            throws PortalException {
        HelpProposal entry = _helpProposalLocalService.getHelpProposal(classPK);

        HelpProposalAssetRenderer helpProposalAssetRenderer =
                new HelpProposalAssetRenderer(entry);

        helpProposalAssetRenderer.setAssetRendererType(type);

        return helpProposalAssetRenderer;

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

    private HelpProposalLocalService _helpProposalLocalService;

    @Reference(unbind = "-")
    protected void setInterestLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }
}
