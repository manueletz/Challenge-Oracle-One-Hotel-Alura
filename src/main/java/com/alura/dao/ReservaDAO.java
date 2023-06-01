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


public class ReservaDAO {
	
	final private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public List<Reserva> buscar() {

		List<Reserva> reservas = new ArrayList<>();
		
		try {
			String sql = "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM RESERVAS";
			System.out.println(sql);
		
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.execute();
				
				transformarResultSetEnReservas(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> buscarReservasPorTextoBusqueda(String textoBusqueda) {
		List<Reserva> reserva = new ArrayList<>();
		
		try {
			String sql = "SELECT R.ID, R.FECHA_ENTRADA, R.FECHA_SALIDA, R.VALOR, FORMA_PAGO"
					+ " FROM RESERVAS R, HUESPEDES H WHERE R.ID = H.ID_RESERVA AND H.APELLIDO = ?; ";
			System.out.println(sql);
		
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.setString(1, textoBusqueda);
				pstm.execute();
				
				transformarResultSetEnReservas(reserva, pstm);
			}
			return reserva;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarReservasPorIdReservaBusqueda(int idReserva) {
		List<Reserva> reserva = new ArrayList<>();
		
		try {
			String sql = "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM RESERVAS WHERE ID = ?";
			System.out.println(sql);
		
			try(PreparedStatement pstm = con.prepareStatement(sql);){
				pstm.setInt(1, idReserva);
				pstm.execute();
				
				transformarResultSetEnReservas(reserva, pstm);
			}
			return reserva;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEnReservas(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try(ResultSet rst = pstm.getResultSet()){
			while (rst.next()) {
				Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getInt(4), rst.getString(5));
				
				reservas.add(reserva);
			}
		}
		
	}

	public List<Reserva> listarConHuespedes() {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT R.ID, R.FECHA_ENTRADA, R.FECHA_SALIDA, R.VALOR, R.FORMA_PAGO,"
					+ " H.ID, H.NOMBRE, H.APELLIDO, H.FECHA_NACIMIENTO, H.NACIONALIDAD,"
					+ " H.TELEFONO " 
					+"FROM RESERVA R "
					+ "INNER JOIN HUESPED H ON R.ID = H.ID_RESERVA";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.prepareStatement(
					querySelect);
			
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {

						Integer idReserva = resultSet.getInt("R.ID");
						Date fechaEntrada = resultSet.getDate("R.FECHA_ENTRADA");
						Date fechaSalida = resultSet.getDate("R.FECHA_SALIDA");
						Integer valor = resultSet.getInt("R.VALOR");
						String formaPago = resultSet.getString("R.FORMA_PAGO");
						
						var categoria = resultado
								.stream()
								.filter(cat -> cat.getId().equals(idReserva))
								.findAny().orElseGet(() -> {
									Reserva res = new Reserva(idReserva,fechaEntrada, fechaSalida,
											valor, formaPago);
									resultado.add(res);
									
									return res;
								});
						
						Huesped huesped = new Huesped (resultSet.getInt("H.ID"),
								resultSet.getString("H.NOMBRE"),
								resultSet.getString("H.APELLIDO"),
								resultSet.getDate("H.FECHA_NACIMIENTO"),
								resultSet.getString("H.NACIONALIDAD"),
								resultSet.getString("H.TELEFONO"));
						
						categoria.agregar(huesped); 
					}
				};
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

	public void guardar(Reserva reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS "
					+"(FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO)"
					+ " VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				ejecutaRegistro(reserva, statement);
			} 
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    
	    String strFechaEntrada = formatter.format(reserva.getFechaEntrada());
	    String strFechaSalida = formatter.format(reserva.getFechaSalida());
		
		statement.setString(1, strFechaEntrada);
		statement.setString(2, strFechaSalida);
		statement.setInt(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(String.format(
						"Fue insertado la reserva con ID %s", reserva));
			}
		}
	}

	public void editarReserva(Reserva reserva) {
		//try(con) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE RESERVAS SET FECHA_ENTRADA = ?, FECHA_SALIDA = ?, VALOR = ?, FORMA_PAGO = ? WHERE ID = ?");
			
			try (statement) {
				ejecutaEdicion(reserva, statement);
				System.out.println("Edicion de Reserva con Id: "+reserva.getId());

			} 
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void ejecutaEdicion(Reserva reserva, PreparedStatement statement) throws SQLException {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    
	    String strFechaEntrada = formatter.format(reserva.getFechaEntrada());
	    String strFechaSalida = formatter.format(reserva.getFechaSalida());

	    statement.setString(1, strFechaEntrada);
		statement.setString(2, strFechaSalida);
		statement.setInt(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());
		statement.setInt(5, reserva.getId());
		
		statement.execute();
	}

	public void eliminarReserva(int id) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"DELETE FROM RESERVAS WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				System.out.println("Eliminación de Reserva con Id: " + id);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	

}
