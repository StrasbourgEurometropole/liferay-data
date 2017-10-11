(function ($) { 
    $(document).ready(function(){
        if($('.seu-wi-schedules').length){

            $('.seu-wi-schedules').each(function(index, element){

                var $wi = $(this),
                    $tab_list= $(this).find('.tab-list'),
                    $tabs= $(this).find('.tab-content'),
                    tab_list_html= '<div class="tab-menu-rwd"></div>';

                $tabs.each(function(index, element){
                    var _this = $(this),
                        name = _this.find('h3.hidden').html();
                    $(this).attr('data-tab-index', index);

                    if($tabs.length > 1){ // Si plus d'un tab on crée la liste de tabs
                        if(index ==0){
                            tab_list_html += '<button class="tab-toggle current" data-tab-target="'+index+'">'+name+'</button>';
                        }else{
                            tab_list_html += '<button class="tab-toggle" data-tab-target="'+index+'">'+name+'</button>';
                        }
                    }

                });

                // Créé la liste des tab
                $tab_list.html(tab_list_html);
                // Duplique dans RWD
                $wi.find('.tab-toggle').clone().appendTo($wi.find('.tab-menu-rwd'));

                // Store les tab-toggles
                var $tab_toggles = $(this).find('.tab-toggle');

                // Events
                $tab_list.find('.tab-toggle').on('click', function(){
                    if(!$(this).hasClass('current')){
                        var index = $(this).attr('data-tab-target');
                        $tab_toggles.removeClass('current');
                        $tab_toggles.filter('[data-tab-target="'+index+'"]').addClass('current');
                        $tabs
                            .removeClass('tabbed')
                            .filter('[data-tab-index="'+index+'"]').addClass('tabbed');
                        $wi.find('.tab-menu-rwd').removeClass('opened');
                    }else if(environment == "mobile"){
                        $wi.find('.tab-menu-rwd').toggleClass('opened');
                    }
                });

                // $(document).on('click', function(e){
                //     if(!$(e.target).hasClass('tab-toggle') && !$(e.target).parents('.tab-toggle').length){
                //         $wi.find('.tab-menu-rwd').stop().slideUp();
                //     }
                // })

                // Ouvre le premier tab de base
                $tabs.eq(0).addClass('tabbed');
                
                // Manage RWD
                // function buildRwd(){
                //     if(environment == 'mobile'){
                //         $wi.find('.tab-toggle:not(.current)').appendTo($wi.find('.tab-menu-rwd'));
                //     }else{
                //         var current_index = $wi.find('.tab-toggle.current').attr('data-tab-index'); 
                //         // Insert Current at the right place
                //         $wi.find('.tab-toggle.current').insertAfter($wi.find('.tab-toggle[data-tab-index="'+(current_index-1)+'"]'));
                //         // Get back all tabs inside the tab-list
                //         $wi.find('.tab-toggle').appendTo($tab_list);
                //     }
                // }
                // buildRwd();
                // $(window).resize(function(){
                //     if(environmentChanged){
                //         buildRwd();
                //     }
                // });

            });
        }
    })
}(jQuery));