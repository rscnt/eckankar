package io.rscnt.model;

public class Album {

	private Integer codigo;
	private String nombre;
	private String imagen_src;
	private Double precio;
	private Genero genero;
	private Artista artista;
	/**
	 * @param codigo
	 * @param nombre
	 * @param imagen_src
	 * @param precio
	 * @param genero
	 * @param artista
	 */
	public Album(Integer codigo, String nombre, String imagen_src,
			Double precio, Genero genero, Artista artista) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.imagen_src = imagen_src;
		this.precio = precio;
		this.genero = genero;
		this.artista = artista;
	}
	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the imagen_src
	 */
	public String getImagen_src() {
		return imagen_src;
	}
	/**
	 * @param imagen_src the imagen_src to set
	 */
	public void setImagen_src(String imagen_src) {
		this.imagen_src = imagen_src;
	}
	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}
	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	/**
	 * @return the artista
	 */
	public Artista getArtista() {
		return artista;
	}
	/**
	 * @param artista the artista to set
	 */
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (codigo != null)
			builder.append("\"codigo\" : ").append(codigo).append(", ");
		if (nombre != null)
			builder.append("\"nombre\" : ").append(nombre).append(", ");
		if (imagen_src != null)
			builder.append("\"imagen_src\" : ").append(imagen_src).append(", ");
		if (precio != null)
			builder.append("\"precio\" : ").append(precio).append(", ");
		if (genero != null)
			builder.append("\"genero\" : ").append(genero).append(", ");
		if (artista != null)
			builder.append("\"artista\" : ").append(artista);
		builder.append("}");
		return builder.toString();
	}
	
	
}