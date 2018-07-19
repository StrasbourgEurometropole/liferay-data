package eu.strasbourg.portlet.comment;

import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.comment.display.context.EditCommentDisplayContext;
import eu.strasbourg.portlet.comment.display.context.ViewCommentDisplayContext;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author alexandre.quere
 */
@Component(
        immediate=true,
        property= {
                "com.liferay.portlet.instanceable=false",
                "com.liferay.portlet.footer-portlet-javascript=/js/comment-bo-main.js",
                "com.liferay.portlet.header-portlet-css=/css/comment-bo-main.css",
                "com.liferay.portlet.single-page-application=false",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/comment-bo-view.jsp",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class CommentBOPortlet extends MVCPortlet{

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

        String cmd = ParamUtil.getString(renderRequest, "cmd");
        String tab = ParamUtil.getString(renderRequest,"tab");
        String mvcPath = ParamUtil.getString(renderRequest,"mvcPath");
        renderResponse.setTitle("Commentaires");

        //si on est sur la page d'ajout, on affiche bien évidemment un lien de retour
        String returnURL = ParamUtil.getString(renderRequest,"returnURL");
        boolean showBackButton = Validator.isNotNull(returnURL);
        if (showBackButton){
            portletDisplay.setShowBackIcon(true);
            portletDisplay.setURLBack(returnURL);
        }

        //on set le displayContext selon la page sur laquelle on est
        if (cmd.equals("editComment")){
            EditCommentDisplayContext dc = new EditCommentDisplayContext(renderRequest,renderResponse);
            renderRequest.setAttribute("dc",dc);
        }else {
            ViewCommentDisplayContext dc = new ViewCommentDisplayContext(renderRequest,renderResponse);
            renderRequest.setAttribute("dc",dc);
        }
        renderRequest.setAttribute("isAdmin",themeDisplay.getPermissionChecker().isOmniadmin());
        super.render(renderRequest, renderResponse);
    }

}
