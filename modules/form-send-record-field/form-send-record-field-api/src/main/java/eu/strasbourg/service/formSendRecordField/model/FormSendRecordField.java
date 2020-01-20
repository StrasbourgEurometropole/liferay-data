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
 * The extended model interface for the FormSendRecordField service. Represents a row in the &quot;FormSendRecordField_FormSendRecordField&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldModel
 * @see eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldImpl
 * @see eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldImpl")
@ProviderType
public interface FormSendRecordField extends FormSendRecordFieldModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FormSendRecordField, Long> FORM_SEND_RECORD_FIELD_ID_ACCESSOR =
		new Accessor<FormSendRecordField, Long>() {
			@Override
			public Long get(FormSendRecordField formSendRecordField) {
				return formSendRecordField.getFormSendRecordFieldId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<FormSendRecordField> getTypeClass() {
				return FormSendRecordField.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des likes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	public java.util.List<eu.strasbourg.service.like.model.Like> getLikes();

	/**
	* Retourne le nombre de likes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	public int getNbLikes();

	/**
	* Retourne le nombre de dislikes de l'entité
	*
	* @see eu.strasbourg.service.like.model.LikeType
	*/
	public int getNbDislikes();

	/**
	* méthode qui renvoie la liste des signalements d'une réponse.
	*
	* @return la liste des signalements
	*/
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> findSignalements();

	/**
	* méthode qui renvoie le nombre de signalement pour un commentaire.
	*
	* @return le nombre de signalement en int.
	*/
	public int getCountSignalements();
}