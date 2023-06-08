// Script associé aux onglets
jQuery(function(){
    jQuery('ul.tabs li').click(function(){
          $('.currentTab').removeClass('currentTab');
          jQuery('#'+$(this).attr('class')).addClass('currentTab');
          jQuery(this).addClass('currentTab');
    });
    $('#activity-description').addClass('currentTab');
});

// Add regex filter for jquery selectors
jQuery.expr[':'].regex = function(elem, index, match) {
    var matchParams = match[3].split(','),
        validLabels = /^(data|css):/,
        attr = {
            method: matchParams[0].match(validLabels) ? 
                        matchParams[0].split(':')[0] : 'attr',
            property: matchParams.shift().replace(validLabels,'')
        },
        regexFlags = 'ig',
        regex = new RegExp(matchParams.join('').replace(/^s+|s+$/g,''), regexFlags);
    return regex.test(jQuery(elem)[attr.method](attr.property));
}