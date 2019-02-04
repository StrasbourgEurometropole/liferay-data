
package eu.strasbourg.service.place.model.impl;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.place.MairieStateSOAPClient;
import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.model.Price;
import eu.strasbourg.service.place.model.PublicHoliday;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.PeriodLocalServiceUtil;
import eu.strasbourg.service.place.service.PriceLocalServiceUtil;
import eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil;
import eu.strasbourg.service.place.service.ScheduleExceptionLocalServiceUtil;
import eu.strasbourg.service.place.service.SubPlaceLocalServiceUtil;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.OccupationState;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.models.Pair;

/**
 * The extended model implementation for the Place service. Represents a row in
 * the &quot;place_Place&quot; database table, with each column mapped to a
 * property of this class.
 * <p>
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.Place} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class PlaceImpl extends PlaceBaseImpl {
    /**
     *
     */
    private static final long serialVersionUID = -8684903451087898120L;
    private Date publicHoliday;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a place
     * model instance should use the {@link
     * eu.strasbourg.service.place.model.Place} interface instead.
     */
    public PlaceImpl() {
    }

    /**
     * Retourne l'AssetEntry rattaché cet item
     */
    @Override
    public AssetEntry getAssetEntry() {
        return AssetEntryLocalServiceUtil.fetchEntry(Place.class.getName(), this.getPlaceId());
    }

    /**
     * Renvoie la liste des AssetCategory rattachées à cet item (via
     * l'assetEntry)
     */
    @Override
    public List<AssetCategory> getCategories() {
        return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
    }

    /**
     * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
     */
    @Override
    public String getImageURL() {
        return FileEntryHelper.getFileEntryURL(this.getImageId());
    }

    /**
     * Retourne le copyright de l'image principale
     */
    @Override
    public String getImageCopyright(Locale locale) {
        return FileEntryHelper.getImageCopyright(this.getImageId(), locale);
    }

    /**
     * Retourne le prix rattaché au lieu
     */
    @Override
    public Price getPrice() {
        return PriceLocalServiceUtil.fetchPrice(this.getPriceId());
    }

    /**
     * Retourne les ScheduleExceptions du lieu
     */
    @Override
    public List<ScheduleException> getScheduleExceptions() {
        return ScheduleExceptionLocalServiceUtil.getByPlaceId(this.getPlaceId());
    }

    /**
     * Retourne les PublicHolidays
     */
    @Override
    public List<PublicHoliday> getPublicHolidays() {
        return PublicHolidayLocalServiceUtil.getPublicHolidaies(-1, -1);
    }

    /**
     * Renvoie la liste des IDs des ScheduleExceptions auxquelles ce lieu
     * appartient sous forme de String
     */
    @Override
    public String getScheduleExceptionsIds() {
        List<ScheduleException> scheduleExceptions = this.getScheduleExceptions();
        String ids = "";
        for (ScheduleException scheduleException : scheduleExceptions) {
            if (ids.length() > 0) {
                ids += ",";
            }
            ids += scheduleException.getExceptionId();
        }
        return ids;
    }

    /**
     * Retourne les sous lieux du lieux
     */
    @Override
    public List<SubPlace> getSubPlaces() {
        return SubPlaceLocalServiceUtil.getByPlaceId(this.getPlaceId());
    }

    /**
     * Retourne les sous lieux publiés du lieu
     */
    @Override
    public List<SubPlace> getPublishedSubPlaces() {
        return SubPlaceLocalServiceUtil.getByPlaceId(this.getPlaceId()).stream().filter(s -> s.isApproved())
                .collect(Collectors.toList());
    }

    // /**
    // * Renvoie la liste des IDs des sous lieux auxquelles ce lieu appartient
    // * sous forme de String
    // */
    // @Override
    // public String getSubPlacesIds() {
    // List<SubPlace> subPlaces = this.getSubPlaces();
    // String ids = "";
    // for (SubPlace subPlace : subPlaces) {
    // if (ids.length() > 0) {
    // ids += ",";
    // }
    // ids += subPlace.getSubPlaceId();
    // }
    // return ids;
    // }

    /**
     * Retourne les Periods du lieux
     */
    @Override
    public List<Period> getPeriods() {
        return PeriodLocalServiceUtil.getByPlaceId(this.getPlaceId());
    }

    /**
     * Retourne les périodes qui ne sont pas par défaut
     */
    @Override
    public List<Period> getNonDefaultPeriods() {
        return this.getPeriods().stream().filter(p -> !p.getDefaultPeriod()).collect(Collectors.toList());
    }

    /**
     * Retourne la période par défaut
     */
    @Override
    public Period getDefaultPeriod() {
        for (Period period : this.getPeriods()) {
            if (period.getDefaultPeriod()) {
                return period;
            }
        }
        return null;
    }

    /**
     * Retourne les territoire du lieu
     */
    @Override
    public List<AssetCategory> getTerritories() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.TERRITORY);
    }

    /**
     * Retourne les types du lieu
     */
    @Override
    public List<AssetCategory> getTypes() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.PLACE_TYPE);
    }

    /**
     * Retourne le label des types de l'événement
     */
    @Override
    public String getTypeLabel(Locale locale) {
        String types = "";
        for (AssetCategory type : this.getTypes()) {
            if (types.length() > 0) {
                types += " - ";
            }
            types += type.getTitle(locale);
        }
        return types;
    }

    /**
     * Retourne la ville
     */
    @Override
    public String getCity(Locale locale) {
        AssetCategory cityCategory = this.getCityCategory();
        if (cityCategory != null) {
            return cityCategory.getTitle(locale);
        }
        return "";
    }

    /**
     * Vérifie si le lieu à accès au temps réel
     *
     * @throws PortalException
     */
    @Override
    public Boolean isEnabled() throws PortalException {
        List<AssetCategory> types = this.getTypes();
        for (AssetCategory type : types) {
            if (Validator.isNotNull(AssetVocabularyHelper.getCategoryProperty(type.getCategoryId(), "realtime"))) {
                return true;
            }
            // vérification des parents
            for (AssetCategory ancestor : type.getAncestors()) {
                if (Validator
                        .isNotNull(AssetVocabularyHelper.getCategoryProperty(ancestor.getCategoryId(), "realtime"))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retourne true si le type du lieu doit avoir un calendrier d'horaires
     */
    @Override
    public Boolean hasScheduleTable() {
        List<AssetCategory> types = this.getTypes();
        for (AssetCategory type : types) {
            String hasSchedule = AssetVocabularyHelper.getCategoryProperty(type.getCategoryId(), "schedule");
            if (Validator.isNotNull(hasSchedule) && hasSchedule.equals("true")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la catégorie Territoire correspondant à la ville du lieu
     */
    @Override
    public AssetCategory getCityCategory() {
        List<AssetCategory> territories = this.getTerritories();
        for (AssetCategory territory : territories) {
            try {
                if (territory.getAncestors().size() == 1) {
                    return territory;
                }
            } catch (PortalException e) {
                continue;
            }
        }
        return null;
    }

    /**
     * Retourne le quartier
     */
    @Override
    public String getDistrict(Locale locale) {
        AssetCategory districtCategory = this.getDistrictCategory();
        if (districtCategory != null) {
            return districtCategory.getTitle(locale);
        }
        return "";
    }

    /**
     * Retourne la catégorie Territoire correspondant au quartier du lieu
     */
    @Override
    public AssetCategory getDistrictCategory() {
        List<AssetCategory> territories = this.getTerritories();
        for (AssetCategory territory : territories) {
            try {
                if (territory.getAncestors().size() == 2) {
                    return territory;
                }
            } catch (PortalException e) {
                continue;
            }
        }
        return null;
    }

    /**
     * Retourne la liste des URL publiques des images additionnelles
     */
    @Override
    public List<String> getImagesURLs() {
        List<String> URLs = new ArrayList<String>();
        for (String imageIdStr : this.getImageIds().split(",")) {
            Long imageId = GetterUtil.getLong(imageIdStr);
            if (Validator.isNotNull(imageId)) {
                String imageURL = FileEntryHelper.getFileEntryURL(imageId);
                URLs.add(imageURL);
            }
        }
        return URLs;
    }

    /**
     * Retourne une map d'URL et de titre des images additionnelles et des
     * vidéos
     *
     * @throws PortalException
     */
    @Override
    public List<AssetEntry> getRandomContents() throws PortalException {
        List<AssetEntry> contenus = new ArrayList<AssetEntry>();

        for (String imageIdStr : this.getImageIds().split(",")) {
            Long imageId = GetterUtil.getLong(imageIdStr);
            if (Validator.isNotNull(imageId)) {
                AssetEntry imageEntry = AssetEntryLocalServiceUtil.getEntry(DLFileEntry.class.getName(), imageId);
                contenus.add(imageEntry);
            }
        }
        for (String videoIdString : this.getVideosIds().split(",")) {
            Long videoId = GetterUtil.getLong(videoIdString);
            if (Validator.isNotNull(videoId)) {
                Video video = VideoLocalServiceUtil.fetchVideo(videoId);
                AssetEntry videoEntry = video.getAssetEntry();
                contenus.add(videoEntry);
            }
        }
        Collections.shuffle(contenus);
        return contenus;
    }

    /**
     * Retourne l'URL publiques de l'image
     */
    @Override
    public String getImageURL(Long imageId) {
        String imageURL = null;
        if (Validator.isNotNull(imageId)) {
            imageURL = FileEntryHelper.getFileEntryURL(imageId);
        }
        return imageURL;

    }

    /**
     * Retourne le copyright publiques de l'image
     */
    @Override
    public String getImageCopyright(Long imageId, Locale locale) {
        String imageTitle = null;
        if (Validator.isNotNull(imageId)) {
            imageTitle = FileEntryHelper.getImageCopyright(imageId, locale);
        }
        return imageTitle;

    }

    /**
     * Retourne la légende publiques de l'image
     */
    @Override
    public String getImageLegend(Long imageId, Locale locale) {
        String imageTitle = null;
        if (Validator.isNotNull(imageId)) {
            imageTitle = FileEntryHelper.getImageLegend(imageId, locale);
        }
        return imageTitle;

    }

    /**
     * Retourne la liste des URL des documents de ce lieu
     */
    @Override
    public List<String> getDocumentURLs() {
        List<String> URLs = new ArrayList<String>();
        for (String documentIdStr : this.getDocumentsIds().split(",")) {
            Long documentId = GetterUtil.getLong(documentIdStr);
            if (Validator.isNotNull(documentId)) {
                String documentURL = FileEntryHelper.getFileEntryURL(documentId);
                URLs.add(documentURL);
            }
        }
        return URLs;
    }

    /**
     * Retourne une map de titre et d'URL des documents de ce lieu
     */
    @Override
    public Map<String, String> getDocuments() {
        Map<String, String> documents = new HashMap<String, String>();
        for (String documentIdStr : this.getDocumentsIds().split(",")) {
            Long documentId = GetterUtil.getLong(documentIdStr);
            if (Validator.isNotNull(documentId)) {
                String documentURL = FileEntryHelper.getFileEntryURL(documentId);
                DLFileEntry document = FileEntryHelper.getFileEntryByRelativeURL(documentURL);
                String documentTitle = document.getTitle();
                documents.put(documentTitle, documentURL);
            }
        }
        return documents;
    }

    /**
     * Retourne la liste des vidéos de ce lieu
     */

    @Override
    public List<Video> getVideos() {
        List<Video> videos = new ArrayList<Video>();
        for (String videoIdsStr : this.getVideosIds().split(",")) {
            Long videoId = GetterUtil.getLong(videoIdsStr);
            Video aa = VideoLocalServiceUtil.fetchVideo(videoId);
            if (aa != null) {
                videos.add(aa);
            }
        }
        return videos;
    }

    /**
     * Retourne une list d'évènements lié à ce lieu
     */
    @Override
    public List<Event> getEvents() {
        List<Event> events = EventLocalServiceUtil.findByPlaceSIGId(this.getSIGid());
        return events;
    }

    /**
     * Retourne une list d'évènements lié à ce lieu
     */
    @Override
    public List<Event> getPublishedEvents() {
        List<Event> events = EventLocalServiceUtil.findByPlaceSIGId(this.getSIGid());
        return events.stream().filter(e -> e.isApproved()).collect(Collectors.toList());
    }

    /**
     * Retourne une list d'évènements lié à ce lieu
     */
    @Override
    public List<Event> getCurrentAndFuturePublishedEvents() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        List<Event> events = EventLocalServiceUtil.findByPlaceSIGId(this.getSIGid());
        return events.stream().filter(e -> e.isApproved() && e.getStartDateFirstCurrentAndFuturePeriod().compareTo(yesterday) > 0).collect(Collectors.toList());
    }

    /**
     * Retourne true si l'événement est accessible pour au moins un type de
     * handicap
     */
    @Override
    public boolean hasAnyAccessForDisabled() {
        return this.getAccessForBlind() || this.getAccessForDeaf() || this.getAccessForWheelchair()
                || this.getAccessForDeficient() || this.getAccessForElder();
    }

    /**
     * Retourne true si le lieu est ouvert à l'instant passé en paramètre
     */
    @Override
    public Boolean isOpen(LocalDateTime localDateTime) {
        boolean isOpen = false;
        LocalDate date = localDateTime.toLocalDate();
        LocalTime time = localDateTime.toLocalTime();
        List<PlaceSchedule> schedules = this.getPlaceSchedule(date, Locale.FRANCE);
        if (schedules != null && schedules.size() > 0) {
            PlaceSchedule schedule = schedules.get(0);
            if (schedule.isAlwaysOpen()) {
                isOpen = true;
            } else {
                List<Pair<LocalTime, LocalTime>> openingTimes = schedule.getOpeningTimes();
                if (openingTimes != null) {
                    for (Pair<LocalTime, LocalTime> openingTime : openingTimes) {
                        if (openingTime.getFirst().isBefore(time) && openingTime.getSecond().isAfter(time)) {
                            isOpen = true;
                            break;
                        }
                    }
                }
            }
        }
        return isOpen;
    }

    /**
     * Retourne true si le lieu est ouvert à l'instant présent
     */
    @Override
    public Boolean isOpenNow() {
        return isOpen(LocalDateTime.now());
    }

    /**
     * Vérifie si le lieu est fermé un jour donné
     */
    @Override
    public Boolean isClosed(GregorianCalendar jourSemaine) {
        Boolean closed = true;

        // vérifie si cette date n'est pas dans les horaires d'exception
        for (ScheduleException scheduleException : this.getScheduleExceptions()) {
            if (scheduleException.getStartDate() != null && scheduleException.getEndDate() != null
                    && scheduleException.getStartDate().compareTo(jourSemaine.getTime()) <= 0
                    && scheduleException.getEndDate().compareTo(jourSemaine.getTime()) >= 0) {
                if (scheduleException.isClosed())
                    return true;
                else
                    return false;
            }
        }

        // vérifie si cette date n'est pas dans les jours fériés
        if (this.isSubjectToPublicHoliday()) {
            for (PublicHoliday publicHoliday : this.getPublicHolidays()) {
                if (publicHoliday.getDate() != null) {
                    GregorianCalendar publicHolidayYear = new GregorianCalendar();
                    publicHolidayYear.setTime(publicHoliday.getDate());
                    publicHolidayYear.set(Calendar.YEAR, jourSemaine.get(Calendar.YEAR));
                    if (publicHolidayYear.compareTo(jourSemaine) == 0) {
                        return true;
                    }
                }
            }
        }

        // s'il n'y a pas d'exception, on vérifie dans les périodes
        LocalTime heureActuelle = LocalTime.now();
        for (Period period : this.getPeriods()) {
            if (!period.getDefaultPeriod()) {
                if (period.getStartDate() != null && period.getEndDate() != null
                        && period.getStartDate().compareTo(jourSemaine.getTime()) <= 0
                        && period.getEndDate().compareTo(jourSemaine.getTime()) >= 0) {
                    if (period.getAlwaysOpen()) {
                        return false;
                    } else {
                        // on vérifie qu'il n'y a pas de créneau pour ce jour
                        for (Slot slot : period.getSlots()) {
                            if (slot.getDayOfWeek() == (jourSemaine.get(Calendar.DAY_OF_WEEK) == 1 ? 6
                                    : jourSemaine.get(Calendar.DAY_OF_WEEK) - 2)) {
                                String[] heure = slot.getStartHour().split(":");
                                LocalTime startHour = LocalTime.of(Integer.parseInt(heure[0]),
                                        Integer.parseInt(heure[1]), 0, 0);
                                heure = slot.getEndHour().split(":");
                                LocalTime endHour = LocalTime.of(Integer.parseInt(heure[0]), Integer.parseInt(heure[1]),
                                        59, 999);
                                if (heureActuelle.isAfter(startHour) && heureActuelle.isBefore(endHour)) {
                                    return false;
                                } else {
                                    closed = true;
                                }
                            }
                        }
                    }
                }
            } else {
                // on vérifie si le lieu n'est pas ouvert 24h/24
                // 7j/7
                if (period.getAlwaysOpen()) {
                    closed = false;
                } else {
                    for (Slot slot : period.getSlots()) {
                        if (slot.getDayOfWeek() == (jourSemaine.get(Calendar.DAY_OF_WEEK) == 1 ? 6
                                : jourSemaine.get(Calendar.DAY_OF_WEEK) - 2)) {
                            String[] heure = slot.getStartHour().split(":");
                            LocalTime startHour = LocalTime.of(Integer.parseInt(heure[0]), Integer.parseInt(heure[1]),
                                    0, 0);
                            heure = slot.getEndHour().split(":");
                            LocalTime endHour = LocalTime.of(Integer.parseInt(heure[0]), Integer.parseInt(heure[1]), 59,
                                    999);
                            if (heureActuelle.isAfter(startHour) && heureActuelle.isBefore(endHour)) {
                                closed = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return closed;
    }

    /**
     * Retourne true si le lieu est une piscine
     *
     * @return
     */
    @Override
    public boolean isSwimmingPool() {
        return this.getRTType().equals("1");
    }

    /**
     * Retourne true si le lieu est un parking
     *
     * @return
     */
    @Override
    public boolean isParking() {
        return this.getRTType().equals("2");
    }

    /**
     * Retourne true si le lieu est une mairie
     *
     * @return
     */
    @Override
    public boolean isMairie() {
        return this.getRTType().equals("3");
    }

    /**
     * Retourne le temps réel (en gérant automatiquement le fait que ce soit une
     * piscine,une mairie ou un parking)
     *
     * @throws Exception
     */
    @Override
    public OccupationState getRealTime() {
        try {
            return getRealTime(this.getRTType());
        } catch (Exception ex) {
            return OccupationState.NOT_AVAILABLE;
        }
    }

    /**
     * Retourne le temps réel (couleur de fond,valeur)
     *
     * @param type (1 = piscine, 2 = parking, 3 = mairie)
     * @throws Exception
     */
    @Override
    public OccupationState getRealTime(String type) {
        OccupationState state = null;

        GregorianCalendar today = new GregorianCalendar();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.clear(Calendar.MINUTE);
        today.clear(Calendar.SECOND);
        today.clear(Calendar.MILLISECOND);
        if (this.isClosed(today)) {
            state = OccupationState.CLOSED;
            return state;
        }

        if (Validator.isNull(this.getRTExternalId())) {
            state = OccupationState.DISABLED;
            return state;
        }

        long occupation = 0;
        switch (type) {
            case "1":
                // récupération de la période en cours
                Period periodEnCours = null;
                for (Period period : this.getPeriods()) {
                    if (!period.getDefaultPeriod()) {
                        if (period.getStartDate() != null && period.getEndDate() != null
                                && period.getStartDate().compareTo(today.getTime()) <= 0
                                && period.getEndDate().compareTo(today.getTime()) >= 0) {
                            periodEnCours = period;
                            break;
                        }
                    } else {
                        periodEnCours = period;
                    }
                }
                if (Validator.isNull(periodEnCours)) {
                    state = OccupationState.NOT_AVAILABLE;
                    return state;
                }
                if (Validator.isNull(periodEnCours.getRTMaxThreshold())) {
                    state = OccupationState.NOT_AVAILABLE;
                    return state;
                }
                occupation = this.getRTOccupation();
                if (occupation == -1) {
                    state = OccupationState.NOT_AVAILABLE;
                    return state;
                }
                if (occupation > periodEnCours.getRTRedThreshold()) {
                    state = OccupationState.BLACK;
                } else if (occupation > periodEnCours.getRTOrangeThreshold()) {
                    state = OccupationState.RED;
                } else if (occupation > periodEnCours.getRTGreenThreshold()) {
                    state = OccupationState.ORANGE;
                } else
                    state = OccupationState.GREEN;
                state.setOccupation("" + occupation);
                break;
            case "2":
                state = OccupationState.NOT_AVAILABLE;
                switch (this.getRTStatus()) {
                    case "status_1":
                        state = OccupationState.OPEN;
                        state.setAvailable("" + this.getRTAvailable());
                        state.setCapacity("" + this.getRTCapacity());
                        break;
                    case "status_2":
                        state = OccupationState.FULL;
                        break;
                    case "status_3":
                        state = OccupationState.NOT_AVAILABLE;
                        break;
                    case "status_4":
                        state = OccupationState.CLOSED;
                        break;
                }
                break;
            case "3":
                // récupération de la période en cours
                periodEnCours = null;
                for (Period period : this.getPeriods()) {
                    if (!period.getDefaultPeriod()) {
                        if (period.getStartDate() != null && period.getEndDate() != null
                                && period.getStartDate().compareTo(today.getTime()) <= 0
                                && period.getEndDate().compareTo(today.getTime()) >= 0) {
                            periodEnCours = period;
                            break;
                        }
                    } else {
                        periodEnCours = period;
                    }
                }
                if (Validator.isNull(periodEnCours)) {
                    state = OccupationState.NOT_AVAILABLE;
                    return state;
                }
                // TODO est-ce que l'on garde cette vérification ? (de même que pour
                // les piscines ?)
                // car si il n'y a pas de capacié max de renseigné il y a quand même
                // une fréquentation
                /*
                 * if (Validator.isNull(periodEnCours.getRTMaxThreshold())) { state
                 * = OccupationState.NOT_AVAILABLE; return state; }
                 */
                occupation = this.getRTOccupation();
                if (occupation == -1) {
                    state = OccupationState.NOT_AVAILABLE;
                    return state;
                }
                if (occupation > periodEnCours.getRTRedThreshold()) {
                    state = OccupationState.BLACK;
                } else if (occupation > periodEnCours.getRTOrangeThreshold()) {
                    state = OccupationState.RED;
                } else if (occupation > periodEnCours.getRTGreenThreshold()) {
                    state = OccupationState.ORANGE;
                } else
                    state = OccupationState.GREEN;
                String newOccupation = occupation + " min";
                if (occupation > 60) {
                    newOccupation = (occupation / 60) + "h";
                    long min = occupation % 60;
                    newOccupation += (min < 10 ? "0" + min : min);
                }
                state.setOccupation(newOccupation);
                break;
        }
        return state;

    }

    /**
     * Retourne une map contennant les horaires de chaque jour des 7 jours
     * suivants "startDate" (inclus)
     */
    @Override
    public Map<String, List<PlaceSchedule>> getFollowingWeekSchedules(Date startDate, Locale locale) {
        Map<String, List<PlaceSchedule>> schedules = new LinkedHashMap<String, List<PlaceSchedule>>();
        LocalDate localDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale)
                .withZone(ZoneId.systemDefault());
        for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
            List<PlaceSchedule> liste = getPlaceSchedule(localDate, locale);
            String dateString = localDate.atStartOfDay().format(dtf);
            dateString = localDate.format(DateTimeFormatter.ofPattern("EEEE", locale)) + " " + dateString;
            dateString = StringUtil.upperCaseFirstLetter(dateString);
            schedules.put(dateString, liste);
            localDate = localDate.plusDays(1);
        }
        return schedules;
    }

    /**
     * Retourne une map contennant le jour et une liste de PlaceSchedule de la
     * semaine en cours
     */
    @Override
    public Map<String, List<PlaceSchedule>> getHoraire(Date dateJour, Locale locale) {
        Map<String, List<PlaceSchedule>> listHoraires = new LinkedHashMap<String, List<PlaceSchedule>>();

        // réupère le jour voulu de la semaine
        GregorianCalendar jourSemaine = new GregorianCalendar();
        jourSemaine.setTime(dateJour);
        jourSemaine.set(Calendar.HOUR_OF_DAY, 0);
        jourSemaine.clear(Calendar.MINUTE);
        jourSemaine.clear(Calendar.SECOND);
        jourSemaine.clear(Calendar.MILLISECOND);
        int delta = -jourSemaine.get(GregorianCalendar.DAY_OF_WEEK) + 2;
        jourSemaine.add(Calendar.DAY_OF_MONTH, delta);
        for (int jour = 0; jour < 7; jour++) {
            List<PlaceSchedule> liste = getPlaceSchedule(jourSemaine, locale);
            DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
            listHoraires.put(df.format(jourSemaine.getTime()), liste);
            jourSemaine.add(Calendar.DAY_OF_MONTH, 1);
        }
        return listHoraires;
    }

    /**
     * Retourne le PlaceSchedule de la prochaine ouverture (pour X jour)
     */
    @Override
    public PlaceSchedule getNextScheduleOpening(GregorianCalendar today, int nbDays, Locale locale) {
        PlaceSchedule placeSchedule = null;
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(today.getTime());

        boolean find = false;
        for (int number = 0; number <= nbDays; number++) {
            List<PlaceSchedule> list = getPlaceSchedule(date, locale);
            if (!list.isEmpty()) {
                placeSchedule = list.get(0);
                placeSchedule.setStartDate(date.getTime());
                if (!placeSchedule.isClosed()) {
                    // Si le lieu est ouvert, on vérifie que l'heure d'ouverture
                    // n'est pas passée
                    LocalTime time = LocalTime.now();
                    List<Pair<LocalTime, LocalTime>> openingTimes = new ArrayList<Pair<LocalTime, LocalTime>>();
                    for (Pair<LocalTime, LocalTime> openingTime : placeSchedule.getOpeningTimes()) {
                        if (today.before(date) || time.isBefore(openingTime.getSecond())) {
                            openingTimes.add(openingTime);
                            placeSchedule.setOpeningTimes(openingTimes);
                            number = nbDays;
                            find = true;
                            break;
                        }
                    }
                }
            }
            date.add(GregorianCalendar.DATE, 1);
            if (!find) {
                placeSchedule = null;
            }
        }
        return placeSchedule;
    }

    /**
     * Retourne le PlaceSchedule de la prochaine ouverture (sous quinzaine)
     */
    @Override
    public PlaceSchedule getNextScheduleOpening(GregorianCalendar today, Locale locale) {
        return getNextScheduleOpening(today, 15, locale);
    }

    /**
     * Retourne les horaires d'ouverture du jour passé en paramètre jusqu'à
     * "date" + "daysCount"
     */
    @Override
    public Map<String, List<PlaceSchedule>> getPlaceSchedule(Date date, int daysCount, Locale locale) {

        Map<String, List<PlaceSchedule>> listHoraires = new LinkedHashMap<String, List<PlaceSchedule>>();

        // réupère le jour voulu de la semaine
        GregorianCalendar jourSemaine = new GregorianCalendar();
        jourSemaine.setTime(date);
        jourSemaine.set(Calendar.HOUR_OF_DAY, 0);
        jourSemaine.clear(Calendar.MINUTE);
        jourSemaine.clear(Calendar.SECOND);
        jourSemaine.clear(Calendar.MILLISECOND);
        for (int jour = 0; jour < daysCount; jour++) {
            List<PlaceSchedule> liste = getPlaceSchedule(jourSemaine, locale);
            DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
            listHoraires.put(df.format(jourSemaine.getTime()), liste);
            jourSemaine.add(Calendar.DAY_OF_MONTH, 1);
        }
        return listHoraires;
    }

    /**
     * Retourne les horaires d'ouverture du jour
     */
    private List<PlaceSchedule> getPlaceSchedule(LocalDate localDate, Locale locale) {
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return this.getPlaceSchedule(gregorianCalendar, locale);
    }

    /**
     * Retourne les horaires d'ouverture du jour
     */
    @Override
    public List<PlaceSchedule> getPlaceSchedule(GregorianCalendar jourSemaine, Locale locale) {
        List<PlaceSchedule> listHoraires = new ArrayList<PlaceSchedule>();

        // vérifie si cette date n'est pas dans les horaires d'exception ni dans
        // les jours fériés
        listHoraires = getPlaceScheduleException(jourSemaine, false, locale);

        // s'il n'y a pas d'exception, on récupère les horaires de la
        // période concernée
        if (listHoraires.isEmpty()) {
            Period defaultPeriod = null;
            for (Period period : this.getPeriods()) {
                // Soit la période en cours
                if (period.getStartDate() != null && period.getEndDate() != null
                        && period.getStartDate().compareTo(jourSemaine.getTime()) <= 0
                        && period.getEndDate().compareTo(jourSemaine.getTime()) >= 0) {
                    int dayOfWeek = (jourSemaine.get(Calendar.DAY_OF_WEEK) == 1 ? 6
                            : jourSemaine.get(Calendar.DAY_OF_WEEK) - 2);
                    List<Slot> slots = period.getSlots().stream().filter(s -> s.getDayOfWeek() == dayOfWeek)
                            .collect(Collectors.toList());
                    listHoraires.add(PlaceSchedule.fromSlots(slots, period.getAlwaysOpen()));
                    return listHoraires;
                }
                // On met au cas où la période par défaut de côté
                if (period.getDefaultPeriod()) {
                    defaultPeriod = period;
                }
            }
            // S'il n'y a aucune période en cours, la période par défaut
            if (defaultPeriod != null) {
                int dayOfWeek = (jourSemaine.get(Calendar.DAY_OF_WEEK) == 1 ? 6
                        : jourSemaine.get(Calendar.DAY_OF_WEEK) - 2);
                List<Slot> slots = defaultPeriod.getSlots().stream().filter(s -> s.getDayOfWeek() == dayOfWeek)
                        .collect(Collectors.toList());
                listHoraires.add(PlaceSchedule.fromSlots(slots, defaultPeriod.getAlwaysOpen()));
            }
        }
        return listHoraires;
    }

    /**
     * Retourne les horaires des exceptions d'ouverture à partir du lundi de la
     * semaine en cours
     *
     * @param surPériode (false = horaires d'une journée uniquement , true = horaires
     *                   sur 2 mois à partir du jour + le début de la semaine)
     */
    @Override
    public List<PlaceSchedule> getPlaceScheduleException(GregorianCalendar dateChoisie, Boolean surPeriode,
                                                         Locale locale) {
        List<PlaceSchedule> listPlaceSchedules = new ArrayList<PlaceSchedule>();
        GregorianCalendar premierJour = new GregorianCalendar();
        premierJour.setTime(dateChoisie.getTime());
        premierJour.set(Calendar.HOUR_OF_DAY, 0);
        premierJour.set(Calendar.MINUTE, 0);
        premierJour.set(Calendar.SECOND, 0);
        premierJour.set(Calendar.MILLISECOND, 0);
        GregorianCalendar dernierJour = new GregorianCalendar();
        dernierJour.setTime(premierJour.getTime());
        dernierJour.add(Calendar.DAY_OF_YEAR, 1);
        dernierJour.add(Calendar.MINUTE, -1);
        if (surPeriode) {
            premierJour.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            dernierJour.add(Calendar.MONTH, 2);
        }

        // vérifie s'il y a des horaires d'exception
        for (ScheduleException scheduleException : this.getScheduleExceptions()) {
            if (scheduleException.getStartDate() != null && scheduleException.getEndDate() != null
                    && scheduleException.getStartDate().compareTo(dernierJour.getTime()) <= 0
                    && scheduleException.getEndDate().compareTo(premierJour.getTime()) >= 0) {

                if (scheduleException.isClosed()) {
                    PlaceSchedule placeSchedule = new PlaceSchedule(scheduleException.getExceptionId(),
                            scheduleException.getStartDate(), scheduleException.getEndDate(),
                            scheduleException.getComment(locale), locale);
                    placeSchedule.setException(true);
                    placeSchedule.setClosed(true);
                    listPlaceSchedules.add(placeSchedule);
                } else {
                    PlaceSchedule placeSchedule = new PlaceSchedule(scheduleException.getExceptionId(),
                            scheduleException.getStartDate(), scheduleException.getEndDate(),
                            scheduleException.getComment(locale), locale);
                    placeSchedule.setException(true);
                    placeSchedule.setOpeningTimes(scheduleException.getOpeningLocalTimes());
                    placeSchedule.setComments(scheduleException.getComments());
                    listPlaceSchedules.add(placeSchedule);
                }
            }
        }

        if (!surPeriode && !listPlaceSchedules.isEmpty()) {
            return listPlaceSchedules;
        }

        // vérifie s'il y a des jours fériés
        if (this.isSubjectToPublicHoliday()) {
            for (PublicHoliday publicHoliday : this.getPublicHolidays()) {
                if (publicHoliday.getDate() != null) {
                    GregorianCalendar publicHolidayYear = new GregorianCalendar();
                    publicHolidayYear.setTime(publicHoliday.getDate());
                    if (publicHoliday.isRecurrent()) {
                        publicHolidayYear.set(Calendar.YEAR, premierJour.get(Calendar.YEAR));
                    }
                    if (publicHolidayYear.compareTo(premierJour) >= 0 && publicHolidayYear.compareTo(dernierJour) <= 0) {
                        PlaceSchedule placeSchedule = new PlaceSchedule(publicHoliday.getPublicHolidayId(),
                                publicHolidayYear.getTime(), publicHolidayYear.getTime(), publicHoliday.getName(locale),
                                locale);
                        placeSchedule.setPublicHoliday(true);
                        placeSchedule.setClosed(true);
                        // commenté car il supprimait tous les horaires
                        // exceptionnels et n'enregistrait qu'un jour férié
                        // listPlaceSchedules.clear();
                        //On vérifie que le jour férié n'est pas déjà dans les schedules exception
                        if (!listPlaceSchedules.stream()
                                .anyMatch(s -> (s.getStartDate().compareTo(placeSchedule.getStartDate()) <= 0 && s.getEndDate().compareTo(placeSchedule.getEndDate()) >= 0))) {
                            listPlaceSchedules.add(placeSchedule);
                        }
                        // break;
                    }
                }
            }
        }
        listPlaceSchedules = listPlaceSchedules.stream()
                .sorted((s1, s2) -> s1.getStartDate().compareTo(s2.getStartDate())).collect(Collectors.toList());
        return listPlaceSchedules;
    }

    /**
     * Retourne les PlaceSchedule des exceptions d'ouverture à partir du lundi
     * de la semaine en cours, jusqu'à dans 2 mois (pour freemarker)
     */
    @Override
    public List<PlaceSchedule> getPlaceScheduleExceptionFreeMarker(Date dateDeb, Boolean surPeriode, Locale locale) {
        GregorianCalendar premierJour = new GregorianCalendar();
        premierJour.setTime(dateDeb);
        List<PlaceSchedule> exceptions = getPlaceScheduleException(premierJour, surPeriode, locale);

        exceptions = exceptions.stream().filter(e -> e.getEndDate().compareTo(dateDeb) >= 0)
                .collect(Collectors.toList());

        return exceptions;
    }

    /**
     * Retourne la version JSON du lieu
     */
    @Override
    public JSONObject toJSON() {
        JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();

        jsonPlace.put("idSurfs", this.getSIGid());
        jsonPlace.put("name", JSONHelper.getJSONFromI18nMap(this.getAliasMap()));
        jsonPlace.put("address", this.getAddressStreet() + " " + this.getAddressZipCode() + " "
                + this.getCity(Locale.getDefault()) + " " + this.getAddressCountry());
        if (Validator.isNotNull(this.getAddressDistribution())) {
            jsonPlace.put("distribution", this.getAddressDistribution());
        }
        jsonPlace.put("street", this.getAddressStreet());
        if (Validator.isNotNull(this.getAddressComplement())) {
            jsonPlace.put("complement", this.getAddressComplement());
        }

        // Code postal
        jsonPlace.put("zipCode", this.getAddressZipCode());

        // Quartier
        AssetCategory districtCategory = this.getDistrictCategory();
        if (districtCategory != null) {
            String SIGId = AssetVocabularyHelper.getCategoryProperty(districtCategory.getCategoryId(), "SIG");
            jsonPlace.put("districtCode", SIGId);
        }

        // Ville
        AssetCategory cityCategory = this.getCityCategory();
        if (cityCategory != null) {
            String SIGId = AssetVocabularyHelper.getCategoryProperty(cityCategory.getCategoryId(), "SIG");
            jsonPlace.put("cityCode", SIGId);
        }

        jsonPlace.put("city", this.getCity(Locale.getDefault()));

        // Pays
        jsonPlace.put("country", this.getAddressCountry());

        // Coordonnées
        jsonPlace.put("RGF93Y", this.getRGF93Y());
        jsonPlace.put("RGF93X", this.getRGF93X());
        jsonPlace.put("mercatorY", this.getMercatorY());
        jsonPlace.put("mercatorX", this.getMercatorX());

        // Types
        JSONArray jsonTypes = JSONFactoryUtil.createJSONArray();
        for (AssetCategory assetCategory : this.getTypes()) {
            jsonTypes.put(AssetVocabularyHelper.getCategoryProperty(assetCategory.getCategoryId(), "SIG"));
        }
        if (jsonTypes.length() > 0) {
            jsonPlace.put("types", jsonTypes);
        }

        // Description
        jsonPlace.put("description", JSONHelper.getJSONFromI18nMap(this.getPresentationMap()));

        // Services et activités
        if (Validator.isNotNull(this.getServiceAndActivities())) {
            jsonPlace.put("serviceAndActivities", JSONHelper.getJSONFromI18nMap(this.getServiceAndActivitiesMap()));
        }

        // Caractéristiques
        if (Validator.isNotNull(this.getCharacteristics())) {
            jsonPlace.put("characteristics", JSONHelper.getJSONFromI18nMap(this.getCharacteristicsMap()));
        }

        // Tarifs
        if (Validator.isNotNull(this.getPrice()) && Validator.isNotNull(this.getPrice().getPrice())) {
            jsonPlace.put("price", JSONHelper.getJSONFromI18nMap(this.getPrice().getPriceMap()));
        }

        // Mail
        if (Validator.isNotNull(this.getMail())) {
            jsonPlace.put("mail", this.getMail());
        }
        if (Validator.isNotNull(this.getPhone())) {
            jsonPlace.put("phone", this.getPhone());
        }

        // Facebook
        if (Validator.isNotNull(this.getFacebookLabel())) {
            jsonPlace.put("facebookName", JSONHelper.getJSONFromI18nMap(this.getFacebookLabelMap()));
            jsonPlace.put("facebookURL", JSONHelper.getJSONFromI18nMap(this.getFacebookURLMap()));
        }

        // Site
        if (Validator.isNotNull(this.getSiteLabel())) {
            jsonPlace.put("websiteName", JSONHelper.getJSONFromI18nMap(this.getSiteLabelMap()));
            jsonPlace.put("websiteURL", JSONHelper.getJSONFromI18nMap(this.getSiteURLMap()));
        }

        // Accès
        if (Validator.isNotNull(this.getAccess())) {
            jsonPlace.put("access", JSONHelper.getJSONFromI18nMap(this.getAccessMap()));
        }
        if (Validator.isNotNull(this.getAccessForDisabled())) {
            jsonPlace.put("accessForDisabled", JSONHelper.getJSONFromI18nMap(this.getAccessForDisabledMap()));
        }
        jsonPlace.put("accessForBlind", this.getAccessForBlind());
        jsonPlace.put("accessForWheelchair", this.getAccessForWheelchair());
        jsonPlace.put("accessForDeaf", this.getAccessForDeaf());
        jsonPlace.put("accessForElder", this.getAccessForElder());
        jsonPlace.put("accessForDeficient", this.getAccessForDeficient());

        // Horaires et périodes
        JSONArray periodsJSON = JSONFactoryUtil.createJSONArray();
        for (Period period : this.getPeriods()) {
            periodsJSON.put(period.toJSON());
        }
        if (periodsJSON.length() > 0) {
            jsonPlace.put("periods", periodsJSON);
        }

        JSONArray scheduleExceptionsJSON = JSONFactoryUtil.createJSONArray();
        for (ScheduleException scheduleException : this.getScheduleExceptions()) {
            scheduleExceptionsJSON.put(scheduleException.toJSON());
        }
        if (scheduleExceptionsJSON.length() > 0) {
            jsonPlace.put("exceptions", scheduleExceptionsJSON);
        }

        if (Validator.isNotNull(this.getExceptionalSchedule())) {
            jsonPlace.put("exceptionalSchedule", JSONHelper.getJSONFromI18nMap(this.getExceptionalScheduleMap()));
        }

        // Information complémentaire
        if (Validator.isNotNull(this.getAdditionalInformation())) {
            jsonPlace.put("additionalInformation", JSONHelper.getJSONFromI18nMap(this.getAdditionalInformationMap()));
        }

        // URL du lieu
        jsonPlace.put("friendlyURL", StrasbourgPropsUtil.getPlaceDetailURL() + "/-/entity/id/" + this.getPlaceId());

        // Image principale
        if (Validator.isNotNull(this.getImageURL())) {
            String imageURL = this.getImageURL();
            imageURL = StrasbourgPropsUtil.getURL() + imageURL;
            jsonPlace.put("imageURL", imageURL);
            jsonPlace.put("imageCopyright", this.getImageCopyright(Locale.getDefault()));
        }

        // Images secondaires
        JSONArray imagesJSON = JSONFactoryUtil.createJSONArray();
        for (String imageIdString : this.getImageIds().split(",")) {
            JSONObject imageJSON = JSONFactoryUtil.createJSONObject();
            Long imageId = GetterUtil.getLong(imageIdString);
            if (imageId > 0) {
                String imageURL = FileEntryHelper.getFileEntryURL(imageId);
                imageURL = StrasbourgPropsUtil.getURL() + imageURL;
                String imageCopyright = FileEntryHelper.getImageCopyright(imageId, LocaleUtil.FRENCH);
                imageJSON.put("imageURL", imageURL);
                imageJSON.put("imageCopyright", imageCopyright);
                imagesJSON.put(imageJSON);
            }
        }
        if (imagesJSON.length() > 0) {
            jsonPlace.put("images", imagesJSON);
        }

        // Vidéos
        JSONArray videosJSON = JSONFactoryUtil.createJSONArray();
        for (String videoIdString : this.getVideosIds().split(",")) {
            Long videoId = GetterUtil.getLong(videoIdString);
            Video video = VideoLocalServiceUtil.fetchVideo(videoId);
            if (Validator.isNotNull(video)) {
                videosJSON.put(video.getSource(Locale.FRANCE));
            }
        }
        if (videosJSON.length() > 0) {
            jsonPlace.put("videos", videosJSON);
        }

        // Documents
        JSONArray documentsJSON = JSONFactoryUtil.createJSONArray();
        for (String documentURL : this.getDocumentURLs()) {
            documentURL = StrasbourgPropsUtil.getURL() + documentURL;
            documentsJSON.put(documentURL);
        }
        if (documentsJSON.length() > 0) {
            jsonPlace.put("documents", documentsJSON);
        }

        return jsonPlace;
    }

    /**
     * Reprise de l'horriblissime webservice des lieux de LR6
     */
    @Override
    public JSONObject toLegacyJSON() {
        JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();

        JSONObject accessForDisabled = JSONFactoryUtil.createJSONObject();
        accessForDisabled.put("javaClass", "java.util.HashMap");
        JSONObject accessForDisabledMap = JSONFactoryUtil.createJSONObject();
        accessForDisabledMap.put("Personnes agees", this.getAccessForElder());
        accessForDisabledMap.put("Deficients auditif", this.getAccessForDeaf());
        accessForDisabledMap.put("Deficients visuel", this.getAccessForBlind());
        accessForDisabledMap.put("Deficients cognitif", this.getAccessForDeficient());
        accessForDisabledMap.put("Handicap moteur", this.getAccessForWheelchair());
        accessForDisabled.put("map", accessForDisabledMap);
        jsonPlace.put("accessHandicap", accessForDisabled);

        jsonPlace.put("urlSiteInternet", this.getSiteURL(Locale.FRANCE));
        jsonPlace.put("tarifs", this.getPrice() != null ? this.getPrice().getPrice(Locale.FRANCE) : "");
        jsonPlace.put("adresse", this.getAddressStreet() + " " + this.getAddressComplement() + "<br />"
                + this.getAddressZipCode() + " " + this.getCity(Locale.FRANCE) + "<br />" + this.getAddressCountry());
        jsonPlace.put("rue", this.getAddressStreet());
        jsonPlace.put("illustration", this.getImageURL());
        jsonPlace.put("urlFacebook", this.getFacebookURL(Locale.FRANCE));
        jsonPlace.put("ville", this.getCity(Locale.FRANCE));

        JSONObject coordinates = JSONFactoryUtil.createJSONObject();
        coordinates.put("javaClass", "java.util.HashMap");
        JSONObject coordinatesMap = JSONFactoryUtil.createJSONObject();
        coordinatesMap.put("X", this.getRGF93X());
        coordinatesMap.put("Y", this.getRGF93Y());
        coordinates.put("map", coordinatesMap);
        jsonPlace.put("coordonneesRGF93", coordinates);

        jsonPlace.put("nomSiteInternet", this.getSiteLabel(Locale.FRANCE));
        jsonPlace.put("urlVideo", this.getVideos().size() > 0 ? this.getVideos().get(0).getPlayer(Locale.FRANCE) : "");

        JSONObject schedule = JSONFactoryUtil.createJSONObject();
        schedule.put("javaClass", "java.util.TreeMap");
        JSONObject scheduleMap = JSONFactoryUtil.createJSONObject();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            String dateString = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            List<PlaceSchedule> placeSchedules = this.getPlaceSchedule(date, Locale.FRANCE);
            String scheduleString = "";
            for (PlaceSchedule placeSchedule : placeSchedules) {
                if (placeSchedule.isClosed()) {
                    scheduleString = "Ferme";
                } else if (placeSchedule.isAlwaysOpen()) {
                    scheduleString = "24h/24";
                } else if (placeSchedule.getOpeningTimes() != null) {
                    for (Pair<LocalTime, LocalTime> openingTime : placeSchedule.getOpeningTimes()) {
                        if (scheduleString.length() > 0) {
                            scheduleString += ",";
                        }
                        scheduleString += openingTime.getFirst() + "-" + openingTime.getSecond();
                    }
                }
            }
            scheduleMap.put(dateString, scheduleString);
            date = date.plusDays(1);
        }
        schedule.put("map", scheduleMap);
        jsonPlace.put("horaires", schedule);

        jsonPlace.put("descriptionAccesHandicap", this.getAccessForDisabled(Locale.FRANCE));

        JSONObject categories = JSONFactoryUtil.createJSONObject();
        categories.put("javaClass", "java.util.ArrayList");
        JSONArray categoriesArray = JSONFactoryUtil.createJSONArray();
        for (AssetCategory type : this.getTypes()) {
            String sigId = AssetVocabularyHelper.getCategoryProperty(type.getCategoryId(), "SIG");
            if (Validator.isNotNull(sigId)) {
                categoriesArray.put(sigId);
            }
        }
        categories.put("list", categoriesArray);
        jsonPlace.put("categorie", categories);

        jsonPlace.put("horaireExceptionnel", this.getExceptionalSchedule(Locale.FRANCE));
        jsonPlace.put("nomFacebook", this.getFacebookLabel(Locale.FRANCE));

        // "urlGalerie" n'existe plus
        // "nomPeriode" ???
        // "urlLienHoraires" pas possible car par période
        // "nomLienHoraires" pas possible : par période
        // "nomGalerie" : n'existe plus
        // ouvertures exceptionnelles ???
        // Fermetures exceptionnelles
        // TOUT VIDE DU COUP
        jsonPlace.put("urlGalerie", "");
        jsonPlace.put("nomPeriode", "");
        jsonPlace.put("urlLienHoraires", "");
        jsonPlace.put("nomLienHoraires", "");
        jsonPlace.put("nomGalerie", "");
        jsonPlace.put("ouvertures exceptionnelles", "");
        jsonPlace.put("Fermetures exceptionnelles", "");

        jsonPlace.put("services", this.getServiceAndActivities(Locale.FRANCE));
        jsonPlace.put("document1", this.getDocumentURLs().size() > 0 ? this.getDocumentURLs().get(0) : "");
        jsonPlace.put("document2", this.getDocumentURLs().size() > 1 ? this.getDocumentURLs().get(1) : "");
        jsonPlace.put("modeAcces", this.getAccess(Locale.FRANCE));
        jsonPlace.put("caracteristiques", this.getCharacteristics(Locale.FRANCE));
        jsonPlace.put("idSurfs", this.getSIGid());
        jsonPlace.put("nomLieu", this.getAlias(Locale.FRANCE));
        jsonPlace.put("friendlyUrl", "https://www.strasbourg.eu/lieu/-/entity/sig/" + this.getSIGid());
        jsonPlace.put("infosComplementaires", this.getAdditionalInformation(Locale.FRANCE));

        JSONObject territory = JSONFactoryUtil.createJSONObject();
        territory.put("javaClass", "java.util.HashMap");
        JSONObject territoryMap = JSONFactoryUtil.createJSONObject();
        AssetCategory district = this.getDistrictCategory();
        String districtCode = AssetVocabularyHelper.getCategoryProperty(district.getCategoryId(), "SIG");
        if (Validator.isNotNull(districtCode)) {
            territoryMap.put("Quartier", districtCode);
        }
        AssetCategory city = this.getCityCategory();
        territoryMap.put("Commune",
                city != null ? AssetVocabularyHelper.getCategoryProperty(city.getCategoryId(), "SIG") : "");
        territory.put("map", territoryMap);
        jsonPlace.put("territoire", territory);

        jsonPlace.put("presentation", this.getPresentation(Locale.FRANCE));
        jsonPlace.put("email", this.getMail());
        jsonPlace.put("mentionDistribution", this.getAddressDistribution());

        JSONObject mercator = JSONFactoryUtil.createJSONObject();
        mercator.put("javaClass", "java.util.HashMap");
        JSONObject mercatorMap = JSONFactoryUtil.createJSONObject();
        mercatorMap.put("X", this.getMercatorX());
        mercatorMap.put("Y", this.getMercatorY());
        mercator.put("map", mercatorMap);
        jsonPlace.put("coordonneesMercator", mercator);

        jsonPlace.put("javaClass", "com.cus.surfs.service.cusplaceasset.batch.CusPlaceAssetWithSchedule");
        jsonPlace.put("pays", this.getAddressCountry());
        jsonPlace.put("nomVideo", this.getVideos().size() > 0 ? this.getVideos().get(0).getTitle(Locale.FRANCE) : "");
        jsonPlace.put("complementAdresse", this.getAddressComplement());
        jsonPlace.put("telephone", this.getPhone());
        jsonPlace.put("codePostal", this.getAddressZipCode());

        return jsonPlace;
    }
}