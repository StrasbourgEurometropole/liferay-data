<!-- BLOCLES INSTANCES -->

<section class="pro-bloc-large" style="margin-bottom : 80px">
    <figure class="fit-cover">
        <img src="${image.getData()}" width="1600" height="580" alt="${image.getAttribute("alt")}"/>
    </figure>
    <div class="container caption">
        <h2>${title.getData()}</h2>
        ${text.getData()}
        <ul>
            <#list linkLabel.getSiblings() as cur_link>
                <li><a 
                        <#if cur_link.getChild("linkUrl").getData() !="" >
                            href="${cur_link.getChild("linkUrl").getData()}"
                        </#if>
                        title="Lien vers '${cur_link.getData()}'" 
                        tabindex="-1">
                        ${cur_link.getData()}
                    </a>
                </li>
            </#list>
        </ul>
    </div>
</section>