package eu.strasbourg.service.strasbourg.scheduler;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFileEntryService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.utils.constants.GlobalConstants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

/**
 * Publication et purge des actes légaux
 */
@Component(immediate = true, service = DocumentsMessageListener.class)
public class DocumentsMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate() {
		String listenerClass = getClass().getName();

		// Maintenant + 5 min pour ne pas lancer le scheduler au Startup du module
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 5);
		Date fiveMinutesFromNow = now.getTime();

		// Création du trigger "Tous les jours à 1H00
		Trigger trigger = _triggerFactory.createTrigger(
				listenerClass, listenerClass, fiveMinutesFromNow, null,
				"0 0 1 * * ?", TimeZone.getTimeZone(GlobalConstants.TIMEZONE));
//		Trigger trigger = _triggerFactory.createTrigger(
//				listenerClass, listenerClass, new Date(), null,
//				1, TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
				listenerClass, trigger);

		_schedulerEngineHelper.register(
				this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) {
		log.info("Start publication et purge des actes légaux");
		LocalDate today = LocalDate.now();

		// Publication et purge des actes légaux

		// récupération du type de document
		long groupId = _groupLocalService.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(),
				"/strasbourg.eu").getGroupId();
		Optional<DLFileEntryType> fileTypeOptional = DLFileEntryTypeLocalServiceUtil.getDLFileEntryTypes(-1, -1).stream()
				.filter(t -> t.getGroupId() == groupId && t.getName(Locale.FRANCE).equals(LanguageUtil.get(Locale.FRANCE, "eu.rep-commission")))
				.findFirst();
		long fileEntryTypeId;
		if(fileTypeOptional.isPresent()) {
			DLFileEntryType fileType = fileTypeOptional.get();
			fileEntryTypeId = fileType.getFileEntryTypeId();

			// récupération de la structure de document
			String structureKey = null;
			List<DDMStructure> ddmStructures = fileType.getDDMStructures();
			for(DDMStructure ddmStructure : ddmStructures) {
				if(ddmStructure.getClassName().equals(DLFileEntryMetadata.class.getName())) {
					structureKey = ddmStructure.getStructureKey();
				}
			}

			if(Validator.isNotNull(structureKey)){

				// Recuperation des actes légaux
				List<DLFileEntry> fileEntries = new ArrayList<>();

				Role adminRole;
				try {
					// On récupère les droits d'admin pour les donner au thread, pour pouvoir manipuler Documents et Medias
					adminRole = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(),"Administrator");
					List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());
					PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
					PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
					PermissionThreadLocal.setPermissionChecker(permissionChecker);

					// Dossier Article-Rubrique
					DLFolder folderArtRub;
					try {
						folderArtRub = _dlFolderLocalService.getFolder(groupId,
								DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,"Article-Rubrique");

						// Dossier Ville et Eurométropole
						DLFolder folderVilleEuro;
						try {
							folderVilleEuro = _dlFolderLocalService.getFolder(groupId,
									folderArtRub.getFolderId(),
									LanguageUtil.get(Locale.FRANCE, "eu.rep-ville-euro"));

							// Dossier Actes réglementaires et normatifs
							DLFolder folderActe;
							try {
								folderActe = _dlFolderLocalService.getFolder(groupId,
										folderVilleEuro.getFolderId(),
										LanguageUtil.get(Locale.FRANCE, "eu.rep-commission"));

								// Dossiers Strasbourg
								DLFolder folderStrasbourg;
								try {
									folderStrasbourg = _dlFolderLocalService.getFolder(groupId,
											folderActe.getFolderId(),
											"Strasbourg");
									try {
										if(Validator.isNotNull(folderStrasbourg)) {
											fileEntries.addAll(_dlFileEntryService.getFileEntries(groupId,
													folderStrasbourg.getFolderId(), fileEntryTypeId, -1, -1,
													null));
										}
									}catch(PortalException e) {
										log.error("problem retrieving files of Strasbourg");
									}
								}catch(PortalException e) {
									log.error("rep 'Strasbourg' recovery problem");
								}
								// Dossiers Eurométropole
								DLFolder folderEurometropole;
								try {
									folderEurometropole = _dlFolderLocalService.getFolder(groupId,
											folderActe.getFolderId(),
											LanguageUtil.get(Locale.FRANCE, "eu.eurometropole"));
									try {
										if(Validator.isNotNull(folderEurometropole)) {
											fileEntries.addAll(_dlFileEntryService.getFileEntries(groupId,
													folderEurometropole.getFolderId(), fileEntryTypeId, -1, -1,
													null));
										}
									}catch(PortalException e) {
										log.error("problem retrieving files of " + LanguageUtil.get(Locale.FRANCE, "eu.eurometropole"));
									}
								}catch(PortalException e) {
									log.error("rep '" + LanguageUtil.get(Locale.FRANCE, "eu.eurometropole") + "' recovery problem");
								}
							}catch(PortalException e) {
								log.error("rep 'Actes réglementaires et normatifs' recovery problem");
							}
						} catch (PortalException e) {
							log.error("rep 'Ville et Eurométropole' recovery problem");
						}
					} catch (PortalException e) {
						log.error("rep 'Article-Rubrique' recovery problem");
					}
				} catch (Exception e) {
					// Dans le cas où ça plante sur la récupération des droits d'admin
					log.error("Erreur récupération de droit d'admin");
					log.error(e);
				}

				for (DLFileEntry fileEntry : fileEntries) {
					// récupération des métadatas
					List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();
					try {
						long fileVersionId = fileEntry.getFileVersion().getFileVersionId();
						try {
							Map<String, DDMFormValues> ddmFormValuesMap = fileEntry.getDDMFormValuesMap(fileVersionId);
							DDMFormValues ddmFormValues = ddmFormValuesMap.get(structureKey);
							if(Validator.isNotNull(ddmFormValues))
								ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues();
						} catch (PortalException e) {
							log.error("problem retrieving the ddmFormValuesMap of " + fileEntry.getFileName());
						}
					} catch (PortalException e) {
						log.error("problem retrieving the version of " + fileEntry.getFileName());
					}

					LocalDate startDate = null, endDate = null;
					for (DDMFormFieldValue ddmFormFieldValue : ddmFormFieldValues) {
						// récupération des dates de publication
						switch (ddmFormFieldValue.getName()) {
							case "publicationDate":
								Value publicationDate = ddmFormFieldValue.getValue();
								String publicationDateString = publicationDate.getString(Locale.FRANCE);
								if(Validator.isNotNull(publicationDateString))
									startDate = LocalDate.parse(publicationDateString);
								break;
							case "endPublicationDate":
								Value endPublicationDate = ddmFormFieldValue.getValue();
								String endPublicationDateString = endPublicationDate.getString(Locale.FRANCE);
								if(Validator.isNotNull(endPublicationDateString))
									endDate = LocalDate.parse(endPublicationDateString);
								break;
						}
						if (Validator.isNotNull(startDate) && !today.isBefore(startDate) && fileEntry.getStatus() == WorkflowConstants.STATUS_DRAFT) {
							// si le statut du document est en brouillon et que la date de début de publication est passée, on passe le statut en publié
							try {
								DLFileVersion version = fileEntry.getFileVersion();
								version.setStatus(WorkflowConstants.STATUS_APPROVED);
								DLFileVersionLocalServiceUtil.updateDLFileVersion(version);
							} catch (PortalException ex) {
								log.error("Error updating status for File : " + fileEntry.getFileName());
								log.error(ex);
							}
						}
						if (Validator.isNotNull(endDate) && today.isAfter(endDate)) {
							// si la date de fin de publication est passée, on supprime le document
							try {
								_dlFileEntryService.deleteFileEntry(fileEntry.getFileEntryId());
							} catch (PortalException e) {
								log.error("Error deleting file : " + fileEntry.getFileName());
								log.error(e);
							}
						}
					}
				}
			}else{
				log.error("problem retrieving the structureKey of the fileEntryType");
			}
		}else{
			log.error("problem retrieving the fileEntryTypeId");
		}

		log.info("End publication et purge des actes légaux");
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		this._groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	public void setDLFolderLocalService(DLFolderLocalService dlFolderLocalService) {
		this._dlFolderLocalService = dlFolderLocalService;
	}

	@Reference(unbind = "-")
	public void setDLFileEntryService(DLFileEntryService dlFileEntryService) {
		this._dlFileEntryService = dlFileEntryService;
	}


	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
			SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	private volatile SchedulerEngineHelper _schedulerEngineHelper;
	private GroupLocalService _groupLocalService;
	private DLFolderLocalService _dlFolderLocalService;
	private DLFileEntryService _dlFileEntryService;
	private TriggerFactory _triggerFactory;

	private Log log = LogFactoryUtil.getLog(this.getClass());
}
