package eu.strasbourg.service.project.service.persistence.impl;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.persistence.BudgetParticipatifFinder;

public class BudgetParticipatifFinderImpl extends BudgetParticipatifFinderBaseImpl implements BudgetParticipatifFinder{

	
	public java.util.List<BudgetParticipatif> findByStatusAndActivePhaseAndGroupId(int status, long groupId){
		Session session = null;
	    try {
	    	session = openSession();
	    	
	        ClassLoader classLoader = getClass().getClassLoader();

	        DynamicQuery activePhaseQuery = DynamicQueryFactoryUtil.forClass(BudgetPhase.class, classLoader)
	            .add(RestrictionsFactoryUtil.eq("groupId", groupId))
	            .add(RestrictionsFactoryUtil.eq("isActive", true))
	            .setProjection(ProjectionFactoryUtil.property("budgetPhaseId"));
	        
	        Order order = OrderFactoryUtil.desc("modifiedDate");
	        
	        DynamicQuery entryQuery = DynamicQueryFactoryUtil.forClass(BudgetParticipatif.class, classLoader)
	                .add(RestrictionsFactoryUtil.eq("groupId", groupId))
	                .add(RestrictionsFactoryUtil.eq("status", status))
	                .add(PropertyFactoryUtil.forName("budgetPhaseId").in(activePhaseQuery))
	                .addOrder(order);
	        
	        List<BudgetParticipatif> entries = BudgetParticipatifLocalServiceUtil.dynamicQuery(entryQuery);

	        return entries;
	    }
	    catch (Exception e) {
	    	try {
	            throw new SystemException(e);
	        }
	        catch (SystemException se) {
				_log.error(se.getMessage(), e);
	        }
	    }
	    finally {
	            closeSession(session);
	    }

	    return null;
	    /*
	    Return null only if there was an error returning the
	    desired list of entity objects in the try block
	    */
	}

	private Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}
