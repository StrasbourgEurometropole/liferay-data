function destroyPopin(){
    $('#favConfirm').remove().off('clickfavConfirm');
    $('.seu').off('click.favconfirm').removeClass('overlayed');
}
function createPopin(message, agree, deny){
    var template = '<div id="favConfirm"> \
        <div class="favMessage">##favMessage##</div> \
        <div class="favActions"> \
            <button class="seu-btn-square--bordered--core deny"><span class="seu-flexbox"><span class="seu-btn-text">Annuler</span><span class="seu-btn-arrow"></span></span></button> \
            <button class="seu-btn-square--filled--second confirm"><span class="seu-flexbox"><span class="seu-btn-text">Valider</span><span class="seu-btn-arrow"></span></span></button> \
        </div> \
    </div>';

    template = template.replace('##favMessage##', message);
    $('body').append(template);
    $('.seu').addClass('overlayed');


    $('#favConfirm .deny').on('click.favConfirm', function(e){
        if(deny !== undefined){
            deny();
        }
        destroyPopin();
    });
    $('#favConfirm .confirm').on('click.favConfirm', function(){
        destroyPopin();
        if(agree !== undefined){
            agree();
        }
    });
}