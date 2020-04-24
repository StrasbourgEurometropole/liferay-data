package eu.strasbourg.portlet.council.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "Strasbourg",
        scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
        id = "eu.strasbourg.portlet.council.configuration.CouncilConfiguration",
        localization = "content/Language"
)
public interface CouncilConfiguration {

    @Meta.AD(name="useSkypeView", deflt = "false", required = false)
    String useSkypeView();

}
