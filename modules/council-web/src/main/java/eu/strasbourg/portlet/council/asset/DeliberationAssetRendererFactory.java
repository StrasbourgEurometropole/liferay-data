package eu.strasbourg.portlet.council.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.DeliberationLocalService;
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
public class DeliberationAssetRendererFactory extends BaseAssetRendererFactory<Deliberation> {

    private DeliberationLocalService deliberationLocalService;

    public static final String TYPE = "deliberation";

    public DeliberationAssetRendererFactory() {
        setClassName(Deliberation.class.getName());
        setLinkable(true);
        setPortletId(StrasbourgPortletKeys.COUNCIL_WEB);
        setSearchable(true);
    }

    @Override
    public AssetRenderer<Deliberation> getAssetRenderer(long classPK, int type)
            throws PortalException {
        Deliberation entry = this.deliberationLocalService.getDeliberation(classPK);

        DeliberationAssetRenderer deliberationAssetRenderer = new DeliberationAssetRenderer(entry);

        deliberationAssetRenderer.setAssetRendererType(type);

        return deliberationAssetRenderer;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

}
