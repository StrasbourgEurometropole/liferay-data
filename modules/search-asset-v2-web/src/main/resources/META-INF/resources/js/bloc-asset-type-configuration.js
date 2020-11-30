var namespace = '_com_liferay_portlet_configuration_web_portlet_PortletConfigurationPortlet_';

var blocAssetType = "" +
"<div class='card-horizontal main-content-card'>" +
	"<div aria-multiselectable='true' class='panel-group' role='tablist'>" +
        "<fieldset aria-labelledby='assetType%%INDEX%%Title' class='panel panel-default ' id='assetType%%INDEX%%' role='group'>" +
		    "<div class='panel-heading' id='assetType%%INDEX%%Header' role='presentation'>" +
			    "<div class='panel-title' id='assetType%%INDEX%%Title'>" +
                    "<a aria-controls='assetType%%INDEX%%Content' aria-expanded='true' class='collapse-icon collapse-icon-middle' data-toggle='collapse' href='#assetType%%INDEX%%Content' role='button'>" +
                        Liferay.Language.get('some-content') +
				        "<span class='collapse-icon-closed' id='uvlz____'>" +
				            "<svg class='lexicon-icon lexicon-icon-angle-right' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                            "<path class='lexicon-icon-outline' d='M396.394 255.607c-0.22-6.936-2.973-13.81-8.272-19.111l-227.221-227.221c-11.026-11.059-28.94-11.059-39.999 0-11.058 11.026-11.058 28.941 0 39.999l206.333 206.333c0 0-206.333 206.333-206.333 206.333-11.058 11.058-11.058 28.973 0 39.999 11.059 11.059 28.972 11.059 39.999 0l227.221-227.221c5.3-5.3 8.053-12.175 8.272-19.111z'></path>" +
                            "</svg>" +
                        "</span>" +
				        "<span class='collapse-icon-open' id='ored____'>" +
				            "<svg class='lexicon-icon lexicon-icon-angle-down' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                            "<path class='lexicon-icon-outline' d='M256 384c6.936-0.22 13.81-2.973 19.111-8.272l227.221-227.221c11.058-11.026 11.058-28.941 0-39.999-11.026-11.058-28.94-11.058-39.999 0l-206.333 206.333c0 0-206.333-206.333-206.333-206.333-11.059-11.058-28.973-11.058-39.999 0-11.059 11.058-11.059 28.972 0 39.999l227.221 227.221c5.3 5.3 12.174 8.053 19.111 8.272z'></path>" +
                            "</svg>" +
                        "</span>" +
                    "</a>" +
			    "</div>" +
		    "</div>" +
	        "<div aria-labelledby='assetType%%INDEX%%Header' class='panel-collapse collapse show' id='assetType%%INDEX%%Content' role='presentation' style=''>" +
		        "<div class='panel-body'>" +
		            "<input class='field form-control' id='" + namespace + "numAssetType%%INDEX%%' name='" + namespace + "numAssetType%%INDEX%%' type='hidden' value='%%INDEX%%'>" +
                    "<div class='form-group input-select-wrapper'>" +
	                    "<select class='form-control' id='" + namespace + "classname_%%INDEX%%' name='" + namespace + "classname_%%INDEX%%' onchange='reinitializeBloc(%%INDEX%%)' title='classname_%%INDEX%%'>" +
                            "<option class='' selected='' value='false'>" + Liferay.Language.get('select-asset-type') + "</option>";
                            $.each(assetTypeNames.split(','), function(i, assetTypeName) {
                                blocAssetType += "<option class='' value='" + assetTypeName + "'>" + Liferay.Language.get(assetTypeName) + "</option>";
                            });
blocAssetType += "" +
	                    "</select>" +
                    "</div>" +
		            "<button class='btn btn-icon icon icon-trash icon-2x btn-default' id='" + namespace + "epfa' onclick='deleteAssetType(%%INDEX%%);' type='button'></button>" +
                    "<!-- Template et URL -->" +
                    "<fieldset aria-labelledby='template-and-url%%INDEX%%Title' class='panel panel-default ' id='template-and-url%%INDEX%%' role='group'>" +
		                "<div class='panel-heading' id='template-and-url%%INDEX%%Header' role='presentation'>" +
			                "<div class='panel-title' id='template-and-url%%INDEX%%Title'>" +
						        "<a aria-controls='template-and-url%%INDEX%%Content' aria-expanded='true' class='collapse-icon collapse-icon-middle' data-toggle='collapse' href='#template-and-url%%INDEX%%Content' role='button'>" +
                                    Liferay.Language.get('template-and-url') +
				                    "<span class='collapse-icon-closed' id='fehs____'>" +
				                        "<svg class='lexicon-icon lexicon-icon-angle-right' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                                        "<path class='lexicon-icon-outline' d='M396.394 255.607c-0.22-6.936-2.973-13.81-8.272-19.111l-227.221-227.221c-11.026-11.059-28.94-11.059-39.999 0-11.058 11.026-11.058 28.941 0 39.999l206.333 206.333c0 0-206.333 206.333-206.333 206.333-11.058 11.058-11.058 28.973 0 39.999 11.059 11.059 28.972 11.059 39.999 0l227.221-227.221c5.3-5.3 8.053-12.175 8.272-19.111z'></path>" +
                                        "</svg>" +
                                    "</span>" +
				                    "<span class='collapse-icon-open' id='kwsw____'>" +
				                        "<svg class='lexicon-icon lexicon-icon-angle-down' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                                        "<path class='lexicon-icon-outline' d='M256 384c6.936-0.22 13.81-2.973 19.111-8.272l227.221-227.221c11.058-11.026 11.058-28.941 0-39.999-11.026-11.058-28.94-11.058-39.999 0l-206.333 206.333c0 0-206.333-206.333-206.333-206.333-11.059-11.058-28.973-11.058-39.999 0-11.059 11.058-11.059 28.972 0 39.999l227.221 227.221c5.3 5.3 12.174 8.053 19.111 8.272z'></path>" +
                                        "</svg>" +
                                    "</span>" +
						        "</a>" +
			                "</div>" +
		                "</div>" +
                        "<div aria-labelledby='template-and-url%%INDEX%%Header' class='panel-collapse collapse show' id='template-and-url%%INDEX%%Content' role='presentation' style=''>" +
		                    "<div class='panel-body'>" +
                                "<div class='form-group form-group-inline input-select-wrapper'>" +
                                    "<select class='form-control' id='" + namespace + "templateKey_%%INDEX%%' name='" + namespace + "templateKey_%%INDEX%%' title='template-key_%%INDEX%%'>" +
                                        "<option>" + Liferay.Language.get('select-a-template') + "</option>" +
                                    "</select>" +
                                "</div>" +
                                "<div class='form-group form-group-inline input-text-wrapper'>" +
				                    "<input class='field form-control' id='" + namespace + "friendlyUrl_%%INDEX%%' name='" + namespace + "friendlyUrl_%%INDEX%%' placeholder='" + Liferay.Language.get('detail-friendly-url') + "' title='friendly-url_%%INDEX%%' type='text' value=''>" +
	                            "</div>" +
		                    "</div>" +
	                    "</div>" +
                    "</fieldset>" +
                    "<!-- Portée -->" +
                    "<fieldset aria-labelledby='scope%%INDEX%%Title' class='panel panel-default ' id='scope%%INDEX%%' role='group'>" +
		                "<div class='panel-heading' id='scope%%INDEX%%Header' role='presentation'>" +
			                "<div class='panel-title' id='scope%%INDEX%%Title'>" +
						        "<a aria-controls='scope%%INDEX%%Content' aria-expanded='true' class='collapse-icon collapse-icon-middle' data-toggle='collapse' href='#scope%%INDEX%%Content' role='button'>" +
							        Liferay.Language.get('scope') +
				                    "<span class='collapse-icon-closed' id='ffek____'>" +
				                        "<svg class='lexicon-icon lexicon-icon-angle-right' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                                        "<path class='lexicon-icon-outline' d='M396.394 255.607c-0.22-6.936-2.973-13.81-8.272-19.111l-227.221-227.221c-11.026-11.059-28.94-11.059-39.999 0-11.058 11.026-11.058 28.941 0 39.999l206.333 206.333c0 0-206.333 206.333-206.333 206.333-11.058 11.058-11.058 28.973 0 39.999 11.059 11.059 28.972 11.059 39.999 0l227.221-227.221c5.3-5.3 8.053-12.175 8.272-19.111z'></path>" +
                                        "</svg>" +
                                    "</span>" +
				                    "<span class='collapse-icon-open' id='qcss____'>" +
				                        "<svg class='lexicon-icon lexicon-icon-angle-down' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                                        "<path class='lexicon-icon-outline' d='M256 384c6.936-0.22 13.81-2.973 19.111-8.272l227.221-227.221c11.058-11.026 11.058-28.941 0-39.999-11.026-11.058-28.94-11.058-39.999 0l-206.333 206.333c0 0-206.333-206.333-206.333-206.333-11.059-11.058-28.973-11.058-39.999 0-11.059 11.058-11.059 28.972 0 39.999l227.221 227.221c5.3 5.3 12.174 8.053 19.111 8.272z'></path>" +
                                        "</svg>" +
                                    "</span>" +
						        "</a>" +
			                "</div>" +
		                "</div>" +
	                    "<div aria-labelledby='scope%%INDEX%%Header' class='panel-collapse collapse show' id='scope%%INDEX%%Content' role='presentation' style=''>" +
		                    "<div class='panel-body'>" +
                                Liferay.Language.get('scope-explanations') +
                                "<select class='form-control' name='" + namespace + "scopeIds_%%INDEX%%' id='" + namespace + "scopeIds_%%INDEX%%' multiple onchange='reinitializePrefilter(%%INDEX%%)'>" +
                                    "<option placeholder>" + Liferay.Language.get('select-scopes') +"</option>" +
                                "</select>" +
		                    "</div>" +
	                    "</div>" +
                    "</fieldset>" +
                    "<!-- Préfiltre -->" +
                    "<fieldset aria-labelledby='prefilter%%INDEX%%Title' class='panel panel-default ' id='prefilter%%INDEX%%' role='group'>" +
		                "<div class='panel-heading' id='prefilter%%INDEX%%Header' role='presentation'>" +
			                "<div class='panel-title' id='prefilter%%INDEX%%Title'>" +
						        "<a aria-controls='prefilter%%INDEX%%Content' aria-expanded='false' class='collapse-icon collapse-icon-middle collapsed' data-toggle='collapse' href='#prefilter%%INDEX%%Content' role='button'>" +
                                    Liferay.Language.get('prefilter') +
				                    "<span class='collapse-icon-closed' id='ztie____'>" +
				                        "<svg class='lexicon-icon lexicon-icon-angle-right' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                                        "<path class='lexicon-icon-outline' d='M396.394 255.607c-0.22-6.936-2.973-13.81-8.272-19.111l-227.221-227.221c-11.026-11.059-28.94-11.059-39.999 0-11.058 11.026-11.058 28.941 0 39.999l206.333 206.333c0 0-206.333 206.333-206.333 206.333-11.058 11.058-11.058 28.973 0 39.999 11.059 11.059 28.972 11.059 39.999 0l227.221-227.221c5.3-5.3 8.053-12.175 8.272-19.111z'></path>" +
                                        "</svg>" +
                                    "</span>" +
				                    "<span class='collapse-icon-open' id='jrxp____'>" +
				                        "<svg class='lexicon-icon lexicon-icon-angle-down' focusable='false' role='presentation' viewBox='0 0 512 512'>" +
	                                        "<path class='lexicon-icon-outline' d='M256 384c6.936-0.22 13.81-2.973 19.111-8.272l227.221-227.221c11.058-11.026 11.058-28.941 0-39.999-11.026-11.058-28.94-11.058-39.999 0l-206.333 206.333c0 0-206.333-206.333-206.333-206.333-11.059-11.058-28.973-11.058-39.999 0-11.059 11.058-11.059 28.972 0 39.999l227.221 227.221c5.3 5.3 12.174 8.053 19.111 8.272z'></path>" +
                                        "</svg>" +
                                    "</span>" +
                                "</a>" +
			                "</div>" +
		                "</div>" +
	                    "<div aria-labelledby='prefilter%%INDEX%%Header' class='panel-collapse collapse' id='prefilter%%INDEX%%Content' role='presentation' style=''>" +
		                    "<div class='panel-body'>" +
		                        "<div id='prefilters-content%%INDEX%%'>" +
                                    "<input class='field form-control' id='" + namespace + "nbPrefiltre%%INDEX%%' name='" + namespace + "nbPrefiltre%%INDEX%%' type='hidden' value='1'>" +
                                    "<div id='prefilter%%INDEX%%_0'>" +
                                        "<input class='field form-control' id='" + namespace + "numPrefiltre%%INDEX%%_0' name='" + namespace + "numPrefiltre%%INDEX%%_0' type='hidden' value='0'>" +
                                        "<div class='form-group form-group-inline input-select-wrapper'>" +
                                            "<select class='form-control' id='" + namespace + "includeOrExclude_%%INDEX%%_0' name='" + namespace + "includeOrExclude_%%INDEX%%_0' title='include-or-exclude_%%INDEX%%_0'>" +
                                                "<option class='' selected='' value='contains'>" +
                                                    Liferay.Language.get('eu.search.asset.web.configuration.contains') +
                                                "</option>" +
                                                "<option class='' value='notContains'>" +
                                                    Liferay.Language.get('eu.search.asset.web.configuration.notContains') +
                                                "</option>" +
                                            "</select>" +
                                        "</div>" +
                                        "<div class='form-group form-group-inline input-select-wrapper'>" +
                                            "<select class='form-control' id='" + namespace + "allOrAny_%%INDEX%%_0' name='" + namespace + "allOrAny_%%INDEX%%_0' title='all-or-any_%%INDEX%%_0'>" +
                                                "<option class='' selected='' value='all'>" +
                                                    Liferay.Language.get('eu.search.asset.web.configuration.all') +
                                                "</option>" +
                                                "<option class='' value='any'>" +
                                                    Liferay.Language.get('eu.search.asset.web.configuration.any') +
                                                "</option>" +
                                            "</select>" +
                                        "</div>" +
                                        "<span class='form-group form-group-inline'>parmi</span>" +
                                        "<div class='form-group form-group-inline input-select-wrapper'>" +
                                            "<select class='form-control' id='" + namespace + "categoriesOrTags_%%INDEX%%_0' name='" + namespace + "%%INDEX%%_0' onchange='updatePrefilterChoices(this)' title='categories-or-tags_%%INDEX%%_0'>" +
                                                "<option class='' value='categories'>" +
                                                    Liferay.Language.get('eu.search.asset.web.configuration.categories') +
                                                "</option>" +
                                                "<option class='' selected='' value='tags'>" +
                                                    Liferay.Language.get('eu.search.asset.web.configuration.tags') +
                                                "</option>" +
                                            "</select>" +
                                        "</div>" +
                                        "<select class='form-control' name='" + namespace + "prefilterChoices_%%INDEX%%_0' id='" + namespace + "prefilterChoices_%%INDEX%%_0' multiple=''>" +
                                            "<option placeholder>" + Liferay.Language.get('select-prefilters') +"</option>" +
                                        "</select>" +
                                    "</div>" +
                                "</div>" +
                                "<button class='btn btn-icon icon icon-plus icon-2x btn-default' id='" + namespace + "famg' onclick='addPrefilter(%%INDEX%%);' type='button'></button>" +
		                    "</div>" +
	                    "</div>" +
                    "</fieldset>" +
		        "</div>" +
	        "</div>" +
        "</fieldset>" +
	"</div>" +
"</div>";