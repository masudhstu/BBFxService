package bb.org.bd.model;

import java.math.BigInteger;

public class HeaderParams {

	private BigInteger id_log;
	private String dataType; // lc/exp/1/2
	private String queryType;
	private String date;
	private String timeFrom;
	private String timeTo;
	private String lc;
	private String exp;

	public HeaderParams(String dataType, String queryType, String date, String timeFrom, String timeTo, String lc,
			String exp) {
		super();
		this.dataType = dataType;
		this.queryType = queryType;
		this.date = date;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.lc = lc;
		this.exp = exp;
	}

	public BigInteger getId_log() {
		return id_log;
	}

	public void setId_log(BigInteger id_log) {
		this.id_log = id_log;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "HeaderParams [dataType=" + dataType + ", queryType=" + queryType + ", date=" + date + ", timeFrom="
				+ timeFrom + ", timeTo=" + timeTo + ", lc=" + lc + ", exp=" + exp + "]";
	}

}
