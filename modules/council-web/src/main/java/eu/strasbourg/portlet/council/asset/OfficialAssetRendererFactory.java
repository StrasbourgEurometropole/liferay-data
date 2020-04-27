package eu.strasbourg.portlet.council.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.OfficialLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_WEB
        },
        service = AssetRendererFactory.class
)
public class OfficialAssetRendererFactory extends BaseAssetRendererFactory<Official> {

    private OfficialLocalService officialLocalService;

    public static final String TYPE = "official";

    public OfficialAssetRendererFactory() {
        setClassName(Official.class.getName());
        setLinkable(true);
        setPortletId(StrasbourgPortletKeys.COUNCIL_WEB);
        setSearchable(true);
    }

    @Override
    public AssetRenderer<Official> getAssetRenderer(long classPK, int type)
            throws PortalException {
        Official entry = this.officialLocalService.getOfficial(classPK);

        OfficialAssetRenderer officialAssetRenderer = new OfficialAssetRenderer(entry);

        officialAssetRenderer.setAssetRendererType(type);

        return officialAssetRenderer;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Reference(unbind = "-")
    protected void setOfficialLocalService(OfficialLocalService officialLocalService) {
        this.officialLocalService = officialLocalService;
    }

}
