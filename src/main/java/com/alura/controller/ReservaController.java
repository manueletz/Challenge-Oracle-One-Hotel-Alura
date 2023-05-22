package com.alura.controller;

import java.util.Date;
import java.util.List;

import com.alura.dao.ReservaDAO;
import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Huesped;
import com.alura.modelo.Reserva;


public class ReservaController {
	
	private ReservaDAO reservaDAO;
	private Reserva reserva;
	
	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}
	

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}

    public List<Reserva> cargaReporte() {
        return this.reservaDAO.listarConHuespedes();
    }
    
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}
	
	public int calcularTarifaEstadia(Date fechaEntrada, Date fechaSalida) {
		return reserva.calcularTarifaEstadia(fechaEntrada, fechaSalida);
	}

}
