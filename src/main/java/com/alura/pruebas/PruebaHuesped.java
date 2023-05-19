package com.alura.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alura.modelo.Huesped;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class PruebaHuesped {
	public static void main(String[] args) {
		Date date = new Date(); 
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		
		//Date date = new Date();
		
		try {
		date = dateformat.parse("18-05-2023");
		System.out.println(dateformat.format(date));
		}catch(ParseException e) {
			throw new RuntimeException(e);
		}
		
		
		Huesped huesped = new Huesped(1, "manuel", "estevez", '2023-05-05', "Salvadore�a", "77887788");
		
		System.out.println(huesped);
		
		
	}

}
