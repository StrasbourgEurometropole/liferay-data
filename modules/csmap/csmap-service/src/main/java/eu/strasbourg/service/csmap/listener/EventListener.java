package eu.strasbourg.service.csmap.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.csmap.service.CacheAgendaJsonLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author jeremy.zwickert
 *
 */
@Component(immediate = true, service = ModelListener.class)
public class EventListener extends BaseModelListener<Event>
{

	/**
	 *  A la modification d'un event, on met à jour le cache de csmapAgenda
	 */
	@Override
	public void onAfterUpdate(Event event) throws ModelListenerException {

			// Use TCC to avoid post method business done by Liferay for DLFileEntry which clears categories
			TransactionCommitCallbackUtil.registerCallback(() -> {
				Thread.sleep(1 * 100);
				_cacheAgendaJsonLocalService.updateCacheAgendaJson();
				return null;
			});

		super.onAfterUpdate(event);

	}

	/**
	 *  A la suppression d'un event, on met à jour le cache de csmapAgenda
	 */
	@Override
	public void onAfterRemove(Event event) throws ModelListenerException {

			// Use TCC to avoid post method business done by Liferay for DLFileEntry which clears categories
			TransactionCommitCallbackUtil.registerCallback(() -> {
				Thread.sleep(1 * 100);
				_cacheAgendaJsonLocalService.updateCacheAgendaJson();
				return null;
			});

		super.onAfterRemove(event);

	}

	private CacheAgendaJsonLocalService _cacheAgendaJsonLocalService;

	@Reference(unbind = "-")
	protected void setCacheAgendaJsonLocalService(CacheAgendaJsonLocalService cacheAgendaJsonLocalService) {
		_cacheAgendaJsonLocalService = cacheAgendaJsonLocalService;
	}

	private EventLocalService _eventLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

}
