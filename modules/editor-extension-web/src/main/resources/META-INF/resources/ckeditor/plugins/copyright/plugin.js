(function() {
	CKEDITOR.plugins.add('copyright', {
		init : function(editor) {
			var instance = this;
			editor.addCommand('insertCopyright', {
				exec : function(editor) {
					var selection = editor.getSelection();
					var element = selection.getStartElement();
					if (element.$ && element.$.src) {
						var path = new URL(element.$.src).pathname;
						var urlInterstingPart = path.split("/documents/")[1];
		                var groupId = urlInterstingPart.split("/")[0];
		                var urlParts = urlInterstingPart.split("/");
		                var uuid = urlParts[urlParts.length - 1].substring(0, 36);
						Liferay.Service('/strasbourg.strasbourg/get-copyright', {
							groupId : groupId,
							uuid: uuid,
							language : 'fr_FR'
						}, function(response) {
							var mainDiv = jQuery(element.$).wrap('<div class="image-with-copyright"></div>');
							mainDiv.parent().append('<div class="copyright">' + response.copyright + '</div>');
						});
					}
				}
			});
			editor.ui.addButton('Copyright', {
				label : 'Ajouter le copyright de l\'image',
				command : 'insertCopyright',
				icon : instance.path + 'assets/copyright.png'
			});
		}
	});
})();