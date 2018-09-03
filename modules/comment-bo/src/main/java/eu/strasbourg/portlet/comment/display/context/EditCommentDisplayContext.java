package eu.strasbourg.portlet.comment.display.context;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class EditCommentDisplayContext {

    private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;

    private PublikUser publikUser;
    private Comment _comment;
    private String _banishment;
    private LocalDateTime date;
    private int _year;
    private int _month;
    private int _day;

    public EditCommentDisplayContext(RenderRequest request, RenderResponse response) {
        publikUser = getPublikUser(request);
        this.date = getDate();
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    private PublikUser getPublikUser(RenderRequest request) {
        long commentId = ParamUtil.getLong(request, "commentId");
        if (_comment == null && commentId > 0) {
            _comment = CommentLocalServiceUtil.fetchComment(commentId);
        }
        return PublikUserLocalServiceUtil.getByPublikUserId(_comment.getPublikId());
    }

    private LocalDateTime getDate() {
        Date banishDate = publikUser.getBanishDate();
        if (banishDate != null)
            return new Timestamp(banishDate.getTime()).toLocalDateTime();
        else return LocalDateTime.now();
    }

    public int getYear() {
        _year = date.getYear();
        return _year;
    }

    public int getMonth() {
        _month = date.getMonthValue() - 1;
        return _month;
    }

    public int getDay() {
        _day = date.getDayOfMonth();
        return _day;
    }

    public Comment getComment() {
        long commentId = ParamUtil.getLong(_request, "commentId");
        if (_comment == null && commentId > 0) {
            _comment = CommentLocalServiceUtil.fetchComment(commentId);
        }
        return _comment;
    }

    public String getBanishment() {
        if (this._comment == null) {
            _comment = this.getComment();
        }
        return publikUser.getBanishDescription();
    }

    public String getDefaultIndexes(int length) {
        String indexes = "";
        for (int i = 1; i <= length; i++) {
            if (Validator.isNotNull(indexes)) {
                indexes += ",";
            }
            indexes += i;
        }
        return indexes;
    }

    public Locale[] getAvailableLocales() {
        Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
        return availableLocalesSet
                .toArray(new Locale[availableLocalesSet.size()]);
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    public boolean isWorkflowEnabled() {
        boolean result = WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                _themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
                Comment.class.getName());
        return result;
    }

    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COMMENT_BO,
                StrasbourgPortletKeys.COMMENT_BO,
                actionId);
    }
}
