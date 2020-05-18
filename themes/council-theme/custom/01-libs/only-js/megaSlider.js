/**
 * Library megaSlider (script Custom) pour faire fonctionner les sliders Socials et Une 
 */



/**
 * Get sources, create an array for each category and save them inside the conf object
 * @param {*} source 
 * @param {*} conf 
 */
function getSources(source, conf){
    conf.source_tous = source; // Table for category 'tous'
    // Build tables for each category
    source.forEach(function(element, index) {
        var category = element.category;
        if(!conf['source_'+category]){
            conf['source_'+category] = [];
        }
        conf['source_'+category].push(element);
    }, this);
}


/**
 * Create a DomNode element, cloned from the templates in index.html and populated from category flow
 * @param {string} category       - Filter category
 * @param {Object} conf           - Configuration object
 */
function majItems(category, conf){
    var source = conf['source_'+category] !== undefined ? conf['source_'+category] : [],
        clones = [];
    source.forEach(function(element, index) {
        var item_category = element.category; // Get element category
        var $template;
        if(conf['$template_'+item_category]){
            $template = conf['$template_'+item_category]; // Get element template*
        }else{
            console.log('ERROR: Le template pour la catégorie ['+item_category+'] est introuvable');
        }
        var clone_html = setTemplateInfos(element, item_category, $template, conf); // Set informations from source element inside the cloned template
        if(element.is_Big){
            clones.unshift(clone_html);
        }else{
            clones.push(clone_html);
        }
    }, this);
    addToSlider(conf, clones);
}

/**
 * Update infos like title, description etc, inside the cloned template that will be used to populate the slider
 * @param {object} infos - Infos of the element to put in the slider
 * @param {string} category - Category of the element
 * @param {jQueryObject} $clone - Clone from category dedicated template
 * @return {jQueryObject}
 */
function setTemplateInfos(infos, category, $template, conf){
    var $new_template = $template.clone();
    if(infos.picture != undefined && infos.picture != '' && conf.hasPicture_class != undefined){
        $new_template.addClass(conf.hasPicture_class);
    }
    if(infos.ville != undefined && infos.ville != '' && conf.hasVille_class != undefined){
        $new_template.addClass(conf.hasVille_class);
        $new_template.find('.seu-lead').attr('data-dot', 3);
    }
    if(infos.is_Big && conf.is_Big_Class != undefined){
        $new_template.addClass(conf.is_Big_Class);
    }
    var clone = $new_template[0].outerHTML;
    
    for(var info in infos){
        var toReplace = '__'+info+'__';
        toReplace = new RegExp(toReplace, "g");
        clone = clone.replace(toReplace, infos[info]);
    }
    return clone;
}

/**
 * Add elements to slider, divided into pages of 8 elements
 * @param {Object} conf 
 * @param {Array} elements - Array of all elements to add
 */
function addToSlider(conf, elements){
    var nodeStringToAdd = '<div class="'+conf.pages_class+'">';
    var itemSeparator = 8;
    var numElt = 0;
    if(elements[0].indexOf('seu-big') != -1){
        numElt = 1;
        nodeStringToAdd = '<div class="'+conf.pages_class + ' '+conf.is_Big_Class+'">';
    }
    if(environment == 'tablette'){
        itemSeparator = 4;
    }else if(environment == 'mobile'){
        itemSeparator = 1;
    }
    
    elements.forEach(function(element, index) {
        if(numElt%itemSeparator == 0 && index > 0){
            nodeStringToAdd += '</div><div class="'+conf.pages_class+'">';
        }
        numElt++;
        nodeStringToAdd += element;
    }, this);
    nodeStringToAdd += '</div>';
    conf.$slider.html(nodeStringToAdd);
}


/**
 * Remove all items from slider
 * @param {Object} conf - Configuration object
 */
function removeAllItems(conf){
    conf.$slider.find('.'+conf.pages_class).remove();
}

/**
 * Build Slider 
 * @param {Object} conf - Configuration object
 */
function buildSlider(conf){
    conf.$slider.addClass('owl-carousel').owlCarousel({
        margin : 20,
        items: 1,
        smartSpeed: 800,
        onInitialized: function(e){
            manageNavigationDisplay(e, conf.$prev, conf.$next);
            attachCustomNavEvents(conf.$slider, conf.$prev, conf.$next);
        },
        onTranslate : function(e){
            manageNavigationDisplay(e, conf.$prev, conf.$next);
        }
    });
}

/**
 * Destroy Slider 
 * @param {Object} conf - Configuration object
 */
function destroySlider(conf){
    conf.$slider.trigger('destroy.owl.carousel');
}

/**
 * Suite d'appels permettant de construire un mega-slider à la une en fonction d'une catégorie
 * @param {string} category - Catégorie à afficher
 */
function megaSlider(slider, category){
    destroySlider(slider);
    removeAllItems(slider);
    majItems(category, slider);
    buildSlider(slider);
    dot();
}