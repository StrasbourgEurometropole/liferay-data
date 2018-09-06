package eu.strasbourg.portlet.projectpopup.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author alexandre.quere
 */
@ExtendedObjectClassDefinition(category = "Strasbourg",
        scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id="eu.strasbourg.portlet.project.projectpopup.ProjectPopupConfiguration",
        localization = "content/Language",
        name = "portlet.project.popup.configuration.name")
public interface ProjectPopupConfiguration {

    @Meta.AD(name = "popupTemplateId", required = false)
    String popupTemplateId();
}
