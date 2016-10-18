package bb.org.bd.service;

import java.math.BigInteger;
import java.util.List;

import bb.org.bd.model.LcToCustoms;
import bb.org.bd.model.lc.LC;
import bb.org.bd.model.lc.LC_INFO;

public interface LcToCustomsService {
	
	
	LC findLcdByLcID(String lc);
	LC findLcsByDate(String date);
	LC findLcsByDateAndTimeRange(String date, String timeFrom, String timeTo);
	
	LcToCustoms findLcdByLcID(BigInteger lcId);
	List<LC_INFO> findLcInfossByDate(String date);	
	List<LcToCustoms> findLcsByfrequency(int frequency, int part);
}
