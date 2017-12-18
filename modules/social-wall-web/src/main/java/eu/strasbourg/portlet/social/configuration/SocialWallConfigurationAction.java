package eu.strasbourg.portlet.social.configuration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.social.configuration.SocialWallConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.SOCIAL_WALL_WEB },
	service = ConfigurationAction.class)
public class SocialWallConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/social-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String twitterAccount = ParamUtil.getString(actionRequest, "twitterAccount");
		setPreference(actionRequest, "twitterAccount", twitterAccount);

		String instagramClientId = ParamUtil.getString(actionRequest, "instagramClientId");
		setPreference(actionRequest, "instagramClientId", instagramClientId);
		
		String instagramClientSecret = ParamUtil.getString(actionRequest, "instagramClientSecret");
		setPreference(actionRequest, "instagramClientSecret", instagramClientSecret);
		
		String instagramToken = ParamUtil.getString(actionRequest, "instagramToken");
		setPreference(actionRequest, "instagramToken", instagramToken);

		String dailymotionAccountId = ParamUtil.getString(actionRequest, "dailymotionAccountId");
		setPreference(actionRequest, "dailymotionAccountId", dailymotionAccountId);

		String facebookToken = ParamUtil.getString(actionRequest, "facebookToken");
		setPreference(actionRequest, "facebookToken", facebookToken);

		String postCount = ParamUtil.getString(actionRequest, "postCount");
		setPreference(actionRequest, "postCount", postCount);
		
		String template = ParamUtil.getString(actionRequest,  "template");
		setPreference(actionRequest, "template", template);
		
		MultiVMPoolUtil.getPortalCache("twitter_cache").remove(twitterAccount);
		MultiVMPoolUtil.getPortalCache("twitter_cache")
			.remove(twitterAccount + "_last_update");
		
		MultiVMPoolUtil.getPortalCache("dailymotion_cache").remove(dailymotionAccountId);
		MultiVMPoolUtil.getPortalCache("dailymotion_cache")
			.remove(dailymotionAccountId + "_last_update");

		MultiVMPoolUtil.getPortalCache("instagram_cache").remove(instagramClientId);
		MultiVMPoolUtil.getPortalCache("instagram_cache")
			.remove(instagramClientId + "_last_update");

		MultiVMPoolUtil.getPortalCache("facebook_cache").remove(facebookToken);
		MultiVMPoolUtil.getPortalCache("facebook_cache")
			.remove(facebookToken + "_last_update");
		

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			SocialWallConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(SocialWallConfiguration.class);

			request.setAttribute("twitterAccount", configuration.twitterAccount());
			request.setAttribute("instagramClientId", configuration.instagramClientId());
			request.setAttribute("instagramClientSecret", configuration.instagramClientSecret());
			request.setAttribute("instagramToken", configuration.instagramToken());
			request.setAttribute("dailymotionAccountId", configuration.dailymotionAccountId());
			request.setAttribute("facebookToken", configuration.facebookToken());
			request.setAttribute("postCount", configuration.postCount());
			request.setAttribute("template", configuration.template());

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
