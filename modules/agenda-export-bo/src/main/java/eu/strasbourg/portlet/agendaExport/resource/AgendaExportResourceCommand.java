package eu.strasbourg.portlet.agendaExport.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.portlet.agendaExport.displayContext.EditAgendaExportDisplayContext;
import eu.strasbourg.portlet.agendaExport.dto.EventFiltersDTO;
import eu.strasbourg.portlet.agendaExport.exporter.Exporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Export d'une campagne au format JSON
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                "mvc.command.name=exportAgendaExport"
        },
        service = MVCResourceCommand.class
)
public class AgendaExportResourceCommand implements MVCResourceCommand {

    private ResourceBundle bundle = ResourceBundleUtil.getBundle(
        "content.Language", this.getClass().getClassLoader()
    );

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
            throws PortletException {

        OutputStream os = null;
        try {

            os = resourceResponse.getPortletOutputStream();

            /** Get form values **/

            //Valeur de l'instance export Agenda
            Map<Locale, String> title = LocalizationUtil.getLocalizationMap(resourceRequest, "title");
            Map<Long, List<Long>> vocabularies = getCategories(resourceRequest);
            LocalDate startDate = getDate(resourceRequest,"startDate", "0");
            LocalDate endDate = getDate(resourceRequest,"endDate", "0");
            String language = ParamUtil.getString(resourceRequest, "language");
            String[] tags = ParamUtil.getString(resourceRequest, "assetTagNames").split(",");

            //Choix du template
            String exportFormat = ParamUtil.getString(resourceRequest, "exportFormat");
            Long fileEntryId = ParamUtil.getLong(resourceRequest, "template");

            //Valeurs des agrégations
            String aggregationLevel = ParamUtil.getString(resourceRequest, "aggregationLevel");
            String firstAggregationType = ParamUtil.getString(resourceRequest, "firstAggregationType");
            String firstAggregationVocabulary = ParamUtil.getString(resourceRequest, "firstAggregationVocabulary");
            String firstAggregationCategory = ParamUtil.getString(resourceRequest, "firstAggregationCategory");
            boolean firstCategoryFilter = ParamUtil.getBoolean(resourceRequest, "firstCategoryFilter");
            String secondAggregationType = ParamUtil.getString(resourceRequest, "secondAggregationType");
            String secondAggregationVocabulary = ParamUtil.getString(resourceRequest, "secondAggregationVocabulary");
            String secondAggregationCategory = ParamUtil.getString(resourceRequest, "secondAggregationCategory");
            boolean secondCategoryFilter = ParamUtil.getBoolean(resourceRequest, "secondCategoryFilter");

            /** Asset categories **/
            List<Long[]> sortedCategories = sortCategoriesForSearch(vocabularies);
            List<AssetCategory> categories = getCategories(vocabularies);

            /** Create Filter DTO **/
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            EventFiltersDTO filters = new EventFiltersDTO(title.get(themeDisplay.getLocale()), language);
            filters.addPeriod(startDate, endDate);
            filters.setTags(Arrays.asList(tags));
            filters.addAssetCategories(categories);
            filters.setFile(getDLFileEntry(fileEntryId));
            filters.setGroupDepth(aggregationLevel);
            filters.addAggregationFilters(
                firstAggregationType,
                valueResolver(firstAggregationType, firstAggregationVocabulary, firstAggregationCategory),
                secondAggregationType,
                valueResolver(secondAggregationType, secondAggregationVocabulary, secondAggregationCategory)
            );
            filters.setFirstCategoryFilter(firstCategoryFilter);
            filters.setSecondCategoryFilter(secondCategoryFilter);

            if(exportFormat.toUpperCase().equals("DOCX")){
                if(filters.getFile() != null) {
                    Exporter.exportDOCX(
                            resourceRequest, resourceResponse, bundle, os, themeDisplay, filters, sortedCategories
                    );
                }
            }
            else if(exportFormat.toUpperCase().equals("JSON")){
                Exporter.exportJSON(
                    resourceRequest, resourceResponse , bundle, os, themeDisplay, filters, sortedCategories
                );
            }

            if(os != null) {
                os.flush();
                os.close();
            }

        } catch (Exception e) {
            _log.error(e.getMessage(), e);

            //Close output stream if needed
            if(os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException ex) {
                    _log.error(ex.getMessage(), ex);
                }
            }
        }

        return true;
    }

    /**
     *
     * @param request
     * @param field
     * @param index
     * @return Date | null
     */
    private static LocalDate getDate(ResourceRequest request, String field, String index) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if(!Validator.isNotNull(ParamUtil.getString(request, field + index))) {
            return null;
        }

        Date date = ParamUtil.getDate(request, field + index, dateFormat);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     *
     * Récupération des catégories
     * @param request
     * @return Map<Long, List<Long>>
     */
    private static Map<Long, List<Long>> getCategories(ResourceRequest request) {
        int vocabularyNumber = ParamUtil.getInteger(request, "vocabulary_number");
        Map<Long, List<Long>> vocabularies = new HashMap<>();
        for(int i = 0; i < vocabularyNumber; i++) {
            long vocabularyId = ParamUtil.getLong(request, "vocabulary_" + i + "_id");
            long[] categoryIds = ParamUtil.getLongValues(request, "vocabulary_" + i + "_select");
            if(categoryIds.length == 0) {
                continue;
            }
            List<Long> categories = new ArrayList<>();
            for (int j = 0; j < categoryIds.length; j++) {
                categories.add(categoryIds[j]);
            }
            vocabularies.put(vocabularyId, categories);
        }
        return vocabularies;
    }

    /**
     * Récupère les données des categories contenus dans la liste des vocabulaires
     * @param vocabularyMap
     * @return
     */
    private static List<AssetCategory> getCategories(Map<Long, List<Long>> vocabularyMap) {

        List<AssetCategory> categories = new ArrayList<>();
        for (Map.Entry<Long, List<Long>> entry : vocabularyMap.entrySet()) {
            List<Long> values = entry.getValue();

            for(Long id : values) {
                try {
                    categories.add(AssetCategoryLocalServiceUtil.getCategory(id));
                } catch (PortalException e) {
                    _log.error(e.getMessage() + " : " + id);
                }
            }
        }

        return categories;

    }

    private static List<Long[]> sortCategoriesForSearch(Map<Long, List<Long>> vocabularyMap) {

        List<Long[]> sortedCategories = new ArrayList<>();
        for (Map.Entry<Long, List<Long>> entry : vocabularyMap.entrySet()) {
            List<Long> categories = entry.getValue();
            Long[] categoriesArray = new Long[categories.size()];
            int i = 0;
            for(Long category : categories) {
                categoriesArray[i] = category;
                i++;
            }
            sortedCategories.add(categoriesArray);
        }

        return sortedCategories;
    }

    /**
     * Return the complete Path of the wanted word template
     * @param fileEntryId
     * @return
     */
    private static DLFileEntry getDLFileEntry(Long fileEntryId) {

        DLFileEntry file = null;
        try {
            if(fileEntryId != 0) {
                file = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
            }
        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + fileEntryId);
        }

        return file;
    }

    /**
     * Renvoit la bonne valeur en fonction des choix dans le formulaire
     * @param type
     * @param vocabulary
     * @param category
     * @return
     */
    private static String valueResolver(String type, String vocabulary, String category) {

        if(type.equals("VOCABULARY")) {
            return vocabulary;
        }
        else if(type.equals("CATEGORY")) {
            return category;
        }

        return "";
    }

    private static final Log _log = LogFactoryUtil.getLog(AgendaExportResourceCommand.class.getName());
}
