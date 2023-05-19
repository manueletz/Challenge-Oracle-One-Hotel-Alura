package com.alura.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
	
	private Integer id;
	
	private Date fechaEntrada;
	
	private Date fechaSalida;
	
	private Integer valor;
	
	private String formaPago;
	
	private List<Huesped> huespedes;

	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, Integer valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor="
				+ valor + ", formaPago=" + formaPago + "]";
	}
	
	public void agregar(Huesped huesped) {
		if (this.huespedes == null) {
			this.huespedes = new ArrayList<>();
		}
		
		this.huespedes.add(huesped);
	}

	public List<Huesped> getHuespedes() {
		return this.huespedes;
	}
	

	/*
	private Integer id;
	
	private String nombre;
	
	private List<Producto> productos;

	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	public void agregar(Producto producto) {
		if (this.productos == null) {
			this.productos = new ArrayList<>();
		}
		
		this.productos.add(producto);
	}

	public List<Producto> getProductos() {
		return this.productos;
	}
	*/

}
