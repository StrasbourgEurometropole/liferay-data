package eu.strasbourg.portlet.help.info.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.list.provider.InfoListProvider;
import com.liferay.info.list.provider.InfoListProviderContext;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.SessionParamUtil;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import java.util.Locale;


@Component(service = InfoListProvider.class)
public class HelpProposalInfoListProvider implements InfoListProvider<AssetEntry> {

    @Override
    public List<AssetEntry> getInfoList(InfoListProviderContext infoListProviderContext) {

        // Recuperer l'utilisateur publik courant.
        HttpServletRequest  request = ServiceContextThreadLocal.getServiceContext().getRequest();
        boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
        boolean isNotBanish = SessionParamUtil.getBoolean(request, "is_banish");
        String currentPublikUserId = "";
        if (isLoggedIn && !isNotBanish) {
            currentPublikUserId = SessionParamUtil.getString(request, "publik_internal_id");
        }

        // Recuperer les propositions d'aides pour cet utilisateur
        List<AssetEntry> helpProposalsAssets = new ArrayList<>();
        if (!currentPublikUserId.equals(""))
        {
            List<HelpProposal> helpProposals = _helProposalLocalService.getByPublikID(currentPublikUserId);
            for (HelpProposal helpProposal: helpProposals) {
                helpProposalsAssets.add(helpProposal.getAssetEntry());
            }
        }

        return helpProposalsAssets;
    }

    @Override
    public List<AssetEntry> getInfoList(
            InfoListProviderContext infoListProviderContext, Pagination pagination,
            Sort sort) {


        return getInfoList(infoListProviderContext);
    }



    @Override
    public int getInfoListCount(InfoListProviderContext infoListProviderContext) {
        return getInfoList(infoListProviderContext).size();
    }

    @Override
    public String getLabel(Locale locale) {
        return "Propositions d'aide";
    }

    @Reference
    HelpProposalLocalService _helProposalLocalService;

    @Reference
    PublikUserLocalService _publikUserLocalService;

}