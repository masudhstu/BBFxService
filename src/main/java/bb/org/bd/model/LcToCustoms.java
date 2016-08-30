package bb.org.bd.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

public class LcToCustoms {
	
	private BigInteger SLNo;    
    private BigInteger lcId;    
    private Date lcDate;
    private Date lcExpiryDate;
    private Date lastShipmentDate;
    private Integer updateStatus;
    private String updatedate;
    private BigDecimal lcAmount;
    private String currencyCode;
    private String countryCode;
    private BigInteger bin;
    private String impName;
    private String beneficiaryName;

    public LcToCustoms() {
    }


	public BigInteger getSLNo() {
		return SLNo;
	}


	public void setSLNo(BigInteger sLNo) {
		SLNo = sLNo;
	}


	public BigInteger getLcId() {
		return lcId;
	}

	public void setLcId(BigInteger lcId) {
		this.lcId = lcId;
	}

	public Date getLcDate() {
		return lcDate;
	}

	public void setLcDate(Date lcDate) {
		this.lcDate = lcDate;
	}

	public Date getLcExpiryDate() {
		return lcExpiryDate;
	}

	public void setLcExpiryDate(Date lcExpiryDate) {
		this.lcExpiryDate = lcExpiryDate;
	}

	public Date getLastShipmentDate() {
		return lastShipmentDate;
	}

	public void setLastShipmentDate(Date lastShipmentDate) {
		this.lastShipmentDate = lastShipmentDate;
	}

	public Integer getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public BigDecimal getLcAmount() {
		return lcAmount;
	}

	public void setLcAmount(BigDecimal lcAmount) {
		this.lcAmount = lcAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public BigInteger getBin() {
		return bin;
	}

	public void setBin(BigInteger bin) {
		this.bin = bin;
	}

	public String getImpName() {
		return impName;
	}

	public void setImpName(String impName) {
		this.impName = impName;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	@Override
	public String toString() {
		return "LcToCustoms [SLNo=" + SLNo + ", lcId=" + lcId + ", lcDate=" + lcDate + ", lcExpiryDate=" + lcExpiryDate
				+ ", lastShipmentDate=" + lastShipmentDate + ", updateStatus=" + updateStatus + ", updatedate="
				+ updatedate + ", lcAmount=" + lcAmount + ", currencyCode=" + currencyCode + ", countryCode="
				+ countryCode + ", bin=" + bin + ", impName=" + impName + ", beneficiaryName=" + beneficiaryName + "]";
	}
    
    
	
}
