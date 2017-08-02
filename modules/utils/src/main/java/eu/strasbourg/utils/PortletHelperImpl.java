package eu.strasbourg.utils;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.utils.api.PortletHelperService;

@Component(
    immediate = true,
    property = {
    },
    service = PortletHelperService.class
)
public class PortletHelperImpl implements PortletHelperService {

	/**
	 * Retourne le titre du portlet configuré dans la configuration Look And
	 * Feel s'il existe et si "utiliser le titre personnalisé" est coché, sinon
	 * à partir de la clé de traduction passée en paramètre
	 */
	public String getPortletTitle(String key, PortletRequest request) {
		return PortletHelper.getPortletTitle(key, request);
	}
}
