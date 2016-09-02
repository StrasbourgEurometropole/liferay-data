
package eu.strasbourg.picker.taglib.file.servlet.taglib;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import eu.strasbourg.picker.taglib.FileObject;
import eu.strasbourg.picker.taglib.file.internal.servlet.ServletContextUtil;

/**
 * @author Benjamin Bini
 */
public class FilePickerTag extends IncludeTag {

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
		FileItemSelectorCriterion fileItemSelectorCriterion = new FileItemSelectorCriterion();
		fileItemSelectorCriterion
			.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);

		PortletURL itemSelectorURL = ServletContextUtil.getItemSelector()
			.getItemSelectorURL(
				RequestBackedPortletURLFactoryUtil.create(request),
				"itemSelected" + _name, fileItemSelectorCriterion);
		request.setAttribute("itemSelectorURL", itemSelectorURL);
	}

	private static final String _PAGE = "/file/file-picker-page.jsp";

	private String _label;
	private String _name;
	private String _required;
	private String _value;
	private String _multiple;

}