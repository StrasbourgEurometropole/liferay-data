var linkBlockTemplate =  '<div class="seu-wi-link-group"><a class="seu-wi seu-media" href="https://www.example.com" title="Lien hypertexte">'  + 
                         '  <div class="seu-media-container">'  + 
                         '    <div class="seu-media-left"><div class="seu-media-picto"></div></div>'  + 
                         '    <div class="seu-media-right">'  + 
                         '      <div class="seu-media-text">'  + 
                         '        <div class="seu-media-title">Lien hypertexte</div>'  + 
                         '      </div>'  + 
                         '      <div class="seu-link-group-arrow"></div>'  + 
                         '    </div>'  + 
                         '  </div>'  + 
                         '</a></div>  ' ;

/**
 * Plugin chiffres clés
 */
CKEDITOR.plugins.add('linkblock', {
 requires: 'widget',
  init: function(editor) {
    var instance = this;

    // On enregistre la boîte de dialogue
    CKEDITOR.dialog.add('linkblock', this.path + 'dialogs/linkblock.js');

    // Autorise div dans a
    CKEDITOR.dtd.a.div = 1;

    // On enregistre le widget
    editor.widgets.add('linkblock', {
      // Contenu minimum du widget
      requiredContent: 'div(seu-wi-link-group)',
      // Dialog à afficher
      dialog: 'linkblock',
      // Champs du widget
      editables: {
        link: {
          selector: '.seu-media-title',
          allowedContent: ''
        }
      },
      // Template généré
      template: linkBlockTemplate,
      upcast: function(element) {
        return element.name == 'div' && element.hasClass('seu-wi-link-group')
      },
      // A l'initialisation on envoie les données nécessaires à la boîte de dialogue
      init: function() {
        var href = this.element.getChild(0).getAttribute('href');
        var targetIsNewWindow = this.element.getChild(0).getAttribute('target') === '_blank'
        var title = this.element.getChild(0).getAttribute('title');
        this.setData('href', href);
        this.setData('title', title);
        this.setData('newWindow', targetIsNewWindow);
      },
      // On met à jour le markup après validation de la boîte de dialogue
      data: function() {
        this.element.getChild(0).setAttribute('href', this.data.href);
        this.element.getChild(0).setAttribute('data-cke-saved-href', this.data.href);
        this.element.getChild(0).setAttribute('title', this.data.title);
        if (this.data.newWindow) {
          this.element.getChild(0).setAttribute('target', '_blank');
        } else {
          this.element.getChild(0).setAttribute('target', '_self');
        }
      }
    });

    // Ajout de la commande pour créer un premier widget
    editor.addCommand('insertLinkBlock', {
      exec: function(editor) {
        editor.insertHtml(linkBlockTemplate)
      }
    });

    // Bouton pour la commande
    editor.ui.addButton('LinkBlock', {
      label: 'Insérer un bloc lien',
      command: 'insertLinkBlock',
      icon: instance.path + 'assets/linkblock.png'
    });
    
    // Custom CSS
    editor.addContentsCss(instance.path + 'assets/linkblock.css');
  }
});
