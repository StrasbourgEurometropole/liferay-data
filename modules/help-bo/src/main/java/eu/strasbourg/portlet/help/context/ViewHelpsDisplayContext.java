package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewHelpsDisplayContext extends ViewListBaseDisplayContext<Initiative> {

	private List <Initiative> _helps;
	private Initiative _help;
	
	public ViewHelpsDisplayContext(RenderRequest request, RenderResponse response) {
		super(Initiative.class, request, response);
	}
	
	public Initiative getHelp() {
		long helpId = ParamUtil.getLong(_request, "helpId");
		if (_help == null && helpId > 0) {
			_help = InitiativeLocalServiceUtil.fetchInitiative(helpId);
		}
		return _help;
	}

	public List<Initiative> getHelps() throws PortalException {
		if (this._helps == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());
			this._helps = createObjectList(hits);
		}
		return this._helps;
	}

	private List<Initiative> createObjectList(Hits hits) {
		// Création de la liste d'objet
		List<Initiative> results = new ArrayList<>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				Initiative help = InitiativeLocalServiceUtil.fetchInitiative(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (help != null) {
					results.add(help);
				}
			}
		}
		return results;
	}

	/**
	 * Retourne la liste des initiatives correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<Initiative> getAllHelps() throws PortalException {
		Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

		// Création de la liste d'objet
		return createObjectList(hits);
	}
	
	/**
	 * Retourne la liste des PK de toutes les initiatives
	 * @return liste de PK (ex: "1,5,7,8")
	 */
	public String getAllHelpIds() throws PortalException {
		String helpIds = "";
		for (Initiative help : this.getAllHelps()) {
			if (helpIds.length() > 0) {
				helpIds += ",";
			}
			helpIds += help.getInitiativeId();
		}
		return helpIds;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.HELP_BO, StrasbourgPortletKeys.HELP_BO,
			actionId);
	}

}
