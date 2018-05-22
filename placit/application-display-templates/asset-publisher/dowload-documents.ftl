<!-- DOCUMENTS A TELECHARGER -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<section class="container">
    <div class="pro-bloc pro-bloc-texte pro-bloc-telechargements">
        <h3>Documents à télécharger</h3>
        <div class="row">

            <#if entries?has_content>

                <!-- Parcours des entites de l'asset publisher -->
                <#list entries as curEntry>

                    <#assign file = curEntry.getAssetRenderer().getAssetObject() />
                    <#assign fileUrl = FileEntryHelper.getFileEntryURL(file.getFileEntryId()) >

                    <div class="col-md-4 col-sm-6">
                        <a target="_blank" href="${fileUrl}" download title="${file.getTitle()}">
                            <span class="pro-filename">${file.getTitle()}</span>
                            <span class="pro-poids">Poids ${FileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale)}</span>
                        </a>
                    </div>
                </#list>

            <#else>
                Aucun document à partager pour le moment
            </#if>

        </div>
    </div>
</section>