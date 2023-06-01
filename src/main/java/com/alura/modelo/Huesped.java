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

	public Huesped(int id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, int idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
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
	}
}
