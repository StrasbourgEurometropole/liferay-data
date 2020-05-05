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

package eu.strasbourg.service.council.model.impl;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;

/**
 * The extended model implementation for the Vote service. Represents a row in the &quot;council_Vote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.model.Vote} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class VoteImpl extends VoteBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a vote model instance should use the {@link eu.strasbourg.service.council.model.Vote} interface instead.
	 */
	public VoteImpl() {
	}

	/**
	 * Renvoie l'élu à qui appartient le vote
	 */
	@Override
	public Official getOfficial() {
		return OfficialLocalServiceUtil.fetchOfficial(this.getOfficialId());
	}

	/**
	 * Renvoie le nom complet de l'élu à qui appartient le vote
	 */
	@Override
	public String getOfficialFullName() {
		Official official = this.getOfficial();
		return official != null ? official.getFullName() : "";
	}

	/**
	 * Renvoie l'élu qui a voté par procuration
	 */
	@Override
	public Official getOfficialProcuration() {
		return OfficialLocalServiceUtil.fetchOfficial(this.getOfficialProcurationId());
	}

	/**
	 * Renvoie le nom complet de l'élu l'élu qui a voté par procuration
	 */
	@Override
	public String getgetOfficialProcurationFullName() {
		Official official = this.getOfficialProcuration();
		return official != null ? official.getFullName() : "";
	}

}