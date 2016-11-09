package eu.strasbourg.utils;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;

import eu.strasbourg.utils.api.LayoutHelperService;

/**
 * Implémentation du service LayoutHelperService
 * N'est qu'une couche accessible par les templates FreeMarker qui délègue le travail
 * à une classe helper
 */
@Component(
    immediate = true,
    property = {
    },
    service = LayoutHelperService.class
)
public class LayoutHelperImpl implements LayoutHelperService {

	@Override
	public String getLayoutPath(Layout layout, Locale locale) {
		return LayoutHelper.getLayoutPath(layout, locale);
	}

}
