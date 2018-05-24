package eu.strasbourg.portlet.graveyard.portlet;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class GraveyardResponse {
    
	public String err_class;
	public String err_desc;
	public String data;
    public String count;
    public String err;
    public String erreur;
    public List<DefuntDTO> defunts; 
    
    public GraveyardResponse(JSONObject json) {
    	err_class = json.getString("err_class");
    	err_desc = json.getString("err_desc");
    	data = json.getString("data");
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
				e.printStackTrace();
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
}
