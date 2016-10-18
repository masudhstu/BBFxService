package bb.org.bd.utils;

import org.apache.log4j.Logger;

public class LoggerProvider {
	
	public static Logger getLogger(Class<?> neededClass)
	{
		return Logger.getLogger(neededClass);
	}

}
