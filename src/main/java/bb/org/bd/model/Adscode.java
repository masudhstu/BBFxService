package bb.org.bd.model;

import java.math.BigDecimal;
import java.util.Date;

public class Adscode {
	private String adscode;
	private String adsAddr1;
	private String adsAddr2;
	private String bankCode;
	private BigDecimal districtCode;
	private Date entDate;
	private String entUser;
	private BigDecimal ewdRitCode;
	private String obuYn;
	private BigDecimal reportOffice;
	private String contactNo;
    public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHoYn() {
		return hoYn;
	}

	public void setHoYn(String hoYn) {
		this.hoYn = hoYn;
	}

	private String email;
    private String hoYn;

	public Adscode() {
	}

	public String getAdscode() {
		return adscode;
	}

	public void setAdscode(String adscode) {
		this.adscode = adscode;
	}

	public String getAdsAddr1() {
		return adsAddr1;
	}

	public void setAdsAddr1(String adsAddr1) {
		this.adsAddr1 = adsAddr1;
	}

	public String getAdsAddr2() {
		return adsAddr2;
	}

	public void setAdsAddr2(String adsAddr2) {
		this.adsAddr2 = adsAddr2;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public BigDecimal getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(BigDecimal districtCode) {
		this.districtCode = districtCode;
	}

	public Date getEntDate() {
		return entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public String getEntUser() {
		return entUser;
	}

	public void setEntUser(String entUser) {
		this.entUser = entUser;
	}

	public BigDecimal getEwdRitCode() {
		return ewdRitCode;
	}

	public void setEwdRitCode(BigDecimal ewdRitCode) {
		this.ewdRitCode = ewdRitCode;
	}

	public String getObuYn() {
		return obuYn;
	}

	public void setObuYn(String obuYn) {
		this.obuYn = obuYn;
	}

	public BigDecimal getReportOffice() {
		return reportOffice;
	}

	public void setReportOffice(BigDecimal reportOffice) {
		this.reportOffice = reportOffice;
	}

	@Override
	public String toString() {
		return "Adscode [adscode=" + adscode + ", adsAddr1=" + adsAddr1 + ", adsAddr2=" + adsAddr2 + ", bankCode="
				+ bankCode + ", districtCode=" + districtCode + ", entDate=" + entDate + ", entUser=" + entUser
				+ ", ewdRitCode=" + ewdRitCode + ", obuYn=" + obuYn + ", reportOffice=" + reportOffice + "]";
	}
	
	

}
