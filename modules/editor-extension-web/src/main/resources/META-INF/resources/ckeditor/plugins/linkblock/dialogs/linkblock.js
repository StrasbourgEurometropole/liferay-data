CKEDITOR.dialog.add('linkblock', function(editor) {
  return {
    title: 'Lien',
    minWidth: 300,
    minHeight: 100,
    contents: [{
      id: 'info',
      elements: [{
        id: 'href',
        type: 'text',
        width: '290px',
        label: 'Destination',
        setup: function(widget) {
          this.setValue(widget.data.href);
        },
        commit: function(widget) {
          widget.setData('href', this.getValue());
        }
      }, {
        id: 'title',
        type: 'text',
        width: '290px',
        label: 'Titre',
        setup: function(widget) {
          this.setValue(widget.data.title);
        },
        commit: function(widget) {
          widget.setData('title', this.getValue());
        }
      }, {
        id: 'newWindow',
        type: 'checkbox',
        width: '100px',
        label: 'Nouvelle fenêtre',
        setup: function(widget) {
          this.setValue(widget.data.newWindow);
        },
        commit: function(widget) {
          widget.setData('newWindow', this.getValue());
        }
      }]
    }]
  }
});
