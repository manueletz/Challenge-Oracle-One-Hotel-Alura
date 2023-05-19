package com.alura.modelo;

import java.util.Date;

public class Huesped {
	
	private Integer id;
	
	private String nombre;

	private String apellido;
	
	private Date fecha_nacimiento;
	
	private String nacionalidad;
	
	private String telefono;
	
	private Integer id_reserva;

	public Huesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public Huesped(int id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
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

	public Integer getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(Integer id_reserva) {
		this.id_reserva = id_reserva;
	}

	@Override
	public String toString() {

		return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento="
				+ fecha_nacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", id_reserva="
				+ id_reserva + "]";

		
		/*
		return String.format(
				"{id: %s, nombre: %s, apellido: %s,"
				+ " fecha_nacimiento: %s, nacionalidad: %s, telefono: %s}",
				this.id,
				this.nombre,
				this.apellido,
				this.fecha_nacimiento,
				this.nacionalidad,
				this.telefono);
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
