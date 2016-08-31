package bb.org.bd.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Miscellaneous {
	
	private static final int TOTALMINUTESINADAY = 1440;
	
	public static boolean isValidFrequency(int frequency)
	{
		if(frequency > 48 && frequency < 288)
			return true;
		
			
		if(TOTALMINUTESINADAY % frequency == 0)
			return true;
		
		
		return false;
	}
	public static String getTimeRange(int frequency, int part)
	{
		String today = "";
		String fromTime = "";
		String toTime = "";
		
		if(isValidFrequency(frequency))
		{
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH24:mm:ss");
		    
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			int second = now.get(Calendar.SECOND);
			int millis = now.get(Calendar.MILLISECOND);
			
			today = year + "/" + month + "/" + day ;
			
			int multiplierMinute = TOTALMINUTESINADAY / frequency;			
			//calculating from time
			multiplierMinute = multiplierMinute * (part - 1);			
			hour = multiplierMinute / 60;
			multiplierMinute = multiplierMinute % 60;			
			minute = multiplierMinute;
			
			fromTime = today + " " + hour + ":" + minute + ":" + "00" ;
			now.set(year, month, day, hour, minute, 0);
			
			date = now.getTime();			
			formatter.format(date);
			
			System.out.println(date);
			
			//calculating from time
			multiplierMinute = TOTALMINUTESINADAY / frequency;	
			multiplierMinute = multiplierMinute * part;			
			hour = multiplierMinute / 60;
			multiplierMinute = multiplierMinute % 60;			
			minute = multiplierMinute;
			
			toTime = today + " " + hour + ":" + minute + ":" + "00" ;
			now.set(year, month, day, hour, minute, 0);
			
			date = now.getTime();			
			formatter.format(date);
			
			System.out.println(date);
		}
		
		
		return "";
	}

}
