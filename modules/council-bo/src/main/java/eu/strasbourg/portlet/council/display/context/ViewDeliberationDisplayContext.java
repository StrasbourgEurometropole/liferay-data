package eu.strasbourg.portlet.council.display.context;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewDeliberationDisplayContext extends ViewListBaseDisplayContext<Deliberation> {

    public ViewDeliberationDisplayContext(RenderRequest request, RenderResponse response) {
        super(Deliberation.class, request, response);
    }
}
