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

package eu.strasbourg.service.formSendRecordField.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * The extended model implementation for the FormSendRecordFieldSignalement service. Represents a row in the &quot;FormSendRecordField_FormSendRecordFieldSignalement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement} interface.
 * </p>
 *
 * @author Angélique Zunino
 */
@ProviderType
public class FormSendRecordFieldSignalementImpl
	extends FormSendRecordFieldSignalementBaseImpl {

	private static final long serialVersionUID = 6922508470027188080L;
	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a form send record field signalement model instance should use the {@link eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement} interface instead.
	 */
	public FormSendRecordFieldSignalementImpl() {
	}
}