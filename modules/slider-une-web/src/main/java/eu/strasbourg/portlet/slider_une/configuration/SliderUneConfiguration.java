package eu.strasbourg.portlet.slider_une.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.slider_une.configuration.SliderUneConfiguration",
        localization = "content/Language", name = "portlet.slider_une.configuration.name")
public interface SliderUneConfiguration {

    @Meta.AD(name="classPKs", required = false)
    public String classPKs();

    @Meta.AD(name="showTags", required = false)
    public boolean showTags();

}
