var entityMap = {
	"&" : "&amp;",
	"<" : "&lt;",
	">" : "&gt;",
	'"' : '&quot;',
	"'" : '&#39;',
	"/" : '&#x2F;'
};

function escapeHtml(string) {
	return String(string).replace(/[&<>"'\/]/g, function(s) {
		return entityMap[s];
	});
}

eckankar.generos = {

	init : function() {
		this.generalView();
		return true;
	},

	loadDetailArtista : function(generoID) {

		var artista, generoDetailView, eckart = this;

		$.getJSON('/generos/' + generoID, function(data) {

			genero = data;

			$.get("/app/template/html/generos_detalle.html?"
					+ (new Date()).getTime(), function(responseTemplate) {
				generoDetailView = $(_.template(responseTemplate, {
					genero : genero
				}));

				$("#generos-container").html(generoDetailView);

				eckart.bindDetailEvents();
			}); // sorry

		});

	},

	loadAlbumsView : function(generoID) {

		var album, albumDetailView, eckart = this;

		$("#genero-descp").html("");

		$.getJSON('/generos/' + generoID + '/albums/', function(albums) {

			_.each(albums, function(album) {

				albumDetailView = $(_.template(Templates.albumContainerView, {
					album : album
				}));

				$("#genero-descp").append(albumDetailView);

				$.get("/albums/" + album.codigo + "/canciones", function(
						canciones) {

					_.each(canciones, function(cancion) {
						$(_.template(Templates.albumSongView, {
							cancion : cancion,
							album : album
						})).appendTo("#album-songs-" + album.codigo);
					});

					eckankar.playlist.bindExternalEvents();
				});

				eckart.bindDetailEvents();

				console.log(album);

			});
		});
	},

	loadEditView : function(generoID) {

		var artista, artistaEditView, eckart = this;

		$.getJSON('/generos/' + generoID, function(data) {

			artista = data;

			$.get("/app/template/html/generos_edit.html?"
					+ (new Date()).getTime(), function(responseTemplate) {

				artistaEditView = $(_.template(responseTemplate, {
					genero : genero
				}));

				$("#generos-container").html(artistaEditView);
				eckart.bindEditEvents();
			}); // sorry

		});

	},

	bindBothEvents : function() {
		return true;
	},

	bindDetailEvents : function() {

		var eckart = this;

		$("#back-generos").click(function(e) {
			e.preventDefault();
			eckart.loadArtistas();
			eckankar.banner.set_banner();
		});

		$("#to-edit").click(function(e) {
			e.preventDefault();
			eckart.loadEditView($(this).attr("data-id"));
		});

		$("#data-view-albums").click(function(e) {
			e.preventDefault();
			eckart.loadAlbumsView($(this).attr("data-id"))
		});

		$("#data-view-descps").click(function(e) {
			e.preventDefault();
			eckart.loadDetailArtista($(this).attr("data-id"));
		});

	},

	bindEditEvents : function() {

		var eckart = this, imgSrcChooser = document
				.getElementById("imgSrcChooser"), bannerchooser = document
				.getElementById("bannerchooser");

		$("#back-artista").click(function(e) {
			e.preventDefault();
			eckart.loadDetailArtista($(this).attr("data-id"));
		});

		$("#uploadBanner").click(function(e) {
			e.preventDefault();
			bannerchooser.click();
		})

		bannerchooser.addEventListener("change", function() {
			var fileReader = new FileReader();

			fileReader.onload = function(e) {

				console.log(e.target.result);
				$("#banner-top").attr('src', e.target.result);
			}

			fileReader.readAsDataURL(bannerchooser.files[0]);
		});

		$("#uploadImagenSrc").click(function(e) {
			e.preventDefault();
			imgSrcChooser.click();
		});

		imgSrcChooser.addEventListener("change", function() {
			// yeah this.
			if (imgSrcChooser.files && imgSrcChooser.files[0]) {

				var fileReader = new FileReader();

				fileReader.onload = function(e) {

					$("#artist-image-src").attr('src', e.target.result);
					$("#artista-imgSRC").val(e.target.result);
				}

				fileReader.readAsDataURL(imgSrcChooser.files[0]);
			}
		});

		$("#submitEditArtista")
				.click(
						function(e) {
							e.preventDefault();
							$this = $(this), url = '/upload', method = 'PUT',
									generoID = $("#artista-codigo").val();

							// you know 'cause shit why no?, well there's a lot
							// of
							// reasons.
							$
									.get(
											'/generos/' + generoID,
											function(data) {
												var artista = data, artistData = new FormData(), result = {
													done : false,
													data : null
												};

												$("#artista-descpVal")
														.val(
																escapeHtml($(
																		"#artista-edit-descp")
																		.html()));

												artista.nombre = $(
														"#artista-nombre")
														.val();

												artista.descripcion = escapeHtml(
														$("#artista-edit-descp")
																.html()).trim();

												artista.imagen_src = $(
														"#artist-image-src")
														.attr("src");

												artistData.append('nombre',
														artista.nombre);

												artistData.append('imagen_src',
														artista.imagen_src);

												artistData.append(
														'descripcion',
														artista.descripcion);

												artistData.append('file',
														bannerchooser.files[0]);

												var posting = {
													dataType : "json",
													url : '/generos/u/'
															+ artista.codigo,
													data : artistData,
													type : 'POST',
													contentType : false,
													cache : false,
													processData : false,
													async : false,
													success : function(data) {
														// what i going to do
														// with this.
														result.data = data;
														result.done = true;
													},
													error : function(jqXHR,
															textStatus,
															errorThrown) {
														jqXHR.abort();
													}
												}

												Messenger()
														.run(
																{
																	errorMessage : 'El genero: '
																			+ artista.nombre
																			+ ' no pudo ser actualizado.',
																	successMessage : 'El genero: '
																			+ artista.nombre
																			+ ' pudo ser actualizado.',
																	progressMessage : 'El genero: '
																			+ artista.nombre
																			+ ' esta siendo actualizado.'
																}, posting);

											});
						});

	},

	loadArtistas : function() {

		var eckart = this, artistaGeneralView = "";

		$("#generos-container").html("");

		request = $.ajax({
			url : '/generos',
			type : 'GET'
		});

		request.done(function(data) {

			var generos = data;

			$("#generos-container").html("");

			_.each(generos, function(genero) {

				var generoEl;

				generoEl = $(_.template(Templates.generoView, {
					genero : genero
				}));

				$(generoEl).click(function(e) {

					e.preventDefault();

					var generoID = $(this).attr('data-id');

					$("#generos-container").fadeOut('slow', function() {
						$("#generos-container").css("display", "none");
						eckart.loadDetailArtista(generoID);
						$("#generos-container").css("display", "none");
						$("#generos-container").fadeIn('slow');
					});

				});

				$("#generos-container").append(generoEl);

			});

			$.each($("p.genero-descp"), function(x, k) {
				$(k).html($(k).text());
			});

		});

	},

	generalView : function() {

		var eckart = this;

		eckart.loadArtistas();

	}
}