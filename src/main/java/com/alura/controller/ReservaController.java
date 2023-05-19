package com.alura.controller;

import java.util.List;

import com.alura.dao.ReservaDAO;
import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Reserva;


public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}
	

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}

    public List<Reserva> cargaReporte() {
        return this.reservaDAO.listarConProductos();
    }

}
