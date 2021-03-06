/*  */

eckankar.upload = {
	init : function() {
		this.uploader();
	},
	uploader : function() {
		var uploadButton = document.getElementById("uploadButton"), filechooser = document
				.getElementById("filechooser"), uploadForm = document
				.getElementById("uploadForm"), uploadedContainer = document
				.getElementById("uploadedContainer");

		if (filechooser !== null) {

			$(uploadForm)
					.submit(
							function(e) {
								e.preventDefault();
								var $this = $(this), url = $this.attr('action'), files = filechooser.files, i = 0, tempalte = null;

								while (i < files.length) {
									var file = files[i], result = eckankar.upload
											.upload_song(file, i);
									if (result.done) {
										console.log(result.data);
										$("a.quit[data-id=" + i + "]").click()
												.delay(200);
										console.log(result.data);
										template = _
												.template(
														Templates.uploadedTempalte,
														{
															cancion : result.data,
															album : result.data.album.nombre
																	.replace(
																			/\s+/g,
																			'')
														});
										$("#uploads-container")
												.append(template).delay(500);
										eckankar.playlist
												.addtoPlaylist(cancion = result.data);
										i++;
									} else {
										break;
									}
									;

								}
							});

			filechooser
					.addEventListener(
							"change",
							function() {
								// --------------------------
								var message = "Se agrego ", template = Templates.uploadTable, fileHandler = this;

								if (fileHandler.files.length > 1) {
									message = "Se agregaron ";
								}

								message += fileHandler.files.length
										+ " para ser subidos.";

								Messenger().post({
									message : message,
									type : 'info',
									showCloseButton : true
								});
								// ---------------------------

								// ---------------------------
								$("#uploaded-container").html(
										_.template(template, {
											files : fileHandler.files
										}));
								$("#uploaded-container").fadeIn('slow');

								// ---------------------------

								$(".quit")
										.click(
												function(e) {
													e.preventDefault();
													var $this = $(this), key = $this
															.attr('data-id');

													$("tr#upload-" + key)
															.remove();

													if ($("#pre-upload-table")
															.find("tbody")
															.find("tr").length === 0) {
														$("#pre-upload-table")
																.fadeOut();
													}
												});

							});

			uploadButton.addEventListener("click", function() {
				filechooser.click();
			});

			return true;
		}

		else {
			return false;
		}
	},

	upload_song : function(file, index) {

		var data = new FormData(), result = {
			done : false,
			data : null
		};

		data.append('name', file.name);
		data.append('file', file);

		var posting = {
			dataType : "json",
			url : '/upload',
			data : data,
			type : 'POST',
			contentType : false,
			cache : false,
			processData : false,
			async : false,
			success : function(data) {
				// what i going to do with this.
				result.data = data;
				result.done = true;
			},
			error : function(jqXHR, textStatus, errorThrown) {
				jqXHR.abort();
			}
		}

		Messenger().run(
				{
					errorMessage : 'El archivo : ' + file.name
							+ 'no pudo ser ingresado',
					successMessage : 'El archivo : ' + file.name
							+ ' fue ingresado con exito',
					progressMessage : 'El archivo : ' + file.name
							+ ' esta siendo subido'
				}, posting);

		return result;
	}
}