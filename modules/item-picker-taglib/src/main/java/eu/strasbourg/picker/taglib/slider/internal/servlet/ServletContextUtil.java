package eu.strasbourg.picker.taglib.slider.internal.servlet;


import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.liferay.item.selector.ItemSelector;

/**
 * @author Benjamin Bini
 */
@Component(immediate = true)
public class ServletContextUtil {

	public static final ServletContext getServletContext() {
		return _instance._getServletContext();
	}
	
	public static final ItemSelector getItemSelector() {
		return _instance._getItemSelector();
	}

	@Activate
	protected void activate() {
		_instance = this;
	}

	@Deactivate
	protected void deactivate() {
		_instance = null;
	}

	@Reference(
		target = "(osgi.web.symbolicname=eu.strasbourg.taglib.item.picker)", unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}
	
	@Reference( unbind = "-")
	protected void setItemSelector(ItemSelector itemSelector) {
		_itemSelector = itemSelector;
	}


	private ServletContext _getServletContext() {
		return _servletContext;
	}
	
	private ItemSelector _getItemSelector() {
		return _itemSelector;
	}
	
	private static ServletContextUtil _instance;

	private ServletContext _servletContext;

	private ItemSelector _itemSelector;

}
