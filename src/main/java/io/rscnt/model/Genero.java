package io.rscnt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`Genero`")
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "imagen_src")
	private String imagen_src;

	public Genero() {
	}

	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param imagen_src
	 */
	public Genero(Long codigo, String nombre, String descripcion,
			String imagen_src) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen_src = imagen_src;
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
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
	 * @param descripcion
	 *            the descripcion to set
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
	 * @param imagen_src
	 *            the imagen_src to set
	 */
	public void setImagen_src(String imagen_src) {
		this.imagen_src = imagen_src;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (descripcion != null)
			builder.append("\"descripcion\" : ").append(descripcion)
					.append(", ");
		if (imagen_src != null)
			builder.append("\"imagen_src\" : ").append(imagen_src);
		builder.append("}");
		return builder.toString();
	}

}
