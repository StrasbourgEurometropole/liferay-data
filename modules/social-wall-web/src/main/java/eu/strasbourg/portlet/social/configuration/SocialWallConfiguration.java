package eu.strasbourg.portlet.social.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.social.configuration.SocialWallConfiguration",
	localization = "content/Language",
	name = "portlet.social.configuration.name")
public interface SocialWallConfiguration {
	
	@Meta.AD(name="twitterAccount", required = false)
	public String twitterAccount();
	
	@Meta.AD(name="instagramAccount", required = false)
	public String instagramAccount();
	
	@Meta.AD(name="dailymotionAccountId", required = false)
	public String dailymotionAccountId();

	@Meta.AD(name="facebookToken", required = false)
	public String facebookToken();

	@Meta.AD(name="postCount", required = false)
	public int postCount();
	
	@Meta.AD(name="template", deflt="default", required= false)
	public String template();
}
