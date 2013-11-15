/* global Templates */
/* Lazy object, yeah... shame on me.. */
var Templates = {};

Templates.uploadTable = "<table id=\"pre-upload-table\" class=\"striped rounded l-centered\">"
		+ "<thead class=\"l-centered\"> "
		+ "<tr class=\"l-centered\">"
		+ "<th class=\"l-centered\">Nombre Archivo</th>"
		+ "<th class=\"l-centered\">Tamaño Archivo</th>"
		+ "<th class=\"l-centered\">Tipo Archivo</th>"
		+ "<th></th>"
		+ "</tr>"
		+ "</thead>"
		+ "<tbody class=\"l-centered\">"
		+ "<%"
		+ "_.each(files, function(file, key, list) {"
		+ "%>"
		+ "<tr class=\"l-centered\" id=\"upload-<%= key %>\" data-key=\"<%= key %>\" >"
		+ "<td class=\"l-centered\"> <%= file.name %> </td>"
		+ "<td class=\"l-centered\"> ~ <%= Math.floor(file.size/1048576) %> MB </td>"
		+ "<td class=\"l-centered\"> <%= file.type %> </td>"
		+ "<td class=\"l-centered\">  "
		+ "<a href=\"#\" class=\"tiny danger btn quit\""
		+ "style=\"color: #FCFCFC\" data-id=\"<%= key %>\">"
		+ "<i class=\"icon-cancel\"></i>"
		+ "</a>"
		+ "</td>"
		+ "</tr>"
		+ "<% "
		+ "});" + "%>" + "</tbody>" + "</table>";

Templates.uploadedTempalte = "<div class=\"row\"><div class=\"default alert twelve columns\"><br/>"
		+ "<div class=\"four columns l-centered\">"
		+ "<img class=\"album-cover\" src=\"/covers/<%= album %>.jpg\" />"
		+ "</div>"
		+ "<div class=\"seven columns l-centered\">"
		+ "<h4><i class=\"icon-note\" /><%= cancion.nombre %></h4>"
		+ "<p class=\"l-centered\"><i class=\"icon-mic\" /><%= cancion.artista.nombre %></p>"
		+ "<p class=\"l-centered\"><i class=\"icon-music\" /><%= cancion.album.nombre %></p>"
		+ "<p class=\"l-centered\"><i class=\"icon-note-beamed\" /><%= cancion.genero.nombre %></p>"
		+ "</div> </article> </div></div>";

Templates.playlistSong = "<div data-id=\"<%= id %>\" class=\"song-playlist\">"
		+ "<div class=\"song-playlist-art\">"
		+ "<img class=\"song-playlist-cover\""
		+ "src=\"/covers/<%= album %>.jpg\" alt=\"\" />"
		+ "</div>"
		+ "<div class=\"song-playlist-info\">"
		+ "<div class=\"song-playlist-song\"><%= cancion.nombre %></div>"
		+ "<div class=\"song-playlist-artist\"><%= cancion.artista.nombre %></div></div>"
		+ "</div>";

Templates.artistView = "<li data-id=\"<%= artista.codigo %>\" id=\"<%= artista.codigo %>\"  class=\"artist-view\">"
		+ "<div class=\"row\"> <div class=\"twelve columns\">"
		+ "<img class=\"artist-cover\" src=\"<%= artista.imagen_src %>\"/>"
		+ "</div>"
		+ "</div>"
		+ "<div class=\"row\">"
		+ "<div class=\"twelve columns\">"
		+ "<h3 class=\"artist-title\"> <%= artista.nombre %> </h3>"
		+ "</div>"
		+ "</div>"
		+ "<div class=\"row\">"
		+ "<div class=\"twelve columns\">"
		+ "<p class=\"artist-descp\">  <%= artista.descripcion %> </p>"
		+ "</div>" + "</div>" + "</li>";
