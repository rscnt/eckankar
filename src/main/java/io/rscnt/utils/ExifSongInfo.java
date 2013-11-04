package io.rscnt.utils;

import java.util.HashMap;
import java.util.Map;

public class ExifSongInfo {
	private Map<String, String> exifTags = new HashMap<String, String>();

	private final String TITLE = "Title";
	private final String ARTIST = "Artist";
	private final String TRACK = "Track";
	private final String ALBUM_NAME = "Album";
	private final String PICTURE = "Picture";
	private final String GENRE_NAME = "Genre";
	private final String FILE_NAME = "File Name";

	public void setTag(String tagName, String value) {
		exifTags.put(tagName, value);
	}

	public String getTitle() {
		return exifTags.get(TITLE);
	}

	public String getAlbum() {
		return exifTags.get(ALBUM_NAME);
	}

	public String getArtist() {
		return exifTags.get(ARTIST);
	}

	public String getGenre() {
		return exifTags.get(GENRE_NAME);
	}

	public String getTrack(){
		return exifTags.get(TRACK);
	}

	public String getPicture() {
		return exifTags.get(PICTURE);
	}

	/**
	 * @return the fILE_NAME
	 */
	public String getFileName() {
		return exifTags.get(FILE_NAME);
	}
}