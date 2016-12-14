
package eu.strasbourg.picker.taglib.image.servlet.taglib;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import eu.strasbourg.picker.taglib.FileObject;
import eu.strasbourg.picker.taglib.image.internal.servlet.ServletContextUtil;

/**
 * @author Benjamin Bini
 */
public class ImagePickerTag extends IncludeTag {

	public void setName(String name) {
		_name = name;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setRequired(String required) {
		_required = required;
	}

	public void setValue(String value) {
		_value = value;
	}

	public void setMultiple(String multiple) {
		_multiple = multiple;
	}

	public void setGlobal(String global) {
		_global = global;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		_name = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("name", _name);
		request.setAttribute("label", _label);
		request.setAttribute("required", "true".equals(_required));
		request.setAttribute("value", "0".equals(_value) ? "" : _value);
		request.setAttribute("multiple", "true".equals(_multiple));
		request.setAttribute("global", "true".equals(_global));

		// Files
		List<FileObject> files = new ArrayList<FileObject>();

		for (String fileId : _value.split(",")) {
			if (Validator.isNumber(fileId) && Long.parseLong(fileId) > 0) {
				FileObject file = new FileObject(Long.parseLong(fileId));
				if (file != null && file.getId() > 0) {
					files.add(file);
				}
			}
		}

		request.setAttribute("files", files);

		// ItemSelector URL
		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<>();
		desiredItemSelectorReturnTypes
			.add(new FileEntryItemSelectorReturnType());
		ImageItemSelectorCriterion imageItemSelectorCriterion = new ImageItemSelectorCriterion();
		imageItemSelectorCriterion
			.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);
		PortletURL itemSelectorURL = ServletContextUtil.getItemSelector()
			.getItemSelectorURL(
				RequestBackedPortletURLFactoryUtil.create(request),
				"itemSelected" + _name, imageItemSelectorCriterion);
		
		// Si l'attribut "global" est "true", on se met sur le groupe global en
		// modifiant l'URL
		String itemSelectorURLString = itemSelectorURL.toString();
		if ("true".equals(this._global)) {	
			itemSelectorURLString = itemSelectorURLString.replaceAll("(?<=group).*(?=~)", "/global/");
		}

		request.setAttribute("itemSelectorURL", itemSelectorURLString);
	}

	private static final String _PAGE = "/image/image-picker-page.jsp";

	private String _label;
	private String _name;
	private String _required;
	private String _value;
	private String _multiple;
	private String _global;

}