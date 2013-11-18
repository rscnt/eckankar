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

eckankar.usuarios = {

	init : function() {
		this.generalView();
		return true;
	},

	loadNuevoArtista : function() {

		var usuario, usuarioDetailView, eckart = this;

		$("#usr-container").html($(Templates.usuarioForm));

		eckart.bindDetailEvents();
		eckankar.playlist.bindExternalEvents();

	},

	bindBothEvents : function() {
		return true;
	},

	bindDetailEvents : function() {

		var eckart = this;

		$("#data-view-nuser").click(function(e) {
			e.preventDefault();
			eckart.loadNuevoArtista();
			eckankar.banner.set_banner();
		});

		$("#data-view-usuarios").click(function(e) {
			e.preventDefault();
			eckart.loadUsuarios();
			eckankar.banner.set_banner();
		});

		$("#nusrform")
				.submit(
						function(e) {
							e.preventDefault();
							$this = $(this), url = $(this).attr('action'),
									method = $(this).attr('method'),
									userData = new FormData();

							userData.append('usuario', $("#usuario").val());
							userData.append('password', $("#password").val());
							userData.append('email', $("#correo").val());
							userData.append('nombre', $("#nombre").val());
							userData.append('apellidos', $("#apellidos").val());
							userData.append('telefono', $("#telefono").val());

							var posting = {
								dataType : "json",
								url : url,
								data : userData,
								type : 'POST',
								contentType : false,
								cache : false,
								processData : false,
								async : false,
								success : function(data) {
									
									$("#errors").html("");
									
									if (data.email[0] === "fail") {
										$("#errors")
												.append(
														"<div class=\"row\"> <div class=\"twelve columns danger alert\">El correo no es valido</div></div>");
									}
									if (data.telefono[0] === "fail") {
										$("#errors")
												.append(
														"<div class=\"row\"><div class=\"twelve columns danger alert\">El telefono no es valido</div></div>");
									}

									if (data.general[0] === "success") {
										$("#errors").html("");
									}
									
								},
								error : function(jqXHR, textStatus, errorThrown) {
									jqXHR.abort();
								}
							}

							Messenger().run({
								errorMessage : 'no se pudo completar la peticion',
								successMessage : 'peticion completada.',
								progressMessage : 'procesando peticion.'
							}, posting);

						});

		eckankar.playlist.bindExternalEvents();

	},

	loadUsuarios : function() {

		var eckart = this, usuarioGeneralView = "";

		$("#usr-container").html("");

		request = $.ajax({
			url : '/usuarios',
			type : 'GET'
		});

		request.done(function(data) {

			var usuarios = data;

			$("#usr-container").html($(Templates.usuariosContainer));

			_.each(usuarios, function(usuario) {

				var usuario;

				usuarioEl = $(_.template(Templates.usuarioDetailView, {
					usuario : usuario
				}));

				$("#users-table").append(usuarioEl);

			});

			eckart.bindDetailEvents()
			eckankar.playlist.bindExternalEvents();

		});

	},

	generalView : function() {

		var eckart = this;

		eckart.loadUsuarios();

	}
}