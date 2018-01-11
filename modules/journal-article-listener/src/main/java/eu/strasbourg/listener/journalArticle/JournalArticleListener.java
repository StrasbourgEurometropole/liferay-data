package eu.strasbourg.listener.journalArticle;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

/**
 * @author jeremy.zwickert
 *
 */
@Component(immediate = true, service = ModelListener.class)
public class JournalArticleListener extends BaseModelListener<JournalArticle> 
{


	/**
	 *  A la publication d'un nouvel article, on déprécie les anciennes versions
	 */
	@Override
	public void onAfterCreate(JournalArticle model) throws ModelListenerException 
	{
		depreciateJournalArticleHistory(model);

		super.onAfterCreate(model);
	}
	
	/**
	 *  A la publication d'un nouvel article, on déprécie les anciennes versions
	 *  Update => dans le cas où l'on passe de brouillon à publié
	 */
	@Override
	public void onAfterUpdate(JournalArticle model) throws ModelListenerException {
		
		
		depreciateJournalArticleHistory(model);
		
		super.onAfterUpdate(model);
	}
	
	/**
	 * Méthode permettant de déprécier les anciennes versions d'un article publié
	 *
	 */
	private void depreciateJournalArticleHistory (JournalArticle model)
	{
		int statusJournalArticle = model.getStatus();
		long idJournalArticle = model.getId();
		List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(model.getGroupId(),
				model.getArticleId());

		if (statusJournalArticle == 0) 
		{
			for (JournalArticle journalArticle : articles) 
			{
				if(journalArticle.getId() != idJournalArticle && journalArticle.getStatus() != 3)
				{
					journalArticle.setStatus(3);
					JournalArticleLocalServiceUtil.updateJournalArticle(journalArticle);
				}
			}
		}
	}
	
}
