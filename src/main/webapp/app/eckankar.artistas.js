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

				console.log($("p.artist-descp"));

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
									artistaID = $("#artista-codigo").val();

							// you know 'cause shit why no?, well there's a lot
							// of
							// reasons.
							$
									.get(
											'/artistas/' + artistaID,
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
													url : '/artistas/u/'
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
																	errorMessage : 'El artista: '
																			+ artista.nombre
																			+ ' no pudo ser actualizado.',
																	successMessage : 'El artista: '
																			+ artista.nombre
																			+ ' pudo ser actualizado.',
																	progressMessage : 'El artista: '
																			+ artista.nombre
																			+ ' esta siendo actualizado.'
																}, posting);

											});
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

			$.each($("p.artist-descp"), function(x, k) {
				$(k).html($(k).text());
			});

		});

	},

	generalView : function() {

		var eckart = this;

		eckart.loadArtistas();

	}
}