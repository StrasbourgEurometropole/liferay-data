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
}