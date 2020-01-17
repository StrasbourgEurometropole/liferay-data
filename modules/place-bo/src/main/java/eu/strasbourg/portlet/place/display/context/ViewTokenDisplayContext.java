package eu.strasbourg.portlet.place.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewTokenDisplayContext {

    private final RenderRequest _request;
    private final RenderResponse _response;
    private String refreshToken;
    private Ticket lastAccesToken;

    public ViewTokenDisplayContext(RenderRequest request,
                                   RenderResponse response) {
        this._request = request;
        this._response = response;
    }

    public String getOrderByCol() {
        return null;
    }

    public String getOrderByType() {
        return null;
    }

    public String getFilterCategoriesIds() throws PortalException {
        return null;
    }

    public String getRefeshToken() throws PortalException {
        if (this.refreshToken == null) {
            // récupération du ticket de type 98 (refresh-token)
            Ticket ticket = TicketLocalServiceUtil.getTickets(-1,-1).stream()
                    .filter(t -> t.getType() == 98).findFirst().orElse(null);
            if (ticket != null) {
                this.refreshToken = ticket.getExtraInfo();
            }
        }
        return this.refreshToken;
    }

    public Ticket getLastAccessToken() throws PortalException {
        if (this.lastAccesToken == null) {
            // récupération du ticket de type 99(acces-token)
            lastAccesToken = TicketLocalServiceUtil.getTickets(-1,-1).stream()
                    .filter(t -> t.getType() == 99).findFirst().orElse(null);
        }
        return this.lastAccesToken;
    }
}
