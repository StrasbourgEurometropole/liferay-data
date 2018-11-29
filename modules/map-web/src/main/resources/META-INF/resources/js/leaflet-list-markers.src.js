/* 
 * Leaflet List Markers v0.1.0 - 2017-11-26 
 * 
 * Copyright 2017 Stefano Cudini 
 * stefano.cudini@gmail.com 
 * http://labs.easyblog.it/ 
 * 
 * Licensed under the MIT license. 
 * 
 * Demo: 
 * http://labs.easyblog.it/maps/leaflet-list-markers/ 
 * 
 * Source: 
 * git@github.com:stefanocudini/leaflet-list-markers.git 
 * 
 */

(function() {

L.Control.ListMarkers = L.Control.extend({

	includes: L.version[0]==='1' ? L.Evented.prototype : L.Mixin.Events,

	options: {		
		layer: false,
		maxItems: 20,
		collapsed: false,		
		label: 'title',
		itemIcon: L.Icon.Default.imagePath+'/marker-icon.png',
		itemArrow: '&#10148;',	//visit: http://character-code.com/arrows-html-codes.php
		minZoom: 9,
		position: 'bottomleft',
		type: 'type'
		//TODO autocollapse
	},

	initialize: function(options) {
		L.Util.setOptions(this, options);
		this._container = null;
		this._list = null;
		this._layer = this.options.layer || new L.LayerGroup();
	},

	onAdd: function (map) {

		this._map = map;
	
		var container = this._container = L.DomUtil.create('div', 'list-markers');

		this._list = L.DomUtil.create('div', 'filtres__list', container);

		this._initToggle();

		map.on('moveend', this._updateList, this);
			
		this._updateList();

		return container;
	},
	
	onRemove: function(map) {
		map.off('moveend', this._updateList, this);
		this._container = null;
		this._list = null;		
	},

	_createItem: function(layer) {
		var self = this;
		var div = L.DomUtil.create('div', 'filtres__item form-group grid-item filtres__item--favorite'),
			a = L.DomUtil.create('a', 'infowindow__name', div),
			that = this;

		a.href = layer.feature.properties.url;
		L.DomEvent
			.disableClickPropagation(a)
			.on(a, 'click', L.DomEvent.stop, this)
			.on(a, 'click', function(e) {
				this._moveTo( layer.getLatLng() );
			}, this)
			.on(a, 'click', function(e) {
				this._moveTo( layer.getLatLng() );
				this._map.once("moveend zoomend", function() {
					var cluster = self._layer.getVisibleParent(layer);
					if (cluster.spiderfy) {
						setTimeout(function() {
							cluster.spiderfy();
							layer.openPopup();
						}, 500);
					} else {
						setTimeout(function() {
							layer.openPopup();
						}, 250);
					}
				});
			}, this)
//			.on(a, 'click', function(e) {
//				this._toggleDisplay(layer);
//			}, this)
			.on(a, 'mouseover', function(e) {
				that.fire('item-mouseover', {layer: layer });
			}, this)
			.on(a, 'mouseout', function(e) {
				that.fire('item-mouseout', {layer: layer });
			}, this);


		if( layer.options.hasOwnProperty(this.options.label) )
		{
			a.innerHTML = "";
			if (layer.feature.properties.visual) {
				a.innerHTML += '<div class="filtres__item__visuel" style="background-image: url(' + layer.feature.properties.visual + ');"></div>';
			}
			a.innerHTML += '<span>'+layer.options[this.options.label]+'</span>';
			//TODO use related marker icon!
			//TODO use template for item
		}
		else
			console.log("propertyName '"+this.options.label+"' not found in marker");
		
		if(layer.feature.properties.type != null){
			var addedFavorite = false;
			if (window.userFavorites) {
				var i;
				for (i = 0; i < window.userFavorites.length; i++) {
					if(window.userFavorites[i].typeId == layer.feature.properties.type && window.userFavorites[i].entityId == layer.feature.properties.id){
						addedFavorite = true;
						break;
					}
				} 
			}
			var lienFavori = '<div style="background-color: white; padding: 0px 8px;"><a href="#" class="add-favorites';
			if(addedFavorite){
				lienFavori += ' liked';
			}
			lienFavori += '" style="display: flex;" '
				+ 'data-type="' + layer.feature.properties.type + '"' 
		        + 'data-title="' + layer.options[this.options.label] + '"' 
		        + 'data-url="' + layer.feature.properties.url + '"' 
		        + 'data-id="' + layer.feature.properties.id + '">';
			if(addedFavorite){
				lienFavori += '<span>' + Liferay.Language.get("eu.remove-from-favorite") + '</span>';
			}else{
				lienFavori += '<span>' + Liferay.Language.get("eu.add-to-favorite") + '</span>';
			}
			lienFavori += '</a></div>';
		    a.insertAdjacentHTML('afterend', lienFavori);
		}

		if (layer.feature.properties.listeTypes || layer.feature.properties.type) {
		    a.insertAdjacentHTML('afterend', '<p class="types">' + (layer.feature.properties.listeTypes || layer.feature.properties.type) + '</p>');
		}

		return div;
	},

	_updateList: function() {
	
		var that = this,
			n = 0;

		this._list.innerHTML = '';
		this._layer.eachLayer(function(layer) {
			if(layer instanceof L.Marker)
				if( that._map.getBounds().contains(layer.getLatLng()) )
					if(++n < that.options.maxItems)
						that._list.appendChild( that._createItem(layer) );
		});
        if(this._list.innerHTML == ''){
            this._list.innerHTML = '<h2>'+Liferay.Language.get("select-an-interest")+'</h2>';
        }
	},

	_initToggle: function () {

		/* inspired by L.Control.Layers */

		var container = this._container;

		//Makes this work on IE10 Touch devices by stopping it from firing a mouseout event when the touch is released
		container.setAttribute('aria-haspopup', true);

		if (!L.Browser.touch) {
			L.DomEvent
				.disableClickPropagation(container);
				//.disableScrollPropagation(container);
		} else {
			L.DomEvent.on(container, 'click', L.DomEvent.stopPropagation);
		}

		if (this.options.collapsed)
		{
			this._collapse();

			if (!L.Browser.android) {
				L.DomEvent
					.on(container, 'mouseover', this._expand, this)
					.on(container, 'mouseout', this._collapse, this);
			}
			var link = this._button = L.DomUtil.create('a', 'list-markers-toggle', container);
			link.href = '#';
			link.title = 'List Markers';

			if (L.Browser.touch) {
				L.DomEvent
					.on(link, 'click', L.DomEvent.stop)
					.on(link, 'click', this._expand, this);
			}
			else {
				L.DomEvent.on(link, 'focus', this._expand, this);
			}

			this._map.on('click', this._collapse, this);
			// TODO keyboard accessibility
		}
	},

	_expand: function () {
		this._container.className = this._container.className.replace(' list-markers-collapsed', '');
	},

	_collapse: function () {
		L.DomUtil.addClass(this._container, 'list-markers-collapsed');
	},

    _moveTo: function(latlng) {
		if(this.options.minZoom)
			this._map.setView(latlng, Math.max(this._map.getZoom(), this.options.minZoom) );
		else
			this._map.panTo(latlng);    
    },
	_toggleDisplay: function(layer)
	{
		//this._map.removeLayer(layer);
		//markers.removeLayer(layer);
		
		if(layer.options.opacity == 0)
		{
			layer.clusterShow();
		}
		else
		{
			layer.clusterHide();
		}
		
		//markers.addLayer(layer);
		//this._map.addLayer(layer);
		//markers.refreshClusters();
	}
});

L.control.listMarkers = function (options) {
    return new L.Control.ListMarkers(options);
};

L.Map.addInitHook(function () {
    if (this.options.listMarkersControl) {
        this.listMarkersControl = L.control.listMarkers(this.options.listMarkersControl);
        this.addControl(this.listMarkersControl);
    }
});

}).call(this);
