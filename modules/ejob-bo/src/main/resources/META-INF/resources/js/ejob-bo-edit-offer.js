var emailAutoFields = undefined; // Référence au champ email répétable (setté plus loin)
var gradeRangeAutoFields = undefined; // Référence au bloc grade range répétable (setté plus loin)


var namespace = "_eu_strasbourg_portlet_ejob_EjobBOPortlet_";

//Initialisation de l'affichage des champs en fonction du type de recrutement,
// du type d'export, des filières et catégories et des directions
var publicationId = document.getElementById(namespace + "publicationId");
publicationId.disabled = true;
var typeRecrutements = document.getElementById(namespace + "ejobTypeRecrutement");
var typeExportTotem = document.getElementById("typeExportTotem");
var postNumber = document.getElementById(namespace + "postNumber");
var jobCreationDescription = document.querySelectorAll('[for=' + namespace + 'jobCreationDescription]')[0];
var startDate = document.querySelectorAll('[for=' + namespace + 'startDate2]')[0];
var motif = document.querySelectorAll('[for=' + namespace + 'motif]')[0];
var permanentDescription = document.querySelectorAll('[for=' + namespace + 'permanentDescription]')[0];
var directions = document.getElementById(namespace + "ejobDirection");
var services = document.getElementById(namespace + "ejobService");
// gestion de l'affichage des sélecteurs de service
directions.onchange = function(){
    services.parentNode.style.display = "block";
    initialiseService();
    services.options[0].selected = 'selected';
}
if(directions.value == "")
    services.parentNode.style.display='none';
else
    initialiseService();
function initialiseService(){
    Array.prototype.forEach.call(services.children, function(child, i){
        if(child.attributes["data-direction-id"] != undefined){
            if(child.attributes["data-direction-id"].value == directions.value ){
                child.style.display="block";
            }
            else{
                child.style.display="none";
            }
        }
    });
}
var blockFullTime = document.getElementById("blockFullTime");
// gestion de l'affichage de la description en fonction du temps du contrat
var fullTime = document.querySelectorAll('input[name=' + namespace + 'isFullTime]');
var fullTimeDescription = document.querySelectorAll('[for=' + namespace + 'fullTimeDescription]')[0];
if(fullTime[1].checked)
    fullTimeDescription.parentNode.style.display="none";
function changeHandlerFullTime(event) {
    if(fullTime[0].checked)
        fullTimeDescription.parentNode.style.display="block";
    else{
        document.getElementById(namespace + "fullTimeDescription").value = "";
        fullTimeDescription.parentNode.style.display="none";
    }
}
Array.prototype.forEach.call(fullTime, function(element) {
   element.addEventListener('change', changeHandlerFullTime);
});
var gradeRangeFields = document.getElementById("grade-range-fields");
Array.prototype.forEach.call(gradeRangeFields.getElementsByClassName("lfr-form-row"), function(element) {
    if(element.querySelectorAll('[id^=' + namespace + 'ejobCategory]')[0].value != ""){
        initialiseFilieres(element.querySelectorAll('[id^=' + namespace + 'ejobCategory]')[0]);
        element.querySelectorAll('[id^=' + namespace + 'ejobFiliere]')[0].parentNode.style.display = "block";
    }
    if(element.querySelectorAll('[id^=' + namespace + 'ejobFiliere]')[0].value != ""){
        initialiseGrades(element.querySelectorAll('[id^=' + namespace + 'ejobFiliere]')[0]);
        element.querySelectorAll('[id^=' + namespace + 'ejobGradeMin]')[0].parentNode.style.display = "block";
        element.querySelectorAll('[id^=' + namespace + 'ejobGradeMax]')[0].parentNode.style.display = "block";
    }
});
var avantages = document.getElementById('avantages');
var ejobContact = document.getElementById(namespace + "ejobContact");
// gestion de l'affichage des champs en fonction du type de recrutement
function initialise(){
    var typeRecrutementsValue = typeRecrutements.children[typeRecrutements.selectedIndex].text;
    if(typeRecrutementsValue == "Stage" || typeRecrutementsValue == "Apprentissage"){
        document.getElementById(namespace + "jobCreationDescription").value = "";
        jobCreationDescription.parentNode.style.display="none";
        document.getElementById(namespace + "startDate2").value = "";
        startDate.parentNode.style.display="none";
        document.getElementById(namespace + "motif").value = "";
        motif.parentNode.style.display="none";
        document.getElementById(namespace + "permanentDescription").value = Liferay.Language.get('ejob-permanent-description-value');
        permanentDescription.parentNode.style.display="none";
        // réinitialise en temps complet par défaut
        fullTime[0].checked = "true";
        document.getElementById(namespace + "fullTimeDescription").value = "";
        changeHandlerFullTime();
        blockFullTime.style.display="none";
        if(gradeRangeAutoFields != null)
            gradeRangeAutoFields.reset();
        gradeRangeFields.style.display="none";
        if(CKEDITOR.instances[namespace + "avantagesEditor"] != undefined)
            CKEDITOR.instances[namespace + "avantagesEditor"].setData("");
        avantages.style.display="none";
        ejobContact.options[0].selected = 'selected';
        ejobContact.parentNode.style.display="none";
    }else{
        jobCreationDescription.parentNode.style.display="block";
        if(publicationId.value == '')
            document.getElementById(namespace + "startDate2").value = new Date().toLocaleString().split(" à ")[0];
        startDate.parentNode.style.display="block";
        motif.parentNode.style.display="block";
        blockFullTime.style.display="block";
        if (typeRecrutementsValue == "Permanent"){
                permanentDescription.parentNode.style.display="block";
        }else{
                document.getElementById(namespace + "permanentDescription").value = Liferay.Language.get('eu.offer-job-permanent-description-value');
                permanentDescription.parentNode.style.display="none";
        }
        if (typeRecrutementsValue == "Vacataire"){
            gradeRangeAutoFields.reset();
            gradeRangeFields.style.display="none";
        }else{
            gradeRangeFields.style.display="block";
        }
        avantages.style.display="block";
        ejobContact.parentNode.style.display="block";
    }
}
typeRecrutements.addEventListener('change', initialise);
initialise();

//Gestion des validateurs
var allValidate = true;
var submitButton = document.getElementsByClassName('saveButton')[0];
submitButton.onclick = function(event){
    allValidate = true;

    var typeRecrutementsValue = typeRecrutements.children[typeRecrutements.selectedIndex].text;
    var isStage = typeRecrutementsValue === "Stage";
    var isApprentissage = typeRecrutementsValue === "Apprentissage";
    var isVacataire = typeRecrutementsValue === "Vacataire";
    if (!isStage && !isApprentissage && !isVacataire) {
        setFiliereConditionalValidators(event);
    }

    if (!allValidate) {
       event.preventDefault();
    }
};

//Soumission du formulaire
function submitForm(event) {
	emailAutoFields.save(event.target);
	gradeRangeAutoFields.save(event.target);
	return true;
}

function setFiliereConditionalValidators(event) {
    Array.prototype.forEach.call(gradeRangeFields.getElementsByClassName("lfr-form-row"), function(element) {
        if(!element.classList.contains("hide")){
            offerCategory = element.querySelectorAll('[id^=' + namespace + 'ejobCategory]')[0];
            if (offerCategory.value == "") {
                element.getElementsByClassName('offerCategoryError')[0].style.display = "block";
                offerCategory.closest(".form-group").classList.add('has-error');
                if(allValidate){
                    offerCategory.scrollIntoView();
                    allValidate = false;
                }
            }else{
                element.getElementsByClassName('offerCategoryError')[0].style.display = "none";
                offerCategory.closest(".form-group").classList.remove('has-error');

                offerFiliere = element.querySelectorAll('[id^=' + namespace + 'ejobFiliere]')[0];
                if (offerFiliere.value == "") {
                    element.getElementsByClassName('offerFiliereError')[0].style.display = "block";
                    offerFiliere.closest(".form-group").classList.add('has-error');
                    if(allValidate){
                        offerFiliere.scrollIntoView();
                        allValidate = false;
                    }
                }else{
                    element.getElementsByClassName('offerFiliereError')[0].style.display = "none";
                    offerFiliere.closest(".form-group").classList.remove('has-error');

                    offerGradeMin = element.querySelectorAll('[id^=' + namespace + 'ejobGradeMin]')[0];
                    if (offerGradeMin.value == "") {
                        element.getElementsByClassName('offerGradeMinError')[0].style.display = "block";
                        offerGradeMin.closest(".form-group").classList.add('has-error');
                        if(allValidate){
                            offerGradeMin.scrollIntoView();
                            allValidate = false;
                        }
                    }else{
                        element.getElementsByClassName('offerGradeMinError')[0].style.display = "none";
                        offerGradeMin.closest(".form-group").classList.remove('has-error');
                    }

                    offerGradeMax = element.querySelectorAll('[id^=' + namespace + 'ejobGradeMax]')[0];
                    if (offerGradeMax.value == "") {
                        element.getElementsByClassName('offerGradeMaxError')[0].style.display = "block";
                        offerGradeMax.closest(".form-group").classList.add('has-error');
                        if(allValidate){
                            offerGradeMax.scrollIntoView();
                            allValidate = false;
                        }
                    }else{
                        element.getElementsByClassName('offerGradeMaxError')[0].style.display = "none";
                        offerGradeMax.closest(".form-group").classList.remove('has-error');
                    }

                    if (offerGradeMax.selectedOptions[0].attributes["data-filiere-category-id"].value != offerGradeMin.selectedOptions[0].attributes["data-filiere-category-id"].value) {
                        element.getElementsByClassName('offerGradeMinMaxError')[0].style.display = "block";
                        offerGradeMin.closest(".form-group").classList.add('has-error');
                        offerGradeMax.closest(".form-group").classList.add('has-error');
                        if(allValidate){
                            offerGradeMin.scrollIntoView();
                            allValidate = false;
                        }
                    }else{
                        element.getElementsByClassName('offerGradeMinMaxError')[0].style.display = "none";
                        offerGradeMin.closest(".form-group").classList.remove('has-error');
                        offerGradeMax.closest(".form-group").classList.remove('has-error');
                    }
                }
            }
        }
    });
}

// gestion de l'affichage des sélecteurs de filières
function initialiseFilieres(element){
    Array.prototype.forEach.call(element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobFiliere]')[0].children, function(child, i){
        if(child.attributes["data-categories"] != undefined){
            if(child.attributes["data-categories"].value.includes(element.selectedOptions[0].attributes["data-categorie"].value)){
                child.style.display="block";
            }else{
                child.style.display="none";
            }
        }
    });
}
function changeHandlerEjobCategory(element) {
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobFiliere]')[0].parentNode.style.display = "block";
    initialiseFilieres(element);
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobFiliere]')[0].options[0].selected = 'selected';

    // cache les grades
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMin]')[0].parentNode.style.display = "none";
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMin]')[0].options[0].selected = 'selected';
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMax]')[0].parentNode.style.display = "none";
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMax]')[0].options[0].selected = 'selected';
}

// gestion de l'affichage des sélecteurs de grades
function initialiseGrades(element){
    Array.prototype.forEach.call(element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMin]')[0].children, function(child, i){
        if(child.attributes["data-categories"] != undefined){
            if(child.attributes["data-categories"].value.includes(element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobCategory]')[0].selectedOptions[0].attributes["data-categorie"].value)
                && child.attributes["data-filiere-id"].value == element.value){
                child.style.display="block";
            }else{
                child.style.display="none";
            }
        }
    });
    Array.prototype.forEach.call(element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMax]')[0].children, function(child, i){
        if(child.attributes["data-categories"] != undefined){
            if(child.attributes["data-categories"].value.includes(element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobCategory]')[0].selectedOptions[0].attributes["data-categorie"].value)
                && child.attributes["data-filiere-id"].value == element.value){
                child.style.display="block";
            }else{
                child.style.display="none";
            }
        }
    });
}
function changeHandlerEjobFiliere(element) {
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMin]')[0].parentNode.style.display = "block";
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMax]')[0].parentNode.style.display = "block";
    initialiseGrades(element);
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMin]')[0].options[0].selected = 'selected';
    element.closest('.row-fields').querySelectorAll('[id^=' + namespace + 'ejobGradeMax]')[0].options[0].selected = 'selected';
}

// gestion des champs répétables
(function($) {
	// Configuration de l'autofield
	AUI().use('liferay-auto-fields', function(Y) {
        var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
        rules[namespace + 'ejobFiliere0'].required = false;
        rules[namespace + 'ejobCategory0'].required = false;
        rules[namespace + 'ejobGradeMin0'].required = false;
        rules[namespace + 'ejobGradeMax0'].required = false;

		if (!!document.getElementById('email-fields')) {
			// Création de l'autofield
			emailAutoFields = new Liferay.AutoFields({
				contentBox : '#email-fields',
				fieldIndexes : namespace + 'offerEmailsIndexes',
				namespace : namespace,
				url : getOfferEmailRowJSPURL
			}).render();
		}

		if (!!document.getElementById('grade-range-fields')) {
			// Création de l'autofield
			gradeRangeAutoFields = new Liferay.AutoFields({
				contentBox : '#grade-range-fields',
				fieldIndexes : namespace + 'offerGradeRangeIndexes',
				namespace : namespace,
				url : getOfferGradeRangeRowJSPURL,
                on: {
                    'clone': function(event) {
                        var grade_range_fields = document.getElementById('grade-range-fields');
                        var form_row = grade_range_fields.getElementsByClassName('lfr-form-row');
                        var form_row_hide = grade_range_fields.getElementsByClassName('lfr-form-row hide');
                        if(form_row.length - form_row_hide.length >= 3){
                            for (var i = 0 ; i < form_row.length ; i++) {
                                form_row[i].getElementsByClassName('add-row')[0].style.display = "none";
                            }
                        }
                    },
                    'delete': function(event) {
                        var grade_range_fields = document.getElementById('grade-range-fields');
                        var form_row = grade_range_fields.getElementsByClassName('lfr-form-row');
                        var form_row_hide = grade_range_fields.getElementsByClassName('lfr-form-row hide');
                        if(form_row.length - form_row_hide.length < 3){
                            for (var i = 0 ; i < form_row.length ; i++) {
                                form_row[i].getElementsByClassName('add-row')[0].style.display = "inline-block";
                            }
                        }
                    }
                }
			}).render();
            var form_row = gradeRangeFields.getElementsByClassName('lfr-form-row');
            if(form_row.length >= 3){
                for (var i = 0 ; i < form_row.length ; i++) {
                    form_row[i].getElementsByClassName('add-row')[0].style.display = "none";
                }
            }
		}
	});
})(jQuery);