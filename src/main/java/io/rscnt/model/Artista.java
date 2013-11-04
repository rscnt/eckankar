package io.rscnt.model;

public class Artista {
	
	private Integer codigo;
	private String nombre;
	private String descripcion;
	private String imagen_src;
	
	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param imagen_src
	 */
	public Artista(Integer codigo, String nombre, String descripcion,
			String imagen_src) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen_src = imagen_src;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ ");
		if (codigo != null)
			builder.append("\"codigo\" : ").append(codigo).append(", ");
		if (nombre != null)
			builder.append("\"nombre\" : ").append(nombre).append(", ");
		if (descripcion != null)
			builder.append("\"descripcion\" : ").append(descripcion).append(", ");
		if (imagen_src != null)
			builder.append("\"imagen_src\" : ").append(imagen_src);
		builder.append("}");
		return builder.toString();
	}
	
	

}
