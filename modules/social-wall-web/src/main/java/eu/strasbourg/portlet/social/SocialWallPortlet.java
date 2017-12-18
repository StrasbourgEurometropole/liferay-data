package eu.strasbourg.portlet.social;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.social.configuration.SocialWallConfiguration;
import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.service.social.SocialService;
import eu.strasbourg.service.social.instagram.DailymotionThumbnailRatio;
import eu.strasbourg.service.social.twitter.Tweet;

@Component(immediate = true, configurationPid = "eu.strasbourg.portlet.social.configuration.SocialWallConfiguration", property = {
		"com.liferay.portlet.display-category=Strasbourg", "com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=social-wall-portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/social-view.jsp", "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class SocialWallPortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			SocialWallConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(SocialWallConfiguration.class);

			// Nombre de posts à afficher
			int postCount = configuration.postCount();

			List<SocialPost> allPosts = new ArrayList<SocialPost>();

			// Facebook posts
			String facebookToken = configuration.facebookToken();
			if (Validator.isNotNull(facebookToken)) {
				List<SocialPost> facebookPosts = socialService.getFacebookPosts(facebookToken, postCount);
				allPosts.addAll(facebookPosts);
			}

			// Tweets
			String twitterAccount = configuration.twitterAccount();
			if (Validator.isNotNull(twitterAccount)) {
				List<Tweet> tweets = socialService.getUserTweets(twitterAccount, postCount);
				allPosts.addAll(tweets);
			}
			// Instagram posts
			String instagramClientId = configuration.instagramClientId();
			String instagramClientSecret = configuration.instagramClientSecret();
			String instagramToken = configuration.instagramToken();
			if (Validator.isNotNull(instagramClientId) && Validator.isNotNull(instagramClientSecret) && Validator.isNotNull(instagramToken)) {
				List<SocialPost> instagramPosts = socialService.getInstagramPosts(instagramClientId, instagramClientSecret, instagramToken, postCount);
				allPosts.addAll(instagramPosts);
			}

			// Dailymotion videos
			String dailymotionAccountId = configuration.dailymotionAccountId();
			if (Validator.isNotNull(dailymotionAccountId)) {
				List<SocialPost> videos = socialService.getDailymotionVideos(dailymotionAccountId, postCount,
						DailymotionThumbnailRatio.SQUARE);
				allPosts.addAll(videos);
			}

			// Tri
			allPosts = allPosts.stream().sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate()))
					.collect(Collectors.toList());
			renderRequest.setAttribute("posts", allPosts);

			// Template
			String template = configuration.template();
			if (Validator.isNull(template)) {
				template = "default";
			}
			renderRequest.setAttribute("template", template);

		} catch (ConfigurationException e) {
			log.error(e);
		}

		super.render(renderRequest, renderResponse);
	}

	@Reference
	SocialService socialService;

}