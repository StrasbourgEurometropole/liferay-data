package eu.strasbourg.portlet.comment.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.comment.configuration.CommentConfiguration", localization = "content/Language", name = "portlet.comment.configuration.name")
public interface CommentConfiguration {
	
	@Meta.AD(name="orderBy", deflt = "", required = false)
	public String orderBy();
}
