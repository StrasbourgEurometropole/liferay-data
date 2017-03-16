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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CampaignEventStatus service. Represents a row in the &quot;agenda_CampaignEventStatus&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see CampaignEventStatusModel
 * @see eu.strasbourg.service.agenda.model.impl.CampaignEventStatusImpl
 * @see eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.CampaignEventStatusImpl")
@ProviderType
public interface CampaignEventStatus extends CampaignEventStatusModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.CampaignEventStatusImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CampaignEventStatus, Long> STATUS_ID_ACCESSOR = new Accessor<CampaignEventStatus, Long>() {
			@Override
			public Long get(CampaignEventStatus campaignEventStatus) {
				return campaignEventStatus.getStatusId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CampaignEventStatus> getTypeClass() {
				return CampaignEventStatus.class;
			}
		};

	public eu.strasbourg.service.agenda.model.CampaignEventStatus getPreviousStatus();

	/**
	* Retourne le label correspondant au statut
	*/
	public java.lang.String getStatusLabel();
}