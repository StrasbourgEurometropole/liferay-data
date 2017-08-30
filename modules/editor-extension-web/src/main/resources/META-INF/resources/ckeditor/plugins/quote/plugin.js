var quoteTemplate = '   <div class="seu-wi seu-wi-quote">  '  + 
                        '       <div class="seu-container">  '  + 
                        '           <h2 class="seu-section-title">  '  + 
                        '               <span class="seu-title">« Monsieur Jack, vous dactylographiez bien mieux que votre ami Wolf »</span>  '  + 
                        '               <span class="seu-subtitle">Candice Porter</span>  '  + 
                        '           </h2>  '  + 
                        '       </div>  '  + 
                        '  </div>  ' ; 
/**
 * Plugin chiffres clés
 */
CKEDITOR.plugins.add('quote', {
 requires: 'widget',
  init: function(editor) {
    var instance = this;

    // On enregistre le widget
    editor.widgets.add('quote', {
      // Contenu minimum du widget
      requiredContent: 'div(seu-wi-quote)',
      // Dialog à afficher
      dialog: 'linkblock',
      // Champs du widget
      editables: {
        link: {
          selector: '.seu-container',
          allowedContent: 'h2(seu-section-title);span(seu-title,seu-subtitle)'
        }
      },
      // Template généré
      template: quoteTemplate,
      upcast: function(element) {
        return element.name == 'div' && element.hasClass('seu-wi-quote')
      }
    });

    // Ajout de la commande pour créer un premier widget
    editor.addCommand('insertQuote', {
      exec: function(editor) {
        editor.insertHtml(quoteTemplate)
      }
    });

    // Bouton pour la commande
    editor.ui.addButton('Quote', {
      label: 'Ajouter une citation',
      command: 'insertQuote',
      icon: instance.path + 'assets/quote.png'
    });
    
    // Custom CSS
    editor.addContentsCss(instance.path + 'assets/quote.css');
  }
});
