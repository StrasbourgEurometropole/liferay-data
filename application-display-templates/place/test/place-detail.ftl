<!-- Détail lieu -->
<#setting locale = locale />
<#setting date_format="d MMMM yyyy">
SIG ID : ${entry.getSIGid()}<br>
nonm : ${entry.getName()}<br>
Rue : ${entry.getAddressStreet()}<br>
Complement : ${entry.getAddressComplement()}<br>
Distribution : ${entry.getAddressDistribution()}<br>
Code postal : ${entry.getAddressZipCode()}<br>
Pays : ${entry.getAddressCountry()}<br>
Mercator X : ${entry.getMercatorX()}<br>
Mercator Y : ${entry.getMercatorY()}<br>
RGF93 X : ${entry.getRGF93X()}<br>
RGF93 Y : ${entry.getRGF93Y()}<br>
Alias : ${entry.getAlias(locale)}<br>
Presentation : ${entry.getPresentation(locale)}<br>
Service et activities : ${entry.getServiceAndActivities(locale)}<br>
Caracteristiques : ${entry.getCharacteristics(locale)}<br>
Id image principale : ${entry.getImageId()}<br>
Id images secondaires : ${entry.getImageIds()}<br>
Id vidéos : ${entry.getVideosIds()}<br>
Id documents : ${entry.getDocumentsIds()}<br>
Téléphone : ${entry.getPhone()}<br>
Email : ${entry.getMail()}<br>
Label du site : ${entry.getSiteLabel(locale)}<br>
URL du site : ${entry.getSiteURL(locale)}<br>
Label du Facebook : ${entry.getFacebookLabel(locale)}<br>
URL du Facebook : ${entry.getFacebookURL(locale)}<br>
Mode d'accès : ${entry.getAccess(locale)}<br>
Place d'accès : ${entry.getAccesMap(locale)}<br>
accessForDisabled : ${entry.getAccessForDisabled(locale)}<br>
accessForBlind : ${entry.getAccessForBlind()?string('oui', 'non')}<br>
accessForDeaf : ${entry.getAccessForDeaf()?string('oui', 'non')}<br>
accessForWheelchair : ${entry.getAccessForWheelchair()?string('oui', 'non')}<br>
accessForElder : ${entry.getAccessForElder()?string('oui', 'non')}<br>
accessForDeficient : ${entry.getAccessForDeficient()?string('oui', 'non')}<br>
<#list entry.getPeriods() as period>
    <#assign startDate = period.getStartDate()!.now />
    <#assign endDate = period.getEndDate()!.now />
    id période : ${period.getPeriodId()}<br>
	nom période : ${period.getName()}<br>
	Label site période : ${period.getLinkLabel()}<br>
	URL site période : ${period.getLinkURL()}<br>
	Période par défaut : ${period.getDefaultPeriod()?string('oui', 'non')}<br>
	Date de début : ${startDate?date}<br>
	Date de fin : ${endDate?date}<br>
	Ce lieu est ouvert 24h/24 7j/7 : ${period.getAlwaysOpen()?string('oui', 'non')}<br>
	<#list period.getSlots() as slot>
    	id slot : ${slot.getSlotId()}<br>
		Jour : ${slot.getDayOfWeek()}<br>
		Heure de début : ${slot.getStartHour()}<br>
		Heure de fin : ${slot.getEndHour()}<br>
	</#list>
</#list>
<#list entry.getScheduleExceptions() as scheduleException>
    id scheduleException : ${scheduleException.getExceptionId()}<br>
	Heure de début : ${scheduleException.getStartHour()}<br>
	Heure de fin : ${scheduleException.getEndHour()}<br>
	Description : ${scheduleException.getComment()}<br>
    <#assign startDateScheduleException = scheduleException.getStartDate()!.now />
	Date de début : ${startDateScheduleException?date}<br>
    <#assign endDateScheduleException = scheduleException.getEndDate()!.now />
	Date de fin : ${endDateScheduleException?date}<br>
	Fermé : ${scheduleException.getClosed()?string('oui', 'non')}<br>
</#list>
Afficher l'agenda : ${entry.getDisplayEvents()?string('oui', 'non')}<br>
Info. complémentaires : ${entry.getAdditionalInformation(locale)}<br>
Id externe : ${entry.getRTExternalId()}<br>
<#list entry.getPeriods() as period>
Seuil vert - orange : ${period.getRTGreenThreshold()}<br>
Seuil orange - rouge : ${period.getRTOrangeThreshold()}<br>
Seuil rouge - nonir : ${period.getRTRedThreshold()}<br>
Capacité totale : ${period.getRTMaxThreshold()}<br>
</#list>
<#list entry.getSubPlaces() as subPlace>
	nom du sous lieu : ${subPlace.getName()}<br>
</#list>