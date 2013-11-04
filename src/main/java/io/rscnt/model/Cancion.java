package io.rscnt.model;

/**
 * 
 * @author rscnt <rscnt.github.io>
 * @date 28/10/2013
 * @time 20:00
 *
 */
public class Cancion {

	private Integer codigo;
	private String fName;
	private String nombre;
	private Integer track;
	private Integer duracion;
	private String imagen_src;
	private Artista artista;
	private Genero genero;
	private Album album;
	
	/**
	 * @param codigo
	 * @param nombre
	 * @param track
	 * @param duracion
	 * @param artista
	 * @param genero
	 * @param album
	 * @param imagen_src
	 */
	public Cancion(Integer codigo, String fName, String nombre, Integer track,
			Integer duracion, Artista artista, Genero genero, Album album,
			String imagen_src) {
		this.codigo = codigo;
		this.fName = fName;
		this.nombre = nombre;
		this.track = track;
		this.duracion = duracion;
		this.artista = artista;
		this.genero = genero;
		this.album = album;
		this.imagen_src = imagen_src;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @return the artista
	 */
	public Artista getArtista() {
		return artista;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the duracion
	 */
	public Integer getDuracion() {
		return duracion;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @return the imagen_src
	 */
	public String getImagen_src() {
		return imagen_src;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the track
	 */
	public Integer getTrack() {
		return track;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	/**
	 * @param artista the artista to set
	 */
	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @param imagen_src the imagen_src to set
	 */
	public void setImagen_src(String imagen_src) {
		this.imagen_src = imagen_src;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param track the track to set
	 */
	public void setTrack(Integer track) {
		this.track = track;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (getAlbum() != null)
			builder.append("\"album\" : ").append(getAlbum()).append(", ");
		if (getArtista() != null)
			builder.append("\"artista\" : ").append(getArtista()).append(", ");
		if (getCodigo() != null)
			builder.append("\"codigo\" : ").append(getCodigo()).append(", ");
		if (getDuracion() != null)
			builder.append("\"duracion\" : ").append(getDuracion()).append(", ");
		if (getGenero() != null)
			builder.append("\"genero\" : ").append(getGenero()).append(", ");
		if (getImagen_src() != null)
			builder.append("\"imagen_src\" : ").append(getImagen_src())
					.append(", ");
		if (getNombre() != null)
			builder.append("\"nombre\" : ").append(getNombre()).append(", ");
		if (getTrack() != null)
			builder.append("\"track\" : ").append(getTrack());
		builder.append("}");
		return builder.toString();
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	

}
