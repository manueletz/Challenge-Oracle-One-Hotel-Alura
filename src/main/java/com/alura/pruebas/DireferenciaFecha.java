package com.alura.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DireferenciaFecha {
	public static void main(String[] args) throws ParseException {
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    Date firstDate = sdf.parse("04/22/2020");
	    Date secondDate = sdf.parse("04/27/2020");

	    long diff = secondDate.getTime() - firstDate.getTime();
	    
	    System.out.println(diff);

	    TimeUnit time = TimeUnit.DAYS; 
	    long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);

	    System.out.println("The difference in days is : "+diffrence);
	}
}
