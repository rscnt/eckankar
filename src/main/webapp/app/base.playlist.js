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
	stackPlaylist : new Array(), // [{el: $(".song-playlist), cancion: {} id:
	// 0}]
	currentPlaylist : {
		el : null,
		cancion : null,
		id : null
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

		base.playlist.playSong(base.playlist.currentPlaylist.cancion.fName);
	},
	bindEvents : function() {
		$(".jp-next")
				.click(
						function(e) {
							if (base.playlist.currentPlaylist.id === (base.playlist.stackPlaylist.length - 1)) {
								base.playlist.currentPlaylist = base.playlist.stackPlaylist[0];
							} else {
								base.playlist.currentPlaylist = base.playlist.stackPlaylist[base.playlist.currentPlaylist.id + 1];
							}
							console.log('-- next --');
							console.log(base.playlist.currentPlaylist.cancion);
							console.log('-- next --');
							base.playlist.playCurrent();
							e.preventDefault();
							return false;
						});
		$(".jp-previous")
				.click(
						function(e) {
							if (base.playlist.currentPlaylist.id === 0) {
								console
										.log(base.playlist.stackPlaylist[base.playlist.stackPlaylist.length - 1]);
								base.playlist.currentPlaylist = base.playlist.stackPlaylist[base.playlist.stackPlaylist.length - 1];
							} else {
								base.playlist.currentPlaylist = base.playlist.stackPlaylist[base.playlist.currentPlaylist.id - 1]; // never
								// go
								// never
							}
							console.log('-- previous --');
							console.log(base.playlist.currentPlaylist.cancion);
							console.log('-- previous --');
							base.playlist.playCurrent();
							e.preventDefault();
							return false;
						});
	}
}