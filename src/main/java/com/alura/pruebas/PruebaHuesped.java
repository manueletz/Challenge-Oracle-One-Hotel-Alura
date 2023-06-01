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
		
		Date datefinal=new Date();
		try {
		datefinal = dateformat.parse("18-05-2023");
		System.out.println(dateformat.format(datefinal));
		}catch(ParseException e) {
			throw new RuntimeException(e);
		}
		
		Huesped huesped = new Huesped(1, "manuel", "estevez", datefinal, "Salvadoreña", "77887788");
		
		System.out.println(huesped);
		
		
	}

}
