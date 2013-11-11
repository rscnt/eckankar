/*jshint undef: true, unused: true */
/* global base */
/* global $ */
/* global Messenger */

eckankar.playlist = {
	init : function() {
		$("#jplayer").jPlayer({
			swfPath : "/app/vendor/jplayer",
			supplied : "mp3"
		});
		$("#jplayer").jPlayer("volume", 0.50);
		this.bindEvents();
	},
	status : {
		loop : false,
		random : false
	},
	stackPlaylist : new Array(), // [{el: $(".song-playlist), cancion: {} id:
	// 0}]
	currentPlaylist : {
		el : null,
		cancion : null,
		id : null,
		fav : false,
		fname : null
	},
	addtoPlaylist : function(cancion) {

		var eckpl = this; // that = this, me = this.

		songEL = $(_.template(Templates.playlistSong, {
			cancion : cancion,
			album : cancion.album.nombre.replace(/\s+/g, ''),
			id : eckpl.stackPlaylist.length
		})).appendTo("#playlist");

		this.stackPlaylist.push({
			el : songEL,
			cancion : cancion,
			id : eckpl.stackPlaylist.length
		});

		$(songEL).click(
				function() {
					$(".song-playlist").removeClass("song-playlist-active");
					$(this).addClass("song-playlist-active");
					eckpl.currentPlaylist = eckpl.stackPlaylist[$(this).attr(
							'data-id')];
					eckpl.playCurrent();
				});
	},

	playSong : function(fName) {

		var eckpl = this; // that = this, me = this.

		$("#jplayer").jPlayer("clearMedia");
		$("#jplayer").jPlayer("setMedia", {
			mp3 : "/songs/" + fName
		});
		$("#jplayer").jPlayer("play");

	},

	playCurrent : function() {

		$("#jplayer").jPlayer("clearMedia");

		if (document.querySelector(".song-playlist-active") === null) {
			this.currentPlaylist = this.stackPlaylist[0];
		}
		$(".song-playlist").removeClass("song-playlist-active");
		$(this.currentPlaylist.el).addClass("song-playlist-active");

		this.showCurrentSong();

		this.playSong(this.currentPlaylist.cancion.fName);

	},

	playNext : function() {

		if (this.currentPlaylist.id === (this.stackPlaylist.length - 1)) {
			this.currentPlaylist = this.stackPlaylist[0];
		} else {
			this.currentPlaylist = this.stackPlaylist[base.playlist.currentPlaylist.id + 1];
		}

		this.playCurrent();
		return true;
	},

	playPrevious : function() {

		if (this.currentPlaylist.id === 0) {
			this.currentPlaylist = this.stackPlaylist[this.stackPlaylist.length - 1];
		} else {
			this.currentPlaylist = this.stackPlaylist[this.currentPlaylist.id - 1];
		}

		this.playCurrent();
		return true;
	},

	playRandom : function() {

		var s = Math.floor(Math.random() * (this.stackPlaylist.length - 0 + 1)
				+ 0);

		this.currentPlaylist = this.stackPlaylist[s];

		this.playCurrent();
		return true;
	},

	showCurrentSong : function() {

		var eckpl = this; // that = this, me = this.

		Messenger()
				.post(
						{
							message : "<div class=\"row\">"
									+ "<div class=\"three columns\"><img class=\"lft\" src=\"/covers/"
									+ eckpl.currentPlaylist.cancion.album.nombre
											.replace(/\s+/g, '')
									+ "\" /></div>"
									+ "<div class=\"nine columns\">"
									+ "<i class=\"icon-note\"></i>"
									+ eckpl.currentPlaylist.cancion.nombre
									+ "<br/><i class=\"icon-mic\"></i>"
									+ eckpl.currentPlaylist.cancion.artista.nombre
									+ "<br/><i class=\"icon-music\"></i>"
									+ eckpl.currentPlaylist.cancion.album.nombre
									+ "</div>",
							type : 'info',
							showCloseButton : true
						});

	},

	bindEvents : function() {

		var eckpl = this; // that = this, me = this.

		$(".jp-next").click(function(e) {
			if (eckpl.status.random) {
				eckpl.playRandom();
			} else {
				eckpl.playNext();
			}
			e.preventDefault();
			return false;
		});
		$(".jp-previous").click(function(e) {

			if (eckpl.status.random) {
				eckpl.playRandom();
			} else {
				eckpl.playPrevious();
			}

			e.preventDefault();
			return false;

		});

		$(".jp-show-plst").click(function(e) {
			e.preventDefault();
			$(".player-container").height(37).animate({
				height : "170px"
			}, 500) // show
			$(".jp-show-plst").hide();
			$(".jp-hide-plst").show();
		});

		$(".jp-hide-plst").click(function(e) {
			e.preventDefault();
			$(".player-container").height(170).animate({
				height : "37px"
			}, 500) // show
			$(".jp-hide-plst").hide();
			$(".jp-show-plst").show();
		});

		$(".jp-info").click(function(e) {
			e.preventDefault();
			eckpl.showCurrentSong();
		});

		$(".jp-loop").click(
				function(e) {
					if (eckpl.status.loop) {
						$("#jplayer").unbind(".repeat");
						eckpl.status.loop = false;
						$(".jp-loop").removeClass('active');
					} else {
						$(".jp-loop").addClass('active');
						eckpl.status.loop = true;
						$("#jplayer").bind($.jPlayer.event.ended + ".repeat",
								function() {
									if (eckpl.status.random) {
										eckpl.playRandom();
									} else {
										eckpl.playNext();
									}
								});
					}
					e.preventDefault();
				});

		$(".jp-random").click(
				function(e) {
					if (eckpl.status.random) {
						$("#jplayer").unbind(".random");
						eckpl.status.random = false;
						$(".jp-random").removeClass('active');
					} else {
						$(".jp-random").addClass('active');
						eckpl.status.random = true;
						$("#jplayer").bind($.jPlayer.event.ended + ".random",
								function() {
									eckpl.playRandom();
								});
					}
					e.preventDefault();
				});
	}
}