package eu.strasbourg.portlet.council.display.context;

import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewCouncilSessionsDisplayContext extends ViewListBaseDisplayContext<CouncilSession> {

    public ViewCouncilSessionsDisplayContext(RenderRequest request, RenderResponse response) {
        super(CouncilSession.class, request, response);
    }

}
