package eu.strasbourg.listener.journalArticle;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalService;

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
		deleteFavorite(model);
			
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
	
	/**
	 * Méthode permettant de supprimer les favoris si toutes les versions d'un article sont dépréciés 
	 *
	 */
	private void deleteFavorite (JournalArticle model)
	{
		long idJournalArticle = model.getId();
		List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(model.getGroupId(),
				model.getArticleId());

		// Si tous les articles sont dépréciés, on supprime les éventuels Favoris existants
		if(articles.stream().allMatch(x -> x.getStatus() == 3)) {
			// Je ne suis pas sûr de savoir faire la différence entre NEWS et ARTICLE mais c'est le même objet, 
			// donc en lançant les deux, il sera au final supprimé
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(Long.parseLong(model.getArticleId()), FavoriteType.NEWS.getId());
			_favoriteLocalService.deleteFavoriteByEntityIdAndType(Long.parseLong(model.getArticleId()), FavoriteType.ARTICLE.getId());
		}	
	}
	
	private FavoriteLocalService _favoriteLocalService;
	
	@Reference(unbind = "-")
	protected void setFavoriteLocalService(
			FavoriteLocalService favoriteLocalService) {

		_favoriteLocalService = favoriteLocalService;
	}	
	
}
