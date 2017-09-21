<#setting locale = locale />
<div class="mns-bloc-agenda" itemscope itemtype="http://schema.org/Event">
    <a href="/event/-/entity/id/${entry.eventId}">
        <span class="date">${entry.getEventScheduleDisplay(locale)}</span>
        <figure>
            <img src='${entry.getImageURL()}' alt="${entry.getTitle(locale)}" width="270" height="400" class="fit-cover" />
        </figure>
        <div>
            <div class="col-xs-6 mns-indic">
                <span class="icon-ico-map-marker"></span>
                <span itemprop="location" itemscope itemtype="http://schema.org/Place"><span itemprop="address" itemscope itemtype="http://schema.org/PostalAddress">${entry.getPlaceAlias(locale)}</span></span>
            </div>
            <div class="col-xs-6 mns-indic">
                <span class="icon-ico-type"></span>
                <span>${entry.getTypeLabel(locale)}</span>
            </div>
            <h3 itemprop="name">${entry.getTitle(locale)}</h3>
            <span class="basic-link">Découvrir</span>
        </div>
    </a>
</div>