eckankar.artistas = {
		init : function(){},
		generalView: function(){
			$.ajax({
				url : '/artistas',
				type : 'GET',
				done : function(data) {
					var artContainer = $("#artistas-container"),
						artistas = data;
				}
			});
		};
}