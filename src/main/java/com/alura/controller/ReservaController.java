package com.alura.controller;

import java.util.Date;
import java.util.List;

import com.alura.dao.ReservaDAO;
import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Reserva;


public class ReservaController {
	
	private ReservaDAO reservaDAO;
	private Reserva reserva;
	
	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}

	public List<Reserva> buscar() {
		return reservaDAO.buscar();
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
	
	public List<Reserva> buscarReservasPorTextoBusqueda(String textoBusqueda) {
		return reservaDAO.buscarReservasPorTextoBusqueda(textoBusqueda);
	}
	
	public List<Reserva> buscarReservasPorIdReservaBusqueda(int idReserva) {
		return reservaDAO.buscarReservasPorIdReservaBusqueda(idReserva);
	}

	public void editarReserva(Reserva reserva) {
		reservaDAO.editarReserva(reserva);
	}

	public void eliminarReserva(int id) {
		reservaDAO.eliminarReserva(id);
		
	}

}
