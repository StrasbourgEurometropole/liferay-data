(function() {
	var STR_FILE_ENTRY_RETURN_TYPE = 'com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType';

	var TPL_FILE_SCRIPT = '<a href="{url}" target="_blank">{name} ({type} - {size})</a>';
	CKEDITOR.plugins.add(
		'fileselector',
		{
			init: function(editor) {
				var instance = this;

				instance._fileTPL = new CKEDITOR.template(TPL_FILE_SCRIPT);

				editor.addCommand(
					'fileselector',
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
						'FileSelector',
						{
							command: 'fileselector',
							icon: instance.path + 'assets/fileselector.png',
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
							instance._bindBrowseButton(editor, dialogDefinition, 'info', 'fileselector', 'url');
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
									type: response.type,
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