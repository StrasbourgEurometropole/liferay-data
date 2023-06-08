/*
  Copyright 2000-present Liferay, Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package eu.strasbourg.portlet.agenda.csmap.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalService;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_AGENDA,
		"mvc.command.name=ActivateAgendaThematique" },
	service = MVCActionCommand.class)
public class ActivateAgendaThematiqueActionCommand
	implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) {
		// désactive l'agenda activé
		Agenda agendaActif = _agendaLocalService.getAgendaThematiqueActif();
		if(Validator.isNotNull(agendaActif)){
			agendaActif.setIsActive(false);
			_agendaLocalService.updateAgenda(agendaActif);
		}

		long agendaId = ParamUtil.getLong(request, "agendaId");
		Agenda agenda = _agendaLocalService.fetchAgenda(agendaId);
		if(Validator.isNotNull(agenda)) {
			agenda.setIsActive(true);
			_agendaLocalService.updateAgenda(agenda);
		}

		// Régénération du cache des agendas pour CSMap
		_csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.AGENDA.getId());
		return true;
	}

	private AgendaLocalService _agendaLocalService;
	private CsmapCacheLocalService _csmapCacheLocalService;

	@Reference(unbind = "-")
	protected void setAgendaLocalService(AgendaLocalService agendaLocalService) {
		_agendaLocalService = agendaLocalService;
	}

	@Reference(unbind = "-")
	protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
		_csmapCacheLocalService = csmapCacheLocalService;
	}
}