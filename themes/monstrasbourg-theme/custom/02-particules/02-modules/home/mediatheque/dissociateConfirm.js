function destroyPopinMediatheque(){
    $('#dissociateConfirm').remove().off('clickdissociateConfirm');
    $('.mseu').off('click.dissociateconfirm').removeClass('overlayed');
}
function createPopinMediatheque(message, agree, deny){
    var template = '<div id="dissociateConfirm"> \
        <div class="dissociateMessage">##dissociateMessage##</div> \
        <div class="dissociateActions"> \
            <input type="hidden" id="dissociate" name="<portlet:namespace />dissociate" value="dissocier"> \
            <button class="btn-square--bordered--core deny"><span class="flexbox"><span class="btn-text">Annuler</span><span class="btn-arrow"></span></span></button> \
            <button class="btn-square--filled--second confirm"><span class="flexbox"><span class="btn-text">Valider</span><span class="btn-arrow"></span></span></button> \
        </div> \
    </div>';

    template = template.replace('##dissociateMessage##', message);
    $('body').append(template);
    $('.mseu').addClass('overlayed');


    $('#dissociateConfirm .deny').on('click.dissociateConfirm', function(e){
        if(deny !== undefined){
            deny();
        }
        destroyPopinMediatheque();
    });
    $('#dissociateConfirm .confirm').on('click.dissociateConfirm', function(){
        destroyPopinMediatheque();
        if(agree !== undefined){
            agree();
        }
    });
}