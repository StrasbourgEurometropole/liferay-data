package eu.strasbourg.portlet.help.info.provider;


import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.item.renderer.InfoItemRenderer;
import eu.strasbourg.service.help.model.HelpProposal;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = InfoItemRenderer.class)
public class HelpProposalInfoItemRenderer implements InfoItemRenderer<AssetEntry> {
    @Override
    public void render(
            AssetEntry myOrder, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        try {
            PrintWriter printWriter = httpServletResponse.getWriter();

            printWriter.write("<div>Test</div>");
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}