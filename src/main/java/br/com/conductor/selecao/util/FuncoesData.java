package br.com.conductor.selecao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FuncoesData {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static String calendarToString(Calendar calendar) {
		if(calendar == null) {
			return null;
		}
		
		return sdf.format(calendar.getTime());
	}
	
	public static String dateToString(Date data) {
		return sdf.format(data);
	}
	
	public static Date stringToDate(String date) throws ParseException {
		if(date == null) {
			return null;
		}
		
		return sdf.parse(date);
	}
	
}
