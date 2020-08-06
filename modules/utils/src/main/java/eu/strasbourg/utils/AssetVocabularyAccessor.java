package eu.strasbourg.utils;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.utils.constants.VocabularyNames;

public class AssetVocabularyAccessor {

	public AssetVocabulary getEventThemes() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_THEME);
		} catch (PortalException e) {
			return null;
		}
	}

	public AssetVocabulary getEventTypes() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
		} catch (PortalException e) {
			return null;
		}
	}

	public AssetVocabulary getEventPublics() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_PUBLIC);
		} catch (PortalException e) {
			return null;
		}
	}

	public AssetVocabulary getTerritories() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
		} catch (PortalException e) {
			return null;
		}
	}
	
	public AssetVocabulary getPlaceTypes() {
		try {
			return AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
		} catch (PortalException e) {
			return null;
		}		
	}
	
	public AssetVocabulary getProjectStatus(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.PROJECT_STATUS, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getPlacitStatus(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.PLACIT_STATUS, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getBudgetParticipatifStatus(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.BUDGET_PARTICIPATIF_STATUS, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getInitiativeStatus(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.INITIATIVE_STATUS, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getParticipationTypes(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.PARTICIPATION_TYPE, groupID);
		} catch (Exception e) {
			return null;
		}		
	}

	public AssetVocabulary getThematics(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.THEMATIC, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getCategoriesSignalement(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.SIGNALEMENT_CATEGORIE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getProjects(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.PROJECT, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getTypesNoel(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.TYPE_NOEL, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getTauxNoel(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.TAUX_NOEL, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getDureesNoel(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.DUREE_NOEL, groupID);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public AssetVocabulary getEventSubscriptionTypes(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.OPS_EVENT_SUBSCRIPTION_TYPE, groupID);
		} catch (Exception e) {
			return null;
		}		
	}

	public AssetVocabulary getEventTypologies(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.OPS_TYPOLOGIE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getActivityCoursePublic(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.ACTIVITY_COURSE_PUBLIC, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getPractice(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.PRACTICE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary gePracticePublic(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.PRACTICE_PUBLIC, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getAccessibility(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.ACCESSIBILITY, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getArretType(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.ARRET_TYPE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getCouncilSession(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getCouncilType(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_TYPE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public AssetVocabulary getCouncilOfficialActivity(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_OFFICIAL_ACTIVITY, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobFilieres(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_FILIERES, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobCategories(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_CATEGORIES, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobTypeRecrutement(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_TYPE_RECRUTEMENT, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobDirection(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_DIRECTION, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobService(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_SERVICE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobNiveauEtude(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_NIVEAU_ETUDE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobFamille(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_FAMILLE, groupID);
		} catch (Exception e) {
			return null;
		}
	}

	public static AssetVocabulary getEJobContact(long groupID) {
		try {
			return AssetVocabularyHelper.getVocabulary(VocabularyNames.EJOB_CONTACT, groupID);
		} catch (Exception e) {
			return null;
		}
	}
}