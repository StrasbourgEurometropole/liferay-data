<%@ include file="/social-init.jsp" %>

<!-- Section social wall-->
<section id="seu-socials">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-suptitle">Suivez</span>
            <span class="seu-title">#Strasbourg</span>
        </h2>
        <div class="seu-filters">
            <button class="seu-social-filter seu-texted seu-actif" data-category="tous">
                <span class="seu-flexbox">
                    <span class="seu-btn-text">Tous</span>
                </span>
            </button>
            <button class="seu-social-filter seu-fb" data-category="facebook">
                <svg xmlns="http://www.w3.org/2000/svg"  viewbox="0 0 100 100" height="100" width="100"><path d="M74.986 17.86l-9.058.004c-7.102 0-8.477 3.375-8.477 8.328v10.92h16.94l-.004 17.107H57.45v43.89H39.785V54.22h-14.77V37.112h14.77V24.5c0-14.64 8.943-22.613 22.003-22.613l13.2.02V17.86z"/></svg>   
            </button>
            <button class="seu-social-filter seu-twit" data-category="twitter">
                <svg xmlns="http://www.w3.org/2000/svg"  viewbox="0 0 100 100" height="100" width="100"><path d="M98.705 19.707c-.14-.165-.37-.22-.568-.13-2.92 1.294-5.99 2.225-9.153 2.775 3.358-2.526 5.86-6.024 7.142-10.035.062-.192-.002-.402-.16-.527-.157-.125-.376-.14-.55-.038-3.78 2.243-7.877 3.824-12.178 4.7-3.813-3.955-9.135-6.218-14.644-6.218-11.204 0-20.318 9.114-20.318 20.317 0 1.36.13 2.7.39 4-15.516-.95-30.026-8.4-39.893-20.5-.1-.12-.254-.19-.414-.17-.16.01-.31.1-.39.24-1.8 3.09-2.75 6.62-2.75 10.217 0 6.23 2.83 12.053 7.65 15.896-2.48-.3-4.91-1.08-7.09-2.294-.15-.083-.33-.08-.48.003-.15.08-.24.24-.25.41v.26c0 8.94 5.893 16.8 14.28 19.41-2.21.35-4.5.33-6.754-.1-.167-.03-.34.023-.455.15-.113.13-.15.307-.1.47 2.516 7.85 9.504 13.355 17.638 14.04-6.783 4.97-14.803 7.59-23.28 7.59-1.56 0-3.13-.092-4.67-.274-.22-.026-.437.105-.513.317-.075.213.006.45.196.572 9.17 5.88 19.775 8.984 30.666 8.984 35.626 0 56.914-28.94 56.914-56.913 0-.774-.015-1.55-.046-2.322 3.845-2.81 7.14-6.254 9.804-10.237.12-.18.106-.416-.033-.582z"/></svg>
            </button>
            <button class="seu-social-filter seu-daily" data-category="dailymotion">
                <svg xmlns="http://www.w3.org/2000/svg"  fill="' + $svgFillPH + '" width="188" height="245" viewBox="0 15 188.4 245.1"><path d="M97.5 213.1c-26.6 0-45.6-18.7-45.6-43.3 0-23.6 19-43.6 43.7-43.6 24.3 0 43 19 43 44.3 0 24.3-18.7 42.7-41 42.7zm90.9-96.3V15L136.5 25.7v76.8C124.7 87.4 108.3 80.2 87 80.2c-22.3 0-42.3 8.2-58.7 24.3C9.8 122.2 0 144.9 0 170.1c0 27.6 10.5 51.2 30.9 69.2 15.4 13.8 33.8 20.7 55.1 20.7 21 0 36.8-5.6 52.2-20.7v19.7h50.2v-33.4"/></svg>
            </button>
            <button class="seu-social-filter seu-insta" data-category="instagram">
                <svg xmlns="http://www.w3.org/2000/svg" fill="' + $svgFillPH + '"  viewBox="0 0 135.2 135.3"><path d="M97.9 0H37.3C16.7 0 0 16.7 0 37.3v60.6c0 20.6 16.7 37.3 37.3 37.3h60.6c20.6 0 37.3-16.7 37.3-37.3V37.3C135.2 16.7 118.5 0 97.9 0L97.9 0zM123.2 97.9c0 14-11.4 25.3-25.3 25.3H37.3c-14 0-25.3-11.4-25.3-25.3V37.3C12 23.4 23.4 12 37.3 12h60.6c14 0 25.3 11.4 25.3 25.3V97.9zM123.2 97.9"/><path d="M67.6 32.8c-19.2 0-34.9 15.6-34.9 34.8 0 19.2 15.6 34.9 34.9 34.9 19.2 0 34.9-15.6 34.9-34.9C102.5 48.4 86.8 32.8 67.6 32.8L67.6 32.8zM67.6 90.5c-12.6 0-22.9-10.2-22.9-22.8C44.8 55 55 44.8 67.6 44.8S90.5 55 90.5 67.6C90.5 80.2 80.2 90.5 67.6 90.5L67.6 90.5zM67.6 90.5"/><path d="M103.9 22.6c-2.3 0-4.6 0.9-6.2 2.6 -1.6 1.6-2.6 3.9-2.6 6.2 0 2.3 0.9 4.6 2.6 6.2 1.6 1.6 3.9 2.6 6.2 2.6 2.3 0 4.6-0.9 6.2-2.6 1.6-1.6 2.6-3.9 2.6-6.2 0-2.3-0.9-4.6-2.6-6.2C108.5 23.5 106.3 22.6 103.9 22.6L103.9 22.6zM103.9 22.6"/></svg>
            </button>
        </div>
        <div class="seu-no-js-links">
            <a href="#"  title="Facebook">Sur Facebook</a>
            <a href="#" title="Twitter">Sur Twitter</a>
            <a href="#" title="Instagram">Sur Instagram</a>
            <a href="#" title="Dailymotion">Sur Dailymotion</a>
        </div>
        <div class="seu-slider-social-container">
            <div class="seu-slider-overflow">
                <div class="seu-slider">
                </div>
            </div>
            <div class="owl-nav">
                <button class="seu-owl-prev">
                    <span class="seu-picto"></span>
                </button>
                <button class="seu-owl-next">
                    <span class="seu-picto"></span>
                </button>
            </div>
        </div>
        <script type="text/javascript">
            /* Exemple de flux pour populer le slider socials (voir script social.js)
                Chaque donner viendra remplacer un placeholder prévu dans les templates ci dessous
                Pour bien fonctionner le flux doit faire figurer les remontées dans l'ordre où elles doivent apparaître en front
                Pour les dates, 3 possibilié :
                    *date unique: "le 24.06", renseigner date_end + date_prefix (peut être automatiquement "le")
                    *date "à partir de": reseigner data_end + date_prefix (peut être automatiquement "à partir du")
                    *date interval "du X au X" : renseigner date_start, date_end,  date_prefix (peut être automatiquement "du") et date_suffix (peut être automatiquement "au")
                Important: Laisser également les champs vides présent dans le json, sinon les placeholders ne seront pas remplacés
            */
            var social_source = [
            	<c:forEach var="post" items="${posts}" varStatus="loopStatus">
           	        {
   	                    "category": "${post.socialMedia.toString()}",
   	                    "content": "${post.jsonEscapedContent}",
   	                    "picture": "${post.imageURL}",
   	                    "date": "<fmt:formatDate pattern="dd \/ MM" value="${post.date}" />",
   	                    "link": "${post.url}"
   	                }
            		<c:if test="${!loopStatus.last}">,
     	        	</c:if>
     			</c:forEach>
            ];
        </script>
        <div id="seu-social-templates">
            <!--Template Social-->
            <div class="seu-item seu-__category__">
                <a href="__link__" class="seu-link" title="__category__">
                    <div class="seu-picture" style="background-image: url(__picture__)">
                    </div>
                    <div class="seu-text">
                        <div class="seu-content dotme" data-dot="7">__content__</div>
                        <div class="seu-text-bottom">
                            <div class="seu-category dotme"><span class="seu-picto-category"></span>__category__</div>
                            <div class="seu-date"><span class="seu-picto-date"></span>__date__</div>
                        </div>
                    </div>
                </a>
            </div>

        </div>
    </div>
</section>