package eu.strasbourg.portlet.familySpace;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Family {

    public String idFamily;
    public List<Person> persons;

    public Family(JSONObject json) {
        idFamily = json.getString("identifiantFamille");
        persons = new ArrayList<Person>();
        try {
            JSONArray jsonPersons = JSONFactoryUtil.createJSONArray(json.getString("personnes"));
            for (Object person : jsonPersons) {
                persons.add(new Person(JSONFactoryUtil.createJSONObject(person.toString())));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Family() {
    }

    public String getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(String idFamily) {
        this.idFamily = idFamily;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
