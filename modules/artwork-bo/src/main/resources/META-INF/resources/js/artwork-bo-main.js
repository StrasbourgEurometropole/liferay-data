jQuery(function() {
	var translationButtons = jQuery('.lfr-translation-manager-translation');
	var previousLanguage = 'fr_FR';
	var language ='fr_FR';
	var namespace = '_eu_strasbourg_portlet_artwork_ArtworkBOPortlet_';
	// When clicking on a language
	 jQuery('body').off('click').on('click', '.lfr-translation-manager-translation', function() {
		var xmlInput = jQuery('input[name=' + namespace + 'description]')[0];
		var xmlString = xmlInput.value ? xmlInput.value : '<?xml version="1.0" encoding="UTF-8"?><root available-locales="fr_FR," default-locale="fr_FR"></root>';
		var xmlDocument = jQuery(jQuery.parseXML(xmlString));
		var editor = tinyMCE.editors.filter(e => e.id === namespace + 'descriptionEditor')[0];

		language = $(this).attr('locale') ? $(this).attr('locale') : 'fr_FR';
		
		if (previousLanguage !== language) {
			// Trigger click on all the flags on the page
			var flags = jQuery('.palette-item[data-value=' + language + ']');
			flags.trigger('click');
	
			// Update the XML to set the content of the previous language with
			// the data of the editor
			extractEditorData(previousLanguage, xmlInput, editor);
			
			// Update the editor content
			var xmlNode = xmlDocument.find('[language-id=' + language + ']');
			if (xmlNode.length > 0) {
				editor.setContent(xmlNode.text());
			} else {
				editor.setContent('');
			}
			
			previousLanguage = language;
		}
		
	})
	// Lors du clic sur le bouton 'publier', on set le champ 'forceStatus'
	.on('click', '#' + namespace + 'publish', function() {
		jQuery('input[name=' + namespace + 'forceStatus]')[0].value
		= 'publish';
	})
	// Lors du clic sur le bouton 'dépublier', on set le champ 'forceStatus'
	.on('click', '#' + namespace + 'unpublish', function() {
		jQuery('input[name=' + namespace + 'forceStatus]')[0].value
		= 'unpublish';
	});
	
	// When submitting the form, set the XML with the data of the editor in the
	// current language
	jQuery('body').off('submit').on('submit', '#' + namespace + 'fm', function() {
		var xmlInput = jQuery('input[name=' + namespace + 'description]')[0];
		var editor = tinyMCE.editors.filter(e => e.id === namespace + 'descriptionEditor')[0];
		extractEditorData(language, xmlInput, editor);
	});
	 
	var extractEditorData = function(language, xmlInput, editor) {
		var xmlString = xmlInput.value ? xmlInput.value : '<?xml version="1.0" encoding="UTF-8"?><root available-locales="fr_FR," default-locale="fr_FR"></root>';
		var xmlDocument = jQuery(jQuery.parseXML(xmlString));
		var xmlNode = xmlDocument.find('[language-id=' + language + ']');
		
		var content = editor.getContent();
		if (content.substring(0, 9) !== '<![CDATA[') {
			content = '<![CDATA[' + content + ']]>';
		}
		
		if (xmlNode.length === 0) {
			xmlDocument.find('root').append('<Description language-id="' + previousLanguage + '">' + content + '</Description>');
		} else {
			xmlNode.text(content);
		}
		xmlInput.value = new XMLSerializer().serializeToString(xmlDocument[0]);
	};
});
