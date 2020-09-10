function createPopinOK(message){
    var template = '<div id="favConfirm"> \
        <div class="favMessage">##favMessage##</div> \
        <div class="favActions"> \
            <button class="seu-btn-square--filled--second confirm"><span class="seu-flexbox"><span class="seu-btn-text">OK</span><span class="seu-btn-arrow"></span></span></button> \
        </div> \
    </div>';

    template = template.replace('##favMessage##', message);
    $('body').append(template);
    $('.seu').addClass('overlayed');


    $('#favConfirm .confirm').on('click.favConfirm', function(e){
        destroyPopin();
    });
}