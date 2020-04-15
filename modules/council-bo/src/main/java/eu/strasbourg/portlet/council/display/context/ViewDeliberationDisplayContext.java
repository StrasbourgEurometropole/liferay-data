package eu.strasbourg.portlet.council.display.context;

import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewDeliberationDisplayContext extends ViewListBaseDisplayContext<Deliberation> {

    public ViewDeliberationDisplayContext(RenderRequest request, RenderResponse response) {
        super(Deliberation.class, request, response);
    }
}
