<div class="rubrique">
<div class="container content">
  <h1>${name.getData()}</h1>
  <p class="hat">${description.getData()}</p>
</div>

<div id="grid">
  <ul class="grid-list">
    <#if childName.getSiblings()?has_content>
      <#list childName.getSiblings() as child>
          <li class="grid-item">
            <a href="${child.link.data}">
              <div class="grid-item-visu" style="background-image: url(${child.image.getData()});">
              </div>
              <div class="grid-item-text">
                <span>${child.data}</span>
              </div>
            </a>
          </li>
      </#list>
    </#if>
  </ul>
</div>
</div>