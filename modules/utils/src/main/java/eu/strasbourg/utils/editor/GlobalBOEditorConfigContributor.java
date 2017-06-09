package eu.strasbourg.utils.editor;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	property = { "editor.name=ckeditor",
		"javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_BO,
		"javax.portlet.name=" + StrasbourgPortletKeys.EDITION_BO,
		"javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"javax.portlet.name=" + StrasbourgPortletKeys.LINK_BO,
		"javax.portlet.name=" + StrasbourgPortletKeys.VIDEO_BO,
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"javax.portlet.name=" + StrasbourgPortletKeys.OFFICIAL_BO,
		"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO},
	service = EditorConfigContributor.class)
public class GlobalBOEditorConfigContributor
	extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(JSONObject jsonObject,
		Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		JSONArray toolbarConfiguration = jsonObject
			.getJSONArray("toolbar_liferay");
		jsonObject.put("toolbar_phone", toolbarConfiguration);
		jsonObject.put("toolbar_simple", toolbarConfiguration);
	}

}
