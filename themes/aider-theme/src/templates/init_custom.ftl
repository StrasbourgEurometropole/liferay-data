<#assign the_title_OG = "" />

<#if layout.getHTMLTitle(locale)??>
	<#assign the_title_OG = layout.getHTMLTitle(locale) />
</#if>

<#if pageTitle??>
	<#assign the_title_OG = pageTitle />
</#if>

<#if pageSubtitle??>
	<#assign the_title_OG = pageSubtitle + " - " + the_title_OG />
</#if>

<#if tilesTitle?has_content>
	<#assign the_title_OG = languageUtil.get(locale, tilesTitle) />
</#if>

<#if page_group.isLayoutPrototype()>
	<#assign the_title_OG = page_group.getDescriptiveName(locale) />
</#if>

<#if !tilesTitle?has_content>
	<#assign the_title_OG = htmlUtil.escape(the_title_OG) />
</#if>