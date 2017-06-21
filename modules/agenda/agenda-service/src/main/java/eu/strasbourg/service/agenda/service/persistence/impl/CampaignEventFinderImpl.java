package eu.strasbourg.service.agenda.service.persistence.impl;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.service.CampaignEventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventFinder;

public class CampaignEventFinderImpl extends CampaignEventFinderBaseImpl
	implements CampaignEventFinder {

	/**
	 * Lance une recherche par mots-clés, thème, statut et utilisateur. Pour
	 * l'utilisateur, on retourne les événements créés par l'utilisateur et ceux
	 * qui appartiennent à des campagnes dont l'utilisateur est manager.
	 */
	@Override
	public List<CampaignEvent> findByKeywordThemeAndStatus(String keyword,
		long themeId, int status, long userId, long groupId, int start,
		int end) {
		DynamicQuery campaignEventQuery = CampaignEventLocalServiceUtil
			.dynamicQuery();

		DynamicQuery campaignQuery = CampaignLocalServiceUtil.dynamicQuery()
			.add(
				RestrictionsFactoryUtil.like("managersIds", "%" + userId + "%"))
			.setProjection(ProjectionFactoryUtil.property("campaignId"));

		if (keyword.length() > 0) {
			campaignEventQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (themeId > 0) {
			campaignEventQuery
				.add(PropertyFactoryUtil.forName("themeId").eq(themeId));
		}
		if (status >= 0) {
			campaignEventQuery
				.add(PropertyFactoryUtil.forName("status").eq(status));
		}
		if (groupId > 0) {
			campaignEventQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		campaignEventQuery.setLimit(start, end);
		Criterion userCriterion = RestrictionsFactoryUtil.or(
			PropertyFactoryUtil.forName("userId").eq(userId),
			PropertyFactoryUtil.forName("campaignId").in(campaignQuery));
		
		Order order = OrderFactoryUtil.desc("modifiedDate");
		campaignEventQuery.add(userCriterion).addOrder(order);

		return this.campaignEventPersistence
			.findWithDynamicQuery(campaignEventQuery);
	}

	/**
	 * Compte de la recherche par mots-clés, thème, statut et utilisateur
	 */
	@Override
	public long findByKeywordThemeAndStatusCount(String keyword, long themeId,
		int status, long userId, long groupId) {
		DynamicQuery campaignEventQuery = CampaignEventLocalServiceUtil
			.dynamicQuery();

		DynamicQuery campaignQuery = CampaignLocalServiceUtil.dynamicQuery()
			.add(
				RestrictionsFactoryUtil.like("managersIds", "%" + userId + "%"))
			.setProjection(ProjectionFactoryUtil.property("campaignId"));

		if (keyword.length() > 0) {
			campaignEventQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (themeId > 0) {
			campaignEventQuery
				.add(PropertyFactoryUtil.forName("themeId").eq(themeId));
		}
		if (status >= 0) {
			campaignEventQuery
				.add(PropertyFactoryUtil.forName("status").eq(status));
		}
		if (groupId > 0) {
			campaignEventQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		Criterion userCriterion = RestrictionsFactoryUtil.or(
			PropertyFactoryUtil.forName("userId").eq(userId),
			PropertyFactoryUtil.forName("campaignId").in(campaignQuery));
		campaignEventQuery.add(userCriterion);

		return this.campaignEventPersistence
			.countWithDynamicQuery(campaignEventQuery);
	}

}
