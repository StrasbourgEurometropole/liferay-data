package eu.strasbourg.portlet.twitter.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.twitter.configuration.TwitterConfiguration",
	localization = "content/Language",
	name = "portlet.twitter.configuration.name")
public interface TwitterConfiguration {

	@Meta.AD(name="twitterAccount", deflt = "", required = false)
	public String twitterAccount();
	
	@Meta.AD(name="tweetCount", required = false)
	public long tweetCount();
	
}
