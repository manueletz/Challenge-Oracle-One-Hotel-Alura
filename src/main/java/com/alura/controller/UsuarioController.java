package com.alura.controller;

import com.alura.dao.UsuarioDAO;
import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Usuario;

public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioController() {
		var factory = new ConnectionFactory();
		this.usuarioDAO = new UsuarioDAO(factory.recuperaConexion());
	}
	
	public Usuario buscarPorId(int id) {
		return usuarioDAO.buscarPorId(id);
	}
	public Usuario buscarPorNombreUsuario(String nombreUsuario) {
		return usuarioDAO.buscarPorNombreUsuario(nombreUsuario);
	}
}
