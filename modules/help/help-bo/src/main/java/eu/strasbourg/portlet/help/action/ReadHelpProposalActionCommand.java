package eu.strasbourg.portlet.help.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
		"mvc.command.name=readHelpProposal"
	},
	service = MVCActionCommand.class
)
public class ReadHelpProposalActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			long helpProposalId = ParamUtil.getLong(request, "helpProposalId");
			HelpProposal helpProposal = _helpProposalLocalService.getHelpProposal(helpProposalId);

			ServiceContext sc = ServiceContextFactory.getInstance(request);
			// Mise du statut de modération en lue
			long[] ids = helpProposal.getCategories().stream()
					.mapToLong(AssetCategory::getCategoryId).toArray();
			List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());

			AssetVocabulary proposalModerationVocab =
					AssetVocabularyHelper.getVocabulary(VocabularyNames.HELP_PROPOSAL_MODERATION_STATUS, sc.getScopeGroupId());
			long readCategoryId = 0;
			long currentCategoryId = 0;
			for (AssetCategory category : proposalModerationVocab.getCategories()) {
				if (idsLong.contains(category.getCategoryId())) {
					currentCategoryId = category.getCategoryId();
				}
				if (category.getName().equalsIgnoreCase("Lue")) {
					readCategoryId = category.getCategoryId();
				}
			}
			if (readCategoryId != 0 && currentCategoryId != 0) {
				idsLong.remove(idsLong.indexOf(currentCategoryId));
				idsLong.add(readCategoryId);
			}

			sc.setAssetCategoryIds(idsLong.stream().mapToLong(w -> w).toArray());

			_helpProposalLocalService.updateHelpProposal(helpProposal, sc);
		} catch (PortalException e) {
			_log.error(e);
		}
		return true;
	}

	@Reference(unbind = "-")
	protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
		_helpProposalLocalService = helpProposalLocalService;
	}
	
	private HelpProposalLocalService _helpProposalLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
