package eu.strasbourg.portlet.form_send.formulaire;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class Option {
    public Map<Locale, String> label;
    public String value;

    public Option(JSONObject json) {
        label = new HashMap<Locale, String>();
        JSONObject jsonObject = json.getJSONObject("label");
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String localeAsString = keys.next();
            label.put(Locale.forLanguageTag(localeAsString.replace("_", "-")),jsonObject.getString(localeAsString));
        }

        value = json.getString("value");
    }

    public Option() {}

    public Map<Locale, String> getLabelMap() {
        return label;
    }

    public void setLabelMap(Map<Locale, String> label) {
        this.label = label;
    }

    public String getLabel(Locale locale) {
        return label.get(locale).substring(0, 1).toUpperCase() + label.get(locale).substring(1);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
