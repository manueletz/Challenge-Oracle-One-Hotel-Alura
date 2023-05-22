package com.alura.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alura.modelo.Huesped;

public class PruebaInsert {
	
	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC",
				"root",
				"root101");
		
	    Date date = new Date();  
	    System.out.println(date);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate= formatter.format(date);  
	    System.out.println(strDate); 
		
		Statement statement = con.createStatement();
		
		statement.execute("INSERT INTO HUESPEDES (ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO,"
				+ " NACIONALIDAD, TELEFONO) VALUES (3, 'ALEXA', 'ESTEVEZ', '"+strDate+"', 'SALVADOREÑA', '77449999');");
		
		//con.commit();
		
		con.close();
	}

}
