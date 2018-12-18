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

package eu.strasbourg.service.project.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.constants.InitiativeHelpTypes;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;

/**
 * The extended model implementation for the InitiativeHelp service. Represents a row in the &quot;project_InitiativeHelp&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.InitiativeHelp} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class InitiativeHelpImpl extends InitiativeHelpBaseImpl {
	
	private static final long serialVersionUID = 1051882333723138904L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a initiative help model instance should use the {@link eu.strasbourg.service.project.model.InitiativeHelp} interface instead.
	 */
	public InitiativeHelpImpl() {
	}
	
	/**
	 * Retourne l'utilisateur Publik depositaire
	 * @return
	 */
	@Override
	public PublikUser getAuthor() {
		return PublikUserLocalServiceUtil.getByPublikUserId(this.getPublikUserId());
	}
	
	/**
     * Retourne le nom de du depositaire sous forme "Truc M." ou le "Au nom de ..."
     */
    @Override
    public String getAuthorLabel() {
    	PublikUser author = this.getAuthor();
    	if (author != null) {
    		return StringUtil.upperCaseFirstLetter(author.getFirstName())
    				+ " "
    				+  StringUtil.toUpperCase(StringUtil.shorten(author.getLastName(), 2, "."));
    	} else {
    		return null;
    	}
    }
    
    /**
     * Retourne le message d'accompagnement sans les balises et autres fioritures 
     * @return
     */
    @Override
    public String getFormatedMessage() {
    	return HtmlUtil.stripHtml(HtmlUtil.escape(this.getMessage()));
    }
	
    /**
     * Retourne le label des types d'aide
     */
    @Override
	public String getTypesLabel() {
		String result = "";
		
		if (this.getHelpTypes().contains((InitiativeHelpTypes.TIME.toString()))) {
			result += "" + InitiativeHelpTypes.TIME.getLabel();
		}
		if (this.getHelpTypes().contains((InitiativeHelpTypes.MONEY.toString()))) {
			result += (result.equals("") ? " " : ", ") + InitiativeHelpTypes.TIME.getLabel();
		}
		if (this.getHelpTypes().contains((InitiativeHelpTypes.PLACE.toString()))) {
			result += (result.equals("") ? " " : ", ") + InitiativeHelpTypes.PLACE.getLabel();
		}
		if (this.getHelpTypes().contains((InitiativeHelpTypes.EXPERTISE.toString()))) {
			result += (result.equals("") ? " " : ", ") + InitiativeHelpTypes.EXPERTISE.getLabel();
		}
		
		return result;
	}
    
    /**
     * Retourne l'initiative de l'aide
     * @return
     * @throws PortalException 
     */
    @Override
	public Initiative getInitiative() {
		try {
			return InitiativeLocalServiceUtil.getInitiative(this.getInitiativeId());
		} catch (PortalException e) {
			e.printStackTrace();
			return null;
		}
	}
}