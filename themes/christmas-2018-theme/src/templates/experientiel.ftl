<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
<#assign listTypeNoel = assetVocabularyHelper.getSortedCategories("type noel", themeDisplay.scopeGroupId) />

<#assign listTauxNoel = assetVocabularyHelper.getSortedCategories("taux noel", themeDisplay.scopeGroupId) />

<#assign listDureeNoel = assetVocabularyHelper.getSortedCategories("duree noel", themeDisplay.scopeGroupId) />




<div class="mns-page-experientielle mns-vheight">
    <!-- Launcher -->
    <div class="mns-moteur-launch" style="display: block;">
        <figure>
            <img src="/o/christmas-2018-theme//images/bg-content-std1.jpg" width="800" height="800" alt="Image Marché de Noël"/>
        </figure>
        <div class="mns-caption">
            <h1><@liferay_ui.message key='ready-for' /> <span><@liferay_ui.message key='magic' /></span></h1>
            <a href="#step1" class="mns-btn"><@liferay_ui.message key='let-s-go' /></a>
        </div>
    </div>


    <!-- Wrapper questions -->
    <div class="mns-moteur-wrapper" style="display: block;">
        <div class="mns-small-grid mns-back-homepage"><a href="/accueil" class="link"><@liferay_ui.message key='back-to-home' /></a></div>
        <h2 data-type="question-label-wrapper"><@liferay_ui.message key='your-journey-more-like' /></h2>

        <div class="mns-question-wrapper">
            <form action="${homeURL}recherche-experience" method="POST" id="mns-moteur_experientiel">

                <!-- Question 1 -->
                <div class="reponse rep_3 mns-anim" id="question_1">
                    <span class="hide" data-type="question-label" data-num_question="1"><@liferay_ui.message key='your-journey-more-like' /></span>
                    <!-- Images -->
                    <div class="label-wrapper">
                        <label class="label" for="rep_1_1">
                            <span class="fit-cover"><img src="/o/christmas-2018-theme//images/card-exp-1.jpg" width="240" height="240" alt="Photo"/></span>
                        </label>
                        <label class="label" for="rep_1_2">
                            <span class="fit-cover"><img src="/o/christmas-2018-theme//images/card-exp-2.jpg" width="240" height="240" alt="Photo"/></span>
                        </label>
                        <label class="label" for="rep_1_3">
                            <span class="fit-cover"><img src="/o/christmas-2018-theme//images/card-exp-3.jpg" width="240" height="240" alt="Photo"/></span>
                        </label>
                    </div>
                    <!-- Wrapper Arrow -->
                    <div class="mns-wrapper-arrows mns-small-grid">
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-left.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-left-2x.png 2x" width="116" height="100" alt="Flèche gauche" class="mns-fleche-left"/>
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-center.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-center-2x.png 2x" width="13" height="42" alt="Flèche centre"
                             class="mns-fleche-center"/>
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-right.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-right-2x.png 2x" width="19" height="30" alt="Flèche droite" class="mns-fleche-right"/>
                    </div>
                    <!-- Barre de choix -->
                    <div class="inputs-wrapper">
                        <#assign i=0>
                        <#list listTypeNoel as type>
                            <#assign i++>
                            <div class="input-wrapper">
                            <label for="rep_1_${i}">
                                <input type="radio" name="question_1" id="rep_1_${i}" value="${type.getCategoryId()}" checked="checked">
                                <span></span>
                            </label>
                        </div>
                        </#list>
                    </div>
                    <div class="selected-answer" data-question="question_1">
                        <#assign i=0>
                        <#list listTypeNoel as type>
                            <#assign i++>
                            <label for="rep_1_${i}">${type.getName()}</label>
                        </#list>
                    </div>
                </div>


                <!-- Question 2 -->
                <div class="reponse rep_2" id="question_2" style="display: none;">
                    <span class="hide" data-type="question-label" data-num_question="2"><@liferay_ui.message key='your-journey-more' /></span>
                    <!-- Images -->
                    <div class="label-wrapper">
                        <label class="label" for="rep_2_1">
                            <span class="fit-cover"><img src="/o/christmas-2018-theme//images/card-exp-1.jpg" width="330" height="240" alt="Photo"/></span>
                        </label>
                        <label class="label" for="rep_2_2">
                            <span class="fit-cover"><img src="/o/christmas-2018-theme//images/card-exp-2.jpg" width="330" height="240" alt="Photo"/></span>
                        </label>
                    </div>
                    <!-- Wrapper Arrow -->
                    <div class="mns-wrapper-arrows mns-small-grid">
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-left.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-left-2x.png 2x" width="116" height="100" alt="Flèche gauche" class="mns-fleche-left"/>
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-right.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-right-2x.png 2x" width="19" height="30" alt="Flèche droite" class="mns-fleche-right"/>
                    </div>
                    <!-- Barre de choix -->
                    <div class="inputs-wrapper">
                        <#assign i=0>
                        <#list listTauxNoel as taux>
                            <#assign i++>
                            <div class="input-wrapper">
                            <label for="rep_1_${i}">
                                <input type="radio" name="question_2" id="rep_2_${i}" value="${taux.getCategoryId()}" checked="checked">
                                <span></span>
                            </label>
                        </div>
                        </#list>
                    </div>
                    <div class="selected-answer" data-question="question_2">
                        <#assign i=0>
                        <#list listTauxNoel as taux>
                            <#assign i++>
                            <label for="rep_2_${i}">${taux.getName()}</label>
                        </#list>
                    </div>
                </div>


                <!-- Question 3 -->
                <div class="reponse rep_3_text" id="question_3" style="display: none;">
                    <span class="hide" data-type="question-label" data-num_question="3"><@liferay_ui.message key='your-journey-is' /></span>
                    <!-- Images -->
                    <div class="label-wrapper">
                        <label class="label" for="rep_3_1"><span>1</span><span class="mns-day"><@liferay_ui.message key='day' /></span></label>
                        <label class="label" for="rep_3_2"><span>2</span><span class="mns-day"><@liferay_ui.message key='days' /></span></label>
                        <label class="label" for="rep_3_3"><span>3</span><span class="mns-day"><@liferay_ui.message key='days' /></span></label>
                    </div>
                    <!-- Wrapper Arrow -->
                    <div class="mns-wrapper-arrows mns-small-grid">
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-left.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-left-2x.png 2x" width="116" height="100" alt="Flèche gauche" class="mns-fleche-left"/>
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-center.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-center-2x.png 2x" width="13" height="42" alt="Flèche centre"
                             class="mns-fleche-center"/>
                        <img src="/o/christmas-2018-theme//images/moteur/fleche-right.png" srcset="/o/christmas-2018-theme//images/moteur/fleche-right-2x.png 2x" width="19" height="30" alt="Flèche droite" class="mns-fleche-right"/>
                    </div>
                    <!-- Barre de choix -->
                    <div class="inputs-wrapper">
                        <#assign i=0>
                        <#list listDureeNoel as duree>
                            <#assign i++>
                            <div class="input-wrapper">
                            <label for="rep_1_${i}">
                                <input type="radio" name="question_3" id="rep_3_${i}" value="${duree.getCategoryId()}" checked="checked">
                                <span></span>
                            </label>
                        </div>
                        </#list>
                    </div>
                    <div class="selected-answer" data-question="question_3">
                         <#assign i=0>
                        <#list listDureeNoel as duree>
                            <#assign i++>
                            <label for="rep_3_${i}">${duree.getName()}</label>
                        </#list>
                    </div>
                </div>
            </form>
        </div>

        <div class="mns-questions-pagination mns-small-grid">
            <a href="#Prev" class="mns-prev"><@liferay_ui.message key='eu.previous' /></a>
            <a href="#Next" class="mns-next mns-btn"><@liferay_ui.message key='eu.next' /></a>
        </div>
        <div class="mns-questions-dots mns-small-grid">
            <ul>
                <li><a href="#step1" class="current" data-num_question="1">1</a></li>
                <li><a href="#step2" data-num_question="2">2</a></li>
                <li><a href="#step3" data-num_question="3">3</a></li>
            </ul>
        </div>
    </div>


    <!-- Images de décoration -->
    <img src="/o/christmas-2018-theme//images/moteur/deco-pastilles.png" srcset="/o/christmas-2018-theme//images/moteur/deco-pastilles-2x.png 2x" width="192" height="353" alt="Décoration de pastilles" class="msn-deco-pastilles"/>
    <img src="/o/christmas-2018-theme//images/moteur/deco-paquets.png" srcset="/o/christmas-2018-theme//images/moteur/deco-paquets-2x.png 2x" width="479" height="369" alt="Décoration de cadeaux" class="mns-deco-paquets"/>
    <img src="/o/christmas-2018-theme//images/moteur/deco-scotch.png" srcset="/o/christmas-2018-theme//images/moteur/deco-scotch-2x.png 2x" width="44" height="147" alt="Décoration scotch" class="mns-deco-scotch"/>
    <img src="/o/christmas-2018-theme//images/moteur/deco-star.png" srcset="/o/christmas-2018-theme//images/moteur/deco-star-2x.png 2x" width="180" height="213" alt="Décoration d'étoile" class="mns-deco-star"/>

</div>