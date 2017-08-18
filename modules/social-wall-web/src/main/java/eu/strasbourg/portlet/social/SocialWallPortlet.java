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
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.social.configuration.SocialWallConfiguration;
import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.service.social.SocialService;
import eu.strasbourg.service.social.instagram.DailymotionThumbnailRatio;
import eu.strasbourg.service.social.twitter.Tweet;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.social.configuration.SocialWallConfiguration",
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=social-wall-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/social-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class SocialWallPortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			SocialWallConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(SocialWallConfiguration.class);

			// Nombre de posts à afficher
			int postCount = configuration.postCount();

			// Facebook posts
			String facebookToken = configuration.facebookToken();
			List<SocialPost> facebookPosts = socialService
				.getFacebookPosts(facebookToken, postCount);
			
			// Tweets
			String twitterAccount = configuration.twitterAccount();
			List<Tweet> tweets = socialService.getUserTweets(twitterAccount,
				postCount);

			// Instagram posts
			String instagramAccount = configuration.twitterAccount();
			List<SocialPost> instagramPosts = socialService
				.getInstagramPosts(instagramAccount, postCount);

			// Dailymotion videos
			String dailymotionAccountId = configuration.dailymotionAccountId();
			List<SocialPost> videos = socialService.getDailymotionVideos(
				dailymotionAccountId, postCount,
				DailymotionThumbnailRatio.SQUARE);

			List<SocialPost> allPosts = new ArrayList<SocialPost>();
			allPosts.addAll(facebookPosts);
			allPosts.addAll(tweets);
			allPosts.addAll(instagramPosts);
			allPosts.addAll(videos);

			allPosts = allPosts.stream()
				.sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate()))
				.collect(Collectors.toList());

			renderRequest.setAttribute("posts", allPosts);

		} catch (ConfigurationException e) {
			log.error(e);
		}

		super.render(renderRequest, renderResponse);
	}

	@Reference
	SocialService socialService;

}