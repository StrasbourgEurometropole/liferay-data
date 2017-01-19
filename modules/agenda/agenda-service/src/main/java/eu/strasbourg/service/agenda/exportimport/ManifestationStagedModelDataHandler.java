package eu.strasbourg.service.agenda.exportimport;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.ManifestationLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class ManifestationStagedModelDataHandler
	extends BaseStagedModelDataHandler<Manifestation> {

	public static final String[] CLASS_NAMES = {
		Manifestation.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		Manifestation VideoManifestation = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);
		if (VideoManifestation != null) {
			deleteStagedModel(VideoManifestation);
		}
	}

	@Override
	public void deleteStagedModel(Manifestation stagedModel)
		throws PortalException {
		_manifestationLocalService
			.removeManifestation(stagedModel.getManifestationId());
	}

	@Override
	public List<Manifestation> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._manifestationLocalService
			.getManifestationsByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		Manifestation stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout références aux éditions
		for (Event event : stagedModel.getEvents()) {
			if (event.isApproved()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, event,
					PortletDataContext.REFERENCE_TYPE_WEAK);
			}
		}

		// Ajout référence à l'image
		try {
			FileEntry image = DLAppLocalServiceUtil
				.getFileEntry(stagedModel.getImageId());
			if (GroupLocalServiceUtil.getGroup(image.getGroupId())
				.isStagingGroup()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, image,
					PortletDataContext.REFERENCE_TYPE_WEAK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void doImportStagedModel(PortletDataContext portletDataContext,
		Manifestation stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getCompanyGroupId());
		sc.setUserId(userId);
		Manifestation importedManifestation = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Manifestation existingManifestation = this._manifestationLocalService
				.fetchManifestationByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getCompanyGroupId());

			if (existingManifestation == null) {
				importedManifestation = this._manifestationLocalService
					.createManifestation(sc);
			} else {
				importedManifestation = existingManifestation;
			}

		} else {
			importedManifestation = this._manifestationLocalService
				.createManifestation(sc);
		}

		importedManifestation.setUuid(stagedModel.getUuid());
		importedManifestation.setTitle(stagedModel.getTitle());
		importedManifestation.setImageId(stagedModel.getImageId());
		importedManifestation.setDescription(stagedModel.getDescription());
		importedManifestation.setStartDate(stagedModel.getStartDate());
		importedManifestation.setEndDate(stagedModel.getEndDate());
		importedManifestation.setDisplayDate(stagedModel.getDisplayDate());
		importedManifestation.setStatus(stagedModel.getStatus());

		// Import de l'asset, tags, catégories, et des éléments liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel,
			importedManifestation);

		// On update l'id de l'image avec le nouveau
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedManifestation.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));

		// On update la galerie
		this._manifestationLocalService
			.updateManifestation(importedManifestation, sc);

		// On lie la galerie à ses éditions
		for (Event oldEvent : importedManifestation.getEvents()) {
			_manifestationLocalService.deleteEventManifestation(
				oldEvent.getEventId(), importedManifestation);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> eventsIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(Event.class);
		for (Map.Entry<Long, Long> eventIdMapEntry : eventsIdsMap.entrySet()) {
			if (stagedModel.getEventsIds()
				.contains(String.valueOf(eventIdMapEntry.getKey()))) {
				_manifestationLocalService.addEventManifestation(
					eventIdMapEntry.getValue(), importedManifestation);
			}
		}

	}

	@Reference(unbind = "-")
	protected void setManifestationLocalService(
		ManifestationLocalService manifestationLocalService) {
		this._manifestationLocalService = manifestationLocalService;
	}

	private ManifestationLocalService _manifestationLocalService;

}
