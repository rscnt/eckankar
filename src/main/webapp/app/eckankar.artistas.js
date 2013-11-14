eckankar.artistas = {

	init : function() {
		this.generalView();
		return true;
	},

	loadDetailArtista : function(artistaID) {

		var artista, artistaDetailView, eckart = this;

		$.getJSON('/artistas/' + artistaID, function(data) {

			artista = data;

			$.get("/app/template/html/artista_detalle.html?"
					+ (new Date()).getTime(), function(responseTemplate) {
				artistaDetailView = $(_.template(responseTemplate, {
					artista : artista
				}));

				$("#artistas-container").html(artistaDetailView);
				eckart.bindDetailEvents();
			}); // sorry

		});

	},

	loadEditView : function(artistaID) {

		var artista, artistaEditView, eckart = this;

		$.getJSON('/artistas/' + artistaID, function(data) {

			artista = data;

			$.get("/app/template/html/artista_edit.html?"
					+ (new Date()).getTime(), function(responseTemplate) {
				artistaEditView = $(_.template(responseTemplate, {
					artista : artista
				}));

				$("#artistas-container").html(artistaEditView);
				eckart.bindEditEvents();
			}); // sorry

		});

	},

	bindBothEvents : function() {
		return true;
	},

	bindDetailEvents : function() {

		var eckart = this;

		$("#back-artistas").click(function(e) {
			e.preventDefault();
			eckart.loadArtistas();
			eckankar.banner.set_banner();
		});

		$("#to-edit").click(function(e) {
			e.preventDefault();
			eckart.loadEditView($(this).attr("data-id"));
		});

	},

	bindEditEvents : function() {

		var eckart = this, imgSrcChooser = document
				.getElementById("imgSrcChooser"),
				uploadBanner = document.getElementById("uploadBanner");

		$("#back-artista").click(function(e) {
			e.preventDefault();
			eckart.loadDetailArtista($(this).attr("data-id"));
		});

		$("#uploadImagenSrc").click(function(e) {
			e.preventDefault();
			imgSrcChooser.click();
		});

		imgSrcChooser.addEventListener("change", function() {
			// yeah this.
			if (imgSrcChooser.files && imgSrcChooser.files[0]) {
				var fileReader = new FileReader();

				console.log('asdasdsa');

				fileReader.onload = function(e) {

					console.log(e.target.result);
					$("#artist-image-src").attr('src', e.target.result);
					console.log($("#artist-image-src").attr('src'));
				}

				fileReader.readAsDataURL(imgSrcChooser.files[0]);
			}
		});

	},
	loadArtistas : function() {

		var eckart = this, artistaGeneralView = "";

		$("#artistas-container").html("");

		request = $.ajax({
			url : '/artistas',
			type : 'GET'
		});

		request.done(function(data) {

			var artistas = data;

			_.each(artistas, function(artista) {

				var artistaEL;

				artistaEl = $(_.template(Templates.artistView, {
					artista : artista
				}));

				$(artistaEl).click(function(e) {

					e.preventDefault();

					var artistaID = $(this).attr('data-id');

					$("#artistas-container").fadeOut('slow', function() {
						$("#artistas-container").css("display", "none");
						eckart.loadDetailArtista(artistaID);
						$("#artistas-container").css("display", "none");
						$("#artistas-container").fadeIn('slow');
					});

				});

				$("#artistas-container").append(artistaEl);

			});

		});

	},

	generalView : function() {

		var eckart = this;

		eckart.loadArtistas();

	}
}