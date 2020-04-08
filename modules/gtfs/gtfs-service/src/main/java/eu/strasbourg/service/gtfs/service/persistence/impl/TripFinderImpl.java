package eu.strasbourg.service.gtfs.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

import eu.strasbourg.service.gtfs.model.Trip;
import eu.strasbourg.service.gtfs.model.impl.TripImpl;
import eu.strasbourg.service.gtfs.service.persistence.TripFinder;

public class TripFinderImpl extends TripFinderBaseImpl implements TripFinder {
	
	@SuppressWarnings("unchecked")
	public List<Trip> getTripAvailable(String stopId) {
		            
	    Session session = null;
	    try {
	        session = openSession();

	        String sql = CustomSQLUtil.get(getClass(), FIND_TRIP_AVAILABLE);
	        
	        SQLQuery q = session.createSQLQuery(sql);
	        q.setCacheable(false);
	        q.addEntity("gtfs_trip", TripImpl.class);

	        QueryPos qPos = QueryPos.getInstance(q);  
	        qPos.add(stopId);
	        
	        return (List<Trip>) q.list();
	    } catch (Exception e) {
	        try {
	            throw new SystemException(e);
	        } catch (SystemException se) {
	            se.printStackTrace();
	        }
	    } finally {
	        closeSession(session);
	    }

	    return null;
	    
	}	

	public static final String FIND_TRIP_AVAILABLE = TripFinder.class.getName() + ".findTripAvailable";

}
