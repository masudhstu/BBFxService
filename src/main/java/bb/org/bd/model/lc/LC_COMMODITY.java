package bb.org.bd.model.lc;

public class LC_COMMODITY {
	
	private String COMMODITY_CODE;
	private String UNIT_CODE;
	private String UNIT_PRICE;
	private String QUANTITY;
	private String COUNTRY_CODE_ORG;
	private String DESCRIPTION;
	
	public String getCOMMODITY_CODE() {
		return COMMODITY_CODE;
	}
	public void setCOMMODITY_CODE(String cOMMODITY_CODE) {
		COMMODITY_CODE = cOMMODITY_CODE;
	}
	public String getUNIT_CODE() {
		return UNIT_CODE;
	}
	public void setUNIT_CODE(String uNIT_CODE) {
		UNIT_CODE = uNIT_CODE;
	}
	public String getUNIT_PRICE() {
		return UNIT_PRICE;
	}
	public void setUNIT_PRICE(String uNIT_PRICE) {
		UNIT_PRICE = uNIT_PRICE;
	}
	public String getQUANTITY() {
		return QUANTITY;
	}
	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}
	public String getCOUNTRY_CODE_ORG() {
		return COUNTRY_CODE_ORG;
	}
	public void setCOUNTRY_CODE_ORG(String cOUNTRY_CODE_ORG) {
		COUNTRY_CODE_ORG = cOUNTRY_CODE_ORG;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	
	@Override
	public String toString() {
		return "LC_COMMODITY [COMMODITY_CODE=" + COMMODITY_CODE + ", UNIT_CODE=" + UNIT_CODE + ", UNIT_PRICE="
				+ UNIT_PRICE + ", QUANTITY=" + QUANTITY + ", COUNTRY_CODE_ORG=" + COUNTRY_CODE_ORG + ", DESCRIPTION="
				+ DESCRIPTION + "]";
	}
	
}
