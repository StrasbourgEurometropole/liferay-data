if($('.seu-wi.seu-wi-agenda.seu-type--actu').length){
    $(document).ready(function(){
        $('.seu-wi.seu-wi-agenda.seu-type--actu').each(function(index, widget){
            var wi = buildPagination(widget,4);
            goToPage(wi, 1);
            wi.$widget.find('[data-action="next"]').on('click', function(){
                goToPage(wi, getCurrentPage(wi) + 1);
            });
            wi.$widget.find('[data-action="prev"]').on('click', function(){
                goToPage(wi, getCurrentPage(wi) - 1);
            });
            wi.$widget.find('.seu-pagin-item button[data-page]').on('click', function(){
                var target = $(this).attr('data-page'); 
                goToPage(wi, target);
            })
        });
    });
}
