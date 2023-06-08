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

package eu.strasbourg.portlet.tipi_stats_portlet.panel;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author Angélique ZUNINO CHAMPOUGNY
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.TIPI_STATS_PORTLET_WEB
	},
	service = ControlPanelEntry.class
)
public class TipiStatsControlPanelEntry
	extends BaseControlPanelEntry  {
}