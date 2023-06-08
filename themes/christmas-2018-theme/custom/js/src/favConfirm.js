function destroyPopin(){
    $('#favConfirm').remove().off('clickfavConfirm');
    $('html').off('click.favconfirm').removeClass('overlayed');
}
function createPopin(message, agree, deny, agreeLabel, denyLabel){
    var template = '<div id="favConfirm"> \
        <div class="favMessage">##favMessage##</div> \
        <div class="favActions"> \
            <button class="btn-square--bordered--core deny"><span class="flexbox"><span class="btn-text">##denyLabel##</span><span class="btn-arrow"></span></span></button> \
            <button class="btn-square--filled--second confirm"><span class="flexbox"><span class="btn-text">##agreeLabel##</span><span class="btn-arrow"></span></span></button> \
        </div> \
    </div>';

    template = template.replace('##favMessage##', message);
    if (agreeLabel) {
        template = template.replace('##denyLabel##', denyLabel);
    }
    if (denyLabel) {
        template = template.replace('##agreeLabel##', agreeLabel);
    }
    $('body').append(template);
    $('html').addClass('overlayed');


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