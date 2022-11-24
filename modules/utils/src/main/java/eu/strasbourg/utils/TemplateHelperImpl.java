package eu.strasbourg.utils;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import eu.strasbourg.utils.api.TemplateHelperService;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Locale;

/**
 * Implémentation du service DDMTemplateLocalService N'est qu'une couche accessible
 * par les templates FreeMarker qui délègue le travail à une classe helper
 */
@Component(immediate = true, property = {}, service = TemplateHelperService.class)
public class TemplateHelperImpl implements TemplateHelperService {

	@Override
	public DDMTemplate getDDMTemplateByGroupeIdAndName(long groupId, String name) {
		List<DDMTemplate> liste = DDMTemplateLocalServiceUtil.getTemplatesByGroupId(groupId);
		DDMTemplate template = liste.stream().filter(t -> StringHelper.compareIgnoringAccentuation(t.getName(Locale.FRANCE).toLowerCase(), name)).findFirst().orElse(null);
		return template;
	}
}
