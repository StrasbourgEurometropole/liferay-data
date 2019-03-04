package eu.strasbourg.portlet.form_send.formulaire;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Formulaire {

    public long recordSetId;
    public Map<Locale, String> name;
    public List<Champ> fields;

    public Formulaire(long recordSetId, Map<Locale, String> name, JSONArray jsonArray) {
        this.name = name;
        this.recordSetId = recordSetId;
        List<Champ> fieldListe = new ArrayList<Champ>();
        for (Object json : jsonArray) {
            try {
                fieldListe.add(new Champ(JSONFactoryUtil.createJSONObject(json.toString())));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.fields = fieldListe;
    }

    public Formulaire() {
    }

    public long getRecordSetId() {
        return recordSetId;
    }

    public void setRecordSetId(long recordSetId) {
        this.recordSetId = recordSetId;
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
                e.printStackTrace();
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

}
