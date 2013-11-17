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

eckankar.albumes = {

	init : function() {
		this.generalView();
		return true;
	},

	loadDetailArtista : function(albumID) {

		var album, albumDetailView, eckart = this;

		$.getJSON('/albums/' + albumID, function(data) {

			album = data;

			$.get("/app/template/html/album_detalle.html?"
					+ (new Date()).getTime(), function(responseTemplate) {
				albumDetailView = $(_.template(responseTemplate, {
					album : album
				}));

				$("#albumes-container").html(albumDetailView);

				$.get("/albums/" + albumID + "/canciones", function(canciones) {
					_.each(canciones, function(cancion) {
						$(_.template(Templates.albumSongView, {
							cancion : cancion,
							album : album
						})).appendTo("#album-songs");
					});

					eckankar.playlist.bindExternalEvents();
				});

				eckart.bindDetailEvents();
				eckankar.playlist.bindExternalEvents();
			}); // sorry

		});

	},

	loadEditView : function(albumID) {

		var album, albumEditView, eckart = this;

		$.getJSON('/albums/' + albumID, function(data) {

			album = data;

			$.get("/app/template/html/album_edit.html?"
					+ (new Date()).getTime(), function(responseTemplate) {

				albumEditView = $(_.template(responseTemplate, {
					album : album
				}));

				$("#albumes-container").html(albumEditView);
				eckart.bindEditEvents();
				eckankar.playlist.bindExternalEvents();
			}); // sorry

		});

	},

	bindBothEvents : function() {
		return true;
	},

	bindDetailEvents : function() {

		var eckart = this;

		$("#back-albums").click(function(e) {
			e.preventDefault();
			eckart.loadAlbumes();
			eckankar.banner.set_banner();
		});

		$("#to-edit").click(function(e) {
			e.preventDefault();
			eckart.loadEditView($(this).attr("data-id"));
		});

		eckankar.playlist.bindExternalEvents();

	},

	bindEditEvents : function() {

		var eckart = this, imgSrcChooser = document
				.getElementById("imgSrcChooser"), bannerchooser = document
				.getElementById("bannerchooser");

		$("#back-album").click(function(e) {
			e.preventDefault();
			eckart.loadDetailArtista($(this).attr("data-id"));
		});

		$("#uploadBanner").click(function(e) {
			e.preventDefault();
			bannerchooser.click();
		});

		bannerchooser.addEventListener("change", function() {
			var fileReader = new FileReader();

			fileReader.onload = function(e) {
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
					$("#album-imgSRC").val(e.target.result);
				}

				fileReader.readAsDataURL(imgSrcChooser.files[0]);
			}
		});

		$("#submitEditArtista")
				.click(
						function(e) {
							e.preventDefault();
							$this = $(this), url = '/upload', method = 'PUT',
									albumID = $("#album-codigo").val();

							// you know 'cause shit why no?, well there's a lot
							// of
							// reasons.
							$
									.get(
											'/albums/' + albumID,
											function(data) {
												var album = data, artistData = new FormData(), result = {
													done : false,
													data : null
												};

												$("#album-descpVal")
														.val(
																escapeHtml($(
																		"#album-edit-descp")
																		.html()));

												album.nombre = $(
														"#album-nombre").val();

												album.descripcion = escapeHtml(
														$("#album-edit-descp")
																.html()).trim();

												album.imagen_src = $(
														"#album-image-src")
														.attr("src");

												artistData.append('nombre',
														album.nombre);

												artistData.append('imagen_src',
														imgSrcChooser.files[0]);

												artistData.append(
														'descripcion',
														album.descripcion);

												artistData.append('file',
														bannerchooser.files[0]);

												var posting = {
													dataType : "json",
													url : '/albums/u/'
															+ album.codigo,
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
																	errorMessage : 'El album: '
																			+ album.nombre
																			+ ' no pudo ser actualizado.',
																	successMessage : 'El album: '
																			+ album.nombre
																			+ ' pudo ser actualizado.',
																	progressMessage : 'El album: '
																			+ album.nombre
																			+ ' esta siendo actualizado.'
																}, posting);

											});
						});

	},

	loadAlbumes : function() {

		var eckart = this, albumGeneralView = "";

		$("#albumes-container").html("");

		request = $.ajax({
			url : '/albums',
			type : 'GET'
		});

		request.done(function(data) {

			var albumes = data;

			_.each(albumes, function(album) {

				var albumEL;

				albumEl = $(_.template(Templates.albumView, {
					album : album
				}));

				$(albumEl).click(function(e) {

					e.preventDefault();

					var albumID = $(this).attr('data-id');

					$("#albumes-container").fadeOut('slow', function() {
						$("#albumes-container").css("display", "none");
						eckart.loadDetailArtista(albumID);
						$("#albumes-container").css("display", "none");
						$("#albumes-container").fadeIn('slow');
					});

				});

				$("#albumes-container").append(albumEl);

			});

			$.each($("p.artist-descp"), function(x, k) {
				$(k).html($(k).text());
			});

			eckankar.playlist.bindExternalEvents();

		});

	},

	generalView : function() {

		var eckart = this;

		eckart.loadAlbumes();

	}
}