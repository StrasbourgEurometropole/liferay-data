
package eu.strasbourg.service.place.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
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
import eu.strasbourg.utils.UriHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.models.Pair;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
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
     * Retourne les périodes qui ne sont pas par défaut (uniquement les périodes en cours ou futures)
     */
    @Override
    public List<Period> getNonDefaultPeriods() {
        return this.getPeriods().stream().filter(p -> !p.getDefaultPeriod() && !p.getEndDate().before(new Date())).collect(Collectors.toList());
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
     * Retourne la liste des URL publiques des images additionnelles avec la version et le timastamp
     */
    @Override
    public List<String> getImageURLsWithTimeStamp () {
        List<String> URLs = new ArrayList<String>();
        for (String imageIdStr : this.getImageIds().split(",")) {
            Long imageId = GetterUtil.getLong(imageIdStr);
            if (Validator.isNotNull(imageId)) {
                String imageURL = FileEntryHelper.getFileEntryURLWithTimeStamp(imageId);
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
    /**
    @Override
    public List<Event> getEvents() {
        List<Event> events = EventLocalServiceUtil.findByPlaceSIGId(this.getSIGid());
        return events;
    }
    */

    /**
     * Retourne une list d'évènements lié à ce lieu
     */
    /**
    @Override
    public List<Event> getPublishedEvents() {
        List<Event> events = EventLocalServiceUtil.findByPlaceSIGId(this.getSIGid());
        return events.stream().filter(e -> e.isApproved()).collect(Collectors.toList());
    }
    */

    /**
     * Retourne une list d'évènements lié à ce lieu
     */
    /**
    @Override
    public List<Event> getCurrentAndFuturePublishedEvents() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        List<Event> events = EventLocalServiceUtil.findByPlaceSIGId(this.getSIGid());
        return events.stream().filter(e -> e.isApproved() && e.getStartDateFirstCurrentAndFuturePeriod().compareTo(yesterday) > 0).collect(Collectors.toList());
    }
    */

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
                else {
                    return false;
                }
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
                                return false;
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
                            closed = false;
                            break;
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
     * Retourne true si le lieu est une patinoire
     *
     * @return
     */
    @Override
    public boolean isIceRink() {
        return this.getRTType().equals("4");
    }

    /**
     * Retourne true si le lieu est une station vélhop
     *
     * @return
     */
    @Override
    public boolean isVelhopStation() {
        return this.getRTType().equals("5");
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
     * @param type (1 = piscine, 2 = parking, 3 = mairie, 4 = patinoire, 5 =Vélop)
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

        if (!this.isOpenNow()) {
            state = OccupationState.CLOSED;
            return state;
        }

        if (Validator.isNull(this.getRTExternalId())) {
            state = OccupationState.DISABLED;
            return state;
        }

        // Vérifie si ce la fait plus de 10 minutes que l'on a pas reçu de temps réel
        // Affichage "Indisponible" si c'est le cas
        try {
            Instant instantDebut = this.getRTLastUpdate().toInstant();
            Instant instantFin = new Date().toInstant();
            long minutesBetween = ChronoUnit.MINUTES.between(instantDebut, instantFin);
            if (minutesBetween >= 10) {
                state = OccupationState.NOT_AVAILABLE;
                return state;
            }
        } catch (Exception e) {
            state = OccupationState.NOT_AVAILABLE;
            return state;
        }

        long occupation = 0;
        switch (type) {
            case "1":
            case "4":
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
                state.setOccupationLabel("" + occupation);
                state.setOccupation("" + occupation);
                state.setCapacity("" + periodEnCours.getRTMaxThreshold());
                break;
            case "2":
                state = OccupationState.NOT_AVAILABLE;
                switch (this.getRTStatus()) {
                    case "0":
                        state = OccupationState.NOT_AVAILABLE;
                        break;
                    case "1":
                        state = OccupationState.OPEN;
                        state.setAvailable("" + this.getRTAvailable());
                        state.setCapacity("" + this.getRTCapacity());
                        break;
                    case "2":
                        state = OccupationState.CLOSED;
                        break;
                    case "3":
                        state = OccupationState.FULL;
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
                state.setOccupationLabel(newOccupation);
                state.setOccupation("" + occupation);
                break;
            case "5":
                occupation = this.getRTOccupation();
                state = OccupationState.NOT_AVAILABLE;
                if ( occupation!=-1 ) {
                    state = OccupationState.OPEN;
                    state.setAvailable("" + this.getRTAvailable());
                    state.setCapacity("" + this.getRTCapacity());
                }
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
            PlaceSchedule regularSchedules = getRegularPlaceSchedule(jourSemaine, locale);
            if(Validator.isNotNull(regularSchedules))
                listHoraires.add(regularSchedules);
        }
        return listHoraires;
    }

    /**
     * Retourne les horaires habituels d'ouverture du jour
     */
    @Override
    public PlaceSchedule getRegularPlaceSchedule(GregorianCalendar jourSemaine, Locale locale) {
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
                return PlaceSchedule.fromSlots(slots, period.getAlwaysOpen());
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
            return PlaceSchedule.fromSlots(slots, defaultPeriod.getAlwaysOpen());
        }

        return null;
    }

    /**
     * Retourne les horaires des exceptions d'ouverture à partir du lundi de la
     * semaine en cours
     *
     * @param surPeriode (false = horaires d'une journée uniquement , true = horaires
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

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateDeb);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date newDateDeb = cal.getTime();
        exceptions = exceptions.stream().filter(e -> e.getEndDate().compareTo(newDateDeb) >= 0)
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
        jsonPlace.put("normalizedAlias", UriHelper.normalizeToFriendlyUrl(this.getAlias(Locale.FRANCE)));
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

        // SIGId des Types
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

        // ExternalID des équipements
        JSONArray jsonEquipments = JSONFactoryUtil.createJSONArray();
        for (AssetCategory assetCategory : AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.EQUIPMENT)) {
            jsonEquipments.put(AssetVocabularyHelper.getCategoryProperty(assetCategory.getCategoryId(), "externalId"));
        }
        if (jsonEquipments.length() > 0) {
            jsonPlace.put("equipment", jsonEquipments);
        }

        // Caractéristiques
        if (Validator.isNotNull(this.getCharacteristics())) {
            jsonPlace.put("characteristics", JSONHelper.getJSONFromI18nMap(this.getCharacteristicsMap()));
        }

        // Tarifs
        if (Validator.isNotNull(this.getPrice()) && Validator.isNotNull(this.getPrice().getPriceDescription())) {
            jsonPlace.put("price", JSONHelper.getJSONFromI18nMap(this.getPrice().getPriceDescriptionMap()));
        }

        // Mail
        if (Validator.isNotNull(this.getMail())) {
            jsonPlace.put("mail", this.getMail());
        }

        // Phone
        if (Validator.isNotNull(this.getPhone())) {
            jsonPlace.put("phone", this.getPhone());
        }

        // Facebook
        if (Validator.isNotNull(this.getFacebookLabel())) {
            jsonPlace.put("facebookName", JSONHelper.getJSONFromI18nMap(this.getFacebookLabelMap()));
            jsonPlace.put("facebookURL", JSONHelper.getJSONFromI18nMap(this.getFacebookURLMap()));
        }

        // Instagram
        if (Validator.isNotNull(this.getInstagramLabel())) {
            jsonPlace.put("instagramName", JSONHelper.getJSONFromI18nMap(this.getInstagramLabelMap()));
            jsonPlace.put("instagramURL", JSONHelper.getJSONFromI18nMap(this.getInstagramURLMap()));
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

        jsonPlace.put("hasURLSchedule", this.getHasURLSchedule());
        if (Validator.isNotNull(this.getScheduleLinkNameMap())) {
            jsonPlace.put("scheduleLinkName", JSONHelper.getJSONFromI18nMap(this.getScheduleLinkNameMap()));
        }
        if (Validator.isNotNull(this.getScheduleLinkURLMap())) {
            jsonPlace.put("scheduleLinkURL", JSONHelper.getJSONFromI18nMap(this.getScheduleLinkURLMap()));
        }

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
        jsonPlace.put("friendlyURL", StrasbourgPropsUtil.getPlaceDetailURL() + "/-/entity/id/" + this.getPlaceId() + "/" + UriHelper.normalizeToFriendlyUrl(this.getAlias(Locale.FRANCE)));

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
     * Retourne la version GeoJSON du lieu
     */
    @Override
    public JSONObject toGeoJSON() {
        JSONObject feature = JSONFactoryUtil.createJSONObject();
        feature.put("type", "Feature");

        feature.put("properties", this.toJSON());

        JSONObject geometry = JSONFactoryUtil.createJSONObject();
        geometry.put("type", "Point");
        JSONArray coordinates = JSONFactoryUtil.createJSONArray();
        coordinates.put(Float.valueOf(this.getMercatorX()));
        coordinates.put(Float.valueOf(this.getMercatorY()));
        geometry.put("coordinates", coordinates);
        feature.put("geometry", geometry);

        return feature;
    }

    /**
     * Renvoie le JSON de l'entite au format GeoJSON pour la map
     */
    @Override
    public JSONObject getGeoJSON(long groupId, Locale locale) {

        JSONObject feature = JSONFactoryUtil.createJSONObject();
        feature.put("type", "Feature");

        JSONObject properties = JSONFactoryUtil.createJSONObject();
        properties.put("name", this.getAlias(locale));
        properties.put("address", this.getAddressStreet() + " " + this.getAddressComplement() + "<br>"
                + this.getAddressZipCode() + " " + this.getCity(locale));
        properties.put("visual", this.getImageURL());
        // récupère l'url de détail du poi
        Group group = GroupLocalServiceUtil.fetchGroup(groupId);
        if (group == null) {
            group = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/strasbourg.eu");
        }
        if (group != null) {
            String url = "";
            String virtualHostName = group.getPublicLayoutSet().getVirtualHostname();
            if (virtualHostName.isEmpty()) {
                url = "/web" + group.getFriendlyURL() + "/";
            } else {
                url = "https://" + virtualHostName + "/";
            }
            url += "lieu/-/entity/sig/" + this.getSIGid() + "/" + this.getNormalizedAlias(locale);
            properties.put("url", url);
        }

        // gestion des doublons
        properties.put("sigId", this.getSIGid());
        String types = "";
        for (AssetCategory type : this.getTypes()) {
            if (types.length() > 0) {
                types += ", ";
            }
            types += type.getTitle(locale);
        }
        properties.put("listeTypes", types);

        // bouton favoris
        properties.put("type", "1");
        properties.put("id", this.getPlaceId());

        // Horaires et temps réel, ou contenu du tooltip carto
        if(!this.getContenuTooltipCarto(locale).isEmpty()){
            properties.put("contenu", this.getContenuTooltipCarto(locale));
        }else {
            if(this.getHasURLSchedule()){
                // Il n'a pas d'horaires mais un lien
                JSONObject urlPeriodJSON = JSONFactoryUtil.createJSONObject();
                urlPeriodJSON.put("url", this.getScheduleLinkURL(locale));
                properties.put("opened",urlPeriodJSON );
            }else {
                // Il a des horaires
                // récupère les horaires en cours
                GregorianCalendar now = new GregorianCalendar();
                List<PlaceSchedule> currentSchedules = this.getPlaceSchedule(now, locale);
                if (currentSchedules.size() > 0) {
                    PlaceSchedule currentSchedule = currentSchedules.get(0);
                    String schedule = "";
                    String opened = "";
                    if (currentSchedule.isAlwaysOpen()) {
                        schedule = LanguageUtil.get(locale, "open-all-time");
                        opened = LanguageUtil.get(locale, "open-period");
                    } else if (this.isOpenNow()) {
                        opened = LanguageUtil.get(locale, "open-period");
                        for (Pair<LocalTime, LocalTime> openingTime : currentSchedule.getOpeningTimes()) {
                            if (schedule.length() > 0) {
                                schedule += "<br>";
                            }
                            String startString = openingTime.getFirst().format(DateTimeFormatter.ofPattern("HH'h'mm"));
                            String endString = openingTime.getSecond().format(DateTimeFormatter.ofPattern("HH'h'mm"));
                            schedule += startString + " - " + endString;
                        }
                    } else {
                        opened = LanguageUtil.get(locale, "closed-period");
                        // on récupère le prochain horaire d'ouverture
                        PlaceSchedule nextOpening = this.getNextScheduleOpening(now, 2, locale);
                        if (nextOpening == null) {
                            opened = LanguageUtil.get(locale, "closed-now");
                            schedule += "";
                        } else {
                            Pair<LocalTime, LocalTime> openingTime = nextOpening.getOpeningTimes().get(0);
                            String startString = openingTime.getFirst().format(DateTimeFormatter.ofPattern("HH'h'mm"));
                            String endString = openingTime.getSecond().format(DateTimeFormatter.ofPattern("HH'h'mm"));
                            schedule += LanguageUtil.get(locale, "reopening") + " ";
                            int diff = nextOpening.getStartDate().compareTo(now.getTime());
                            if (diff > 0) {
                                now.add(GregorianCalendar.DAY_OF_YEAR, 1);
                                if (nextOpening.getStartDate().compareTo(now.getTime()) == 0) {
                                    schedule += LanguageUtil.get(locale, "tomorrow") + " ";
                                } else {
                                    schedule += LanguageUtil.get(locale, "after-tomorrow") + " ";
                                }
                            }
                            schedule += LanguageUtil.get(locale, "at") + " " + startString;
                            if (diff == 0) {
                                schedule += " " + LanguageUtil.get(locale, "up-to") + " " + endString;
                            }
                        }
                    }
                    properties.put("opened", opened);
                    properties.put("schedules", schedule);
                }
            }
        }

        // Icône (on prend le premier icon que l'on trouve dans une des catégories de
        // type de lieu)
        String icon = "";
        List<AssetCategory> categories = this.getTypes();
        String[] icons = null;
        for (AssetCategory category : categories) {
            if (!category.getDescription(locale).isEmpty()) {
                icons = category.getDescription(locale).split(";");
                // vérifi si le lieu dispose d'un horaire et s'il est fermé
                if (icons.length > 1 && this.hasScheduleTable() && !this.isOpenNow()) {
                    icon = icons[1];
                } else {
                    if (icons.length > 0) {
                        icon = icons[0];

                    }
                }
                break;
            }
        }

        properties.put("icon", icon);

        // Temps réel
        if (this.getRTEnabled()) {
            OccupationState occupation = this.getRealTime();
            String title = "";
            String frequentation = "";
            String label = "";
            String color = occupation.getCssClass();
            if (this.isSwimmingPool() ||this.isIceRink()) {
                title = "frequentation-real";
                frequentation = occupation.getOccupationLabel();
                label = occupation.getLabel();
            } else if (this.isMairie()) {
                title = "time-real";
                frequentation = occupation.getOccupationLabel();
                label = occupation.getLabel();
            } else if(this.isParking()){
                title = "occupation-real";
                frequentation = occupation.getAvailable();
                label = "available-spots";
            } else if(this.isVelhopStation()){
                title = "live-disponibility";
                frequentation = occupation.getAvailable();
                label = "eu.place.available-velhop";
            }
            JSONObject amountProperty = JSONFactoryUtil.createJSONObject();
            amountProperty.put("title", title);
            amountProperty.put("frequentation", frequentation);
            amountProperty.put("label", label);
            amountProperty.put("color", color);
            properties.put("amount", amountProperty);
        }

        feature.put("properties", properties);

        JSONObject geometry = JSONFactoryUtil.createJSONObject();
        geometry.put("type", "Point");
        JSONArray coordinates = JSONFactoryUtil.createJSONArray();
        coordinates.put(Float.valueOf(this.getMercatorX()));
        coordinates.put(Float.valueOf(this.getMercatorY()));
        geometry.put("coordinates", coordinates);
        feature.put("geometry", geometry);

        return feature;
    }

    /**
     * Renvoie le JSON de l'entite au format CSMap
     */
    @Override
    public JSONObject getCSMapJSON()    {
        JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();

        jsonPlace.put("idSurfs", this.getSIGid());
        JSONArray jsonSigs = JSONFactoryUtil.createJSONArray();
        for (AssetCategory categ : this.getTypes()) {
            if(Validator.isNotNull(categ)) {
                String sig = AssetVocabularyHelper.getCategoryProperty(categ.getCategoryId(), "SIG");
                if(Validator.isNotNull(sig))
                    jsonSigs.put(sig);
            }
        }
        jsonPlace.put("types", jsonSigs);
        JSONObject names = JSONFactoryUtil.createJSONObject();
        names.put("fr_FR", this.getAlias(Locale.FRANCE));
        jsonPlace.put("name", names);
        if (Validator.isNotNull(this.getAddressStreet())) {
            jsonPlace.put("street", this.getAddressStreet());
        }
        if (Validator.isNotNull(this.getAddressZipCode())) {
            jsonPlace.put("zipCode", this.getAddressZipCode());
        }
        if (Validator.isNotNull(this.getCity(Locale.FRANCE))) {
            jsonPlace.put("city", this.getCity(Locale.FRANCE));
        }
        if (Validator.isNotNull(this.getPhone())) {
            jsonPlace.put("phone", this.getPhone());
        }
        JSONArray jsonImagesURLs = JSONFactoryUtil.createJSONArray();
        JSONArray jsonImagesThumbnailURLs = JSONFactoryUtil.createJSONArray();
        for (String imageUrl : this.getImageURLsWithTimeStamp()) {
            if(Validator.isNotNull(imageUrl)) {
                try {
                    jsonImagesURLs.put(StrasbourgPropsUtil.getURL() + imageUrl);
                    jsonImagesThumbnailURLs.put(UriHelper.appendUriImagePreview(StrasbourgPropsUtil.getURL() + imageUrl).toString());
                } catch (Exception e){
                    jsonImagesURLs.put(StrasbourgPropsUtil.getURL() + imageUrl);
                    jsonImagesThumbnailURLs.put(StrasbourgPropsUtil.getURL() + imageUrl);
                }
            }
        }
        if (jsonImagesURLs.length() > 0) {
            jsonPlace.put("imageURL", jsonImagesURLs);
        }
        if (jsonImagesThumbnailURLs.length() > 0) {
            jsonPlace.put("imageThumbnailURL", jsonImagesThumbnailURLs);
        }
        JSONObject descriptions = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(this.getPresentation(Locale.FRANCE))) {
            descriptions.put("fr_FR", this.getPresentation(Locale.FRANCE));
        }
        if(descriptions.length() > 0)
            jsonPlace.put("description", descriptions);
        JSONArray scheduleExceptionsJSON = JSONFactoryUtil.createJSONArray();
        for (ScheduleException scheduleException : this.getScheduleExceptions()) {
            scheduleExceptionsJSON.put(scheduleException.toCSMapJSON());
        }
        if (Validator.isNotNull(this.getExceptionalSchedule())) {
            JSONObject scheduleExceptionJSON = JSONFactoryUtil.createJSONObject();
            JSONObject exceptionalScheduleJSON = JSONFactoryUtil.createJSONObject();
            exceptionalScheduleJSON.put("fr_FR", this.getExceptionalSchedule(Locale.FRANCE));
            scheduleExceptionJSON.put("description", exceptionalScheduleJSON);
            scheduleExceptionsJSON.put(scheduleExceptionJSON);
        }
        if (scheduleExceptionsJSON.length() > 0) {
            jsonPlace.put("exceptions", scheduleExceptionsJSON);
        }
        jsonPlace.put("accessForDeficient", this.getAccessForDeficient());
        jsonPlace.put("accessForElder", this.getAccessForElder());
        jsonPlace.put("accessForDeaf", this.getAccessForDeaf());
        jsonPlace.put("accessForBlind", this.getAccessForBlind());
        jsonPlace.put("accessForWheelchair", this.getAccessForWheelchair());
        if (Validator.isNotNull(this.getMercatorX())) {
            jsonPlace.put("mercatorX", this.getMercatorX());
        }
        if (Validator.isNotNull(this.getMercatorY())) {
            jsonPlace.put("mercatorY", this.getMercatorY());
        }
        jsonPlace.put("friendlyURL", StrasbourgPropsUtil.getPlaceDetailURL() + "/-/entity/id/" + this.getPlaceId() + "/" + UriHelper.normalizeToFriendlyUrl(this.getAlias(Locale.FRANCE)));
        if (Validator.isNotNull(this.getSiteURL(Locale.FRANCE))) {
            jsonPlace.put("websiteURL", this.getSiteURL(Locale.FRANCE));
        }
        return jsonPlace;
    }

    /**
     * Renvoie le JSON des horaires sur 7 jours au format CSMap
     */
    @Override
    public JSONObject getScheduleCSMapJSON() {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        if(!this.getPeriods().isEmpty()) {
            // si les horaires du lieux sont un lien, le JSON sera vide
            if (!this.getHasURLSchedule()) {
                // on récupère une map de clé jour et de valeur une liste de détail d'horaire d'ouvetrure (n'ayant qu'un enregistrement) pour les 7 prochains jours
                Map<String, List<PlaceSchedule>> schedules = this.getPlaceSchedule(new Date(), 7, Locale.FRANCE);
                JSONArray schedulesJSON = JSONFactoryUtil.createJSONArray();
                // on parcours les 7 prochains jours
                for (Map.Entry schedule : schedules.entrySet()) {
                    JSONObject scheduleJSON = JSONFactoryUtil.createJSONObject();
                    scheduleJSON.put("date", schedule.getKey());
                    // on récupère le détail des horaires du lieu pour ce jour
                    if (((List<PlaceSchedule>) schedule.getValue()).size() > 0) {
                        PlaceSchedule detail = ((List<PlaceSchedule>) schedule.getValue()).get(0);
                        scheduleJSON.put("isClosed", detail.isClosed());
                        scheduleJSON.put("alwaysOpen", detail.isAlwaysOpen());
                        if (detail.getOpeningTimes() != null) {
                            JSONArray hoursJSON = JSONFactoryUtil.createJSONArray();
                            // on parcours la liste des horaires d'ouverture de ce lieu pour ce jour
                            for (Pair<LocalTime, LocalTime> openingTime : detail.getOpeningTimes()) {
                                JSONObject hourJSON = JSONFactoryUtil.createJSONObject();
                                hourJSON.put("startHour", openingTime.getFirst());
                                hourJSON.put("endHour", openingTime.getSecond());
                                hoursJSON.put(hourJSON);
                            }
                            scheduleJSON.put("hours", hoursJSON);
                        }
                    }
                    schedulesJSON.put(scheduleJSON);
                }
                json.put("schedules", schedulesJSON);
            }
        }

        return json;
    }

    /**
     * Renvoie le titre du lieu pour friendlyUrl
     */
    @Override
    public String getNormalizedAlias() {
        return UriHelper.normalizeToFriendlyUrl(this.getAlias(Locale.FRANCE));
    }

    /**
     * Renvoie le titre du lieu pour friendlyUrl
     */
    @Override
    public String getNormalizedAlias(Locale locale) {
        return UriHelper.normalizeToFriendlyUrl(this.getAlias(locale));
    }

}