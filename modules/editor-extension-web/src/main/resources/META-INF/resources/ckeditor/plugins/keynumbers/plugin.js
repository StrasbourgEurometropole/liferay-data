var keyNumbersTemplate = '   <div class="seu-wi seu-wi-chiffres">  '  + 
                         '       <div class="seu-container">  '  + 
                         '           <div class="seu-wi-chiffres-list">  '  + 
                         '               <div class="seu-wi-text seu-wi-chiffres-item">  '  + 
                         '                   <div class="seu-section-title">  '  + 
                         '                       <div class="seu-title">489 767</div>  '  + 
                         '                   </div>  '  + 
                         '                   <div class="seu-wi-title">Habitants</div>  '  + 
                         '               </div>  '  + 
                         '               <div class="seu-wi-text seu-wi-chiffres-item">  '  + 
                         '                   <div class="seu-section-title">  '  + 
                         '                       <div class="seu-title">339,85</div>  '  + 
                         '                   </div>  '  + 
                         '                   <div class="seu-wi-title">KM</div>  '  + 
                         '               </div>  '  + 
                         '               <div class="seu-wi-text seu-wi-chiffres-item">  '  + 
                         '                   <div class="seu-section-title">  '  + 
                         '                       <div class="seu-title">31.12.1966</div>  '  + 
                         '                   </div>  '  + 
                         '                   <div class="seu-wi-title">Date de création de la communauté urbaine de Strasbourg</div>  '  + 
                         '               </div>  '  + 
                         '               <div class="seu-wi-text seu-wi-chiffres-item">  '  + 
                         '                   <div class="seu-section-title">  '  + 
                         '                       <div class="seu-title">01.01.2015</div>  '  + 
                         '                   </div>  '  + 
                         '                   <div class="seu-wi-title">Date de transformation en eurométropole de Strasbourg</div>  '  + 
                         '               </div>  '  + 
                         '           </div>  '  + 
                         '           <div class="seu-subtitle">Source :  Ville et Eurométropole de Strasbourg</div>  '  + 
                         '       </div>  '  + 
                         '  </div>  ' ;

var numberTemplate = '<div class="seu-wi-text seu-wi-chiffres-item">  '  + 
                     '  <div class="seu-section-title">  '  + 
                     '    <div class="seu-title">489 767</div>  '  + 
                     '  </div>  '  + 
                     '  <div class="seu-wi-title">Habitants</div>  '  + 
                     '</div>';
/**
 * Plugin chiffres clés
 */
CKEDITOR.plugins.add('keynumbers', {
  requires: 'widget',
  init: function(editor) {
    var instance = this;

    // On enregistre la boîte de dialogue
    CKEDITOR.dialog.add('keynumbers', this.path + 'dialogs/keynumbers.js');

    // On enregistre le widget
    editor.widgets.add('keynumbers', {
      // Contenu minimum du widget
      requiredContent: 'div(seu-wi-chiffres)',
      // Dialog à afficher
      dialog: 'keynumbers',
      // Champs du widget
      editables: {
        number: {
          selector: '.seu-wi-chiffres-list',
          allowedContent: 'div(seu-wi-text); div(seu-wi-chiffres-item); div(seu-section-title); div(seu-title); div(seu-wi-title)'
        },
        source: {
          selector: '.seu-subtitle',
          allowedContent: ''
        }
      },
      // Template généré
      template: keyNumbersTemplate,
      upcast: function(element) {
        return element.name == 'div' && element.hasClass('seu-wi-chiffres')
      },
      // A l'initialisation on envoie les données nécessaires à la boîte de dialogue
      init: function() {
        var numberOfColumns = this.element.$.getElementsByClassName('seu-wi-chiffres-item').length;
        this.setData('numberOfColumns', numberOfColumns);
      },
      // On met à jour le markup après validation de la boîte de dialogue
      data: function() {
        if (typeof this.data.numberOfColumns !== "undefined") {
          var numberOfColumnsBefore = this.element.$.getElementsByClassName('seu-wi-chiffres-item').length;
          // Si on a toujours le même nombre de chiffres, on ne change rien
          if (this.data.numberOfColumns === numberOfColumnsBefore) {
            return;
          } else if (this.data.numberOfColumns > numberOfColumnsBefore) {
            // Si on en a plus qu'avant, on en rajoute
            var columnsToAdd = this.data.numberOfColumns - numberOfColumnsBefore;
            var numberListDom = this.element.$.getElementsByClassName('seu-wi-chiffres-list')[0];
            for (var i = 0; i < columnsToAdd; i++) {
              numberListDom.insertAdjacentHTML('beforeend', numberTemplate); 
            }
          } else if (this.data.numberOfColumns < numberOfColumnsBefore) {
            // Si on en a moins qu'avant on en supprime
            var columnsToRemove = numberOfColumnsBefore - this.data.numberOfColumns;
            if (columnsToRemove > numberOfColumnsBefore) {
              columnsToRemove = numberOfColumnsBefore;
            }
            var numbersDom = this.element.$.getElementsByClassName('seu-wi-chiffres-item');
            for (var i = 0; i < columnsToRemove; i++) {
              numbersDom[numbersDom.length - 1].remove();
            }
          }
        }
      }
    });

    // Ajout de la commande pour créer un premier widget
    editor.addCommand('insertKeyNumbers', {
      exec: function(editor) {
        editor.insertHtml(keyNumbersTemplate)
      }
    });

    // Bouton pour la commande
    editor.ui.addButton('KeyNumbers', {
      label: 'Ajouter des chiffres clés',
      command: 'insertKeyNumbers',
      icon: instance.path + 'assets/keynumbers.png'
    });
    
    // Custom CSS
    editor.addContentsCss(instance.path + 'assets/keynumbers.css');
  }
});
