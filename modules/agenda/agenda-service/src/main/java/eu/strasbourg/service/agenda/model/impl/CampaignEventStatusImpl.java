/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.agenda.model.impl;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.service.CampaignEventStatusLocalServiceUtil;

/**
 * The extended model implementation for the CampaignEventStatus service.
 * Represents a row in the &quot;agenda_CampaignEventStatus&quot; database
 * table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.agenda.model.CampaignEventStatus}
 * interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class CampaignEventStatusImpl extends CampaignEventStatusBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3433138127587248873L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a campaign
	 * event status model instance should use the {@link
	 * eu.strasbourg.service.agenda.model.CampaignEventStatus} interface
	 * instead.
	 */
	public CampaignEventStatusImpl() {
		this.setDeletionDenied(false);
	}

	@Override
	public CampaignEventStatus getPreviousStatus() {
		if (Validator.isNull(this.getPreviousStatusId())) {
			return null;
		} else {
			return CampaignEventStatusLocalServiceUtil
				.fetchCampaignEventStatus(this.getPreviousStatusId());
		}
	}

	/**
	 * Retourne le label correspondant au statut
	 */
	@Override
	public String getStatusLabel() {
		switch (this.getStatus()) {
		case WorkflowConstants.STATUS_DRAFT:
			return "draft";
		case WorkflowConstants.STATUS_APPROVED:
			return "approved";
		case WorkflowConstants.STATUS_PENDING:
			return "submitted";
		case WorkflowConstants.STATUS_DENIED:
			return "denied";
		case WorkflowConstants.STATUS_IN_TRASH:
			return "deletion-requested";
		default:
			return "";
		}
	}

}