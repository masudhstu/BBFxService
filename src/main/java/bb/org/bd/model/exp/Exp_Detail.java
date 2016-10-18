package bb.org.bd.model.exp;

public class Exp_Detail {
	
	private String EXP_NUMBER;
	private String AMOUNT;
	private String CURRENCY_CODE;
	private String COUNTRY_CODE;
	private String COMMODITY_CODE;
	private String BIN;
	private String ERC;
	private String EXPORTER_NAME;
	private String EXPORTER_ADDRESS;
	private String UPDATE_STATUS;
	private String UPDATE_DATE;
	
	public String getEXP_NUMBER() {
		return EXP_NUMBER;
	}
	public void setEXP_NUMBER(String eXP_NUMBER) {
		EXP_NUMBER = eXP_NUMBER;
	}
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getCURRENCY_CODE() {
		return CURRENCY_CODE;
	}
	public void setCURRENCY_CODE(String cURRENCY_CODE) {
		CURRENCY_CODE = cURRENCY_CODE;
	}
	public String getCOUNTRY_CODE() {
		return COUNTRY_CODE;
	}
	public void setCOUNTRY_CODE(String cOUNTRY_CODE) {
		COUNTRY_CODE = cOUNTRY_CODE;
	}
	public String getCOMMODITY_CODE() {
		return COMMODITY_CODE;
	}
	public void setCOMMODITY_CODE(String cOMMODITY_CODE) {
		COMMODITY_CODE = cOMMODITY_CODE;
	}
	public String getBIN() {
		return BIN;
	}
	public void setBIN(String bIN) {
		BIN = bIN;
	}
	public String getERC() {
		return ERC;
	}
	public void setERC(String eRC) {
		ERC = eRC;
	}
	public String getEXPORTER_NAME() {
		return EXPORTER_NAME;
	}
	public void setEXPORTER_NAME(String eXPORTER_NAME) {
		EXPORTER_NAME = eXPORTER_NAME;
	}
	public String getEXPORTER_ADDRESS() {
		return EXPORTER_ADDRESS;
	}
	public void setEXPORTER_ADDRESS(String eXPORTER_ADDRESS) {
		EXPORTER_ADDRESS = eXPORTER_ADDRESS;
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
		UPDATE_DATE = uPDATE_DATE;
	}
	@Override
	public String toString() {
		return "Exp_Detail [EXP_NUMBER=" + EXP_NUMBER + ", AMOUNT=" + AMOUNT + ", CURRENCY_CODE=" + CURRENCY_CODE
				+ ", COUNTRY_CODE=" + COUNTRY_CODE + ", COMMODITY_CODE=" + COMMODITY_CODE + ", BIN=" + BIN + ", ERC="
				+ ERC + ", EXPORTER_NAME=" + EXPORTER_NAME + ", EXPORTER_ADDRESS=" + EXPORTER_ADDRESS
				+ ", UPDATE_STATUS=" + UPDATE_STATUS + ", UPDATE_DATE=" + UPDATE_DATE + "]";
	}	
	
}
