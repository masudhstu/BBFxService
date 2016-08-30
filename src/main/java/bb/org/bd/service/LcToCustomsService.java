package bb.org.bd.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import bb.org.bd.model.LcToCustoms;

public interface LcToCustomsService {
	
	LcToCustoms findLcdByLcID(BigInteger lcId);
	List<LcToCustoms> findLcsByTimeDuration(Date timeFrom, Date timeTo);
	List<LcToCustoms> findLcsByTimeDate(Date date);
	List<LcToCustoms> findLcsByfrequency(int frequency, int part);
}
