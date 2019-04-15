var thConfig = {

    tabletPortraitBreakpoint: 980,
    tabletPaysageBreakpoint: 1280,

    debug: true,

    // themeUrl: document.getElementById('mainscript').getAttribute('data-themeurl'),

    map: {

        init: {
            maps_class: '.ops-maps:not(.no-autoload):not(.initialized)',
            tileLayerUrl: 'http://tile.jawg.io/jawg-light/{z}/{x}/{y}.png?access-token={accessToken}',
            accessToken: 'qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkki',
            tileLayerOptions: {
                attribution: '',
                maxZoom: 16,
                accessToken: 'qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkki'
            }
        },

        defaultThemes: {
            accessToken: 'qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkkiz',
            style: 'https://tile.jawg.io/jawg-light/{z}/{x}/{y}.png?access-token=qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkki',
            attribution: '',
        }

    },

};
function isTabletPortraitOrSmalller(){
    if(document.body.clientWidth < thConfig.tabletPortraitBreakpoint){
        return true;
    }
    return false;
}
/*
 * jquery-filestyle
 * doc: http://markusslima.github.io/jquery-filestyle/
 * github: https://github.com/markusslima/jquery-filestyle
 *
 * Copyright (c) 2015 Markus Vinicius da Silva Lima
 * Version 1.5.1
 * Licensed under the MIT license.
 */
(function($){var nextId=0;var JFilestyle=function(element,options){this.options=options;this.$elementjFilestyle=[];this.$element=$(element)};JFilestyle.prototype={clear:function(){this.$element.val("");this.$elementjFilestyle.find(":text").val("")},destroy:function(){this.$element.removeAttr("style").removeData("jfilestyle").val("");this.$elementjFilestyle.remove()},disabled:function(value){if(value===true){if(!this.options.disabled){this.$element.attr("disabled","true");this.$elementjFilestyle.find("label").attr("disabled","true");this.options.disabled=true}}else{if(value===false){if(this.options.disabled){this.$element.removeAttr("disabled");this.$elementjFilestyle.find("label").removeAttr("disabled");this.options.disabled=false}}else{return this.options.disabled}}},buttonBefore:function(value){if(value===true){if(!this.options.buttonBefore){this.options.buttonBefore=true;if(this.options.input){this.$elementjFilestyle.remove();this.constructor();this.pushNameFiles()}}}else{if(value===false){if(this.options.buttonBefore){this.options.buttonBefore=false;if(this.options.input){this.$elementjFilestyle.remove();this.constructor();this.pushNameFiles()}}}else{return this.options.buttonBefore}}},input:function(value){if(value===true){if(!this.options.input){this.options.input=true;this.$elementjFilestyle.prepend(this.htmlInput());this.$elementjFilestyle.find(".count-jfilestyle").remove();this.pushNameFiles()}}else{if(value===false){if(this.options.input){this.options.input=false;this.$elementjFilestyle.find(":text").remove();var files=this.pushNameFiles();if(files.length>0){this.$elementjFilestyle.find("label").append(' <span class="count-jfilestyle">'+files.length+"</span>")}}}else{return this.options.input}}},buttonText:function(value){if(value!==undefined){this.options.buttonText=value;this.$elementjFilestyle.find("label span").html(this.options.buttonText)}else{return this.options.buttonText}},inputSize:function(value){if(value!==undefined){this.options.inputSize=value;this.$elementjFilestyle.find(":text").css("width",this.options.inputSize)}else{return this.options.inputSize}},placeholder:function(value){if(value!==undefined){this.options.placeholder=value;this.$elementjFilestyle.find(":text").attr("placeholder",value)}else{return this.options.placeholder}},htmlInput:function(){if(this.options.input){return'<input type="text" style="width:'+this.options.inputSize+'" placeholder="'+this.options.placeholder+'" disabled> '}else{return""}},pushNameFiles:function(){var content="",files=[];if(this.$element[0].files===undefined){files[0]={name:this.$element.value}}else{files=this.$element[0].files}for(var i=0;i<files.length;i++){content+=files[i].name.split("\\").pop()+", "}if(content!==""){this.$elementjFilestyle.find(":text").val(content.replace(/\, $/g,""))}else{this.$elementjFilestyle.find(":text").val("")}return files},constructor:function(){var _self=this,html="",id=_self.$element.attr("id"),$label,files=[];if(id===""||!id){id="jfilestyle-"+nextId;_self.$element.attr({id:id});nextId++}html='<span class="focus-jfilestyle"><label for="'+id+'" '+(_self.options.disabled?'disabled="true"':"")+"><span>"+_self.options.buttonText+"</span></label></span>";if(_self.options.buttonBefore===true){html=html+_self.htmlInput()}else{html=_self.htmlInput()+html}_self.$elementjFilestyle=$('<div class="jfilestyle '+(_self.options.input?"jfilestyle-corner":"")+" "+(this.options.buttonBefore?" jfilestyle-buttonbefore":"")+'">'+html+"</div>");_self.$elementjFilestyle.find(".focus-jfilestyle").attr("tabindex","0").keypress(function(e){if(e.keyCode===13||e.charCode===32){_self.$elementjFilestyle.find("label").click();return false}});_self.$element.css({position:"absolute",clip:"rect(0px 0px 0px 0px)"}).attr("tabindex","-1").after(_self.$elementjFilestyle);if(_self.options.disabled){_self.$element.attr("disabled","true")}_self.$element.change(function(){var files=_self.pushNameFiles();if(_self.options.input==false){if(_self.$elementjFilestyle.find(".count-jfilestyle").length==0){_self.$elementjFilestyle.find("label").append(' <span class="count-jfilestyle">'+files.length+"</span>")}else{if(files.length==0){_self.$elementjFilestyle.find(".count-jfilestyle").remove()}else{_self.$elementjFilestyle.find(".count-jfilestyle").html(files.length)}}}else{_self.$elementjFilestyle.find(".count-jfilestyle").remove()}});if(window.navigator.userAgent.search(/firefox/i)>-1){this.$elementjFilestyle.find("label").click(function(){_self.$element.click();return false})}}};var old=$.fn.jfilestyle;$.fn.jfilestyle=function(option,value){var get="",element=this.each(function(){if($(this).attr("type")==="file"){var $this=$(this),data=$this.data("jfilestyle"),options=$.extend({},$.fn.jfilestyle.defaults,option,typeof option==="object"&&option);if(!data){$this.data("jfilestyle",(data=new JFilestyle(this,options)));data.constructor()}if(typeof option==="string"){get=data[option](value)}}});if(typeof get!==undefined){return get}else{return element}};$.fn.jfilestyle.defaults={buttonText:"Choose file",input:true,disabled:false,buttonBefore:false,inputSize:"200px",placeholder:""};$.fn.jfilestyle.noConflict=function(){$.fn.jfilestyle=old;return this};$(function(){$(".jfilestyle").each(function(){var $this=$(this),options={buttonText:$this.attr("data-buttonText"),input:$this.attr("data-input")==="false"?false:true,disabled:$this.attr("data-disabled")==="true"?true:false,buttonBefore:$this.attr("data-buttonBefore")==="true"?true:false,inputSize:$this.attr("data-inputSize"),placeholder:$this.attr("data-placeholder")};$this.jfilestyle(options)})})})(window.jQuery);
!function(e){"use strict";var t=e(document),s=e(window),i="selectric",l=".sl",n=["a","e","i","o","u","n","c","y"],a=[/[\xE0-\xE5]/g,/[\xE8-\xEB]/g,/[\xEC-\xEF]/g,/[\xF2-\xF6]/g,/[\xF9-\xFC]/g,/[\xF1]/g,/[\xE7]/g,/[\xFD-\xFF]/g],o=function(t,s){var i=this;i.element=t,i.$element=e(t),i.state={multiple:!!i.$element.attr("multiple"),enabled:!1,opened:!1,currValue:-1,selectedIdx:-1,highlightedIdx:-1},i.eventTriggers={open:i.open,close:i.close,destroy:i.destroy,refresh:i.refresh,init:i.init},i.init(s)};o.prototype={utils:{isMobile:function(){return/android|ip(hone|od|ad)/i.test(navigator.userAgent)},escapeRegExp:function(e){return e.replace(/[.*+?^${}()|[\]\\]/g,"\\$&")},replaceDiacritics:function(e){for(var t=a.length;t--;)e=e.toLowerCase().replace(a[t],n[t]);return e},format:function(e){var t=arguments;return(""+e).replace(/\{(?:(\d+)|(\w+))\}/g,function(e,s,i){return i&&t[1]?t[1][i]:t[s]})},nextEnabledItem:function(e,t){for(;e[t=(t+1)%e.length].disabled;);return t},previousEnabledItem:function(e,t){for(;e[t=(t>0?t:e.length)-1].disabled;);return t},toDash:function(e){return e.replace(/([a-z])([A-Z])/g,"$1-$2").toLowerCase()},triggerCallback:function(t,s){var l=s.element,n=s.options["on"+t],a=[l].concat([].slice.call(arguments).slice(1));e.isFunction(n)&&n.apply(l,a),e(l).trigger(i+"-"+this.toDash(t),a)},arrayToClassname:function(t){var s=e.grep(t,function(e){return!!e});return e.trim(s.join(" "))}},init:function(t){var s=this;if(s.options=e.extend(!0,{},e.fn[i].defaults,s.options,t),s.utils.triggerCallback("BeforeInit",s),s.destroy(!0),s.options.disableOnMobile&&s.utils.isMobile())s.disableOnMobile=!0;else{s.classes=s.getClassNames();var l=e("<input/>",{class:s.classes.input,readonly:s.utils.isMobile()}),n=e("<div/>",{class:s.classes.items,tabindex:-1}),a=e("<div/>",{class:s.classes.scroll}),o=e("<div/>",{class:s.classes.prefix,html:s.options.arrowButtonMarkup}),r=e("<span/>",{class:"label"}),p=s.$element.wrap("<div/>").parent().append(o.prepend(r),n,l),u=e("<div/>",{class:s.classes.hideselect});s.elements={input:l,items:n,itemsScroll:a,wrapper:o,label:r,outerWrapper:p},s.options.nativeOnMobile&&s.utils.isMobile()&&(s.elements.input=void 0,u.addClass(s.classes.prefix+"-is-native"),s.$element.on("change",function(){s.refresh()})),s.$element.on(s.eventTriggers).wrap(u),s.originalTabindex=s.$element.prop("tabindex"),s.$element.prop("tabindex",-1),s.populate(),s.activate(),s.utils.triggerCallback("Init",s)}},activate:function(){var e=this,t=e.elements.items.closest(":visible").children(":hidden").addClass(e.classes.tempshow),s=e.$element.width();t.removeClass(e.classes.tempshow),e.utils.triggerCallback("BeforeActivate",e),e.elements.outerWrapper.prop("class",e.utils.arrayToClassname([e.classes.wrapper,e.$element.prop("class").replace(/\S+/g,e.classes.prefix+"-$&"),e.options.responsive?e.classes.responsive:""])),e.options.inheritOriginalWidth&&s>0&&e.elements.outerWrapper.width(s),e.unbindEvents(),e.$element.prop("disabled")?(e.elements.outerWrapper.addClass(e.classes.disabled),e.elements.input&&e.elements.input.prop("disabled",!0)):(e.state.enabled=!0,e.elements.outerWrapper.removeClass(e.classes.disabled),e.$li=e.elements.items.removeAttr("style").find("li"),e.bindEvents()),e.utils.triggerCallback("Activate",e)},getClassNames:function(){var t=this,s=t.options.customClass,i={};return e.each("Input Items Open Disabled TempShow HideSelect Wrapper Focus Hover Responsive Above Scroll Group GroupLabel".split(" "),function(e,l){var n=s.prefix+l;i[l.toLowerCase()]=s.camelCase?n:t.utils.toDash(n)}),i.prefix=s.prefix,i},setLabel:function(){var t=this,s=t.options.labelBuilder;if(t.state.multiple){var i=e.isArray(t.state.currValue)?t.state.currValue:[t.state.currValue];i=0===i.length?[0]:i;var l=e.map(i,function(s){return e.grep(t.lookupItems,function(e){return e.index===s})[0]});l=e.grep(l,function(t){return l.length>1||0===l.length?""!==e.trim(t.value):t}),l=e.map(l,function(i){return e.isFunction(s)?s(i):t.utils.format(s,i)}),t.options.multiple.maxLabelEntries&&(l.length>=t.options.multiple.maxLabelEntries+1?(l=l.slice(0,t.options.multiple.maxLabelEntries)).push(e.isFunction(s)?s({text:"..."}):t.utils.format(s,{text:"..."})):l.slice(l.length-1)),t.elements.label.html(l.join(t.options.multiple.separator))}else{var n=t.lookupItems[t.state.currValue];t.elements.label.html(e.isFunction(s)?s(n):t.utils.format(s,n))}},populate:function(){var t=this,s=t.$element.children(),i=t.$element.find("option"),l=i.filter(":selected"),n=i.index(l),a=0,o=t.state.multiple?[]:0;l.length>1&&t.state.multiple&&(n=[],l.each(function(){n.push(e(this).index())})),t.state.currValue=~n?n:o,t.state.selectedIdx=t.state.currValue,t.state.highlightedIdx=t.state.currValue,t.items=[],t.lookupItems=[],s.length&&(s.each(function(s){var i=e(this);if(i.is("optgroup")){var l={element:i,label:i.prop("label"),groupDisabled:i.prop("disabled"),items:[]};i.children().each(function(s){var i=e(this);l.items[s]=t.getItemData(a,i,l.groupDisabled||i.prop("disabled")),t.lookupItems[a]=l.items[s],a++}),t.items[s]=l}else t.items[s]=t.getItemData(a,i,i.prop("disabled")),t.lookupItems[a]=t.items[s],a++}),t.setLabel(),t.elements.items.append(t.elements.itemsScroll.html(t.getItemsMarkup(t.items))))},getItemData:function(t,s,i){return{index:t,element:s,value:s.val(),className:s.prop("class"),text:s.html(),slug:e.trim(this.utils.replaceDiacritics(s.html())),selected:s.prop("selected"),disabled:i}},getItemsMarkup:function(t){var s=this,i="<ul>";return e.isFunction(s.options.listBuilder)&&s.options.listBuilder&&(t=s.options.listBuilder(t)),e.each(t,function(t,l){void 0!==l.label?(i+=s.utils.format('<ul class="{1}"><li class="{2}">{3}</li>',s.utils.arrayToClassname([s.classes.group,l.groupDisabled?"disabled":"",l.element.prop("class")]),s.classes.grouplabel,l.element.prop("label")),e.each(l.items,function(e,t){i+=s.getItemMarkup(t.index,t)}),i+="</ul>"):i+=s.getItemMarkup(l.index,l)}),i+"</ul>"},getItemMarkup:function(t,s){var i=this,l=i.options.optionsItemBuilder,n={value:s.value,text:s.text,slug:s.slug,index:s.index};return i.utils.format('<li data-index="{1}" class="{2}">{3}</li>',t,i.utils.arrayToClassname([s.className,t===i.items.length-1?"last":"",s.disabled?"disabled":"",s.selected?"selected":""]),e.isFunction(l)?i.utils.format(l(s),s):i.utils.format(l,n))},unbindEvents:function(){this.elements.wrapper.add(this.$element).add(this.elements.outerWrapper).add(this.elements.input).off(l)},bindEvents:function(){var t=this;t.elements.outerWrapper.on("mouseenter.sl mouseleave"+l,function(s){e(this).toggleClass(t.classes.hover,"mouseenter"===s.type),t.options.openOnHover&&(clearTimeout(t.closeTimer),"mouseleave"===s.type?t.closeTimer=setTimeout(e.proxy(t.close,t),t.options.hoverIntentTimeout):t.open())}),t.elements.wrapper.on("click"+l,function(e){t.state.opened?t.close():t.open(e)}),t.options.nativeOnMobile&&t.utils.isMobile()||(t.$element.on("focus"+l,function(){t.elements.input.focus()}),t.elements.input.prop({tabindex:t.originalTabindex,disabled:!1}).on("keydown"+l,e.proxy(t.handleKeys,t)).on("focusin"+l,function(e){t.elements.outerWrapper.addClass(t.classes.focus),t.elements.input.one("blur",function(){t.elements.input.blur()}),t.options.openOnFocus&&!t.state.opened&&t.open(e)}).on("focusout"+l,function(){t.elements.outerWrapper.removeClass(t.classes.focus)}).on("input propertychange",function(){var s=t.elements.input.val(),i=new RegExp("^"+t.utils.escapeRegExp(s),"i");clearTimeout(t.resetStr),t.resetStr=setTimeout(function(){t.elements.input.val("")},t.options.keySearchTimeout),s.length&&e.each(t.items,function(e,s){(!s.disabled&&i.test(s.text)||i.test(s.slug))&&t.highlight(e)})})),t.$li.on({mousedown:function(e){e.preventDefault(),e.stopPropagation()},click:function(){return t.select(e(this).data("index")),!1}})},handleKeys:function(t){var s=this,i=t.which,l=s.options.keys,n=e.inArray(i,l.previous)>-1,a=e.inArray(i,l.next)>-1,o=e.inArray(i,l.select)>-1,r=e.inArray(i,l.open)>-1,p=s.state.highlightedIdx,u=n&&0===p||a&&p+1===s.items.length,c=0;if(13!==i&&32!==i||t.preventDefault(),n||a){if(!s.options.allowWrap&&u)return;n&&(c=s.utils.previousEnabledItem(s.lookupItems,p)),a&&(c=s.utils.nextEnabledItem(s.lookupItems,p)),s.highlight(c)}if(o&&s.state.opened)return s.select(p),void(s.state.multiple&&s.options.multiple.keepMenuOpen||s.close());r&&!s.state.opened&&s.open()},refresh:function(){this.populate(),this.activate(),this.utils.triggerCallback("Refresh",this)},setOptionsDimensions:function(){var e=this,t=e.elements.items.closest(":visible").children(":hidden").addClass(e.classes.tempshow),s=e.options.maxHeight,i=e.elements.items.outerWidth(),l=e.elements.wrapper.outerWidth()-(i-e.elements.items.width());!e.options.expandToItemText||l>i?e.finalWidth=l:(e.elements.items.css("overflow","scroll"),e.elements.outerWrapper.width(9e4),e.finalWidth=e.elements.items.width(),e.elements.items.css("overflow",""),e.elements.outerWrapper.width("")),e.elements.items.width(e.finalWidth).height()>s&&e.elements.items.height(s),t.removeClass(e.classes.tempshow)},isInViewport:function(){var e=this,t=s.scrollTop(),i=s.height(),l=e.elements.outerWrapper.offset().top,n=e.elements.outerWrapper.outerHeight(),a=l+n+e.itemsHeight<=t+i,o=l-e.itemsHeight>t,r=!a&&o;e.elements.outerWrapper.toggleClass(e.classes.above,r)},detectItemVisibility:function(t){var s=this,i=s.$li.filter("[data-index]");s.state.multiple&&(t=e.isArray(t)&&0===t.length?0:t,t=e.isArray(t)?Math.min.apply(Math,t):t);var l=i.eq(t).outerHeight(),n=i[t].offsetTop,a=s.elements.itemsScroll.scrollTop(),o=n+2*l;s.elements.itemsScroll.scrollTop(o>a+s.itemsHeight?o-s.itemsHeight:n-l<a?n-l:a)},open:function(s){var n=this;if(n.options.nativeOnMobile&&n.utils.isMobile())return!1;n.utils.triggerCallback("BeforeOpen",n),s&&(s.preventDefault(),n.options.stopPropagation&&s.stopPropagation()),n.state.enabled&&(n.setOptionsDimensions(),e("."+n.classes.hideselect,"."+n.classes.open).children()[i]("close"),n.state.opened=!0,n.itemsHeight=n.elements.items.outerHeight(),n.itemsInnerHeight=n.elements.items.height(),n.elements.outerWrapper.addClass(n.classes.open),n.elements.input.val(""),s&&"focusin"!==s.type&&n.elements.input.focus(),setTimeout(function(){t.on("click"+l,e.proxy(n.close,n)).on("scroll"+l,e.proxy(n.isInViewport,n))},1),n.isInViewport(),n.options.preventWindowScroll&&t.on("mousewheel.sl DOMMouseScroll"+l,"."+n.classes.scroll,function(t){var s=t.originalEvent,i=e(this).scrollTop(),l=0;"detail"in s&&(l=-1*s.detail),"wheelDelta"in s&&(l=s.wheelDelta),"wheelDeltaY"in s&&(l=s.wheelDeltaY),"deltaY"in s&&(l=-1*s.deltaY),(i===this.scrollHeight-n.itemsInnerHeight&&l<0||0===i&&l>0)&&t.preventDefault()}),n.detectItemVisibility(n.state.selectedIdx),n.highlight(n.state.multiple?-1:n.state.selectedIdx),n.utils.triggerCallback("Open",n))},close:function(){var e=this;e.utils.triggerCallback("BeforeClose",e),t.off(l),e.elements.outerWrapper.removeClass(e.classes.open),e.state.opened=!1,e.utils.triggerCallback("Close",e)},change:function(){var t=this;t.utils.triggerCallback("BeforeChange",t),t.state.multiple?(e.each(t.lookupItems,function(e){t.lookupItems[e].selected=!1,t.$element.find("option").prop("selected",!1)}),e.each(t.state.selectedIdx,function(e,s){t.lookupItems[s].selected=!0,t.$element.find("option").eq(s).prop("selected",!0)}),t.state.currValue=t.state.selectedIdx,t.setLabel(),t.utils.triggerCallback("Change",t)):t.state.currValue!==t.state.selectedIdx&&(t.$element.prop("selectedIndex",t.state.currValue=t.state.selectedIdx).data("value",t.lookupItems[t.state.selectedIdx].text),t.setLabel(),t.utils.triggerCallback("Change",t))},highlight:function(e){var t=this,s=t.$li.filter("[data-index]").removeClass("highlighted");t.utils.triggerCallback("BeforeHighlight",t),void 0===e||-1===e||t.lookupItems[e].disabled||(s.eq(t.state.highlightedIdx=e).addClass("highlighted"),t.detectItemVisibility(e),t.utils.triggerCallback("Highlight",t))},select:function(t){var s=this,i=s.$li.filter("[data-index]");if(s.utils.triggerCallback("BeforeSelect",s,t),void 0!==t&&-1!==t&&!s.lookupItems[t].disabled){if(s.state.multiple){s.state.selectedIdx=e.isArray(s.state.selectedIdx)?s.state.selectedIdx:[s.state.selectedIdx];var l=e.inArray(t,s.state.selectedIdx);-1!==l?s.state.selectedIdx.splice(l,1):s.state.selectedIdx.push(t),i.removeClass("selected").filter(function(t){return-1!==e.inArray(t,s.state.selectedIdx)}).addClass("selected")}else i.removeClass("selected").eq(s.state.selectedIdx=t).addClass("selected");s.state.multiple&&s.options.multiple.keepMenuOpen||s.close(),s.change(),s.utils.triggerCallback("Select",s,t)}},destroy:function(e){var t=this;t.state&&t.state.enabled&&(t.elements.items.add(t.elements.wrapper).add(t.elements.input).remove(),e||t.$element.removeData(i).removeData("value"),t.$element.prop("tabindex",t.originalTabindex).off(l).off(t.eventTriggers).unwrap().unwrap(),t.state.enabled=!1)}},e.fn[i]=function(t){return this.each(function(){var s=e.data(this,i);s&&!s.disableOnMobile?"string"==typeof t&&s[t]?s[t]():s.init(t):e.data(this,i,new o(this,t))})},e.fn[i].defaults={onChange:function(t){e(t).change()},maxHeight:300,keySearchTimeout:500,arrowButtonMarkup:'<b class="button">&#x25be;</b>',disableOnMobile:!1,nativeOnMobile:!0,openOnFocus:!0,openOnHover:!1,hoverIntentTimeout:500,expandToItemText:!1,responsive:!1,preventWindowScroll:!0,inheritOriginalWidth:!1,allowWrap:!0,stopPropagation:!0,optionsItemBuilder:"{text}",labelBuilder:"{text}",listBuilder:!1,keys:{previous:[37,38],next:[39,40],select:[9,13,27],open:[13,32,37,38,39,40],close:[9,27]},customClass:{prefix:i,camelCase:!1},multiple:{separator:", ",keepMenuOpen:!0,maxLabelEntries:!1}}}(jQuery);!function(e){"use strict";var t=e(document),s=e(window),i="selectric",l=".sl",n=["a","e","i","o","u","n","c","y"],a=[/[\xE0-\xE5]/g,/[\xE8-\xEB]/g,/[\xEC-\xEF]/g,/[\xF2-\xF6]/g,/[\xF9-\xFC]/g,/[\xF1]/g,/[\xE7]/g,/[\xFD-\xFF]/g],o=function(t,s){var i=this;i.element=t,i.$element=e(t),i.state={multiple:!!i.$element.attr("multiple"),enabled:!1,opened:!1,currValue:-1,selectedIdx:-1,highlightedIdx:-1},i.eventTriggers={open:i.open,close:i.close,destroy:i.destroy,refresh:i.refresh,init:i.init},i.init(s)};o.prototype={utils:{isMobile:function(){return/android|ip(hone|od|ad)/i.test(navigator.userAgent)},escapeRegExp:function(e){return e.replace(/[.*+?^${}()|[\]\\]/g,"\\$&")},replaceDiacritics:function(e){for(var t=a.length;t--;)e=e.toLowerCase().replace(a[t],n[t]);return e},format:function(e){var t=arguments;return(""+e).replace(/\{(?:(\d+)|(\w+))\}/g,function(e,s,i){return i&&t[1]?t[1][i]:t[s]})},nextEnabledItem:function(e,t){for(;e[t=(t+1)%e.length].disabled;);return t},previousEnabledItem:function(e,t){for(;e[t=(t>0?t:e.length)-1].disabled;);return t},toDash:function(e){return e.replace(/([a-z])([A-Z])/g,"$1-$2").toLowerCase()},triggerCallback:function(t,s){var l=s.element,n=s.options["on"+t],a=[l].concat([].slice.call(arguments).slice(1));e.isFunction(n)&&n.apply(l,a),e(l).trigger(i+"-"+this.toDash(t),a)},arrayToClassname:function(t){var s=e.grep(t,function(e){return!!e});return e.trim(s.join(" "))}},init:function(t){var s=this;if(s.options=e.extend(!0,{},e.fn[i].defaults,s.options,t),s.utils.triggerCallback("BeforeInit",s),s.destroy(!0),s.options.disableOnMobile&&s.utils.isMobile())s.disableOnMobile=!0;else{s.classes=s.getClassNames();var l=e("<input/>",{class:s.classes.input,readonly:s.utils.isMobile()}),n=e("<div/>",{class:s.classes.items,tabindex:-1}),a=e("<div/>",{class:s.classes.scroll}),o=e("<div/>",{class:s.classes.prefix,html:s.options.arrowButtonMarkup}),r=e("<span/>",{class:"label"}),p=s.$element.wrap("<div/>").parent().append(o.prepend(r),n,l),u=e("<div/>",{class:s.classes.hideselect});s.elements={input:l,items:n,itemsScroll:a,wrapper:o,label:r,outerWrapper:p},s.options.nativeOnMobile&&s.utils.isMobile()&&(s.elements.input=void 0,u.addClass(s.classes.prefix+"-is-native"),s.$element.on("change",function(){s.refresh()})),s.$element.on(s.eventTriggers).wrap(u),s.originalTabindex=s.$element.prop("tabindex"),s.$element.prop("tabindex",-1),s.populate(),s.activate(),s.utils.triggerCallback("Init",s)}},activate:function(){var e=this,t=e.elements.items.closest(":visible").children(":hidden").addClass(e.classes.tempshow),s=e.$element.width();t.removeClass(e.classes.tempshow),e.utils.triggerCallback("BeforeActivate",e),e.elements.outerWrapper.prop("class",e.utils.arrayToClassname([e.classes.wrapper,e.$element.prop("class").replace(/\S+/g,e.classes.prefix+"-$&"),e.options.responsive?e.classes.responsive:""])),e.options.inheritOriginalWidth&&s>0&&e.elements.outerWrapper.width(s),e.unbindEvents(),e.$element.prop("disabled")?(e.elements.outerWrapper.addClass(e.classes.disabled),e.elements.input&&e.elements.input.prop("disabled",!0)):(e.state.enabled=!0,e.elements.outerWrapper.removeClass(e.classes.disabled),e.$li=e.elements.items.removeAttr("style").find("li"),e.bindEvents()),e.utils.triggerCallback("Activate",e)},getClassNames:function(){var t=this,s=t.options.customClass,i={};return e.each("Input Items Open Disabled TempShow HideSelect Wrapper Focus Hover Responsive Above Scroll Group GroupLabel".split(" "),function(e,l){var n=s.prefix+l;i[l.toLowerCase()]=s.camelCase?n:t.utils.toDash(n)}),i.prefix=s.prefix,i},setLabel:function(){var t=this,s=t.options.labelBuilder;if(t.state.multiple){var i=e.isArray(t.state.currValue)?t.state.currValue:[t.state.currValue];i=0===i.length?[0]:i;var l=e.map(i,function(s){return e.grep(t.lookupItems,function(e){return e.index===s})[0]});l=e.grep(l,function(t){return l.length>1||0===l.length?""!==e.trim(t.value):t}),l=e.map(l,function(i){return e.isFunction(s)?s(i):t.utils.format(s,i)}),t.options.multiple.maxLabelEntries&&(l.length>=t.options.multiple.maxLabelEntries+1?(l=l.slice(0,t.options.multiple.maxLabelEntries)).push(e.isFunction(s)?s({text:"..."}):t.utils.format(s,{text:"..."})):l.slice(l.length-1)),t.elements.label.html(l.join(t.options.multiple.separator))}else{var n=t.lookupItems[t.state.currValue];t.elements.label.html(e.isFunction(s)?s(n):t.utils.format(s,n))}},populate:function(){var t=this,s=t.$element.children(),i=t.$element.find("option"),l=i.filter(":selected"),n=i.index(l),a=0,o=t.state.multiple?[]:0;l.length>1&&t.state.multiple&&(n=[],l.each(function(){n.push(e(this).index())})),t.state.currValue=~n?n:o,t.state.selectedIdx=t.state.currValue,t.state.highlightedIdx=t.state.currValue,t.items=[],t.lookupItems=[],s.length&&(s.each(function(s){var i=e(this);if(i.is("optgroup")){var l={element:i,label:i.prop("label"),groupDisabled:i.prop("disabled"),items:[]};i.children().each(function(s){var i=e(this);l.items[s]=t.getItemData(a,i,l.groupDisabled||i.prop("disabled")),t.lookupItems[a]=l.items[s],a++}),t.items[s]=l}else t.items[s]=t.getItemData(a,i,i.prop("disabled")),t.lookupItems[a]=t.items[s],a++}),t.setLabel(),t.elements.items.append(t.elements.itemsScroll.html(t.getItemsMarkup(t.items))))},getItemData:function(t,s,i){return{index:t,element:s,value:s.val(),className:s.prop("class"),text:s.html(),slug:e.trim(this.utils.replaceDiacritics(s.html())),selected:s.prop("selected"),disabled:i}},getItemsMarkup:function(t){var s=this,i="<ul>";return e.isFunction(s.options.listBuilder)&&s.options.listBuilder&&(t=s.options.listBuilder(t)),e.each(t,function(t,l){void 0!==l.label?(i+=s.utils.format('<ul class="{1}"><li class="{2}">{3}</li>',s.utils.arrayToClassname([s.classes.group,l.groupDisabled?"disabled":"",l.element.prop("class")]),s.classes.grouplabel,l.element.prop("label")),e.each(l.items,function(e,t){i+=s.getItemMarkup(t.index,t)}),i+="</ul>"):i+=s.getItemMarkup(l.index,l)}),i+"</ul>"},getItemMarkup:function(t,s){var i=this,l=i.options.optionsItemBuilder,n={value:s.value,text:s.text,slug:s.slug,index:s.index};return i.utils.format('<li data-index="{1}" class="{2}">{3}</li>',t,i.utils.arrayToClassname([s.className,t===i.items.length-1?"last":"",s.disabled?"disabled":"",s.selected?"selected":""]),e.isFunction(l)?i.utils.format(l(s),s):i.utils.format(l,n))},unbindEvents:function(){this.elements.wrapper.add(this.$element).add(this.elements.outerWrapper).add(this.elements.input).off(l)},bindEvents:function(){var t=this;t.elements.outerWrapper.on("mouseenter.sl mouseleave"+l,function(s){e(this).toggleClass(t.classes.hover,"mouseenter"===s.type),t.options.openOnHover&&(clearTimeout(t.closeTimer),"mouseleave"===s.type?t.closeTimer=setTimeout(e.proxy(t.close,t),t.options.hoverIntentTimeout):t.open())}),t.elements.wrapper.on("click"+l,function(e){t.state.opened?t.close():t.open(e)}),t.options.nativeOnMobile&&t.utils.isMobile()||(t.$element.on("focus"+l,function(){t.elements.input.focus()}),t.elements.input.prop({tabindex:t.originalTabindex,disabled:!1}).on("keydown"+l,e.proxy(t.handleKeys,t)).on("focusin"+l,function(e){t.elements.outerWrapper.addClass(t.classes.focus),t.elements.input.one("blur",function(){t.elements.input.blur()}),t.options.openOnFocus&&!t.state.opened&&t.open(e)}).on("focusout"+l,function(){t.elements.outerWrapper.removeClass(t.classes.focus)}).on("input propertychange",function(){var s=t.elements.input.val(),i=new RegExp("^"+t.utils.escapeRegExp(s),"i");clearTimeout(t.resetStr),t.resetStr=setTimeout(function(){t.elements.input.val("")},t.options.keySearchTimeout),s.length&&e.each(t.items,function(e,s){(!s.disabled&&i.test(s.text)||i.test(s.slug))&&t.highlight(e)})})),t.$li.on({mousedown:function(e){e.preventDefault(),e.stopPropagation()},click:function(){return t.select(e(this).data("index")),!1}})},handleKeys:function(t){var s=this,i=t.which,l=s.options.keys,n=e.inArray(i,l.previous)>-1,a=e.inArray(i,l.next)>-1,o=e.inArray(i,l.select)>-1,r=e.inArray(i,l.open)>-1,p=s.state.highlightedIdx,u=n&&0===p||a&&p+1===s.items.length,c=0;if(13!==i&&32!==i||t.preventDefault(),n||a){if(!s.options.allowWrap&&u)return;n&&(c=s.utils.previousEnabledItem(s.lookupItems,p)),a&&(c=s.utils.nextEnabledItem(s.lookupItems,p)),s.highlight(c)}if(o&&s.state.opened)return s.select(p),void(s.state.multiple&&s.options.multiple.keepMenuOpen||s.close());r&&!s.state.opened&&s.open()},refresh:function(){this.populate(),this.activate(),this.utils.triggerCallback("Refresh",this)},setOptionsDimensions:function(){var e=this,t=e.elements.items.closest(":visible").children(":hidden").addClass(e.classes.tempshow),s=e.options.maxHeight,i=e.elements.items.outerWidth(),l=e.elements.wrapper.outerWidth()-(i-e.elements.items.width());!e.options.expandToItemText||l>i?e.finalWidth=l:(e.elements.items.css("overflow","scroll"),e.elements.outerWrapper.width(9e4),e.finalWidth=e.elements.items.width(),e.elements.items.css("overflow",""),e.elements.outerWrapper.width("")),e.elements.items.width(e.finalWidth).height()>s&&e.elements.items.height(s),t.removeClass(e.classes.tempshow)},isInViewport:function(){var e=this,t=s.scrollTop(),i=s.height(),l=e.elements.outerWrapper.offset().top,n=e.elements.outerWrapper.outerHeight(),a=l+n+e.itemsHeight<=t+i,o=l-e.itemsHeight>t,r=!a&&o;e.elements.outerWrapper.toggleClass(e.classes.above,r)},detectItemVisibility:function(t){var s=this,i=s.$li.filter("[data-index]");s.state.multiple&&(t=e.isArray(t)&&0===t.length?0:t,t=e.isArray(t)?Math.min.apply(Math,t):t);var l=i.eq(t).outerHeight(),n=i[t].offsetTop,a=s.elements.itemsScroll.scrollTop(),o=n+2*l;s.elements.itemsScroll.scrollTop(o>a+s.itemsHeight?o-s.itemsHeight:n-l<a?n-l:a)},open:function(s){var n=this;if(n.options.nativeOnMobile&&n.utils.isMobile())return!1;n.utils.triggerCallback("BeforeOpen",n),s&&(s.preventDefault(),n.options.stopPropagation&&s.stopPropagation()),n.state.enabled&&(n.setOptionsDimensions(),e("."+n.classes.hideselect,"."+n.classes.open).children()[i]("close"),n.state.opened=!0,n.itemsHeight=n.elements.items.outerHeight(),n.itemsInnerHeight=n.elements.items.height(),n.elements.outerWrapper.addClass(n.classes.open),n.elements.input.val(""),s&&"focusin"!==s.type&&n.elements.input.focus(),setTimeout(function(){t.on("click"+l,e.proxy(n.close,n)).on("scroll"+l,e.proxy(n.isInViewport,n))},1),n.isInViewport(),n.options.preventWindowScroll&&t.on("mousewheel.sl DOMMouseScroll"+l,"."+n.classes.scroll,function(t){var s=t.originalEvent,i=e(this).scrollTop(),l=0;"detail"in s&&(l=-1*s.detail),"wheelDelta"in s&&(l=s.wheelDelta),"wheelDeltaY"in s&&(l=s.wheelDeltaY),"deltaY"in s&&(l=-1*s.deltaY),(i===this.scrollHeight-n.itemsInnerHeight&&l<0||0===i&&l>0)&&t.preventDefault()}),n.detectItemVisibility(n.state.selectedIdx),n.highlight(n.state.multiple?-1:n.state.selectedIdx),n.utils.triggerCallback("Open",n))},close:function(){var e=this;e.utils.triggerCallback("BeforeClose",e),t.off(l),e.elements.outerWrapper.removeClass(e.classes.open),e.state.opened=!1,e.utils.triggerCallback("Close",e)},change:function(){var t=this;t.utils.triggerCallback("BeforeChange",t),t.state.multiple?(e.each(t.lookupItems,function(e){t.lookupItems[e].selected=!1,t.$element.find("option").prop("selected",!1)}),e.each(t.state.selectedIdx,function(e,s){t.lookupItems[s].selected=!0,t.$element.find("option").eq(s).prop("selected",!0)}),t.state.currValue=t.state.selectedIdx,t.setLabel(),t.utils.triggerCallback("Change",t)):t.state.currValue!==t.state.selectedIdx&&(t.$element.prop("selectedIndex",t.state.currValue=t.state.selectedIdx).data("value",t.lookupItems[t.state.selectedIdx].text),t.setLabel(),t.utils.triggerCallback("Change",t))},highlight:function(e){var t=this,s=t.$li.filter("[data-index]").removeClass("highlighted");t.utils.triggerCallback("BeforeHighlight",t),void 0===e||-1===e||t.lookupItems[e].disabled||(s.eq(t.state.highlightedIdx=e).addClass("highlighted"),t.detectItemVisibility(e),t.utils.triggerCallback("Highlight",t))},select:function(t){var s=this,i=s.$li.filter("[data-index]");if(s.utils.triggerCallback("BeforeSelect",s,t),void 0!==t&&-1!==t&&!s.lookupItems[t].disabled){if(s.state.multiple){s.state.selectedIdx=e.isArray(s.state.selectedIdx)?s.state.selectedIdx:[s.state.selectedIdx];var l=e.inArray(t,s.state.selectedIdx);-1!==l?s.state.selectedIdx.splice(l,1):s.state.selectedIdx.push(t),i.removeClass("selected").filter(function(t){return-1!==e.inArray(t,s.state.selectedIdx)}).addClass("selected")}else i.removeClass("selected").eq(s.state.selectedIdx=t).addClass("selected");s.state.multiple&&s.options.multiple.keepMenuOpen||s.close(),s.change(),s.utils.triggerCallback("Select",s,t)}},destroy:function(e){var t=this;t.state&&t.state.enabled&&(t.elements.items.add(t.elements.wrapper).add(t.elements.input).remove(),e||t.$element.removeData(i).removeData("value"),t.$element.prop("tabindex",t.originalTabindex).off(l).off(t.eventTriggers).unwrap().unwrap(),t.state.enabled=!1)}},e.fn[i]=function(t){return this.each(function(){var s=e.data(this,i);s&&!s.disableOnMobile?"string"==typeof t&&s[t]?s[t]():s.init(t):e.data(this,i,new o(this,t))})},e.fn[i].defaults={onChange:function(t){e(t).change()},maxHeight:300,keySearchTimeout:500,arrowButtonMarkup:'<b class="button">&#x25be;</b>',disableOnMobile:!1,nativeOnMobile:!0,openOnFocus:!0,openOnHover:!1,hoverIntentTimeout:500,expandToItemText:!1,responsive:!1,preventWindowScroll:!0,inheritOriginalWidth:!1,allowWrap:!0,stopPropagation:!0,optionsItemBuilder:"{text}",labelBuilder:"{text}",listBuilder:!1,keys:{previous:[37,38],next:[39,40],select:[9,13,27],open:[13,32,37,38,39,40],close:[9,27]},customClass:{prefix:i,camelCase:!1},multiple:{separator:", ",keepMenuOpen:!0,maxLabelEntries:!1}}}(jQuery);
/*!
Waypoints - 4.0.1
Copyright © 2011-2016 Caleb Troughton
Licensed under the MIT license.
https://github.com/imakewebthings/waypoints/blob/master/licenses.txt
*/
!function(){"use strict";function t(o){if(!o)throw new Error("No options passed to Waypoint constructor");if(!o.element)throw new Error("No element option passed to Waypoint constructor");if(!o.handler)throw new Error("No handler option passed to Waypoint constructor");this.key="waypoint-"+e,this.options=t.Adapter.extend({},t.defaults,o),this.element=this.options.element,this.adapter=new t.Adapter(this.element),this.callback=o.handler,this.axis=this.options.horizontal?"horizontal":"vertical",this.enabled=this.options.enabled,this.triggerPoint=null,this.group=t.Group.findOrCreate({name:this.options.group,axis:this.axis}),this.context=t.Context.findOrCreateByElement(this.options.context),t.offsetAliases[this.options.offset]&&(this.options.offset=t.offsetAliases[this.options.offset]),this.group.add(this),this.context.add(this),i[this.key]=this,e+=1}var e=0,i={};t.prototype.queueTrigger=function(t){this.group.queueTrigger(this,t)},t.prototype.trigger=function(t){this.enabled&&this.callback&&this.callback.apply(this,t)},t.prototype.destroy=function(){this.context.remove(this),this.group.remove(this),delete i[this.key]},t.prototype.disable=function(){return this.enabled=!1,this},t.prototype.enable=function(){return this.context.refresh(),this.enabled=!0,this},t.prototype.next=function(){return this.group.next(this)},t.prototype.previous=function(){return this.group.previous(this)},t.invokeAll=function(t){var e=[];for(var o in i)e.push(i[o]);for(var n=0,r=e.length;r>n;n++)e[n][t]()},t.destroyAll=function(){t.invokeAll("destroy")},t.disableAll=function(){t.invokeAll("disable")},t.enableAll=function(){t.Context.refreshAll();for(var e in i)i[e].enabled=!0;return this},t.refreshAll=function(){t.Context.refreshAll()},t.viewportHeight=function(){return window.innerHeight||document.documentElement.clientHeight},t.viewportWidth=function(){return document.documentElement.clientWidth},t.adapters=[],t.defaults={context:window,continuous:!0,enabled:!0,group:"default",horizontal:!1,offset:0},t.offsetAliases={"bottom-in-view":function(){return this.context.innerHeight()-this.adapter.outerHeight()},"right-in-view":function(){return this.context.innerWidth()-this.adapter.outerWidth()}},window.Waypoint=t}(),function(){"use strict";function t(t){window.setTimeout(t,1e3/60)}function e(t){this.element=t,this.Adapter=n.Adapter,this.adapter=new this.Adapter(t),this.key="waypoint-context-"+i,this.didScroll=!1,this.didResize=!1,this.oldScroll={x:this.adapter.scrollLeft(),y:this.adapter.scrollTop()},this.waypoints={vertical:{},horizontal:{}},t.waypointContextKey=this.key,o[t.waypointContextKey]=this,i+=1,n.windowContext||(n.windowContext=!0,n.windowContext=new e(window)),this.createThrottledScrollHandler(),this.createThrottledResizeHandler()}var i=0,o={},n=window.Waypoint,r=window.onload;e.prototype.add=function(t){var e=t.options.horizontal?"horizontal":"vertical";this.waypoints[e][t.key]=t,this.refresh()},e.prototype.checkEmpty=function(){var t=this.Adapter.isEmptyObject(this.waypoints.horizontal),e=this.Adapter.isEmptyObject(this.waypoints.vertical),i=this.element==this.element.window;t&&e&&!i&&(this.adapter.off(".waypoints"),delete o[this.key])},e.prototype.createThrottledResizeHandler=function(){function t(){e.handleResize(),e.didResize=!1}var e=this;this.adapter.on("resize.waypoints",function(){e.didResize||(e.didResize=!0,n.requestAnimationFrame(t))})},e.prototype.createThrottledScrollHandler=function(){function t(){e.handleScroll(),e.didScroll=!1}var e=this;this.adapter.on("scroll.waypoints",function(){(!e.didScroll||n.isTouch)&&(e.didScroll=!0,n.requestAnimationFrame(t))})},e.prototype.handleResize=function(){n.Context.refreshAll()},e.prototype.handleScroll=function(){var t={},e={horizontal:{newScroll:this.adapter.scrollLeft(),oldScroll:this.oldScroll.x,forward:"right",backward:"left"},vertical:{newScroll:this.adapter.scrollTop(),oldScroll:this.oldScroll.y,forward:"down",backward:"up"}};for(var i in e){var o=e[i],n=o.newScroll>o.oldScroll,r=n?o.forward:o.backward;for(var s in this.waypoints[i]){var a=this.waypoints[i][s];if(null!==a.triggerPoint){var l=o.oldScroll<a.triggerPoint,h=o.newScroll>=a.triggerPoint,p=l&&h,u=!l&&!h;(p||u)&&(a.queueTrigger(r),t[a.group.id]=a.group)}}}for(var c in t)t[c].flushTriggers();this.oldScroll={x:e.horizontal.newScroll,y:e.vertical.newScroll}},e.prototype.innerHeight=function(){return this.element==this.element.window?n.viewportHeight():this.adapter.innerHeight()},e.prototype.remove=function(t){delete this.waypoints[t.axis][t.key],this.checkEmpty()},e.prototype.innerWidth=function(){return this.element==this.element.window?n.viewportWidth():this.adapter.innerWidth()},e.prototype.destroy=function(){var t=[];for(var e in this.waypoints)for(var i in this.waypoints[e])t.push(this.waypoints[e][i]);for(var o=0,n=t.length;n>o;o++)t[o].destroy()},e.prototype.refresh=function(){var t,e=this.element==this.element.window,i=e?void 0:this.adapter.offset(),o={};this.handleScroll(),t={horizontal:{contextOffset:e?0:i.left,contextScroll:e?0:this.oldScroll.x,contextDimension:this.innerWidth(),oldScroll:this.oldScroll.x,forward:"right",backward:"left",offsetProp:"left"},vertical:{contextOffset:e?0:i.top,contextScroll:e?0:this.oldScroll.y,contextDimension:this.innerHeight(),oldScroll:this.oldScroll.y,forward:"down",backward:"up",offsetProp:"top"}};for(var r in t){var s=t[r];for(var a in this.waypoints[r]){var l,h,p,u,c,d=this.waypoints[r][a],f=d.options.offset,w=d.triggerPoint,y=0,g=null==w;d.element!==d.element.window&&(y=d.adapter.offset()[s.offsetProp]),"function"==typeof f?f=f.apply(d):"string"==typeof f&&(f=parseFloat(f),d.options.offset.indexOf("%")>-1&&(f=Math.ceil(s.contextDimension*f/100))),l=s.contextScroll-s.contextOffset,d.triggerPoint=Math.floor(y+l-f),h=w<s.oldScroll,p=d.triggerPoint>=s.oldScroll,u=h&&p,c=!h&&!p,!g&&u?(d.queueTrigger(s.backward),o[d.group.id]=d.group):!g&&c?(d.queueTrigger(s.forward),o[d.group.id]=d.group):g&&s.oldScroll>=d.triggerPoint&&(d.queueTrigger(s.forward),o[d.group.id]=d.group)}}return n.requestAnimationFrame(function(){for(var t in o)o[t].flushTriggers()}),this},e.findOrCreateByElement=function(t){return e.findByElement(t)||new e(t)},e.refreshAll=function(){for(var t in o)o[t].refresh()},e.findByElement=function(t){return o[t.waypointContextKey]},window.onload=function(){r&&r(),e.refreshAll()},n.requestAnimationFrame=function(e){var i=window.requestAnimationFrame||window.mozRequestAnimationFrame||window.webkitRequestAnimationFrame||t;i.call(window,e)},n.Context=e}(),function(){"use strict";function t(t,e){return t.triggerPoint-e.triggerPoint}function e(t,e){return e.triggerPoint-t.triggerPoint}function i(t){this.name=t.name,this.axis=t.axis,this.id=this.name+"-"+this.axis,this.waypoints=[],this.clearTriggerQueues(),o[this.axis][this.name]=this}var o={vertical:{},horizontal:{}},n=window.Waypoint;i.prototype.add=function(t){this.waypoints.push(t)},i.prototype.clearTriggerQueues=function(){this.triggerQueues={up:[],down:[],left:[],right:[]}},i.prototype.flushTriggers=function(){for(var i in this.triggerQueues){var o=this.triggerQueues[i],n="up"===i||"left"===i;o.sort(n?e:t);for(var r=0,s=o.length;s>r;r+=1){var a=o[r];(a.options.continuous||r===o.length-1)&&a.trigger([i])}}this.clearTriggerQueues()},i.prototype.next=function(e){this.waypoints.sort(t);var i=n.Adapter.inArray(e,this.waypoints),o=i===this.waypoints.length-1;return o?null:this.waypoints[i+1]},i.prototype.previous=function(e){this.waypoints.sort(t);var i=n.Adapter.inArray(e,this.waypoints);return i?this.waypoints[i-1]:null},i.prototype.queueTrigger=function(t,e){this.triggerQueues[e].push(t)},i.prototype.remove=function(t){var e=n.Adapter.inArray(t,this.waypoints);e>-1&&this.waypoints.splice(e,1)},i.prototype.first=function(){return this.waypoints[0]},i.prototype.last=function(){return this.waypoints[this.waypoints.length-1]},i.findOrCreate=function(t){return o[t.axis][t.name]||new i(t)},n.Group=i}(),function(){"use strict";function t(t){this.$element=e(t)}var e=window.jQuery,i=window.Waypoint;e.each(["innerHeight","innerWidth","off","offset","on","outerHeight","outerWidth","scrollLeft","scrollTop"],function(e,i){t.prototype[i]=function(){var t=Array.prototype.slice.call(arguments);return this.$element[i].apply(this.$element,t)}}),e.each(["extend","inArray","isEmptyObject"],function(i,o){t[o]=e[o]}),i.adapters.push({name:"jquery",Adapter:t}),i.Adapter=t}(),function(){"use strict";function t(t){return function(){var i=[],o=arguments[0];return t.isFunction(arguments[0])&&(o=t.extend({},arguments[1]),o.handler=arguments[0]),this.each(function(){var n=t.extend({},o,{element:this});"string"==typeof n.context&&(n.context=t(this).closest(n.context)[0]),i.push(new e(n))}),i}}var e=window.Waypoint;window.jQuery&&(window.jQuery.fn.waypoint=t(window.jQuery)),window.Zepto&&(window.Zepto.fn.waypoint=t(window.Zepto))}();
/*jslint browser: true, evil: true */

var scripts = document.getElementsByTagName('script'),
    path = scripts[scripts.length - 1].src.split('?')[0],
    cdn = path.split('/').slice(0, -1).join('/') + '/',
    alreadyLaunch = (alreadyLaunch === undefined) ? 0 : alreadyLaunch,
    tarteaucitronForceLanguage = (tarteaucitronForceLanguage === undefined) ? '' : tarteaucitronForceLanguage,
    tarteaucitronProLoadServices,
    tarteaucitronNoAdBlocker = false;

var tarteaucitron = {
    "version": 323,
    "cdn": cdn,
    "user": {},
    "lang": {},
    "services": {},
    "added": [],
    "idprocessed": [],
    "state": [],
    "launch": [],
    "parameters": {},
    "isAjax": false,
    "reloadThePage": false,
    "init": function (params) {
        "use strict";
        var origOpen;

        tarteaucitron.parameters = params;
        if (alreadyLaunch === 0) {
            alreadyLaunch = 1;
            if (window.addEventListener) {
                window.addEventListener("load", function () {
                    tarteaucitron.load();
                    tarteaucitron.fallback(['tarteaucitronOpenPanel'], function (elem) {
                        elem.addEventListener("click", function (event) {
                            tarteaucitron.userInterface.openPanel();
                            event.preventDefault();
                        }, false);
                    }, true);
                }, false);
                window.addEventListener("scroll", function () {
                    var scrollPos = window.pageYOffset || document.documentElement.scrollTop,
                        heightPosition;
                    if (document.getElementById('tarteaucitronAlertBig') !== null && !tarteaucitron.highPrivacy) {
                        if (document.getElementById('tarteaucitronAlertBig').style.display === 'block') {
                            heightPosition = document.getElementById('tarteaucitronAlertBig').offsetHeight + 10 + 'px';
                            
                            if (scrollPos > (screen.height * 2)) {
                                tarteaucitron.userInterface.respondAll(true);
                            } else if (scrollPos > (screen.height / 2)) {
                                document.getElementById('tarteaucitronDisclaimerAlert').innerHTML = '<b>' + tarteaucitron.lang.alertBigScroll + '</b> ' + tarteaucitron.lang.alertBig;
                            }
                            
                            if (tarteaucitron.orientation === 'top') {
                                document.getElementById('tarteaucitronPercentage').style.top = heightPosition;
                            } else {
                                document.getElementById('tarteaucitronPercentage').style.bottom = heightPosition;
                            }
                            document.getElementById('tarteaucitronPercentage').style.width = ((100 / (screen.height * 2)) * scrollPos) + '%';
                        }
                    }
                }, false);
                window.addEventListener("keydown", function (evt) {
                    if (evt.keyCode === 27) {
                        tarteaucitron.userInterface.closePanel();
                    }
                }, false);
                window.addEventListener("hashchange", function () {
                    if (document.location.hash === tarteaucitron.hashtag && tarteaucitron.hashtag !== '') {
                        tarteaucitron.userInterface.openPanel();
                    }
                }, false);
                window.addEventListener("resize", function () {
                    if (document.getElementById('tarteaucitron') !== null) {
                        if (document.getElementById('tarteaucitron').style.display === 'block') {
                            tarteaucitron.userInterface.jsSizing('main');
                        }
                    }
                    
                    if (document.getElementById('tarteaucitronCookiesListContainer') !== null) {
                        if (document.getElementById('tarteaucitronCookiesListContainer').style.display === 'block') {
                            tarteaucitron.userInterface.jsSizing('cookie');
                        }
                    }
                }, false);
            } else {
                window.attachEvent("onload", function () {
                    tarteaucitron.load();
                    tarteaucitron.fallback(['tarteaucitronOpenPanel'], function (elem) {
                        elem.attachEvent("onclick", function (event) {
                            tarteaucitron.userInterface.openPanel();
                            event.preventDefault();
                        });
                    }, true);
                });
                window.attachEvent("onscroll", function () {
                    var scrollPos = window.pageYOffset || document.documentElement.scrollTop,
                        heightPosition;
                    if (document.getElementById('tarteaucitronAlertBig') !== null && !tarteaucitron.highPrivacy) {
                        if (document.getElementById('tarteaucitronAlertBig').style.display === 'block') {
                            heightPosition = document.getElementById('tarteaucitronAlertBig').offsetHeight + 'px';
                            
                            if (scrollPos > (screen.height * 2)) {
                                tarteaucitron.userInterface.respondAll(true);
                            } else if (scrollPos > (screen.height / 2)) {
                                document.getElementById('tarteaucitronDisclaimerAlert').innerHTML = '<b>' + tarteaucitron.lang.alertBigScroll + '</b> ' + tarteaucitron.lang.alertBig;
                            }
                            if (tarteaucitron.orientation === 'top') {
                                document.getElementById('tarteaucitronPercentage').style.top = heightPosition;
                            } else {
                                document.getElementById('tarteaucitronPercentage').style.bottom = heightPosition;
                            }
                            document.getElementById('tarteaucitronPercentage').style.width = ((100 / (screen.height * 2)) * scrollPos) + '%';
                        }
                    }
                });
                window.attachEvent("onkeydown", function (evt) {
                    if (evt.keyCode === 27) {
                        tarteaucitron.userInterface.closePanel();
                    }
                });
                window.attachEvent("onhashchange", function () {
                    if (document.location.hash === tarteaucitron.hashtag && tarteaucitron.hashtag !== '') {
                        tarteaucitron.userInterface.openPanel();
                    }
                });
                window.attachEvent("onresize", function () {
                    if (document.getElementById('tarteaucitron') !== null) {
                        if (document.getElementById('tarteaucitron').style.display === 'block') {
                            tarteaucitron.userInterface.jsSizing('main');
                        }
                    }
                    
                    if (document.getElementById('tarteaucitronCookiesListContainer') !== null) {
                        if (document.getElementById('tarteaucitronCookiesListContainer').style.display === 'block') {
                            tarteaucitron.userInterface.jsSizing('cookie');
                        }
                    }
                });
            }
            
            if (typeof XMLHttpRequest !== 'undefined') {
                origOpen = XMLHttpRequest.prototype.open;
                XMLHttpRequest.prototype.open = function () {
                    
                    if (window.addEventListener) {
                        this.addEventListener("load", function () {
                            if (typeof tarteaucitronProLoadServices === 'function') {
                                tarteaucitronProLoadServices();
                            }
                        }, false);
                    } else if (typeof this.attachEvent !== 'undefined') {
                        this.attachEvent("onload", function () {
                            if (typeof tarteaucitronProLoadServices === 'function') {
                                tarteaucitronProLoadServices();
                            }
                        });
                    } else {
                        if (typeof tarteaucitronProLoadServices === 'function') {
                            setTimeout(tarteaucitronProLoadServices, 1000);
                        }
                    }
                    
                    try {
                        origOpen.apply(this, arguments);
                    } catch (err) {}
                };
            }
        }
    },
    "load": function () {
        "use strict";
        var cdn = tarteaucitron.cdn,
            language = tarteaucitron.getLanguage(),
            // pathToLang = cdn + 'lang/tarteaucitron.' + language + '.js?v=' + tarteaucitron.version,
            // pathToServices = cdn + 'tarteaucitron.services.js?v=' + tarteaucitron.version,
            linkElement = document.createElement('link'),
            defaults = {
                "adblocker": false,
                "hashtag": '#tarteaucitron',
                "highPrivacy": false,
                "orientation": "top",
                "removeCredit": false,
                "showAlertSmall": true,
                "cookieslist": true
            },
            params = tarteaucitron.parameters;
        
        // Step 0: get params
        if (params !== undefined) {
            tarteaucitron.extend(defaults, params);
        }
        
        // global
        tarteaucitron.orientation = defaults.orientation;
        tarteaucitron.hashtag = defaults.hashtag;
        tarteaucitron.highPrivacy = defaults.highPrivacy;
        tarteaucitron.lang = tarteaucitron.langlist[language];

        // Step 1: load css @author Thuria : on le colle directement dans scss/libs
        // linkElement.rel = 'stylesheet';
        // linkElement.type = 'text/css';
        // linkElement.href = cdn + 'css/tarteaucitron.css?v=' + tarteaucitron.version;
        // document.getElementsByTagName('head')[0].appendChild(linkElement);

        // Step 2: load language and services
        // tarteaucitron.addScript(pathToLang, '', function () {
        //     tarteaucitron.addScript(pathToServices, '', function () {

                var body = document.body,
                    div = document.createElement('div'),
                    html = '',
                    index,
                    orientation = 'Top',
                    cat = ['ads', 'analytic', 'api', 'comment', 'social', 'support', 'video', 'other'],
                    i;
                
                cat = cat.sort(function (a, b) {
                    if (tarteaucitron.lang[a].title > tarteaucitron.lang[b].title) { return 1; }
                    if (tarteaucitron.lang[a].title < tarteaucitron.lang[b].title) { return -1; }
                    return 0;
                });

                // Step 3: prepare the html
                html += '<div id="tarteaucitronPremium"></div>';
                html += '<div id="tarteaucitronBack" onclick="tarteaucitron.userInterface.closePanel();"></div>';
                html += '<div id="tarteaucitron">';
                html += '   <div id="tarteaucitronClosePanel" onclick="tarteaucitron.userInterface.closePanel();">';
                html += '       ' + tarteaucitron.lang.close;
                html += '   </div>';
                html += '   <div id="tarteaucitronServices">';
                html += '      <div class="tarteaucitronLine tarteaucitronMainLine" id="tarteaucitronMainLineOffset">';
                html += '         <div class="tarteaucitronName">';
                html += '            <b><a href="#" onclick="tarteaucitron.userInterface.toggle(\'tarteaucitronInfo\', \'tarteaucitronInfoBox\');return false">&#10011;</a> ' + tarteaucitron.lang.all + '</b>';
                html += '         </div>';
                html += '         <div class="tarteaucitronAsk" id="tarteaucitronScrollbarAdjust">';
                html += '            <div id="tarteaucitronAllAllowed" class="tarteaucitronAllow" onclick="tarteaucitron.userInterface.respondAll(true);">';
                html += '               &#10003; ' + tarteaucitron.lang.allow;
                html += '            </div> ';
                html += '            <div id="tarteaucitronAllDenied" class="tarteaucitronDeny" onclick="tarteaucitron.userInterface.respondAll(false);">';
                html += '               &#10007; ' + tarteaucitron.lang.deny;
                html += '            </div>';
                html += '         </div>';
                html += '      </div>';
                html += '      <div id="tarteaucitronInfo" class="tarteaucitronInfoBox">';
                html += '         ' + tarteaucitron.lang.disclaimer;
                if (defaults.removeCredit === false) {
                    html += '        <br/><br/>';
                    html += '        <a href="https://opt-out.ferank.eu/" rel="nofollow" target="_blank" rel="noopener">' + tarteaucitron.lang.credit + '</a>';
                }
                html += '      </div>';
                html += '      <div class="tarteaucitronBorder" id="tarteaucitronScrollbarParent">';
                html += '         <div class="clear"></div>';
                for (i = 0; i < cat.length; i += 1) {
                    html += '         <div id="tarteaucitronServicesTitle_' + cat[i] + '" class="tarteaucitronHidden">';
                    html += '            <div class="tarteaucitronTitle">';
                    html += '               <a href="#" onclick="tarteaucitron.userInterface.toggle(\'tarteaucitronDetails' + cat[i] + '\', \'tarteaucitronInfoBox\');return false">&#10011;</a> ' + tarteaucitron.lang[cat[i]].title;
                    html += '            </div>';
                    html += '            <div id="tarteaucitronDetails' + cat[i] + '" class="tarteaucitronDetails tarteaucitronInfoBox">';
                    html += '               ' + tarteaucitron.lang[cat[i]].details;
                    html += '            </div>';
                    html += '         </div>';
                    html += '         <div id="tarteaucitronServices_' + cat[i] + '"></div>';
                }
                html += '         <div class="tarteaucitronHidden" id="tarteaucitronScrollbarChild" style="display:block"></div>';
                html += '       </div>';
                html += '   </div>';
                html += '</div>';
                
                if (defaults.orientation === 'bottom') {
                    orientation = 'Bottom';
                }
                
                if (defaults.highPrivacy) {
                    html += '<div id="tarteaucitronAlertBig" class="tarteaucitronAlertBig' + orientation + '">';
                    html += '   <span id="tarteaucitronDisclaimerAlert">';
                    html += '       ' + tarteaucitron.lang.alertBigPrivacy;
                    html += '   </span>';
                    html += '   <span id="tarteaucitronPersonalize" onclick="tarteaucitron.userInterface.openPanel();">';
                    html += '       ' + tarteaucitron.lang.personalize;
                    html += '   </span>';
                    html += '</div>';
                } else {
                    html += '<div id="tarteaucitronAlertBig" class="tarteaucitronAlertBig' + orientation + '">';
                    html += '   <span id="tarteaucitronDisclaimerAlert">';
                    html += '       ' + tarteaucitron.lang.alertBigClick + ' ' + tarteaucitron.lang.alertBig;
                    html += '   </span>';
                    html += '   <span id="tarteaucitronPersonalize" onclick="tarteaucitron.userInterface.respondAll(true);">';
                    html += '       &#10003; ' + tarteaucitron.lang.acceptAll;
                    html += '   </span>';
                    html += '   <span id="tarteaucitronCloseAlert" onclick="tarteaucitron.userInterface.openPanel();">';
                    html += '       ' + tarteaucitron.lang.personalize;
                    html += '   </span>';
                    html += '</div>';
                    html += '<div id="tarteaucitronPercentage"></div>';
                }
                
                if (defaults.showAlertSmall === true) {
                    html += '<div id="tarteaucitronAlertSmall">';
                    html += '   <div id="tarteaucitronManager" onclick="tarteaucitron.userInterface.openPanel();">';
                    html += '       ' + tarteaucitron.lang.alertSmall;
                    html += '       <div id="tarteaucitronDot">';
                    html += '           <span id="tarteaucitronDotGreen"></span>';
                    html += '           <span id="tarteaucitronDotYellow"></span>';
                    html += '           <span id="tarteaucitronDotRed"></span>';
                    html += '       </div>';
                    if (defaults.cookieslist === true) {
                        html += '   </div><!-- @whitespace';
                        html += '   --><div id="tarteaucitronCookiesNumber" onclick="tarteaucitron.userInterface.toggleCookiesList();">0</div>';
                        html += '   <div id="tarteaucitronCookiesListContainer">';
                        html += '       <div id="tarteaucitronClosePanelCookie" onclick="tarteaucitron.userInterface.closePanel();">';
                        html += '           ' + tarteaucitron.lang.close;
                        html += '       </div>';
                        html += '       <div class="tarteaucitronCookiesListMain" id="tarteaucitronCookiesTitle">';
                        html += '            <b id="tarteaucitronCookiesNumberBis">0 cookie</b>';
                        html += '       </div>';
                        html += '       <div id="tarteaucitronCookiesList"></div>';
                        html += '    </div>';
                    } else {
                        html += '   </div>';
                    }
                    html += '</div>';
                }
                
                //tarteaucitron.addScript(tarteaucitron.cdn + 'advertising.js?v=' + tarteaucitron.version, '', function () {////
                    if (tarteaucitronNoAdBlocker === true || defaults.adblocker === false) {
                        div.id = 'tarteaucitronRoot';
                        body.appendChild(div, body);
                        div.innerHTML = html;
                
                        if (tarteaucitron.job !== undefined) {
                            tarteaucitron.job = tarteaucitron.cleanArray(tarteaucitron.job);
                            for (index = 0; index < tarteaucitron.job.length; index += 1) {
                                tarteaucitron.addService(tarteaucitron.job[index]);
                            }
                        } else {
                            tarteaucitron.job = [];////////
                        }
                
                        tarteaucitron.isAjax = true;
                        tarteaucitron.job.push = function (id) {
                    
                            // ie <9 hack
                            if (typeof tarteaucitron.job.indexOf === 'undefined') {
                                tarteaucitron.job.indexOf = function (obj, start) {
                                    var i,
                                        j = this.length;
                                    for (i = (start || 0); i < j; i += 1) {
                                        if (this[i] === obj) { return i; }
                                    }
                                    return -1;
                                };
                            }
                    
                            if (tarteaucitron.job.indexOf(id) === -1) {
                                Array.prototype.push.call(this, id);
                            }
                            tarteaucitron.launch[id] = false;
                            tarteaucitron.addService(id);
                        };
                
                        if (document.location.hash === tarteaucitron.hashtag && tarteaucitron.hashtag !== '') {
                            tarteaucitron.userInterface.openPanel();
                        }
                
                        tarteaucitron.cookie.number();
                        setInterval(tarteaucitron.cookie.number, 60000);
                    }
                //}, defaults.adblocker);
                
                if (defaults.adblocker === true) {
                    setTimeout(function () {
                        if (tarteaucitronNoAdBlocker === false) {
                            html = '<div id="tarteaucitronAlertBig" class="tarteaucitronAlertBig' + orientation + '" style="display:block">';
                            html += '   <span id="tarteaucitronDisclaimerAlert">';
                            html += '       ' + tarteaucitron.lang.adblock + '<br/>';
                            html += '       <b>' + tarteaucitron.lang.adblock_call + '</b>';
                            html += '   </span>';
                            html += '   <span id="tarteaucitronPersonalize" onclick="location.reload();">';
                            html += '       ' + tarteaucitron.lang.reload;
                            html += '   </span>';
                            html += '</div>';
                            html += '<div id="tarteaucitronPremium"></div>';
                            div.id = 'tarteaucitronRoot';
                            body.appendChild(div, body);
                            div.innerHTML = html;
                            tarteaucitron.pro('!adblocker=true');
                        } else {
                            tarteaucitron.pro('!adblocker=false');
                        }
                    }, 1500);
                }
        //     });
        // });
    },
    "serviceAllowed" : function (service) {
        var cookie = tarteaucitron.cookie.read();
        var isAllowed = (cookie.indexOf(service + '=true') >= 0) ? true : false;
        return isAllowed;
    },
    "addService": function (serviceId) {
        "use strict";
        var html = '',
            s = tarteaucitron.services,
            service = s[serviceId],
            cookie = tarteaucitron.cookie.read(),
            hostname = document.location.hostname,
            hostRef = document.referrer.split('/')[2],
            isNavigating = (hostRef === hostname) ? true : false,
            isAutostart = (!service.needConsent) ? true : false,
            isWaiting = (cookie.indexOf(service.key + '=wait') >= 0) ? true : false,
            isDenied = (cookie.indexOf(service.key + '=false') >= 0) ? true : false,
            isAllowed = (cookie.indexOf(service.key + '=true') >= 0) ? true : false,
            isResponded = (cookie.indexOf(service.key + '=false') >= 0 || cookie.indexOf(service.key + '=true') >= 0) ? true : false;

        if (tarteaucitron.added[service.key] !== true) {
            tarteaucitron.added[service.key] = true;
            
            html += '<div id="' + service.key + 'Line" class="tarteaucitronLine">';
            html += '   <div class="tarteaucitronName">';
            html += '       <b>' + service.name + '</b><br/>';
            html += '       <span id="tacCL' + service.key + '" class="tarteaucitronListCookies"></span><br/>';
            html += '       <a href="https://opt-out.ferank.eu/service/' + service.key + '/" target="_blank" rel="noopener">';
            html += '           ' + tarteaucitron.lang.more;
            html += '       </a>';
            html += '        - ';
            html += '       <a href="' + service.uri + '" target="_blank" rel="noopener">';
            html += '           ' + tarteaucitron.lang.source;
            html += '       </a>';
            html += '   </div>';
            html += '   <div class="tarteaucitronAsk">';
            html += '       <div id="' + service.key + 'Allowed" class="tarteaucitronAllow" onclick="tarteaucitron.userInterface.respond(this, true);">';
            html += '           &#10003; ' + tarteaucitron.lang.allow;
            html += '       </div> ';
            html += '       <div id="' + service.key + 'Denied" class="tarteaucitronDeny" onclick="tarteaucitron.userInterface.respond(this, false);">';
            html += '           &#10007; ' + tarteaucitron.lang.deny;
            html += '       </div>';
            html += '   </div>';
            html += '</div>';
           
            tarteaucitron.userInterface.css('tarteaucitronServicesTitle_' + service.type, 'display', 'block');
            
            if (document.getElementById('tarteaucitronServices_' + service.type) !== null) {
                document.getElementById('tarteaucitronServices_' + service.type).innerHTML += html;
            }
            
            tarteaucitron.userInterface.order(service.type);
        }

        // allow by default for non EU
        if (isResponded === false && tarteaucitron.user.bypass === true) {
            isAllowed = true;
            tarteaucitron.cookie.create(service.key, true);
        }
       
        if ((!isResponded && (isAutostart || (isNavigating && isWaiting)) && !tarteaucitron.highPrivacy) || isAllowed) {
            if (!isAllowed) {
                tarteaucitron.cookie.create(service.key, true);
            }
            if (tarteaucitron.launch[service.key] !== true) {
                tarteaucitron.launch[service.key] = true;
                service.js();
            }
            tarteaucitron.state[service.key] = true;
            tarteaucitron.userInterface.color(service.key, true);
        } else if (isDenied) {
            if (typeof service.fallback === 'function') {
                service.fallback();
            }
            tarteaucitron.state[service.key] = false;
            tarteaucitron.userInterface.color(service.key, false);
        } else if (!isResponded) {
            tarteaucitron.cookie.create(service.key, 'wait');
            if (typeof service.fallback === 'function') {
                service.fallback();
            }
            tarteaucitron.userInterface.color(service.key, 'wait');
            tarteaucitron.userInterface.openAlert();
        }

        tarteaucitron.cookie.checkCount(service.key);
    },
    "cleanArray": function cleanArray(arr) {
        "use strict";
        var i,
            len = arr.length,
            out = [],
            obj = {},
            s = tarteaucitron.services;
 
        for (i = 0; i < len; i += 1) {
            if (!obj[arr[i]]) {
                obj[arr[i]] = {};
                if (tarteaucitron.services[arr[i]] !== undefined) {
                    out.push(arr[i]);
                }
            }
        }
                
        out = out.sort(function (a, b) {
            if (s[a].type + s[a].key > s[b].type + s[b].key) { return 1; }
            if (s[a].type + s[a].key < s[b].type + s[b].key) { return -1; }
            return 0;
        });
                    
        return out;
    },
    "userInterface": {
        "css": function (id, property, value) {
            "use strict";
            if (document.getElementById(id) !== null) {
                document.getElementById(id).style[property] = value;
            }
        },
        "respondAll": function (status) {
            "use strict";
            var s = tarteaucitron.services,
                service,
                key,
                index = 0;
            
            for (index = 0; index < tarteaucitron.job.length; index += 1) {
                service = s[tarteaucitron.job[index]];
                key = service.key;
                if (tarteaucitron.state[key] !== status) {
                    if (status === false && tarteaucitron.launch[key] === true) {
                        tarteaucitron.reloadThePage = true;
                    }
                    if (tarteaucitron.launch[key] !== true && status === true) {
                        tarteaucitron.launch[key] = true;
                        tarteaucitron.services[key].js();
                    }
                    tarteaucitron.state[key] = status;
                    tarteaucitron.cookie.create(key, status);
                    tarteaucitron.userInterface.color(key, status);
                }
            }
        },
        "respond": function (el, status) {
            "use strict";
            var key = el.id.replace(new RegExp("(Eng[0-9]+|Allow|Deni)ed", "g"), '');

            // return if same state
            if (tarteaucitron.state[key] === status) {
                return;
            }
            
            if (status === false && tarteaucitron.launch[key] === true) {
                tarteaucitron.reloadThePage = true;
            }
        
            // if not already launched... launch the service
            if (status === true) {
                if (tarteaucitron.launch[key] !== true) {
                    tarteaucitron.launch[key] = true;
                    tarteaucitron.services[key].js();
                }
            }
            tarteaucitron.state[key] = status;
            tarteaucitron.cookie.create(key, status);
            tarteaucitron.userInterface.color(key, status);
        },
        "color": function (key, status) {
            "use strict";
            var gray = '#b7b5b5',
                greenDark = '#4db13f',
                greenLight = '#4db13f',
                redDark = '#d82626',
                redLight = '#FFE2E2',
                yellowDark = '#FBDA26',
                c = 'tarteaucitron',
                nbDenied = 0,
                nbPending = 0,
                nbAllowed = 0,
                sum = tarteaucitron.job.length,
                index;

            if (status === true) {
                //tarteaucitron.userInterface.css(key + 'Line', 'borderLeft', '5px solid ' + greenDark);
                tarteaucitron.userInterface.css(key + 'Allowed', 'backgroundColor', greenDark);
                tarteaucitron.userInterface.css(key + 'Denied', 'backgroundColor', gray);
            } else if (status === false) {
                //tarteaucitron.userInterface.css(key + 'Line', 'borderLeft', '5px solid ' + redDark);
                tarteaucitron.userInterface.css(key + 'Allowed', 'backgroundColor', gray);
                tarteaucitron.userInterface.css(key + 'Denied', 'backgroundColor', redDark);
            }

            // check if all services are allowed
            for (index = 0; index < sum; index += 1) {
                if (tarteaucitron.state[tarteaucitron.job[index]] === false) {
                    nbDenied += 1;
                } else if (tarteaucitron.state[tarteaucitron.job[index]] === undefined) {
                    nbPending += 1;
                } else if (tarteaucitron.state[tarteaucitron.job[index]] === true) {
                    nbAllowed += 1;
                }
            }
        
            tarteaucitron.userInterface.css(c + 'DotGreen', 'width', ((100 / sum) * nbAllowed) + '%');
            tarteaucitron.userInterface.css(c + 'DotYellow', 'width', ((100 / sum) * nbPending) + '%');
            tarteaucitron.userInterface.css(c + 'DotRed', 'width', ((100 / sum) * nbDenied) + '%');
            
            if (nbDenied === 0 && nbPending === 0) {
                tarteaucitron.userInterface.css(c + 'AllAllowed', 'backgroundColor', greenDark);
                tarteaucitron.userInterface.css(c + 'AllDenied', 'backgroundColor', gray);
            } else if (nbAllowed === 0 && nbPending === 0) {
                tarteaucitron.userInterface.css(c + 'AllAllowed', 'backgroundColor', gray);
                tarteaucitron.userInterface.css(c + 'AllDenied', 'backgroundColor', redDark);
            } else {
                tarteaucitron.userInterface.css(c + 'AllAllowed', 'backgroundColor', gray);
                tarteaucitron.userInterface.css(c + 'AllDenied', 'backgroundColor', gray);
            }
            
            // close the alert if all service have been reviewed
            if (nbPending === 0) {
                tarteaucitron.userInterface.closeAlert();
            }
            
            if (tarteaucitron.services[key].cookies.length > 0 && status === false) {
                tarteaucitron.cookie.purge(tarteaucitron.services[key].cookies);
            }
            
            if (status === true) {
                if (document.getElementById('tacCL' + key) !== null) {
                    document.getElementById('tacCL' + key).innerHTML = '...';
                }
                setTimeout(function () {
                    tarteaucitron.cookie.checkCount(key);
                }, 2500);
            } else {
                tarteaucitron.cookie.checkCount(key);
            }
        },
        "openPanel": function () {
            "use strict";
            tarteaucitron.userInterface.css('tarteaucitron', 'display', 'block');
            tarteaucitron.userInterface.css('tarteaucitronBack', 'display', 'block');
            tarteaucitron.userInterface.css('tarteaucitronCookiesListContainer', 'display', 'none');
            tarteaucitron.userInterface.jsSizing('main');
        },
        "closePanel": function () {
            "use strict";
            
            if (document.location.hash === tarteaucitron.hashtag) {
                document.location.hash = '';
            }
            tarteaucitron.userInterface.css('tarteaucitron', 'display', 'none');
            tarteaucitron.userInterface.css('tarteaucitronCookiesListContainer', 'display', 'none');
            
            tarteaucitron.fallback(['tarteaucitronInfoBox'], function (elem) {
                elem.style.display = 'none';
            }, true);
            
            if (tarteaucitron.reloadThePage === true) {
                window.location.reload();
            } else {
                tarteaucitron.userInterface.css('tarteaucitronBack', 'display', 'none');
            }
        },
        "openAlert": function () {
            "use strict";
            var c = 'tarteaucitron';
            tarteaucitron.userInterface.css(c + 'Percentage', 'display', 'block');
            tarteaucitron.userInterface.css(c + 'AlertSmall', 'display', 'none');
            tarteaucitron.userInterface.css(c + 'AlertBig',   'display', 'block');
        },
        "closeAlert": function () {
            "use strict";
            var c = 'tarteaucitron';
            tarteaucitron.userInterface.css(c + 'Percentage', 'display', 'none');
            tarteaucitron.userInterface.css(c + 'AlertSmall', 'display', 'block');
            tarteaucitron.userInterface.css(c + 'AlertBig',   'display', 'none');
            tarteaucitron.userInterface.jsSizing('box');
        },
        "toggleCookiesList": function () {
            "use strict";
            var div = document.getElementById('tarteaucitronCookiesListContainer');
            
            if (div === null) {
                return;
            }
            
            if (div.style.display !== 'block') {
                tarteaucitron.cookie.number();
                div.style.display = 'block';
                tarteaucitron.userInterface.jsSizing('cookie');
                tarteaucitron.userInterface.css('tarteaucitron', 'display', 'none');
                tarteaucitron.userInterface.css('tarteaucitronBack', 'display', 'block');
                tarteaucitron.fallback(['tarteaucitronInfoBox'], function (elem) {
                    elem.style.display = 'none';
                }, true);
            } else {
                div.style.display = 'none';
                tarteaucitron.userInterface.css('tarteaucitron', 'display', 'none');
                tarteaucitron.userInterface.css('tarteaucitronBack', 'display', 'none');
            }
        },
        "toggle": function (id, closeClass) {
            "use strict";
            var div = document.getElementById(id);
            
            if (div === null) {
                return;
            }
            
            if (closeClass !== undefined) {
                tarteaucitron.fallback([closeClass], function (elem) {
                    if (elem.id !== id) {
                        elem.style.display = 'none';
                    }
                }, true);
            }
            
            if (div.style.display !== 'block') {
                div.style.display = 'block';
            } else {
                div.style.display = 'none';
            }
        },
        "order": function (id) {
            "use strict";
            var main = document.getElementById('tarteaucitronServices_' + id),
                allDivs,
                store = [],
                i;

            if (main === null) {
                return;
            }
            
            allDivs = main.childNodes;
            
            if (typeof Array.prototype.map === 'function') {
                Array.prototype.map.call(main.children, Object).sort(function (a, b) {
                    if (tarteaucitron.services[a.id.replace(/Line/g, '')].name > tarteaucitron.services[b.id.replace(/Line/g, '')].name) { return 1; }
                    if (tarteaucitron.services[a.id.replace(/Line/g, '')].name < tarteaucitron.services[b.id.replace(/Line/g, '')].name) { return -1; }
                    return 0;
                }).forEach(function (element) {
                    main.appendChild(element);
                });
            }
        },
        "jsSizing": function (type) {
            "use strict";
            var scrollbarMarginRight = 10,
                scrollbarWidthParent,
                scrollbarWidthChild,
                servicesHeight,
                e = window,
                a = 'inner',
                windowInnerHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight,
                mainTop,
                mainHeight,
                closeButtonHeight,
                headerHeight,
                cookiesListHeight,
                cookiesCloseHeight,
                cookiesTitleHeight,
                paddingBox,
                alertSmallHeight,
                cookiesNumberHeight;
            
            if (type === 'box') {
                if (document.getElementById('tarteaucitronAlertSmall') !== null && document.getElementById('tarteaucitronCookiesNumber') !== null) {
                    
                    // reset
                    tarteaucitron.userInterface.css('tarteaucitronCookiesNumber', 'padding', '0px 10px');
                    
                    // calculate
                    alertSmallHeight = document.getElementById('tarteaucitronAlertSmall').offsetHeight;
                    cookiesNumberHeight = document.getElementById('tarteaucitronCookiesNumber').offsetHeight;
                    paddingBox = (alertSmallHeight - cookiesNumberHeight) / 2;
                    
                    // apply
                    tarteaucitron.userInterface.css('tarteaucitronCookiesNumber', 'padding', paddingBox + 'px 10px');
                }
            } else if (type === 'main') {

                // get the real window width for media query
                if (window.innerWidth === undefined) {
                    a = 'client';
                    e = document.documentElement || document.body;
                }

                // height of the services list container
                if (document.getElementById('tarteaucitron') !== null && document.getElementById('tarteaucitronClosePanel') !== null && document.getElementById('tarteaucitronMainLineOffset') !== null) {
                    
                    // reset
                    tarteaucitron.userInterface.css('tarteaucitronScrollbarParent', 'height', 'auto');
                    
                    // calculate
                    mainHeight = document.getElementById('tarteaucitron').offsetHeight;
                    closeButtonHeight = document.getElementById('tarteaucitronClosePanel').offsetHeight;
                    headerHeight = document.getElementById('tarteaucitronMainLineOffset').offsetHeight;
                    
                    // apply
                    servicesHeight = (mainHeight - closeButtonHeight - headerHeight + 1);
                    tarteaucitron.userInterface.css('tarteaucitronScrollbarParent', 'height', servicesHeight + 'px');
                }
                
                // align the main allow/deny button depending on scrollbar width
                if (document.getElementById('tarteaucitronScrollbarParent') !== null && document.getElementById('tarteaucitronScrollbarChild') !== null) {
                    
                    // media query
                    if (e[a + 'Width'] <= 479) {
                        tarteaucitron.userInterface.css('tarteaucitronScrollbarAdjust', 'marginLeft', '11px');
                    } else if (e[a + 'Width'] <= 767) {
                        scrollbarMarginRight = 12;
                    }
                    
                    scrollbarWidthParent = document.getElementById('tarteaucitronScrollbarParent').offsetWidth;
                    scrollbarWidthChild = document.getElementById('tarteaucitronScrollbarChild').offsetWidth;
                    tarteaucitron.userInterface.css('tarteaucitronScrollbarAdjust', 'marginRight', ((scrollbarWidthParent - scrollbarWidthChild) + scrollbarMarginRight) + 'px');
                }
                
                // center the main panel
                if (document.getElementById('tarteaucitron') !== null) {
                    
                    // media query
                    if (e[a + 'Width'] <= 767) {
                        mainTop = 0;
                    } else {
                        mainTop = ((windowInnerHeight - document.getElementById('tarteaucitron').offsetHeight) / 2) - 21;
                    }
                    
                    // correct
                    if (mainTop < 0) {
                        mainTop = 0;
                    }
                    
                    if (document.getElementById('tarteaucitronMainLineOffset') !== null) {
                        if (document.getElementById('tarteaucitron').offsetHeight < (windowInnerHeight / 2)) {
                            mainTop -= document.getElementById('tarteaucitronMainLineOffset').offsetHeight;
                        }
                    }
                    
                    // apply
                    tarteaucitron.userInterface.css('tarteaucitron', 'top', mainTop + 'px');
                }


            } else if (type === 'cookie') {
                
                // put cookies list at bottom
                if (document.getElementById('tarteaucitronAlertSmall') !== null) {
                    tarteaucitron.userInterface.css('tarteaucitronCookiesListContainer', 'bottom', (document.getElementById('tarteaucitronAlertSmall').offsetHeight) + 'px');
                }
                
                // height of cookies list
                if (document.getElementById('tarteaucitronCookiesListContainer') !== null) {
                    
                    // reset
                    tarteaucitron.userInterface.css('tarteaucitronCookiesList', 'height', 'auto');
                    
                    // calculate
                    cookiesListHeight = document.getElementById('tarteaucitronCookiesListContainer').offsetHeight;
                    cookiesCloseHeight = document.getElementById('tarteaucitronClosePanelCookie').offsetHeight;
                    cookiesTitleHeight = document.getElementById('tarteaucitronCookiesTitle').offsetHeight;
                    
                    // apply
                    tarteaucitron.userInterface.css('tarteaucitronCookiesList', 'height', (cookiesListHeight - cookiesCloseHeight - cookiesTitleHeight - 2) + 'px');
                }
            }
        }
    },
    "cookie": {
        "owner": {},
        "create": function (key, status) {
            "use strict";
            var d = new Date(),
                time = d.getTime(),
                expireTime = time + 31536000000, // 365 days
                regex = new RegExp("!" + key + "=(wait|true|false)", "g"),
                cookie = tarteaucitron.cookie.read().replace(regex, ""),
                value = 'tarteaucitron=' + cookie + '!' + key + '=' + status;
            
            if (tarteaucitron.cookie.read().indexOf(key + '=' + status) === -1) {
                tarteaucitron.pro('!' + key + '=' + status);
            }

            d.setTime(expireTime);
            document.cookie = value + '; expires=' + d.toGMTString() + '; path=/;';
        },
        "read": function () {
            "use strict";
            var nameEQ = "tarteaucitron=",
                ca = document.cookie.split(';'),
                i,
                c;

            for (i = 0; i < ca.length; i += 1) {
                c = ca[i];
                while (c.charAt(0) === ' ') {
                    c = c.substring(1, c.length);
                }
                if (c.indexOf(nameEQ) === 0) {
                    return c.substring(nameEQ.length, c.length);
                }
            }
            return '';
        },
        "purge": function (arr) {
            "use strict";
            var i;
            
            for (i = 0; i < arr.length; i += 1) {
                document.cookie = arr[i] + '=; expires=Thu, 01 Jan 2000 00:00:00 GMT; path=/;';
                document.cookie = arr[i] + '=; expires=Thu, 01 Jan 2000 00:00:00 GMT; path=/; domain=.' + location.hostname + ';';
                document.cookie = arr[i] + '=; expires=Thu, 01 Jan 2000 00:00:00 GMT; path=/; domain=.' + location.hostname.split('.').slice(-2).join('.') + ';';
            }
        },
        "checkCount": function (key) {
            "use strict";
            var arr = tarteaucitron.services[key].cookies,
                nb = arr.length,
                nbCurrent = 0,
                html = '',
                i,
                status = document.cookie.indexOf(key + '=true');
            
            if (status >= 0 && nb === 0) {
                html += tarteaucitron.lang.useNoCookie;
            } else if (status >= 0) {
                for (i = 0; i < nb; i += 1) {
                    if (document.cookie.indexOf(arr[i] + '=') !== -1) {
                        nbCurrent += 1;
                        if (tarteaucitron.cookie.owner[arr[i]] === undefined) {
                            tarteaucitron.cookie.owner[arr[i]] = [];
                        }
                        if (tarteaucitron.cookie.crossIndexOf(tarteaucitron.cookie.owner[arr[i]], tarteaucitron.services[key].name) === false) {
                            tarteaucitron.cookie.owner[arr[i]].push(tarteaucitron.services[key].name);
                        }
                    }
                }
                
                if (nbCurrent > 0) {
                    html += tarteaucitron.lang.useCookieCurrent + ' ' + nbCurrent + ' cookie';
                    if (nbCurrent > 1) {
                        html += 's';
                    }
                    html += '.';
                } else {
                    html += tarteaucitron.lang.useNoCookie;
                }
            } else if (nb === 0) {
                html = tarteaucitron.lang.noCookie;
            } else {
                html += tarteaucitron.lang.useCookie + ' ' + nb + ' cookie';
                if (nb > 1) {
                    html += 's';
                }
                html += '.';
            }
            
            if (document.getElementById('tacCL' + key) !== null) {
                document.getElementById('tacCL' + key).innerHTML = html;
            }
        },
        "crossIndexOf": function (arr, match) {
            "use strict";
            var i;
            for (i = 0; i < arr.length; i += 1) {
                if (arr[i] === match) {
                    return true;
                }
            }
            return false;
        },
        "number": function () {
            "use strict";
            var cookies = document.cookie.split(';'),
                nb = (document.cookie !== '') ? cookies.length : 0,
                html = '',
                i,
                name,
                namea,
                nameb,
                c,
                d,
                s = (nb > 1) ? 's' : '',
                savedname,
                regex = /^https?\:\/\/([^\/?#]+)(?:[\/?#]|$)/i,
                regexedDomain = (tarteaucitron.cdn.match(regex) !== null) ? tarteaucitron.cdn.match(regex)[1] : tarteaucitron.cdn,
                host = (tarteaucitron.domain !== undefined) ? tarteaucitron.domain : regexedDomain;
            
            cookies = cookies.sort(function (a, b) {
                namea = a.split('=', 1).toString().replace(/ /g, '');
                nameb = b.split('=', 1).toString().replace(/ /g, '');
                c = (tarteaucitron.cookie.owner[namea] !== undefined) ? tarteaucitron.cookie.owner[namea] : '0';
                d = (tarteaucitron.cookie.owner[nameb] !== undefined) ? tarteaucitron.cookie.owner[nameb] : '0';
                if (c + a > d + b) { return 1; }
                if (c + a < d + b) { return -1; }
                return 0;
            });
            
            if (document.cookie !== '') {
                for (i = 0; i < nb; i += 1) {
                    name = cookies[i].split('=', 1).toString().replace(/ /g, '');
                    if (tarteaucitron.cookie.owner[name] !== undefined && tarteaucitron.cookie.owner[name].join(' // ') !== savedname) {
                        savedname = tarteaucitron.cookie.owner[name].join(' // ');
                        html += '<div class="tarteaucitronHidden">';
                        html += '     <div class="tarteaucitronTitle">';
                        html += '        ' + tarteaucitron.cookie.owner[name].join(' // ');
                        html += '    </div>';
                        html += '</div>';
                    } else if (tarteaucitron.cookie.owner[name] === undefined && host !== savedname) {
                        savedname = host;
                        html += '<div class="tarteaucitronHidden">';
                        html += '     <div class="tarteaucitronTitle">';
                        html += '        ' + host;
                        html += '    </div>';
                        html += '</div>';
                    }
                    html += '<div class="tarteaucitronCookiesListMain">';
                    html += '    <div class="tarteaucitronCookiesListLeft"><a href="#" onclick="tarteaucitron.cookie.purge([\'' + cookies[i].split('=', 1) + '\']);tarteaucitron.cookie.number();tarteaucitron.userInterface.jsSizing(\'cookie\');return false"><b>&times;</b></a> <b>' + name + '</b>';
                    html += '    </div>';
                    html += '    <div class="tarteaucitronCookiesListRight">' + cookies[i].split('=').slice(1).join('=') + '</div>';
                    html += '</div>';
                }
            } else {
                html += '<div class="tarteaucitronCookiesListMain">';
                html += '    <div class="tarteaucitronCookiesListLeft"><b>-</b></div>';
                html += '    <div class="tarteaucitronCookiesListRight"></div>';
                html += '</div>';
            }
            
            html += '<div class="tarteaucitronHidden" style="height:20px;display:block"></div>';
            
            if (document.getElementById('tarteaucitronCookiesList') !== null) {
                document.getElementById('tarteaucitronCookiesList').innerHTML = html;
            }
            
            if (document.getElementById('tarteaucitronCookiesNumber') !== null) {
                document.getElementById('tarteaucitronCookiesNumber').innerHTML = nb;
            }
            
            if (document.getElementById('tarteaucitronCookiesNumberBis') !== null) {
                document.getElementById('tarteaucitronCookiesNumberBis').innerHTML = nb + ' cookie' + s;
            }
            
            for (i = 0; i < tarteaucitron.job.length; i += 1) {
                tarteaucitron.cookie.checkCount(tarteaucitron.job[i]);
            }
        }
    },
    "getLanguage": function () {
        "use strict";
        if (!navigator) { return 'en'; }
        
        var availableLanguages = 'cs,en,fr,es,it,de,pt,pl,ru',
            defaultLanguage = 'en',
            lang = navigator.language || navigator.browserLanguage ||
                navigator.systemLanguage || navigator.userLang || null,
            userLanguage = lang.substr(0, 2);

        if (tarteaucitronForceLanguage !== '') {
            if (availableLanguages.indexOf(tarteaucitronForceLanguage) !== -1) {
                return tarteaucitronForceLanguage;
            }
        }
        
        if (availableLanguages.indexOf(userLanguage) === -1) {
            return defaultLanguage;
        }
        return userLanguage;
    },
    "getLocale": function () {
        "use strict";
        if (!navigator) { return 'en_US'; }
        
        var lang = navigator.language || navigator.browserLanguage ||
                navigator.systemLanguage || navigator.userLang || null,
            userLanguage = lang.substr(0, 2);
        
        if (userLanguage === 'fr') {
            return 'fr_FR';
        } else if (userLanguage === 'en') {
            return 'en_US';
        } else if (userLanguage === 'de') {
            return 'de_DE';
        } else if (userLanguage === 'es') {
            return 'es_ES';
        } else if (userLanguage === 'it') {
            return 'it_IT';
        } else if (userLanguage === 'pt') {
            return 'pt_PT';
        } else {
            return 'en_US';
        }
    },
    "addScript": function (url, id, callback, execute, attrName, attrVal) {
        "use strict";
        var script,
            done = false;

        if (execute === false) {
            if (typeof callback === 'function') {
                callback();
            }
        } else {
            script = document.createElement('script');
            script.type = 'text/javascript';
            script.id = (id !== undefined) ? id : '';
            script.async = true;
            script.src = url;
            
            if (attrName !== undefined && attrVal !== undefined) {
                script.setAttribute(attrName, attrVal);
            }

            if (typeof callback === 'function') {
                script.onreadystatechange = script.onload = function () {
                    var state = script.readyState;
                    if (!done && (!state || /loaded|complete/.test(state))) {
                        done = true;
                        callback();
                    }
                };
            }
            document.getElementsByTagName('head')[0].appendChild(script);
        }
    },
    "makeAsync": {
        "antiGhost": 0,
        "buffer": '',
        "init": function (url, id) {
            "use strict";
            var savedWrite = document.write,
                savedWriteln = document.writeln;

            document.write = function (content) {
                tarteaucitron.makeAsync.buffer += content;
            };
            document.writeln = function (content) {
                tarteaucitron.makeAsync.buffer += content.concat("\n");
            };
        
            setTimeout(function () {
                document.write = savedWrite;
                document.writeln = savedWriteln;
            }, 20000);
            
            tarteaucitron.makeAsync.getAndParse(url, id);
        },
        "getAndParse": function (url, id) {
            "use strict";
            if (tarteaucitron.makeAsync.antiGhost > 9) {
                tarteaucitron.makeAsync.antiGhost = 0;
                return;
            }
            tarteaucitron.makeAsync.antiGhost += 1;
            tarteaucitron.addScript(url, '', function () {
                if (document.getElementById(id) !== null) {
                    document.getElementById(id).innerHTML += "<span style='display:none'>&nbsp;</span>" + tarteaucitron.makeAsync.buffer;
                    tarteaucitron.makeAsync.buffer = '';
                    tarteaucitron.makeAsync.execJS(id);
                }
            });
        },
        "execJS": function (id) {
            /* not strict because third party scripts may have errors */
            var i,
                scripts,
                childId,
                type;

            if (document.getElementById(id) === null) {
                return;
            }
            
            scripts = document.getElementById(id).getElementsByTagName('script');
            for (i = 0; i < scripts.length; i += 1) {
                type = (scripts[i].getAttribute('type') !== null) ? scripts[i].getAttribute('type') : '';
                if (type === '') {
                    type = (scripts[i].getAttribute('language') !== null) ? scripts[i].getAttribute('language') : '';
                }
                if (scripts[i].getAttribute('src') !== null && scripts[i].getAttribute('src') !== '') {
                    childId = id + Math.floor(Math.random() * 99999999999);
                    document.getElementById(id).innerHTML += '<div id="' + childId + '"></div>';
                    tarteaucitron.makeAsync.getAndParse(scripts[i].getAttribute('src'), childId);
                } else if (type.indexOf('javascript') !== -1 || type === '') {
                    eval(scripts[i].innerHTML);
                }
            }
        }
    },
    "fallback": function (matchClass, content, noInner) {
        "use strict";

        var elems = document.getElementsByTagName('*'),
            i,
            index = 0;
        for (i in elems) {
            if (elems[i] !== undefined) {
                for (index = 0; index < matchClass.length; index += 1) {
                    if ((' ' + elems[i].className + ' ')
                            .indexOf(' ' + matchClass[index] + ' ') > -1) {

                        if (typeof content === 'function') {
                            if (noInner === true) {
                                content(elems[i]);
                            } else {
                                elems[i].innerHTML = content(elems[i]);
                            }
                        } else {
                            elems[i].innerHTML = content;
                        }
                    }
                }
            }
        }
    },
    "engage": function (id) {
        "use strict";
        var html = '',
            r = Math.floor(Math.random() * 100000);
                
        html += '<div class="tac_activate">';
        html += '   <div class="tac_float">';
        html += '      <b>' + tarteaucitron.services[id].name + '</b> ' + tarteaucitron.lang.fallback;
        html += '      <div class="tarteaucitronAllow" id="Eng' + r + 'ed' + id + '" onclick="tarteaucitron.userInterface.respond(this, true);">';
        html += '          &#10003; ' + tarteaucitron.lang.allow;
        html += '       </div>';
        html += '   </div>';
        html += '</div>';
        
        return html;
    },
    "extend": function (a, b) {
        "use strict";
        var prop;
        for (prop in b) {
            if (b.hasOwnProperty(prop)) {
                a[prop] = b[prop];
            }
        }
    },
    "proTemp": '',
    "proTimer": function () {
        "use strict";
        setTimeout(tarteaucitron.proPing, 1000);
    },
    "pro": function (list) {
        "use strict";
        tarteaucitron.proTemp += list;
        clearTimeout(tarteaucitron.proTimer);
        tarteaucitron.proTimer = setTimeout(tarteaucitron.proPing, 2500);
    },
    "proPing": function () {
        "use strict";
        if (tarteaucitron.uuid !== '' && tarteaucitron.uuid !== undefined && tarteaucitron.proTemp !== '') {
            var div = document.getElementById('tarteaucitronPremium'),
                timestamp = new Date().getTime(),
                url = '//opt-out.ferank.eu/premium.php?';
            
            if (div === null) {
                return;
            }
            
            url += 'domain=' + tarteaucitron.domain + '&';
            url += 'uuid=' + tarteaucitron.uuid + '&';
            url += 'c=' + encodeURIComponent(tarteaucitron.proTemp) + '&';
            url += '_' + timestamp;
            
            div.innerHTML = '<img src="' + url + '" style="display:none" />';
            
            tarteaucitron.proTemp = '';
        }
        
        tarteaucitron.cookie.number();
    }
};

tarteaucitronNoAdBlocker = true;
/*global tarteaucitron, ga, Shareaholic, stLight, clicky, top, google, Typekit, FB, ferankReady, IN, stButtons, twttr, PCWidget*/
/*jslint regexp: true, nomen: true*/

// generic iframe
tarteaucitron.services.iframe = {
    "key": "iframe",
    "type": "other",
    "name": "Web content",
    "uri": "",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tac_iframe'], function (x) {
            var width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                url = x.getAttribute("data-url");
            
            return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="0" scrolling="no" allowtransparency allowfullscreen></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'iframe';
        tarteaucitron.fallback(['tac_iframe'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// addthis
tarteaucitron.services.addthis = {
    "key": "addthis",
    "type": "social",
    "name": "AddThis",
    "uri": "https://www.addthis.com/privacy/privacy-policy#publisher-visitors",
    "needConsent": true,
    "cookies": ['__atuvc', '__atuvs'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.addthisPubId === undefined) {
            return;
        }
        if (tarteaucitron.isAjax === true) {
            window.addthis = null;
            window._adr = null;
            window._atc = null;
            window._atd = null;
            window._ate = null;
            window._atr = null;
            window._atw = null;
        }
        tarteaucitron.fallback(['addthis_sharing_toolbox'], '');
        tarteaucitron.addScript('//s7.addthis.com/js/300/addthis_widget.js#pubid=' + tarteaucitron.user.addthisPubId);
    },
    "fallback": function () {
        "use strict";
        var id = 'addthis';
        tarteaucitron.fallback(['addthis_sharing_toolbox'], tarteaucitron.engage(id));
    }
};

// addtoanyfeed
tarteaucitron.services.addtoanyfeed = {
    "key": "addtoanyfeed",
    "type": "social",
    "name": "AddToAny (feed)",
    "uri": "https://www.addtoany.com/privacy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.addtoanyfeedUri === undefined) {
            return;
        }
        tarteaucitron.user.addtoanyfeedSubscribeLink = 'https://www.addtoany.com/subscribe?linkurl=' + tarteaucitron.user.addtoanyfeedUri;
        window.a2a_config = window.a2a_config || {};
        window.a2a_config.linkurl = tarteaucitron.user.addtoanyfeedUri;
        tarteaucitron.addScript('//static.addtoany.com/menu/feed.js');
    },
    "fallback": function () {
        "use strict";
        tarteaucitron.user.addtoanyfeedSubscribeLink = 'https://www.addtoany.com/subscribe?linkurl=' + tarteaucitron.user.addtoanyfeedUri;
    }
};

// addtoanyshare
tarteaucitron.services.addtoanyshare = {
    "key": "addtoanyshare",
    "type": "social",
    "name": "AddToAny (share)",
    "uri": "https://www.addtoany.com/privacy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tac_addtoanyshare'], '');
        tarteaucitron.addScript('//static.addtoany.com/menu/page.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'addtoanyshare';
        tarteaucitron.fallback(['tac_addtoanyshare'], tarteaucitron.engage(id));
    }
};

// alexa
tarteaucitron.services.alexa = {
    "key": "alexa",
    "type": "analytic",
    "name": "Alexa",
    "uri": "https://www.alexa.com/help/privacy",
    "needConsent": true,
    "cookies": ['__asc', '__auc'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.alexaAccountID === undefined) {
            return;
        }
        window._atrk_opts = {
            atrk_acct: tarteaucitron.user.alexaAccountID,
            domain: window.location.hostname.match(/[^\.]*\.[^.]*$/)[0],
            dynamic: true
        };
        tarteaucitron.addScript('https://d31qbv1cthcecs.cloudfront.net/atrk.js');
    }
};

// amazon
tarteaucitron.services.amazon = {
    "key": "amazon",
    "type": "ads",
    "name": "Amazon",
    "uri": "https://www.amazon.fr/gp/help/customer/display.html?ie=UTF8&*Version*=1&*entries*=0&nodeId=201149360",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['amazon_product'], function (x) {
            var amazonId = x.getAttribute("amazonid"),
                productId = x.getAttribute("productid"),
                url = '//ws-eu.amazon-adsystem.com/widgets/q?ServiceVersion=20070822&OneJS=1&Operation=GetAdHtml&MarketPlace=' + tarteaucitron.getLanguage().toUpperCase() + '&source=ss&ref=ss_til&ad_type=product_link&tracking_id=' + amazonId + '&marketplace=amazon&region=' + tarteaucitron.getLanguage().toUpperCase() + '&placement=' + productId + '&asins=' + productId + '&show_border=true&link_opens_in_new_window=true',
                iframe = '<iframe style="width:120px;height:240px;" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" src="' + url + '"></iframe>';
            
            return iframe;
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'amazon';
        tarteaucitron.fallback(['amazon_product'], tarteaucitron.engage(id));
    }
};

// calameo
tarteaucitron.services.calameo = {
    "key": "calameo",
    "type": "video",
    "name": "Calameo",
    "uri": "https://fr.calameo.com/privacy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['calameo-canvas'], function (x) {
            var id = x.getAttribute("data-id"),
                width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                url = '//v.calameo.com/?bkcode=' + id;
            
            return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="0" scrolling="no" allowtransparency allowfullscreen></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'calameo';
        tarteaucitron.fallback(['calameo-canvas'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// clicky
tarteaucitron.services.clicky = {
    "key": "clicky",
    "type": "analytic",
    "name": "Clicky",
    "uri": "https://clicky.com/terms",
    "needConsent": true,
    "cookies": ['_jsuid', '_eventqueue', '_referrer_og', '_utm_og', '_first_pageview', 'clicky_olark', 'no_trackyy_' + tarteaucitron.user.clickyId, 'unpoco_' + tarteaucitron.user.clickyId, 'heatmaps_g2g_' + tarteaucitron.user.clickyId],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.clickyId === undefined) {
            return;
        }
        tarteaucitron.addScript('//static.getclicky.com/js', '', function () {
            if (typeof clicky.init === 'function') {
                clicky.init(tarteaucitron.user.clickyId);
            }
            if (typeof tarteaucitron.user.clickyMore === 'function') {
                tarteaucitron.user.clickyMore();
            }
        });
    }
};

// clicmanager
tarteaucitron.services.clicmanager = {
    "key": "clicmanager",
    "type": "ads",
    "name": "Clicmanager",
    "uri": "http://www.clicmanager.fr/infos_legales.php",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        var uniqIds = [],
            i,
            uri;

        tarteaucitron.fallback(['clicmanager-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            return '<div id="' + uniqId + '" c="' + x.getAttribute('c') + '" s="' + x.getAttribute('s') + '" t="' + x.getAttribute('t') + '"></div>';
        });
        
        for (i = 0; i < uniqIds.length; i += 1) {
            uri = '//ads.clicmanager.fr/exe.php?';
            uri += 'c=' + document.getElementById(uniqIds[i]).getAttribute('c') + '&';
            uri += 's=' + document.getElementById(uniqIds[i]).getAttribute('s') + '&';
            uri += 't=' + document.getElementById(uniqIds[i]).getAttribute('t');
            
            tarteaucitron.makeAsync.init(uri, uniqIds[i]);
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'clicmanager';
        tarteaucitron.fallback(['clicmanager-canvas'], tarteaucitron.engage(id));
    }
};

// crazyegg
tarteaucitron.services.crazyegg = {
    "key": "crazyegg",
    "type": "analytic",
    "name": "Crazy Egg",
    "uri": "https://www.crazyegg.com/privacy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        
        if (tarteaucitron.user.crazyeggId === undefined) {
            return;
        }
        
        tarteaucitron.addScript('//script.crazyegg.com/pages/scripts/' + tarteaucitron.user.crazyeggId.substr(0, 4) + '/' + tarteaucitron.user.crazyeggId.substr(4, 4) + '.js');
    }
};

// criteo
tarteaucitron.services.criteo = {
    "key": "criteo",
    "type": "ads",
    "name": "Criteo",
    "uri": "http://www.criteo.com/privacy/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        document.MAX_ct0 = '';
        var uniqIds = [],
            i,
            uri;

        tarteaucitron.fallback(['criteo-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            return '<div id="' + uniqId + '" zoneid="' + x.getAttribute('zoneid') + '"></div>';
        });
        
        for (i = 0; i < uniqIds.length; i += 1) {
            uri = '//cas.criteo.com/delivery/ajs.php?';
            uri += 'zoneid=' + document.getElementById(uniqIds[i]).getAttribute('zoneid');
            uri += '&nodis=1&cb=' + Math.floor(Math.random() * 99999999999);
            uri += '&loc=' + encodeURI(window.location);
            uri += (document.MAX_used !== ',') ? '&exclude=' + document.MAX_used : '';
            uri += (document.charset !== undefined ? '&charset=' + document.charset : '');
            uri += (document.characterSet !== undefined ? '&charset=' + document.characterSet : '');
            uri += (document.referrer !== undefined) ? '&referer=' + encodeURI(document.referrer) : '';
            uri += (document.context !== undefined) ? '&context=' + encodeURI(document.context) : '';
            uri += ((document.MAX_ct0 !== undefined) && (document.MAX_ct0.substring(0, 4) === 'http')) ? '&ct0=' + encodeURI(document.MAX_ct0) : '';
            uri += (document.mmm_fo !== undefined) ? '&mmm_fo=1' : '';
            
            tarteaucitron.makeAsync.init(uri, uniqIds[i]);
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'criteo';
        tarteaucitron.fallback(['criteo-canvas'], tarteaucitron.engage(id));
    }
};

// dailymotion
tarteaucitron.services.dailymotion = {
    "key": "dailymotion",
    "type": "video",
    "name": "Dailymotion",
    "uri": "https://www.dailymotion.com/legal/privacy",
    "needConsent": true,
    "cookies": ['ts', 'dmvk', 'hist', 'v1st', 's_vi'],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['dailymotion_player'], function (x) {
            return tarteaucitron.services.dailymotion.jsOnElement(x);
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'dailymotion';
        tarteaucitron.fallback(['dailymotion_player'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    },
    "jsOnElement" : function (x) {//
        var video_id = x.getAttribute("videoID"),
            video_width = x.getAttribute("width"),
            frame_width = 'width=',
            video_height = x.getAttribute("height"),
            frame_height = 'height=',
            video_frame,
            params = 'info=' + x.getAttribute("showinfo") + '&autoPlay=' + x.getAttribute("autoplay");

        if (video_id === undefined) {
            return "";
        }
        if (video_width !== undefined) {
            frame_width += '"' + video_width + '" ';
        } else {
            frame_width += '"" ';
        }
        if (video_height !== undefined) {
            frame_height +=  '"' + video_height + '" ';
        } else {
            frame_height += '"" ';
        }
        video_frame = '<iframe src="https://www.dailymotion.com/embed/video/' + video_id + '?' + params + '" ' + frame_width + frame_height + ' frameborder="0" allowfullscreen></iframe>';
        return video_frame;
    }
};

// dating affiliation
tarteaucitron.services.datingaffiliation = {
    "key": "datingaffiliation",
    "type": "ads",
    "name": "Dating Affiliation",
    "uri": "http://www.dating-affiliation.com/conditions-generales.php",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['datingaffiliation-canvas'], function (x) {
            var comfrom = x.getAttribute("data-comfrom"),
                r = x.getAttribute("data-r"),
                p = x.getAttribute("data-p"),
                cf0 = x.getAttribute("data-cf0"),
                langue = x.getAttribute("data-langue"),
                forward_affiliate = x.getAttribute("data-forwardAffiliate"),
                cf2 = x.getAttribute("data-cf2"),
                cfsa2 = x.getAttribute("data-cfsa2"),
                width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                url = 'http://www.tools-affil2.com/rotaban/ban.php?' + comfrom;
            
            return '<iframe src="' + url + '&r=' + r + '&p=' + p + '&cf0=' + cf0 + '&langue=' + langue + '&forward_affiliate=' + forward_affiliate + '&cf2=' + cf2 + '&cfsa2=' + cfsa2 + '" width="' + width + '" height="' + height + '" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'datingaffiliation';
        tarteaucitron.fallback(['datingaffiliation-canvas'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// dating affiliation popup
tarteaucitron.services.datingaffiliationpopup = {
    "key": "datingaffiliationpopup",
    "type": "ads",
    "name": "Dating Affiliation (Pop Up)",
    "uri": "http://www.dating-affiliation.com/conditions-generales.php",
    "needConsent": true,
    "cookies": ['__utma', '__utmb', '__utmc', '__utmt_Tools', '__utmv', '__utmz', '_ga', '_gat', '_gat_UA-65072040-17', '__da-pu-xflirt-ID-pc-o169'],
    "js": function () {
        "use strict";
        var uniqIds = [],
            i,
            uri;

        tarteaucitron.fallback(['datingaffiliationpopup-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            return '<div id="' + uniqId + '" uri="' + x.getAttribute('uri') + '" comfrom="' + x.getAttribute('comfrom') + '" promo="' + x.getAttribute('promo') + '" productid="' + x.getAttribute('productid') + '" submitconfig="' + x.getAttribute('submitconfig') + '" ur="' + x.getAttribute('ur') + '" brand="' + x.getAttribute('brand') + '" lang="' + x.getAttribute('lang') + '" cf0="' + x.getAttribute('cf0') + '" cf2="' + x.getAttribute('cf2') + '" subid1="' + x.getAttribute('subid1') + '" cfsa2="' + x.getAttribute('cfsa2') + '" subid2="' + x.getAttribute('subid2') + '" nicheid="' + x.getAttribute('nicheid') + '" degreid="' + x.getAttribute('degreid') + '" bt="' + x.getAttribute('bt') + '" vis="' + x.getAttribute('vis') + '" hid="' + x.getAttribute('hid') + '" snd="' + x.getAttribute('snd') + '" aabd="' + x.getAttribute('aabd') + '" aabs="' + x.getAttribute('aabs') + '"></div>';
        });
        
        for (i = 0; i < uniqIds.length; i += 1) {
            uri = 'http://www.promotools.biz/da/popunder/script.php?';
            uri += 'comfrom=' + document.getElementById(uniqIds[i]).getAttribute('comfrom') + '&';
            uri += 'promo=' + document.getElementById(uniqIds[i]).getAttribute('promo') + '&';
            uri += 'product_id=' + document.getElementById(uniqIds[i]).getAttribute('productid') + '&';
            uri += 'submitconfig=' + document.getElementById(uniqIds[i]).getAttribute('submitconfig') + '&';
            uri += 'ur=' + document.getElementById(uniqIds[i]).getAttribute('ur') + '&';
            uri += 'brand=' + document.getElementById(uniqIds[i]).getAttribute('brand') + '&';
            uri += 'lang=' + document.getElementById(uniqIds[i]).getAttribute('lang') + '&';
            uri += 'cf0=' + document.getElementById(uniqIds[i]).getAttribute('cf0') + '&';
            uri += 'cf2=' + document.getElementById(uniqIds[i]).getAttribute('cf2') + '&';
            uri += 'subid1=' + document.getElementById(uniqIds[i]).getAttribute('subid1') + '&';
            uri += 'cfsa2=' + document.getElementById(uniqIds[i]).getAttribute('cfsa2') + '&';
            uri += 'subid2=' + document.getElementById(uniqIds[i]).getAttribute('subid2') + '&';
            uri += 'nicheId=' + document.getElementById(uniqIds[i]).getAttribute('nicheid') + '&';
            uri += 'degreId=' + document.getElementById(uniqIds[i]).getAttribute('degreid') + '&';
            uri += 'bt=' + document.getElementById(uniqIds[i]).getAttribute('bt') + '&';
            uri += 'vis=' + document.getElementById(uniqIds[i]).getAttribute('vis') + '&';
            uri += 'hid=' + document.getElementById(uniqIds[i]).getAttribute('hid') + '&';
            uri += 'snd=' + document.getElementById(uniqIds[i]).getAttribute('snd') + '&';
            uri += 'aabd=' + document.getElementById(uniqIds[i]).getAttribute('aabd') + '&';
            uri += 'aabs=' + document.getElementById(uniqIds[i]).getAttribute('aabs');
            
            tarteaucitron.makeAsync.init(uri, uniqIds[i]);
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'datingaffiliationpopup';
        tarteaucitron.fallback(['datingaffiliationpopup-canvas'], tarteaucitron.engage(id));
    }
};

// disqus
tarteaucitron.services.disqus = {
    "key": "disqus",
    "type": "comment",
    "name": "Disqus",
    "uri": "https://help.disqus.com/customer/portal/articles/466259-privacy-policy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.disqusShortname === undefined) {
            return;
        }
        tarteaucitron.addScript('//' + tarteaucitron.user.disqusShortname + '.disqus.com/embed.js');
        tarteaucitron.addScript('//' + tarteaucitron.user.disqusShortname + '.disqus.com/count.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'disqus';
        
        if (document.getElementById('disqus_thread')) {
            document.getElementById('disqus_thread').innerHTML = tarteaucitron.engage(id);
        }
    }
};

// ekomi
tarteaucitron.services.ekomi = {
    "key": "ekomi",
    "type": "social",
    "name": "eKomi",
    "uri": "http://www.ekomi-us.com/us/privacy/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.ekomiCertId === undefined) {
            return;
        }
        window.eKomiIntegrationConfig = [
            {certId: tarteaucitron.user.ekomiCertId}
        ];
        tarteaucitron.addScript('//connect.ekomi.de/integration_1410173009/' + tarteaucitron.user.ekomiCertId + '.js');
    }
};

// etracker
tarteaucitron.services.etracker = {
    "key": "etracker",
    "type": "analytic",
    "name": "eTracker",
    "uri": "https://www.etracker.com/en/data-protection.html",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.etracker === undefined) {
            return;
        }

        tarteaucitron.addScript('//static.etracker.com/code/e.js', '_etLoader', function () {}, true, "data-secure-code", tarteaucitron.user.etracker);
    }
};

// facebook
tarteaucitron.services.facebook = {
    "key": "facebook",
    "type": "social",
    "name": "Facebook",
    "uri": "https://www.facebook.com/policies/cookies/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['fb-post', 'fb-follow', 'fb-activity', 'fb-send', 'fb-share-button', 'fb-like'], '');
        tarteaucitron.addScript('//connect.facebook.net/' + tarteaucitron.getLocale() + '/sdk.js#xfbml=1&version=v2.0', 'facebook-jssdk');
        if (tarteaucitron.isAjax === true) {
            if (typeof FB !== "undefined") {
                FB.XFBML.parse();
            }
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'facebook';
        tarteaucitron.fallback(['fb-post', 'fb-follow', 'fb-activity', 'fb-send', 'fb-share-button', 'fb-like'], tarteaucitron.engage(id));
    }
};

// facebooklikebox
tarteaucitron.services.facebooklikebox = {
    "key": "facebooklikebox",
    "type": "social",
    "name": "Facebook (like box)",
    "uri": "https://www.facebook.com/policies/cookies/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['fb-like-box', 'fb-page'], '');
        tarteaucitron.addScript('//connect.facebook.net/' + tarteaucitron.getLocale() + '/sdk.js#xfbml=1&version=v2.3', 'facebook-jssdk');
        if (tarteaucitron.isAjax === true) {
            if (typeof FB !== "undefined") {
                FB.XFBML.parse();
            }
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'facebooklikebox';
        tarteaucitron.fallback(['fb-like-box', 'fb-page'], tarteaucitron.engage(id));
    }
};

// facebookcomment
tarteaucitron.services.facebookcomment = {
    "key": "facebookcomment",
    "type": "comment",
    "name": "Facebook (commentaire)",
    "uri": "https://www.facebook.com/policies/cookies/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['fb-comments'], '');
        tarteaucitron.addScript('//connect.facebook.net/' + tarteaucitron.getLocale() + '/sdk.js#xfbml=1&version=v2.0', 'facebook-jssdk');
        if (tarteaucitron.isAjax === true) {
            if (typeof FB !== "undefined") {
                FB.XFBML.parse();
            }
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'facebookcomment';
        tarteaucitron.fallback(['fb-comments'], tarteaucitron.engage(id));
    }
};

// ferank
tarteaucitron.services.ferank = {
    "key": "ferank",
    "type": "analytic",
    "name": "FERank",
    "uri": "https://www.ferank.fr/respect-vie-privee/#mesureaudience",
    "needConsent": false,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('//static.ferank.fr/pixel.js', '', function () {
            if (typeof tarteaucitron.user.ferankMore === 'function') {
                tarteaucitron.user.ferankMore();
            }
        });
    }
};

// ferank pub
tarteaucitron.services.ferankpub = {
    "key": "ferankpub",
    "type": "ads",
    "name": "FERank (pub)",
    "uri": "https://www.ferank.fr/respect-vie-privee/#regiepublicitaire",
    "needConsent": false,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('//static.ferank.fr/publicite.async.js');
        if (tarteaucitron.isAjax === true) {
            if (typeof ferankReady === 'function') {
                ferankReady();
            }
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'ferankpub';
        tarteaucitron.fallback(['ferank-publicite'], tarteaucitron.engage(id));
    }
};

// get+
tarteaucitron.services.getplus = {
    "key": "getplus",
    "type": "analytic",
    "name": "Get+",
    "uri": "http://www.getplus.fr/Conditions-generales-de-vente_a226.html",
    "needConsent": true,
    "cookies": ['_first_pageview', '_jsuid', 'no_trackyy_' + tarteaucitron.user.getplusId, '_eventqueue'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.getplusId === undefined) {
            return;
        }
        
        window.webleads_site_ids = window.webleads_site_ids || [];
        window.webleads_site_ids.push(tarteaucitron.user.getplusId);
        tarteaucitron.addScript('//stats.webleads-tracker.com/js');
    }
};

// google+
tarteaucitron.services.gplus = {
    "key": "gplus",
    "type": "social",
    "name": "Google+",
    "uri": "https://policies.google.com/privacy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('https://apis.google.com/js/platform.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'gplus';
        tarteaucitron.fallback(['g-plus', 'g-plusone'], tarteaucitron.engage(id));
    }
};

// google+ badge
tarteaucitron.services.gplusbadge = {
    "key": "gplusbadge",
    "type": "social",
    "name": "Google+ (badge)",
    "uri": "https://policies.google.com/privacy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('https://apis.google.com/js/platform.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'gplusbadge';
        tarteaucitron.fallback(['g-page', 'g-person'], tarteaucitron.engage(id));
    }
};

// google adsense
tarteaucitron.services.adsense = {
    "key": "adsense",
    "type": "ads",
    "name": "Google Adsense",
    "uri": "http://www.google.com/ads/preferences/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'adsense';
        tarteaucitron.fallback(['adsbygoogle'], tarteaucitron.engage(id));
    }
};

// google partners badge
tarteaucitron.services.googlepartners = {
    "key": "googlepartners",
    "type": "ads",
    "name": "Google Partners Badge",
    "uri": "http://www.google.com/ads/preferences/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('https://apis.google.com/js/platform.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'googlepartners';
        tarteaucitron.fallback(['g-partnersbadge'], tarteaucitron.engage(id));
    }
};

// google adsense search (form)
tarteaucitron.services.adsensesearchform = {
    "key": "adsensesearchform",
    "type": "ads",
    "name": "Google Adsense Search (form)",
    "uri": "http://www.google.com/ads/preferences/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('//www.google.com/coop/cse/brand?form=cse-search-box&lang=' + tarteaucitron.getLanguage());
    }
};

// google adsense search (result)
tarteaucitron.services.adsensesearchresult = {
    "key": "adsensesearchresult",
    "type": "ads",
    "name": "Google Adsense Search (result)",
    "uri": "http://www.google.com/ads/preferences/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.adsensesearchresultCx === undefined) {
            return;
        }
        tarteaucitron.addScript('//www.google.com/cse/cse.js?cx=' + tarteaucitron.user.adsensesearchresultCx);
    },
    "fallback": function () {
        "use strict";
        var id = 'adsensesearchresult';
        
        if (document.getElementById('gcse_searchresults')) {
            document.getElementById('gcse_searchresults').innerHTML = tarteaucitron.engage(id);
        }
    }
};

// googleadwordsconversion
tarteaucitron.services.googleadwordsconversion = {
    "key": "googleadwordsconversion",
    "type": "ads",
    "name": "Google Adwords (conversion)",
    "uri": "https://www.google.com/settings/ads",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.adwordsconversionId === undefined) {
            return;
        }
        
        tarteaucitron.addScript('//www.googleadservices.com/pagead/conversion_async.js', '', function () {
            window.google_trackConversion({
                google_conversion_id: tarteaucitron.user.adwordsconversionId,
                google_conversion_label: tarteaucitron.user.adwordsconversionLabel,
                google_conversion_language: tarteaucitron.user.adwordsconversionLanguage,
                google_conversion_format: tarteaucitron.user.adwordsconversionFormat,
                google_conversion_color: tarteaucitron.user.adwordsconversionColor,
                google_conversion_value: tarteaucitron.user.adwordsconversionValue,
                google_conversion_currency: tarteaucitron.user.adwordsconversionCurrency,
                google_custom_params: {
                    parameter1: tarteaucitron.user.adwordsconversionCustom1,
                    parameter2: tarteaucitron.user.adwordsconversionCustom2
                }
            });
        });
    }
};

// googleadwordsremarketing
tarteaucitron.services.googleadwordsremarketing = {
    "key": "googleadwordsremarketing",
    "type": "ads",
    "name": "Google Adwords (remarketing)",
    "uri": "https://www.google.com/settings/ads",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.adwordsremarketingId === undefined) {
            return;
        }
        
        tarteaucitron.addScript('//www.googleadservices.com/pagead/conversion_async.js', '', function () {
            window.google_trackConversion({
                google_conversion_id: tarteaucitron.user.adwordsremarketingId,
                google_remarketing_only: true
            });
        });
    }
};

// google analytics (old)
tarteaucitron.services.gajs = {
    "key": "gajs",
    "type": "analytic",
    "name": "Google Analytics (ga.js)",
    "uri": "https://support.google.com/analytics/answer/6004245",
    "needConsent": true,
    "cookies": ['_ga', '_gat', '__utma', '__utmb', '__utmc', '__utmt', '__utmz'],
    "js": function () {
        "use strict";
        window._gaq = window._gaq || [];
        window._gaq.push(['_setAccount', tarteaucitron.user.gajsUa]);
        window._gaq.push(['_trackPageview']);
        
        tarteaucitron.addScript('//www.google-analytics.com/ga.js', '', function () {
            if (typeof tarteaucitron.user.gajsMore === 'function') {
                tarteaucitron.user.gajsMore();
            }
        });
    }
};

// google analytics
tarteaucitron.services.analytics = {
    "key": "analytics",
    "type": "analytic",
    "name": "Google Analytics (universal)",
    "uri": "https://support.google.com/analytics/answer/6004245",
    "needConsent": true,
    "cookies": ['_ga', '_gat', '_gid', '__utma', '__utmb', '__utmc', '__utmt', '__utmz'],
    "js": function () {
        "use strict";
        window.GoogleAnalyticsObject = 'ga';
        window.ga = window.ga || function () {
            window.ga.q = window.ga.q || [];
            window.ga.q.push(arguments);
        };
        window.ga.l = new Date();
        
        tarteaucitron.addScript('//www.google-analytics.com/analytics.js', '', function () {
            ga('create', tarteaucitron.user.analyticsUa, {'cookieExpires': 34128000});
            ga('send', 'pageview');
            if (typeof tarteaucitron.user.analyticsMore === 'function') {
                tarteaucitron.user.analyticsMore();
            }
        });
    }
};

// google analytics
tarteaucitron.services.gtag = {
    "key": "gtag",
    "type": "analytic",
    "name": "Google Analytics", //"name": "Google Analytics (gtag.js)"
    "uri": "https://support.google.com/analytics/answer/6004245",
    "needConsent": true,
    "cookies": ['_ga', '_gat', '_gid', '__utma', '__utmb', '__utmc', '__utmt', '__utmz'],
    "js": function () {
        "use strict";
        window.dataLayer = window.dataLayer || [];
        
        tarteaucitron.addScript('//www.googletagmanager.com/gtag/js?id=' + tarteaucitron.user.gtagUa, '', function () {
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());
            gtag('config', tarteaucitron.user.gtagUa);
          
            if (typeof tarteaucitron.user.gtagMore === 'function') {
                tarteaucitron.user.gtagMore();
            }
        });
    }
};

// google maps
tarteaucitron.services.googlemaps = {
    "key": "googlemaps",
    "type": "api",
    "name": "Google Maps",
    "uri": "http://www.google.com/ads/preferences/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        var mapOptions,
            map,
            uniqIds = [],
            i;

        if (tarteaucitron.user.mapscallback === undefined) {
            tarteaucitron.user.mapscallback = 'tac_googlemaps_callback';
        }
        
        tarteaucitron.addScript('//maps.googleapis.com/maps/api/js?v=3.exp&key=' + tarteaucitron.user.googlemapsKey + '&libraries=places‌​&callback='+tarteaucitron.user.mapscallback);

        window.tac_googlemaps_callback = function () {
            tarteaucitron.fallback(['googlemaps-canvas'], function (x) {
                var uniqId = '_' + Math.random().toString(36).substr(2, 9);
                uniqIds.push(uniqId);
                return '<div id="' + uniqId + '" zoom="' + x.getAttribute('zoom') + '" latitude="' + x.getAttribute('latitude') + '" longitude="' + x.getAttribute('longitude') + '" style="width:' + x.offsetWidth + 'px;height:' + x.offsetHeight + 'px"></div>';
            });
        
            for (i = 0; i < uniqIds.length; i += 1) {
                mapOptions = {
                    zoom: parseInt(document.getElementById(uniqIds[i]).getAttribute('zoom'), 10),
                    center: new google.maps.LatLng(parseFloat(document.getElementById(uniqIds[i]).getAttribute('latitude'), 10), parseFloat(document.getElementById(uniqIds[i]).getAttribute('longitude'), 10))
                };
                map = new google.maps.Map(document.getElementById(uniqIds[i]), mapOptions);
            }
        };
    },
    "fallback": function () {
        "use strict";
        var id = 'googlemaps';
        tarteaucitron.fallback(['maps'], tarteaucitron.engage(id));
    }
};

// google tag manager
tarteaucitron.services.googletagmanager = {
    "key": "googletagmanager",
    "type": "api",
    "name": "Google Tag Manager",
    "uri": "http://www.google.com/ads/preferences/",
    "needConsent": true,
    "cookies": ['_ga', '_gat', '__utma', '__utmb', '__utmc', '__utmt', '__utmz', '__gads', '_drt_', 'FLC', 'exchange_uid', 'id', 'fc', 'rrs', 'rds', 'rv', 'uid', 'UIDR', 'UID', 'clid', 'ipinfo', 'acs'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.googletagmanagerId === undefined) {
            return;
        }
        window.dataLayer = window.dataLayer || [];
        window.dataLayer.push({
            'gtm.start': new Date().getTime(),
            event: 'gtm.js'
        });
        tarteaucitron.addScript('//www.googletagmanager.com/gtm.js?id=' + tarteaucitron.user.googletagmanagerId);
    }
};

// jsapi
tarteaucitron.services.jsapi = {
    "key": "jsapi",
    "type": "api",
    "name": "Google jsapi",
    "uri": "http://www.google.com/policies/privacy/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.addScript('//www.google.com/jsapi');
    }
};

// linkedin
tarteaucitron.services.linkedin = {
    "key": "linkedin",
    "type": "social",
    "name": "Linkedin",
    "uri": "https://www.linkedin.com/legal/cookie_policy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tacLinkedin'], '');
        tarteaucitron.addScript('//platform.linkedin.com/in.js');
        if (tarteaucitron.isAjax === true) {
            if (typeof IN !== "undefined") {
                IN.parse();
            }
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'linkedin';
        tarteaucitron.fallback(['tacLinkedin'], tarteaucitron.engage(id));
    }
};

// mautic
tarteaucitron.services.mautic = {
    "key": "mautic",
    "type": "analytic",
    "name": "Mautic",
    "uri": "https://www.mautic.org/privacy-policy/",
    "needConsent": true,
    "cookies": ['mtc_id', 'mtc_sid'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.mauticurl === undefined) {
            return;
        }
        
        window['MauticTrackingObject'] = 'mt';
        window['mt'] = window['mt'] || function() {
            (window['mt'].q = window['mt'].q || []).push(arguments);
        };
        
        tarteaucitron.addScript(tarteaucitron.user.mauticurl, '', function() {
            mt('send', 'pageview');
        });
    }
};

// microsoftcampaignanalytics
tarteaucitron.services.microsoftcampaignanalytics = {
    "key": "microsoftcampaignanalytics",
    "type": "analytic",
    "name": "Microsoft Campaign Analytics",
    "uri": "https://privacy.microsoft.com/privacystatement/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.microsoftcampaignanalyticsUUID === undefined) {
            return;
        }
        
        tarteaucitron.addScript('//flex.atdmt.com/mstag/site/' + tarteaucitron.user.microsoftcampaignanalyticsUUID + '/mstag.js', 'mstag_tops', function () {
            window.mstag = {loadTag : function () {}, time : (new Date()).getTime()};
            window.mstag.loadTag("analytics", {dedup: "1", domainId: tarteaucitron.user.microsoftcampaignanalyticsdomainId, type: "1", actionid: tarteaucitron.user.microsoftcampaignanalyticsactionId});
        });
    }
};

// pinterest
tarteaucitron.services.pinterest = {
    "key": "pinterest",
    "type": "social",
    "name": "Pinterest",
    "uri": "https://about.pinterest.com/privacy-policy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tacPinterest'], '');
        tarteaucitron.addScript('//assets.pinterest.com/js/pinit.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'pinterest';
        tarteaucitron.fallback(['tacPinterest'], tarteaucitron.engage(id));
    }
};

// prelinker
tarteaucitron.services.prelinker = {
    "key": "prelinker",
    "type": "ads",
    "name": "Prelinker",
    "uri": "http://www.prelinker.com/index/index/cgu/",
    "needConsent": true,
    "cookies": ['_sp_id.32f5', '_sp_ses.32f5'],
    "js": function () {
        "use strict";
        var uniqIds = [],
            i,
            uri;

        tarteaucitron.fallback(['prelinker-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            return '<div id="' + uniqId + '" siteId="' + x.getAttribute('siteId') + '" bannerId="' + x.getAttribute('bannerId') + '" defaultLanguage="' + x.getAttribute('defaultLanguage') + '" tracker="' + x.getAttribute('tracker') + '"></div>';
        });
        
        for (i = 0; i < uniqIds.length; i += 1) {
            uri = 'http://promo.easy-dating.org/banner/index?';
            uri += 'site_id=' + document.getElementById(uniqIds[i]).getAttribute('siteId') + '&';
            uri += 'banner_id=' + document.getElementById(uniqIds[i]).getAttribute('bannerId') + '&';
            uri += 'default_language=' + document.getElementById(uniqIds[i]).getAttribute('defaultLanguage') + '&';
            uri += 'tr4ck=' + document.getElementById(uniqIds[i]).getAttribute('trackrt');
            
            tarteaucitron.makeAsync.init(uri, uniqIds[i]);
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'prelinker';
        tarteaucitron.fallback(['prelinker-canvas'], tarteaucitron.engage(id));
    }
};

// prezi
tarteaucitron.services.prezi = {
    "key": "prezi",
    "type": "video",
    "name": "Prezi",
    "uri": "https://prezi.com/privacy-policy/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['prezi-canvas'], function (x) {
            var id = x.getAttribute("data-id"),
                width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                url = 'https://prezi.com/embed/' + id + '/?bgcolor=ffffff&amp;lock_to_path=0&amp;autoplay=0&amp;autohide_ctrls=0';
            
            return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="0" scrolling="no" allowtransparency allowfullscreen></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'prezi';
        tarteaucitron.fallback(['prezi-canvas'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// pubdirecte
tarteaucitron.services.pubdirecte = {
    "key": "pubdirecte",
    "type": "ads",
    "name": "Pubdirecte",
    "uri": "http://pubdirecte.com/contact.php",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        var uniqIds = [],
            i,
            uri;

        tarteaucitron.fallback(['pubdirecte-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            return '<div id="' + uniqId + '" pid="' + x.getAttribute('pid') + '" ref="' + x.getAttribute('ref') + '"></div>';
        });
        
        for (i = 0; i < uniqIds.length; i += 1) {
            uri = '//www.pubdirecte.com/script/banniere.php?';
            uri += 'id=' + document.getElementById(uniqIds[i]).getAttribute('pid') + '&';
            uri += 'ref=' + document.getElementById(uniqIds[i]).getAttribute('ref');
            
            tarteaucitron.makeAsync.init(uri, uniqIds[i]);
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'pubdirecte';
        tarteaucitron.fallback(['pubdirecte-canvas'], tarteaucitron.engage(id));
    }
};

// purechat
tarteaucitron.services.purechat = {
    "key": "purechat",
    "type": "support",
    "name": "PureChat",
    "uri": "https://www.purechat.com/privacy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.purechatId === undefined) {
            return;
        }
        
        tarteaucitron.addScript('//app.purechat.com/VisitorWidget/WidgetScript', '', function () {
            try {
                window.w = new PCWidget({ c: tarteaucitron.user.purechatId, f: true });
            } catch (e) {}
        });
    }
};

// shareaholic
tarteaucitron.services.shareaholic = {
    "key": "shareaholic",
    "type": "social",
    "name": "Shareaholic",
    "uri": "https://shareaholic.com/privacy/choices",
    "needConsent": true,
    "cookies": ['__utma', '__utmb', '__utmc', '__utmz', '__utmt_Shareaholic%20Pageviews'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.shareaholicSiteId === undefined) {
            return;
        }
        
        tarteaucitron.fallback(['shareaholic-canvas'], '');
        tarteaucitron.addScript('//dsms0mj1bbhn4.cloudfront.net/assets/pub/shareaholic.js', '', function () {
            try {
                Shareaholic.init(tarteaucitron.user.shareaholicSiteId);
            } catch (e) {}
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'shareaholic';
        tarteaucitron.fallback(['shareaholic-canvas'], tarteaucitron.engage(id));
    }
};

// shareasale
tarteaucitron.services.shareasale = {
    "key": "shareasale",
    "type": "ads",
    "name": "ShareASale",
    "uri": "https://www.shareasale.com/PrivacyPolicy.pdf",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        var uniqIds = [],
            i,
            uri;

        tarteaucitron.fallback(['shareasale-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            return '<div id="' + uniqId + '" amount="' + x.getAttribute('amount') + '" tracking="' + x.getAttribute('tracking') + '" transtype="' + x.getAttribute('transtype') + '" persale="' + x.getAttribute('persale') + '" perlead="' + x.getAttribute('perlead') + '" perhit="' + x.getAttribute('perhit') + '" merchantID="' + x.getAttribute('merchantID') + '"></div>';
        });
        
        for (i = 0; i < uniqIds.length; i += 1) {
            uri = 'https://shareasale.com/sale.cfm?';
            uri += 'amount=' + document.getElementById(uniqIds[i]).getAttribute('amount') + '&';
            uri += 'tracking=' + document.getElementById(uniqIds[i]).getAttribute('tracking') + '&';
            uri += 'transtype=' + document.getElementById(uniqIds[i]).getAttribute('transtype') + '&';
            uri += 'persale=' + document.getElementById(uniqIds[i]).getAttribute('persale') + '&';
            uri += 'perlead=' + document.getElementById(uniqIds[i]).getAttribute('perlead') + '&';
            uri += 'perhit=' + document.getElementById(uniqIds[i]).getAttribute('perhit') + '&';
            uri += 'merchantID=' + document.getElementById(uniqIds[i]).getAttribute('merchantID');
            
            document.getElementById(uniqIds[i]).innerHTML = '<img src=\'' + uri + '\' width=\'1\' height=\'1\' />';
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'shareasale';
        tarteaucitron.fallback(['shareasale-canvas'], tarteaucitron.engage(id));
    }
};

// sharethis
tarteaucitron.services.sharethis = {
    "key": "sharethis",
    "type": "social",
    "name": "ShareThis",
    "uri": "http://www.sharethis.com/legal/privacy/",
    "needConsent": true,
    "cookies": ['__unam'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.sharethisPublisher === undefined) {
            return;
        }
        var switchTo5x = true,
            uri = ('https:' === document.location.protocol ? 'https://ws' : 'http://w') + '.sharethis.com/button/buttons.js';
        
        tarteaucitron.fallback(['tacSharethis'], '');
        tarteaucitron.addScript(uri, '', function () {
            stLight.options({publisher: tarteaucitron.user.sharethisPublisher, doNotHash: false, doNotCopy: false, hashAddressBar: false});
        });
        
        if (tarteaucitron.isAjax === true) {
            if (typeof stButtons !== "undefined") {
                stButtons.locateElements();
            }
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'sharethis';
        tarteaucitron.fallback(['tacSharethis'], tarteaucitron.engage(id));
    }
};

// slideshare
tarteaucitron.services.slideshare = {
    "key": "slideshare",
    "type": "video",
    "name": "SlideShare",
    "uri": "https://www.linkedin.com/legal/privacy-policy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['slideshare-canvas'], function (x) {
            var id = x.getAttribute("data-id"),
                width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                url = '//www.slideshare.net/slideshow/embed_code/' + id;
            
            return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="0" scrolling="no" allowtransparency allowfullscreen></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'slideshare';
        tarteaucitron.fallback(['slideshare-canvas'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// statcounter
tarteaucitron.services.statcounter = {
    "key": "statcounter",
    "type": "analytic",
    "name": "StatCounter",
    "uri": "https://fr.statcounter.com/about/legal/#privacy",
    "needConsent": true,
    "cookies": ['sc_is_visitor_unique'],
    "js": function () {
        "use strict";
        var uniqIds = [],
            i,
            uri = '//statcounter.com/counter/counter.js';

        tarteaucitron.fallback(['statcounter-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            return '<div id="' + uniqId + '"></div>';
        });
        
        for (i = 0; i < uniqIds.length; i += 1) {
            tarteaucitron.makeAsync.init(uri, uniqIds[i]);
        }
    },
    "fallback": function () {
        "use strict";
        var id = 'statcounter';
        tarteaucitron.fallback(['statcounter-canvas'], tarteaucitron.engage(id));
    }
};

// timelinejs
tarteaucitron.services.timelinejs = {
    "key": "timelinejs",
    "type": "api",
    "name": "Timeline JS",
    "uri": "http://timeline.knightlab.com/#help",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['timelinejs-canvas'], function (x) {
            var spreadsheet_id = x.getAttribute("spreadsheet_id"),
                width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                lang = x.getAttribute("lang_2_letter"),
                font = x.getAttribute("font"),
                map = x.getAttribute("map"),
                start_at_end = x.getAttribute("start_at_end"),
                hash_bookmark = x.getAttribute("hash_bookmark"),
                start_at_slide = x.getAttribute("start_at_slide"),
                start_zoom = x.getAttribute("start_zoom"),
                url = '//cdn.knightlab.com/libs/timeline/latest/embed/index.html?source=' + spreadsheet_id + '&font=' + font + '&maptype=' + map + '&lang=' + lang + '&start_at_end=' + start_at_end + '&hash_bookmark=' + hash_bookmark + '&start_at_slide=' + start_at_slide + '&start_zoom_adjust=' + start_zoom + '&height=' + height;

            return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="0" allowtransparency allowfullscreen></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'timelinejs';
        tarteaucitron.fallback(['timelinejs-canvas'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// typekit
tarteaucitron.services.typekit = {
    "key": "typekit",
    "type": "api",
    "name": "Typekit (adobe)",
    "uri": "http://www.adobe.com/fr/privacy.html",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.typekitId === undefined) {
            return;
        }
        tarteaucitron.addScript('//use.typekit.net/' + tarteaucitron.user.typekitId + '.js', '', function () {
            try {
                Typekit.load();
            } catch (e) {}
        });
    }
};

// twenga
tarteaucitron.services.twenga = {
    "key": "twenga",
    "type": "ads",
    "name": "Twenga",
    "uri": "http://www.twenga.com/privacy.php",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        
        if (tarteaucitron.user.twengaId === undefined || tarteaucitron.user.twengaLocale === undefined) {
            return;
        }
        
        tarteaucitron.addScript('//tracker.twenga.' + tarteaucitron.user.twengaLocale + '/st/tracker_' + tarteaucitron.user.twengaId + '.js');
    }
};

// twitter
tarteaucitron.services.twitter = {
    "key": "twitter",
    "type": "social",
    "name": "Twitter",
    "uri": "https://support.twitter.com/articles/20170514",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tacTwitter'], '');
        tarteaucitron.addScript('//platform.twitter.com/widgets.js', 'twitter-wjs');
    },
    "fallback": function () {
        "use strict";
        var id = 'twitter';
        tarteaucitron.fallback(['tacTwitter'], tarteaucitron.engage(id));
    }
};

// twitter embed
tarteaucitron.services.twitterembed = {
    "key": "twitterembed",
    "type": "social",
    "name": "Twitter (cards)",
    "uri": "https://support.twitter.com/articles/20170514",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        var uniqIds = [],
            i,
            e,
            html;

        tarteaucitron.fallback(['twitterembed-canvas'], function (x) {
            var uniqId = '_' + Math.random().toString(36).substr(2, 9);
            uniqIds.push(uniqId);
            html = '<div id="' + uniqId + '" ';
            html += 'tweetid="' + x.getAttribute('tweetid') + '" ';
            html += 'theme="' + x.getAttribute('theme') + '" ';
            html += 'cards="' + x.getAttribute('cards') + '" ';
            html += 'conversation="' + x.getAttribute('conversation') + '" ';
            html += 'data-width="' + x.getAttribute('data-width') + '" ';
            html += 'data-align="' + x.getAttribute('data-align') + '" ';
            html += '></div>';
            return html;
        });
        
        tarteaucitron.addScript('//platform.twitter.com/widgets.js', 'twitter-wjs', function () {
            for (i = 0; i < uniqIds.length; i += 1) {
                e = document.getElementById(uniqIds[i]);
                twttr.widgets.createTweet(
                    e.getAttribute('tweetid'),
                    e,
                    {
                        theme: e.getAttribute('theme'),
                        cards: e.getAttribute('cards'),
                        conversation: e.getAttribute('conversation'),
                        lang: tarteaucitron.getLanguage(),
                        dnt: true,
                        width: e.getAttribute('data-width'),
                        align: e.getAttribute('data-align')
                    }
                );
            }
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'twitterembed';
        tarteaucitron.fallback(['twitterembed-canvas'], function (elem) {
            elem.style.width = elem.getAttribute('data-width') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// twitter timeline
tarteaucitron.services.twittertimeline = {
    "key": "twittertimeline",
    "type": "social",
    "name": "Twitter (timelines)",
    "uri": "https://support.twitter.com/articles/20170514",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tacTwitterTimelines'], '');
        tarteaucitron.addScript('//platform.twitter.com/widgets.js', 'twitter-wjs');
    },
    "fallback": function () {
        "use strict";
        var id = 'twittertimeline';
        tarteaucitron.fallback(['tacTwitterTimelines'], tarteaucitron.engage(id));
    }
};

// user voice
tarteaucitron.services.uservoice = {
    "key": "uservoice",
    "type": "support",
    "name": "UserVoice",
    "uri": "https://www.uservoice.com/privacy/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.userVoiceApi === undefined) {
            return;
        }
        tarteaucitron.addScript('//widget.uservoice.com/' + tarteaucitron.user.userVoiceApi + '.js');
    }
};

// vimeo
tarteaucitron.services.vimeo = {
    "key": "vimeo",
    "type": "video",
    "name": "Vimeo",
    "uri": "http://vimeo.com/privacy",
    "needConsent": true,
    "cookies": ['__utmt_player', '__utma', '__utmb', '__utmc', '__utmv', 'vuid', '__utmz', 'player'],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['vimeo_player'], function (x) {
            return tarteaucitron.services.vimeo.jsOnElement(x);
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'vimeo';
        tarteaucitron.fallback(['vimeo_player'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    },
    "jsOnElement" : function (x) {
            var video_id = x.getAttribute("videoID"),
                video_width = x.getAttribute("width"),
                frame_width = 'width=',
                video_height = x.getAttribute("height"),
                frame_height = 'height=',
                video_frame,
                params = 'automute=' + elem.getAttribute("mute") + '&autoplay=' + x.getAttribute("autoplay") ;

            if (video_id === undefined) {
                return "";
            }
            if (video_width !== undefined) {
                frame_width += '"' + video_width + '" ';
            } else {
                frame_width += '"" ';
            }
            if (video_height !== undefined) {
                frame_height +=  '"' + video_height + '" ';
            } else {
                frame_height += '"" ';
            }
            video_frame = '<iframe src="https://player.vimeo.com/video/' + video_id +  '?' + params +'" ' + frame_width + frame_height +' frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>';

            return video_frame;
    }
};

// visualrevenue
tarteaucitron.services.visualrevenue = {
    "key": "visualrevenue",
    "type": "analytic",
    "name": "VisualRevenue",
    "uri": "http://www.outbrain.com/legal/privacy-713/",
    "needConsent": true,
    "cookies": ['__vrf', '__vrm', '__vrl', '__vry', '__vru', '__vrid', '__vrz'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.visualrevenueId === undefined) {
            return;
        }
        window._vrq = window._vrq || [];
        window._vrq.push(['id', tarteaucitron.user.visualrevenueId]);
        window._vrq.push(['automate', true]);
        window._vrq.push(['track', function () {}]);
        tarteaucitron.addScript('http://a.visualrevenue.com/vrs.js');
    }
};

// vshop
tarteaucitron.services.vshop = {
    "key": "vshop",
    "type": "ads",
    "name": "vShop",
    "uri": "http://vshop.fr/privacy-policy",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['vcashW'], '');
        tarteaucitron.addScript('//vshop.fr/js/w.js');
    },
    "fallback": function () {
        "use strict";
        var id = 'vshop';
        tarteaucitron.fallback(['vcashW'], tarteaucitron.engage(id));
    }
};

// wysistat
tarteaucitron.services.wysistat = {
    "key": "wysistat",
    "type": "analytic",
    "name": "Wysistat",
    "uri": "http://wysistat.net/contact/",
    "needConsent": true,
    "cookies": ['Wysistat'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.wysistat === undefined) {
            return;
        }
        tarteaucitron.addScript('//www.wysistat.com/statistique.js', '', function () {
            window.stat(tarteaucitron.user.wysistat.cli, tarteaucitron.user.wysistat.frm, tarteaucitron.user.wysistat.prm, tarteaucitron.user.wysistat.ce, tarteaucitron.user.wysistat.page, tarteaucitron.user.wysistat.roi, tarteaucitron.user.wysistat.prof, tarteaucitron.user.wysistat.cpt);
        });
    }
};

// xiti
tarteaucitron.services.xiti = {
    "key": "xiti",
    "type": "analytic",
    "name": "Xiti",
    "uri": "http://www.atinternet.com/politique-du-respect-de-la-vie-privee/",
    "needConsent": true,
    "cookies": [],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.xitiId === undefined) {
            return;
        }
        var Xt_param = 's=' + tarteaucitron.user.xitiId + '&p=',
            Xt_r,
            Xt_h,
            Xt_i,
            Xt_s,
            div = document.createElement('div');
        try {
            Xt_r = top.document.referrer;
        } catch (e) {
            Xt_r = document.referrer;
        }
        Xt_h = new Date();
        Xt_i = '<img style="display:none" border="0" alt="" ';
        Xt_i += 'src="http://logv3.xiti.com/hit.xiti?' + Xt_param;
        Xt_i += '&hl=' + Xt_h.getHours() + 'x' + Xt_h.getMinutes() + 'x' + Xt_h.getSeconds();
        if (parseFloat(navigator.appVersion) >= 4) {
            Xt_s = screen;
            Xt_i += '&r=' + Xt_s.width + 'x' + Xt_s.height + 'x' + Xt_s.pixelDepth + 'x' + Xt_s.colorDepth;
        }
        
        div.innerHTML = Xt_i + '&ref=' + Xt_r.replace(/[<>"]/g, '').replace(/&/g, '$') + '" title="Internet Audience">';
        document.getElementsByTagName('body')[0].appendChild(div.firstChild);
        
        if (typeof tarteaucitron.user.xitiMore === 'function') {
            tarteaucitron.user.xitiMore();
        }
    }
};

// youtube
tarteaucitron.services.youtube = {
    "key": "youtube",
    "type": "video",
    "name": "YouTube",
    "uri": "https://www.google.fr/intl/fr/policies/privacy/",
    "needConsent": true,
    "cookies": ['VISITOR_INFO1_LIVE', 'YSC', 'PREF', 'GEUP'],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['youtube_player'], function (x) {
            return tarteaucitron.services.youtube.jsOnElement(x)
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'youtube';
        tarteaucitron.fallback(['youtube_player'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    },
    "jsOnElement" : function (elem) {
        "use strict";

        var video_id = elem.getAttribute("videoID"),
            video_width = elem.getAttribute("width"),
            frame_width = 'width=',
            video_height = elem.getAttribute("height"),
            frame_height = 'height=',
            video_frame,
            params = 'theme=' + elem.getAttribute("theme") + '&rel=' + elem.getAttribute("rel") + '&controls=' + elem.getAttribute("controls") + '&showinfo=' + elem.getAttribute("showinfo") + '&mute=' + elem.getAttribute("mute") + '&autoplay=' + elem.getAttribute("autoplay");

        if (video_id === undefined) {
            return "";
        }
        if (video_width !== undefined) {
            frame_width += '"' + video_width + '" ';
        } else {
            frame_width += '"" ';
        }
        if (video_height !== undefined) {
            frame_height +=  '"' + video_height + '" ';
        } else {
            frame_height += '"" ';
        }
        video_frame = '<iframe type="text/html" ' + frame_width + frame_height + ' src="https://www.youtube-nocookie.com/embed/' + video_id + '?' + params + '" frameborder="0"></iframe>';
        return video_frame;
    }
};

// youtube playlist
tarteaucitron.services.youtubeplaylist = {
    "key": "youtubeplaylist",
    "type": "video",
    "name": "YouTube (playlist)",
    "uri": "https://www.google.fr/intl/fr/policies/privacy/",
    "needConsent": true,
    "cookies": ['VISITOR_INFO1_LIVE', 'YSC', 'PREF', 'GEUP'],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['youtube_playlist_player'], function (x) {
            var playlist_id = x.getAttribute("playlistID"),
                video_width = x.getAttribute("width"),
                frame_width = 'width=',
                video_height = x.getAttribute("height"),
                frame_height = 'height=',
                video_frame,
                params = 'theme=' + x.getAttribute("theme") + '&rel=' + x.getAttribute("rel") + '&controls=' + x.getAttribute("controls") + '&showinfo=' + x.getAttribute("showinfo") + '&autoplay=' + x.getAttribute("autoplay");
            
            if (playlist_id === undefined) {
                return "";
            }
            if (video_width !== undefined) {
                frame_width += '"' + video_width + '" ';
            } else {
                frame_width += '"" ';
            }
            if (video_height !== undefined) {
                frame_height +=  '"' + video_height + '" ';
            } else {
                frame_height += '"" ';
            }
            video_frame = '<iframe type="text/html" ' + frame_width + frame_height + ' src="//www.youtube-nocookie.com/embed/videoseries?list=' + playlist_id + '&' + params + '" frameborder="0"></iframe>';
            return video_frame;
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'youtubeplaylist';
        tarteaucitron.fallback(['youtube_playlist_player'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// zopim
tarteaucitron.services.zopim = {
    "key": "zopim",
    "type": "support",
    "name": "Zopim",
    "uri": "https://www.zopim.com/privacy",
    "needConsent": true,
    "cookies": ['__zlcid', '__zprivacy'],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.zopimID === undefined) {
            return;
        }
        tarteaucitron.addScript('//v2.zopim.com/?' + tarteaucitron.user.zopimID);
    }
};

// xiti smartTag
tarteaucitron.services.xiti_smarttag = {
    "key": "xiti_smarttag",
    "type": "analytic",
    "name": "Xiti (SmartTag)",
    "uri": "https://www.atinternet.com/societe/protection-des-donnees/",
    "needConsent": true,
    "cookies": ["atidvisitor", "atreman", "atredir", "atsession", "atuserid", "attvtreman", "attvtsession"],
    "js": function () {
        "use strict";
        if (tarteaucitron.user.xiti_smarttagLocalPath !== undefined) {
            tarteaucitron.addScript(tarteaucitron.user.xiti_smarttagLocalPath, 'smarttag', null, null, "onload", "addTracker();");
        } else {
            var xitiSmarttagId = tarteaucitron.user.xiti_smarttagSiteId;
            if (xitiSmarttagId === undefined) {
                return;
            }

            tarteaucitron.addScript('//tag.aticdn.net/' + xitiSmarttagId + '/smarttag.js', 'smarttag', null, null, "onload", "addTracker();");
        }
    }
};

// facebook pixel
tarteaucitron.services.facebookpixel = {
    "key": "facebookpixel",
    "type": "ads",
    "name": "Facebook Pixel",
    "uri": "https://fr-fr.facebook.com/business/help/www/651294705016616",
    "needConsent": true,
    "cookies": ['datr', 'fr', 'reg_ext_ref', 'reg_fb_gate', 'reg_fb_ref', 'sb', 'wd', 'x-src'],
    "js": function () {
        "use strict";
        var n;
        if(window.fbq)return;
        n=window.fbq=function(){n.callMethod? n.callMethod.apply(n,arguments):n.queue.push(arguments)} ;
        if(!window._fbq)window._fbq=n;
        n.push=n;
        n.loaded=!0;
        n.version='2.0';
        n.queue=[];
        tarteaucitron.addScript('https://connect.facebook.net/en_US/fbevents.js');
        fbq('init', tarteaucitron.user.facebookpixelId);
        fbq('track', 'PageView');

        if (typeof tarteaucitron.user.facebookpixelMore === 'function') {
            tarteaucitron.user.facebookpixelMore();
        }
    }
};



/*global tarteaucitron */

if(!tarteaucitron.langlist){
    tarteaucitron.langlist = {};
}

tarteaucitron.langlist.cs = {
    "adblock": "Ahoj! Tato stránka je transparetní a umožňuje ti si přímo vybrat, jaké služby třetích stran chceš povolit.",
    "adblock_call": "Pro úpravu osobních preferencí si, prosím, vypni adblock.",
    "reload": "Načíst stránku znovu",
    
    "alertBigScroll": "Pokračováním ve scrollování,",
    "alertBigClick": "Pokud pokračujete v brouzdání našich stránek,",
    "alertBig": "povolujete všechny služby třetích stran.",
    
    "alertBigPrivacy": "Tato stránka využívá cookies a dává ti na výběr, co chceš aktivovat",
    "alertSmall": "Spravovat služby",
    "personalize": "Přizpůsobit",
    "acceptAll": "OK, přijmout vše",
    "close": "Zavřít",

    "all": "Nastavení všech služeb",

    "info": "Chrání tvé soukromí",
    "disclaimer": "Povolením těchto služeb třetích stran, přijímáš jejich cookies, jež jsou nezbytné pro řádné fungování jejich technologií.",
    "allow": "Povolit",
    "deny": "Zamítnout",
    "noCookie": "Tato služba nepoužívá cookies.",
    "useCookie": "Tato služba může nainstalovat",
    "useCookieCurrent": "Tato služba nainstalovala",
    "useNoCookie": "Tato služba nenainstalovala žádné cookies.",
    "more": "Dozvědět se více",
    "source": "Zobrazit oficiální stránku",
    "credit": "Správce cookies od tarteaucitron.js",
    
    "fallback": "je vypnutý.",

    "ads": {
        "title": "Reklamní síť",
        "details": "Prodejem reklamních ploch na této stránce mohou reklamní sítě vydělávat peníze."
    },
    "analytic": {
        "title": "Statistika návštěvnosti",
        "details": "Služby pro analýzu návštěvníků slouží k vytvoření užitečných statistik návštěvnosti. Ty zase slouží ke zlepšení stránky."
    },
    "social": {
        "title": "Sociální sítě",
        "details": "Sociální sítě mohou usnadnit práci se stránkou a pomáhají jí prosadit se pomocí sdílení."
    },
    "video": {
        "title": "Videa",
        "details": "Video-hostingové služby pomáhají přidat na stránku bohaté mediální prvky."
    },
    "comment": {
        "title": "Komentáře",
        "details": "Správce komentářů zajišťují vyplňování komentářů a bojují proti šíření spamu."
    },
    "support": {
        "title": "Podpora",
        "details": "Služby podpory ti pomáhají spojit se s týmem stojícím za stránkou a umožňují ti vyjádřit se k jejím nedostatkům."
    },
    "api": {
        "title": "API",
        "details": "API slouží k načtění skriptů: geolokace, vyhledávačů, překladů, ..."
    },
    "other": {
        "title": "Jiný",
        "details": "Služby pro zobrazení webového obsahu."
    }
};

tarteaucitron.langlist.de = {
    "adblock": "Hallo! Diese Seite ist transparent und lässt Ihnen die Wahl der externen Services, die aktiviert werden dürfen.",
    "adblock_call": "Bitte deaktivieren Sie Ihren 'Werbeblocker' um Konfigurieren zu können.",
    "reload": "Seite neu laden",

    "alertBigScroll": "Durch die fortgesetzte blättern,",
    "alertBigClick": "Wenn Sie diese Webseite benutzen,",
    "alertBig": "stimmen Sie der Benutzung von externen Diensten zu",

    "alertBigPrivacy": "Diese Webseite verwendet 'Cookies' und ermöglicht dadurch Kontrolle, welche Dienste benutzt werden dürfen",
    "alertSmall": "Service-Kontrolle",
    "personalize": "Personalisieren",
    "acceptAll": "OK, akzeptiere alles",
    "close": "Beenden",

    "all": "Präferenz für alle Dienste",

    "info": "Schutz der Privatsphäre",
    "disclaimer": "Wenn Sie diese Dienste nutzen, erlauben Sie deren 'Cookies' und Tracking-Funktionen, die zu ihrer ordnungsgemäßen Funktion notwendig sind.",
    "allow": "Erlauben",
    "deny": "Ablehnen",
    "noCookie": "Dieser Dienst nutzt keine 'Cookies'.",
    "useCookie": "Dieser Dienst kann installieren",
    "useCookieCurrent": "Dieser Dienst hat installiert",
    "useNoCookie": "Dieser Dienst hat keine 'Cookies' installiert.",
    "more": "Weiter lesen",
    "source": "Zur offiziellen Webseite",
    "credit": "Cookies manager von tarteaucitron.js",

    "fallback": "ist deaktiviert.",

    "ads": {
        "title": "Anzeigen Netzwerke",
        "details": "Anzeigen Netzwerke können mit dem Verkauf von Werbeplatzierungen auf der Seite Einnahmen erhalten."
    },
    "analytic": {
        "title": "Besucher Zähldienste",
        "details": "Die verwendeten Besucher Zähldienste generieren Statistiken die dabei helfen, die Seite zu verbessern."
    },
    "social": {
        "title": "Soziale Netzwerke",
        "details": "Soziale Netzwerke können die Benutzbarkeit der Seite verbessern und ihren Bekanntheitsgrad erhöhen."
    },
    "video": {
        "title": "Videos",
        "details": "Video Platformen erlauben Videoinhalte einzublenden und die Sichtbarkeit der Seite zu erhöhen."
    },
    "comment": {
        "title": "Kommentare",
        "details": "Kommentar Manager erleichtern die Organisation von Kommentaren und helfen dabei Spam zu verhindern."
    },
    "support": {
        "title": "Support",
        "details": "Support Dienste erlauben es die Urheber der Seite zu kontaktieren und sie zu verbessern."
    },
    "api": {
        "title": "APIs",
        "details": "APIs werden benutzt um Skripte zu laden, wie: Geolokalisation, Suchmaschinen, Übersetzungen, ..."
    },
    "other": {
        "title": "Andere",
        "details": "Dienste zum Anzeigen von Web-Inhalten."
    }
};

tarteaucitron.langlist.en = {
    "adblock": "Hello! This site is transparent and lets you chose the 3rd party services you want to allow.",
    "adblock_call": "Please disable your adblocker to start customizing.",
    "reload": "Refresh the page",

    "alertBigScroll": "By continuing to scroll,",
    "alertBigClick": "If you continue to browse this website,",
    "alertBig": "you are allowing all third-party services",

    "alertBigPrivacy": "This site uses cookies and gives you control over what you want to activate",
    "alertSmall": "Manage services",
    "personalize": "Personalize",
    "acceptAll": "OK, accept all",
    "close": "Close",

    "all": "Preference for all services",

    "info": "Protecting your privacy",
    "disclaimer": "By allowing these third party services, you accept their cookies and the use of tracking technologies necessary for their proper functioning.",
    "allow": "Allow",
    "deny": "Deny",
    "noCookie": "This service does not use cookie.",
    "useCookie": "This service can install",
    "useCookieCurrent": "This service has installed",
    "useNoCookie": "This service has not installed any cookie.",
    "more": "Read more",
    "source": "View the official website",
    "credit": "Cookies manager by tarteaucitron.js",

    "fallback": "is disabled.",

    "ads": {
        "title": "Advertising network",
        "details": "Ad networks can generate revenue by selling advertising space on the site."
    },
    "analytic": {
        "title": "Audience measurement",
        "details": "The audience measurement services used to generate useful statistics attendance to improve the site."
    },
    "social": {
        "title": "Social networks",
        "details": "Social networks can improve the usability of the site and help to promote it via the shares."
    },
    "video": {
        "title": "Videos",
        "details": "Video sharing services help to add rich media on the site and increase its visibility."
    },
    "comment": {
        "title": "Comments",
        "details": "Comments managers facilitate the filing of comments and fight against spam."
    },
    "support": {
        "title": "Support",
        "details": "Support services allow you to get in touch with the site team and help to improve it."
    },
    "api": {
        "title": "APIs",
        "details": "APIs are used to load scripts: geolocation, search engines, translations, ..."
    },
    "other": {
        "title": "Other",
        "details": "Services to display web content."
    }
};
tarteaucitron.langlist.es = {
    "adblock": "Hola! Este sitio web es transparente y le da la opción de activar los servicios de terceros.",
    "adblock_call": "Por favor deshabilite su AdBlocker para comenzar a personalizar.",
    "reload": "Actualizar esta página",

    "alertBigScroll": "Al continuar para desplazarse,",
    "alertBigClick": "Si continuas navegando por este sitio web,",
    "alertBig": "estar permitiendo servicios terceros",

    "alertBigPrivacy": "Este sitio web usa cookies y te permite controlar lo que deseas activar",
    "alertSmall": "Gestionar servicios",
    "personalize": "Personalizar",
    "acceptAll": "OK, aceptar todas",
    "close": "Cerrar",

    "all": "Preference for all services",

    "info": "Protegiendo tu privacidad",
    "disclaimer": "Aceptando estos servicios terceros, estas aceptando sus cookies y el uso de tecnologías de rastreo necesarias para su correcto funcionamiento.",
    "allow": "Permitir",
    "deny": "Denegar",
    "noCookie": "Este servicio no usa cookie.",
    "useCookie": "Este servicio puede instalar",
    "useCookieCurrent": "Este servicio ha instalado",
    "useNoCookie": "Este servicio no ha instalado ninguna cookie.",
    "more": "Leer más",
    "source": "Ver sitio web oficial",
    "credit": "Gestor de cookies realizada por tarteaucitron.js",

    "fallback": "esta deshabilitado.",

    "ads": {
        "title": "Red de publicidad",
        "details": "Las redes publicitarias pueden generar ingresos mediante la venta de espacios publicitarios en el sitio."
    },
    "analytic": {
        "title": "Mediciión de audiencia",
        "details": "Los servicios de medición de audiencia se usan para generar asistencia estadísticas útiles para mejorar el sitio."
    },
    "social": {
        "title": "Redes sociales",
        "details": "Las redes sociales pueden aumentar la usabilidad del sitio web y ayudar a promoverlo a través de la contribución."
    },
    "video": {
        "title": "Videos",
        "details": "Los servicios para compartir videos ayudan a añadir contenido enriquecido en el sitio web y aumentar su visibilidad."
    },
    "comment": {
        "title": "Comentarios",
        "details": "El gestor de comentarios facilita la clasificación de comentarios y luchar contra spam."
    },
    "support": {
        "title": "Soporte",
        "details": "Los servicios de soporte te permiten contactar con el sitio web y ayudar a mejorarlo."
    },
    "api": {
        "title": "APIs",
        "details": "APIs se utilizan para cargar scripts: geolocalización, motor de búsqueda, traducciones, ..."
    },
    "other": {
        "title": "Otro",
        "details": "Servicios para mostrar contenido web."
    }
};

tarteaucitron.langlist.fr = {
    "adblock": "Bonjour! Ce site joue la transparence et vous donne le choix des services tiers à activer.",
    "adblock_call": "Merci de désactiver votre adblocker pour commencer la personnalisation.",
    "reload": "Recharger la page",

    "alertBigScroll": "En continuant de défiler,",
    "alertBigClick": "En poursuivant votre navigation,",
    "alertBig": "vous acceptez l'utilisation de services tiers pouvant installer des cookies",

    "alertBigPrivacy": "Ce site utilise des cookies et vous donne le contrôle sur ce que vous souhaitez activer",
    "alertSmall": "Gestion des services",
    "acceptAll": "OK, tout accepter",
    "personalize": "Personnaliser",
    "close": "Fermer",

    "all": "Préférence pour tous les services",

    "info": "Protection de votre vie privée",
    "disclaimer": "En autorisant ces services tiers, vous acceptez le dépôt et la lecture de cookies et l'utilisation de technologies de suivi nécessaires à leur bon fonctionnement.",
    "allow": "Autoriser",
    "deny": "Interdire",
    "noCookie": "Ce service ne dépose aucun cookie.",
    "useCookie": "Ce service peut déposer",
    "useCookieCurrent": "Ce service a déposé",
    "useNoCookie": "Ce service n'a déposé aucun cookie.",
    "more": "En savoir plus",
    "source": "Voir le site officiel",
    "credit": "Gestion des cookies par tarteaucitron.js",

    "fallback": "est désactivé.",

    "ads": {
        "title": "Régies publicitaires",
        "details": "Les régies publicitaires permettent de générer des revenus en commercialisant les espaces publicitaires du site."
    },
    "analytic": {
        "title": "Mesure d'audience",
        "details": "Les services de mesure d'audience permettent de générer des statistiques de fréquentation utiles à l'amélioration du site."
    },
    "social": {
        "title": "Réseaux sociaux",
        "details": "Les réseaux sociaux permettent d'améliorer la convivialité du site et aident à sa promotion via les partages."
    },
    "video": {
        "title": "Vidéos",
        "details": "Les services de partage de vidéo permettent d'enrichir le site de contenu multimédia et augmentent sa visibilité."
    },
    "comment": {
        "title": "Commentaires",
        "details": "Les gestionnaires de commentaires facilitent le dépôt de vos commentaires et luttent contre le spam."
    },
    "support": {
        "title": "Support",
        "details": "Les services de support vous permettent d'entrer en contact avec l'équipe du site et d'aider à son amélioration."
    },
    "api": {
        "title": "APIs",
        "details": "Les APIs permettent de charger des scripts : géolocalisation, moteurs de recherche, traductions, ..."
    },
    "other": {
        "title": "Autre",
        "details": "Services visant à afficher du contenu web."
    }
};

tarteaucitron.langlist.it = {
    "adblock": "Benvenuto! Questo sito ti permette di attivare i servizi di terzi di tua scelta.",
    "adblock_call": "Disabilita il tuo adblocker per iniziare la navigazione.",
    "reload": "Aggiorna la pagina",

    "alertBigScroll": "Continuando a scorrere,",
    "alertBigClick": "Continuando a navigare nel sito,",
    "alertBig": "autorizzi l’utilizzo dei cookies inviati da domini di terze parti",

    "alertBigPrivacy": "Questo sito fa uso di cookies e ti consente di decidere se accettarli o rifiutarli",
    "alertSmall": "Gestione dei servizi",
    "acceptAll": "Ok, accetta tutto",
    "personalize": "Personalizza",
    "close": "Chiudi",

    "all": "Preferenze per tutti i servizi",

    "info": "Tutela della privacy",
    "disclaimer": "Abilitando l'uso dei servizi di terze parti, accetti la ricezione dei cookies e l'uso delle tecnologie analitici necessarie al loro funzionamento.",
    "allow": "Consenti",
    "deny": "Blocca",
    "noCookie": "Questo servizio non invia nessun cookie",
    "useCookie": "Questo servizio puo' inviare",
    "useCookieCurrent": "Questo servizio ha inviato",
    "useNoCookie": "Questo servizio non ha inviato nessun cookie",
    "more": "Saperne di più",
    "source": "Vai al sito ufficiale",
    "credit": "Gestione dei cookies da tarteaucitron.js",

    "fallback": "è disattivato",

    "ads": {
        "title": "Regie pubblicitarie",
        "details": "Le regie pubblicitarie producono redditi gestendo la commercializzazione degli spazi del sito dedicati alle campagne pubblicitarie"
    },
    "analytic": {
        "title": "Misura del pubblico",
        "details": "I servizi di misura del pubblico permettono di raccogliere le statistiche utili al miglioramento del sito"
    },
    "social": {
        "title": "Reti sociali",
        "details": "Le reti sociali permettono di migliorare l'aspetto conviviale del sito e di sviluppare la condivisione dei contenuti da parte degli utenti a fini promozionali."
    },
    "video": {
        "title": "Video",
        "details": "I servizi di condivisione di video permettono di arricchire il sito di contenuti multimediali e di aumentare la sua visibilità"
    },
    "comment": {
        "title": "Commenti",
        "details": "La gestione dei commenti utente aiuta a gestire la pubblicazione dei commenti e a lottare contro lo spamming"
    },
    "support": {
        "title": "Supporto",
        "details": "I servizi di supporto ti consentono di contattare la team del sito e di contribuire al suo miglioramento"
    },
    "api": {
        "title": "API",
        "details": "Le API permettono di implementare script diversi : geolocalizzazione, motori di ricerca, traduttori..."
    },
    "other": {
        "title": "Altro",
        "details": "Servizi per visualizzare contenuti web."
    }
};

tarteaucitron.langlist.pl = {
    "adblock": "Witaj! Ta witryna oferuje przejrzystosc i daje mozliwosc wyboru aktywacji uslug zewnetrznych.",
    "adblock_call": "Prosze wylaczyc adblocker aby rozpoczac dostosowanie do potrzeb uzytkownika.",
    "reload": "Odswiez strone",

    "alertBigScroll": "Poprzez kontynuowanie przewijania,",
    "alertBigClick": "Pozostajac na tej stronie",
    "alertBig": "zgadzasz sie na korzystanie ze wszystkich zewnetrzynych uslug",

    "alertBigPrivacy": "Ta witryna używa plików cookie i pozwala kontrolować ich aktywacje",
    "alertSmall": "Zarządzanie usługami",
    "personalize": "Personalizacja",
    "acceptAll": "OK, akceptuję wszystko",
    "close": "zamknij",

    "all": "Preferencja dla wszystkich usług",

    "info": "Ochrona prywatności",
    "disclaimer": "Zgadzajac sie na korzystanie z uslug zewnetrznych , akceptuje ich pliki cookies oraz wykorzystanie technologii niezbędnych do ich funkcjonowania.",
    "allow": "Zezwalaj",
    "deny": "Odmów",
    "noCookie": "Ta usługa nie korzysta z plików cookie.",
    "useCookie": "Ta usługa może zainstalować pliki cookie",
    "useCookieCurrent": "Ta usługa zainstalowala plikie cookie",
    "useNoCookie": "Ta usługa nie zainstalowala żadnego pliku cookie.",
    "more": "Więcej informacji",
    "source": "Zobacz oficjalną stronę internetowa",
    "credit": "Cookies menadżer z tarteaucitron.js",

    "fallback": "jest nieaktywna.",

    "ads": {
        "title": "Sieć reklamowa",
        "details": "Sieci reklamowe mogą generować przychody ze sprzedaży powierzchni reklamowej na stronie."
    },
    "analytic": {
        "title": "Pomiar ogladalnosci",
        "details": "Usługi pomiaru oglądalności wykorzystywane sa do generowania przydatnych statystyk potrzebnych w doskonaleniu strony."
    },
    "social": {
        "title": "Portale społecznościowe",
        "details": "Sieci społecznościowe mogą poprawić użyteczność serwisu i pomóc w promocji za pośrednictwem propagacji strony."
    },
    "video": {
        "title": "Filmy",
        "details": "Usługa udostępniania wideo pomoże dodać multimedia do strony i zwiększyć jej ogladalność."
    },
    "comment": {
        "title": "Komentarze",
        "details": "Zarządzanie komentarzami ułatwia komentowanie i zwalcza spam."
    },
    "support": {
        "title": "Pomoc",
        "details": "Usługa pomocy technicznej pozwala, skontaktować się z administratorem witryny i pomaga ją udoskonalić."
    },
    "api": {
        "title": "APIs",
        "details": "APIs służą do ładowania skryptów: geolokalizacji, wyszukiwarek, tłumaczenia, ..."
    },
    "other": {
        "title": "Inny",
        "details": "Usługi do wyświetlania treści internetowych."
    }

};
tarteaucitron.langlist.pt = {
    "adblock": "Olá! Em uma açao de transparencia, este site lhe dá a opção de quais serviços terceiros deseje ativar.",
    "adblock_call": "Por favor, desative seu bloqueador de publicidades para poder customizar.",
    "reload": "Atualizar esta página",

    "alertBigScroll": "Ao continuar a rolar,",
    "alertBigClick": "Se você continuar a navegaçao neste site,",
    "alertBig": "você estará aceitando todos os serviços terceiros",

    "alertBigPrivacy": "Esse site utiliza cookies and lhe dá controle sobre o que você quer ativar",
    "alertSmall": "Gerenciar serviços",
    "personalize": "Personalizar",
    "acceptAll": "OK, aceitar tudo",
    "close": "Fechar",
    "all": "Definições dos serviços",
    "info": "Proteger sua privacidade",
    "disclaimer": "Ao aceitar os serviços terceiros, você aceita o uso de cookies em conjunto de tecnologias de rastreamento que lhe são necessárias para funcionar",
    "allow": "Autorizar",
    "deny": "Recusar",
    "noCookie": "Este serviço não usa cookies.",
    "useCookie": "Este serviço pode instalar",
    "useCookieCurrent": "Este serviço instalou",
    "useNoCookie": "Este serviço não instalou nenhum cookie.",
    "more": "Ler mais",
    "source": "Ver o site oficial",
    "credit": "Gerenciador de cookies por tarteaucitron.js",
    "fallback": "está desativado.",
    "ads": {
        "title": "Rede de anúncios",
        "details": "As redes de anúncios podem gerar receitas com a venda de espaço publicitário no site."
    },
    "analytic": {
        "title": "Medição de audiência",
        "details": "Serviços de medição de audiência usados para gerar estatísticas no intuito de melhorar o site."
    },
    "social": {
        "title": "Rede sociais",
        "details": "Rede sociais podem ameliorar o utilização do site e ajudar a promove-lo via compartilhamentos."
    },
    "video": {
        "title": "Vídeos",
        "details": "Video sharing services help to add rich media on the site and increase its visibility."
    },
    "comment": {
        "title": "Comentários",
        "details": "Gerenciadores de comentários facilitam o sistema de comentários e lutam contra o spam."
    },
    "support": {
        "title": "Suporte",
        "details": "Serviços de suporte lhe ajudam a entrar em contato com a equipe de suporte."
    },
    "api": {
        "title": "APIs",
        "details": "APIs são usadas para carregar scripts: geolocalização, motores de pesquisa, traduções, ..."
    },
    "other": {
        "title": "De outros",
        "details": "Serviços para exibir conteúdo da web."
    }
};

tarteaucitron.langlist.ru = {
    "adblock": "Привет! Этот сайт совершенно открытый и позволяет вам выбрать сервисы третьих лиц, которым вы хотите дать доступ.",
    "adblock_call": "Пожалуйста дезактивируйте АдБлокер чтобы начать настройку.",
    "reload": "Перезагрузите страницу",

    "alertBigScroll": "Продолжая прокрутки",
    "alertBigClick": "Если вы продолжаете использовать сайт",
    "alertBig": "вы позволяете сервисы третьих лиц",

    "alertBigPrivacy": "Этот сайт использует кукис и позволяет вам контролировать сервисы которые вы хотите активировать",
    "alertSmall": "Настройка сервисов",
    "personalize": "Персонализировать",
    "acceptAll": "Ок, все активировать",
    "close": "Закрыть",

    "all": "Преференция всем сервисам",

    "info": "Защитить вашу конфиденциальность",
    "disclaimer": "Активирование сервисов третьих лиц позволяет использование их кукис и технолоний отслеживания необходимых для их функционирования",
    "allow": "Позролить",
    "deny": "Не позволить",
    "noCookie": "Этот сервис не использует кукис.",
    "useCookie": "Этот сервис может быть инсталирован",
    "useCookieCurrent": "Этот сервис инсталирован",
    "useNoCookie": "Этот сервис не использует кукис.",
    "more": "Подробнее",
    "source": "Посетите официальный сайт",
    "credit": "Кукис манаджер tarteaucitron.js",

    "fallback": "Деактивирован.",

    "ads": {
        "title": "Рекламная сеть",
        "details": "Мы позволяем вам аренду нашей рекламной сети."
    },
    "analytic": {
        "title": "Измерение аудиенции",
        "details": "Измерение аудиенции сайта для статистики помогают улучшить предлагаемый сервис."
    },
    "social": {
        "title": "Социальная сеть",
        "details": "Социальная сеть сайтов помогает улучшить предлагаемый сервис через обмен информации."
    },
    "video": {
        "title": "Видео",
        "details": "Обмен видео информации позволяет улучшить сервис и увеличит траффик сайта."
    },
    "comment": {
        "title": "Комментарии",
        "details": "Манаджер комментариев позволяет обмен информации и борьбу со спамом."
    },
    "support": {
        "title": "Помощь",
        "details": "Помощь позволяет вам контактировать напрямую сайт манаджер и улучшить предлагаемый сервис."
    },
    "api": {
        "title": "АПИ",
        "details": "АПИ используются для загрузки скриптов; геолокация, поисковый мотор и переводы..."
    },
    "other": {
        "title": "Другие",
        "details": "Службы для отображения веб-контента."
    }
};


;(function () {
	'use strict';

	/**
	 * @preserve FastClick: polyfill to remove click delays on browsers with touch UIs.
	 *
	 * @codingstandard ftlabs-jsv2
	 * @copyright The Financial Times Limited [All Rights Reserved]
	 * @license MIT License (see LICENSE.txt)
	 */

	/*jslint browser:true, node:true*/
	/*global define, Event, Node*/


	/**
	 * Instantiate fast-clicking listeners on the specified layer.
	 *
	 * @constructor
	 * @param {Element} layer The layer to listen on
	 * @param {Object} [options={}] The options to override the defaults
	 */
	function FastClick(layer, options) {
		var oldOnClick;

		options = options || {};

		/**
		 * Whether a click is currently being tracked.
		 *
		 * @type boolean
		 */
		this.trackingClick = false;


		/**
		 * Timestamp for when click tracking started.
		 *
		 * @type number
		 */
		this.trackingClickStart = 0;


		/**
		 * The element being tracked for a click.
		 *
		 * @type EventTarget
		 */
		this.targetElement = null;


		/**
		 * X-coordinate of touch start event.
		 *
		 * @type number
		 */
		this.touchStartX = 0;


		/**
		 * Y-coordinate of touch start event.
		 *
		 * @type number
		 */
		this.touchStartY = 0;


		/**
		 * ID of the last touch, retrieved from Touch.identifier.
		 *
		 * @type number
		 */
		this.lastTouchIdentifier = 0;


		/**
		 * Touchmove boundary, beyond which a click will be cancelled.
		 *
		 * @type number
		 */
		this.touchBoundary = options.touchBoundary || 10;


		/**
		 * The FastClick layer.
		 *
		 * @type Element
		 */
		this.layer = layer;

		/**
		 * The minimum time between tap(touchstart and touchend) events
		 *
		 * @type number
		 */
		this.tapDelay = options.tapDelay || 200;

		/**
		 * The maximum time for a tap
		 *
		 * @type number
		 */
		this.tapTimeout = options.tapTimeout || 700;

		if (FastClick.notNeeded(layer)) {
			return;
		}

		// Some old versions of Android don't have Function.prototype.bind
		function bind(method, context) {
			return function() { return method.apply(context, arguments); };
		}


		var methods = ['onMouse', 'onClick', 'onTouchStart', 'onTouchMove', 'onTouchEnd', 'onTouchCancel'];
		var context = this;
		for (var i = 0, l = methods.length; i < l; i++) {
			context[methods[i]] = bind(context[methods[i]], context);
		}

		// Set up event handlers as required
		if (deviceIsAndroid) {
			layer.addEventListener('mouseover', this.onMouse, true);
			layer.addEventListener('mousedown', this.onMouse, true);
			layer.addEventListener('mouseup', this.onMouse, true);
		}

		layer.addEventListener('click', this.onClick, true);
		layer.addEventListener('touchstart', this.onTouchStart, false);
		layer.addEventListener('touchmove', this.onTouchMove, false);
		layer.addEventListener('touchend', this.onTouchEnd, false);
		layer.addEventListener('touchcancel', this.onTouchCancel, false);

		// Hack is required for browsers that don't support Event#stopImmediatePropagation (e.g. Android 2)
		// which is how FastClick normally stops click events bubbling to callbacks registered on the FastClick
		// layer when they are cancelled.
		if (!Event.prototype.stopImmediatePropagation) {
			layer.removeEventListener = function(type, callback, capture) {
				var rmv = Node.prototype.removeEventListener;
				if (type === 'click') {
					rmv.call(layer, type, callback.hijacked || callback, capture);
				} else {
					rmv.call(layer, type, callback, capture);
				}
			};

			layer.addEventListener = function(type, callback, capture) {
				var adv = Node.prototype.addEventListener;
				if (type === 'click') {
					adv.call(layer, type, callback.hijacked || (callback.hijacked = function(event) {
						if (!event.propagationStopped) {
							callback(event);
						}
					}), capture);
				} else {
					adv.call(layer, type, callback, capture);
				}
			};
		}

		// If a handler is already declared in the element's onclick attribute, it will be fired before
		// FastClick's onClick handler. Fix this by pulling out the user-defined handler function and
		// adding it as listener.
		if (typeof layer.onclick === 'function') {

			// Android browser on at least 3.2 requires a new reference to the function in layer.onclick
			// - the old one won't work if passed to addEventListener directly.
			oldOnClick = layer.onclick;
			layer.addEventListener('click', function(event) {
				oldOnClick(event);
			}, false);
			layer.onclick = null;
		}
	}

	/**
	* Windows Phone 8.1 fakes user agent string to look like Android and iPhone.
	*
	* @type boolean
	*/
	var deviceIsWindowsPhone = navigator.userAgent.indexOf("Windows Phone") >= 0;

	/**
	 * Android requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsAndroid = navigator.userAgent.indexOf('Android') > 0 && !deviceIsWindowsPhone;


	/**
	 * iOS requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsIOS = /iP(ad|hone|od)/.test(navigator.userAgent) && !deviceIsWindowsPhone;


	/**
	 * iOS 4 requires an exception for select elements.
	 *
	 * @type boolean
	 */
	var deviceIsIOS4 = deviceIsIOS && (/OS 4_\d(_\d)?/).test(navigator.userAgent);


	/**
	 * iOS 6.0-7.* requires the target element to be manually derived
	 *
	 * @type boolean
	 */
	var deviceIsIOSWithBadTarget = deviceIsIOS && (/OS [6-7]_\d/).test(navigator.userAgent);

	/**
	 * BlackBerry requires exceptions.
	 *
	 * @type boolean
	 */
	var deviceIsBlackBerry10 = navigator.userAgent.indexOf('BB10') > 0;

	/**
	 * Determine whether a given element requires a native click.
	 *
	 * @param {EventTarget|Element} target Target DOM element
	 * @returns {boolean} Returns true if the element needs a native click
	 */
	FastClick.prototype.needsClick = function(target) {
		switch (target.nodeName.toLowerCase()) {

		// Don't send a synthetic click to disabled inputs (issue #62)
		case 'button':
		case 'select':
		case 'textarea':
			if (target.disabled) {
				return true;
			}

			break;
		case 'input':

			// File inputs need real clicks on iOS 6 due to a browser bug (issue #68)
			if ((deviceIsIOS && target.type === 'file') || target.disabled) {
				return true;
			}

			break;
		case 'label':
		case 'iframe': // iOS8 homescreen apps can prevent events bubbling into frames
		case 'video':
			return true;
		}

		return (/\bneedsclick\b/).test(target.className);
	};


	/**
	 * Determine whether a given element requires a call to focus to simulate click into element.
	 *
	 * @param {EventTarget|Element} target Target DOM element
	 * @returns {boolean} Returns true if the element requires a call to focus to simulate native click.
	 */
	FastClick.prototype.needsFocus = function(target) {
		switch (target.nodeName.toLowerCase()) {
		case 'textarea':
			return true;
		case 'select':
			return !deviceIsAndroid;
		case 'input':
			switch (target.type) {
			case 'button':
			case 'checkbox':
			case 'file':
			case 'image':
			case 'radio':
			case 'submit':
				return false;
			}

			// No point in attempting to focus disabled inputs
			return !target.disabled && !target.readOnly;
		default:
			return (/\bneedsfocus\b/).test(target.className);
		}
	};


	/**
	 * Send a click event to the specified element.
	 *
	 * @param {EventTarget|Element} targetElement
	 * @param {Event} event
	 */
	FastClick.prototype.sendClick = function(targetElement, event) {
		var clickEvent, touch;

		// On some Android devices activeElement needs to be blurred otherwise the synthetic click will have no effect (#24)
		if (document.activeElement && document.activeElement !== targetElement) {
			document.activeElement.blur();
		}

		touch = event.changedTouches[0];

		// Synthesise a click event, with an extra attribute so it can be tracked
		clickEvent = document.createEvent('MouseEvents');
		clickEvent.initMouseEvent(this.determineEventType(targetElement), true, true, window, 1, touch.screenX, touch.screenY, touch.clientX, touch.clientY, false, false, false, false, 0, null);
		clickEvent.forwardedTouchEvent = true;
		targetElement.dispatchEvent(clickEvent);
	};

	FastClick.prototype.determineEventType = function(targetElement) {

		//Issue #159: Android Chrome Select Box does not open with a synthetic click event
		if (deviceIsAndroid && targetElement.tagName.toLowerCase() === 'select') {
			return 'mousedown';
		}

		return 'click';
	};


	/**
	 * @param {EventTarget|Element} targetElement
	 */
	FastClick.prototype.focus = function(targetElement) {
		var length;

		// Issue #160: on iOS 7, some input elements (e.g. date datetime month) throw a vague TypeError on setSelectionRange. These elements don't have an integer value for the selectionStart and selectionEnd properties, but unfortunately that can't be used for detection because accessing the properties also throws a TypeError. Just check the type instead. Filed as Apple bug #15122724.
		if (deviceIsIOS && targetElement.setSelectionRange && targetElement.type.indexOf('date') !== 0 && targetElement.type !== 'time' && targetElement.type !== 'month' && targetElement.type !== 'email') {
			length = targetElement.value.length;
			targetElement.setSelectionRange(length, length);
		} else {
			targetElement.focus();
		}
	};


	/**
	 * Check whether the given target element is a child of a scrollable layer and if so, set a flag on it.
	 *
	 * @param {EventTarget|Element} targetElement
	 */
	FastClick.prototype.updateScrollParent = function(targetElement) {
		var scrollParent, parentElement;

		scrollParent = targetElement.fastClickScrollParent;

		// Attempt to discover whether the target element is contained within a scrollable layer. Re-check if the
		// target element was moved to another parent.
		if (!scrollParent || !scrollParent.contains(targetElement)) {
			parentElement = targetElement;
			do {
				if (parentElement.scrollHeight > parentElement.offsetHeight) {
					scrollParent = parentElement;
					targetElement.fastClickScrollParent = parentElement;
					break;
				}

				parentElement = parentElement.parentElement;
			} while (parentElement);
		}

		// Always update the scroll top tracker if possible.
		if (scrollParent) {
			scrollParent.fastClickLastScrollTop = scrollParent.scrollTop;
		}
	};


	/**
	 * @param {EventTarget} targetElement
	 * @returns {Element|EventTarget}
	 */
	FastClick.prototype.getTargetElementFromEventTarget = function(eventTarget) {

		// On some older browsers (notably Safari on iOS 4.1 - see issue #56) the event target may be a text node.
		if (eventTarget.nodeType === Node.TEXT_NODE) {
			return eventTarget.parentNode;
		}

		return eventTarget;
	};


	/**
	 * On touch start, record the position and scroll offset.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchStart = function(event) {
		var targetElement, touch, selection;

		// Ignore multiple touches, otherwise pinch-to-zoom is prevented if both fingers are on the FastClick element (issue #111).
		if (event.targetTouches.length > 1) {
			return true;
		}

		targetElement = this.getTargetElementFromEventTarget(event.target);
		touch = event.targetTouches[0];

		if (deviceIsIOS) {

			// Only trusted events will deselect text on iOS (issue #49)
			selection = window.getSelection();
			if (selection.rangeCount && !selection.isCollapsed) {
				return true;
			}

			if (!deviceIsIOS4) {

				// Weird things happen on iOS when an alert or confirm dialog is opened from a click event callback (issue #23):
				// when the user next taps anywhere else on the page, new touchstart and touchend events are dispatched
				// with the same identifier as the touch event that previously triggered the click that triggered the alert.
				// Sadly, there is an issue on iOS 4 that causes some normal touch events to have the same identifier as an
				// immediately preceeding touch event (issue #52), so this fix is unavailable on that platform.
				// Issue 120: touch.identifier is 0 when Chrome dev tools 'Emulate touch events' is set with an iOS device UA string,
				// which causes all touch events to be ignored. As this block only applies to iOS, and iOS identifiers are always long,
				// random integers, it's safe to to continue if the identifier is 0 here.
				if (touch.identifier && touch.identifier === this.lastTouchIdentifier) {
					event.preventDefault();
					return false;
				}

				this.lastTouchIdentifier = touch.identifier;

				// If the target element is a child of a scrollable layer (using -webkit-overflow-scrolling: touch) and:
				// 1) the user does a fling scroll on the scrollable layer
				// 2) the user stops the fling scroll with another tap
				// then the event.target of the last 'touchend' event will be the element that was under the user's finger
				// when the fling scroll was started, causing FastClick to send a click event to that layer - unless a check
				// is made to ensure that a parent layer was not scrolled before sending a synthetic click (issue #42).
				this.updateScrollParent(targetElement);
			}
		}

		this.trackingClick = true;
		this.trackingClickStart = event.timeStamp;
		this.targetElement = targetElement;

		this.touchStartX = touch.pageX;
		this.touchStartY = touch.pageY;

		// Prevent phantom clicks on fast double-tap (issue #36)
		if ((event.timeStamp - this.lastClickTime) < this.tapDelay) {
			event.preventDefault();
		}

		return true;
	};


	/**
	 * Based on a touchmove event object, check whether the touch has moved past a boundary since it started.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.touchHasMoved = function(event) {
		var touch = event.changedTouches[0], boundary = this.touchBoundary;

		if (Math.abs(touch.pageX - this.touchStartX) > boundary || Math.abs(touch.pageY - this.touchStartY) > boundary) {
			return true;
		}

		return false;
	};


	/**
	 * Update the last position.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchMove = function(event) {
		if (!this.trackingClick) {
			return true;
		}

		// If the touch has moved, cancel the click tracking
		if (this.targetElement !== this.getTargetElementFromEventTarget(event.target) || this.touchHasMoved(event)) {
			this.trackingClick = false;
			this.targetElement = null;
		}

		return true;
	};


	/**
	 * Attempt to find the labelled control for the given label element.
	 *
	 * @param {EventTarget|HTMLLabelElement} labelElement
	 * @returns {Element|null}
	 */
	FastClick.prototype.findControl = function(labelElement) {

		// Fast path for newer browsers supporting the HTML5 control attribute
		if (labelElement.control !== undefined) {
			return labelElement.control;
		}

		// All browsers under test that support touch events also support the HTML5 htmlFor attribute
		if (labelElement.htmlFor) {
			return document.getElementById(labelElement.htmlFor);
		}

		// If no for attribute exists, attempt to retrieve the first labellable descendant element
		// the list of which is defined here: http://www.w3.org/TR/html5/forms.html#category-label
		return labelElement.querySelector('button, input:not([type=hidden]), keygen, meter, output, progress, select, textarea');
	};


	/**
	 * On touch end, determine whether to send a click event at once.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onTouchEnd = function(event) {
		var forElement, trackingClickStart, targetTagName, scrollParent, touch, targetElement = this.targetElement;

		if (!this.trackingClick) {
			return true;
		}

		// Prevent phantom clicks on fast double-tap (issue #36)
		if ((event.timeStamp - this.lastClickTime) < this.tapDelay) {
			this.cancelNextClick = true;
			return true;
		}

		if ((event.timeStamp - this.trackingClickStart) > this.tapTimeout) {
			return true;
		}

		// Reset to prevent wrong click cancel on input (issue #156).
		this.cancelNextClick = false;

		this.lastClickTime = event.timeStamp;

		trackingClickStart = this.trackingClickStart;
		this.trackingClick = false;
		this.trackingClickStart = 0;

		// On some iOS devices, the targetElement supplied with the event is invalid if the layer
		// is performing a transition or scroll, and has to be re-detected manually. Note that
		// for this to function correctly, it must be called *after* the event target is checked!
		// See issue #57; also filed as rdar://13048589 .
		if (deviceIsIOSWithBadTarget) {
			touch = event.changedTouches[0];

			// In certain cases arguments of elementFromPoint can be negative, so prevent setting targetElement to null
			targetElement = document.elementFromPoint(touch.pageX - window.pageXOffset, touch.pageY - window.pageYOffset) || targetElement;
			targetElement.fastClickScrollParent = this.targetElement.fastClickScrollParent;
		}

		targetTagName = targetElement.tagName.toLowerCase();
		if (targetTagName === 'label') {
			forElement = this.findControl(targetElement);
			if (forElement) {
				this.focus(targetElement);
				if (deviceIsAndroid) {
					return false;
				}

				targetElement = forElement;
			}
		} else if (this.needsFocus(targetElement)) {

			// Case 1: If the touch started a while ago (best guess is 100ms based on tests for issue #36) then focus will be triggered anyway. Return early and unset the target element reference so that the subsequent click will be allowed through.
			// Case 2: Without this exception for input elements tapped when the document is contained in an iframe, then any inputted text won't be visible even though the value attribute is updated as the user types (issue #37).
			if ((event.timeStamp - trackingClickStart) > 100 || (deviceIsIOS && window.top !== window && targetTagName === 'input')) {
				this.targetElement = null;
				return false;
			}

			this.focus(targetElement);
			this.sendClick(targetElement, event);

			// Select elements need the event to go through on iOS 4, otherwise the selector menu won't open.
			// Also this breaks opening selects when VoiceOver is active on iOS6, iOS7 (and possibly others)
			if (!deviceIsIOS || targetTagName !== 'select') {
				this.targetElement = null;
				event.preventDefault();
			}

			return false;
		}

		if (deviceIsIOS && !deviceIsIOS4) {

			// Don't send a synthetic click event if the target element is contained within a parent layer that was scrolled
			// and this tap is being used to stop the scrolling (usually initiated by a fling - issue #42).
			scrollParent = targetElement.fastClickScrollParent;
			if (scrollParent && scrollParent.fastClickLastScrollTop !== scrollParent.scrollTop) {
				return true;
			}
		}

		// Prevent the actual click from going though - unless the target node is marked as requiring
		// real clicks or if it is in the whitelist in which case only non-programmatic clicks are permitted.
		if (!this.needsClick(targetElement)) {
			event.preventDefault();
			this.sendClick(targetElement, event);
		}

		return false;
	};


	/**
	 * On touch cancel, stop tracking the click.
	 *
	 * @returns {void}
	 */
	FastClick.prototype.onTouchCancel = function() {
		this.trackingClick = false;
		this.targetElement = null;
	};


	/**
	 * Determine mouse events which should be permitted.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onMouse = function(event) {

		// If a target element was never set (because a touch event was never fired) allow the event
		if (!this.targetElement) {
			return true;
		}

		if (event.forwardedTouchEvent) {
			return true;
		}

		// Programmatically generated events targeting a specific element should be permitted
		if (!event.cancelable) {
			return true;
		}

		// Derive and check the target element to see whether the mouse event needs to be permitted;
		// unless explicitly enabled, prevent non-touch click events from triggering actions,
		// to prevent ghost/doubleclicks.
		if (!this.needsClick(this.targetElement) || this.cancelNextClick) {

			// Prevent any user-added listeners declared on FastClick element from being fired.
			if (event.stopImmediatePropagation) {
				event.stopImmediatePropagation();
			} else {

				// Part of the hack for browsers that don't support Event#stopImmediatePropagation (e.g. Android 2)
				event.propagationStopped = true;
			}

			// Cancel the event
			event.stopPropagation();
			event.preventDefault();

			return false;
		}

		// If the mouse event is permitted, return true for the action to go through.
		return true;
	};


	/**
	 * On actual clicks, determine whether this is a touch-generated click, a click action occurring
	 * naturally after a delay after a touch (which needs to be cancelled to avoid duplication), or
	 * an actual click which should be permitted.
	 *
	 * @param {Event} event
	 * @returns {boolean}
	 */
	FastClick.prototype.onClick = function(event) {
		var permitted;

		// It's possible for another FastClick-like library delivered with third-party code to fire a click event before FastClick does (issue #44). In that case, set the click-tracking flag back to false and return early. This will cause onTouchEnd to return early.
		if (this.trackingClick) {
			this.targetElement = null;
			this.trackingClick = false;
			return true;
		}

		// Very odd behaviour on iOS (issue #18): if a submit element is present inside a form and the user hits enter in the iOS simulator or clicks the Go button on the pop-up OS keyboard the a kind of 'fake' click event will be triggered with the submit-type input element as the target.
		if (event.target.type === 'submit' && event.detail === 0) {
			return true;
		}

		permitted = this.onMouse(event);

		// Only unset targetElement if the click is not permitted. This will ensure that the check for !targetElement in onMouse fails and the browser's click doesn't go through.
		if (!permitted) {
			this.targetElement = null;
		}

		// If clicks are permitted, return true for the action to go through.
		return permitted;
	};


	/**
	 * Remove all FastClick's event listeners.
	 *
	 * @returns {void}
	 */
	FastClick.prototype.destroy = function() {
		var layer = this.layer;

		if (deviceIsAndroid) {
			layer.removeEventListener('mouseover', this.onMouse, true);
			layer.removeEventListener('mousedown', this.onMouse, true);
			layer.removeEventListener('mouseup', this.onMouse, true);
		}

		layer.removeEventListener('click', this.onClick, true);
		layer.removeEventListener('touchstart', this.onTouchStart, false);
		layer.removeEventListener('touchmove', this.onTouchMove, false);
		layer.removeEventListener('touchend', this.onTouchEnd, false);
		layer.removeEventListener('touchcancel', this.onTouchCancel, false);
	};


	/**
	 * Check whether FastClick is needed.
	 *
	 * @param {Element} layer The layer to listen on
	 */
	FastClick.notNeeded = function(layer) {
		var metaViewport;
		var chromeVersion;
		var blackberryVersion;
		var firefoxVersion;

		// Devices that don't support touch don't need FastClick
		if (typeof window.ontouchstart === 'undefined') {
			return true;
		}

		// Chrome version - zero for other browsers
		chromeVersion = +(/Chrome\/([0-9]+)/.exec(navigator.userAgent) || [,0])[1];

		if (chromeVersion) {

			if (deviceIsAndroid) {
				metaViewport = document.querySelector('meta[name=viewport]');

				if (metaViewport) {
					// Chrome on Android with user-scalable="no" doesn't need FastClick (issue #89)
					if (metaViewport.content.indexOf('user-scalable=no') !== -1) {
						return true;
					}
					// Chrome 32 and above with width=device-width or less don't need FastClick
					if (chromeVersion > 31 && document.documentElement.scrollWidth <= window.outerWidth) {
						return true;
					}
				}

			// Chrome desktop doesn't need FastClick (issue #15)
			} else {
				return true;
			}
		}

		if (deviceIsBlackBerry10) {
			blackberryVersion = navigator.userAgent.match(/Version\/([0-9]*)\.([0-9]*)/);

			// BlackBerry 10.3+ does not require Fastclick library.
			// https://github.com/ftlabs/fastclick/issues/251
			if (blackberryVersion[1] >= 10 && blackberryVersion[2] >= 3) {
				metaViewport = document.querySelector('meta[name=viewport]');

				if (metaViewport) {
					// user-scalable=no eliminates click delay.
					if (metaViewport.content.indexOf('user-scalable=no') !== -1) {
						return true;
					}
					// width=device-width (or less than device-width) eliminates click delay.
					if (document.documentElement.scrollWidth <= window.outerWidth) {
						return true;
					}
				}
			}
		}

		// IE10 with -ms-touch-action: none or manipulation, which disables double-tap-to-zoom (issue #97)
		if (layer.style.msTouchAction === 'none' || layer.style.touchAction === 'manipulation') {
			return true;
		}

		// Firefox version - zero for other browsers
		firefoxVersion = +(/Firefox\/([0-9]+)/.exec(navigator.userAgent) || [,0])[1];

		if (firefoxVersion >= 27) {
			// Firefox 27+ does not have tap delay if the content is not zoomable - https://bugzilla.mozilla.org/show_bug.cgi?id=922896

			metaViewport = document.querySelector('meta[name=viewport]');
			if (metaViewport && (metaViewport.content.indexOf('user-scalable=no') !== -1 || document.documentElement.scrollWidth <= window.outerWidth)) {
				return true;
			}
		}

		// IE11: prefixed -ms-touch-action is no longer supported and it's recomended to use non-prefixed version
		// http://msdn.microsoft.com/en-us/library/windows/apps/Hh767313.aspx
		if (layer.style.touchAction === 'none' || layer.style.touchAction === 'manipulation') {
			return true;
		}

		return false;
	};


	/**
	 * Factory method for creating a FastClick object
	 *
	 * @param {Element} layer The layer to listen on
	 * @param {Object} [options={}] The options to override the defaults
	 */
	FastClick.attach = function(layer, options) {
		return new FastClick(layer, options);
	};


	if (typeof define === 'function' && typeof define.amd === 'object' && define.amd) {

		// AMD. Register as an anonymous module.
		define(function() {
			return FastClick;
		});
	} else if (typeof module !== 'undefined' && module.exports) {
		module.exports = FastClick.attach;
		module.exports.FastClick = FastClick;
	} else {
		window.FastClick = FastClick;
	}
}());
!function(e){if("object"==typeof exports&&"undefined"!=typeof module)module.exports=e();else if("function"==typeof define&&define.amd)define([],e);else{var t;t="undefined"!=typeof window?window:"undefined"!=typeof global?global:"undefined"!=typeof self?self:this,t.flexibility=e()}}(function(){return function e(t,r,l){function n(f,i){if(!r[f]){if(!t[f]){var s="function"==typeof require&&require;if(!i&&s)return s(f,!0);if(o)return o(f,!0);var a=new Error("Cannot find module '"+f+"'");throw a.code="MODULE_NOT_FOUND",a}var c=r[f]={exports:{}};t[f][0].call(c.exports,function(e){var r=t[f][1][e];return n(r?r:e)},c,c.exports,e,t,r,l)}return r[f].exports}for(var o="function"==typeof require&&require,f=0;f<l.length;f++)n(l[f]);return n}({1:[function(e,t,r){t.exports=function(e){var t,r,l,n=-1;if(e.lines.length>1&&"flex-start"===e.style.alignContent)for(t=0;l=e.lines[++n];)l.crossStart=t,t+=l.cross;else if(e.lines.length>1&&"flex-end"===e.style.alignContent)for(t=e.flexStyle.crossSpace;l=e.lines[++n];)l.crossStart=t,t+=l.cross;else if(e.lines.length>1&&"center"===e.style.alignContent)for(t=e.flexStyle.crossSpace/2;l=e.lines[++n];)l.crossStart=t,t+=l.cross;else if(e.lines.length>1&&"space-between"===e.style.alignContent)for(r=e.flexStyle.crossSpace/(e.lines.length-1),t=0;l=e.lines[++n];)l.crossStart=t,t+=l.cross+r;else if(e.lines.length>1&&"space-around"===e.style.alignContent)for(r=2*e.flexStyle.crossSpace/(2*e.lines.length),t=r/2;l=e.lines[++n];)l.crossStart=t,t+=l.cross+r;else for(r=e.flexStyle.crossSpace/e.lines.length,t=e.flexStyle.crossInnerBefore;l=e.lines[++n];)l.crossStart=t,l.cross+=r,t+=l.cross}},{}],2:[function(e,t,r){t.exports=function(e){for(var t,r=-1;line=e.lines[++r];)for(t=-1;child=line.children[++t];){var l=child.style.alignSelf;"auto"===l&&(l=e.style.alignItems),"flex-start"===l?child.flexStyle.crossStart=line.crossStart:"flex-end"===l?child.flexStyle.crossStart=line.crossStart+line.cross-child.flexStyle.crossOuter:"center"===l?child.flexStyle.crossStart=line.crossStart+(line.cross-child.flexStyle.crossOuter)/2:(child.flexStyle.crossStart=line.crossStart,child.flexStyle.crossOuter=line.cross,child.flexStyle.cross=child.flexStyle.crossOuter-child.flexStyle.crossBefore-child.flexStyle.crossAfter)}}},{}],3:[function(e,t,r){t.exports=function l(e,l){var t="row"===l||"row-reverse"===l,r=e.mainAxis;if(r){var n=t&&"inline"===r||!t&&"block"===r;n||(e.flexStyle={main:e.flexStyle.cross,cross:e.flexStyle.main,mainOffset:e.flexStyle.crossOffset,crossOffset:e.flexStyle.mainOffset,mainBefore:e.flexStyle.crossBefore,mainAfter:e.flexStyle.crossAfter,crossBefore:e.flexStyle.mainBefore,crossAfter:e.flexStyle.mainAfter,mainInnerBefore:e.flexStyle.crossInnerBefore,mainInnerAfter:e.flexStyle.crossInnerAfter,crossInnerBefore:e.flexStyle.mainInnerBefore,crossInnerAfter:e.flexStyle.mainInnerAfter,mainBorderBefore:e.flexStyle.crossBorderBefore,mainBorderAfter:e.flexStyle.crossBorderAfter,crossBorderBefore:e.flexStyle.mainBorderBefore,crossBorderAfter:e.flexStyle.mainBorderAfter})}else t?e.flexStyle={main:e.style.width,cross:e.style.height,mainOffset:e.style.offsetWidth,crossOffset:e.style.offsetHeight,mainBefore:e.style.marginLeft,mainAfter:e.style.marginRight,crossBefore:e.style.marginTop,crossAfter:e.style.marginBottom,mainInnerBefore:e.style.paddingLeft,mainInnerAfter:e.style.paddingRight,crossInnerBefore:e.style.paddingTop,crossInnerAfter:e.style.paddingBottom,mainBorderBefore:e.style.borderLeftWidth,mainBorderAfter:e.style.borderRightWidth,crossBorderBefore:e.style.borderTopWidth,crossBorderAfter:e.style.borderBottomWidth}:e.flexStyle={main:e.style.height,cross:e.style.width,mainOffset:e.style.offsetHeight,crossOffset:e.style.offsetWidth,mainBefore:e.style.marginTop,mainAfter:e.style.marginBottom,crossBefore:e.style.marginLeft,crossAfter:e.style.marginRight,mainInnerBefore:e.style.paddingTop,mainInnerAfter:e.style.paddingBottom,crossInnerBefore:e.style.paddingLeft,crossInnerAfter:e.style.paddingRight,mainBorderBefore:e.style.borderTopWidth,mainBorderAfter:e.style.borderBottomWidth,crossBorderBefore:e.style.borderLeftWidth,crossBorderAfter:e.style.borderRightWidth},"content-box"===e.style.boxSizing&&("number"==typeof e.flexStyle.main&&(e.flexStyle.main+=e.flexStyle.mainInnerBefore+e.flexStyle.mainInnerAfter+e.flexStyle.mainBorderBefore+e.flexStyle.mainBorderAfter),"number"==typeof e.flexStyle.cross&&(e.flexStyle.cross+=e.flexStyle.crossInnerBefore+e.flexStyle.crossInnerAfter+e.flexStyle.crossBorderBefore+e.flexStyle.crossBorderAfter));e.mainAxis=t?"inline":"block",e.crossAxis=t?"block":"inline","number"==typeof e.style.flexBasis&&(e.flexStyle.main=e.style.flexBasis+e.flexStyle.mainInnerBefore+e.flexStyle.mainInnerAfter+e.flexStyle.mainBorderBefore+e.flexStyle.mainBorderAfter),e.flexStyle.mainOuter=e.flexStyle.main,e.flexStyle.crossOuter=e.flexStyle.cross,"auto"===e.flexStyle.mainOuter&&(e.flexStyle.mainOuter=e.flexStyle.mainOffset),"auto"===e.flexStyle.crossOuter&&(e.flexStyle.crossOuter=e.flexStyle.crossOffset),"number"==typeof e.flexStyle.mainBefore&&(e.flexStyle.mainOuter+=e.flexStyle.mainBefore),"number"==typeof e.flexStyle.mainAfter&&(e.flexStyle.mainOuter+=e.flexStyle.mainAfter),"number"==typeof e.flexStyle.crossBefore&&(e.flexStyle.crossOuter+=e.flexStyle.crossBefore),"number"==typeof e.flexStyle.crossAfter&&(e.flexStyle.crossOuter+=e.flexStyle.crossAfter)}},{}],4:[function(e,t,r){var l=e("../reduce");t.exports=function(e){if(e.mainSpace>0){var t=l(e.children,function(e,t){return e+parseFloat(t.style.flexGrow)},0);t>0&&(e.main=l(e.children,function(r,l){return"auto"===l.flexStyle.main?l.flexStyle.main=l.flexStyle.mainOffset+parseFloat(l.style.flexGrow)/t*e.mainSpace:l.flexStyle.main+=parseFloat(l.style.flexGrow)/t*e.mainSpace,l.flexStyle.mainOuter=l.flexStyle.main+l.flexStyle.mainBefore+l.flexStyle.mainAfter,r+l.flexStyle.mainOuter},0),e.mainSpace=0)}}},{"../reduce":12}],5:[function(e,t,r){var l=e("../reduce");t.exports=function(e){if(e.mainSpace<0){var t=l(e.children,function(e,t){return e+parseFloat(t.style.flexShrink)},0);t>0&&(e.main=l(e.children,function(r,l){return l.flexStyle.main+=parseFloat(l.style.flexShrink)/t*e.mainSpace,l.flexStyle.mainOuter=l.flexStyle.main+l.flexStyle.mainBefore+l.flexStyle.mainAfter,r+l.flexStyle.mainOuter},0),e.mainSpace=0)}}},{"../reduce":12}],6:[function(e,t,r){var l=e("../reduce");t.exports=function(e){var t;e.lines=[t={main:0,cross:0,children:[]}];for(var r,n=-1;r=e.children[++n];)"nowrap"===e.style.flexWrap||0===t.children.length||"auto"===e.flexStyle.main||e.flexStyle.main-e.flexStyle.mainInnerBefore-e.flexStyle.mainInnerAfter-e.flexStyle.mainBorderBefore-e.flexStyle.mainBorderAfter>=t.main+r.flexStyle.mainOuter?(t.main+=r.flexStyle.mainOuter,t.cross=Math.max(t.cross,r.flexStyle.crossOuter)):e.lines.push(t={main:r.flexStyle.mainOuter,cross:r.flexStyle.crossOuter,children:[]}),t.children.push(r);e.flexStyle.mainLines=l(e.lines,function(e,t){return Math.max(e,t.main)},0),e.flexStyle.crossLines=l(e.lines,function(e,t){return e+t.cross},0),"auto"===e.flexStyle.main&&(e.flexStyle.main=Math.max(e.flexStyle.mainOffset,e.flexStyle.mainLines+e.flexStyle.mainInnerBefore+e.flexStyle.mainInnerAfter+e.flexStyle.mainBorderBefore+e.flexStyle.mainBorderAfter)),"auto"===e.flexStyle.cross&&(e.flexStyle.cross=Math.max(e.flexStyle.crossOffset,e.flexStyle.crossLines+e.flexStyle.crossInnerBefore+e.flexStyle.crossInnerAfter+e.flexStyle.crossBorderBefore+e.flexStyle.crossBorderAfter)),e.flexStyle.crossSpace=e.flexStyle.cross-e.flexStyle.crossInnerBefore-e.flexStyle.crossInnerAfter-e.flexStyle.crossBorderBefore-e.flexStyle.crossBorderAfter-e.flexStyle.crossLines,e.flexStyle.mainOuter=e.flexStyle.main+e.flexStyle.mainBefore+e.flexStyle.mainAfter,e.flexStyle.crossOuter=e.flexStyle.cross+e.flexStyle.crossBefore+e.flexStyle.crossAfter}},{"../reduce":12}],7:[function(e,t,r){function l(t){for(var r,l=-1;r=t.children[++l];)e("./flex-direction")(r,t.style.flexDirection);e("./flex-direction")(t,t.style.flexDirection),e("./order")(t),e("./flexbox-lines")(t),e("./align-content")(t),l=-1;for(var n;n=t.lines[++l];)n.mainSpace=t.flexStyle.main-t.flexStyle.mainInnerBefore-t.flexStyle.mainInnerAfter-t.flexStyle.mainBorderBefore-t.flexStyle.mainBorderAfter-n.main,e("./flex-grow")(n),e("./flex-shrink")(n),e("./margin-main")(n),e("./margin-cross")(n),e("./justify-content")(n,t.style.justifyContent,t);e("./align-items")(t)}t.exports=l},{"./align-content":1,"./align-items":2,"./flex-direction":3,"./flex-grow":4,"./flex-shrink":5,"./flexbox-lines":6,"./justify-content":8,"./margin-cross":9,"./margin-main":10,"./order":11}],8:[function(e,t,r){t.exports=function(e,t,r){var l,n,o,f=r.flexStyle.mainInnerBefore,i=-1;if("flex-end"===t)for(l=e.mainSpace,l+=f;o=e.children[++i];)o.flexStyle.mainStart=l,l+=o.flexStyle.mainOuter;else if("center"===t)for(l=e.mainSpace/2,l+=f;o=e.children[++i];)o.flexStyle.mainStart=l,l+=o.flexStyle.mainOuter;else if("space-between"===t)for(n=e.mainSpace/(e.children.length-1),l=0,l+=f;o=e.children[++i];)o.flexStyle.mainStart=l,l+=o.flexStyle.mainOuter+n;else if("space-around"===t)for(n=2*e.mainSpace/(2*e.children.length),l=n/2,l+=f;o=e.children[++i];)o.flexStyle.mainStart=l,l+=o.flexStyle.mainOuter+n;else for(l=0,l+=f;o=e.children[++i];)o.flexStyle.mainStart=l,l+=o.flexStyle.mainOuter}},{}],9:[function(e,t,r){t.exports=function(e){for(var t,r=-1;t=e.children[++r];){var l=0;"auto"===t.flexStyle.crossBefore&&++l,"auto"===t.flexStyle.crossAfter&&++l;var n=e.cross-t.flexStyle.crossOuter;"auto"===t.flexStyle.crossBefore&&(t.flexStyle.crossBefore=n/l),"auto"===t.flexStyle.crossAfter&&(t.flexStyle.crossAfter=n/l),"auto"===t.flexStyle.cross?t.flexStyle.crossOuter=t.flexStyle.crossOffset+t.flexStyle.crossBefore+t.flexStyle.crossAfter:t.flexStyle.crossOuter=t.flexStyle.cross+t.flexStyle.crossBefore+t.flexStyle.crossAfter}}},{}],10:[function(e,t,r){t.exports=function(e){for(var t,r=0,l=-1;t=e.children[++l];)"auto"===t.flexStyle.mainBefore&&++r,"auto"===t.flexStyle.mainAfter&&++r;if(r>0){for(l=-1;t=e.children[++l];)"auto"===t.flexStyle.mainBefore&&(t.flexStyle.mainBefore=e.mainSpace/r),"auto"===t.flexStyle.mainAfter&&(t.flexStyle.mainAfter=e.mainSpace/r),"auto"===t.flexStyle.main?t.flexStyle.mainOuter=t.flexStyle.mainOffset+t.flexStyle.mainBefore+t.flexStyle.mainAfter:t.flexStyle.mainOuter=t.flexStyle.main+t.flexStyle.mainBefore+t.flexStyle.mainAfter;e.mainSpace=0}}},{}],11:[function(e,t,r){var l=/^(column|row)-reverse$/;t.exports=function(e){e.children.sort(function(e,t){return e.style.order-t.style.order||e.index-t.index}),l.test(e.style.flexDirection)&&e.children.reverse()}},{}],12:[function(e,t,r){function l(e,t,r){for(var l=e.length,n=-1;++n<l;)n in e&&(r=t(r,e[n],n));return r}t.exports=l},{}],13:[function(e,t,r){function l(e){i(f(e))}var n=e("./read"),o=e("./write"),f=e("./readAll"),i=e("./writeAll");t.exports=l,t.exports.read=n,t.exports.write=o,t.exports.readAll=f,t.exports.writeAll=i},{"./read":15,"./readAll":16,"./write":17,"./writeAll":18}],14:[function(e,t,r){function l(e,t,r){var l=e[t],f=String(l).match(o);if(!f){var a=t.match(s);if(a){var c=e["border"+a[1]+"Style"];return"none"===c?0:i[l]||0}return l}var y=f[1],x=f[2];return"px"===x?1*y:"cm"===x?.3937*y*96:"in"===x?96*y:"mm"===x?.3937*y*96/10:"pc"===x?12*y*96/72:"pt"===x?96*y/72:"rem"===x?16*y:n(l,r)}function n(e,t){f.style.cssText="border:none!important;clip:rect(0 0 0 0)!important;display:block!important;font-size:1em!important;height:0!important;margin:0!important;padding:0!important;position:relative!important;width:"+e+"!important",t.parentNode.insertBefore(f,t.nextSibling);var r=f.offsetWidth;return t.parentNode.removeChild(f),r}t.exports=l;var o=/^([-+]?\d*\.?\d+)(%|[a-z]+)$/,f=document.createElement("div"),i={medium:4,none:0,thick:6,thin:2},s=/^border(Bottom|Left|Right|Top)Width$/},{}],15:[function(e,t,r){function l(e){var t={alignContent:"stretch",alignItems:"stretch",alignSelf:"auto",borderBottomStyle:"none",borderBottomWidth:0,borderLeftStyle:"none",borderLeftWidth:0,borderRightStyle:"none",borderRightWidth:0,borderTopStyle:"none",borderTopWidth:0,boxSizing:"content-box",display:"inline",flexBasis:"auto",flexDirection:"row",flexGrow:0,flexShrink:1,flexWrap:"nowrap",justifyContent:"flex-start",height:"auto",marginTop:0,marginRight:0,marginLeft:0,marginBottom:0,paddingTop:0,paddingRight:0,paddingLeft:0,paddingBottom:0,maxHeight:"none",maxWidth:"none",minHeight:0,minWidth:0,order:0,position:"static",width:"auto"},r=e instanceof Element;if(r){var l=e.hasAttribute("data-style"),i=l?e.getAttribute("data-style"):e.getAttribute("style")||"";l||e.setAttribute("data-style",i);var s=window.getComputedStyle&&getComputedStyle(e)||{};f(t,s);var c=e.currentStyle||{};n(t,c),o(t,i);for(var y in t)t[y]=a(t,y,e);var x=e.getBoundingClientRect();t.offsetHeight=x.height||e.offsetHeight,t.offsetWidth=x.width||e.offsetWidth}var S={element:e,style:t};return S}function n(e,t){for(var r in e){var l=r in t;if(l)e[r]=t[r];else{var n=r.replace(/[A-Z]/g,"-$&").toLowerCase(),o=n in t;o&&(e[r]=t[n])}}var f="-js-display"in t;f&&(e.display=t["-js-display"])}function o(e,t){for(var r;r=i.exec(t);){var l=r[1].toLowerCase().replace(/-[a-z]/g,function(e){return e.slice(1).toUpperCase()});e[l]=r[2]}}function f(e,t){for(var r in e){var l=r in t;l&&!s.test(r)&&(e[r]=t[r])}}t.exports=l;var i=/([^\s:;]+)\s*:\s*([^;]+?)\s*(;|$)/g,s=/^(alignSelf|height|width)$/,a=e("./getComputedLength")},{"./getComputedLength":14}],16:[function(e,t,r){function l(e){var t=[];return n(e,t),t}function n(e,t){for(var r,l=o(e),i=[],s=-1;r=e.childNodes[++s];){var a=3===r.nodeType&&!/^\s*$/.test(r.nodeValue);if(l&&a){var c=r;r=e.insertBefore(document.createElement("flex-item"),c),r.appendChild(c)}var y=r instanceof Element;if(y){var x=n(r,t);if(l){var S=r.style;S.display="inline-block",S.position="absolute",x.style=f(r).style,i.push(x)}}}var m={element:e,children:i};return l&&(m.style=f(e).style,t.push(m)),m}function o(e){var t=e instanceof Element,r=t&&e.getAttribute("data-style"),l=t&&e.currentStyle&&e.currentStyle["-js-display"],n=i.test(r)||s.test(l);return n}t.exports=l;var f=e("../read"),i=/(^|;)\s*display\s*:\s*(inline-)?flex\s*(;|$)/i,s=/^(inline-)?flex$/i},{"../read":15}],17:[function(e,t,r){function l(e){o(e);var t=e.element.style,r="inline"===e.mainAxis?["main","cross"]:["cross","main"];t.boxSizing="content-box",t.display="block",t.position="relative",t.width=n(e.flexStyle[r[0]]-e.flexStyle[r[0]+"InnerBefore"]-e.flexStyle[r[0]+"InnerAfter"]-e.flexStyle[r[0]+"BorderBefore"]-e.flexStyle[r[0]+"BorderAfter"]),t.height=n(e.flexStyle[r[1]]-e.flexStyle[r[1]+"InnerBefore"]-e.flexStyle[r[1]+"InnerAfter"]-e.flexStyle[r[1]+"BorderBefore"]-e.flexStyle[r[1]+"BorderAfter"]);for(var l,f=-1;l=e.children[++f];){var i=l.element.style,s="inline"===l.mainAxis?["main","cross"]:["cross","main"];i.boxSizing="content-box",i.display="block",i.position="absolute","auto"!==l.flexStyle[s[0]]&&(i.width=n(l.flexStyle[s[0]]-l.flexStyle[s[0]+"InnerBefore"]-l.flexStyle[s[0]+"InnerAfter"]-l.flexStyle[s[0]+"BorderBefore"]-l.flexStyle[s[0]+"BorderAfter"])),"auto"!==l.flexStyle[s[1]]&&(i.height=n(l.flexStyle[s[1]]-l.flexStyle[s[1]+"InnerBefore"]-l.flexStyle[s[1]+"InnerAfter"]-l.flexStyle[s[1]+"BorderBefore"]-l.flexStyle[s[1]+"BorderAfter"])),i.top=n(l.flexStyle[s[1]+"Start"]),i.left=n(l.flexStyle[s[0]+"Start"]),i.marginTop=n(l.flexStyle[s[1]+"Before"]),i.marginRight=n(l.flexStyle[s[0]+"After"]),i.marginBottom=n(l.flexStyle[s[1]+"After"]),i.marginLeft=n(l.flexStyle[s[0]+"Before"])}}function n(e){return"string"==typeof e?e:Math.max(e,0)+"px"}t.exports=l;var o=e("../flexbox")},{"../flexbox":7}],18:[function(e,t,r){function l(e){for(var t,r=-1;t=e[++r];)n(t)}t.exports=l;var n=e("../write")},{"../write":17}]},{},[13])(13)});
/*!
 * Masonry PACKAGED v4.2.2
 * Cascading grid layout library
 * https://masonry.desandro.com
 * MIT License
 * by David DeSandro
 */

/**
 * Bridget makes jQuery widgets
 * v2.0.1
 * MIT license
 */

/* jshint browser: true, strict: true, undef: true, unused: true */

( function( window, factory ) {
 
    // browser global
    window.jQueryBridget = factory(
      window,
      window.jQuery
    );
  

}( window, function factory( window, jQuery ) {
'use strict';

// ----- utils ----- //

var arraySlice = Array.prototype.slice;

// helper function for logging errors
// $.error breaks jQuery chaining
var console = window.console;
var logError = typeof console == 'undefined' ? function() {} :
  function( message ) {
    console.error( message );
  };

// ----- jQueryBridget ----- //

function jQueryBridget( namespace, PluginClass, $ ) {
  $ = $ || jQuery || window.jQuery;
  if ( !$ ) {
    return;
  }

  // add option method -> $().plugin('option', {...})
  if ( !PluginClass.prototype.option ) {
    // option setter
    PluginClass.prototype.option = function( opts ) {
      // bail out if not an object
      if ( !$.isPlainObject( opts ) ){
        return;
      }
      this.options = $.extend( true, this.options, opts );
    };
  }

  // make jQuery plugin
  $.fn[ namespace ] = function( arg0 /*, arg1 */ ) {
    if ( typeof arg0 == 'string' ) {
      // method call $().plugin( 'methodName', { options } )
      // shift arguments by 1
      var args = arraySlice.call( arguments, 1 );
      return methodCall( this, arg0, args );
    }
    // just $().plugin({ options })
    plainCall( this, arg0 );
    return this;
  };

  // $().plugin('methodName')
  function methodCall( $elems, methodName, args ) {
    var returnValue;
    var pluginMethodStr = '$().' + namespace + '("' + methodName + '")';

    $elems.each( function( i, elem ) {
      // get instance
      var instance = $.data( elem, namespace );
      if ( !instance ) {
        logError( namespace + ' not initialized. Cannot call methods, i.e. ' +
          pluginMethodStr );
        return;
      }

      var method = instance[ methodName ];
      if ( !method || methodName.charAt(0) == '_' ) {
        logError( pluginMethodStr + ' is not a valid method' );
        return;
      }

      // apply method, get return value
      var value = method.apply( instance, args );
      // set return value if value is returned, use only first value
      returnValue = returnValue === undefined ? value : returnValue;
    });

    return returnValue !== undefined ? returnValue : $elems;
  }

  function plainCall( $elems, options ) {
    $elems.each( function( i, elem ) {
      var instance = $.data( elem, namespace );
      if ( instance ) {
        // set options & init
        instance.option( options );
        instance._init();
      } else {
        // initialize new instance
        instance = new PluginClass( elem, options );
        $.data( elem, namespace, instance );
      }
    });
  }

  updateJQuery( $ );

}

// ----- updateJQuery ----- //

// set $.bridget for v1 backwards compatibility
function updateJQuery( $ ) {
  if ( !$ || ( $ && $.bridget ) ) {
    return;
  }
  $.bridget = jQueryBridget;
}

updateJQuery( jQuery || window.jQuery );

// -----  ----- //

return jQueryBridget;

}));

/**
 * EvEmitter v1.1.0
 * Lil' event emitter
 * MIT License
 */

/* jshint unused: true, undef: true, strict: true */

( function( global, factory ) {
    global.EvEmitter = factory();
}( typeof window != 'undefined' ? window : this, function() {



function EvEmitter() {}

var proto = EvEmitter.prototype;

proto.on = function( eventName, listener ) {
  if ( !eventName || !listener ) {
    return;
  }
  // set events hash
  var events = this._events = this._events || {};
  // set listeners array
  var listeners = events[ eventName ] = events[ eventName ] || [];
  // only add once
  if ( listeners.indexOf( listener ) == -1 ) {
    listeners.push( listener );
  }

  return this;
};

proto.once = function( eventName, listener ) {
  if ( !eventName || !listener ) {
    return;
  }
  // add event
  this.on( eventName, listener );
  // set once flag
  // set onceEvents hash
  var onceEvents = this._onceEvents = this._onceEvents || {};
  // set onceListeners object
  var onceListeners = onceEvents[ eventName ] = onceEvents[ eventName ] || {};
  // set flag
  onceListeners[ listener ] = true;

  return this;
};

proto.off = function( eventName, listener ) {
  var listeners = this._events && this._events[ eventName ];
  if ( !listeners || !listeners.length ) {
    return;
  }
  var index = listeners.indexOf( listener );
  if ( index != -1 ) {
    listeners.splice( index, 1 );
  }

  return this;
};

proto.emitEvent = function( eventName, args ) {
  var listeners = this._events && this._events[ eventName ];
  if ( !listeners || !listeners.length ) {
    return;
  }
  // copy over to avoid interference if .off() in listener
  listeners = listeners.slice(0);
  args = args || [];
  // once stuff
  var onceListeners = this._onceEvents && this._onceEvents[ eventName ];

  for ( var i=0; i < listeners.length; i++ ) {
    var listener = listeners[i]
    var isOnce = onceListeners && onceListeners[ listener ];
    if ( isOnce ) {
      // remove listener
      // remove before trigger to prevent recursion
      this.off( eventName, listener );
      // unset once flag
      delete onceListeners[ listener ];
    }
    // trigger listener
    listener.apply( this, args );
  }

  return this;
};

proto.allOff = function() {
  delete this._events;
  delete this._onceEvents;
};

return EvEmitter;

}));

/*!
 * getSize v2.0.3
 * measure size of elements
 * MIT license
 */

/* jshint browser: true, strict: true, undef: true, unused: true */
/* globals console: false */

( function( window, factory ) {
    // browser global
    window.getSize = factory();
  
})( window, function factory() {
'use strict';

// -------------------------- helpers -------------------------- //

// get a number from a string, not a percentage
function getStyleSize( value ) {
  var num = parseFloat( value );
  // not a percent like '100%', and a number
  var isValid = value.indexOf('%') == -1 && !isNaN( num );
  return isValid && num;
}

function noop() {}

var logError = typeof console == 'undefined' ? noop :
  function( message ) {
    console.error( message );
  };

// -------------------------- measurements -------------------------- //

var measurements = [
  'paddingLeft',
  'paddingRight',
  'paddingTop',
  'paddingBottom',
  'marginLeft',
  'marginRight',
  'marginTop',
  'marginBottom',
  'borderLeftWidth',
  'borderRightWidth',
  'borderTopWidth',
  'borderBottomWidth'
];

var measurementsLength = measurements.length;

function getZeroSize() {
  var size = {
    width: 0,
    height: 0,
    innerWidth: 0,
    innerHeight: 0,
    outerWidth: 0,
    outerHeight: 0
  };
  for ( var i=0; i < measurementsLength; i++ ) {
    var measurement = measurements[i];
    size[ measurement ] = 0;
  }
  return size;
}

// -------------------------- getStyle -------------------------- //

/**
 * getStyle, get style of element, check for Firefox bug
 * https://bugzilla.mozilla.org/show_bug.cgi?id=548397
 */
function getStyle( elem ) {
  var style = getComputedStyle( elem );
  if ( !style ) {
    logError( 'Style returned ' + style +
      '. Are you running this code in a hidden iframe on Firefox? ' +
      'See https://bit.ly/getsizebug1' );
  }
  return style;
}

// -------------------------- setup -------------------------- //

var isSetup = false;

var isBoxSizeOuter;

/**
 * setup
 * check isBoxSizerOuter
 * do on first getSize() rather than on page load for Firefox bug
 */
function setup() {
  // setup once
  if ( isSetup ) {
    return;
  }
  isSetup = true;

  // -------------------------- box sizing -------------------------- //

  /**
   * Chrome & Safari measure the outer-width on style.width on border-box elems
   * IE11 & Firefox<29 measures the inner-width
   */
  var div = document.createElement('div');
  div.style.width = '200px';
  div.style.padding = '1px 2px 3px 4px';
  div.style.borderStyle = 'solid';
  div.style.borderWidth = '1px 2px 3px 4px';
  div.style.boxSizing = 'border-box';

  var body = document.body || document.documentElement;
  body.appendChild( div );
  var style = getStyle( div );
  // round value for browser zoom. desandro/masonry#928
  isBoxSizeOuter = Math.round( getStyleSize( style.width ) ) == 200;
  getSize.isBoxSizeOuter = isBoxSizeOuter;

  body.removeChild( div );
}

// -------------------------- getSize -------------------------- //

function getSize( elem ) {
  setup();

  // use querySeletor if elem is string
  if ( typeof elem == 'string' ) {
    elem = document.querySelector( elem );
  }

  // do not proceed on non-objects
  if ( !elem || typeof elem != 'object' || !elem.nodeType ) {
    return;
  }

  var style = getStyle( elem );

  // if hidden, everything is 0
  if ( style.display == 'none' ) {
    return getZeroSize();
  }

  var size = {};
  size.width = elem.offsetWidth;
  size.height = elem.offsetHeight;

  var isBorderBox = size.isBorderBox = style.boxSizing == 'border-box';

  // get all measurements
  for ( var i=0; i < measurementsLength; i++ ) {
    var measurement = measurements[i];
    var value = style[ measurement ];
    var num = parseFloat( value );
    // any 'auto', 'medium' value will be 0
    size[ measurement ] = !isNaN( num ) ? num : 0;
  }

  var paddingWidth = size.paddingLeft + size.paddingRight;
  var paddingHeight = size.paddingTop + size.paddingBottom;
  var marginWidth = size.marginLeft + size.marginRight;
  var marginHeight = size.marginTop + size.marginBottom;
  var borderWidth = size.borderLeftWidth + size.borderRightWidth;
  var borderHeight = size.borderTopWidth + size.borderBottomWidth;

  var isBorderBoxSizeOuter = isBorderBox && isBoxSizeOuter;

  // overwrite width and height if we can get it from style
  var styleWidth = getStyleSize( style.width );
  if ( styleWidth !== false ) {
    size.width = styleWidth +
      // add padding and border unless it's already including it
      ( isBorderBoxSizeOuter ? 0 : paddingWidth + borderWidth );
  }

  var styleHeight = getStyleSize( style.height );
  if ( styleHeight !== false ) {
    size.height = styleHeight +
      // add padding and border unless it's already including it
      ( isBorderBoxSizeOuter ? 0 : paddingHeight + borderHeight );
  }

  size.innerWidth = size.width - ( paddingWidth + borderWidth );
  size.innerHeight = size.height - ( paddingHeight + borderHeight );

  size.outerWidth = size.width + marginWidth;
  size.outerHeight = size.height + marginHeight;

  return size;
}

return getSize;

});

/**
 * matchesSelector v2.0.2
 * matchesSelector( element, '.selector' )
 * MIT license
 */

/*jshint browser: true, strict: true, undef: true, unused: true */

( function( window, factory ) {
    // browser global
    window.matchesSelector = factory();
  
}( window, function factory() {
  'use strict';

  var matchesMethod = ( function() {
    var ElemProto = window.Element.prototype;
    // check for the standard method name first
    if ( ElemProto.matches ) {
      return 'matches';
    }
    // check un-prefixed
    if ( ElemProto.matchesSelector ) {
      return 'matchesSelector';
    }
    // check vendor prefixes
    var prefixes = [ 'webkit', 'moz', 'ms', 'o' ];

    for ( var i=0; i < prefixes.length; i++ ) {
      var prefix = prefixes[i];
      var method = prefix + 'MatchesSelector';
      if ( ElemProto[ method ] ) {
        return method;
      }
    }
  })();

  return function matchesSelector( elem, selector ) {
    return elem[ matchesMethod ]( selector );
  };

}));

/**
 * Fizzy UI utils v2.0.7
 * MIT license
 */

/*jshint browser: true, undef: true, unused: true, strict: true */

( function( window, factory ) {
    // browser global
    window.fizzyUIUtils = factory(
      window,
      window.matchesSelector
    );
}( window, function factory( window, matchesSelector ) {



var utils = {};

// ----- extend ----- //

// extends objects
utils.extend = function( a, b ) {
  for ( var prop in b ) {
    a[ prop ] = b[ prop ];
  }
  return a;
};

// ----- modulo ----- //

utils.modulo = function( num, div ) {
  return ( ( num % div ) + div ) % div;
};

// ----- makeArray ----- //

var arraySlice = Array.prototype.slice;

// turn element or nodeList into an array
utils.makeArray = function( obj ) {
  if ( Array.isArray( obj ) ) {
    // use object if already an array
    return obj;
  }
  // return empty array if undefined or null. #6
  if ( obj === null || obj === undefined ) {
    return [];
  }

  var isArrayLike = typeof obj == 'object' && typeof obj.length == 'number';
  if ( isArrayLike ) {
    // convert nodeList to array
    return arraySlice.call( obj );
  }

  // array of single index
  return [ obj ];
};

// ----- removeFrom ----- //

utils.removeFrom = function( ary, obj ) {
  var index = ary.indexOf( obj );
  if ( index != -1 ) {
    ary.splice( index, 1 );
  }
};

// ----- getParent ----- //

utils.getParent = function( elem, selector ) {
  while ( elem.parentNode && elem != document.body ) {
    elem = elem.parentNode;
    if ( matchesSelector( elem, selector ) ) {
      return elem;
    }
  }
};

// ----- getQueryElement ----- //

// use element as selector string
utils.getQueryElement = function( elem ) {
  if ( typeof elem == 'string' ) {
    return document.querySelector( elem );
  }
  return elem;
};

// ----- handleEvent ----- //

// enable .ontype to trigger from .addEventListener( elem, 'type' )
utils.handleEvent = function( event ) {
  var method = 'on' + event.type;
  if ( this[ method ] ) {
    this[ method ]( event );
  }
};

// ----- filterFindElements ----- //

utils.filterFindElements = function( elems, selector ) {
  // make array of elems
  elems = utils.makeArray( elems );
  var ffElems = [];

  elems.forEach( function( elem ) {
    // check that elem is an actual element
    if ( !( elem instanceof HTMLElement ) ) {
      return;
    }
    // add elem if no selector
    if ( !selector ) {
      ffElems.push( elem );
      return;
    }
    // filter & find items if we have a selector
    // filter
    if ( matchesSelector( elem, selector ) ) {
      ffElems.push( elem );
    }
    // find children
    var childElems = elem.querySelectorAll( selector );
    // concat childElems to filterFound array
    for ( var i=0; i < childElems.length; i++ ) {
      ffElems.push( childElems[i] );
    }
  });

  return ffElems;
};

// ----- debounceMethod ----- //

utils.debounceMethod = function( _class, methodName, threshold ) {
  threshold = threshold || 100;
  // original method
  var method = _class.prototype[ methodName ];
  var timeoutName = methodName + 'Timeout';

  _class.prototype[ methodName ] = function() {
    var timeout = this[ timeoutName ];
    clearTimeout( timeout );

    var args = arguments;
    var _this = this;
    this[ timeoutName ] = setTimeout( function() {
      method.apply( _this, args );
      delete _this[ timeoutName ];
    }, threshold );
  };
};

// ----- docReady ----- //

utils.docReady = function( callback ) {
  var readyState = document.readyState;
  if ( readyState == 'complete' || readyState == 'interactive' ) {
    // do async to allow for other scripts to run. metafizzy/flickity#441
    setTimeout( callback );
  } else {
    document.addEventListener( 'DOMContentLoaded', callback );
  }
};

// ----- htmlInit ----- //

// http://jamesroberts.name/blog/2010/02/22/string-functions-for-javascript-trim-to-camel-case-to-dashed-and-to-underscore/
utils.toDashed = function( str ) {
  return str.replace( /(.)([A-Z])/g, function( match, $1, $2 ) {
    return $1 + '-' + $2;
  }).toLowerCase();
};

var console = window.console;
/**
 * allow user to initialize classes via [data-namespace] or .js-namespace class
 * htmlInit( Widget, 'widgetName' )
 * options are parsed from data-namespace-options
 */
utils.htmlInit = function( WidgetClass, namespace ) {
  utils.docReady( function() {
    var dashedNamespace = utils.toDashed( namespace );
    var dataAttr = 'data-' + dashedNamespace;
    var dataAttrElems = document.querySelectorAll( '[' + dataAttr + ']' );
    var jsDashElems = document.querySelectorAll( '.js-' + dashedNamespace );
    var elems = utils.makeArray( dataAttrElems )
      .concat( utils.makeArray( jsDashElems ) );
    var dataOptionsAttr = dataAttr + '-options';
    var jQuery = window.jQuery;

    elems.forEach( function( elem ) {
      var attr = elem.getAttribute( dataAttr ) ||
        elem.getAttribute( dataOptionsAttr );
      var options;
      try {
        options = attr && JSON.parse( attr );
      } catch ( error ) {
        // log error, do not initialize
        if ( console ) {
          console.error( 'Error parsing ' + dataAttr + ' on ' + elem.className +
          ': ' + error );
        }
        return;
      }
      // initialize
      var instance = new WidgetClass( elem, options );
      // make available via $().data('namespace')
      if ( jQuery ) {
        jQuery.data( elem, namespace, instance );
      }
    });

  });
};

// -----  ----- //

return utils;

}));

/**
 * Outlayer Item
 */

( function( window, factory ) {
    // browser global
    window.Outlayer = {};
    window.Outlayer.Item = factory(
      window.EvEmitter,
      window.getSize
    ); 
}( window, function factory( EvEmitter, getSize ) {
'use strict';

// ----- helpers ----- //

function isEmptyObj( obj ) {
  for ( var prop in obj ) {
    return false;
  }
  prop = null;
  return true;
}

// -------------------------- CSS3 support -------------------------- //


var docElemStyle = document.documentElement.style;

var transitionProperty = typeof docElemStyle.transition == 'string' ?
  'transition' : 'WebkitTransition';
var transformProperty = typeof docElemStyle.transform == 'string' ?
  'transform' : 'WebkitTransform';

var transitionEndEvent = {
  WebkitTransition: 'webkitTransitionEnd',
  transition: 'transitionend'
}[ transitionProperty ];

// cache all vendor properties that could have vendor prefix
var vendorProperties = {
  transform: transformProperty,
  transition: transitionProperty,
  transitionDuration: transitionProperty + 'Duration',
  transitionProperty: transitionProperty + 'Property',
  transitionDelay: transitionProperty + 'Delay'
};

// -------------------------- Item -------------------------- //

function Item( element, layout ) {
  if ( !element ) {
    return;
  }

  this.element = element;
  // parent layout class, i.e. Masonry, Isotope, or Packery
  this.layout = layout;
  this.position = {
    x: 0,
    y: 0
  };

  this._create();
}

// inherit EvEmitter
var proto = Item.prototype = Object.create( EvEmitter.prototype );
proto.constructor = Item;

proto._create = function() {
  // transition objects
  this._transn = {
    ingProperties: {},
    clean: {},
    onEnd: {}
  };

  this.css({
    position: 'absolute'
  });
};

// trigger specified handler for event type
proto.handleEvent = function( event ) {
  var method = 'on' + event.type;
  if ( this[ method ] ) {
    this[ method ]( event );
  }
};

proto.getSize = function() {
  this.size = getSize( this.element );
};

/**
 * apply CSS styles to element
 * @param {Object} style
 */
proto.css = function( style ) {
  var elemStyle = this.element.style;

  for ( var prop in style ) {
    // use vendor property if available
    var supportedProp = vendorProperties[ prop ] || prop;
    elemStyle[ supportedProp ] = style[ prop ];
  }
};

 // measure position, and sets it
proto.getPosition = function() {
  var style = getComputedStyle( this.element );
  var isOriginLeft = this.layout._getOption('originLeft');
  var isOriginTop = this.layout._getOption('originTop');
  var xValue = style[ isOriginLeft ? 'left' : 'right' ];
  var yValue = style[ isOriginTop ? 'top' : 'bottom' ];
  var x = parseFloat( xValue );
  var y = parseFloat( yValue );
  // convert percent to pixels
  var layoutSize = this.layout.size;
  if ( xValue.indexOf('%') != -1 ) {
    x = ( x / 100 ) * layoutSize.width;
  }
  if ( yValue.indexOf('%') != -1 ) {
    y = ( y / 100 ) * layoutSize.height;
  }
  // clean up 'auto' or other non-integer values
  x = isNaN( x ) ? 0 : x;
  y = isNaN( y ) ? 0 : y;
  // remove padding from measurement
  x -= isOriginLeft ? layoutSize.paddingLeft : layoutSize.paddingRight;
  y -= isOriginTop ? layoutSize.paddingTop : layoutSize.paddingBottom;

  this.position.x = x;
  this.position.y = y;
};

// set settled position, apply padding
proto.layoutPosition = function() {
  var layoutSize = this.layout.size;
  var style = {};
  var isOriginLeft = this.layout._getOption('originLeft');
  var isOriginTop = this.layout._getOption('originTop');

  // x
  var xPadding = isOriginLeft ? 'paddingLeft' : 'paddingRight';
  var xProperty = isOriginLeft ? 'left' : 'right';
  var xResetProperty = isOriginLeft ? 'right' : 'left';

  var x = this.position.x + layoutSize[ xPadding ];
  // set in percentage or pixels
  style[ xProperty ] = this.getXValue( x );
  // reset other property
  style[ xResetProperty ] = '';

  // y
  var yPadding = isOriginTop ? 'paddingTop' : 'paddingBottom';
  var yProperty = isOriginTop ? 'top' : 'bottom';
  var yResetProperty = isOriginTop ? 'bottom' : 'top';

  var y = this.position.y + layoutSize[ yPadding ];
  // set in percentage or pixels
  style[ yProperty ] = this.getYValue( y );
  // reset other property
  style[ yResetProperty ] = '';

  this.css( style );
  this.emitEvent( 'layout', [ this ] );
};

proto.getXValue = function( x ) {
  var isHorizontal = this.layout._getOption('horizontal');
  return this.layout.options.percentPosition && !isHorizontal ?
    ( ( x / this.layout.size.width ) * 100 ) + '%' : x + 'px';
};

proto.getYValue = function( y ) {
  var isHorizontal = this.layout._getOption('horizontal');
  return this.layout.options.percentPosition && isHorizontal ?
    ( ( y / this.layout.size.height ) * 100 ) + '%' : y + 'px';
};

proto._transitionTo = function( x, y ) {
  this.getPosition();
  // get current x & y from top/left
  var curX = this.position.x;
  var curY = this.position.y;

  var didNotMove = x == this.position.x && y == this.position.y;

  // save end position
  this.setPosition( x, y );

  // if did not move and not transitioning, just go to layout
  if ( didNotMove && !this.isTransitioning ) {
    this.layoutPosition();
    return;
  }

  var transX = x - curX;
  var transY = y - curY;
  var transitionStyle = {};
  transitionStyle.transform = this.getTranslate( transX, transY );

  this.transition({
    to: transitionStyle,
    onTransitionEnd: {
      transform: this.layoutPosition
    },
    isCleaning: true
  });
};

proto.getTranslate = function( x, y ) {
  // flip cooridinates if origin on right or bottom
  var isOriginLeft = this.layout._getOption('originLeft');
  var isOriginTop = this.layout._getOption('originTop');
  x = isOriginLeft ? x : -x;
  y = isOriginTop ? y : -y;
  return 'translate3d(' + x + 'px, ' + y + 'px, 0)';
};

// non transition + transform support
proto.goTo = function( x, y ) {
  this.setPosition( x, y );
  this.layoutPosition();
};

proto.moveTo = proto._transitionTo;

proto.setPosition = function( x, y ) {
  this.position.x = parseFloat( x );
  this.position.y = parseFloat( y );
};

// ----- transition ----- //

/**
 * @param {Object} style - CSS
 * @param {Function} onTransitionEnd
 */

// non transition, just trigger callback
proto._nonTransition = function( args ) {
  this.css( args.to );
  if ( args.isCleaning ) {
    this._removeStyles( args.to );
  }
  for ( var prop in args.onTransitionEnd ) {
    args.onTransitionEnd[ prop ].call( this );
  }
};

/**
 * proper transition
 * @param {Object} args - arguments
 *   @param {Object} to - style to transition to
 *   @param {Object} from - style to start transition from
 *   @param {Boolean} isCleaning - removes transition styles after transition
 *   @param {Function} onTransitionEnd - callback
 */
proto.transition = function( args ) {
  // redirect to nonTransition if no transition duration
  if ( !parseFloat( this.layout.options.transitionDuration ) ) {
    this._nonTransition( args );
    return;
  }

  var _transition = this._transn;
  // keep track of onTransitionEnd callback by css property
  for ( var prop in args.onTransitionEnd ) {
    _transition.onEnd[ prop ] = args.onTransitionEnd[ prop ];
  }
  // keep track of properties that are transitioning
  for ( prop in args.to ) {
    _transition.ingProperties[ prop ] = true;
    // keep track of properties to clean up when transition is done
    if ( args.isCleaning ) {
      _transition.clean[ prop ] = true;
    }
  }

  // set from styles
  if ( args.from ) {
    this.css( args.from );
    // force redraw. http://blog.alexmaccaw.com/css-transitions
    var h = this.element.offsetHeight;
    // hack for JSHint to hush about unused var
    h = null;
  }
  // enable transition
  this.enableTransition( args.to );
  // set styles that are transitioning
  this.css( args.to );

  this.isTransitioning = true;

};

// dash before all cap letters, including first for
// WebkitTransform => -webkit-transform
function toDashedAll( str ) {
  return str.replace( /([A-Z])/g, function( $1 ) {
    return '-' + $1.toLowerCase();
  });
}

var transitionProps = 'opacity,' + toDashedAll( transformProperty );

proto.enableTransition = function(/* style */) {
  // HACK changing transitionProperty during a transition
  // will cause transition to jump
  if ( this.isTransitioning ) {
    return;
  }

  // make `transition: foo, bar, baz` from style object
  // HACK un-comment this when enableTransition can work
  // while a transition is happening
  // var transitionValues = [];
  // for ( var prop in style ) {
  //   // dash-ify camelCased properties like WebkitTransition
  //   prop = vendorProperties[ prop ] || prop;
  //   transitionValues.push( toDashedAll( prop ) );
  // }
  // munge number to millisecond, to match stagger
  var duration = this.layout.options.transitionDuration;
  duration = typeof duration == 'number' ? duration + 'ms' : duration;
  // enable transition styles
  this.css({
    transitionProperty: transitionProps,
    transitionDuration: duration,
    transitionDelay: this.staggerDelay || 0
  });
  // listen for transition end event
  this.element.addEventListener( transitionEndEvent, this, false );
};

// ----- events ----- //

proto.onwebkitTransitionEnd = function( event ) {
  this.ontransitionend( event );
};

proto.onotransitionend = function( event ) {
  this.ontransitionend( event );
};

// properties that I munge to make my life easier
var dashedVendorProperties = {
  '-webkit-transform': 'transform'
};

proto.ontransitionend = function( event ) {
  // disregard bubbled events from children
  if ( event.target !== this.element ) {
    return;
  }
  var _transition = this._transn;
  // get property name of transitioned property, convert to prefix-free
  var propertyName = dashedVendorProperties[ event.propertyName ] || event.propertyName;

  // remove property that has completed transitioning
  delete _transition.ingProperties[ propertyName ];
  // check if any properties are still transitioning
  if ( isEmptyObj( _transition.ingProperties ) ) {
    // all properties have completed transitioning
    this.disableTransition();
  }
  // clean style
  if ( propertyName in _transition.clean ) {
    // clean up style
    this.element.style[ event.propertyName ] = '';
    delete _transition.clean[ propertyName ];
  }
  // trigger onTransitionEnd callback
  if ( propertyName in _transition.onEnd ) {
    var onTransitionEnd = _transition.onEnd[ propertyName ];
    onTransitionEnd.call( this );
    delete _transition.onEnd[ propertyName ];
  }

  this.emitEvent( 'transitionEnd', [ this ] );
};

proto.disableTransition = function() {
  this.removeTransitionStyles();
  this.element.removeEventListener( transitionEndEvent, this, false );
  this.isTransitioning = false;
};

/**
 * removes style property from element
 * @param {Object} style
**/
proto._removeStyles = function( style ) {
  // clean up transition styles
  var cleanStyle = {};
  for ( var prop in style ) {
    cleanStyle[ prop ] = '';
  }
  this.css( cleanStyle );
};

var cleanTransitionStyle = {
  transitionProperty: '',
  transitionDuration: '',
  transitionDelay: ''
};

proto.removeTransitionStyles = function() {
  // remove transition
  this.css( cleanTransitionStyle );
};

// ----- stagger ----- //

proto.stagger = function( delay ) {
  delay = isNaN( delay ) ? 0 : delay;
  this.staggerDelay = delay + 'ms';
};

// ----- show/hide/remove ----- //

// remove element from DOM
proto.removeElem = function() {
  this.element.parentNode.removeChild( this.element );
  // remove display: none
  this.css({ display: '' });
  this.emitEvent( 'remove', [ this ] );
};

proto.remove = function() {
  // just remove element if no transition support or no transition
  if ( !transitionProperty || !parseFloat( this.layout.options.transitionDuration ) ) {
    this.removeElem();
    return;
  }

  // start transition
  this.once( 'transitionEnd', function() {
    this.removeElem();
  });
  this.hide();
};

proto.reveal = function() {
  delete this.isHidden;
  // remove display: none
  this.css({ display: '' });

  var options = this.layout.options;

  var onTransitionEnd = {};
  var transitionEndProperty = this.getHideRevealTransitionEndProperty('visibleStyle');
  onTransitionEnd[ transitionEndProperty ] = this.onRevealTransitionEnd;

  this.transition({
    from: options.hiddenStyle,
    to: options.visibleStyle,
    isCleaning: true,
    onTransitionEnd: onTransitionEnd
  });
};

proto.onRevealTransitionEnd = function() {
  // check if still visible
  // during transition, item may have been hidden
  if ( !this.isHidden ) {
    this.emitEvent('reveal');
  }
};

/**
 * get style property use for hide/reveal transition end
 * @param {String} styleProperty - hiddenStyle/visibleStyle
 * @returns {String}
 */
proto.getHideRevealTransitionEndProperty = function( styleProperty ) {
  var optionStyle = this.layout.options[ styleProperty ];
  // use opacity
  if ( optionStyle.opacity ) {
    return 'opacity';
  }
  // get first property
  for ( var prop in optionStyle ) {
    return prop;
  }
};

proto.hide = function() {
  // set flag
  this.isHidden = true;
  // remove display: none
  this.css({ display: '' });

  var options = this.layout.options;

  var onTransitionEnd = {};
  var transitionEndProperty = this.getHideRevealTransitionEndProperty('hiddenStyle');
  onTransitionEnd[ transitionEndProperty ] = this.onHideTransitionEnd;

  this.transition({
    from: options.visibleStyle,
    to: options.hiddenStyle,
    // keep hidden stuff hidden
    isCleaning: true,
    onTransitionEnd: onTransitionEnd
  });
};

proto.onHideTransitionEnd = function() {
  // check if still hidden
  // during transition, item may have been un-hidden
  if ( this.isHidden ) {
    this.css({ display: 'none' });
    this.emitEvent('hide');
  }
};

proto.destroy = function() {
  this.css({
    position: '',
    left: '',
    right: '',
    top: '',
    bottom: '',
    transition: '',
    transform: ''
  });
};

return Item;

}));

/*!
 * Outlayer v2.1.1
 * the brains and guts of a layout library
 * MIT license
 */

( function( window, factory ) {
  'use strict';
  
    // browser global
    window.Outlayer = factory(
      window,
      window.EvEmitter,
      window.getSize,
      window.fizzyUIUtils,
      window.Outlayer.Item
    );

}( window, function factory( window, EvEmitter, getSize, utils, Item ) {
'use strict';

// ----- vars ----- //

var console = window.console;
var jQuery = window.jQuery;
var noop = function() {};

// -------------------------- Outlayer -------------------------- //

// globally unique identifiers
var GUID = 0;
// internal store of all Outlayer intances
var instances = {};


/**
 * @param {Element, String} element
 * @param {Object} options
 * @constructor
 */
function Outlayer( element, options ) {
  var queryElement = utils.getQueryElement( element );
  if ( !queryElement ) {
    if ( console ) {
      console.error( 'Bad element for ' + this.constructor.namespace +
        ': ' + ( queryElement || element ) );
    }
    return;
  }
  this.element = queryElement;
  // add jQuery
  if ( jQuery ) {
    this.$element = jQuery( this.element );
  }

  // options
  this.options = utils.extend( {}, this.constructor.defaults );
  this.option( options );

  // add id for Outlayer.getFromElement
  var id = ++GUID;
  this.element.outlayerGUID = id; // expando
  instances[ id ] = this; // associate via id

  // kick it off
  this._create();

  var isInitLayout = this._getOption('initLayout');
  if ( isInitLayout ) {
    this.layout();
  }
}

// settings are for internal use only
Outlayer.namespace = 'outlayer';
Outlayer.Item = Item;

// default options
Outlayer.defaults = {
  containerStyle: {
    position: 'relative'
  },
  initLayout: true,
  originLeft: true,
  originTop: true,
  resize: true,
  resizeContainer: true,
  // item options
  transitionDuration: '0.4s',
  hiddenStyle: {
    opacity: 0,
    transform: 'scale(0.001)'
  },
  visibleStyle: {
    opacity: 1,
    transform: 'scale(1)'
  }
};

var proto = Outlayer.prototype;
// inherit EvEmitter
utils.extend( proto, EvEmitter.prototype );

/**
 * set options
 * @param {Object} opts
 */
proto.option = function( opts ) {
  utils.extend( this.options, opts );
};

/**
 * get backwards compatible option value, check old name
 */
proto._getOption = function( option ) {
  var oldOption = this.constructor.compatOptions[ option ];
  return oldOption && this.options[ oldOption ] !== undefined ?
    this.options[ oldOption ] : this.options[ option ];
};

Outlayer.compatOptions = {
  // currentName: oldName
  initLayout: 'isInitLayout',
  horizontal: 'isHorizontal',
  layoutInstant: 'isLayoutInstant',
  originLeft: 'isOriginLeft',
  originTop: 'isOriginTop',
  resize: 'isResizeBound',
  resizeContainer: 'isResizingContainer'
};

proto._create = function() {
  // get items from children
  this.reloadItems();
  // elements that affect layout, but are not laid out
  this.stamps = [];
  this.stamp( this.options.stamp );
  // set container style
  utils.extend( this.element.style, this.options.containerStyle );

  // bind resize method
  var canBindResize = this._getOption('resize');
  if ( canBindResize ) {
    this.bindResize();
  }
};

// goes through all children again and gets bricks in proper order
proto.reloadItems = function() {
  // collection of item elements
  this.items = this._itemize( this.element.children );
};


/**
 * turn elements into Outlayer.Items to be used in layout
 * @param {Array or NodeList or HTMLElement} elems
 * @returns {Array} items - collection of new Outlayer Items
 */
proto._itemize = function( elems ) {

  var itemElems = this._filterFindItemElements( elems );
  var Item = this.constructor.Item;

  // create new Outlayer Items for collection
  var items = [];
  for ( var i=0; i < itemElems.length; i++ ) {
    var elem = itemElems[i];
    var item = new Item( elem, this );
    items.push( item );
  }

  return items;
};

/**
 * get item elements to be used in layout
 * @param {Array or NodeList or HTMLElement} elems
 * @returns {Array} items - item elements
 */
proto._filterFindItemElements = function( elems ) {
  return utils.filterFindElements( elems, this.options.itemSelector );
};

/**
 * getter method for getting item elements
 * @returns {Array} elems - collection of item elements
 */
proto.getItemElements = function() {
  return this.items.map( function( item ) {
    return item.element;
  });
};

// ----- init & layout ----- //

/**
 * lays out all items
 */
proto.layout = function() {
  this._resetLayout();
  this._manageStamps();

  // don't animate first layout
  var layoutInstant = this._getOption('layoutInstant');
  var isInstant = layoutInstant !== undefined ?
    layoutInstant : !this._isLayoutInited;
  this.layoutItems( this.items, isInstant );

  // flag for initalized
  this._isLayoutInited = true;
};

// _init is alias for layout
proto._init = proto.layout;

/**
 * logic before any new layout
 */
proto._resetLayout = function() {
  this.getSize();
};


proto.getSize = function() {
  this.size = getSize( this.element );
};

/**
 * get measurement from option, for columnWidth, rowHeight, gutter
 * if option is String -> get element from selector string, & get size of element
 * if option is Element -> get size of element
 * else use option as a number
 *
 * @param {String} measurement
 * @param {String} size - width or height
 * @private
 */
proto._getMeasurement = function( measurement, size ) {
  var option = this.options[ measurement ];
  var elem;
  if ( !option ) {
    // default to 0
    this[ measurement ] = 0;
  } else {
    // use option as an element
    if ( typeof option == 'string' ) {
      elem = this.element.querySelector( option );
    } else if ( option instanceof HTMLElement ) {
      elem = option;
    }
    // use size of element, if element
    this[ measurement ] = elem ? getSize( elem )[ size ] : option;
  }
};

/**
 * layout a collection of item elements
 * @api public
 */
proto.layoutItems = function( items, isInstant ) {
  items = this._getItemsForLayout( items );

  this._layoutItems( items, isInstant );

  this._postLayout();
};

/**
 * get the items to be laid out
 * you may want to skip over some items
 * @param {Array} items
 * @returns {Array} items
 */
proto._getItemsForLayout = function( items ) {
  return items.filter( function( item ) {
    return !item.isIgnored;
  });
};

/**
 * layout items
 * @param {Array} items
 * @param {Boolean} isInstant
 */
proto._layoutItems = function( items, isInstant ) {
  this._emitCompleteOnItems( 'layout', items );

  if ( !items || !items.length ) {
    // no items, emit event with empty array
    return;
  }

  var queue = [];

  items.forEach( function( item ) {
    // get x/y object from method
    var position = this._getItemLayoutPosition( item );
    // enqueue
    position.item = item;
    position.isInstant = isInstant || item.isLayoutInstant;
    queue.push( position );
  }, this );

  this._processLayoutQueue( queue );
};

/**
 * get item layout position
 * @param {Outlayer.Item} item
 * @returns {Object} x and y position
 */
proto._getItemLayoutPosition = function( /* item */ ) {
  return {
    x: 0,
    y: 0
  };
};

/**
 * iterate over array and position each item
 * Reason being - separating this logic prevents 'layout invalidation'
 * thx @paul_irish
 * @param {Array} queue
 */
proto._processLayoutQueue = function( queue ) {
  this.updateStagger();
  queue.forEach( function( obj, i ) {
    this._positionItem( obj.item, obj.x, obj.y, obj.isInstant, i );
  }, this );
};

// set stagger from option in milliseconds number
proto.updateStagger = function() {
  var stagger = this.options.stagger;
  if ( stagger === null || stagger === undefined ) {
    this.stagger = 0;
    return;
  }
  this.stagger = getMilliseconds( stagger );
  return this.stagger;
};

/**
 * Sets position of item in DOM
 * @param {Outlayer.Item} item
 * @param {Number} x - horizontal position
 * @param {Number} y - vertical position
 * @param {Boolean} isInstant - disables transitions
 */
proto._positionItem = function( item, x, y, isInstant, i ) {
  if ( isInstant ) {
    // if not transition, just set CSS
    item.goTo( x, y );
  } else {
    item.stagger( i * this.stagger );
    item.moveTo( x, y );
  }
};

/**
 * Any logic you want to do after each layout,
 * i.e. size the container
 */
proto._postLayout = function() {
  this.resizeContainer();
};

proto.resizeContainer = function() {
  var isResizingContainer = this._getOption('resizeContainer');
  if ( !isResizingContainer ) {
    return;
  }
  var size = this._getContainerSize();
  if ( size ) {
    this._setContainerMeasure( size.width, true );
    this._setContainerMeasure( size.height, false );
  }
};

/**
 * Sets width or height of container if returned
 * @returns {Object} size
 *   @param {Number} width
 *   @param {Number} height
 */
proto._getContainerSize = noop;

/**
 * @param {Number} measure - size of width or height
 * @param {Boolean} isWidth
 */
proto._setContainerMeasure = function( measure, isWidth ) {
  if ( measure === undefined ) {
    return;
  }

  var elemSize = this.size;
  // add padding and border width if border box
  if ( elemSize.isBorderBox ) {
    measure += isWidth ? elemSize.paddingLeft + elemSize.paddingRight +
      elemSize.borderLeftWidth + elemSize.borderRightWidth :
      elemSize.paddingBottom + elemSize.paddingTop +
      elemSize.borderTopWidth + elemSize.borderBottomWidth;
  }

  measure = Math.max( measure, 0 );
  this.element.style[ isWidth ? 'width' : 'height' ] = measure + 'px';
};

/**
 * emit eventComplete on a collection of items events
 * @param {String} eventName
 * @param {Array} items - Outlayer.Items
 */
proto._emitCompleteOnItems = function( eventName, items ) {
  var _this = this;
  function onComplete() {
    _this.dispatchEvent( eventName + 'Complete', null, [ items ] );
  }

  var count = items.length;
  if ( !items || !count ) {
    onComplete();
    return;
  }

  var doneCount = 0;
  function tick() {
    doneCount++;
    if ( doneCount == count ) {
      onComplete();
    }
  }

  // bind callback
  items.forEach( function( item ) {
    item.once( eventName, tick );
  });
};

/**
 * emits events via EvEmitter and jQuery events
 * @param {String} type - name of event
 * @param {Event} event - original event
 * @param {Array} args - extra arguments
 */
proto.dispatchEvent = function( type, event, args ) {
  // add original event to arguments
  var emitArgs = event ? [ event ].concat( args ) : args;
  this.emitEvent( type, emitArgs );

  if ( jQuery ) {
    // set this.$element
    this.$element = this.$element || jQuery( this.element );
    if ( event ) {
      // create jQuery event
      var $event = jQuery.Event( event );
      $event.type = type;
      this.$element.trigger( $event, args );
    } else {
      // just trigger with type if no event available
      this.$element.trigger( type, args );
    }
  }
};

// -------------------------- ignore & stamps -------------------------- //


/**
 * keep item in collection, but do not lay it out
 * ignored items do not get skipped in layout
 * @param {Element} elem
 */
proto.ignore = function( elem ) {
  var item = this.getItem( elem );
  if ( item ) {
    item.isIgnored = true;
  }
};

/**
 * return item to layout collection
 * @param {Element} elem
 */
proto.unignore = function( elem ) {
  var item = this.getItem( elem );
  if ( item ) {
    delete item.isIgnored;
  }
};

/**
 * adds elements to stamps
 * @param {NodeList, Array, Element, or String} elems
 */
proto.stamp = function( elems ) {
  elems = this._find( elems );
  if ( !elems ) {
    return;
  }

  this.stamps = this.stamps.concat( elems );
  // ignore
  elems.forEach( this.ignore, this );
};

/**
 * removes elements to stamps
 * @param {NodeList, Array, or Element} elems
 */
proto.unstamp = function( elems ) {
  elems = this._find( elems );
  if ( !elems ){
    return;
  }

  elems.forEach( function( elem ) {
    // filter out removed stamp elements
    utils.removeFrom( this.stamps, elem );
    this.unignore( elem );
  }, this );
};

/**
 * finds child elements
 * @param {NodeList, Array, Element, or String} elems
 * @returns {Array} elems
 */
proto._find = function( elems ) {
  if ( !elems ) {
    return;
  }
  // if string, use argument as selector string
  if ( typeof elems == 'string' ) {
    elems = this.element.querySelectorAll( elems );
  }
  elems = utils.makeArray( elems );
  return elems;
};

proto._manageStamps = function() {
  if ( !this.stamps || !this.stamps.length ) {
    return;
  }

  this._getBoundingRect();

  this.stamps.forEach( this._manageStamp, this );
};

// update boundingLeft / Top
proto._getBoundingRect = function() {
  // get bounding rect for container element
  var boundingRect = this.element.getBoundingClientRect();
  var size = this.size;
  this._boundingRect = {
    left: boundingRect.left + size.paddingLeft + size.borderLeftWidth,
    top: boundingRect.top + size.paddingTop + size.borderTopWidth,
    right: boundingRect.right - ( size.paddingRight + size.borderRightWidth ),
    bottom: boundingRect.bottom - ( size.paddingBottom + size.borderBottomWidth )
  };
};

/**
 * @param {Element} stamp
**/
proto._manageStamp = noop;

/**
 * get x/y position of element relative to container element
 * @param {Element} elem
 * @returns {Object} offset - has left, top, right, bottom
 */
proto._getElementOffset = function( elem ) {
  var boundingRect = elem.getBoundingClientRect();
  var thisRect = this._boundingRect;
  var size = getSize( elem );
  var offset = {
    left: boundingRect.left - thisRect.left - size.marginLeft,
    top: boundingRect.top - thisRect.top - size.marginTop,
    right: thisRect.right - boundingRect.right - size.marginRight,
    bottom: thisRect.bottom - boundingRect.bottom - size.marginBottom
  };
  return offset;
};

// -------------------------- resize -------------------------- //

// enable event handlers for listeners
// i.e. resize -> onresize
proto.handleEvent = utils.handleEvent;

/**
 * Bind layout to window resizing
 */
proto.bindResize = function() {
  window.addEventListener( 'resize', this );
  this.isResizeBound = true;
};

/**
 * Unbind layout to window resizing
 */
proto.unbindResize = function() {
  window.removeEventListener( 'resize', this );
  this.isResizeBound = false;
};

proto.onresize = function() {
  this.resize();
};

utils.debounceMethod( Outlayer, 'onresize', 100 );

proto.resize = function() {
  // don't trigger if size did not change
  // or if resize was unbound. See #9
  if ( !this.isResizeBound || !this.needsResizeLayout() ) {
    return;
  }

  this.layout();
};

/**
 * check if layout is needed post layout
 * @returns Boolean
 */
proto.needsResizeLayout = function() {
  var size = getSize( this.element );
  // check that this.size and size are there
  // IE8 triggers resize on body size change, so they might not be
  var hasSizes = this.size && size;
  return hasSizes && size.innerWidth !== this.size.innerWidth;
};

// -------------------------- methods -------------------------- //

/**
 * add items to Outlayer instance
 * @param {Array or NodeList or Element} elems
 * @returns {Array} items - Outlayer.Items
**/
proto.addItems = function( elems ) {
  var items = this._itemize( elems );
  // add items to collection
  if ( items.length ) {
    this.items = this.items.concat( items );
  }
  return items;
};

/**
 * Layout newly-appended item elements
 * @param {Array or NodeList or Element} elems
 */
proto.appended = function( elems ) {
  var items = this.addItems( elems );
  if ( !items.length ) {
    return;
  }
  // layout and reveal just the new items
  this.layoutItems( items, true );
  this.reveal( items );
};

/**
 * Layout prepended elements
 * @param {Array or NodeList or Element} elems
 */
proto.prepended = function( elems ) {
  var items = this._itemize( elems );
  if ( !items.length ) {
    return;
  }
  // add items to beginning of collection
  var previousItems = this.items.slice(0);
  this.items = items.concat( previousItems );
  // start new layout
  this._resetLayout();
  this._manageStamps();
  // layout new stuff without transition
  this.layoutItems( items, true );
  this.reveal( items );
  // layout previous items
  this.layoutItems( previousItems );
};

/**
 * reveal a collection of items
 * @param {Array of Outlayer.Items} items
 */
proto.reveal = function( items ) {
  this._emitCompleteOnItems( 'reveal', items );
  if ( !items || !items.length ) {
    return;
  }
  var stagger = this.updateStagger();
  items.forEach( function( item, i ) {
    item.stagger( i * stagger );
    item.reveal();
  });
};

/**
 * hide a collection of items
 * @param {Array of Outlayer.Items} items
 */
proto.hide = function( items ) {
  this._emitCompleteOnItems( 'hide', items );
  if ( !items || !items.length ) {
    return;
  }
  var stagger = this.updateStagger();
  items.forEach( function( item, i ) {
    item.stagger( i * stagger );
    item.hide();
  });
};

/**
 * reveal item elements
 * @param {Array}, {Element}, {NodeList} items
 */
proto.revealItemElements = function( elems ) {
  var items = this.getItems( elems );
  this.reveal( items );
};

/**
 * hide item elements
 * @param {Array}, {Element}, {NodeList} items
 */
proto.hideItemElements = function( elems ) {
  var items = this.getItems( elems );
  this.hide( items );
};

/**
 * get Outlayer.Item, given an Element
 * @param {Element} elem
 * @param {Function} callback
 * @returns {Outlayer.Item} item
 */
proto.getItem = function( elem ) {
  // loop through items to get the one that matches
  for ( var i=0; i < this.items.length; i++ ) {
    var item = this.items[i];
    if ( item.element == elem ) {
      // return item
      return item;
    }
  }
};

/**
 * get collection of Outlayer.Items, given Elements
 * @param {Array} elems
 * @returns {Array} items - Outlayer.Items
 */
proto.getItems = function( elems ) {
  elems = utils.makeArray( elems );
  var items = [];
  elems.forEach( function( elem ) {
    var item = this.getItem( elem );
    if ( item ) {
      items.push( item );
    }
  }, this );

  return items;
};

/**
 * remove element(s) from instance and DOM
 * @param {Array or NodeList or Element} elems
 */
proto.remove = function( elems ) {
  var removeItems = this.getItems( elems );

  this._emitCompleteOnItems( 'remove', removeItems );

  // bail if no items to remove
  if ( !removeItems || !removeItems.length ) {
    return;
  }

  removeItems.forEach( function( item ) {
    item.remove();
    // remove item from collection
    utils.removeFrom( this.items, item );
  }, this );
};

// ----- destroy ----- //

// remove and disable Outlayer instance
proto.destroy = function() {
  // clean up dynamic styles
  var style = this.element.style;
  style.height = '';
  style.position = '';
  style.width = '';
  // destroy items
  this.items.forEach( function( item ) {
    item.destroy();
  });

  this.unbindResize();

  var id = this.element.outlayerGUID;
  delete instances[ id ]; // remove reference to instance by id
  delete this.element.outlayerGUID;
  // remove data for jQuery
  if ( jQuery ) {
    jQuery.removeData( this.element, this.constructor.namespace );
  }

};

// -------------------------- data -------------------------- //

/**
 * get Outlayer instance from element
 * @param {Element} elem
 * @returns {Outlayer}
 */
Outlayer.data = function( elem ) {
  elem = utils.getQueryElement( elem );
  var id = elem && elem.outlayerGUID;
  return id && instances[ id ];
};


// -------------------------- create Outlayer class -------------------------- //

/**
 * create a layout class
 * @param {String} namespace
 */
Outlayer.create = function( namespace, options ) {
  // sub-class Outlayer
  var Layout = subclass( Outlayer );
  // apply new options and compatOptions
  Layout.defaults = utils.extend( {}, Outlayer.defaults );
  utils.extend( Layout.defaults, options );
  Layout.compatOptions = utils.extend( {}, Outlayer.compatOptions  );

  Layout.namespace = namespace;

  Layout.data = Outlayer.data;

  // sub-class Item
  Layout.Item = subclass( Item );

  // -------------------------- declarative -------------------------- //

  utils.htmlInit( Layout, namespace );

  // -------------------------- jQuery bridge -------------------------- //

  // make into jQuery plugin
  if ( jQuery && jQuery.bridget ) {
    jQuery.bridget( namespace, Layout );
  }

  return Layout;
};

function subclass( Parent ) {
  function SubClass() {
    Parent.apply( this, arguments );
  }

  SubClass.prototype = Object.create( Parent.prototype );
  SubClass.prototype.constructor = SubClass;

  return SubClass;
}

// ----- helpers ----- //

// how many milliseconds are in each unit
var msUnits = {
  ms: 1,
  s: 1000
};

// munge time-like parameter into millisecond number
// '0.4s' -> 40
function getMilliseconds( time ) {
  if ( typeof time == 'number' ) {
    return time;
  }
  var matches = time.match( /(^\d*\.?\d*)(\w*)/ );
  var num = matches && matches[1];
  var unit = matches && matches[2];
  if ( !num.length ) {
    return 0;
  }
  num = parseFloat( num );
  var mult = msUnits[ unit ] || 1;
  return num * mult;
}

// ----- fin ----- //

// back in global
Outlayer.Item = Item;

return Outlayer;

}));

/*!
 * Masonry v4.2.2
 * Cascading grid layout library
 * https://masonry.desandro.com
 * MIT License
 * by David DeSandro
 */

( function( window, factory ) {
    // browser global
    window.Masonry = factory(
      window.Outlayer,
      window.getSize
    );
}( window, function factory( Outlayer, getSize ) {



// -------------------------- masonryDefinition -------------------------- //

  // create an Outlayer layout class
  var Masonry = Outlayer.create('masonry');
  // isFitWidth -> fitWidth
  Masonry.compatOptions.fitWidth = 'isFitWidth';

  var proto = Masonry.prototype;

  proto._resetLayout = function() {
    this.getSize();
    this._getMeasurement( 'columnWidth', 'outerWidth' );
    this._getMeasurement( 'gutter', 'outerWidth' );
    this.measureColumns();

    // reset column Y
    this.colYs = [];
    for ( var i=0; i < this.cols; i++ ) {
      this.colYs.push( 0 );
    }

    this.maxY = 0;
    this.horizontalColIndex = 0;
  };

  proto.measureColumns = function() {
    this.getContainerWidth();
    // if columnWidth is 0, default to outerWidth of first item
    if ( !this.columnWidth ) {
      var firstItem = this.items[0];
      var firstItemElem = firstItem && firstItem.element;
      // columnWidth fall back to item of first element
      this.columnWidth = firstItemElem && getSize( firstItemElem ).outerWidth ||
        // if first elem has no width, default to size of container
        this.containerWidth;
    }

    var columnWidth = this.columnWidth += this.gutter;

    // calculate columns
    var containerWidth = this.containerWidth + this.gutter;
    var cols = containerWidth / columnWidth;
    // fix rounding errors, typically with gutters
    var excess = columnWidth - containerWidth % columnWidth;
    // if overshoot is less than a pixel, round up, otherwise floor it
    var mathMethod = excess && excess < 1 ? 'round' : 'floor';
    cols = Math[ mathMethod ]( cols );
    this.cols = Math.max( cols, 1 );
  };

  proto.getContainerWidth = function() {
    // container is parent if fit width
    var isFitWidth = this._getOption('fitWidth');
    var container = isFitWidth ? this.element.parentNode : this.element;
    // check that this.size and size are there
    // IE8 triggers resize on body size change, so they might not be
    var size = getSize( container );
    this.containerWidth = size && size.innerWidth;
  };

  proto._getItemLayoutPosition = function( item ) {
    item.getSize();
    // how many columns does this brick span
    var remainder = item.size.outerWidth % this.columnWidth;
    var mathMethod = remainder && remainder < 1 ? 'round' : 'ceil';
    // round if off by 1 pixel, otherwise use ceil
    var colSpan = Math[ mathMethod ]( item.size.outerWidth / this.columnWidth );
    colSpan = Math.min( colSpan, this.cols );
    // use horizontal or top column position
    var colPosMethod = this.options.horizontalOrder ?
      '_getHorizontalColPosition' : '_getTopColPosition';
    var colPosition = this[ colPosMethod ]( colSpan, item );
    // position the brick
    var position = {
      x: this.columnWidth * colPosition.col,
      y: colPosition.y
    };
    // apply setHeight to necessary columns
    var setHeight = colPosition.y + item.size.outerHeight;
    var setMax = colSpan + colPosition.col;
    for ( var i = colPosition.col; i < setMax; i++ ) {
      this.colYs[i] = setHeight;
    }

    return position;
  };

  proto._getTopColPosition = function( colSpan ) {
    var colGroup = this._getTopColGroup( colSpan );
    // get the minimum Y value from the columns
    var minimumY = Math.min.apply( Math, colGroup );

    return {
      col: colGroup.indexOf( minimumY ),
      y: minimumY,
    };
  };

  /**
   * @param {Number} colSpan - number of columns the element spans
   * @returns {Array} colGroup
   */
  proto._getTopColGroup = function( colSpan ) {
    if ( colSpan < 2 ) {
      // if brick spans only one column, use all the column Ys
      return this.colYs;
    }

    var colGroup = [];
    // how many different places could this brick fit horizontally
    var groupCount = this.cols + 1 - colSpan;
    // for each group potential horizontal position
    for ( var i = 0; i < groupCount; i++ ) {
      colGroup[i] = this._getColGroupY( i, colSpan );
    }
    return colGroup;
  };

  proto._getColGroupY = function( col, colSpan ) {
    if ( colSpan < 2 ) {
      return this.colYs[ col ];
    }
    // make an array of colY values for that one group
    var groupColYs = this.colYs.slice( col, col + colSpan );
    // and get the max value of the array
    return Math.max.apply( Math, groupColYs );
  };

  // get column position based on horizontal index. #873
  proto._getHorizontalColPosition = function( colSpan, item ) {
    var col = this.horizontalColIndex % this.cols;
    var isOver = colSpan > 1 && col + colSpan > this.cols;
    // shift to next row if item can't fit on current row
    col = isOver ? 0 : col;
    // don't let zero-size items take up space
    var hasSize = item.size.outerWidth && item.size.outerHeight;
    this.horizontalColIndex = hasSize ? col + colSpan : this.horizontalColIndex;

    return {
      col: col,
      y: this._getColGroupY( col, colSpan ),
    };
  };

  proto._manageStamp = function( stamp ) {
    var stampSize = getSize( stamp );
    var offset = this._getElementOffset( stamp );
    // get the columns that this stamp affects
    var isOriginLeft = this._getOption('originLeft');
    var firstX = isOriginLeft ? offset.left : offset.right;
    var lastX = firstX + stampSize.outerWidth;
    var firstCol = Math.floor( firstX / this.columnWidth );
    firstCol = Math.max( 0, firstCol );
    var lastCol = Math.floor( lastX / this.columnWidth );
    // lastCol should not go over if multiple of columnWidth #425
    lastCol -= lastX % this.columnWidth ? 0 : 1;
    lastCol = Math.min( this.cols - 1, lastCol );
    // set colYs to bottom of the stamp

    var isOriginTop = this._getOption('originTop');
    var stampMaxY = ( isOriginTop ? offset.top : offset.bottom ) +
      stampSize.outerHeight;
    for ( var i = firstCol; i <= lastCol; i++ ) {
      this.colYs[i] = Math.max( stampMaxY, this.colYs[i] );
    }
  };

  proto._getContainerSize = function() {
    this.maxY = Math.max.apply( Math, this.colYs );
    var size = {
      height: this.maxY
    };

    if ( this._getOption('fitWidth') ) {
      size.width = this._getContainerFitWidth();
    }

    return size;
  };

  proto._getContainerFitWidth = function() {
    var unusedCols = 0;
    // count unused columns
    var i = this.cols;
    while ( --i ) {
      if ( this.colYs[i] !== 0 ) {
        break;
      }
      unusedCols++;
    }
    // fit container to columns that have been used
    return ( this.cols - unusedCols ) * this.columnWidth - this.gutter;
  };

  proto.needsResizeLayout = function() {
    var previousWidth = this.containerWidth;
    this.getContainerWidth();
    return previousWidth != this.containerWidth;
  };

  return Masonry;

}));

/*! npm.im/object-fit-images 3.1.3 */
var objectFitImages = (function () {
    'use strict';

    var OFI = 'bfred-it:object-fit-images';
    var propRegex = /(object-fit|object-position)\s*:\s*([-\w\s%]+)/g;
    var testImg = typeof Image === 'undefined' ? {style: {'object-position': 1}} : new Image();
    var supportsObjectFit = 'object-fit' in testImg.style;
    var supportsObjectPosition = 'object-position' in testImg.style;
    var supportsOFI = 'background-size' in testImg.style;
    var supportsCurrentSrc = typeof testImg.currentSrc === 'string';
    var nativeGetAttribute = testImg.getAttribute;
    var nativeSetAttribute = testImg.setAttribute;
    var autoModeEnabled = false;

    function createPlaceholder(w, h) {
        return ("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='" + w + "' height='" + h + "'%3E%3C/svg%3E");
    }

    function polyfillCurrentSrc(el) {
        if (el.srcset && !supportsCurrentSrc && window.picturefill) {
            var pf = window.picturefill._;
            // parse srcset with picturefill where currentSrc isn't available
            if (!el[pf.ns] || !el[pf.ns].evaled) {
                // force synchronous srcset parsing
                pf.fillImg(el, {reselect: true});
            }

            if (!el[pf.ns].curSrc) {
                // force picturefill to parse srcset
                el[pf.ns].supported = false;
                pf.fillImg(el, {reselect: true});
            }

            // retrieve parsed currentSrc, if any
            el.currentSrc = el[pf.ns].curSrc || el.src;
        }
    }

    function getStyle(el) {
        var style = getComputedStyle(el).fontFamily;
        var parsed;
        var props = {};
        while ((parsed = propRegex.exec(style)) !== null) {
            props[parsed[1]] = parsed[2];
        }
        return props;
    }

    function setPlaceholder(img, width, height) {
        // Default: fill width, no height
        var placeholder = createPlaceholder(width || 1, height || 0);

        // Only set placeholder if it's different
        if (nativeGetAttribute.call(img, 'src') !== placeholder) {
            nativeSetAttribute.call(img, 'src', placeholder);
        }
    }

    function onImageReady(img, callback) {
        // naturalWidth is only available when the image headers are loaded,
        // this loop will poll it every 100ms.
        if (img.naturalWidth) {
            callback(img);
        } else {
            setTimeout(onImageReady, 100, img, callback);
        }
    }

    function fixOne(el) {
        var style = getStyle(el);
        var ofi = el[OFI];
        style['object-fit'] = style['object-fit'] || 'fill'; // default value

        // Avoid running where unnecessary, unless OFI had already done its deed
        if (!ofi.img) {
            // fill is the default behavior so no action is necessary
            if (style['object-fit'] === 'fill') {
                return;
            }

            // Where object-fit is supported and object-position isn't (Safari < 10)
            if (
                !ofi.skipTest && // unless user wants to apply regardless of browser support
                supportsObjectFit && // if browser already supports object-fit
                !style['object-position'] // unless object-position is used
            ) {
                return;
            }
        }

        // keep a clone in memory while resetting the original to a blank
        if (!ofi.img) {
            ofi.img = new Image(el.width, el.height);
            ofi.img.srcset = nativeGetAttribute.call(el, "data-ofi-srcset") || el.srcset;
            ofi.img.src = nativeGetAttribute.call(el, "data-ofi-src") || el.src;

            // preserve for any future cloneNode calls
            // https://github.com/bfred-it/object-fit-images/issues/53
            nativeSetAttribute.call(el, "data-ofi-src", el.src);
            if (el.srcset) {
                nativeSetAttribute.call(el, "data-ofi-srcset", el.srcset);
            }

            setPlaceholder(el, el.naturalWidth || el.width, el.naturalHeight || el.height);

            // remove srcset because it overrides src
            if (el.srcset) {
                el.srcset = '';
            }
            try {
                keepSrcUsable(el);
            } catch (err) {
                if (window.console) {
                    console.log('http://bit.ly/ofi-old-browser');
                }
            }
        }

        polyfillCurrentSrc(ofi.img);

        el.style.backgroundImage = "url(\"" + ((ofi.img.currentSrc || ofi.img.src).replace(/"/g, '\\"')) + "\")";
        el.style.backgroundPosition = style['object-position'] || 'center';
        el.style.backgroundRepeat = 'no-repeat';

        if (/scale-down/.test(style['object-fit'])) {
            onImageReady(ofi.img, function () {
                if (ofi.img.naturalWidth > el.width || ofi.img.naturalHeight > el.height) {
                    el.style.backgroundSize = 'contain';
                } else {
                    el.style.backgroundSize = 'auto';
                }
            });
        } else {
            el.style.backgroundSize = style['object-fit'].replace('none', 'auto').replace('fill', '100% 100%');
        }

        onImageReady(ofi.img, function (img) {
            setPlaceholder(el, img.naturalWidth, img.naturalHeight);
        });
    }

    function keepSrcUsable(el) {
        var descriptors = {
            get: function get(prop) {
                return el[OFI].img[prop ? prop : 'src'];
            },
            set: function set(value, prop) {
                el[OFI].img[prop ? prop : 'src'] = value;
                nativeSetAttribute.call(el, ("data-ofi-" + prop), value); // preserve for any future cloneNode
                fixOne(el);
                return value;
            }
        };
        Object.defineProperty(el, 'src', descriptors);
        Object.defineProperty(el, 'currentSrc', {
            get: function () { return descriptors.get('currentSrc'); }
        });
        Object.defineProperty(el, 'srcset', {
            get: function () { return descriptors.get('srcset'); },
            set: function (ss) { return descriptors.set(ss, 'srcset'); }
        });
    }

    function hijackAttributes() {
        function getOfiImageMaybe(el, name) {
            return el[OFI] && el[OFI].img && (name === 'src' || name === 'srcset') ? el[OFI].img : el;
        }
        if (!supportsObjectPosition) {
            HTMLImageElement.prototype.getAttribute = function (name) {
                return nativeGetAttribute.call(getOfiImageMaybe(this, name), name);
            };

            HTMLImageElement.prototype.setAttribute = function (name, value) {
                return nativeSetAttribute.call(getOfiImageMaybe(this, name), name, String(value));
            };
        }
    }

    function fix(imgs, opts) {
        var startAutoMode = !autoModeEnabled && !imgs;
        opts = opts || {};
        imgs = imgs || 'img';

        if ((supportsObjectPosition && !opts.skipTest) || !supportsOFI) {
            return false;
        }

        // use imgs as a selector or just select all images
        if (typeof imgs === 'string') {
            imgs = document.querySelectorAll(imgs);
        } else if (!('length' in imgs)) {
            imgs = [imgs];
        }

        // apply fix to all
        for (var i = 0; i < imgs.length; i++) {
            imgs[i][OFI] = imgs[i][OFI] || {
                    skipTest: opts.skipTest
                };
            fixOne(imgs[i]);
        }

        if (startAutoMode) {
            document.body.addEventListener('load', function (e) {
                if (e.target.tagName === 'IMG') {
                    fix(e.target, {
                        skipTest: opts.skipTest
                    });
                }
            }, true);
            autoModeEnabled = true;
            imgs = 'img'; // reset to a generic selector for watchMQ
        }

        // if requested, watch media queries for object-fit change
        if (opts.watchMQ) {
            window.addEventListener('resize', fix.bind(null, imgs, {
                skipTest: opts.skipTest
            }));
        }
    }

    fix.supportsObjectFit = supportsObjectFit;
    fix.supportsObjectPosition = supportsObjectPosition;

    hijackAttributes();

    return fix;

}());
!function(i){"use strict";i(jQuery);}(function(i){"use strict";var e=window.Slick||{};(e=function(){var e=0;return function(t,o){var s,n=this;n.defaults={accessibility:!0,adaptiveHeight:!1,appendArrows:i(t),appendDots:i(t),arrows:!0,asNavFor:null,prevArrow:'<button class="slick-prev" aria-label="Previous" type="button">Previous</button>',nextArrow:'<button class="slick-next" aria-label="Next" type="button">Next</button>',autoplay:!1,autoplaySpeed:3e3,centerMode:!1,centerPadding:"50px",cssEase:"ease",customPaging:function(e,t){return i('<button type="button" />').text(t+1)},dots:!1,dotsClass:"slick-dots",draggable:!0,easing:"linear",edgeFriction:.35,fade:!1,focusOnSelect:!1,focusOnChange:!1,infinite:!0,initialSlide:0,lazyLoad:"ondemand",mobileFirst:!1,pauseOnHover:!0,pauseOnFocus:!0,pauseOnDotsHover:!1,respondTo:"window",responsive:null,rows:1,rtl:!1,slide:"",slidesPerRow:1,slidesToShow:1,slidesToScroll:1,speed:500,swipe:!0,swipeToSlide:!1,touchMove:!0,touchThreshold:5,useCSS:!0,useTransform:!0,variableWidth:!1,vertical:!1,verticalSwiping:!1,waitForAnimate:!0,zIndex:1e3},n.initials={animating:!1,dragging:!1,autoPlayTimer:null,currentDirection:0,currentLeft:null,currentSlide:0,direction:1,$dots:null,listWidth:null,listHeight:null,loadIndex:0,$nextArrow:null,$prevArrow:null,scrolling:!1,slideCount:null,slideWidth:null,$slideTrack:null,$slides:null,sliding:!1,slideOffset:0,swipeLeft:null,swiping:!1,$list:null,touchObject:{},transformsEnabled:!1,unslicked:!1},i.extend(n,n.initials),n.activeBreakpoint=null,n.animType=null,n.animProp=null,n.breakpoints=[],n.breakpointSettings=[],n.cssTransitions=!1,n.focussed=!1,n.interrupted=!1,n.hidden="hidden",n.paused=!0,n.positionProp=null,n.respondTo=null,n.rowCount=1,n.shouldClick=!0,n.$slider=i(t),n.$slidesCache=null,n.transformType=null,n.transitionType=null,n.visibilityChange="visibilitychange",n.windowWidth=0,n.windowTimer=null,s=i(t).data("slick")||{},n.options=i.extend({},n.defaults,o,s),n.currentSlide=n.options.initialSlide,n.originalSettings=n.options,void 0!==document.mozHidden?(n.hidden="mozHidden",n.visibilityChange="mozvisibilitychange"):void 0!==document.webkitHidden&&(n.hidden="webkitHidden",n.visibilityChange="webkitvisibilitychange"),n.autoPlay=i.proxy(n.autoPlay,n),n.autoPlayClear=i.proxy(n.autoPlayClear,n),n.autoPlayIterator=i.proxy(n.autoPlayIterator,n),n.changeSlide=i.proxy(n.changeSlide,n),n.clickHandler=i.proxy(n.clickHandler,n),n.selectHandler=i.proxy(n.selectHandler,n),n.setPosition=i.proxy(n.setPosition,n),n.swipeHandler=i.proxy(n.swipeHandler,n),n.dragHandler=i.proxy(n.dragHandler,n),n.keyHandler=i.proxy(n.keyHandler,n),n.instanceUid=e++,n.htmlExpr=/^(?:\s*(<[\w\W]+>)[^>]*)$/,n.registerBreakpoints(),n.init(!0)}}()).prototype.activateADA=function(){this.$slideTrack.find(".slick-active").attr({"aria-hidden":"false"}).find("a, input, button, select").attr({tabindex:"0"})},e.prototype.addSlide=e.prototype.slickAdd=function(e,t,o){var s=this;if("boolean"==typeof t)o=t,t=null;else if(t<0||t>=s.slideCount)return!1;s.unload(),"number"==typeof t?0===t&&0===s.$slides.length?i(e).appendTo(s.$slideTrack):o?i(e).insertBefore(s.$slides.eq(t)):i(e).insertAfter(s.$slides.eq(t)):!0===o?i(e).prependTo(s.$slideTrack):i(e).appendTo(s.$slideTrack),s.$slides=s.$slideTrack.children(this.options.slide),s.$slideTrack.children(this.options.slide).detach(),s.$slideTrack.append(s.$slides),s.$slides.each(function(e,t){i(t).attr("data-slick-index",e)}),s.$slidesCache=s.$slides,s.reinit()},e.prototype.animateHeight=function(){var i=this;if(1===i.options.slidesToShow&&!0===i.options.adaptiveHeight&&!1===i.options.vertical){var e=i.$slides.eq(i.currentSlide).outerHeight(!0);i.$list.animate({height:e},i.options.speed)}},e.prototype.animateSlide=function(e,t){var o={},s=this;s.animateHeight(),!0===s.options.rtl&&!1===s.options.vertical&&(e=-e),!1===s.transformsEnabled?!1===s.options.vertical?s.$slideTrack.animate({left:e},s.options.speed,s.options.easing,t):s.$slideTrack.animate({top:e},s.options.speed,s.options.easing,t):!1===s.cssTransitions?(!0===s.options.rtl&&(s.currentLeft=-s.currentLeft),i({animStart:s.currentLeft}).animate({animStart:e},{duration:s.options.speed,easing:s.options.easing,step:function(i){i=Math.ceil(i),!1===s.options.vertical?(o[s.animType]="translate("+i+"px, 0px)",s.$slideTrack.css(o)):(o[s.animType]="translate(0px,"+i+"px)",s.$slideTrack.css(o))},complete:function(){t&&t.call()}})):(s.applyTransition(),e=Math.ceil(e),!1===s.options.vertical?o[s.animType]="translate3d("+e+"px, 0px, 0px)":o[s.animType]="translate3d(0px,"+e+"px, 0px)",s.$slideTrack.css(o),t&&setTimeout(function(){s.disableTransition(),t.call()},s.options.speed))},e.prototype.getNavTarget=function(){var e=this,t=e.options.asNavFor;return t&&null!==t&&(t=i(t).not(e.$slider)),t},e.prototype.asNavFor=function(e){var t=this.getNavTarget();null!==t&&"object"==typeof t&&t.each(function(){var t=i(this).slick("getSlick");t.unslicked||t.slideHandler(e,!0)})},e.prototype.applyTransition=function(i){var e=this,t={};!1===e.options.fade?t[e.transitionType]=e.transformType+" "+e.options.speed+"ms "+e.options.cssEase:t[e.transitionType]="opacity "+e.options.speed+"ms "+e.options.cssEase,!1===e.options.fade?e.$slideTrack.css(t):e.$slides.eq(i).css(t)},e.prototype.autoPlay=function(){var i=this;i.autoPlayClear(),i.slideCount>i.options.slidesToShow&&(i.autoPlayTimer=setInterval(i.autoPlayIterator,i.options.autoplaySpeed))},e.prototype.autoPlayClear=function(){var i=this;i.autoPlayTimer&&clearInterval(i.autoPlayTimer)},e.prototype.autoPlayIterator=function(){var i=this,e=i.currentSlide+i.options.slidesToScroll;i.paused||i.interrupted||i.focussed||(!1===i.options.infinite&&(1===i.direction&&i.currentSlide+1===i.slideCount-1?i.direction=0:0===i.direction&&(e=i.currentSlide-i.options.slidesToScroll,i.currentSlide-1==0&&(i.direction=1))),i.slideHandler(e))},e.prototype.buildArrows=function(){var e=this;!0===e.options.arrows&&(e.$prevArrow=i(e.options.prevArrow).addClass("slick-arrow"),e.$nextArrow=i(e.options.nextArrow).addClass("slick-arrow"),e.slideCount>e.options.slidesToShow?(e.$prevArrow.removeClass("slick-hidden").removeAttr("aria-hidden tabindex"),e.$nextArrow.removeClass("slick-hidden").removeAttr("aria-hidden tabindex"),e.htmlExpr.test(e.options.prevArrow)&&e.$prevArrow.prependTo(e.options.appendArrows),e.htmlExpr.test(e.options.nextArrow)&&e.$nextArrow.appendTo(e.options.appendArrows),!0!==e.options.infinite&&e.$prevArrow.addClass("slick-disabled").attr("aria-disabled","true")):e.$prevArrow.add(e.$nextArrow).addClass("slick-hidden").attr({"aria-disabled":"true",tabindex:"-1"}))},e.prototype.buildDots=function(){var e,t,o=this;if(!0===o.options.dots){for(o.$slider.addClass("slick-dotted"),t=i("<ul />").addClass(o.options.dotsClass),e=0;e<=o.getDotCount();e+=1)t.append(i("<li />").append(o.options.customPaging.call(this,o,e)));o.$dots=t.appendTo(o.options.appendDots),o.$dots.find("li").first().addClass("slick-active")}},e.prototype.buildOut=function(){var e=this;e.$slides=e.$slider.children(e.options.slide+":not(.slick-cloned)").addClass("slick-slide"),e.slideCount=e.$slides.length,e.$slides.each(function(e,t){i(t).attr("data-slick-index",e).data("originalStyling",i(t).attr("style")||"")}),e.$slider.addClass("slick-slider"),e.$slideTrack=0===e.slideCount?i('<div class="slick-track"/>').appendTo(e.$slider):e.$slides.wrapAll('<div class="slick-track"/>').parent(),e.$list=e.$slideTrack.wrap('<div class="slick-list"/>').parent(),e.$slideTrack.css("opacity",0),!0!==e.options.centerMode&&!0!==e.options.swipeToSlide||(e.options.slidesToScroll=1),i("img[data-lazy]",e.$slider).not("[src]").addClass("slick-loading"),e.setupInfinite(),e.buildArrows(),e.buildDots(),e.updateDots(),e.setSlideClasses("number"==typeof e.currentSlide?e.currentSlide:0),!0===e.options.draggable&&e.$list.addClass("draggable")},e.prototype.buildRows=function(){var i,e,t,o,s,n,r,l=this;if(o=document.createDocumentFragment(),n=l.$slider.children(),l.options.rows>1){for(r=l.options.slidesPerRow*l.options.rows,s=Math.ceil(n.length/r),i=0;i<s;i++){var d=document.createElement("div");for(e=0;e<l.options.rows;e++){var a=document.createElement("div");for(t=0;t<l.options.slidesPerRow;t++){var c=i*r+(e*l.options.slidesPerRow+t);n.get(c)&&a.appendChild(n.get(c))}d.appendChild(a)}o.appendChild(d)}l.$slider.empty().append(o),l.$slider.children().children().children().css({width:100/l.options.slidesPerRow+"%",display:"inline-block"})}},e.prototype.checkResponsive=function(e,t){var o,s,n,r=this,l=!1,d=r.$slider.width(),a=window.innerWidth||i(window).width();if("window"===r.respondTo?n=a:"slider"===r.respondTo?n=d:"min"===r.respondTo&&(n=Math.min(a,d)),r.options.responsive&&r.options.responsive.length&&null!==r.options.responsive){s=null;for(o in r.breakpoints)r.breakpoints.hasOwnProperty(o)&&(!1===r.originalSettings.mobileFirst?n<r.breakpoints[o]&&(s=r.breakpoints[o]):n>r.breakpoints[o]&&(s=r.breakpoints[o]));null!==s?null!==r.activeBreakpoint?(s!==r.activeBreakpoint||t)&&(r.activeBreakpoint=s,"unslick"===r.breakpointSettings[s]?r.unslick(s):(r.options=i.extend({},r.originalSettings,r.breakpointSettings[s]),!0===e&&(r.currentSlide=r.options.initialSlide),r.refresh(e)),l=s):(r.activeBreakpoint=s,"unslick"===r.breakpointSettings[s]?r.unslick(s):(r.options=i.extend({},r.originalSettings,r.breakpointSettings[s]),!0===e&&(r.currentSlide=r.options.initialSlide),r.refresh(e)),l=s):null!==r.activeBreakpoint&&(r.activeBreakpoint=null,r.options=r.originalSettings,!0===e&&(r.currentSlide=r.options.initialSlide),r.refresh(e),l=s),e||!1===l||r.$slider.trigger("breakpoint",[r,l])}},e.prototype.changeSlide=function(e,t){var o,s,n,r=this,l=i(e.currentTarget);switch(l.is("a")&&e.preventDefault(),l.is("li")||(l=l.closest("li")),n=r.slideCount%r.options.slidesToScroll!=0,o=n?0:(r.slideCount-r.currentSlide)%r.options.slidesToScroll,e.data.message){case"previous":s=0===o?r.options.slidesToScroll:r.options.slidesToShow-o,r.slideCount>r.options.slidesToShow&&r.slideHandler(r.currentSlide-s,!1,t);break;case"next":s=0===o?r.options.slidesToScroll:o,r.slideCount>r.options.slidesToShow&&r.slideHandler(r.currentSlide+s,!1,t);break;case"index":var d=0===e.data.index?0:e.data.index||l.index()*r.options.slidesToScroll;r.slideHandler(r.checkNavigable(d),!1,t),l.children().trigger("focus");break;default:return}},e.prototype.checkNavigable=function(i){var e,t;if(e=this.getNavigableIndexes(),t=0,i>e[e.length-1])i=e[e.length-1];else for(var o in e){if(i<e[o]){i=t;break}t=e[o]}return i},e.prototype.cleanUpEvents=function(){var e=this;e.options.dots&&null!==e.$dots&&(i("li",e.$dots).off("click.slick",e.changeSlide).off("mouseenter.slick",i.proxy(e.interrupt,e,!0)).off("mouseleave.slick",i.proxy(e.interrupt,e,!1)),!0===e.options.accessibility&&e.$dots.off("keydown.slick",e.keyHandler)),e.$slider.off("focus.slick blur.slick"),!0===e.options.arrows&&e.slideCount>e.options.slidesToShow&&(e.$prevArrow&&e.$prevArrow.off("click.slick",e.changeSlide),e.$nextArrow&&e.$nextArrow.off("click.slick",e.changeSlide),!0===e.options.accessibility&&(e.$prevArrow&&e.$prevArrow.off("keydown.slick",e.keyHandler),e.$nextArrow&&e.$nextArrow.off("keydown.slick",e.keyHandler))),e.$list.off("touchstart.slick mousedown.slick",e.swipeHandler),e.$list.off("touchmove.slick mousemove.slick",e.swipeHandler),e.$list.off("touchend.slick mouseup.slick",e.swipeHandler),e.$list.off("touchcancel.slick mouseleave.slick",e.swipeHandler),e.$list.off("click.slick",e.clickHandler),i(document).off(e.visibilityChange,e.visibility),e.cleanUpSlideEvents(),!0===e.options.accessibility&&e.$list.off("keydown.slick",e.keyHandler),!0===e.options.focusOnSelect&&i(e.$slideTrack).children().off("click.slick",e.selectHandler),i(window).off("orientationchange.slick.slick-"+e.instanceUid,e.orientationChange),i(window).off("resize.slick.slick-"+e.instanceUid,e.resize),i("[draggable!=true]",e.$slideTrack).off("dragstart",e.preventDefault),i(window).off("load.slick.slick-"+e.instanceUid,e.setPosition)},e.prototype.cleanUpSlideEvents=function(){var e=this;e.$list.off("mouseenter.slick",i.proxy(e.interrupt,e,!0)),e.$list.off("mouseleave.slick",i.proxy(e.interrupt,e,!1))},e.prototype.cleanUpRows=function(){var i,e=this;e.options.rows>1&&((i=e.$slides.children().children()).removeAttr("style"),e.$slider.empty().append(i))},e.prototype.clickHandler=function(i){!1===this.shouldClick&&(i.stopImmediatePropagation(),i.stopPropagation(),i.preventDefault())},e.prototype.destroy=function(e){var t=this;t.autoPlayClear(),t.touchObject={},t.cleanUpEvents(),i(".slick-cloned",t.$slider).detach(),t.$dots&&t.$dots.remove(),t.$prevArrow&&t.$prevArrow.length&&(t.$prevArrow.removeClass("slick-disabled slick-arrow slick-hidden").removeAttr("aria-hidden aria-disabled tabindex").css("display",""),t.htmlExpr.test(t.options.prevArrow)&&t.$prevArrow.remove()),t.$nextArrow&&t.$nextArrow.length&&(t.$nextArrow.removeClass("slick-disabled slick-arrow slick-hidden").removeAttr("aria-hidden aria-disabled tabindex").css("display",""),t.htmlExpr.test(t.options.nextArrow)&&t.$nextArrow.remove()),t.$slides&&(t.$slides.removeClass("slick-slide slick-active slick-center slick-visible slick-current").removeAttr("aria-hidden").removeAttr("data-slick-index").each(function(){i(this).attr("style",i(this).data("originalStyling"))}),t.$slideTrack.children(this.options.slide).detach(),t.$slideTrack.detach(),t.$list.detach(),t.$slider.append(t.$slides)),t.cleanUpRows(),t.$slider.removeClass("slick-slider"),t.$slider.removeClass("slick-initialized"),t.$slider.removeClass("slick-dotted"),t.unslicked=!0,e||t.$slider.trigger("destroy",[t])},e.prototype.disableTransition=function(i){var e=this,t={};t[e.transitionType]="",!1===e.options.fade?e.$slideTrack.css(t):e.$slides.eq(i).css(t)},e.prototype.fadeSlide=function(i,e){var t=this;!1===t.cssTransitions?(t.$slides.eq(i).css({zIndex:t.options.zIndex}),t.$slides.eq(i).animate({opacity:1},t.options.speed,t.options.easing,e)):(t.applyTransition(i),t.$slides.eq(i).css({opacity:1,zIndex:t.options.zIndex}),e&&setTimeout(function(){t.disableTransition(i),e.call()},t.options.speed))},e.prototype.fadeSlideOut=function(i){var e=this;!1===e.cssTransitions?e.$slides.eq(i).animate({opacity:0,zIndex:e.options.zIndex-2},e.options.speed,e.options.easing):(e.applyTransition(i),e.$slides.eq(i).css({opacity:0,zIndex:e.options.zIndex-2}))},e.prototype.filterSlides=e.prototype.slickFilter=function(i){var e=this;null!==i&&(e.$slidesCache=e.$slides,e.unload(),e.$slideTrack.children(this.options.slide).detach(),e.$slidesCache.filter(i).appendTo(e.$slideTrack),e.reinit())},e.prototype.focusHandler=function(){var e=this;e.$slider.off("focus.slick blur.slick").on("focus.slick blur.slick","*",function(t){t.stopImmediatePropagation();var o=i(this);setTimeout(function(){e.options.pauseOnFocus&&(e.focussed=o.is(":focus"),e.autoPlay())},0)})},e.prototype.getCurrent=e.prototype.slickCurrentSlide=function(){return this.currentSlide},e.prototype.getDotCount=function(){var i=this,e=0,t=0,o=0;if(!0===i.options.infinite)if(i.slideCount<=i.options.slidesToShow)++o;else for(;e<i.slideCount;)++o,e=t+i.options.slidesToScroll,t+=i.options.slidesToScroll<=i.options.slidesToShow?i.options.slidesToScroll:i.options.slidesToShow;else if(!0===i.options.centerMode)o=i.slideCount;else if(i.options.asNavFor)for(;e<i.slideCount;)++o,e=t+i.options.slidesToScroll,t+=i.options.slidesToScroll<=i.options.slidesToShow?i.options.slidesToScroll:i.options.slidesToShow;else o=1+Math.ceil((i.slideCount-i.options.slidesToShow)/i.options.slidesToScroll);return o-1},e.prototype.getLeft=function(i){var e,t,o,s,n=this,r=0;return n.slideOffset=0,t=n.$slides.first().outerHeight(!0),!0===n.options.infinite?(n.slideCount>n.options.slidesToShow&&(n.slideOffset=n.slideWidth*n.options.slidesToShow*-1,s=-1,!0===n.options.vertical&&!0===n.options.centerMode&&(2===n.options.slidesToShow?s=-1.5:1===n.options.slidesToShow&&(s=-2)),r=t*n.options.slidesToShow*s),n.slideCount%n.options.slidesToScroll!=0&&i+n.options.slidesToScroll>n.slideCount&&n.slideCount>n.options.slidesToShow&&(i>n.slideCount?(n.slideOffset=(n.options.slidesToShow-(i-n.slideCount))*n.slideWidth*-1,r=(n.options.slidesToShow-(i-n.slideCount))*t*-1):(n.slideOffset=n.slideCount%n.options.slidesToScroll*n.slideWidth*-1,r=n.slideCount%n.options.slidesToScroll*t*-1))):i+n.options.slidesToShow>n.slideCount&&(n.slideOffset=(i+n.options.slidesToShow-n.slideCount)*n.slideWidth,r=(i+n.options.slidesToShow-n.slideCount)*t),n.slideCount<=n.options.slidesToShow&&(n.slideOffset=0,r=0),!0===n.options.centerMode&&n.slideCount<=n.options.slidesToShow?n.slideOffset=n.slideWidth*Math.floor(n.options.slidesToShow)/2-n.slideWidth*n.slideCount/2:!0===n.options.centerMode&&!0===n.options.infinite?n.slideOffset+=n.slideWidth*Math.floor(n.options.slidesToShow/2)-n.slideWidth:!0===n.options.centerMode&&(n.slideOffset=0,n.slideOffset+=n.slideWidth*Math.floor(n.options.slidesToShow/2)),e=!1===n.options.vertical?i*n.slideWidth*-1+n.slideOffset:i*t*-1+r,!0===n.options.variableWidth&&(o=n.slideCount<=n.options.slidesToShow||!1===n.options.infinite?n.$slideTrack.children(".slick-slide").eq(i):n.$slideTrack.children(".slick-slide").eq(i+n.options.slidesToShow),e=!0===n.options.rtl?o[0]?-1*(n.$slideTrack.width()-o[0].offsetLeft-o.width()):0:o[0]?-1*o[0].offsetLeft:0,!0===n.options.centerMode&&(o=n.slideCount<=n.options.slidesToShow||!1===n.options.infinite?n.$slideTrack.children(".slick-slide").eq(i):n.$slideTrack.children(".slick-slide").eq(i+n.options.slidesToShow+1),e=!0===n.options.rtl?o[0]?-1*(n.$slideTrack.width()-o[0].offsetLeft-o.width()):0:o[0]?-1*o[0].offsetLeft:0,e+=(n.$list.width()-o.outerWidth())/2)),e},e.prototype.getOption=e.prototype.slickGetOption=function(i){return this.options[i]},e.prototype.getNavigableIndexes=function(){var i,e=this,t=0,o=0,s=[];for(!1===e.options.infinite?i=e.slideCount:(t=-1*e.options.slidesToScroll,o=-1*e.options.slidesToScroll,i=2*e.slideCount);t<i;)s.push(t),t=o+e.options.slidesToScroll,o+=e.options.slidesToScroll<=e.options.slidesToShow?e.options.slidesToScroll:e.options.slidesToShow;return s},e.prototype.getSlick=function(){return this},e.prototype.getSlideCount=function(){var e,t,o=this;return t=!0===o.options.centerMode?o.slideWidth*Math.floor(o.options.slidesToShow/2):0,!0===o.options.swipeToSlide?(o.$slideTrack.find(".slick-slide").each(function(s,n){if(n.offsetLeft-t+i(n).outerWidth()/2>-1*o.swipeLeft)return e=n,!1}),Math.abs(i(e).attr("data-slick-index")-o.currentSlide)||1):o.options.slidesToScroll},e.prototype.goTo=e.prototype.slickGoTo=function(i,e){this.changeSlide({data:{message:"index",index:parseInt(i)}},e)},e.prototype.init=function(e){var t=this;i(t.$slider).hasClass("slick-initialized")||(i(t.$slider).addClass("slick-initialized"),t.buildRows(),t.buildOut(),t.setProps(),t.startLoad(),t.loadSlider(),t.initializeEvents(),t.updateArrows(),t.updateDots(),t.checkResponsive(!0),t.focusHandler()),e&&t.$slider.trigger("init",[t]),!0===t.options.accessibility&&t.initADA(),t.options.autoplay&&(t.paused=!1,t.autoPlay())},e.prototype.initADA=function(){var e=this,t=Math.ceil(e.slideCount/e.options.slidesToShow),o=e.getNavigableIndexes().filter(function(i){return i>=0&&i<e.slideCount});e.$slides.add(e.$slideTrack.find(".slick-cloned")).attr({"aria-hidden":"true",tabindex:"-1"}).find("a, input, button, select").attr({tabindex:"-1"}),null!==e.$dots&&(e.$slides.not(e.$slideTrack.find(".slick-cloned")).each(function(t){var s=o.indexOf(t);i(this).attr({role:"tabpanel",id:"slick-slide"+e.instanceUid+t,tabindex:-1}),-1!==s&&i(this).attr({"aria-describedby":"slick-slide-control"+e.instanceUid+s})}),e.$dots.attr("role","tablist").find("li").each(function(s){var n=o[s];i(this).attr({role:"presentation"}),i(this).find("button").first().attr({role:"tab",id:"slick-slide-control"+e.instanceUid+s,"aria-controls":"slick-slide"+e.instanceUid+n,"aria-label":s+1+" of "+t,"aria-selected":null,tabindex:"-1"})}).eq(e.currentSlide).find("button").attr({"aria-selected":"true",tabindex:"0"}).end());for(var s=e.currentSlide,n=s+e.options.slidesToShow;s<n;s++)e.$slides.eq(s).attr("tabindex",0);e.activateADA()},e.prototype.initArrowEvents=function(){var i=this;!0===i.options.arrows&&i.slideCount>i.options.slidesToShow&&(i.$prevArrow.off("click.slick").on("click.slick",{message:"previous"},i.changeSlide),i.$nextArrow.off("click.slick").on("click.slick",{message:"next"},i.changeSlide),!0===i.options.accessibility&&(i.$prevArrow.on("keydown.slick",i.keyHandler),i.$nextArrow.on("keydown.slick",i.keyHandler)))},e.prototype.initDotEvents=function(){var e=this;!0===e.options.dots&&(i("li",e.$dots).on("click.slick",{message:"index"},e.changeSlide),!0===e.options.accessibility&&e.$dots.on("keydown.slick",e.keyHandler)),!0===e.options.dots&&!0===e.options.pauseOnDotsHover&&i("li",e.$dots).on("mouseenter.slick",i.proxy(e.interrupt,e,!0)).on("mouseleave.slick",i.proxy(e.interrupt,e,!1))},e.prototype.initSlideEvents=function(){var e=this;e.options.pauseOnHover&&(e.$list.on("mouseenter.slick",i.proxy(e.interrupt,e,!0)),e.$list.on("mouseleave.slick",i.proxy(e.interrupt,e,!1)))},e.prototype.initializeEvents=function(){var e=this;e.initArrowEvents(),e.initDotEvents(),e.initSlideEvents(),e.$list.on("touchstart.slick mousedown.slick",{action:"start"},e.swipeHandler),e.$list.on("touchmove.slick mousemove.slick",{action:"move"},e.swipeHandler),e.$list.on("touchend.slick mouseup.slick",{action:"end"},e.swipeHandler),e.$list.on("touchcancel.slick mouseleave.slick",{action:"end"},e.swipeHandler),e.$list.on("click.slick",e.clickHandler),i(document).on(e.visibilityChange,i.proxy(e.visibility,e)),!0===e.options.accessibility&&e.$list.on("keydown.slick",e.keyHandler),!0===e.options.focusOnSelect&&i(e.$slideTrack).children().on("click.slick",e.selectHandler),i(window).on("orientationchange.slick.slick-"+e.instanceUid,i.proxy(e.orientationChange,e)),i(window).on("resize.slick.slick-"+e.instanceUid,i.proxy(e.resize,e)),i("[draggable!=true]",e.$slideTrack).on("dragstart",e.preventDefault),i(window).on("load.slick.slick-"+e.instanceUid,e.setPosition),i(e.setPosition)},e.prototype.initUI=function(){var i=this;!0===i.options.arrows&&i.slideCount>i.options.slidesToShow&&(i.$prevArrow.show(),i.$nextArrow.show()),!0===i.options.dots&&i.slideCount>i.options.slidesToShow&&i.$dots.show()},e.prototype.keyHandler=function(i){var e=this;i.target.tagName.match("TEXTAREA|INPUT|SELECT")||(37===i.keyCode&&!0===e.options.accessibility?e.changeSlide({data:{message:!0===e.options.rtl?"next":"previous"}}):39===i.keyCode&&!0===e.options.accessibility&&e.changeSlide({data:{message:!0===e.options.rtl?"previous":"next"}}))},e.prototype.lazyLoad=function(){function e(e){i("img[data-lazy]",e).each(function(){var e=i(this),t=i(this).attr("data-lazy"),o=i(this).attr("data-srcset"),s=i(this).attr("data-sizes")||n.$slider.attr("data-sizes"),r=document.createElement("img");r.onload=function(){e.animate({opacity:0},100,function(){o&&(e.attr("srcset",o),s&&e.attr("sizes",s)),e.attr("src",t).animate({opacity:1},200,function(){e.removeAttr("data-lazy data-srcset data-sizes").removeClass("slick-loading")}),n.$slider.trigger("lazyLoaded",[n,e,t])})},r.onerror=function(){e.removeAttr("data-lazy").removeClass("slick-loading").addClass("slick-lazyload-error"),n.$slider.trigger("lazyLoadError",[n,e,t])},r.src=t})}var t,o,s,n=this;if(!0===n.options.centerMode?!0===n.options.infinite?s=(o=n.currentSlide+(n.options.slidesToShow/2+1))+n.options.slidesToShow+2:(o=Math.max(0,n.currentSlide-(n.options.slidesToShow/2+1)),s=n.options.slidesToShow/2+1+2+n.currentSlide):(o=n.options.infinite?n.options.slidesToShow+n.currentSlide:n.currentSlide,s=Math.ceil(o+n.options.slidesToShow),!0===n.options.fade&&(o>0&&o--,s<=n.slideCount&&s++)),t=n.$slider.find(".slick-slide").slice(o,s),"anticipated"===n.options.lazyLoad)for(var r=o-1,l=s,d=n.$slider.find(".slick-slide"),a=0;a<n.options.slidesToScroll;a++)r<0&&(r=n.slideCount-1),t=(t=t.add(d.eq(r))).add(d.eq(l)),r--,l++;e(t),n.slideCount<=n.options.slidesToShow?e(n.$slider.find(".slick-slide")):n.currentSlide>=n.slideCount-n.options.slidesToShow?e(n.$slider.find(".slick-cloned").slice(0,n.options.slidesToShow)):0===n.currentSlide&&e(n.$slider.find(".slick-cloned").slice(-1*n.options.slidesToShow))},e.prototype.loadSlider=function(){var i=this;i.setPosition(),i.$slideTrack.css({opacity:1}),i.$slider.removeClass("slick-loading"),i.initUI(),"progressive"===i.options.lazyLoad&&i.progressiveLazyLoad()},e.prototype.next=e.prototype.slickNext=function(){this.changeSlide({data:{message:"next"}})},e.prototype.orientationChange=function(){var i=this;i.checkResponsive(),i.setPosition()},e.prototype.pause=e.prototype.slickPause=function(){var i=this;i.autoPlayClear(),i.paused=!0},e.prototype.play=e.prototype.slickPlay=function(){var i=this;i.autoPlay(),i.options.autoplay=!0,i.paused=!1,i.focussed=!1,i.interrupted=!1},e.prototype.postSlide=function(e){var t=this;t.unslicked||(t.$slider.trigger("afterChange",[t,e]),t.animating=!1,t.slideCount>t.options.slidesToShow&&t.setPosition(),t.swipeLeft=null,t.options.autoplay&&t.autoPlay(),!0===t.options.accessibility&&(t.initADA(),t.options.focusOnChange&&i(t.$slides.get(t.currentSlide)).attr("tabindex",0).focus()))},e.prototype.prev=e.prototype.slickPrev=function(){this.changeSlide({data:{message:"previous"}})},e.prototype.preventDefault=function(i){i.preventDefault()},e.prototype.progressiveLazyLoad=function(e){e=e||1;var t,o,s,n,r,l=this,d=i("img[data-lazy]",l.$slider);d.length?(t=d.first(),o=t.attr("data-lazy"),s=t.attr("data-srcset"),n=t.attr("data-sizes")||l.$slider.attr("data-sizes"),(r=document.createElement("img")).onload=function(){s&&(t.attr("srcset",s),n&&t.attr("sizes",n)),t.attr("src",o).removeAttr("data-lazy data-srcset data-sizes").removeClass("slick-loading"),!0===l.options.adaptiveHeight&&l.setPosition(),l.$slider.trigger("lazyLoaded",[l,t,o]),l.progressiveLazyLoad()},r.onerror=function(){e<3?setTimeout(function(){l.progressiveLazyLoad(e+1)},500):(t.removeAttr("data-lazy").removeClass("slick-loading").addClass("slick-lazyload-error"),l.$slider.trigger("lazyLoadError",[l,t,o]),l.progressiveLazyLoad())},r.src=o):l.$slider.trigger("allImagesLoaded",[l])},e.prototype.refresh=function(e){var t,o,s=this;o=s.slideCount-s.options.slidesToShow,!s.options.infinite&&s.currentSlide>o&&(s.currentSlide=o),s.slideCount<=s.options.slidesToShow&&(s.currentSlide=0),t=s.currentSlide,s.destroy(!0),i.extend(s,s.initials,{currentSlide:t}),s.init(),e||s.changeSlide({data:{message:"index",index:t}},!1)},e.prototype.registerBreakpoints=function(){var e,t,o,s=this,n=s.options.responsive||null;if("array"===i.type(n)&&n.length){s.respondTo=s.options.respondTo||"window";for(e in n)if(o=s.breakpoints.length-1,n.hasOwnProperty(e)){for(t=n[e].breakpoint;o>=0;)s.breakpoints[o]&&s.breakpoints[o]===t&&s.breakpoints.splice(o,1),o--;s.breakpoints.push(t),s.breakpointSettings[t]=n[e].settings}s.breakpoints.sort(function(i,e){return s.options.mobileFirst?i-e:e-i})}},e.prototype.reinit=function(){var e=this;e.$slides=e.$slideTrack.children(e.options.slide).addClass("slick-slide"),e.slideCount=e.$slides.length,e.currentSlide>=e.slideCount&&0!==e.currentSlide&&(e.currentSlide=e.currentSlide-e.options.slidesToScroll),e.slideCount<=e.options.slidesToShow&&(e.currentSlide=0),e.registerBreakpoints(),e.setProps(),e.setupInfinite(),e.buildArrows(),e.updateArrows(),e.initArrowEvents(),e.buildDots(),e.updateDots(),e.initDotEvents(),e.cleanUpSlideEvents(),e.initSlideEvents(),e.checkResponsive(!1,!0),!0===e.options.focusOnSelect&&i(e.$slideTrack).children().on("click.slick",e.selectHandler),e.setSlideClasses("number"==typeof e.currentSlide?e.currentSlide:0),e.setPosition(),e.focusHandler(),e.paused=!e.options.autoplay,e.autoPlay(),e.$slider.trigger("reInit",[e])},e.prototype.resize=function(){var e=this;i(window).width()!==e.windowWidth&&(clearTimeout(e.windowDelay),e.windowDelay=window.setTimeout(function(){e.windowWidth=i(window).width(),e.checkResponsive(),e.unslicked||e.setPosition()},50))},e.prototype.removeSlide=e.prototype.slickRemove=function(i,e,t){var o=this;if(i="boolean"==typeof i?!0===(e=i)?0:o.slideCount-1:!0===e?--i:i,o.slideCount<1||i<0||i>o.slideCount-1)return!1;o.unload(),!0===t?o.$slideTrack.children().remove():o.$slideTrack.children(this.options.slide).eq(i).remove(),o.$slides=o.$slideTrack.children(this.options.slide),o.$slideTrack.children(this.options.slide).detach(),o.$slideTrack.append(o.$slides),o.$slidesCache=o.$slides,o.reinit()},e.prototype.setCSS=function(i){var e,t,o=this,s={};!0===o.options.rtl&&(i=-i),e="left"==o.positionProp?Math.ceil(i)+"px":"0px",t="top"==o.positionProp?Math.ceil(i)+"px":"0px",s[o.positionProp]=i,!1===o.transformsEnabled?o.$slideTrack.css(s):(s={},!1===o.cssTransitions?(s[o.animType]="translate("+e+", "+t+")",o.$slideTrack.css(s)):(s[o.animType]="translate3d("+e+", "+t+", 0px)",o.$slideTrack.css(s)))},e.prototype.setDimensions=function(){var i=this;!1===i.options.vertical?!0===i.options.centerMode&&i.$list.css({padding:"0px "+i.options.centerPadding}):(i.$list.height(i.$slides.first().outerHeight(!0)*i.options.slidesToShow),!0===i.options.centerMode&&i.$list.css({padding:i.options.centerPadding+" 0px"})),i.listWidth=i.$list.width(),i.listHeight=i.$list.height(),!1===i.options.vertical&&!1===i.options.variableWidth?(i.slideWidth=Math.ceil(i.listWidth/i.options.slidesToShow),i.$slideTrack.width(Math.ceil(i.slideWidth*i.$slideTrack.children(".slick-slide").length))):!0===i.options.variableWidth?i.$slideTrack.width(5e3*i.slideCount):(i.slideWidth=Math.ceil(i.listWidth),i.$slideTrack.height(Math.ceil(i.$slides.first().outerHeight(!0)*i.$slideTrack.children(".slick-slide").length)));var e=i.$slides.first().outerWidth(!0)-i.$slides.first().width();!1===i.options.variableWidth&&i.$slideTrack.children(".slick-slide").width(i.slideWidth-e)},e.prototype.setFade=function(){var e,t=this;t.$slides.each(function(o,s){e=t.slideWidth*o*-1,!0===t.options.rtl?i(s).css({position:"relative",right:e,top:0,zIndex:t.options.zIndex-2,opacity:0}):i(s).css({position:"relative",left:e,top:0,zIndex:t.options.zIndex-2,opacity:0})}),t.$slides.eq(t.currentSlide).css({zIndex:t.options.zIndex-1,opacity:1})},e.prototype.setHeight=function(){var i=this;if(1===i.options.slidesToShow&&!0===i.options.adaptiveHeight&&!1===i.options.vertical){var e=i.$slides.eq(i.currentSlide).outerHeight(!0);i.$list.css("height",e)}},e.prototype.setOption=e.prototype.slickSetOption=function(){var e,t,o,s,n,r=this,l=!1;if("object"===i.type(arguments[0])?(o=arguments[0],l=arguments[1],n="multiple"):"string"===i.type(arguments[0])&&(o=arguments[0],s=arguments[1],l=arguments[2],"responsive"===arguments[0]&&"array"===i.type(arguments[1])?n="responsive":void 0!==arguments[1]&&(n="single")),"single"===n)r.options[o]=s;else if("multiple"===n)i.each(o,function(i,e){r.options[i]=e});else if("responsive"===n)for(t in s)if("array"!==i.type(r.options.responsive))r.options.responsive=[s[t]];else{for(e=r.options.responsive.length-1;e>=0;)r.options.responsive[e].breakpoint===s[t].breakpoint&&r.options.responsive.splice(e,1),e--;r.options.responsive.push(s[t])}l&&(r.unload(),r.reinit())},e.prototype.setPosition=function(){var i=this;i.setDimensions(),i.setHeight(),!1===i.options.fade?i.setCSS(i.getLeft(i.currentSlide)):i.setFade(),i.$slider.trigger("setPosition",[i])},e.prototype.setProps=function(){var i=this,e=document.body.style;i.positionProp=!0===i.options.vertical?"top":"left","top"===i.positionProp?i.$slider.addClass("slick-vertical"):i.$slider.removeClass("slick-vertical"),void 0===e.WebkitTransition&&void 0===e.MozTransition&&void 0===e.msTransition||!0===i.options.useCSS&&(i.cssTransitions=!0),i.options.fade&&("number"==typeof i.options.zIndex?i.options.zIndex<3&&(i.options.zIndex=3):i.options.zIndex=i.defaults.zIndex),void 0!==e.OTransform&&(i.animType="OTransform",i.transformType="-o-transform",i.transitionType="OTransition",void 0===e.perspectiveProperty&&void 0===e.webkitPerspective&&(i.animType=!1)),void 0!==e.MozTransform&&(i.animType="MozTransform",i.transformType="-moz-transform",i.transitionType="MozTransition",void 0===e.perspectiveProperty&&void 0===e.MozPerspective&&(i.animType=!1)),void 0!==e.webkitTransform&&(i.animType="webkitTransform",i.transformType="-webkit-transform",i.transitionType="webkitTransition",void 0===e.perspectiveProperty&&void 0===e.webkitPerspective&&(i.animType=!1)),void 0!==e.msTransform&&(i.animType="msTransform",i.transformType="-ms-transform",i.transitionType="msTransition",void 0===e.msTransform&&(i.animType=!1)),void 0!==e.transform&&!1!==i.animType&&(i.animType="transform",i.transformType="transform",i.transitionType="transition"),i.transformsEnabled=i.options.useTransform&&null!==i.animType&&!1!==i.animType},e.prototype.setSlideClasses=function(i){var e,t,o,s,n=this;if(t=n.$slider.find(".slick-slide").removeClass("slick-active slick-center slick-current").attr("aria-hidden","true"),n.$slides.eq(i).addClass("slick-current"),!0===n.options.centerMode){var r=n.options.slidesToShow%2==0?1:0;e=Math.floor(n.options.slidesToShow/2),!0===n.options.infinite&&(i>=e&&i<=n.slideCount-1-e?n.$slides.slice(i-e+r,i+e+1).addClass("slick-active").attr("aria-hidden","false"):(o=n.options.slidesToShow+i,t.slice(o-e+1+r,o+e+2).addClass("slick-active").attr("aria-hidden","false")),0===i?t.eq(t.length-1-n.options.slidesToShow).addClass("slick-center"):i===n.slideCount-1&&t.eq(n.options.slidesToShow).addClass("slick-center")),n.$slides.eq(i).addClass("slick-center")}else i>=0&&i<=n.slideCount-n.options.slidesToShow?n.$slides.slice(i,i+n.options.slidesToShow).addClass("slick-active").attr("aria-hidden","false"):t.length<=n.options.slidesToShow?t.addClass("slick-active").attr("aria-hidden","false"):(s=n.slideCount%n.options.slidesToShow,o=!0===n.options.infinite?n.options.slidesToShow+i:i,n.options.slidesToShow==n.options.slidesToScroll&&n.slideCount-i<n.options.slidesToShow?t.slice(o-(n.options.slidesToShow-s),o+s).addClass("slick-active").attr("aria-hidden","false"):t.slice(o,o+n.options.slidesToShow).addClass("slick-active").attr("aria-hidden","false"));"ondemand"!==n.options.lazyLoad&&"anticipated"!==n.options.lazyLoad||n.lazyLoad()},e.prototype.setupInfinite=function(){var e,t,o,s=this;if(!0===s.options.fade&&(s.options.centerMode=!1),!0===s.options.infinite&&!1===s.options.fade&&(t=null,s.slideCount>s.options.slidesToShow)){for(o=!0===s.options.centerMode?s.options.slidesToShow+1:s.options.slidesToShow,e=s.slideCount;e>s.slideCount-o;e-=1)t=e-1,i(s.$slides[t]).clone(!0).attr("id","").attr("data-slick-index",t-s.slideCount).prependTo(s.$slideTrack).addClass("slick-cloned");for(e=0;e<o+s.slideCount;e+=1)t=e,i(s.$slides[t]).clone(!0).attr("id","").attr("data-slick-index",t+s.slideCount).appendTo(s.$slideTrack).addClass("slick-cloned");s.$slideTrack.find(".slick-cloned").find("[id]").each(function(){i(this).attr("id","")})}},e.prototype.interrupt=function(i){var e=this;i||e.autoPlay(),e.interrupted=i},e.prototype.selectHandler=function(e){var t=this,o=i(e.target).is(".slick-slide")?i(e.target):i(e.target).parents(".slick-slide"),s=parseInt(o.attr("data-slick-index"));s||(s=0),t.slideCount<=t.options.slidesToShow?t.slideHandler(s,!1,!0):t.slideHandler(s)},e.prototype.slideHandler=function(i,e,t){var o,s,n,r,l,d=null,a=this;if(e=e||!1,!(!0===a.animating&&!0===a.options.waitForAnimate||!0===a.options.fade&&a.currentSlide===i))if(!1===e&&a.asNavFor(i),o=i,d=a.getLeft(o),r=a.getLeft(a.currentSlide),a.currentLeft=null===a.swipeLeft?r:a.swipeLeft,!1===a.options.infinite&&!1===a.options.centerMode&&(i<0||i>a.getDotCount()*a.options.slidesToScroll))!1===a.options.fade&&(o=a.currentSlide,!0!==t?a.animateSlide(r,function(){a.postSlide(o)}):a.postSlide(o));else if(!1===a.options.infinite&&!0===a.options.centerMode&&(i<0||i>a.slideCount-a.options.slidesToScroll))!1===a.options.fade&&(o=a.currentSlide,!0!==t?a.animateSlide(r,function(){a.postSlide(o)}):a.postSlide(o));else{if(a.options.autoplay&&clearInterval(a.autoPlayTimer),s=o<0?a.slideCount%a.options.slidesToScroll!=0?a.slideCount-a.slideCount%a.options.slidesToScroll:a.slideCount+o:o>=a.slideCount?a.slideCount%a.options.slidesToScroll!=0?0:o-a.slideCount:o,a.animating=!0,a.$slider.trigger("beforeChange",[a,a.currentSlide,s]),n=a.currentSlide,a.currentSlide=s,a.setSlideClasses(a.currentSlide),a.options.asNavFor&&(l=(l=a.getNavTarget()).slick("getSlick")).slideCount<=l.options.slidesToShow&&l.setSlideClasses(a.currentSlide),a.updateDots(),a.updateArrows(),!0===a.options.fade)return!0!==t?(a.fadeSlideOut(n),a.fadeSlide(s,function(){a.postSlide(s)})):a.postSlide(s),void a.animateHeight();!0!==t?a.animateSlide(d,function(){a.postSlide(s)}):a.postSlide(s)}},e.prototype.startLoad=function(){var i=this;!0===i.options.arrows&&i.slideCount>i.options.slidesToShow&&(i.$prevArrow.hide(),i.$nextArrow.hide()),!0===i.options.dots&&i.slideCount>i.options.slidesToShow&&i.$dots.hide(),i.$slider.addClass("slick-loading")},e.prototype.swipeDirection=function(){var i,e,t,o,s=this;return i=s.touchObject.startX-s.touchObject.curX,e=s.touchObject.startY-s.touchObject.curY,t=Math.atan2(e,i),(o=Math.round(180*t/Math.PI))<0&&(o=360-Math.abs(o)),o<=45&&o>=0?!1===s.options.rtl?"left":"right":o<=360&&o>=315?!1===s.options.rtl?"left":"right":o>=135&&o<=225?!1===s.options.rtl?"right":"left":!0===s.options.verticalSwiping?o>=35&&o<=135?"down":"up":"vertical"},e.prototype.swipeEnd=function(i){var e,t,o=this;if(o.dragging=!1,o.swiping=!1,o.scrolling)return o.scrolling=!1,!1;if(o.interrupted=!1,o.shouldClick=!(o.touchObject.swipeLength>10),void 0===o.touchObject.curX)return!1;if(!0===o.touchObject.edgeHit&&o.$slider.trigger("edge",[o,o.swipeDirection()]),o.touchObject.swipeLength>=o.touchObject.minSwipe){switch(t=o.swipeDirection()){case"left":case"down":e=o.options.swipeToSlide?o.checkNavigable(o.currentSlide+o.getSlideCount()):o.currentSlide+o.getSlideCount(),o.currentDirection=0;break;case"right":case"up":e=o.options.swipeToSlide?o.checkNavigable(o.currentSlide-o.getSlideCount()):o.currentSlide-o.getSlideCount(),o.currentDirection=1}"vertical"!=t&&(o.slideHandler(e),o.touchObject={},o.$slider.trigger("swipe",[o,t]))}else o.touchObject.startX!==o.touchObject.curX&&(o.slideHandler(o.currentSlide),o.touchObject={})},e.prototype.swipeHandler=function(i){var e=this;if(!(!1===e.options.swipe||"ontouchend"in document&&!1===e.options.swipe||!1===e.options.draggable&&-1!==i.type.indexOf("mouse")))switch(e.touchObject.fingerCount=i.originalEvent&&void 0!==i.originalEvent.touches?i.originalEvent.touches.length:1,e.touchObject.minSwipe=e.listWidth/e.options.touchThreshold,!0===e.options.verticalSwiping&&(e.touchObject.minSwipe=e.listHeight/e.options.touchThreshold),i.data.action){case"start":e.swipeStart(i);break;case"move":e.swipeMove(i);break;case"end":e.swipeEnd(i)}},e.prototype.swipeMove=function(i){var e,t,o,s,n,r,l=this;return n=void 0!==i.originalEvent?i.originalEvent.touches:null,!(!l.dragging||l.scrolling||n&&1!==n.length)&&(e=l.getLeft(l.currentSlide),l.touchObject.curX=void 0!==n?n[0].pageX:i.clientX,l.touchObject.curY=void 0!==n?n[0].pageY:i.clientY,l.touchObject.swipeLength=Math.round(Math.sqrt(Math.pow(l.touchObject.curX-l.touchObject.startX,2))),r=Math.round(Math.sqrt(Math.pow(l.touchObject.curY-l.touchObject.startY,2))),!l.options.verticalSwiping&&!l.swiping&&r>4?(l.scrolling=!0,!1):(!0===l.options.verticalSwiping&&(l.touchObject.swipeLength=r),t=l.swipeDirection(),void 0!==i.originalEvent&&l.touchObject.swipeLength>4&&(l.swiping=!0,i.preventDefault()),s=(!1===l.options.rtl?1:-1)*(l.touchObject.curX>l.touchObject.startX?1:-1),!0===l.options.verticalSwiping&&(s=l.touchObject.curY>l.touchObject.startY?1:-1),o=l.touchObject.swipeLength,l.touchObject.edgeHit=!1,!1===l.options.infinite&&(0===l.currentSlide&&"right"===t||l.currentSlide>=l.getDotCount()&&"left"===t)&&(o=l.touchObject.swipeLength*l.options.edgeFriction,l.touchObject.edgeHit=!0),!1===l.options.vertical?l.swipeLeft=e+o*s:l.swipeLeft=e+o*(l.$list.height()/l.listWidth)*s,!0===l.options.verticalSwiping&&(l.swipeLeft=e+o*s),!0!==l.options.fade&&!1!==l.options.touchMove&&(!0===l.animating?(l.swipeLeft=null,!1):void l.setCSS(l.swipeLeft))))},e.prototype.swipeStart=function(i){var e,t=this;if(t.interrupted=!0,1!==t.touchObject.fingerCount||t.slideCount<=t.options.slidesToShow)return t.touchObject={},!1;void 0!==i.originalEvent&&void 0!==i.originalEvent.touches&&(e=i.originalEvent.touches[0]),t.touchObject.startX=t.touchObject.curX=void 0!==e?e.pageX:i.clientX,t.touchObject.startY=t.touchObject.curY=void 0!==e?e.pageY:i.clientY,t.dragging=!0},e.prototype.unfilterSlides=e.prototype.slickUnfilter=function(){var i=this;null!==i.$slidesCache&&(i.unload(),i.$slideTrack.children(this.options.slide).detach(),i.$slidesCache.appendTo(i.$slideTrack),i.reinit())},e.prototype.unload=function(){var e=this;i(".slick-cloned",e.$slider).remove(),e.$dots&&e.$dots.remove(),e.$prevArrow&&e.htmlExpr.test(e.options.prevArrow)&&e.$prevArrow.remove(),e.$nextArrow&&e.htmlExpr.test(e.options.nextArrow)&&e.$nextArrow.remove(),e.$slides.removeClass("slick-slide slick-active slick-visible slick-current").attr("aria-hidden","true").css("width","")},e.prototype.unslick=function(i){var e=this;e.$slider.trigger("unslick",[e,i]),e.destroy()},e.prototype.updateArrows=function(){var i=this;Math.floor(i.options.slidesToShow/2),!0===i.options.arrows&&i.slideCount>i.options.slidesToShow&&!i.options.infinite&&(i.$prevArrow.removeClass("slick-disabled").attr("aria-disabled","false"),i.$nextArrow.removeClass("slick-disabled").attr("aria-disabled","false"),0===i.currentSlide?(i.$prevArrow.addClass("slick-disabled").attr("aria-disabled","true"),i.$nextArrow.removeClass("slick-disabled").attr("aria-disabled","false")):i.currentSlide>=i.slideCount-i.options.slidesToShow&&!1===i.options.centerMode?(i.$nextArrow.addClass("slick-disabled").attr("aria-disabled","true"),i.$prevArrow.removeClass("slick-disabled").attr("aria-disabled","false")):i.currentSlide>=i.slideCount-1&&!0===i.options.centerMode&&(i.$nextArrow.addClass("slick-disabled").attr("aria-disabled","true"),i.$prevArrow.removeClass("slick-disabled").attr("aria-disabled","false")))},e.prototype.updateDots=function(){var i=this;null!==i.$dots&&(i.$dots.find("li").removeClass("slick-active").end(),i.$dots.find("li").eq(Math.floor(i.currentSlide/i.options.slidesToScroll)).addClass("slick-active"))},e.prototype.visibility=function(){var i=this;i.options.autoplay&&(document[i.hidden]?i.interrupted=!0:i.interrupted=!1)},i.fn.slick=function(){var i,t,o=this,s=arguments[0],n=Array.prototype.slice.call(arguments,1),r=o.length;for(i=0;i<r;i++)if("object"==typeof s||void 0===s?o[i].slick=new e(o[i],s):t=o[i].slick[s].apply(o[i].slick,n),void 0!==t)return t;return o}});

if (typeof isTouchDevice == 'undefined'){
    function isTouchDevice() {
        return 'ontouchstart' in document.documentElement;
    }
}


function thAnimParse(el){
    if (navigator.userAgent.match(/trident/gi) || navigator.appName == 'Microsoft Internet Explorer'){
        return false;
    }
    if(!el){
        el = document;
    }
    if(!isTouchDevice()){

        $('[data-anim]',el).each(function(){
            $(this).attr('data-anim-hide',true);
            new Waypoint({
                element: this,
                handler: function(direction) {
                    if(direction == 'up'){
                        $(this.element).attr('data-anim-hide',true);
                    }else{
                        $(this.element).attr('data-anim-hide',false);
                    }
                },
                offset: '75%'
            });
        });
    }
    return false;
}

thAnimParse();
var th_dropdown = {

    $last: null,

    init: function(){
        th_dropdown.parse($('body'));
    },

    parse: function(el){
        $('.ops-dropdown > a',el).each(function(){
            $(this).on('click',function(e){
                e.preventDefault();
                if($(this).parent().hasClass('ops-open')){
                    th_dropdown.close();
                }else{
                    th_dropdown.open($(this).parent());
                }
            });
        });
    },

    open: function($dropdown){
        if($dropdown == th_dropdown.$last){
            return false;
        }
        $('#shadow-bg').addClass('display-bloc');
        $dropdown.addClass('ops-open');
        setTimeout(function(){
            th_dropdown.$last = $dropdown;
            $(document).on('click',th_dropdown.closeLastOnClick);
        },50);
    },

    closeLastOnClick: function(e){
        if($(e.target).parents('.ops-dropdown').first() && th_dropdown.$last && ($(e.target).parents('.ops-dropdown').first()[0] == th_dropdown.$last[0])) {
            return true;
            $('#shadow-bg').removeClass('display-bloc');
        }
        th_dropdown.close();
    },

    close: function(){
        th_dropdown.$last.removeClass('ops-open');
        $('#shadow-bg').removeClass('display-bloc');

        $(document).off('click',th_dropdown.closeLastOnClick);
        th_dropdown.$last = null;
        $('#shadow-bg').removeClass('display-bloc');
    }

};

th_dropdown.init();
/*
        EGALISE EN HAUTEUR DES BLOCKS

        Mettre data-egalize en attr sur le parent
        Mettre la cible en tant que valeur
        Exemple (egalize tous les li):
        <ul data-egalize="li">
 */
function egalizeAll(){
    $('[data-egalize]').each(function(){

        var _self = this;
        var target = $(_self).data('egalize');

        egalizeBlock(_self,target);

        $('img',_self).on('load',function(){
            egalizeBlock(_self,target);
        });
    });
}
function egalizeBlock(elem,target){

    var maxHeight = 0;

    $(target, elem).each(function(){
        $(this).css('min-height','auto');
        if(maxHeight < $(this).height()){
            maxHeight = $(this).height();
        }
    });

    $(target, elem).css('min-height',(maxHeight+1)+'px');
}

/*
    Previen de certains bug
 */
egalizeAll();
setTimeout(egalizeAll,250);
setTimeout(egalizeAll,750);
setTimeout(egalizeAll,1500);

$(window).on('resize',egalizeAll);
function th_expand(el) {
    $('.to-expand', el).each(function () {
        var $link = $('a[href="#expand"]', this).first();
        if ($link.length == 0) {
            $link = $('a[href="#expand"]', $(this).parent()).first();
        }
        var $wrapper = $(this);

        var wrapperHeight = 0;
        $wrapper.attr('style', 'height:auto!important;max-height:2000px!important;');
        wrapperHeight = $wrapper.height();
        $wrapper.removeAttr('style').css('height', wrapperHeight + 'px');

        $(window).on('resize', function () {
            $wrapper.attr('style', 'height:auto!important;');
            wrapperHeight = $wrapper.height();
            $wrapper.removeAttr('style').css('height', wrapperHeight + 'px');
        });

        $link.on('click', function (e) {
            e.preventDefault();
            $(this).addClass('fadeout');
            $wrapper.addClass('expand');
        });
    });
}

th_expand(document);
function th_linkAll(el){
    if(!el){
        el = document;
    }
    $('[data-linkall]',el).each(function(){

        var target = $(this).data('linkall');

        $(this).on('click',function(e){
            if(!e.target.tagName.match(/^a$/i) && !e.target.tagName.match(/^button$/i)){

                e.preventDefault();
                var href = $(e.currentTarget).find(target).attr('href');
                if(href){
                    document.location.href = href;
                }
            }
        });

    });
}

th_linkAll();
var th_overlay = {

    selector_overlay: '.overlay',
    selector_overlay_shadow: '.shadow-overlay',

    callbackOpen: [],
    callbackClose: [],

    init: function(){
        th_overlay.parseOverlayShadow($('body'));
        th_overlay.parse($('body'));
    },

    addCallbackOpen: function(callback){
        th_overlay.callbackOpen.push(callback);
    },
    addCallbackClose: function(callback){
        th_overlay.callbackClose.push(callback);
    },

    parseOverlayShadow: function(){
        $(th_overlay.selector_overlay_shadow).on('click',function(e){
            e.preventDefault();
            $(th_overlay.selector_overlay_shadow).removeClass('open');
            $(th_overlay.selector_overlay+'.open').removeClass('open');
        });
    },

    parse: function(el){

        $(th_overlay.selector_overlay).each(function(){

            var overlayId = this.id;
            $('a[href="#'+overlayId+'"]:not(.parsed-overlay):not(.close),.leaflet-popup-content a[href="#'+overlayId+'"]:not(.close), [data-overlay-open="'+overlayId+'"]:not(.parsed-overlay)')
                .addClass('parsed-overlay')
                .on('click',function(e){
                    e.preventDefault();
                    var idOverlay = $(this).attr('href');
                    if(!idOverlay){
                        idOverlay = $(this).attr('data-overlay-open');
                    }else{
                        idOverlay = idOverlay.replace('#','');
                    }
                    th_overlay.open(idOverlay);
                });
            console.log('coucou');

            $('a[href="#'+overlayId+'"].close:not(.parsed-overlay), [data-overlay-close="'+overlayId+'"]:not(.parsed-overlay)')
                .addClass('parsed-overlay')
                .on('click',function(e){
                    var idOverlay = $(this).attr('href');
                    if(!idOverlay){
                        idOverlay = $(this).attr('data-overlay-close');
                    }else{
                        idOverlay = idOverlay.replace('#','');
                    }

                    if(!idOverlay){
                        idOverlay = null;
                    }
                    e.preventDefault();
                    th_overlay.close(idOverlay);
                });
        });
    },

    open: function(overlayId,openShadow,doCallback){
        if(openShadow !== false){
            openShadow = true;
        }
        if(doCallback !== false){
            doCallback = true;
        }

        $('#'+overlayId).addClass('open');

        if(openShadow == true){
            $(th_overlay.selector_overlay_shadow).addClass('open');
        }

        if(doCallback == true){
            $.each(th_overlay.callbackOpen,function(k,callback){
                callback(overlayId);
            });
        }
    },

    close: function(overlayId,closeShadow,doCallback){

        if(closeShadow !== false){
            closeShadow = true;
        }
        if(doCallback !== false){
            doCallback = true;
        }


        if(overlayId){
            $('#'+overlayId).removeClass('open');
            if($(th_overlay.selector_overlay+'.open').length == 0 && closeShadow){
                $(th_overlay.selector_overlay_shadow).removeClass('open');
            }
        }else{
            $(th_overlay.selector_overlay+'.open').removeClass('open');
            if(closeShadow){
                $(th_overlay.selector_overlay_shadow).removeClass('open');
            }
        }

        if(doCallback){
            $.each(th_overlay.callbackClose,function(k,callback){
                callback(overlayId);
            });
        }

    }

};

th_overlay.init();
var ThuriaStickyScrollableEL = document;
// Thuria Sticky
(function ( $ ) {

    var instances = [];

    var defaultSettings = {
        top: 0,
        bottom: 0,
        scrollWindow: ThuriaStickyScrollableEL,
    };

    var generateInstance = function(el){

        instances.push({
            settings:{},
            parent:$(el).parent(),
            el:el,
            $el:$(el),
            offset:$(el).offset(),
            state:0,
            isStart:false
        });

        $(el).attr('data-instance',instances.length-1);
        return instances.length-1;
    };

    var getInstance = function(el){
        var instanceId = el.getAttribute('data-instance');
        if(instanceId || instanceId === 0 || instanceId === "0"){
            return instanceId;
        }else{
            return generateInstance(el);
        }
    }

    var updateParent = function(i){
        instances[i].parent.$el = instances[i].$el.parent();
        instances[i].parent.offset = instances[i].parent.$el.offset();
        instances[i].parent.width = instances[i].parent.$el.width();
        instances[i].parent.height = instances[i].parent.$el.outerHeight();
    };

    var handleScrollEvent = function(e){
        var st = $(ThuriaStickyScrollableEL).scrollTop();
        $.each(instances,function(i,instance){
            if(st > instance.presCalcTop){
                if(st < instance.parent.offset.top+instance.parent.height-instance.$el.outerHeight()-instance.settings.top-instance.settings.bottom){
                    stick(instance);
                }else{
                    stickBottom(instance);
                }
            }else{
                unstick(instance);
            }
        });

    };

    var stick = function(instance){
        if(instance.state != 1){
            instance.state = 1;

            instance.$el
                .addClass('stick')
                .removeClass('stick-bottom')
                .css({top:instance.settings.top,width:instance.parent.width,bottom:instance.settings.bottom,position:'fixed'});
        }
    };

    var stickBottom = function(instance){
        if(instance.state != 2){
            instance.state = 2;

            instance.$el
                .addClass('stick-bottom')
                .css({top:'auto',bottom:instance.settings.bottom,width:instance.parent.width,position:'absolute'});

        }
    };

    var unstick = function(instance){
        if(instance.state != 0){
            instance.state = 0;
            if(instance.$el){
                instance.$el
                    .removeClass('stick-bottom stick')
                    .css({top:0,bottom:'auto',width:instance.parent.width,position:'absolute'});

            }
        }
    };


    var optionalMethod = {
        update: function(el,options){

            var i = getInstance(el);

            instances[i].settings = $.extend(instances[i].settings,options);

            updateParent(i);

            instances[i].presCalcTop = instances[i].offset.top-instances[i].settings.top;
            //instances[i].presCalcParentBottom = instances[i].parent.offset.top+instances[i].parent.height-document.body.clientHeight;
            //instances[i].presCalcParentBottom = instances[i].parent.offset.top+instances[i].parent.height-instances[i].$el.outerHeight()-instances[i].settings.top+-instances[i].settings.bottom;

            handleScrollEvent();
        },
        stop: function(el){

            var i = getInstance(el);

            unstick(i);
            instances[i].isStart = false;
        }
    };


    $.fn.stick = function( options, params ) {

        $(this).each(function(){

            if(typeof options == 'string'){
                optionalMethod[options](this,params);
            }else{
                if(options != 'stop'){

                    var i = getInstance(this);
                    instances[i].isStart = true;
                    instances[i].settings = $.extend(false,defaultSettings,options);


                    updateParent(i);

                    instances[i].presCalcTop = instances[i].offset.top-instances[i].settings.top;

                    setTimeout(function(){
                        updateParent(i);
                    },500);

                }
            }
        });


        return this;
    };


    $.stickListenScroll = function(newEl) {
        if(newEl && newEl != ThuriaStickyScrollableEL){
            $(ThuriaStickyScrollableEL).off('scroll',handleScrollEvent);
            ThuriaStickyScrollableEL = newEl;
            $(ThuriaStickyScrollableEL).on('scroll',handleScrollEvent);
            setTimeout(handleScrollEvent,100);
        }
    }

    $(ThuriaStickyScrollableEL).on('scroll',handleScrollEvent);
    setTimeout(handleScrollEvent,100);

}( jQuery ));
/**
 * Ajout de https à youtube viméo et dailymotion
 * Autoplay vidéo par defaut a true au lieu de false
 * Mise en place de html5 pour les mp4 et autoplay dessus
 */

var scr=document.getElementsByTagName('script');
var zoombox_path = scr[scr.length-1].getAttribute("src").replace('zoombox.js','');

(function($){

    var options = {
        theme       : 'zoombox',      //available themes : zoombox,lightbox, prettyphoto, darkprettyphoto, simple
        opacity     : 0.8,                  // Black overlay opacity
        duration    : 200,                // Animation duration
        animation   : true,             // Do we have to animate the box ?
        width       : 600,                  // Default width
        height      : 400,                  // Default height
        gallery     : true,                 // Allow gallery thumb view
        autoplay : true,                // Autoplay for video
        overflow  : false               // Allow images bigger than screen ?
    }
    var images;         // Gallery Array [gallery name][link]
    var elem;           // HTML element currently used to display box
    var isOpen = false; // Zoombox already opened ?
    var link;           // Shortcut for the link
    var width;
    var height;
    var timer;          // Timing for img loading
    var i = 0;          // iteration variable
    var content;        // The content of the box
    var type = 'multimedia'; // Content type
    var position = false;
    var imageset = false;
    var state = 'closed';

    /**
     * You can edit the html code generated by zoombox for specific reasons.
     * */
    var html = '<div id="zoombox"> \
            <div class="zoombox_mask"></div>\
            <div class="zoombox_container">\
                <div class="zoombox_content"></div>\
                <div class="zoombox_title"></div>\
                <div class="zoombox_next"></div>\
                <div class="zoombox_prev"></div>\
                <div class="zoombox_close"></div>\
            </div>\
            <div class="zoombox_gallery"></div>\
        </div>';
// Regular expressions needed for the content
    var filtreImg=                  /(\.jpg)|(\.jpeg)|(\.bmp)|(\.gif)|(\.png)/i;
    var filtreMP3=			/(\.mp3)/i;
    var filtreFLV=			/(\.flv)/i;
    var filtreSWF=			/(\.swf)/i;
    var filtreQuicktime=	/(\.mov)|(\.mp4)/i;
    var filtreWMV=			/(\.wmv)|(\.avi)/i;
    var filtreDailymotion=	/(http:\/\/www.dailymotion)|(http:\/\/dailymotion)/i;
    var filtreVimeo=		/(http:\/\/www.vimeo)|(http:\/\/vimeo)/i;
    var filtreYoutube=		/(youtube\.)/i;
    var filtreKoreus=		/(http:\/\/www\.koreus)|(http:\/\/koreus)/i;
    var galleryLoaded = 0;

    $.zoombox = function(el,options) {

    }
    $.zoombox.options = options;
    $.zoombox.close = function() {
        close();
    }
    $.zoombox.open = function(tmplink,opts){
        elem = null;
        link = tmplink;
        options = $.extend({},$.zoombox.options,opts);
        load();
    }
    $.zoombox.html = function(cont,opts){
        content = cont;
        options = $.extend({},$.zoombox.options,opts);
        width = options.width;
        height = options.height;
        elem = null;
        open();
    }
    $.fn.zoombox = function(opts){

        images = new Array(); // allow multiple call on one page, for content loaded from ajax

        /**
         * Bind the behaviour on every Elements
         */
        return this.each(function(){
            // No zoombox for IE6
            if($.browser && $.browser.msie && $.browser.version < 7 && !window.XMLHttpRequest){
                return false;
            }
            var obj = this;
            var galleryRegExp =  /zgallery([0-9]+)/;
            var skipRegExp =  /zskip/;
            var gallery = galleryRegExp.exec($(this).attr("class"));
            var skip = skipRegExp.exec($(this).attr("class"));
            var tmpimageset = false;
            if(gallery != null){
                if(!images[gallery[1]]){
                    images[gallery[1]]=new Array();
                }
                if (skip == null) {
                    images[gallery[1]].push($(this));
                }
                var pos = images[gallery[1]].length-1;
                tmpimageset = images[gallery[1]];
            }
            $(this).off('click').click(function(){
                options = $.extend({},$.zoombox.options,opts);
                if(state!='closed') return false;
                if (skip != null) {
                    if (pos < images[gallery[1]].length-1) {
                        elem = images[gallery[1]][pos+1];
                    }
                } else {
                    elem = $(obj);
                }
                imageset = tmpimageset;
                link = elem.attr('href');
                position = pos;
                load();
                return false;
            });
        });
    }

    /**
     * Load the content (with or without loader) and call open()
     * */
    function load(){
        if(state=='closed') isOpen = false;
        state = 'load';
        setDim();
        if(filtreImg.test(link)){
            img=new Image();
            img.src=link;
            $("body").append('<div id="zoombox_loader"></div>');
            $("#zoombox_loader").css("marginTop",scrollY());
            timer = window.setInterval(function(){loadImg(img);},100);
        }else{
            setContent();
            open();
        }
    }

    /**
     * Build the HTML Structure of the box
     * */
    function build(){
        // We add the HTML Code on our page
        $('body').append(html);
        $(window).keydown(function(event){
            shortcut(event.which);
        });
        $(window).resize(function(){
            resize();
        });
        $('#zoombox .zoombox_mask').hide();
        // We add a specific class to define the box theme
        $('#zoombox').addClass(options.theme);
        // We bind the close behaviour (click on the mask / click on the close button)
        $('#zoombox .zoombox_mask,.zoombox_close').click(function(){
            close();
            $('.zoombox_close').hide();
            return false;
        });
        // Next/Prev button
        if(imageset == false){
            $('#zoombox .zoombox_next,#zoombox .zoombox_prev').remove();
        }else{
            $('#zoombox .zoombox_next').click(function(){
                next();
            });
            $('#zoombox .zoombox_prev').click(function(){
                prev();
            });
        }
    }

    /**
     *   Gallery System (with slider if too much images)
     */
    function gallery(){
        var loaded = 0;
        var width = 0;
        var contentWidth = 0;
        if(options.gallery){
            if(imageset === false){
                $('#zoombox .zoombox_gallery').remove();
                return false;
            }
            for(var i in imageset){
                var imgSrc = '';
                var img = $('<img src="'+imgSrc+'" class="video gallery'+(i*1)+'"/>');
                if(filtreImg.test(imageset[i].attr('href'))){
                    imgSrc = imageset[i].attr('href')
                    img = $('<img src="'+imgSrc+'" class="gallery'+(i*1)+'"/>');
                }
                img.data('id',i).appendTo('#zoombox .zoombox_gallery')
                img.click(function(){
                    gotoSlide($(this).data('id'));
                    $('#zoombox .zoombox_gallery img').removeClass('current');
                    $(this).addClass('current');
                });
                if(i==position){ img.addClass('current'); }

                // Listen the loading of Images
                $("<img/>").data('img',img).attr("src", imgSrc).on("load",(function() {
                    loaded++;
                    var img = $(this).data('img');
                    img.width(Math.round(img.height() * this.width/this.height));
                    if(loaded == $('#zoombox .zoombox_gallery img').length){
                        var width = 0;
                        $('#zoombox .zoombox_gallery img').each(function(){
                            width += $(this).outerWidth();
                            $(this).data('left',width);
                        });
                        var div = $('<div>').css({
                            position:'absolute',
                            top:0,
                            left:0,
                            width: width
                        });
                        $('#zoombox .zoombox_gallery').wrapInner(div);
                        contentWidth = $('#zoombox .zoombox_gallery').width();
                        $('#zoombox').trigger('change');
                    }
                }));
            }
            $('#zoombox .zoombox_gallery').show().animate({bottom:0},options.duration);
        }

        $('#zoombox').on('change',function(e,css){
            if($('#zoombox .zoombox_gallery div').width() < $('#zoombox .zoombox_gallery').width){
                return true;
            }
            var d = 0;
            var center = 0;
            if(css != null){
                d = options.duration;
                center = css.width / 2;
            }else{
                center = $('#zoombox .zoombox_gallery').width()/2;
            }
            var decal = - $('#zoombox .zoombox_gallery img.current').data('left') + $('#zoombox .zoombox_gallery img.current').width() / 2;
            var left = decal + center;
            if(left < center * 2 - $('#zoombox .zoombox_gallery div').width() ){
                left = center * 2 - $('#zoombox .zoombox_gallery div').width();
            }
            if(left > 0){
                left = 0;
            }
            $('#zoombox .zoombox_gallery div').animate({left:left},d);
        });

    }


    /**
     * Open the box
     **/
    function open(){
        if(isOpen == false) build(); else $('#zoombox .zoombox_title').empty();
        $('#zoombox .close').hide();
        $('#zoombox .zoombox_container').removeClass('multimedia').removeClass('img').addClass(type);

        // We add a title if we find one on the link
        if(elem != null && elem.attr('title')){
            $('#zoombox .zoombox_title').append(elem.attr('title'));
        }



        // And after... Animation or not depending of preferences
        // We empty the content
        $('#zoombox .zoombox_content').empty();
        // If it's an image we load the content now (to get a good animation)
        if(type=='img' && isOpen == false && options.animation == true){
            $('#zoombox .zoombox_content').append(content);
        }
        // Default position/size of the box to make the "zoom effect"
        if(elem != null && elem.find('img').length != 0 && isOpen == false){
            var min = elem.find('img');
            $('#zoombox .zoombox_container').css({
                width : min.width(),
                height: min.height(),
                top : min.offset().top,
                left : min.offset().left,
                opacity:0,
                marginTop : min.css('marginTop')
            });
        }else if(elem != null && isOpen == false){
            $('#zoombox .zoombox_container').css({
                width:   elem.width(),
                height:  elem.height(),
                top:elem.offset().top,
                left:elem.offset().left
            });
        }else if(isOpen == false){
            $('#zoombox .zoombox_container').css({
                width: 100,
                height: 100,
                top:windowH()/2-50,
                left:windowW()/2-50
            })
        }
        // Final position/size of the box after the animation
        var css = {
            width : width,
            height: height,
            left  : (windowW() - width) / 2,
            top   : (windowH() - height) / 2,
            marginTop : scrollY(),
            opacity:1
        };

        // Trigger the change event
        $('#zoombox').trigger('change',css);

        // Do we animate or not ?
        if(options.animation == true){
            $('#zoombox .zoombox_title').hide();
            $('#zoombox .zoombox_close').hide();
            $('#zoombox .zoombox_container').animate(css,options.duration,function(){
                if(type == 'multimedia' || isOpen == true){
                    $('#zoombox .zoombox_content').append(content);
                }
                if(type == 'image' || isOpen == true){
                    $('#zoombox .zoombox_content img').css('opacity',0).fadeTo(200,1);
                }
                $('#zoombox .zoombox_title').fadeIn(200);
                $('#zoombox .zoombox_close').fadeIn(200);
                state = 'opened';
                if(!isOpen){
                    gallery();
                }
                isOpen = true;
            });
            $('#zoombox .zoombox_mask').fadeTo(200,options.opacity);
        }else{
            $('#zoombox .zoombox_content').append(content);
            $('#zoombox .zoombox_close').show();
            $('#zoombox .zoombox_gallery').show();
            $('#zoombox .zoombox_container').css(css);
            $('#zoombox .zoombox_mask').show();
            $('#zoombox .zoombox_mask').css('opacity',options.opacity);
            if(!isOpen){
                gallery();
            }
            isOpen = true;
            state = 'opened';
        }
    }
    /**
     * Close the box
     * **/
    function close(){
        state = 'closing';
        window.clearInterval(timer);
        $(window).off('keydown');
        $(window).off('resize');
        if(type == 'multimedia'){
            $('#zoombox .zoombox_container').empty();
        }
        var css = {};
        if(elem != null && elem.find('img').length > 0){
            var min = elem.find('img');
            css ={
                width : min.width(),
                height: min.height(),
                top : min.offset().top,
                left : min.offset().left,
                opacity:0,
                marginTop : min.css('marginTop')
            };
        }else if(elem!=null){
            css = {
                width:   elem.width(),
                height:  elem.height(),
                top:elem.offset().top,
                left:elem.offset().left,
                marginTop:0,
                opacity:0
            };
        }else{
            css = {
                width: 100,
                height: 100,
                top:windowH()/2-50,
                left:windowW()/2-50,
                opacity : 0
            };
        }
        if(options.animation == true){
            $('#zoombox .zoombox_mask').fadeOut(200);
            $('#zoombox .zoombox_gallery').animate({bottom:-$('#zoombox .zoombox_gallery').innerHeight()},options.duration);
            $('#zoombox .zoombox_container').animate(css,options.duration,function(){
                $('#zoombox').remove();
                state = 'closed';
                isOpen = false;
            });
        }else{
            $('#zoombox').remove();
            state = 'closed';
            isOpen = false;
        }
    }

    /**
     * Set the HTML Content of the box
     * */
    function setContent(){
        // Overtflow
        if(options.overflow == false){
            if(width*1 + 50 > windowW()){
                height = (windowW() - 50) * height / width;
                width = windowW() - 50;
            }
            if(height*1 + 50 > windowH()){
                width = (windowH()-50) * width / height;
                height = windowH() - 50;
            }
        }
        var url = link;
        type = 'multimedia';
        if(filtreImg.test(url)){
            type = 'img';
            content='<img src="'+link+'" width="100%" height="100%"/>';
        }else if(filtreMP3.test(url)){
            width=300;
            height=40;
            content ='<object type="application/x-shockwave-flash" data="'+MP3Player+'?son='+url+'" width="'+width+'" height="'+height+'">';
            content+='<param name="movie" value="'+MP3Player+'?son='+url+'" /></object>';
        }else if(filtreFLV.test(url)){
            var autostart = 0;
            if(options.autoplay==true){ autostart = 1; }
            content='<object type="application/x-shockwave-flash" data="'+zoombox_path+'FLVplayer.swf" width="'+width+'" height="'+height+'">\
<param name="allowFullScreen" value="true">\
<param name="scale" value="noscale">\
<param name="wmode" value="transparent">\
<param name="flashvars" value="flv='+url+'&autoplay='+autostart+'">\
<embed src="'+zoombox_path+'FLVplayer.swf" width="'+width+'" height="'+height+'" allowscriptaccess="always" allowfullscreen="true" flashvars="flv='+url+'" wmode="transparent" />\
</object>';
        }else if(filtreSWF.test(url)){
            content='<object width="'+width+'" height="'+height+'"><param name="allowfullscreen" value="true" /><param name="allowscriptaccess" value="always" /><param name="movie" value="'+url+'" /><embed src="'+url+'" type="application/x-shockwave-flash" allowfullscreen="true" allowscriptaccess="always" width="'+width+'" height="'+height+'" wmode="transparent"></embed></object>';
        }else if(filtreQuicktime.test(url)){
            content='<embed src="'+url+'" width="'+width+'" height="'+height+'" controller="true" cache="true" autoplay="true"/>';
            // HTML5 Code
            content='<video controls autoplay src="'+url+'" width="'+width+'" height="'+height+'">Your browser does not support this format</video>'
        }else if(filtreWMV.test(url)){
            content='<embed src="'+url+'" width="'+width+'" height="'+height+'" controller="true" cache="true" autoplay="true" wmode="transparent" />';
        }else if(filtreDailymotion.test(url)){
            var id=url.split('_');
            id=id[0].split('/');
            id=id[id.length-1]+'?';
            if(options.autoplay==true){
                id = id + 'autoPlay=1&';
            }
            content='<iframe frameborder="0" width="'+width+'" height="'+height+'" src="https://www.dailymotion.com/embed/video/'+id+'?wmode=transparent"></iframe>';
        }else if(filtreVimeo.test(url)){
            var id=url.split('/');
            id=id[3]+'?';
            if(options.autoplay==true){
                id = id + 'autoplay=1&';
            }
            content='<iframe src="https://player.vimeo.com/video/'+id+'title=0&amp;byline=0&amp;portrait=0&amp;wmode=transparent" width="'+width+'" height="'+height+'" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>'
        }else if(filtreYoutube.test(url)){
            var id=url.split('watch?v=');
            id=id[1].split('&');
            id=id[0]+'?';
            if(options.autoplay==true){
                id = id + 'autoplay=1&';
            }
            content='<iframe width="'+width+'" height="'+height+'" src="https://www.youtube-nocookie.com/embed/'+id+'wmode=Opaque" frameborder="0" allowfullscreen></iframe>';
        }else if(filtreKoreus.test(url)){
            url=url.split('.html');
            url=url[0];
            content='<object type="application/x-shockwave-flash" data="'+url+'" width="'+width+'" height="'+height+'"><param name="movie" value="'+url+'"><embed src="'+url+'" type="application/x-shockwave-flash" width="'+width+'" height="'+height+'"  wmode="transparent"></embed></object>';
        }else{
            content='<iframe src="'+url+'" width="'+width+'" height="'+height+'" border="0"></iframe>';
        }
        return content;
    }
    /**
     *  Handle Image loading
     **/
    function loadImg(img){
        if(img.complete){
            i=0;
            window.clearInterval(timer);
            width=img.width;
            height=img.height;
            $('#zoombox_loader').remove();
            setContent();
            open();
        }
        // On anim le loader
        $('#zoombox_loader').css({'background-position': "0px "+i+"px"});
        i=i-40;
        if(i<(-440)){i=0;}
    }

    function gotoSlide(i){
        if(state != 'opened'){ return false; }
        if (imageset) {
            position = i;
            elem = imageset[position];
            link = elem.attr('href');
            if($('#zoombox .zoombox_gallery img').length > 0){
                $('#zoombox .zoombox_gallery img').removeClass('current');
                $('#zoombox .zoombox_gallery img:eq('+i+')').addClass('current');
            }
            load();
        }
        return false;
    }

    function next(){
        i = position+1;
        if(i  > imageset.length-1){
            i = 0;
        }
        gotoSlide(i);
    }

    function prev(){
        i = position-1;
        if(i < 0){
            i = imageset.length-1;
        }
        gotoSlide(i);
    }

    /**
     * GENERAL FUNCTIONS
     * */
    /**
     * Resize
     **/
    function resize(){
        $('#zoombox .zoombox_container').css({
            top : (windowH() - $('#zoombox .zoombox_container').outerHeight(true)) / 2,
            left : (windowW() - $('#zoombox .zoombox_container').outerWidth(true)) / 2
        });
    }
    /**
     * Keyboard Shortcut
     **/
    function shortcut(key){
        if(key == 37){
            prev();
        }
        if(key == 39){
            next();
        }
        if(key == 27){
            close();
        }

    }
    /**
     * Parse Width/Height of a link and insert it in the width and height variables
     * */
    function setDim(){
        width = options.width;
        height = options.height;
        if(elem!=null){
            var widthReg = /w([0-9]+)/;
            var w = widthReg.exec(elem.attr("class"));
            if(w != null){
                if(w[1]){
                    width = w[1];
                }
            }
            var heightReg = /h([0-9]+)/;
            var h = heightReg.exec(elem.attr("class"));
            if(h != null){
                if(h[1]){
                    height = h[1];
                }
            }
        }
        return false;
    }
    /**
     * Return the window height
     * */
    function windowH(){
        if (window.innerHeight) return window.innerHeight  ;
        else{return $(window).height();}
    }

    /**
     * Return the window width
     * */
    function windowW(){
        if (window.innerWidth) return window.innerWidth  ;
        else{return $(window).width();}
    }

    /**
     *  Return the position of the top
     *  */
    function scrollY() {
        scrOfY = 0;
        if( typeof( window.pageYOffset ) == 'number' ) {
            //Netscape compliant
            scrOfY = window.pageYOffset;
        } else if( document.body && ( document.body.scrollTop ) ) {
            //DOM compliant
            scrOfY = document.body.scrollTop;
        } else if( document.documentElement && ( document.documentElement.scrollTop ) ) {
            //IE6 standards compliant mode
            scrOfY = document.documentElement.scrollTop;
        }
        return scrOfY;
    }

    /**
     *  Return the position of the left scroll
     *  */
    function scrollX(){
        scrOfX = 0;
        if( typeof( window.pageXOffset ) == 'number' ) {
            //Netscape compliant
            scrOfX = window.pageXOffset;
        } else if( document.body && ( document.body.scrollLeft ) ) {
            //DOM compliant
            scrOfX = document.body.scrollLeft;
        } else if( document.documentElement && ( document.documentElement.scrollLeft ) ) {
            //IE6 standards compliant mode
            scrOfX = document.documentElement.scrollLeft;
        }
        return scrOfX;
    }

})(jQuery);
// Scroll To Element
$('.ops-scrollto a').click(function (e) {
    e.preventDefault();
    $(this).parents('nav').find('a').removeClass('ops-active');
    $(this).addClass('ops-active');
    var dest = $(this).attr('href');
    var finalPos = $(dest).offset().top;
    $('html,body').animate({scrollTop:finalPos - 100},300);  
});
tarteaucitron.init({
    "hashtag": "#rgpd", /* Ouverture automatique du panel avec le hashtag */
    "highPrivacy": false, /* désactiver le consentement implicite (en naviguant) ? */
    "orientation": "bottom", /* le bandeau doit être en haut (top) ou en bas (bottom) ? */
    "adblocker": false, /* Afficher un message si un adblocker est détecté */
    "showAlertSmall": false, /* afficher le petit bandeau en bas à droite ? */
    "cookieslist": false, /* Afficher la liste des cookies installés ? */
    "removeCredit": false /* supprimer le lien vers la source ? */
});

// Ajouter ici tous les services susceptibles de déposer des cookies (cf https://opt-out.ferank.eu/fr/install/)
// pour les services nécéssitants des modifs du code html, se referer à la classe ThuriaAuCitron

// Google map (initialisé dans th_maps)


// analytics
// tarteaucitron.user.gtagUa = 'UA-117118653-1';
// tarteaucitron.user.gtagMore = function () { /* add here your optionnal gtag() */ };
// (tarteaucitron.job = tarteaucitron.job || []).push('gtag');

// médias
(tarteaucitron.job = tarteaucitron.job || []).push('youtube');
(tarteaucitron.job = tarteaucitron.job || []).push('vimeo');
(tarteaucitron.job = tarteaucitron.job || []).push('dailymotion');



function CitronVideoHtml(id, plateforme, autoplay, mute) {
    return '<div class="tac_player ' + plateforme + '_player" videoID="' + id + '" width="1280px" theme="dark" height="auto" showinfo="0" controls="0" rel="0" autoplay=' + autoplay +'" mute="' + mute + '"></div>';
}
function isTouchDevice() {
    return 'ontouchstart' in document.documentElement;
}

var Ww = $(window).width();

if (isTouchDevice() && Ww < 1280) {
   	$('#ops-wrapper').addClass('no-hover');
}
var thVheight = {

    height: 0,

    init: function(){
        $(window).on('resize',thVheight.update);
        thVheight.update();
    },
    update: function(){
        if(thVheight.detectChange()){
            $('[data-vheight]').each(function(){
                var vheight = this.getAttribute('data-vheight');
                var vheightPercent;
                var vheightMoin = 0;
                if(vheight.match(/\ \-\ /)){
                    var splitted = vheight.split(' - ');
                    vheightPercent = parseInt(splitted[0]);
                    vheightMoin = parseInt(splitted[1]);
                }else{
                    vheightPercent = parseInt(vheight);
                }
                var newHeight = ((thVheight.height/100)*vheightPercent) - vheightMoin;

                $(this).height(newHeight);
            });
        }
    },
    detectChange: function(){

        var newH = document.body.clientHeight;

        if((newH - thVheight.height) > 150 || (thVheight.height - newH) > 150){

            thVheight.height = newH;

            return true;
        }

        return false;
    }

};
thVheight.init();
if ($('.ops-search-form').length > 0) {
    $formSearch = $('.ops-search-form');
    $openForm = $('.ops-header-right .link-search');
    $closeForm = $('.ops-search-form a');
    $inputForm = $('.ops-search-form input');

    $openForm.on('click', function (e) {
        e.preventDefault();
        $formSearch.addClass('ops-open');
        $inputForm.focus();
    });


    $closeForm.on('click', function (e) {
        e.preventDefault();
        $formSearch.removeClass('ops-open');
    })


}
// Ouverture du menu mobile
$('.ops-burger-menu').click(function () {
    if (!$(this).hasClass('ops-open')) {
        $(this).addClass('ops-open');
        // $('body').css('overflow','hidden');
        $('#ops-header').addClass('ops-menu-open');
    }
    else {
        $(this).removeClass('ops-open');
        $('#ops-header').removeClass('ops-menu-open');
        $(this).addClass('ops-closing');
        // $('body').css('overflow','auto');
        setTimeout(function(){
            $('.ops-burger-menu').removeClass('ops-closing');
        },500);
    }
});



/* Detect the scroll of the page and animate the menu */
$(window).on('scroll', function (e) {
    var st = $(this).scrollTop();

    if (st > 100) {
        $("#ops-wrapper").addClass("is-scrolled");
    }
    else {
        $("#ops-wrapper").removeClass("is-scrolled");
    }
});



// Ouverture - Fermeture de la DropDown Menu en accordéon
var $lienNav = $('.ops-wrapper-nav nav > ul > li.ops-has-sub-menu > a');
$lienNav.each(function(){
    $(this).on('click',function(e){
        e.preventDefault();
        var $otherSubOpened = $('.ops-wrapper-nav nav > ul > li.ops-has-sub-menu.ops-sub-menu-open').first();
        var $ulOpen = $('.ops-wrapper-nav nav > ul > li.ops-has-sub-menu.ops-sub-menu-open > ul').first();
        var $parent = $(this).parents('.ops-has-sub-menu').first();
        if($otherSubOpened[0] != $parent[0]){
            if($otherSubOpened){
                $otherSubOpened.removeClass('ops-sub-menu-open');
                $ulOpen.removeClass('in');
            }
            setTimeout(function(){
                $parent.addClass('ops-sub-menu-open');
            },50);
        }else{
            $otherSubOpened.removeClass('ops-sub-menu-open');
            // $ulOpen.removeClass('in');
            $otherSubOpened = null;
        }
    });
});


// [MOVE MENU TOP] - À 980px et inférieur, on déplace le header-top en bas de la nav wrapper-nav
var HeaderTopPosition = 0;
var $headerLeft = $('.ops-header-left .ops-wrapper-btn');
var $headerRight = $('.ops-header-right .ops-social');
if($headerLeft.length > 0 || $headerRight.length > 0){
    moveTheStickyContainerAside();
    $(window).on('resize',function(){
        moveTheStickyContainerAside();
    });
    $(window).on('orientationchange',moveTheStickyContainerAside());
}

function moveTheStickyContainerAside(){
    if(document.body.clientWidth < 980 && HeaderTopPosition == 0){
        $('.ops-wrapper-nav nav').prepend($headerLeft);
        $('.ops-wrapper-nav nav').append($headerRight);
        HeaderTopPosition = 1;
    }
    else if(document.body.clientWidth > 980 && HeaderTopPosition == 1){
        $('header#ops-header .ops-header-left').append($headerLeft);
        $('header#ops-header .ops-header-right').prepend($headerRight);
        HeaderTopPosition = 0;
    }
}
// Si User et sur IE / EDGE alors on ajoute la classe IE au body
if (navigator.userAgent.match(/trident/gi) || navigator.appName == 'Microsoft Internet Explorer'){
    $('#ops-wrapper').addClass('ops-ie');
}

// Lancement du script Librairie Zoombox
$('.zoombox').zoombox();

// Lancement du script de ObjectFit
objectFitImages('.fit-cover img');
// Slider Basic
// ==========================================================================
$('.slick-basic-slider').each(function(){
    $(this).slick({
        infinite: true,
        dots: true,
        arrows: true,
        appendArrows: $('.ops-slick-nav'),
        appendDots: $('.ops-slick-nav'),
        autoplay: true,
        autoplaySpeed: 2000
    });
});


// Slider Cards
// ==========================================================================
$('.slick-cards-slider').each(function () {
    $(this).slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 1,
        slidesToScroll: 1,
        centerMode: false,
        variableWidth: true,
        responsive: [
            {
                breakpoint: 600,
                settings: {
                    variableWidth: false
                }
            }
        ]
    });
});


// Slider Distribution - Détail CONCERT
// ==========================================================================
$('.slick-distribution').each(function () {
    $(this).slick({
        dots: false,
        arrows: true,
        slidesToShow: 1,
        slidestoScroll: 1,
        slidesPerRow: 3,
        rows: 2,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    slidesPerRow: 2
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    slidesPerRow: 1
                }
            }
        ]
    });
});



// Slider Actualités - Détail CONCERT
// ==========================================================================
$('.slick-actus').each(function(){
    $(this).slick({
        infinite: false,
        dots: false,
        arrows: true,
        autoplay: true,
        autoplaySpeed: 2000
    });
});
if($('.ops-wrapper-content-rh').length > 0) {


    var isInitStick = false;

    // Si nous sommes sur le détail SIT ou détail Rando
    if ($('.ops-wrapper-sticky').length > 0) {

        console.log('oko');

        var $elSticky = $('.ops-wrapper-content-rh .ops-wrapper-sticky');



        if(document.body.clientHeight-120 > $elSticky.outerHeight()){

            if (!isTabletPortraitOrSmalller()) {
                $elSticky.stick({top: 100});
                isInitStick = true;
            }

            $(window).on('resize', function () {
                console.log(isTabletPortraitOrSmalller());
                if (isTabletPortraitOrSmalller()) {
                    $elSticky.stick('stop');
                    $elSticky.attr('style', '');
                } else {
                    if (isInitStick) {
                        $elSticky.stick('update');
                    } else {
                        isInitStick = true;
                        setTimeout(function () {
                            $elSticky.stick({top: 100});
                        }, 500);
                    }
                }
            });

        }


    }

}
// Récupère les valeurs dans la dropdown

var thFacettes = {

    states: {},

    init: function(){

        $('.ops-facette-checkbox').each(function(){
            var facetteFilterEl = this;
            var facetteName = $(this).find('input');

            // console.log(facetteName);

            setTimeout(function(){
                $('a.selected',facetteFilterEl).off('click').on('click',function(e){
                    e.preventDefault();
                    e.stopPropagation();
                    $(this).html('');
                    thFacettes.states[facetteName] = '';
                    facetteName.removeAttr('checked');
                });
            },250);

            thFacettes.states[facetteName] = '';

            $('input',this).on('click', function (e) {
                var newValue =  $(this).val();

                $(this).parents('.ops-facette-checkbox').find('a.selected').html($(this).parent().text());


                $('.ops-facette-checkbox').removeClass('ops-open');

                if(newValue != thFacettes.states[facetteName] && newValue != ''){
                    thFacettes.states[facetteName] = newValue;
                }else{
                    thFacettes.states[facetteName] = '';
                }
            });
        });

    }
};

thFacettes.init();
$('form select').selectric();


// Ajoute une class à la balise form pour annuler le curseur qui clignotte
$('.ops-group-email input').focusin(function () {
    $(this).parents('form:first').addClass('ops-focused');
});


// Quand on enlève le focus, si rien n'est écrit dans le input on ferme le volet
$('.ops-group-email input').focusout(function () {
    if ($(this).val().length < 1) {
        $(this).parents('form:first').removeClass('ops-focused');
    }
});


// A chaque focus
$('.ops-group-email input').focus(function(){
    if($(this).val().length > 0){
        $(this).parent().parent().addClass('ops-nl-open');
    }
    if($('.ops-group-checkbox input').is(':checked')){
        $(this).parent().parent().addClass('ops-nl-open');
    }
    else{
        $(this).parent().parent().removeClass('ops-nl-open');
    }
});


// A chaque check d'un des input, je test s'il y en a au moins 1 en checked. Si 0 alors on ferme le volet.
$('.ops-group-checkbox input').change(function(){
   if($('.ops-group-checkbox input').is(':checked')){
       $(this).parents('form:first').addClass('ops-nl-open');
   }
   else{
       $(this).parents('form:first').removeClass('ops-nl-open');
   }
});
$('.ops-bloc-masonry').masonry({
    // options
    itemSelector: '.ops-col-33'
});
// Bloc video
$('.ops-wrapper-video').each(function () {
    var mask = $('.mask-video', this);
    var vidContainer = $('.embed-container', this);

    var idvideo = vidContainer.data('video_id');
    var plateforme = vidContainer.data('video_plateforme');

    if (typeof plateforme != 'undefined') {
        var html = CitronVideoHtml(idvideo, plateforme, 1, 0);


        if (mask.length > 0) {
            $(mask).click(function (e) {
                e.preventDefault();
                vidContainer.append(html);

                elem = vidContainer.find('.' + plateforme + '_player');
                elem = elem[0];

                if (tarteaucitron.serviceAllowed(plateforme) == true) {
                    elem.innerHTML = tarteaucitron.services[plateforme].jsOnElement(elem);
                } else {
                    tarteaucitron.services[plateforme].fallback();
                }

                setTimeout(function () {
                    mask.addClass('hide');

                    setTimeout(function () {
                        mask.remove();
                    }, 500);
                }, 200);
            });
        } else {
            vidContainer.append(html);

            elem = vidContainer.find('.' + plateforme + '_player');
            elem = elem[0];

            if (tarteaucitron.serviceAllowed(plateforme) == true) {
                elem.innerHTML = tarteaucitron.services[plateforme].jsOnElement(elem);
            } else {
                tarteaucitron.services[plateforme].fallback();
            }
        }
    }
});
