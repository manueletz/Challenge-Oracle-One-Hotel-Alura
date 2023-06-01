package com.alura.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
	
	public Reserva(Date fechaEntrada, Date fechaSalida, Integer valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public Integer getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
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
	
	public long DiasEntreDosFechas(Date fechaEntrada, Date fechaSalida){
	    Date primeraFecha;
	    Date segundaFecha;
	    SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		try {
			primeraFecha = formatoFecha.parse(String.valueOf(fechaEntrada));
			segundaFecha = formatoFecha.parse(String.valueOf(fechaSalida));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	    long diferencia = segundaFecha.getTime() - primeraFecha.getTime();
	    
	    TimeUnit time = TimeUnit.DAYS; 
	    long diferenciaDias = time.convert(diferencia, TimeUnit.MILLISECONDS);

	    System.out.println("The difference in days is : "+diferenciaDias);
	    return diferenciaDias;
	}
	
	public int calcularTarifaEstadia(Date fechaEntrada, Date fechaSalida) {
		int diasEstadia = (int) DiasEntreDosFechas(fechaEntrada, fechaSalida);
		int tarifaDiaria = 20;
		return diasEstadia * tarifaDiaria;
	}
}
