<%@ include file="/council-init.jsp"%>

<div class="delib-title" id="delib-title" style="display:none;">
    <h2></h2>
    <p class="delib-refresh" id="delib-refresh" onClick="document.location.reload(true)"><svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24" fill="#31455d"><path d="M9 12l-4.463 4.969-4.537-4.969h3c0-4.97 4.03-9 9-9 2.395 0 4.565.942 6.179 2.468l-2.004 2.231c-1.081-1.05-2.553-1.699-4.175-1.699-3.309 0-6 2.691-6 6h3zm10.463-4.969l-4.463 4.969h3c0 3.309-2.691 6-6 6-1.623 0-3.094-.65-4.175-1.699l-2.004 2.231c1.613 1.526 3.784 2.468 6.179 2.468 4.97 0 9-4.03 9-9h3l-4.537-4.969z"/></svg></p>
</div>
<div class="rte delib-description" id="delib-description">
    <div></div>

    <!-- AFFICHAGE UNIQUEMENT SKYPE LORSQUE VOTE OUVERT -->
    <h2 style="text-align:center;margin-top: 30px; display:none;" id="vote-en-cours">VOTE EN COURS</h2>

</div>

<!-- PAS DE DELIB -->
<h2 style="margin-top: 30px; display:none;" id="no-delib"></h2>
