package eu.strasbourg.portlet.agendaExport.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.portlet.agendaExport.dto.EventFiltersDTO;
import eu.strasbourg.portlet.agendaExport.exporter.Exporter;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
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

        try {

            /** Get form values **/
            Map<Locale, String> title = LocalizationUtil.getLocalizationMap(resourceRequest, "title");
            Map<Long, List<Long>> vocabularies = getCategories(resourceRequest);
            LocalDate startDate = getDate(resourceRequest,"startDate", "0");
            LocalDate endDate = getDate(resourceRequest,"endDate", "0");
            String language = ParamUtil.getString(resourceRequest, "language");
            String[] tags = ParamUtil.getString(resourceRequest, "assetTagNames").split(",");

            String exportFormat = ParamUtil.getString(resourceRequest, "exportFormat");
            String docxFilename = ParamUtil.getString(resourceRequest, "template");
            String dataTemplate = ParamUtil.getString(resourceRequest, "dataOrder");

            /** Asset categories **/
            List<Long[]> sortedCategories = sortCategoriesForSearch(vocabularies);
            List<AssetCategory> categories = getCategories(vocabularies);

            /** Create Filter DTO **/
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            EventFiltersDTO filters = new EventFiltersDTO(title.get(themeDisplay.getLocale()), language);
            filters.addPeriod(startDate, endDate);
            filters.setTags(Arrays.asList(tags));
            filters.addAssetCategories(categories);
            filters.setFilename(docxFilename);
            filters.setFilepath(getFilePath(docxFilename));
            List<String> orderArgs = getOrdersFromUserInput(dataTemplate);
            filters.setGroupOrdering(orderArgs.size() >= 1 ? orderArgs.get(0) : "");
            filters.setSubGroupOrdering((orderArgs.size() >= 2 ? orderArgs.get(1) : ""));

            OutputStream os = null;

            //Get the output stream
            //TODO Correct this bug !
            try {
                os = resourceResponse.getPortletOutputStream();
            } catch (Exception e) {
                if (os != null) {
                    try {

                        os.close();
                        os.flush();
                        os = resourceResponse.getPortletOutputStream();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            if(exportFormat.toUpperCase().equals("DOCX")){
                os = Exporter.exportDOCX(
                    resourceRequest, resourceResponse, os, themeDisplay, filters, sortedCategories
                );
            }
            else if(exportFormat.toUpperCase().equals("JSON")){
                os = Exporter.exportJSON(
                    resourceRequest, resourceResponse, os, themeDisplay, filters, sortedCategories
                );
            }

            os.close();
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Retrieve values
//		Map<Locale, String> title = LocalizationUtil.getLocalizationMap(resourceRequest, "title");
//		Map<Long, List<Long>> vocabularies = this.getCategories(resourceRequest);
//		LocalDate startDate = this.getDate(resourceRequest,"startDate", "0");
//		LocalDate endDate = this.getDate(resourceRequest,"endDate", "0");
//		String language = ParamUtil.getString(resourceRequest, "language");
//		String exportFormat = ParamUtil.getString(resourceRequest, "exportFormat");
//		String[] tags = ParamUtil.getString(resourceRequest, "assetTagNames").split(",");

        //Load categories and vocabularies
//		List<Long[]> sortedCategories = sortCategoriesForSearch(vocabularies);
//		List<AssetCategory> categories = getCategories(vocabularies);

        //Get the output stream
//		try {
//			os = resourceResponse.getPortletOutputStream();
//		} catch (Exception e) {
//			if (os != null) {
//				try {
//
//					os.close();
//					os.flush();
//					os = resourceResponse.getPortletOutputStream();
//
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//
//		try {

        //Create DTO filter Object
//			ThemeDisplay themeDisplay = null;
//			EventFiltersDTO filters = null;
//
//			themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
//			filters = new EventFiltersDTO(title.get(themeDisplay.getLocale()), language);
//			filters.addPeriod(startDate, endDate);
//			filters.setTags(Arrays.asList(tags));
//			filters.addAssetCategories(categories);

        //Search query
//			List<Event> events = searchEvents(resourceRequest, resourceResponse, themeDisplay, filters, sortedCategories);
//
//			//Create and fill DTO objects
//			ExportAgendaDTO data = this.createDTOObjects(themeDisplay, filters, events, 1);
//
//			StringWriter writer = new StringWriter();
//			JAXBContext context = JAXBContext.newInstance(ExportAgendaDTO.class);
//			Marshaller m = context.createMarshaller();
//			m.marshal(data, writer);
//			String xmlContent = writer.toString();
//
//			WordprocessingMLPackage wordMLPackage = null;

//			File file = null;
//			String docx_filepath = "C:/2019(3).docx";
//
//            //Load docx file
//            wordMLPackage = Docx4J.load(new File(docx_filepath));
//			resourceResponse.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");
//			resourceResponse.setProperty("content-disposition", "attachment; filename=test.docx");
////          Docx4J.bind(wordMLPackage, xmlContent, Docx4J.FLAG_BIND_INSERT_XML | Docx4J.FLAG_BIND_BIND_XML);
////			Save saver = new Save(wordMLPackage);
////			saver.save(os);
//

//			if (os != null) {
//				os.close();
//				os.flush();
//			}

//		} catch (Exception e) {
//
//			if(os != null) {
//				//TODO refactor class
//				try {
//					os.close();
//					os.flush();
//					resourceResponse.getWriter().close();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//
//			e.printStackTrace();
//		}

        return true;
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
                    e.printStackTrace();
                }
            }
        }

        return categories;

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
     * @param filename
     * @return
     */
    private static String getFilePath(String filename) {
        String directoryPath = StrasbourgPropsUtil.getAgendaExportTemplateDirectory();
        return directoryPath + "/" + filename + ".docx";
    }

    /**
     * Check if the file exist
     * @param filename
     * @return
     */
    private static boolean fileExist(String filename) {

        File file = new java.io.File(getFilePath(filename));

        if(file.exists() && !file.isDirectory()) {
            return true;
        }

        return false;
    }

    /**
     * Permet de décoder le mode de tri des documents words
     * TODO -> Enum
     * @return
     */
    private static List<String> getOrdersFromUserInput(String key) {

        List<String> filters = new ArrayList<>();
        Map<String, List<String>> templates = new HashMap<>();
        templates.put("s", Arrays.asList(""));
        templates.put("gj", Arrays.asList("DAY"));
        templates.put("gm", Arrays.asList("MONTH"));
        templates.put("gc", Arrays.asList("CATEGORY"));
        templates.put("ggjc", Arrays.asList("DAY", "CATEGORY"));
        templates.put("ggcj", Arrays.asList("CATEGORY", "DAY"));
        templates.put("ggcm", Arrays.asList("CATEGORY", "MONTH"));
        templates.put("ggmj", Arrays.asList("MONTH", "DAY"));
        templates.put("ggmc", Arrays.asList("MONTH", "CATEGORY"));

        //TODO check if template does exist
        filters = templates.get(key);

        return filters;
    }
}
