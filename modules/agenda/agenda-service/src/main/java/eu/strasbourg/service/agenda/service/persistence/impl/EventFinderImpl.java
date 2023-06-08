package eu.strasbourg.service.agenda.service.persistence.impl;

import aQute.bnd.component.annotations.Reference;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.impl.EventImpl;
import eu.strasbourg.service.agenda.service.persistence.EventFinder;

import java.util.List;

public class EventFinderImpl extends EventFinderBaseImpl implements EventFinder {

    public List<Event> findByNextHappening() {

        Session session = null;
        try {
            session = openSession();

            String sql = customSQL.get(
                    getClass(),
                    FIND_BY_NEXT_HAPPENING);

            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Event_Entry", EventImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);

            return (List<Event>) q.list();
        }
        catch (Exception e) {
            try {
                throw new SystemException(e);
            }
            catch (SystemException se) {
                _log.error(se.getMessage(), se);
            }
        }
        finally {
            closeSession(session);
        }

        return null;
    }

    public static final String FIND_BY_NEXT_HAPPENING =
            EventFinder.class.getName() +
                    ".findByNextHappening";

//    @Reference
    @ServiceReference(type=CustomSQL.class)
    private CustomSQL customSQL;

    private final Log _log = LogFactoryUtil.getLog(this.getClass());
}
