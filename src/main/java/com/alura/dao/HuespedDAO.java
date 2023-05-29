package com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alura.modelo.Huesped;
import com.alura.modelo.Reserva;

public class HuespedDAO {
	
	final private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public List<Huesped> buscar() {

		List<Huesped> huesped = new ArrayList<>();
		
		try {
			String sql = "SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES";
			System.out.println(sql);
		
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.execute();
				
				transformarResultSetEnReservas(huesped, pstm);
			}
			return huesped;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscarPorTextoBusqueda(String textoBusqueda) {
		List<Huesped> huesped = new ArrayList<>();
		
		try {
			String sql = "SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO,"
					+ " NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES WHERE APELLIDO = ? ";
					//+ " OR ID_RESERVA = ? ";
			System.out.println(sql);
		
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.setString(1, textoBusqueda);
				//pstm.setInt(2, Integer.valueOf(textoBusqueda));
				pstm.execute();
				
				transformarResultSetEnReservas(huesped, pstm);
			}
			return huesped;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscarPorIdReservaBusqueda(int idReserva) {
		List<Huesped> huesped = new ArrayList<>();
		
		try {
			String sql = "SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO,"
					+ " NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES WHERE ID_RESERVA = ? ";
					//+ " OR ID_RESERVA = ? ";
			System.out.println(sql);
		
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.setInt(1, idReserva);
				//pstm.setInt(2, Integer.valueOf(textoBusqueda));
				pstm.execute();
				
				transformarResultSetEnReservas(huesped, pstm);
			}
			return huesped;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultSetEnReservas(List<Huesped> huespedes, PreparedStatement pstm) throws SQLException {
		try(ResultSet rst = pstm.getResultSet()){
			while (rst.next()) {
				Huesped huesped = new Huesped(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				//System.out.println(huesped);
				huespedes.add(huesped);
			}
		}
		
	}
	
	
	public void guardar(Huesped huesped) {
		try(con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES "
					+"(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
					+ " VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				ejecutaRegistro(huesped, statement);
			} 
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(Huesped huesped, PreparedStatement statement)
			throws SQLException {
		
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    //String strDate= formatter.format(date);  
	    //System.out.println(strDate); 
	    
	    String strFechaNacimiento = formatter.format(huesped.getFechaNacimiento());
		
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setString(3, strFechaNacimiento);
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserva());
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println(String.format(
						"Fue insertado el huesped con ID %s", huesped));
			}
		}
		
	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO,"
							+ " TELEFONO FROM PRODUCTO");
			
			try(statement) {
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet){
					while (resultSet.next()) {
						resultado.add(new Huesped(
								resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHA_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO")));
					}
				}
			}
			
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {

		try{
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			
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

	public int modificar(String nombre, String apellido, Date fechaNacimiento,
			String nacionalidad, String telefono, int id) {
		try {
		
			final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
					+ " NOMBRE = ? "
					+ ", APELLIDO = ? "
					+ ", FECHA_NACIMIENTO = ? "
					+ ", NACIONALIDAD = ? "
					+ ", TELEFONO = ? "
					+ " WHERE ID = ?");
			
			try(statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, (java.sql.Date) fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listar(Integer idReserva) {
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT ID, NOMBRE, APELLIDO,"
					+ " FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO "
					+" FROM HUESPED "
					+"WHERE ID_RESERVA = ?";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con
					.prepareStatement(querySelect);
			
			try (statement) {
				statement.setInt(1, idReserva);
 				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					while (resultSet.next()) {
						resultado.add(new Huesped(
								resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHA_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO")));
					}
				}
			}
			
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
