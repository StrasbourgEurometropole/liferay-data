package eu.strasbourg.portlet.form_send.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import java.util.Map;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.form_send.configuration.FormSendConfiguration",
        localization = "content/Language", name = "portlet.form_send.configuration.name")
public interface FormSendConfiguration {

    @Meta.AD(name = "title", deflt = "", required = false)
    public String title();

    @Meta.AD(name = "nbEntries", deflt = "", required = false)
    public String nbEntries();

    @Meta.AD(name = "recordSetId", deflt = "", required = false)
    public String recordSetId();

    @Meta.AD(name = "fieldsSelected", required = false)
    public String fieldsSelected();

    @Meta.AD(name = "newLibs", required = false)
    public String newLibs();

}
