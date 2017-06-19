package eu.strasbourg.portlet.twitter.configuration;

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
	configurationPid = "eu.strasbourg.portlet.twitter.configuration.TwitterConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.TWITTER_WEB },
	service = ConfigurationAction.class)
public class TwitterConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration/twitter-configuration.jsp";
	}

	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String twitterAccount = ParamUtil.getString(actionRequest, "twitterAccount");
		setPreference(actionRequest, "twitterAccount", twitterAccount);

		String tweetCount = ParamUtil.getString(actionRequest, "tweetCount");
		setPreference(actionRequest, "tweetCount", tweetCount);
		
		// On vide le cache
		MultiVMPoolUtil.getPortalCache("twitter_cache").remove(twitterAccount);
		MultiVMPoolUtil.getPortalCache("twitter_cache")
			.remove(twitterAccount + "_last_update");

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			TwitterConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(TwitterConfiguration.class);

			request.setAttribute("twitterAccount", configuration.twitterAccount());

			request.setAttribute("tweetCount", configuration.tweetCount());

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
