
package eu.strasbourg.picker.taglib.entity.servlet.taglib;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import eu.strasbourg.picker.taglib.file.internal.servlet.ServletContextUtil;
import eu.strasbourg.portlet.artwork.itemselector.ArtworkCollectionItemSelectorCriterion;
import eu.strasbourg.portlet.artwork.itemselector.ArtworkItemSelectorCriterion;
import eu.strasbourg.portlet.edition.itemselector.EditionGalleryItemSelectorCriterion;
import eu.strasbourg.portlet.edition.itemselector.EditionItemSelectorCriterion;

/**
 * @author Benjamin Bini
 */
public class EntityPickerTag extends IncludeTag {

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
	
	public void setType(String type) {
		_type = type;
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
		request.setAttribute("_type", _type);

		// Entities
		List<AssetEntry> entities = new ArrayList<AssetEntry>();

		for (String entityId : _value.split(",")) {
			if (Validator.isNumber(entityId) && Long.parseLong(entityId) > 0) {
				AssetEntry entry;
				try {
					entry = AssetEntryLocalServiceUtil.getEntry(_type, Long.parseLong(entityId));
					if (entry != null) {
						entities.add(entry);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("entities", entities);


		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<>();
		desiredItemSelectorReturnTypes.add(new URLItemSelectorReturnType());
		
		PortletURL itemSelectorURL;
		switch(_type) {
		case "eu.strasbourg.service.edition.model.Edition":
			EditionItemSelectorCriterion editionItemSelectorCriterion = new EditionItemSelectorCriterion();
			editionItemSelectorCriterion.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);
    		itemSelectorURL = ServletContextUtil.getItemSelector()
    			.getItemSelectorURL(
    				RequestBackedPortletURLFactoryUtil.create(request),
    				"itemSelected" + _name, editionItemSelectorCriterion);
    		itemSelectorURL.setParameter("multiple", _multiple);
    		request.setAttribute("itemSelectorURL", itemSelectorURL);
			break;
		case "eu.strasbourg.service.edition.model.EditionGallery":
			EditionGalleryItemSelectorCriterion editionGalleryItemSelectorCriterion = new EditionGalleryItemSelectorCriterion();
			editionGalleryItemSelectorCriterion.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);
    		itemSelectorURL = ServletContextUtil.getItemSelector()
    			.getItemSelectorURL(
    				RequestBackedPortletURLFactoryUtil.create(request),
    				"itemSelected" + _name, editionGalleryItemSelectorCriterion);
    		itemSelectorURL.setParameter("multiple", _multiple);
    		request.setAttribute("itemSelectorURL", itemSelectorURL);
			break;
    	case "eu.strasbourg.service.artwork.model.Artwork":
    		ArtworkItemSelectorCriterion artworkItemSelectorCriterion = new ArtworkItemSelectorCriterion();
    		artworkItemSelectorCriterion.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);
    		itemSelectorURL = ServletContextUtil.getItemSelector()
    			.getItemSelectorURL(
    				RequestBackedPortletURLFactoryUtil.create(request),
    				"itemSelected" + _name, artworkItemSelectorCriterion);
    		itemSelectorURL.setParameter("multiple", _multiple);
    		request.setAttribute("itemSelectorURL", itemSelectorURL);
    		break;
    	case "eu.strasbourg.service.artwork.model.ArtworkCollection":
    		ArtworkCollectionItemSelectorCriterion artworkCollectionItemSelectorCriterion = new ArtworkCollectionItemSelectorCriterion();
    		artworkCollectionItemSelectorCriterion.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);
    		itemSelectorURL = ServletContextUtil.getItemSelector()
    			.getItemSelectorURL(
    				RequestBackedPortletURLFactoryUtil.create(request),
    				"itemSelected" + _name, artworkCollectionItemSelectorCriterion);
    		itemSelectorURL.setParameter("multiple", _multiple);
    		request.setAttribute("itemSelectorURL", itemSelectorURL);
    		break;
    	}
		
	}

	private static final String _PAGE = "/entity/entity-picker-page.jsp";

	private String _label;
	private String _name;
	private String _required;
	private String _value;
	private String _multiple;
	private String _type;

}