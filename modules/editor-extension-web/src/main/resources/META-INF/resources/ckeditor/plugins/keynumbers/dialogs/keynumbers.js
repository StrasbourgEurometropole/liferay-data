CKEDITOR.dialog.add('keynumbers', function(editor) {
  return {
    title: 'Chiffres clés',
    minWidth: 200,
    minHeight: 100,
    contents: [{
      id: 'info',
      elements: [{
        id: 'numberOfColumns',
        type: 'text',
        width: '50px',
        label: 'Nombre de chiffres',
        setup: function(widget) {
          this.setValue(widget.data.numberOfColumns);
        },
        commit: function(widget) {
          widget.setData('numberOfColumns', this.getValue());
        }
      }]
    }]
  }
});
