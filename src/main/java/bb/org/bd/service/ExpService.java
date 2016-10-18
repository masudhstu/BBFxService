package bb.org.bd.service;

import bb.org.bd.model.exp.Exp_Info;

public interface ExpService {
	
	Exp_Info findExpByExpNo(String ExpNo);
	Exp_Info findExpByDate(String date);
	Exp_Info findExpByDateAndTimeRange(String date, String timeFrom, String timeTo);

}
