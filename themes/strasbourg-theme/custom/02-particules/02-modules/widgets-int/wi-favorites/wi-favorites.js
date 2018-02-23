$(function() {
	var elements = $('.seu-add-favorites, .add-favorites, .item-misc');
	if (elements) {
		elements.each(function( i ) {
			var favorite = $(this);
			if (window.userFavorites) {
				$.each( window.userFavorites, function( index, value ){
				   if(favorite.data("id") == this.entityId){
						favorite[0].classList.add('liked'); 
					  	favorite[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
				    }
				});
			}
		});
	}
});

$(function() {
	//.seu-add-favorites && .add-favorites && .item-misc
	$(document).on("click",'.seu-add-favorites, .add-favorites, .item-misc', function(e) {
		e.preventDefault();
		e.stopPropagation();
		//var favorite = $('.seu-add-favorites');
		//$('.seu-add-favorites').on('click', function() {
		var htmlA = $(this);
		var url = $(this).data("url");
		var id = $(this).data("id");
		var groupId = $(this).data("groupId") ? $(this).data("groupId") : 0;
		var type = $(this).data("type");
		var title = $(this).data("title");		


		if(htmlA[0].classList.contains('liked')) {
			Liferay.Service(
			  '/favorite.favorite/delete-favorite-link',
			  {
			    title: title,
			    url: url,
			    typeId: type,
			    entityId: id
			  },
			  function(obj) {		
			  	if(obj.hasOwnProperty('success')) {
			  		htmlA[0].classList.remove('liked'); 
			  		htmlA[0].children[0].textContent = Liferay.Language.get('eu.add-to-favorite');
			  	}
			  	else if(obj.hasOwnProperty('error')) {
			  		if(obj['error'] == 'notConnected')
			  			window.createPopin('Veuillez vous connecter pour retirer un favori.');
			  		else{
			  			console.log(obj['error']);
			  			window.createPopin('Une erreur est survenue.');
			  		}
			  	}
			  }
			);
		}
		else {
			Liferay.Service(
			  '/favorite.favorite/add-favorite-link',
			  {
			    title: title,
			    url: url,			
			    typeId: type,
			    entityId: id,
			    entityGroupId: groupId
			  },
			  function(obj) {	
			  	if(obj.hasOwnProperty('success')) {
			  		htmlA[0].classList.add('liked'); 
			  		htmlA[0].children[0].textContent = Liferay.Language.get('eu.remove-from-favorite');
			  	}
			  	else if(obj.hasOwnProperty('error')) {
			  		if(obj['error'] == 'notConnected')
			  			window.createPopin('Veuillez vous connecter pour ajouter un favori.');
			  		else{
			  			console.log(obj['error']);
			  			window.createPopin('Une erreur est survenue.');
			  		}
			  	}

			  }
			);
		}
	});	
	
});