package eu.strasbourg.portlet.helppopup.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author alexandre.quere
 */
@ExtendedObjectClassDefinition(category = "Strasbourg",
        scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id="eu.strasbourg.portlet.help.helppopup.HelpPopupConfiguration",
        localization = "content/Language",
        name = "portlet.help.popup.configuration.name")
public interface HelpPopupConfiguration {

    //Est à faux tant qu'aucune configuration n'a été enregistrée
    @Meta.AD(name = "hasConfig", required = false)
    public boolean hasConfig();

    @Meta.AD(name = "popupTemplateId", required = false)
    String popupTemplateId();
}
