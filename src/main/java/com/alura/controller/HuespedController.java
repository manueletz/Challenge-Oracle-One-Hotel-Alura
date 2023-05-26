package com.alura.controller;

import java.util.Date;
import java.util.List;

import com.alura.dao.HuespedDAO;
import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Huesped;
import com.alura.modelo.Reserva;


public class HuespedController {
	
	private HuespedDAO huespedDao;
	
	public HuespedController() {
		this.huespedDao = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(String nombre, String apellido, Date fechaNacimiento,
			String nacionalidad, String telefono, int id) {
		return huespedDao.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, id);
	}

	public int eliminar(Integer id) {
		return huespedDao.eliminar(id);
	}

	public List<Huesped> listar() {
		return huespedDao.listar();
	}
	
	public List<Huesped> buscar() {
		return huespedDao.buscar();
	}
	
	public List<Huesped> listar(Reserva reserva){
		return huespedDao.listar(reserva.getId());
	}

	public void guardar(Huesped huesped, Integer idReserva) {
		huesped.setIdReserva(idReserva);
		huespedDao.guardar(huesped);
	}
	
}
