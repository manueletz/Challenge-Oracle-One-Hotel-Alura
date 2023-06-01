package com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.modelo.Huesped;
import com.alura.modelo.Reserva;
import com.alura.modelo.Usuario;

public class UsuarioDAO {
	
	final private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public int eliminar(Integer id) {

		try{
			final PreparedStatement statement = con.prepareStatement("SELECT ID, USUARIO, CLAVE FROM USUARIOS WHERE ID = ?");
			
			try(statement) {
				statement.setInt(1, id);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario buscarPorId(Integer id) {

		Usuario usuario = new Usuario();
		
		try {
			String sql = "SELECT ID, USUARIO, NOMBRE FROM USUARIOS WHERE ID = ?";
			System.out.println(sql);
		
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.setInt(1, id);
				pstm.execute();
				
				transformarResultSetEnUsuario(usuario, pstm);
			}
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario buscarPorNombreUsuario(String nombreUsuario) {
		
		Usuario usuario = new Usuario();
		
		try {
			String sql = "SELECT ID, USUARIO, CLAVE FROM USUARIOS WHERE USUARIO = ?";
			System.out.println(sql);
			
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.setString(1, nombreUsuario);
				pstm.execute();
				
				transformarResultSetEnUsuario(usuario, pstm);
			}
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEnUsuario(Usuario usuario, PreparedStatement pstm) throws SQLException {
		try(ResultSet rst = pstm.getResultSet()){
			while (rst.next()) {
				usuario.setId(rst.getInt(1));
				usuario.setUsuario(rst.getString(2));
				usuario.setClave(rst.getString(3));
			}
		}
	}
}
