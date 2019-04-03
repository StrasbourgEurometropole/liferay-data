package eu.strasbourg.portlet.slider_une;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import eu.strasbourg.portlet.slider_une.configuration.SliderUneConfiguration;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;

import javax.portlet.RenderRequest;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SliderUneDisplayContext {

    private ThemeDisplay themeDisplay;
    private RenderRequest request;
    private SliderUneConfiguration configuration;
    private List<AssetEntry> assetEntries;

    public SliderUneDisplayContext(ThemeDisplay themeDisplay, RenderRequest request) {
        this.themeDisplay = themeDisplay;
        this.request = request;
        try {
            this.configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(SliderUneConfiguration.class);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public SliderUneConfiguration getConfiguration() {
        return configuration;
    }

    public boolean showTags() {
        return configuration.showTags();
    }

    public String getVirtualHostName() {
        return themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname();
    }

    public String getVirtualStrasbourgHostName() {
        Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(), "/strasbourg.eu");
        return group.getPublicLayoutSet().getVirtualHostname();
    }

    // récupération des actu, webmag et évènements
    public List<AssetEntry> getAssetEntries() {
        if (assetEntries == null) {
            List<AssetEntry> actuWebmagEvent = new ArrayList<AssetEntry>();
            String classPKs = configuration.classPKs();
            for (String classPK : classPKs.split(",")) {
                if (Validator.isNotNull(classPK)) {
                    AssetEntry journalArticleEntry = null;
                    journalArticleEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(),
                            Long.parseLong(classPK));
                    if (journalArticleEntry != null && journalArticleEntry.isVisible()) {
                        actuWebmagEvent.add(journalArticleEntry);
                    }
                    AssetEntry eventEntry = null;
                    eventEntry = AssetEntryLocalServiceUtil.fetchEntry(Event.class.getName(),
                            Long.parseLong(classPK));
                    if (eventEntry != null && eventEntry.isVisible()) {
                        actuWebmagEvent.add(eventEntry);
                    }
                }
            }
            assetEntries = actuWebmagEvent;
        }

        return assetEntries;
    }

    public String DeleteTag(String html) {

        Pattern p = Pattern.compile("<[^>]*>");
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    private String getJournalArticleFieldValue(JournalArticle article, String field, Locale locale) {
        String content = article.getContentByLocale(locale.toString());

        String value = "";

        com.liferay.portal.kernel.xml.Document document = null;

        try {
            document = SAXReaderUtil.read(new StringReader(content));
            Node node = document.selectSingleNode("/root/dynamic-element[@name='" + field + "']/dynamic-content");
            if (node.getText().length() > 0) {
                value = node.getText();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }

    public String getJournalArticleTitle(JournalArticle article, Locale locale) {
        return getJournalArticleFieldValue(article, "title", locale);
    }

    public String getJournalArticleCatcher(JournalArticle article, Locale locale) {
        return getJournalArticleFieldValue(article, "chapo", locale);
    }

    public String getJournalArticleImage(JournalArticle article, Locale locale) {
        return getJournalArticleFieldValue(article, "thumbnail", locale);
    }

    public boolean isMag(String[] tagNames) {
        if (Arrays.toString(tagNames).contains("euromag") || Arrays.toString(tagNames).contains("villemag")
                || Arrays.toString(tagNames).contains("webmag"))
            return true;
        else
            return false;
    }

    public String getJSONEncodedString(String source) {
        return HtmlUtil.escapeJS(source);
    }

    public boolean hasFocus(String[] tagNames) {
        if (Arrays.toString(tagNames).contains("focus"))
            return true;
        else
            return false;
    }
}