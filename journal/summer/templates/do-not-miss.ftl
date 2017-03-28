<style>
<!--
.portlet {
  margin: 12px 10px 10px;
}

.element-title a {
  display: block;
  font-size: 13px;
  color: #1a171b;
  text-decoration: none;
  line-height: 1.8em;
  font-weight: bold;
  padding-right: 5px;
  padding-left: 5px;
}


.button-pas-manquer a {
background: #e6f4ee;  color: #268033; float:left; text-transform: none; font-size: 1.3em; font-weight: bold; height: 30px; line-height: 30px; padding: 0 5px; margin: 13px -9px 6px 0;
}

.button-pas-manquer a:focus,
.button-pas-manquer a:hover {
    background: #268033 none repeat scroll 0 0;
    color: #ffffff;
    text-decoration: none;
}

-->
</style>

<div class="portlet"> 
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