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
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.utils.FileEntryHelper;

@Component(immediate = true, service = StagedModelDataHandler.class)
public class EventStagedModelDataHandler
	extends BaseStagedModelDataHandler<Event> {

	public static final String[] CLASS_NAMES = { Event.class.getName() };

	@Override
	public void deleteStagedModel(String uuid, long groupId, String className,
		String extraData) throws PortalException {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteStagedModel(Event stagedModel) throws PortalException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Event> fetchStagedModelsByUuidAndCompanyId(String uuid,
		long companyId) {
		return this._eventLocalService.getEventsByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(PortletDataContext portletDataContext,
		Event stagedModel) throws Exception {

		Element entryElement = portletDataContext
			.getExportDataElement(stagedModel);

		portletDataContext.addClassedModel(entryElement,
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel);

		// Ajout référence aux galeries
		for (Manifestation manifestation : stagedModel.getManifestations()) {
			if (manifestation.isApproved()) {
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, stagedModel, manifestation,
					PortletDataContext.REFERENCE_TYPE_PARENT);
			}
		}

		try {
			// Ajout référence à l'image
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
		Event stagedModel) throws Exception {
		long userId = portletDataContext.getUserId(stagedModel.getUserUuid());
		ServiceContext sc = portletDataContext
			.createServiceContext(stagedModel);
		sc.setUuid(stagedModel.getUuid());
		sc.setScopeGroupId(portletDataContext.getCompanyGroupId());
		sc.setUserId(userId);
		Event importedEvent = null;
		if (portletDataContext.isDataStrategyMirror()) {
			Event existingEvent = this._eventLocalService
				.fetchEventByUuidAndGroupId(stagedModel.getUuid(),
					portletDataContext.getCompanyGroupId());

			if (existingEvent == null) {
				importedEvent = this._eventLocalService.createEvent(sc);
			} else {
				importedEvent = existingEvent;
			}

		} else {
			importedEvent = this._eventLocalService.createEvent(sc);
		}

		importedEvent.setUuid(stagedModel.getUuid());
		importedEvent.setTitle(stagedModel.getTitle());
		importedEvent.setSubtitle(stagedModel.getSubtitle());
		importedEvent.setImageId(stagedModel.getImageId());
		importedEvent.setDescription(stagedModel.getDescription());
		importedEvent.setExternalImageURL(stagedModel.getExternalImageURL());
		importedEvent.setExternalImageCopyright(stagedModel.getExternalImageCopyright());
		importedEvent.setPlaceSIGId(stagedModel.getPlaceSIGId());
		importedEvent.setPlaceName(stagedModel.getPlaceName());
		importedEvent.setPlaceStreetNumber(stagedModel.getPlaceStreetNumber());
		importedEvent.setPlaceStreetName(stagedModel.getPlaceStreetName());
		importedEvent.setPlaceZipCode(stagedModel.getPlaceZipCode());
		importedEvent.setPlaceCity(stagedModel.getPlaceCity());
		importedEvent.setPlaceCountry(stagedModel.getPlaceCountry());
		importedEvent.setScheduleComments(stagedModel.getScheduleComments());
		importedEvent.setFirstStartDate(stagedModel.getFirstStartDate());
		importedEvent.setLastEndDate(stagedModel.getLastEndDate());
		importedEvent.setAccess(stagedModel.getAccess());
		importedEvent.setAccessForDisabled(stagedModel.getAccessForDisabled());
		importedEvent.setAccessForBlind(stagedModel.getAccessForBlind());
		importedEvent.setAccessForDeaf(stagedModel.getAccessForDeaf());
		importedEvent.setAccessForDeficient(stagedModel.getAccessForDeficient());
		importedEvent.setAccessForElder(stagedModel.getAccessForElder());
		importedEvent.setAccessForWheelchair(stagedModel.getAccessForWheelchair());
		importedEvent.setFree(stagedModel.getFree());
		importedEvent.setPrice(stagedModel.getPrice());
		importedEvent.setPromoter(stagedModel.getPromoter());
		importedEvent.setPhone(stagedModel.getPhone());
		importedEvent.setEmail(stagedModel.getEmail());
		importedEvent.setWebsiteName(stagedModel.getWebsiteName());
		importedEvent.setWebsiteURL(stagedModel.getWebsiteURL());
		importedEvent.setSource(stagedModel.getSource());
		importedEvent.setDisplayDate(stagedModel.getDisplayDate());
		importedEvent.setStatus(stagedModel.getStatus());

		// Import de l'asset, tags, catégories, et des élémeents liés
		// potentiellement non publiés
		portletDataContext.importClassedModel(stagedModel, importedEvent);

		// On update les ids des fichiers et des images avec les nouveaux
		@SuppressWarnings("unchecked")
		Map<Long, Long> newIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(DLFileEntry.class);
		importedEvent.setImageId(FileEntryHelper
			.getLiveFileEntryId(stagedModel.getImageId(), newIdsMap));

		// On update l'édition
		this._eventLocalService.updateEvent(importedEvent, sc);

		// On lie l'édition à ses galeries
		for (Manifestation oldManifestation : importedEvent
			.getManifestations()) {
			_eventLocalService.deleteManifestationEvent(
				oldManifestation.getManifestationId(), importedEvent);
		}
		@SuppressWarnings("unchecked")
		Map<Long, Long> manifestationsIdsMap = (Map<Long, Long>) portletDataContext
			.getNewPrimaryKeysMap(Manifestation.class);
		for (Map.Entry<Long, Long> manifestationIdMapEntry : manifestationsIdsMap
			.entrySet()) {
			_eventLocalService.addManifestationEvent(
				manifestationIdMapEntry.getValue(), importedEvent);
		}

	}

	@Reference(unbind = "-")
	protected void setEventLocalService(
		EventLocalService eventLocalService) {
		this._eventLocalService = eventLocalService;
	}

	private EventLocalService _eventLocalService;

}
