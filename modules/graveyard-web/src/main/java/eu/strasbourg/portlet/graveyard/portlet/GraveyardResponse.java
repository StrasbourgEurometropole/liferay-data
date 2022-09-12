package eu.strasbourg.portlet.graveyard.portlet;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class GraveyardResponse {

	public String count;
	public String err;
	public String erreur;
	public String code_erreur;
	public List<DefuntDTO> defunts;
	public String err_class;
	public String err_desc;
    
    public GraveyardResponse(JSONObject json) {
    	err_class = json.getString("err_class");
    	err_desc = json.getString("err_desc");
		code_erreur = json.getString("code_erreur");
    	count = json.getString("count");
    	err = json.getString("err");
    	erreur = json.getString("erreur");
    	defunts = new ArrayList<DefuntDTO>();
		if (err.equals("0")) {
			try {
				JSONArray jsonDefunts = JSONFactoryUtil.createJSONArray(json.getString("result"));
				for (Object defunt : jsonDefunts) {
					defunts.add(new DefuntDTO(JSONFactoryUtil.createJSONObject(defunt.toString())));
				}
			} catch (JSONException e) {
				_log.error(e.getMessage(), e);
			}
		}
    }

    public GraveyardResponse() {
    }

	public String getErr_class() {
		return err_class;
	}

	public void setErr_class(String err_class) {
		this.err_class = err_class;
	}

	public String getErr_desc() {
		return err_desc;
	}

	public void setErr_desc(String err_desc) {
		this.err_desc = err_desc;
	}

	public String getCodeErreur() {
		return code_erreur;
	}

	public void setCodeErreur(String code_erreur) {
		this.code_erreur = code_erreur;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public List<DefuntDTO> getDefunts() {
		return defunts;
	}

	public void setDefunts(List<DefuntDTO> defunts) {
		this.defunts = defunts;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
