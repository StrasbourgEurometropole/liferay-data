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

package eu.strasbourg.service.formSendRecordField.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the FormSendRecordFieldSignalement service. Represents a row in the &quot;formSendRecordField_FormSendRecordFieldSignalement&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementImpl"
)
@ProviderType
public interface FormSendRecordFieldSignalement
	extends FormSendRecordFieldSignalementModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FormSendRecordFieldSignalement, Long>
		SIGNALEMENT_ID_ACCESSOR =
			new Accessor<FormSendRecordFieldSignalement, Long>() {

				@Override
				public Long get(
					FormSendRecordFieldSignalement
						formSendRecordFieldSignalement) {

					return formSendRecordFieldSignalement.getSignalementId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<FormSendRecordFieldSignalement> getTypeClass() {
					return FormSendRecordFieldSignalement.class;
				}

			};

	/**
	 * Retourne l'utilisateur auteur du signalement
	 */
	public eu.strasbourg.service.oidc.model.PublikUser getSignalementAuthor();

	/**
	 * Retourne le nom de l'auteur du signalement
	 */
	public String getSignalementAuthorLabel();

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategoriesByAssetEntry();

	public String getCategorieName();

}