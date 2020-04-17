$('.seu-wi-crossreading, .seu-wi-contact').each(function() {
  if ($(this).parents('.portlet-nested-portlets').length) {
    $(this).parents('.portlet-nested-portlets').addClass('seu-container seu-wi-duo');
  } else {
    $(this).parents('.portlet-boundary').addClass('seu-container seu-wi-duo seu-wi-solo');
    $(this).parents('.portlet-boundary').css('position', 'relative');
  }
});

$('.seu-wi-trombinoscope').each(function() {
  if ($(this).parents('.portlet-nested-portlets').length) {
    $(this).parents('.portlet-nested-portlets').addClass('seu-container seu-wi-duo');
  }
});

$('.seu-wi-trombinoscope').parents('.col-md-6').addClass('seu-wi-trombinoscope-container');
