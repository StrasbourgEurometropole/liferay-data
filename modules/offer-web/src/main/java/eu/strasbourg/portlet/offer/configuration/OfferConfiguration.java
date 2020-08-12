package eu.strasbourg.portlet.offer.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.offer.configuration.OfferConfiguration", localization = "content/Language", name = "portlet.offer.configuration.name")
public interface OfferConfiguration {
	
	@Meta.AD(name="introduction", deflt = "", required = false)
	public String introduction();

	@Meta.AD(name="text", deflt = "", required = false)
	public String text();

	@Meta.AD(name="url", deflt = "", required = false)
	public String url();
}