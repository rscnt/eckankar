/**/

var options = {
	images : [ 'grounge.png', 'metal.png', 'classic.png', 'pop.png',
			'mprog.png', 'reg.png', 'old.png' ],
	images_url : '/assets/img/banner/',
	el_banner_id : '#banner-top'
};

eckankar.banner = {
	init : function(usrconf) {

		if (typeof usrconf !== 'undefined') {
			usrconf.images = usrconf.images ? usrconf.images : options.images;
			usrconf.images_url = usrconf.images_url ? usrconf.images_url
					: options.images_url;
			usrconf.el_banner_id = usrconf.el_banner_id ? usrconf.el_banner_id
					: options.el_banner_id;
		} else {
			usrconf = options;
		}

		this.set_banner(usrconf.el_banner_id, usrconf.images_url,
				usrconf.images);

	},

	set_img_banner : function(container, url) {
		container.attr('src', url);
		return true;
	},

	set_banner : function(container_name, base_url, images) {
		var cntnr_name = typeof container_name !== 'undefined' ? container_name
				: options.el_banner_id, imgs_bnnr = typeof images !== 'undefined' ? images
				: options.images, current_img = Math.floor(Math.random()
				* (imgs_bnnr.length - 0) + 0), bsURL = typeof base_url !== 'undefined' ? base_url
				: options.images_url;
		this.set_img_banner($(cntnr_name), bsURL + imgs_bnnr[current_img]);
		return true;
	}

}