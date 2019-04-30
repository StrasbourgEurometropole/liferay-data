package eu.strasbourg.portlet.gtfs.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewImportHistoricsDisplayContext extends ViewListBaseDisplayContext<ImportHistoric> {

	private List <ImportHistoric> _importHistorics;
	
	public ViewImportHistoricsDisplayContext(RenderRequest request, RenderResponse response) {
		super(ImportHistoric.class, request, response);
	}
	
	public List<ImportHistoric> getImportHistorics() throws PortalException {
		if (this._importHistorics == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<ImportHistoric> results = new ArrayList<ImportHistoric>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					ImportHistoric importHistoric = ImportHistoricLocalServiceUtil.fetchImportHistoric(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (importHistoric != null) {
						results.add(importHistoric);
					}
				}
			}
			this._importHistorics = results;
		}
		return this._importHistorics;
	}
	
	
	/**
	 * Retourne la liste des entrees d'historique correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<ImportHistoric> getAllImportHistorics() throws PortalException {
		Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

		// Création de la liste d'objet
		List<ImportHistoric> results = new ArrayList<ImportHistoric>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				ImportHistoric importHistoric = ImportHistoricLocalServiceUtil
						.fetchImportHistoric(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (importHistoric != null) {
					results.add(importHistoric);
				}
			}
		}
		return results;
	}
	
	/**
	 * Retourne la liste des PK de toutes les entrees d'historique
	 * @return liste de PK (ex: "1,5,7,8")
	 */
	public String getAllImportHistoricIds() throws PortalException {
		String importHistoricIds = "";
		for (ImportHistoric importHistoric : this.getAllImportHistorics()) {
			if (importHistoricIds.length() > 0) {
				importHistoricIds += ",";
			}
			importHistoricIds += importHistoric.getImportHistoricId();
		}
		return importHistoricIds;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.GTFS_BO, StrasbourgPortletKeys.GTFS_BO,
			actionId);
	}
	
}
