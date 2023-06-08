package eu.strasbourg.portlet.council.utils;


import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import eu.strasbourg.portlet.council.constants.CouncilConstants;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.util.ArrayList;
import java.util.List;

public class UserRoleType {

    private static final Log log = LogFactoryUtil.getLog("UserRoleType");

    /**
     * Renvoie la liste des id de Type de conseil d'un Utilisateur en fonction de ses rôles
     * @param themeDisplay
     * @return
     */
    public static List<Long> typeIdsForUser(ThemeDisplay themeDisplay)  {
        List<Long> typesId = new ArrayList<>();
        // Si l'utilisateur est Admin liferay ou Admin Evote, il a tous les types Ids
        if(themeDisplay.getPermissionChecker().isOmniadmin() || IsAdminEvote(themeDisplay)) {
            for ( Type type : TypeLocalServiceUtil.getTypes(-1,-1)) {
                typesId.add(type.getTypeId());
            }
        }
        // Ou alors on regarde par rapport aux rôles
        else {
            for ( long roleId : themeDisplay.getUser().getRoleIds()) {
                for ( Type type : TypeLocalServiceUtil.findByRoleId(roleId)) {
                    typesId.add(type.getTypeId());
                }
            }
        }
        return typesId;
    }

    /**
     * Renvoie la liste des catégories de Type de conseil d'un Utilisateur en fonction de ses rôles
     * @param themeDisplay
     * @return
     */
    public static List<AssetCategory> typeCategoriesForUser(ThemeDisplay themeDisplay)  {
        List<AssetCategory> typeCategories = new ArrayList<>();

        long groupId = themeDisplay.getScopeGroupId();
        AssetCategory categoryAucunConseil = AssetVocabularyHelper.getCategory(CouncilConstants.NO_COUNCIL_CATEGORY_NAME, groupId);
        typeCategories.add(categoryAucunConseil);

        // Si l'utilisateur est Admin liferay ou Admin Evote, il a tous les types Ids
        if(themeDisplay.getPermissionChecker().isOmniadmin() || IsAdminEvote(themeDisplay)) {
            for ( Type type : TypeLocalServiceUtil.getTypes(-1,-1)) {
                AssetCategory category =  AssetVocabularyHelper.getCategory(type.getTitle(), themeDisplay.getScopeGroupId());
                if(category != null) {
                    typeCategories.add(category);
                }
            }
        }
        // Ou alors on regarde par rapport aux rôles
        else {
            for ( long roleId : themeDisplay.getUser().getRoleIds()) {
                for ( Type type : TypeLocalServiceUtil.findByRoleId(roleId)) {
                    AssetCategory category =  AssetVocabularyHelper.getCategory(type.getTitle(), themeDisplay.getScopeGroupId());
                    if(category != null) {
                        typeCategories.add(category);
                    }
                }
            }
        }
        return typeCategories;
    }

    public static boolean IsAdminEvote(ThemeDisplay themeDisplay) {
        boolean isAdminEvote = false;
        try {
            isAdminEvote = RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "Gestionnaire EVOTE - Administrateur", false);;
        } catch (PortalException e) {
            log.error(e);
        }
        return isAdminEvote;
    }
}
