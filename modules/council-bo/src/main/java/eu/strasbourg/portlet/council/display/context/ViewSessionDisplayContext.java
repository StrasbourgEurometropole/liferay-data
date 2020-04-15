package eu.strasbourg.portlet.council.display.context;

import eu.strasbourg.service.council.model.Session;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewSessionDisplayContext extends ViewListBaseDisplayContext<Session> {

    public ViewSessionDisplayContext(RenderRequest request, RenderResponse response) {
        super(Session.class, request, response);
    }
}
