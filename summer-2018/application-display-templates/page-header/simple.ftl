<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${page.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<header class="mns-header-standard">
    <div class="container">
        <div class="mns-wrapper-bread">
            <div class="mns-breadcrumbs mns-bread-color">
                 <#if !page.ancestors?has_content || page.ancestors?reverse[0].friendlyURL != '/accueil'>
                    <a href="${homeURL}"><@liferay_ui.message key="home" /></a>
                </#if>
                <#list page.ancestors?reverse as ancestor>
                    <a href="${homeURL}${ancestor.friendlyURL?remove_beginning('/')}">${ancestor.getName(locale)}</a>
                </#list>
                <span>${page.getName(locale)}</span>
            </div>
        </div>
    </div>
</header>
<script>
    $('.navbar.mns-nav').addClass('mns-nav-no-header');
    $('.navbar-brand').addClass('hidden');

    // Pour avoir tout le temps le menu sous forme Sticky
    if ((navigator.userAgent).match(/iPad/i) || ((navigator.userAgent).match(/Tablet/i)) && height > width){
        $('body').addClass('ipad');
        $('.mns-nav').addClass("mns-nav-scroll");
        $('.mns-nav').addClass("mns-nav-ipad");
        $('.mns-nav-ipad').addClass('mns-top');

    }else {      
        $('.mns-nav').addClass("mns-nav-scroll");
        $('#layer').addClass('mns-nav-scroll-layer')
    }

    // Il faut rajouter un margin quand on est en haut de page et l'enlever quand on scroll
    $(window).scroll(function(){
        if ($(window).scrollTop() == 0) {
            // Quand on est connecté on a un menu de plus en haut de page, alors on fait 
            //deux margin différents pour que co ou pas on voit le sticky menuj
            if ($(".has-control-menu")[0]){
                 $('.mns-nav').css('margin-top','105px')
            }
            else {
                $('.mns-nav').css('margin-top','40px')
            }
        }
        else{
            if ($(".has-control-menu")[0]){
                 $('.mns-nav').css('margin-top','65px')
            }
            else {
                $('.mns-nav').css('margin-top','0px')
            }
        }
    });
</script>