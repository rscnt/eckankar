base.menuajax = {
	init : function() {
		this.bindEvents();
		return true;
	},
	bindEvents : function() {
		$(".menu-item > a").click(
				function() {
					base.set_banner();
					$this = $(this), place = $this.attr("href")
							.replace("#", ''),
							$container = $("#main-container");

					$container.load("/app/template/html/" + place + ".html");
				});
		return true;
	}
};