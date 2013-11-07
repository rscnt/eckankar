/*jshint undef: true, unused: true */
/* global base */
/* global $ */
/* global Messenger */

base.playlist = {
	init : function() {
		$("#jplayer").jPlayer({
			swfPath : "/app/vendor/jplayer",
			supplied : "mp3"
		});
		$("#jplayer").jPlayer("volume", 0.50);
		base.playlist.bindEvents();
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
		songEL = $(_.template(Templates.playlistSong, {
			cancion : cancion,
			album : cancion.album.nombre.replace(/\s+/g, ''),
			id : this.stackPlaylist.length
		})).appendTo("#playlist");
		this.stackPlaylist.push({
			el : songEL,
			cancion : cancion,
			id : this.stackPlaylist.length
		});

		$(songEL)
				.click(
						function() {
							$(".song-playlist").removeClass(
									"song-playlist-active");
							$(this).addClass("song-playlist-active");
							base.playlist.currentPlaylist = base.playlist.stackPlaylist[$(
									this).attr('data-id')];
							base.playlist.playCurrent();
						});
	},
	playSong : function(fName) {
		$("#jplayer").jPlayer("clearMedia");
		$("#jplayer").jPlayer("setMedia", {
			mp3 : "/songs/" + fName
		});
		$("#jplayer").jPlayer("play");
	},
	playCurrent : function() {
		$("#jplayer").jPlayer("clearMedia");
		if (document.querySelector(".song-playlist-active") === null) {
			base.playlist.currentPlaylist = base.playlist.stackPlaylist[0];
		}
		$(".song-playlist").removeClass("song-playlist-active");
		$(base.playlist.currentPlaylist.el).addClass("song-playlist-active");

		base.playlist.showCurrentSong();
		base.playlist.playSong(base.playlist.currentPlaylist.cancion.fName);
	},
	playNext : function() {
		if (base.playlist.currentPlaylist.id === (base.playlist.stackPlaylist.length - 1)) {
			base.playlist.currentPlaylist = base.playlist.stackPlaylist[0];
		} else {
			base.playlist.currentPlaylist = base.playlist.stackPlaylist[base.playlist.currentPlaylist.id + 1];
		}
		base.playlist.playCurrent();
		return true;
	},
	playPrevious : function() {
		if (base.playlist.currentPlaylist.id === 0) {
			base.playlist.currentPlaylist = base.playlist.stackPlaylist[base.playlist.stackPlaylist.length - 1];
		} else {
			base.playlist.currentPlaylist = base.playlist.stackPlaylist[base.playlist.currentPlaylist.id - 1];
		}
		base.playlist.playCurrent();
		return true;
	},
	playRandom : function() {
		var s = Math.floor(Math.random()
				* (base.playlist.stackPlaylist.length - 0 + 1) + 0);
		console.log('S : ' + s);
		base.playlist.currentPlaylist = base.playlist.stackPlaylist[s];
		base.playlist.playCurrent();
		return true;
	},
	showCurrentSong : function() {
		Messenger()
				.post(
						{
							message : "<i class=\"icon-note\"></i>"
									+ base.playlist.currentPlaylist.cancion.nombre
									+ "<br/><i class=\"icon-mic\"></i>"
									+ base.playlist.currentPlaylist.cancion.artista.nombre
									+ "<br/><i class=\"icon-music\"></i>"
									+ base.playlist.currentPlaylist.cancion.album.nombre,
							type : 'info',
							showCloseButton : true
						});

	},
	bindEvents : function() {
		$(".jp-next").click(function(e) {
			base.playlist.playNext();
			e.preventDefault();
			return false;
		});
		$(".jp-previous").click(function(e) {
			base.playlist.playPrevious();
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
			base.playlist.showCurrentSong();
		});

		$(".jp-loop").click(
				function(e) {
					if (base.playlist.status.loop) {
						$("#jplayer").unbind(".repeat");
						base.playlist.status.loop = false;
						$(".jp-loop").removeClass('active');
					} else {
						$(".jp-loop").addClass('active');
						base.playlist.status.loop = true;
						$("#jplayer").bind($.jPlayer.event.ended + ".repeat",
								function() {
									if (base.playlist.random) {
										base.playlist.playRandom();
									} else {
										base.playlist.playNext();
									}
								});
					}
					e.preventDefault();
				});

		$(".jp-random").click(
				function(e) {
					if (base.playlist.status.random) {
						$("#jplayer").unbind(".random");
						base.playlist.status.random = false;
						$(".jp-random").removeClass('active');
					} else {
						$(".jp-random").addClass('active');
						base.playlist.status.random = true;
						$("#jplayer").bind($.jPlayer.event.ended + ".random",
								function() {
									base.playlist.playRandom();
								});
					}
					e.preventDefault();
				});
	}
}