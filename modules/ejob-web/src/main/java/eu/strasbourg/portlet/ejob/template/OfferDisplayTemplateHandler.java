package eu.strasbourg.portlet.ejob.template;

import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;


@Component(
	immediate = true,
	property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.EJOB_WEB
    },
	service = TemplateHandler.class
)
public class OfferDisplayTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return Offer.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return "Template Offre";
	}

	@Override
	public String getResourceName() {
		return StrasbourgPortletKeys.EJOB_WEB;
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
		long classPK, String language, Locale locale) throws Exception {
		Map<String, TemplateVariableGroup> templateVariableGroups = super.getTemplateVariableGroups(
			classPK, language, locale);
		
		
		TemplateVariableGroup fieldsTemplateVariableGroup =
			templateVariableGroups.get("fields");
		fieldsTemplateVariableGroup.empty();
		
		fieldsTemplateVariableGroup.addCollectionVariable(
			"Offers", List.class, PortletDisplayTemplateManager.ENTRIES,
			"Offer", Offer.class, "currentOffer", "getPost(locale)");
		
		fieldsTemplateVariableGroup.addVariable("Offer", Offer.class, "entry");
		fieldsTemplateVariableGroup.addVariable("Featured", Boolean.class, "isFeatured");
		
		return templateVariableGroups;
	}
}