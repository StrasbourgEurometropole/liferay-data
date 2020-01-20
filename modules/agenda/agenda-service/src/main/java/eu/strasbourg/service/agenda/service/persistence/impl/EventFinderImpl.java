package eu.strasbourg.service.agenda.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.impl.EventImpl;
import eu.strasbourg.service.agenda.service.persistence.EventFinder;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.QueryPos;

import java.util.List;

public class EventFinderImpl extends EventFinderBaseImpl implements EventFinder {

    public List<Event> findByNextHappening() {

        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
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
                se.printStackTrace();
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
}
