/*jshint undef: true, unused: true */
/* global $ */
/* global Messenger */
/* global Template */
/*
 * This script is ugly as fuck.
 * But....
 */

var eckankar = {
	init : function() {
		this.setMessenger();

	},
	setMessenger : function() {
		if (typeof Messenger !== 'undefined') {
			Messenger.options = {
				extraClasses : 'messenger-fixed messenger-on-top messenger-on-right',
				theme : 'future'
			};
			return true;
		} else {
			return false;
		}
	}

};