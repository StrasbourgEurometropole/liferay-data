package eu.strasbourg.portlet.place.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalServiceUtil;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewGoogleDisplayContext  extends ViewListBaseDisplayContext<GoogleMyBusinessHistoric> {

    private List<GoogleMyBusinessHistoric> _googleMyBusinessHistorics;

    public ViewGoogleDisplayContext(RenderRequest request,
                                    RenderResponse response) {
        super(GoogleMyBusinessHistoric.class, request, response);
    }

    public List<GoogleMyBusinessHistoric> getGoogleMyBusinessHistorics() throws PortalException {
        if (this._googleMyBusinessHistorics == null) {
            Hits hits = getHits(this._themeDisplay.getCompanyGroupId());

            // Création de la liste d'objet
            List<GoogleMyBusinessHistoric> results = new ArrayList<GoogleMyBusinessHistoric>();
            if (hits != null) {
                for (Document document : hits.getDocs()) {
                    GoogleMyBusinessHistoric googleMyBusinessHistoric = GoogleMyBusinessHistoricLocalServiceUtil.fetchGoogleMyBusinessHistoric(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (googleMyBusinessHistoric != null) {
                        results.add(googleMyBusinessHistoric);
                    }
                }
            }
            this._googleMyBusinessHistorics = results;
        }
        return this._googleMyBusinessHistorics;
    }

    /**
     * @return True si on peut faire la synchronisation
     */
    public boolean canSynchronise() {
        return Boolean.parseBoolean(StrasbourgPropsUtil.getGMBActivated());
    }
}
