package eu.strasbourg.webservice.csmap.service;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class WSSettings {

    public static List<String> getModuleStatusFieldValue(JournalArticle article) {
        String content = article.getContentByLocale(Locale.FRANCE.toString());

        List<String> value  = new ArrayList<>();
        List<String> result = new ArrayList<String>();

        com.liferay.portal.kernel.xml.Document document = null;

        try {
            document = SAXReaderUtil.read(new StringReader(content));
            value = Arrays.asList(document.getStringValue().split("\n\t"));

            for (String str : value) {
                if (str != null && !str.isEmpty( )&& !str.equals("\n")) {
                    result.add(str);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
