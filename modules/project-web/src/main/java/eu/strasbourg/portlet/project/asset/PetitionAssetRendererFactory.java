package eu.strasbourg.portlet.project.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.PetitionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB},
        service = AssetRendererFactory.class
)
public class PetitionAssetRendererFactory extends BaseAssetRendererFactory<Petition> {

    private PetitionLocalService _petitionLocalService;

    public static final String TYPE = "petition";

    @Override
    public String getType() {
        return TYPE;
    }

    public PetitionAssetRendererFactory() {
        setClassName(Petition.class.getName());
        setLinkable(true);
        setPortletId(StrasbourgPortletKeys.PROJECT_WEB);
        setSearchable(true);
    }

    @Override
    public AssetRenderer<Petition> getAssetRenderer(long classPK, int type) throws PortalException {
        Petition entry = _petitionLocalService.getPetition(classPK);

        PetitionAssetRenderer petitionAssetRenderer = new PetitionAssetRenderer(entry);
        petitionAssetRenderer.setAssetRendererType(type);
        return petitionAssetRenderer;
    }

    @Reference(unbind="-")
    protected void setInterestLocalService(PetitionLocalService petitionLocalService){
        _petitionLocalService = petitionLocalService;
    }

}
