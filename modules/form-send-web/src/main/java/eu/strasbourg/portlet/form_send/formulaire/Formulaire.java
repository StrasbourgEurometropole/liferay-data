package eu.strasbourg.portlet.form_send.formulaire;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Formulaire {

    public long formInstanceId;
    public Map<Locale, String> name;
    public List<Champ> fields;

    public Formulaire(long formInstanceId, Map<Locale, String> name, JSONArray jsonArray) {
        this.name = name;
        this.formInstanceId = formInstanceId;
        List<Champ> fieldListe = new ArrayList<Champ>();
        for (Object json : jsonArray) {
            try {
                fieldListe.add(new Champ(JSONFactoryUtil.createJSONObject(json.toString())));
            } catch (JSONException e) {
                _log.error(e.getMessage() + " : " + json);
            }
        }
        this.fields = fieldListe;
    }

    public Formulaire() {
    }

    public long getFormInstanceId() {
        return formInstanceId;
    }

    public void setFormInstanceId(long formInstanceId) {
        this.formInstanceId = formInstanceId;
    }

    public Map<Locale, String> getNameMap() {
        return name;
    }

    public void setNameMap(Map<Locale, String> name) {
        this.name = name;
    }

    public String getName(Locale locale) {
        return name.get(locale);
    }

    public List<Champ> getFields() {
        return fields;
    }

    public void setFields(JSONArray jsonArray) {
        List<Champ> fieldListe = new ArrayList<Champ>();
        for (Object json : jsonArray) {
            try {
                fieldListe.add(new Champ(JSONFactoryUtil.createJSONObject(json.toString())));
            } catch (JSONException e) {
                _log.error(e.getMessage() + " : " + json);
            }
        }
    }

    public Champ getField(String name){
        for (Champ field : getFields()) {
            if(field.getName().equals(name))
                return field;
        }

        return null;
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass());

}
