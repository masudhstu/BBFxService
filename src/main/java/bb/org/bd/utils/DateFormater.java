package bb.org.bd.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {

	public static String formateDate(String strDate) {

		/*
		 * System.out.println(strDate); DateFormat df = new
		 * SimpleDateFormat("yyyy-mm-dd"); Date date = new Date(); try { date =
		 * df.parse(strDate); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.println(df.format(date));
		 * 
		 * return df.format(date);
		 */

		return strDate;
	}

	public static String formateDate(Date strDate) {

		System.out.println(strDate);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		//return df.format(strDate);
		return strDate.toString();
	}

}
