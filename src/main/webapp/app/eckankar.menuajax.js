eckankar.menuajax = {
	init : function() {
		this.bindEvents();
		return true;
	},
	bindEvents : function() {

		var eckpl = this;

		$(".menu-item > a").click(
				function() {
					eckankar.banner.set_banner();
					$this = $(this), place = $this.attr("href")
							.replace("#", ''),
							$container = $("#main-container");

					loader = $container.load("/app/template/html/" + place
							+ ".html?"+(new Date()).getTime() , function() {

						if (place === "upload") {
							eckankar.upload.uploader();
							eckankar.playlist.bindExternalEvents();
						} else if (place === "artistas") {
							eckankar.artistas.init(); 
							eckankar.playlist.bindExternalEvents();
						} else if (place === "albumes") {
							eckankar.albumes.init();
							eckankar.playlist.bindExternalEvents();
						} else if (place == "generos") {
							eckankar.generos.init();
							eckankar.playlist.bindExternalEvents();
						} else if (place == "nusuario") {
							eckankar.usuarios.init();
							eckankar.playlist.bindExternalEvents();
						}
						

					});

				});
		return true;
	}
};