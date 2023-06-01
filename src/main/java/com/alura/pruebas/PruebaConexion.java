package com.alura.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alura.modelo.Huesped;

public class PruebaConexion {
	
	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC",
				"root",
				"root101");
		
		Statement statement = con.createStatement();
		
		statement.execute("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO,"
				+ " NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES");
		
		ResultSet resultSet = statement.getResultSet();
		
		//List<Map<String, String>> resultado = new ArrayList<>(); //EL ORDEN LO CREA COMO CREA CONVENIENTE
		List<LinkedHashMap<String, String>> resultado2 = new ArrayList<>(); //SE CREA EN ORDEN
		
		Huesped huesped;
		
		while (resultSet.next()) {
			//Map<String,String> fila = new HashMap<>();
			LinkedHashMap<String,String> fila = new LinkedHashMap<>();
			
			fila.put("ID", String.valueOf(resultSet.getInt("ID")));			
			fila.put("NOMBRE", resultSet.getString("NOMBRE"));
			fila.put("APELLIDO", resultSet.getString("APELLIDO"));
			fila.put("FECHA_NACIMIENTO", String.valueOf(resultSet.getDate("FECHA_NACIMIENTO")));
			fila.put("NACIONALIDAD", resultSet.getString("NACIONALIDAD"));
			fila.put("TELEFONO", resultSet.getString("TELEFONO"));
			fila.put("ID_RESERVA", String.valueOf(resultSet.getInt("ID_RESERVA")));	
			resultado2.add(fila);
			
			huesped = new Huesped(resultSet.getInt("ID"),
					resultSet.getString("NOMBRE"),
					resultSet.getString("APELLIDO"),
					resultSet.getDate("FECHA_NACIMIENTO"),
					resultSet.getString("NACIONALIDAD"),
					resultSet.getString("TELEFONO"));
			System.out.println(huesped);
			
		}
	
		System.out.println(resultado2);
		
		con.close();
	}

}
