(function() {
	var STR_FILE_ENTRY_RETURN_TYPE = 'com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType';

	var TPL_FILE_SCRIPT =  '   <div class="seu-wi seu-media seu-wi-download">  '  + 
												 '       <div class="seu-media-container">  '  + 
												 '           <div class="seu-media-left"><div class="seu-media-picto"></div></div>  '  + 
												 '           <div class="seu-media-right">  '  + 
												 '               <div class="seu-media-text">  '  + 
												 '                   <div class="seu-media-title">{name}</div>  '  + 
												 '                   <p>{type} - {size}</p>  '  + 
												 '               </div>  '  + 
												 '               <a href="{url}" target="_blank" class="seu-media-download seu-btn-square seu-filled seu-second" title="{name} (nouvelle fenêtre)">  '  + 
												 '                   <div class="seu-btn-text-editable"><span class="seu-flexbox">  '  + 
												 '                       <span class="seu-btn-text">Télécharger</span>  '  + 
												 '                       <span class="seu-btn-arrow">&nbsp;</span>  '  + 
												 '                   </span></div>  '  + 
												 '               </a>  '  + 
												 '           </div>  '  + 
												 '       </div>  '  + 
												 '  </div>  ' ;


	CKEDITOR.plugins.add(
		'blockfileselector',
		{
			requires: "widget",
			init: function(editor) {
				var instance = this;

		    // Autorise div dans a
		    CKEDITOR.dtd.a.div = 1;

				// On ajoute un widget
		 		editor.widgets.add("blockfileselector", {
		 			requiredContent: "div(seu-wi-download)",
		 			editables: {
		        title: {
		          selector: ".seu-media-title",
		          allowedContent: ""  
		        },
		        button: {
		          selector: ".seu-btn-text-editable",
		          allowedContent: "span(seu-flexbox,seu-btn-text,seu-btn-arrow)"
		        }
		      },
		      upcast: function(element) {
		        return element.name == "div" && element.hasClass("seu-wi-download");
		      }
			 });

				instance._fileTPL = new CKEDITOR.template(TPL_FILE_SCRIPT);

				editor.addCommand(
					'blockfileselector',
					{
						canUndo: false,
						exec: function(editor, callback) {
							var onSelectedFileChangeFn = AUI().bind(
								'_onSelectedFileChange',
								instance,
								editor,
								callback
							);

							instance._getItemSelectorDialog(
								editor,
								editor.config.filebrowserFileBrowseUrl,
								function(itemSelectorDialog) {
									itemSelectorDialog.once('selectedItemChange', onSelectedFileChangeFn);
									itemSelectorDialog.open();
								}
							);
						}
					}
				);

				if (editor.ui.addButton) {
					editor.ui.addButton(
						'BlockFileSelector',
						{
							command: 'blockfileselector',
							icon: instance.path + 'assets/blockfileselector.png',
							label: 'Insérer un lien vers un fichier'
						}
					);
				}

				CKEDITOR.on(
					'dialogDefinition',
					function(event) {
						var dialogName = event.data.name;

						var dialogDefinition = event.data.definition;

						if (dialogName === 'file') {
							instance._bindBrowseButton(editor, dialogDefinition, 'info', 'blockfileselector', 'url');
						}
					}
				);

				editor.once(
					'destroy',
					function() {
						if (instance._itemSelectorDialog) {
							instance._itemSelectorDialog.destroy();
						}
					}
				);
			},

			_bindBrowseButton: function(editor, dialogDefinition, tabName, commandName, targetField) {
				var tab = dialogDefinition.getContents(tabName);

				if (tab) {
					var browseButton = tab.get('browse');

					if (browseButton) {
						browseButton.onClick = function() {
							editor.execCommand(
								commandName,
								function(newVal) {
									dialogDefinition.dialog.setValueOf(tabName, targetField, newVal);
								}
							);
						};
					}
				}
			},
			
			_getItemSelectorDialog: function(editor, url, callback) {
				var instance = this;

				var eventName = editor.name + 'selectItem';

				var itemSelectorDialog = instance._itemSelectorDialog;

				if (itemSelectorDialog) {
					itemSelectorDialog.set('eventName', eventName);
					itemSelectorDialog.set('url', url);
					itemSelectorDialog.set('zIndex', CKEDITOR.getNextZIndex());

					callback(itemSelectorDialog);
				}
				else {
					AUI().use(
						'liferay-item-selector-dialog',
						function(A) {

							itemSelectorDialog = new A.LiferayItemSelectorDialog(
								{
									eventName: eventName,
									url: url,
									zIndex: CKEDITOR.getNextZIndex()
								}
							);

							instance._itemSelectorDialog = itemSelectorDialog;

							callback(itemSelectorDialog);
						}
					);
				}
			},

			_getItemSrc: function(editor, selectedItem) {
				var itemSrc = selectedItem.value;

				if (selectedItem.returnType === STR_FILE_ENTRY_RETURN_TYPE) {
					try {
						var itemValue = JSON.parse(selectedItem.value);

						itemSrc = editor.config.attachmentURLPrefix ? editor.config.attachmentURLPrefix + itemValue.title : itemValue.url;
					}
					catch (e) {
					}
				}

				return itemSrc;
			},

			_onSelectedFileChange: function(editor, callback, event) {
				var instance = this;

				var selectedItem = event.newVal;

				if (selectedItem) {
					var fileSrc = instance._getItemSrc(editor, selectedItem);

					if (fileSrc) {
						var urlInterstingPart = fileSrc.split("/documents/")[1];
		                var groupId = urlInterstingPart.split("/")[0];
		                var urlParts = urlInterstingPart.split("/");
		                var uuid = urlParts[urlParts.length - 1].substring(0, 36);
						Liferay.Service('/strasbourg.strasbourg/get-file-details', {
							groupId : groupId,
							uuid: uuid,
							language : 'fr_FR'
						}, function(response) {
							var htmlToInsert = instance._fileTPL.output(
								{
									url: fileSrc,
									name: response.title,
									type: response.type.toUpperCase(),
									size: response.size.toUpperCase()
								}
							);
							editor.insertHtml(htmlToInsert);
						});
						
					}
				}
			}
		}
	);
})();