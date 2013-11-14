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
						} else if (place === "artistas") {
							eckankar.artistas.init();
						}

					});

				});
		return true;
	}
};