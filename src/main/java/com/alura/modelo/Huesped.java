package com.alura.modelo;

import java.util.Date;

public class Huesped {
	
	private Integer id;
	
	private String nombre;

	private String apellido;
	
	private Date fechaNacimiento;
	
	private String nacionalidad;
	
	private String telefono;
	
	private Integer idReserva;

	public Huesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public Huesped(int id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	@Override
	public String toString() {

		return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento="
				+ fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", id_reserva="
				+ idReserva + "]";

		/*
		return String.format(
				"{id: %s, nombre: %s, apellido: %s,"
				+ " fechaNacimiento: %s, nacionalidad: %s, telefono: %s}",
				this.id,
				this.nombre,
				this.apellido,
				this.fechaNacimiento,
				this.nacionalidad);
		*/

	}
	
	
/*		
	private String descripcion;
	private Integer cantidad;
	private Integer categoriaId;

	public Huesped(String nombre, String descripcion, Integer cantidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}
	public Huesped(int id, String nombre, String descripcion, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}
	public Huesped(int id, String nombre, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;		
	}
	public int getCategoriaId() {
		return this.categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	@Override
	public String toString() {
		return String.format(
				"{id: %s, nombre: %s, descripcion: %s, cantidad: %d}",
				this.id,
				this.nombre,
				this.descripcion,
				this.cantidad);
	}
*/
	
}
