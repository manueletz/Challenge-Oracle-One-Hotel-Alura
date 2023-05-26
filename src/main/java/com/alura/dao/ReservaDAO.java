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

	/*
	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM RESERVAS";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.prepareStatement(
					querySelect);
			
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {
						var categoria = new Reserva(resultSet.getInt("ID"),
								resultSet.getDate("FECHA_ENTRADA"),
								resultSet.getDate("FECHA_SALIDA"),
								resultSet.getInt("VALOR"),
								resultSet.getString("FORMA_PAGO"));
						
						resultado.add(categoria);
					}
				};
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
	*/

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

	private void transformarResultSetEnReservas(List<Reserva> reservas, PreparedStatement resultSet) throws SQLException {
		try(ResultSet rst = resultSet.getResultSet()){
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
		try(con) {
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
	    //Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    //String strDate= formatter.format(date);  
	    //System.out.println(strDate); 
	    
	    String strFechaEntrada = formatter.format(reserva.getFechaEntrada());
	    String strFechaSalida = formatter.format(reserva.getFechaSalida());
		
//		statement.setDate(1, (java.sql.Date) reserva.getFechaEntrada());
//		statement.setDate(2, (java.sql.Date) reserva.getFechaSalida());
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

}
