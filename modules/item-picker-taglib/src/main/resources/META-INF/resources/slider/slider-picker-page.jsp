<%@include file="/picker-init.jsp" %>

<div class="strasbourg-slider-picker">
	<p>
		<label class="label-vignette" >${label}</label>
	</p>
    <ul id="thumbnail-${name}" class="detail">
        <c:if test="${event != null}">
            <li>
                ${not empty event.titleMap ? event.getTitle(locale) : event.title}<br>
                <fmt:formatDate value="${event.publicationDate}" var="formattedPublicationDate" type="date" pattern="dd/MM/yyyy" />
                Publi&eacute; le : ${formattedPublicationDate}<br>
                <fmt:formatDate value="${event.lastEndDate}" var="formattedLastEndDate" type="date" pattern="dd/MM/yyyy" />
                Fin de l'&eacute;v&egrave;nement le : ${formattedLastEndDate}
            </li>
        </c:if>
        <c:if test="${article != null}">
            <li>
                ${not empty article.titleMap ? article.getTitle(locale) : article.title}<br>
                <fmt:formatDate value="${article.createDate}" var="formattedCreateDate" type="date" pattern="dd/MM/yyyy" />
                Publi&eacute; le : ${formattedCreateDate}<br>
                <c:if test="${article.expirationDate != null}">
                    <fmt:formatDate value="${article.expirationDate}" var="formattedExpirationDate" type="date" pattern="dd/MM/yyyy" />
                    D&eacute;publi&eacute; le : ${formattedExpirationDate}
                </c:if>
            </li>
        </c:if>
    </ul>
	<p>
		<aui:button name="choose-event-${name}" id="choose-event-${name}" value="&Eacute;v&eacute;nement" cssClass="eventButton" />
		<aui:button name="choose-article-${name}" id="choose-article-${name}" value="Actu/Webmag" cssClass="articleButton" />

	</p>
	<div class="has-error">
		<aui:input name="${name}" type="hidden" value="${value}" />
	</div>
</div>
<aui:script use="liferay-item-selector-dialog">
    // JS gérant l'ouverture du popup de selection de l'évènement
    $('#<portlet:namespace />choose-event-${name}').on('click',
        function(event) {
            var itemPicker = $(event.target).closest(".strasbourg-slider-picker");
            var itemSelectorDialog = new A.LiferayItemSelectorDialog(
                {
                    eventName: 'itemSelected${name}',
                    on: {
                        selectedItemChange: function(event) {
                            var item = event.newVal;
                            var items = [];
                            if (!!item) {
                                items.push(item);
                            }

                            for (var i = 0; i < items.length; i++) {
                                var selectedItem = items[i];
                                var htmlToAppend = '<li>'
                                    + selectedItem.title + '<br>'
                                    + 'Publi&eacute; le : ' + selectedItem.publicationDate + '<br>'
                                    + "Fin de l'&eacute;v&egrave;nement le : " + selectedItem.lastEndDate
                                    +'</li>';
                                $(itemPicker).find('.detail').empty();
                                $(itemPicker).find('.detail').append(htmlToAppend);
                                $(itemPicker).find('.has-error input').val(selectedItem.entityId);
                                $(itemPicker).find('.label-vignette').html("&Eacute;v&eacute;nement");
                            }
                        }
                    },
                    title: 'S&eacute;lectionnez un &eacute;v&egrave;nement',
                    url: '${itemEventSelectorURL}'
                }
            );
            itemSelectorDialog.open();
        }
    );

    // JS gérant l'ouverture du popup de selection du contenu web
     $('#<portlet:namespace />choose-article-${name}').on('click',
        function(event) {
            var itemPicker = $(event.target).closest(".strasbourg-slider-picker");
            var itemSelectorDialog = new A.LiferayItemSelectorDialog(
                {
                    eventName: 'itemSelected${name}',
                    on: {
                        selectedItemChange: function(event) {
                            var item = event.newVal;
                            var items = [];
                            if (!!item) {
                                items.push(item);
                            }

                            for (var i = 0; i < items.length; i++) {
                                var selectedItem = items[i];
                                var htmlToAppend = '<li>'
                                    + selectedItem.title + '<br>'
                                    + 'Publi&eacute; le : ' + selectedItem.createDate + '<br>';
                                if(selectedItem.expirationDate != "")
                                    htmlToAppend += 'D&eacute;publi&eacute; le : ' + selectedItem.expirationDate;
                                htmlToAppend += '</li>';
                                $(itemPicker).find('.detail').empty();
                                $(itemPicker).find('.detail').append(htmlToAppend);
                                $(itemPicker).find('.has-error input').val(selectedItem.entityId);
                                if(selectedItem.folderId == ${folderWebmagId})
                                    $(itemPicker).find('.label-vignette').html("Webmag");
                                else
                                    $(itemPicker).find('.label-vignette').html("Actu");
                            }
                        }
                    },
                    title: 'S&eacute;lectionnez un article/webmag',
                    url: '${itemArticleSelectorURL}'
                }
            );
            itemSelectorDialog.open();
        }
    );
</aui:script>