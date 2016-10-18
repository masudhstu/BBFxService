package bb.org.bd.model.lc;

import java.io.Serializable;

import bb.org.bd.utils.DateFormater;

public class LC_INFO implements Serializable {
	
	private String LC_ID;
	private String LC_DATE;
	private String LC_EXPIRY_DATE;
	private String LAST_SHIPMENT_DATE;
	private String UPDATE_STATUS;
	private String UPDATE_DATE;
	private String LC_AMOUNT;
	private String CURRENCY;
	private String COUNTRY_CODE_IMP;
	private String BIN;
	private String IMPORTERNAME;
	private String BENEFICIARY;
	
	private LC_DETAIL lc_detail;

	public String getLC_ID() {
		return LC_ID;
	}

	public void setLC_ID(String lC_ID) {
		LC_ID = lC_ID;
	}

	public String getLC_DATE() {
		return LC_DATE;
	}

	public void setLC_DATE(String lC_DATE) {
		LC_DATE = DateFormater.formateDate(lC_DATE);
	}

	public String getLC_EXPIRY_DATE() {
		return LC_EXPIRY_DATE;
	}

	public void setLC_EXPIRY_DATE(String lC_EXPIRY_DATE) {
		LC_EXPIRY_DATE = DateFormater.formateDate(lC_EXPIRY_DATE);;
	}

	public String getLAST_SHIPMENT_DATE() {
		return LAST_SHIPMENT_DATE;
	}

	public void setLAST_SHIPMENT_DATE(String lAST_SHIPMENT_DATE) {
		LAST_SHIPMENT_DATE = DateFormater.formateDate(lAST_SHIPMENT_DATE);;
	}

	public String getUPDATE_STATUS() {
		return UPDATE_STATUS;
	}

	public void setUPDATE_STATUS(String uPDATE_STATUS) {
		UPDATE_STATUS = uPDATE_STATUS;
	}

	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(String uPDATE_DATE) {
		UPDATE_DATE = DateFormater.formateDate(uPDATE_DATE);;
	}

	public String getLC_AMOUNT() {
		return LC_AMOUNT;
	}

	public void setLC_AMOUNT(String lC_AMOUNT) {
		LC_AMOUNT = lC_AMOUNT;
	}

	public String getCURRENCY() {
		return CURRENCY;
	}

	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}

	public String getCOUNTRY_CODE_IMP() {
		return COUNTRY_CODE_IMP;
	}

	public void setCOUNTRY_CODE_IMP(String cOUNTRY_CODE_IMP) {
		COUNTRY_CODE_IMP = cOUNTRY_CODE_IMP;
	}

	public String getBIN() {
		return BIN;
	}

	public void setBIN(String bIN) {
		BIN = bIN;
	}

	public String getIMPORTERNAME() {
		return IMPORTERNAME;
	}

	public void setIMPORTERNAME(String iMPORTERNAME) {
		IMPORTERNAME = iMPORTERNAME;
	}

	public String getBENEFICIARY() {
		return BENEFICIARY;
	}

	public void setBENEFICIARY(String bENEFICIARY) {
		BENEFICIARY = bENEFICIARY;
	}

	public LC_DETAIL getLc_detail() {
		if(lc_detail == null)
			lc_detail = new LC_DETAIL();
		return lc_detail;
	}

	public void setLc_detail(LC_DETAIL lc_detail) {
		this.lc_detail = lc_detail;
	}
	
	

	@Override
	public String toString() {
		return "LC_INFO [LC_ID=" + LC_ID + ", LC_DATE=" + LC_DATE + ", LC_EXPIRY_DATE=" + LC_EXPIRY_DATE
				+ ", LAST_SHIPMENT_DATE=" + LAST_SHIPMENT_DATE + ", UPDATE_STATUS=" + UPDATE_STATUS + ", UPDATE_DATE="
				+ UPDATE_DATE + ", LC_AMOUNT=" + LC_AMOUNT + ", CURRENCY=" + CURRENCY + ", COUNTRY_CODE_IMP="
				+ COUNTRY_CODE_IMP + ", BIN=" + BIN + ", IMPORTERNAME=" + IMPORTERNAME + ", BENEFICIARY=" + BENEFICIARY
				+ "]";
	}

}
