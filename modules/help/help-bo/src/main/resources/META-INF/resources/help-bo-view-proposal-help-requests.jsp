<%@ include file="/help-bo-init.jsp"%>

<%-- URL : definit le lien vers la page d'ajout/edition d'une entite --%>
<liferay-portlet:renderURL varImpl="editHelpRequestURL">
	<portlet:param name="cmd" value="editHelpRequest" />
	<portlet:param name="mvcPath" value="/help-bo-edit-help-request.jsp" />
	<portlet:param name="returnURL" value="/TODO" />
</liferay-portlet:renderURL>

<%-- Header --%>
<div class="navbar navbar-default collapse-basic-search" id="iqzh">
	<div class="container-fluid-1280">
		<div class="navbar-header visible-xs">
			<button class="collapsed navbar-toggle navbar-toggle-left navbar-toggle-page-name" data-target="#_eu_strasbourg_portlet_oidc_OIDCBOPortlet_navTag_1NavbarCollapse" data-toggle="collapse" id="_eu_strasbourg_portlet_oidc_OIDCBOPortlet_NavbarBtn" type="button">
			<span class="sr-only">Basculer la navigation</span>
			<span class="page-name">Utilisateurs Publik</span>
			<span class="caret"></span>
			</button>
		</div>
		<!-- Liste des onglet -->
		<div class="collapse navbar-collapse" id="_eu_strasbourg_portlet_oidc_OIDCBOPortlet_navTagNavbarCollapse">
			<ul aria-label="Gestion Utilisateurs Publik" class="lfr-nav nav navbar-nav" id="_eu_strasbourg_portlet_oidc_OIDCBOPortlet_navTag" role="menubar">
				<li class=" active " id="nraf_" role="presentation" style="margin: auto; position: inherit; font-weight: bold">
					<a class="" role="menuitem" title="Utilisateurs Publik">
					<span class="nav-item-label">
					Liste des demandes de la proposition d'aide : Couscous pour trois tous les dimanches midi
					</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<%-- Body --%>
<div class="container-fluid-1280 main-content-body">
	<form action="" class="form  " data-fm-namespace="_eu_strasbourg_portlet_help_HelpBOPortlet_" id="_eu_strasbourg_portlet_help_HelpBOPortlet_fm" method="post" name="_eu_strasbourg_portlet_help_HelpBOPortlet_fm">
		<input class="field form-control" id="_eu_strasbourg_portlet_help_HelpBOPortlet_formDate" name="_eu_strasbourg_portlet_help_HelpBOPortlet_formDate" type="hidden" value="1614077955201">
		<input class="field form-control" id="_eu_strasbourg_portlet_help_HelpBOPortlet_selectionIds" name="_eu_strasbourg_portlet_help_HelpBOPortlet_selectionIds" type="hidden" value="">
		<div class="hide main-content-body" id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainerEmptyResultsMessage">
			<div class="sheet taglib-empty-result-message">
				<div class="taglib-empty-result-message-header-has-plus-btn"></div>
				<div class="sheet-text text-center">
					Aucune entrée n'a été trouvée.
				</div>
			</div>
		</div>
		<div class="  lfr-search-container-wrapper  ">
			<div id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainer" class="yui3-widget component searchcontainer">
				<div id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainerSearchContainer" class="searchcontainer-content">
					<div class="table-responsive">
						<table class="show-quick-actions-on-hover table table-autofit table-heading-nowrap table-list" data-searchcontainerid="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainer">
							<thead>
								<tr>
									<th class="lfr-title-column" id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainer_col-title">
										Auteur de la demande
									</th>
									<th class="lfr-user-column content-column table-cell-content" id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainer_col-user">
										Description
									</th>
									<th class="lfr-modified-date-column content-column table-cell-content" id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainer_col-modified-date">
                                        <span class="truncate-text">
                                        Date de la demande
                                        </span>
                                    </th>
									<th class="lfr-entry-action-column " id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainer_col-8">
										&nbsp;
									</th>
								</tr>
							</thead>
							<tbody>
								<tr class="   " data-qa-id="row" data-selectable="true">
									<td class="lfr-description-column" colspan="1">
										<a href="${editHelpRequestURL}">Jean-Paul Belmondo</a>
									</td>
									<td class=" lfr-user-column table-cell-content lfr-title-column" colspan="1">
										<a href="${editHelpRequestURL}">Si vous voulez une scene type action pendant un repas, je suis votre homme de main ...</a>
									</td>
									<td class="content-column table-cell-content lfr-modified-date-column" colspan="1">
                                        <span class="truncate-text">
                                        <a href="${editHelpRequestURL}">23/02/2021 10:52</a>
                                        </span>
                                    </td>
									<td class=" lfr-entry-action-column" colspan="1">
										<div class="dropdown lfr-icon-menu ">
											<a class="direction-left dropdown-toggle icon-monospaced " href="javascript:;" id="_eu_strasbourg_portlet_help_HelpBOPortlet_kldx___menu" title="Actions">
												<span id="xdtv____">
													<svg class="lexicon-icon lexicon-icon-ellipsis-v" focusable="false" role="presentation" viewBox="0 0 512 512">
														<path class="lexicon-icon-outline ellipsis-v-dot-2" d="M319 255.5c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
														<path class="lexicon-icon-outline ellipsis-v-dot-3" d="M319 448c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
														<path class="lexicon-icon-outline ellipsis-v-dot-1" d="M319 64c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
													</svg>
												</span>
											</a>
											<script type="text/javascript">
												// <![CDATA[
												AUI().use('liferay-menu', function(A) {(function() {var $ = AUI.$;var _ = AUI._;
														Liferay.Menu.register('_eu_strasbourg_portlet_help_HelpBOPortlet_kldx___menu');
													})();});
												// ]]>
											</script>
											<ul class="dropdown-menu dropdown-menu-left">
												<li class="" role="presentation">
													<a href="${editHelpRequestURL}" target="_self" class=" lfr-icon-item taglib-icon" id="_eu_strasbourg_portlet_help_HelpBOPortlet_kldx______menu__edit" role="menuitem">
													<span class="taglib-text-icon">Consulter la demande</span>
													</a>
												</li>
												<li class="" role="presentation">
													<a href="javascript:;" target="_self" class=" lfr-icon-item taglib-icon" id="_eu_strasbourg_portlet_help_HelpBOPortlet_kldx______menu__delete" onclick="event.preventDefault();submitForm(document.hrefFm, 'http://vm19012:8080/group/entraide/~/control_panel/manage?p_p_id=eu_strasbourg_portlet_help_HelpBOPortlet&amp;p_p_lifecycle=1&amp;p_p_state=maximized&amp;p_p_mode=view&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_javax.portlet.action=deleteHelpProposal&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_tab=helpProposals&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_cmd=deleteHelpProposal&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalId=106276101&amp;p_auth=DmXjALY9')" role="menuitem" data-senna-off="true">
													<span class="taglib-text-icon">Consulter la proposition</span>
													</a>
												</li>
											</ul>
										</div>
									</td>
								</tr>
								<tr class="   " data-qa-id="row" data-selectable="true">
									<td class="content-column " colspan="1">
										<a href="${editHelpRequestURL}">Jean Gabin</a>
									</td>
									<td class=" lfr-user-column table-cell-content lfr-title-column" colspan="1">
										<a href="${editHelpRequestURL}">Bonjour, je suis etudiant en cinema et je souhaiterais m entretenir avec vous afin de ...</a>
									</td>
									<td class="content-column table-cell-content lfr-modified-date-column" colspan="1">
                                        <span class="truncate-text">
                                        <a href="${editHelpRequestURL}">23/02/2021 10:28</a>
                                        </span>
                                    </td>
									<td class=" lfr-entry-action-column" colspan="1">
										<div class="dropdown lfr-icon-menu ">
											<a class="direction-left dropdown-toggle icon-monospaced " href="javascript:;" id="_eu_strasbourg_portlet_help_HelpBOPortlet_tiym___menu" title="Actions">
												<span id="mhaw____">
													<svg class="lexicon-icon lexicon-icon-ellipsis-v" focusable="false" role="presentation" viewBox="0 0 512 512">
														<path class="lexicon-icon-outline ellipsis-v-dot-2" d="M319 255.5c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
														<path class="lexicon-icon-outline ellipsis-v-dot-3" d="M319 448c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
														<path class="lexicon-icon-outline ellipsis-v-dot-1" d="M319 64c0 35.346-28.654 64-64 64s-64-28.654-64-64c0-35.346 28.654-64 64-64s64 28.654 64 64z"></path>
													</svg>
												</span>
											</a>
											<script type="text/javascript">
												// <![CDATA[
												AUI().use('liferay-menu', function(A) {(function() {var $ = AUI.$;var _ = AUI._;
														Liferay.Menu.register('_eu_strasbourg_portlet_help_HelpBOPortlet_tiym___menu');
													})();});
												// ]]>
											</script>
											<ul class="dropdown-menu dropdown-menu-left">
												<li class="" role="presentation">
													<a href="${editHelpRequestURL}" target="_self" class=" lfr-icon-item taglib-icon" id="_eu_strasbourg_portlet_help_HelpBOPortlet_tiym______menu__edit" role="menuitem">
													<span class="taglib-text-icon">Consulter la demande</span>
													</a>
												</li>
												<li class="" role="presentation">
													<a href="javascript:;" target="_self" class=" lfr-icon-item taglib-icon" id="_eu_strasbourg_portlet_help_HelpBOPortlet_tiym______menu__delete" onclick="event.preventDefault();submitForm(document.hrefFm, 'http://vm19012:8080/group/entraide/~/control_panel/manage?p_p_id=eu_strasbourg_portlet_help_HelpBOPortlet&amp;p_p_lifecycle=1&amp;p_p_state=maximized&amp;p_p_mode=view&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_javax.portlet.action=deleteHelpProposal&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_tab=helpProposals&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_cmd=deleteHelpProposal&amp;_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalId=106276503&amp;p_auth=DmXjALY9')" role="menuitem" data-senna-off="true">
													<span class="taglib-text-icon">Consulter la proposition</span>
													</a>
												</li>
											</ul>
										</div>
									</td>
								</tr>
								<tr class="lfr-template">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="taglib-search-iterator-page-iterator-bottom">
						<script>
							function _eu_strasbourg_portlet_help_HelpBOPortlet_submitForm(curParam, cur) {
								var data = {};

								data[curParam] = cur;

								Liferay.Util.postForm(
									document.tydm__eu_strasbourg_portlet_help_HelpBOPortlet_pageIteratorFm,
									{
										data: data
									}
								);
							}
						</script>
					</div>
				</div>
			</div>
		</div>
		<input id="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainerPrimaryKeys" name="_eu_strasbourg_portlet_help_HelpBOPortlet_helpProposalsSearchContainerPrimaryKeys" type="hidden" value="106276101,106276503">
	</form>
</div>