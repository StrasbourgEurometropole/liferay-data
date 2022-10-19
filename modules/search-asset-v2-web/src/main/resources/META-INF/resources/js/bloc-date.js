$(document).ready(function(){
    var namespace = '_eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet_';

    if($('#today').length){
        $('#today').on('click', function(){
            var today = new Date();

            // changement de valeur des champs date
            $('#'+namespace+'fromDate').val(today.getDate() + "/" + ((today.getMonth() + 1) > 9?(today.getMonth() + 1):"0"+(today.getMonth() + 1)) + "/" + today.getFullYear());
            $($('input[name="'+namespace+'fromDay"]')[0]).val(today.getDate());
            $($('input[name="'+namespace+'fromMonth"]')[0]).val(today.getMonth());
            $($('input[name="'+namespace+'fromYear"]')[0]).val(today.getFullYear());
            $('#'+namespace+'toDate').val(today.getDate() + "/" + ((today.getMonth() + 1) > 9?(today.getMonth() + 1):"0"+(today.getMonth() + 1)) + "/" + today.getFullYear());
            $($('input[name="'+namespace+'toDay"]')[0]).val(today.getDate());
            $($('input[name="'+namespace+'toMonth"]')[0]).val(today.getMonth());
            $($('input[name="'+namespace+'toYear"]')[0]).val(today.getFullYear());

            // désactivation des boutons demain et week-end
            $('#tomorrow').removeClass("active");
            $('#week-end').removeClass("active");

            // activation du bouton aujourd'hui
            $(this).addClass("active");

            // garde le bouton selectionné
            $($('input[name="'+namespace+'dateSelected"]')[0]).val("today");

            // lance la recherche
            $('#'+namespace+'fm').submit();
        });
    }

    if($('#tomorrow').length){
        $('#tomorrow').on('click', function(){
            var tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);

            // changement de valeur des champs date
            $('#'+namespace+'fromDate').val(tomorrow.getDate() + "/" + ((tomorrow.getMonth() + 1) > 9?(tomorrow.getMonth() + 1):"0"+(tomorrow.getMonth() + 1)) + "/" + tomorrow.getFullYear());
            $($('input[name="'+namespace+'fromDay"]')[0]).val(tomorrow.getDate());
            $($('input[name="'+namespace+'fromMonth"]')[0]).val(tomorrow.getMonth());
            $($('input[name="'+namespace+'fromYear"]')[0]).val(tomorrow.getFullYear());
            $('#'+namespace+'toDate').val(tomorrow.getDate() + "/" + ((tomorrow.getMonth() + 1) > 9?(tomorrow.getMonth() + 1):"0"+(tomorrow.getMonth() + 1)) + "/" + tomorrow.getFullYear());
            $($('input[name="'+namespace+'toDay"]')[0]).val(tomorrow.getDate());
            $($('input[name="'+namespace+'toMonth"]')[0]).val(tomorrow.getMonth());
            $($('input[name="'+namespace+'toYear"]')[0]).val(tomorrow.getFullYear());

            // désactivation des boutons aujourd'hui et week-end
            $('#today').removeClass("active");
            $('#week-end').removeClass("active");

            // activation du bouton demain
            $(this).addClass("active");

            // garde le bouton selectionné
            $($('input[name="'+namespace+'dateSelected"]')[0]).val("tomorrow");

            // lance la recherche
            $('#'+namespace+'fm').submit();
        });
    }

    if($('#week-end').length){
        $('#week-end').on('click', function(){
            var saturday = new Date();
            saturday.setDate(saturday.getDate() - saturday.getDay() + 6);
            var sunday = new Date();
            sunday.setDate(sunday.getDate() - sunday.getDay() + 7);

            // changement de valeur des champs date
            $('#'+namespace+'fromDate').val(saturday.getDate() + "/" + ((saturday.getMonth() + 1) > 9?(saturday.getMonth() + 1):"0"+(saturday.getMonth() + 1)) + "/" + saturday.getFullYear());
            $($('input[name="'+namespace+'fromDay"]')[0]).val(saturday.getDate());
            $($('input[name="'+namespace+'fromMonth"]')[0]).val(saturday.getMonth());
            $($('input[name="'+namespace+'fromYear"]')[0]).val(saturday.getFullYear());
            $('#'+namespace+'toDate').val(sunday.getDate() + "/" + ((sunday.getMonth() + 1) > 9?(sunday.getMonth() + 1) : "0" +(sunday.getMonth() + 1)) + "/" + sunday.getFullYear());
            $($('input[name="'+namespace+'toDay"]')[0]).val(sunday.getDate());
            $($('input[name="'+namespace+'toMonth"]')[0]).val(sunday.getMonth());
            $($('input[name="'+namespace+'toYear"]')[0]).val(sunday.getFullYear());

            // désactivation des boutons aujourd'hui et demain
            $('#today').removeClass("active");
            $('#tomorrow').removeClass("active");

            // activation du bouton week-end
            $(this).addClass("active");

            // garde le bouton selectionné
            $($('input[name="'+namespace+'dateSelected"]')[0]).val("week-end");

            // lance la recherche
            $('#'+namespace+'fm').submit();
        });
    }

    if($('#'+namespace+'fromDate').length){
        $('#'+namespace+'fromDate').on('change', function(){
            // désactivation des boutons
            $('#today').removeClass("active");
            $('#tomorrow').removeClass("active");
            $('#week-end').removeClass("active");
        });
    }

    if($('#'+namespace+'toDate').length){
        $('#'+namespace+'toDate').on('change', function(){
            // désactivation des boutons
            $('#today').removeClass("active");
            $('#tomorrow').removeClass("active");
            $('#week-end').removeClass("active");
        });
    }
});

