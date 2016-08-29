package bb.org.bd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


public class Bankcode implements Serializable {
	private String bankCode;
	private String bankName;
	private String entUser;
	private Date entDate;
	private String shortName;
	private BigDecimal ewdRitCode;

	private List<Adscode> adscodeList;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getEntUser() {
		return entUser;
	}

	public void setEntUser(String entUser) {
		this.entUser = entUser;
	}

	public Date getEntDate() {
		return entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<Adscode> getAdscodeList() {
		return adscodeList;
	}

	public void setAdscodeList(List<Adscode> adscodeList) {
		this.adscodeList = adscodeList;
	}

	public BigDecimal getEwdRitCode() {
		return ewdRitCode;
	}

	public void setEwdRitCode(BigDecimal ewdRitCode) {
		this.ewdRitCode = ewdRitCode;
	}

	@Override
	public String toString() {
		return "Bankcode [bankCode=" + bankCode + ", bankName=" + bankName + ", entUser=" + entUser + ", entDate="
				+ entDate + ", shortName=" + shortName + ", ewdRitCode=" + ewdRitCode + ", adscodeList=" + adscodeList
				+ "]";
	}
	
	

}
