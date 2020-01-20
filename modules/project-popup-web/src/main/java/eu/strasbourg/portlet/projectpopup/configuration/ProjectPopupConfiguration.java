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

    //Est à faux tant qu'aucune configuration n'a été enregistrée
    @Meta.AD(name = "hasConfig", required = false)
    public boolean hasConfig();

    @Meta.AD(name = "popupTemplateId", required = false)
    String popupTemplateId();

    @Meta.AD(name = "disable", required = false)
    boolean disable();

    @Meta.AD(name = "nbFiles", required = false)
    String nbFiles();

    @Meta.AD(name = "sizeFile", required = false)
    String sizeFile();

    @Meta.AD(name = "typesFiles", required = false)
    String typesFiles();
}
