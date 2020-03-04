package eu.strasbourg.portlet.oidc.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewAnonymisationHistoricsDisplayContext extends ViewListBaseDisplayContext<AnonymisationHistoric> {

	private List<AnonymisationHistoric> _anonymisationHistorics;

	public ViewAnonymisationHistoricsDisplayContext(RenderRequest request,
                                                    RenderResponse response) {
		super(AnonymisationHistoric.class, request, response);
	}

	public List<AnonymisationHistoric> getAnonymisationHistorics() throws PortalException {
		if (this._anonymisationHistorics == null) {
			Hits hits = getHits(this._themeDisplay.getCompanyGroupId());

			// Création de la liste d'objet
			List<AnonymisationHistoric> results = new ArrayList<AnonymisationHistoric>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					AnonymisationHistoric anonymisationHistoric = AnonymisationHistoricLocalServiceUtil.fetchAnonymisationHistoric(
							GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (anonymisationHistoric != null) {
						results.add(anonymisationHistoric);
					}
				}
			}
			this._anonymisationHistorics = results;
		}
		return this._anonymisationHistorics;
	}

}
