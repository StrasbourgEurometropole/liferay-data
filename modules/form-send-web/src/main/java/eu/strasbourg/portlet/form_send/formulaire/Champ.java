package eu.strasbourg.portlet.form_send.formulaire;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.*;

public class Champ {

    public String dataType;
    public String displayStyle;
    public String fieldNamespace;
    public String indexType;
    public String localizable;
    public String name;
    public String type;
    public String visibilityExpression;
    public String dataSourceType;
    public String ddmDataProviderInstanceId;
    public String text;

    public boolean readOnly;
    public boolean repeatable;
    public boolean required;
    public boolean showLabel;
    public boolean multiple;
    public boolean inline;
    public boolean showAsSwitcher;

    public List<Option> options;

    public Map<Locale, String> label;
    public Map<Locale, String> tip;
    public Map<Locale, String> tooltip;
    public Map<Locale, String> placeholder;
    public Map<Locale, String> predefinedValue;

    // contient errorMessage et expression
    public Map<String, String> validation;

    public Champ(JSONObject json) {
        dataType = json.getString("dataType");
        displayStyle = json.getString("displayStyle");
        fieldNamespace = json.getString("fieldNamespace");
        indexType = json.getString("indexType");
        localizable = json.getString("localizable");
        name = json.getString("name");
        type = json.getString("type");
        visibilityExpression = json.getString("visibilityExpression");
        dataSourceType = json.getString("dataSourceType");
        ddmDataProviderInstanceId = json.getString("ddmDataProviderInstanceId");
        text = json.getString("text");

        readOnly = json.getBoolean("readOnly");
        repeatable = json.getBoolean("repeatable");
        required = json.getBoolean("required");
        showLabel = json.getBoolean("showLabel");
        multiple = json.getBoolean("multiple");
        inline = json.getBoolean("inline");
        showAsSwitcher = json.getBoolean("showAsSwitcher");

        List<Option> optionsListe = new ArrayList<Option>();
        JSONArray jsonArray = json.getJSONArray("options");
        if(Validator.isNotNull(jsonArray)){
            for (Object option : jsonArray) {
                try {
                    optionsListe.add(new Option(JSONFactoryUtil.createJSONObject(option.toString())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        this.options = optionsListe;

        label = new HashMap<Locale, String>();
        JSONObject jsonObject = json.getJSONObject("label");
        if(Validator.isNotNull(jsonObject)) {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String localeAsString = keys.next();
                label.put(Locale.forLanguageTag(localeAsString.replace("_", "-")), jsonObject.getString(localeAsString));
            }
        }

        tip = new HashMap<Locale, String>();
        jsonObject = json.getJSONObject("tip");
        if(Validator.isNotNull(jsonObject)) {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String localeAsString = keys.next();
                tip.put(Locale.forLanguageTag(localeAsString.replace("_", "-")), jsonObject.getString(localeAsString));
            }
        }

        tooltip = new HashMap<Locale, String>();
        jsonObject = json.getJSONObject("tooltip");
        if(Validator.isNotNull(jsonObject)) {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String localeAsString = keys.next();
                tooltip.put(Locale.forLanguageTag(localeAsString.replace("_", "-")), jsonObject.getString(localeAsString));
            }
        }

        placeholder = new HashMap<Locale, String>();
        jsonObject = json.getJSONObject("placeholder");
        if(Validator.isNotNull(jsonObject)) {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String localeAsString = keys.next();
                placeholder.put(Locale.forLanguageTag(localeAsString.replace("_", "-")), jsonObject.getString(localeAsString));
            }
        }

        predefinedValue = new HashMap<Locale, String>();
        jsonObject = json.getJSONObject("predefinedValue");
        if(Validator.isNotNull(jsonObject)) {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String localeAsString = keys.next();
                predefinedValue.put(Locale.forLanguageTag(localeAsString.replace("_", "-")), jsonObject.getString(localeAsString));
            }
        }

        validation = new HashMap<String, String>();
        jsonObject = json.getJSONObject("validation");
        if(Validator.isNotNull(jsonObject)) {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                validation.put(key, jsonObject.getString(key));
            }
        }

    }

    public Champ() {
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(String displayStyle) {
        this.displayStyle = displayStyle;
    }

    public String getFieldNamespace() {
        return fieldNamespace;
    }

    public void setFieldNamespace(String fieldNamespace) {
        this.fieldNamespace = fieldNamespace;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getLocalizable() {
        return localizable;
    }

    public void setLocalizable(String localizable) {
        this.localizable = localizable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisibilityExpression() {
        return visibilityExpression;
    }

    public void setVisibilityExpression(String visibilityExpression) {
        this.visibilityExpression = visibilityExpression;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getDdmDataProviderInstanceId() {
        return ddmDataProviderInstanceId;
    }

    public void setDdmDataProviderInstanceId(String ddmDataProviderInstanceId) {
        this.ddmDataProviderInstanceId = ddmDataProviderInstanceId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isShowLabel() {
        return showLabel;
    }

    public void setShowLabel(boolean showLabel) {
        this.showLabel = showLabel;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }

    public boolean isShowAsSwitcher() {
        return showAsSwitcher;
    }

    public void setShowAsSwitcher(boolean showAsSwitcher) {
        this.showAsSwitcher = showAsSwitcher;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Map<Locale, String> getLabelMap() {
        return label;
    }

    public void setLabelMap(Map<Locale, String> label) {
        this.label = label;
    }

    public String getLabel(Locale locale) {
        return label.get(locale);
    }

    public Map<Locale, String> getTipMap() {
        return tip;
    }

    public void setTipMap(Map<Locale, String> tip) {
        this.tip = tip;
    }

    public String getTip(Locale locale) {
        return tip.get(locale);
    }

    public Map<Locale, String> getTooltipMap() {
        return tooltip;
    }

    public void setTooltipMap(Map<Locale, String> tooltip) {
        this.tooltip = tooltip;
    }

    public String getTooltip(Locale locale) {
        return tooltip.get(locale);
    }

    public Map<Locale, String> getPlaceholderMap() {
        return placeholder;
    }

    public void setPlaceholderMap(Map<Locale, String> placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder(Locale locale) {
        return placeholder.get(locale);
    }

    public Map<Locale, String> getPredefinedValueMap() {
        return predefinedValue;
    }

    public void setPredefinedValueMap(Map<Locale, String> predefinedValue) {
        this.predefinedValue = predefinedValue;
    }

    public String getPredefinedValue(Locale locale) {
        return predefinedValue.get(locale);
    }

    public Map<String, String> getValidationMap() {
        return validation;
    }

    public void setValidationMap(Map<String, String> validation) {
        this.validation = validation;
    }

    public String getValidationErrorMessage() {
        return validation.get("errorMessage");
    }

    public String getValidationExpression() {
        return validation.get("expression");
    }
}
