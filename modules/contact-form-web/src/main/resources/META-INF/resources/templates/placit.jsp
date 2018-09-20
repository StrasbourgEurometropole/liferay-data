<%@ include file="/contact-form-init.jsp" %>
<div class="container">
    <div id="breadcrumb" class="pro-bloc">
        <span>
            <span><a href="index.html">Accueil</a>
                <span class="breadcrumb_last">Nous contacter</span>
            </span>
        </span>
    </div>
</div>
<div class="container">
    <div class="pro-bloc pro-bloc-texte pro-max-900 aligncenter">
        <h2>Titre de type H2, Introduction</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
            ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
            occaecat cupidatat non proident, sunt in Lorem ipsum dolor.</p>
    </div>
</div>


<div class="pro-bloc-pcs-form pro-form-page-contact">
    <form>
        <div class="container pro-max-900">
            <div class="pro-wrapper">
                <div class="pro-row">
                    <div class="form-group form-half">
                        <label for="nom">Nom <span class="required">*</span></label>
                        <input type="text" class="form-control" id="nom" placeholder="Dupond"/>
                    </div>
                    <div class="form-group form-half">
                        <label for="prenom">Prénom <span class="required">*</span></label>
                        <input type="text" class="form-control" id="prenom" placeholder="Jean"/>
                    </div>
                </div>
                <div class="pro-row">
                    <div class="form-group form-half">
                        <label for="email">Adresse mail <span class="required">*</span></label>
                        <input type="email" class="form-control" id="email" placeholder="jean.dupond@gmail.com">
                    </div>
                    <div class="form-group form-half">
                        <label for="tel">Téléphone <span class="required">*</span></label>
                        <input type="text" class="form-control" id="tel"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tel">Objet <span class="required">*</span></label>
                    <input type="text" class="form-control" id="objet"/>
                </div>
                <div class="form-group">
                    <label for="message">Message <span>*</span></label>
                    <textarea id="message" class="form-control" rows="12"></textarea>
                </div>
            </div>
        </div>

        <div class="pro-optin form-checkbox">
            <div class="container pro-max-900">
                <input type="checkbox" id="optin-2" value="optin">
                <label for="optin-2">Je consens XXXX (Conditions CNIL à préciser)</label>
            </div>
        </div>
        <div class="pro-form-submit">
            <button type="submit" class="btn btn-default">Envoyer le message</button>
        </div>
    </form>
</div>