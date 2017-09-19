<div class="do-not-miss portlet"> 
  <div class="asset-header-image-page"> 
    <div> 
      <span class="title-with-picto-span last-images">${title.getData()}</span>
    </div> 
    <div class="asset-header-div asset-header-image-div results-grid">  
    
      <div class="asset-header-div asset-header-image-div results-grid" style="padding: 10px 5px 5px; height: auto; width: auto; border: 1px solid #e6f4ee;">
        <div class="column-image">
          <table>
            <tbody>
              <tr>
                <td>
                  <a class="teasing" href="${link.getData()}" title="${title.getData()}">
                  <img src="${image.getData()}" alt="${image.getAttribute('alt')}" style="height: auto; width: 100%; margin: auto;">
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="column-footer"> 
          <div class="element-title"> 
            <a class="" href="${link.getData()}">${text.getData()}</a> 
          </div> 
          <div class="column-bottom button-pas-manquer" style="height: 35px;"> 
            <a href="${link.getData()}" title="${buttonTitle.getData()}" class="image-link btn-more" style="">${buttonTitle.getData()}</a> 
            <div class="clearer">&nbsp;</div> 
          </div> 
        </div> 
        <div class="clearer">&nbsp;</div> 
      </div> 
    </div> 
  </div>
</div>