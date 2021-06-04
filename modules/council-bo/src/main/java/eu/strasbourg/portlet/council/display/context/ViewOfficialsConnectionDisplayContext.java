package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;

public class ViewOfficialsConnectionDisplayContext {
	
	/** Contexte de la requête */
	private RenderRequest request;
	private RenderResponse response;
	private ThemeDisplay themeDisplay;
	private CouncilSession currentCouncilSession;
	
	public ViewOfficialsConnectionDisplayContext(RenderRequest request, RenderResponse response) {
        this.request = request;
        this.response = response;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.currentCouncilSession = null;
    }
	
	public CouncilSession getCurrentCouncilSession() {
		if (this.currentCouncilSession == null) {

			// Calcul de la date
			GregorianCalendar gc = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();
			List<CouncilSession> todayCouncils = CouncilSessionLocalServiceUtil.findByDate(gc.getTime());
			
			if(todayCouncils.size() >0)
				this.currentCouncilSession = todayCouncils.get(0);
		}
		return this.currentCouncilSession;
	}
	
	/**
	 * Recupère l'ID du conseil du jour
	 * @return 0 si null
	 */
	@SuppressWarnings("unused")
    public long getCurrentCouncilSessionId() {
		long result = 0;
		if (this.getCurrentCouncilSession() != null)
			result = this.currentCouncilSession.getCouncilSessionId();
		return result;
    }
	
	/**
	 * Recupère le titre du conseil du jour
	 * @return Vide si null
	 */
	@SuppressWarnings("unused")
    public String getCurrentCouncilSessionTitle() {
		String result = "";
		if (this.getCurrentCouncilSession() != null)
			result = this.currentCouncilSession.getTitle();
		return result;
    }
	
	@SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }

}
