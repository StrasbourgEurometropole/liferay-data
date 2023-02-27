package eu.strasbourg.portlet.familySpace;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FamilySpaceResponse {

	public int codeRetour;
	public String erreurDescription;
	public int count;
	public List<Family> families;

	public FamilySpaceResponse(JSONObject json) {
		codeRetour = json.getInt("codeRetour");
		erreurDescription = json.getString("erreurDescription");
		count = json.getInt("count");
		families = new ArrayList<Family>();
		if (codeRetour == 0) {
			try {
				JSONArray jsonFamilies = JSONFactoryUtil.createJSONArray(json.getString("familles"));
				for (Object family : jsonFamilies) {
					families.add(new Family(JSONFactoryUtil.createJSONObject(family.toString())));
				}
			} catch (JSONException e) {
				_log.error(e.getMessage(), e);
			}
		}
	}
	public FamilySpaceResponse() {
	}

	public int getCodeRetour() {
		return codeRetour;
	}

	public void setCodeRetour(int codeRetour) {
		this.codeRetour = codeRetour;
	}

	public String getErreurDescription() {
		return erreurDescription;
	}

	public void setErreurDescription(String erreurDescription) {
		this.erreurDescription = erreurDescription;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Family> getFamilies() {
		return families;
	}

	public void setFamilies(List<Family> families) {
		this.families = families;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
