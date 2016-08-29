package bb.org.bd.service.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bb.org.bd.model.Adscode;
import bb.org.bd.model.Bankcode;
import bb.org.bd.service.BankcodeService;

@Repository("bankcodeService")
public class BankcodeServiceImpl implements BankcodeService {

	private static final Logger logger = Logger.getLogger(BankcodeServiceImpl.class);
	private JdbcTemplate jdbcTemplate;

	// @Resource(name="dataSource")
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Bankcode findBankByCode(String bankCode) {
		String sql = "SELECT * FROM BANKCODE WHERE BANK_CODE = '" + bankCode + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Bankcode>() {

			@Override
			public Bankcode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Bankcode oBankcode = new Bankcode();
					oBankcode.setBankCode(rs.getString("BANK_CODE"));
					logger.debug("findBankByCode BANKCODE: " + rs.getString("BANK_CODE"));
					oBankcode.setBankName(rs.getString("BANK_NAME"));
					oBankcode.setEntUser(rs.getString("ENT_USER"));
					oBankcode.setEntDate(rs.getDate("ENT_DATE"));
					oBankcode.setShortName(rs.getString("SHORT_NAME"));
					oBankcode.setEwdRitCode(rs.getBigDecimal("EWD_RIT_CODE"));

					return oBankcode;
				}
				return null;
			}
		});
	}
	
	@Override
	public Bankcode findBankByCodeWithAds(String bankCode) {
		String sql = "SELECT BANKCODE.BANK_CODE, BANKCODE.BANK_NAME, BANKCODE.ENT_USER, BANKCODE.ENT_DATE, BANKCODE.SHORT_NAME, BANKCODE.EWD_RIT_CODE, " +
				  "ADSCODE.ADSCODE, ADSCODE.ADS_ADDR1, ADSCODE.ADS_ADDR2, ADSCODE.ENT_USER AS ENT_USER_AD, ADSCODE.ENT_DATE AS ENT_DATE_AD, " +
				  "ADSCODE.REPORT_OFFICE, ADSCODE.OBU_YN, ADSCODE.DISTRICT_CODE, ADSCODE.EWD_RIT_CODE AS EWD_RIT_CODE_AD, ADSCODE.CONTACT_NO, " +
				  "ADSCODE.EMAIL, ADSCODE.HO_YN " +
				  "FROM BANKCODE " +
				  "LEFT JOIN ADSCODE ON ADSCODE.BANK_CODE = BANKCODE.BANK_CODE " +
				  "WHERE BANKCODE.BANK_CODE = '" + bankCode + "'";
		List<Bankcode> listBanks = jdbcTemplate.query(sql, new BankWithAdscodeExtractor());
		return listBanks.get(0);
	}


	@Override
	public List<Bankcode> findAllBank() {
		String sql = "SELECT * FROM BANKCODE ";

		logger.info("findAllBank: " + sql);

		List<Bankcode> listBankcode = this.jdbcTemplate.query(sql, new BankcodeMapper());
		logger.info("findAllBank return size: " + listBankcode.size());
		return listBankcode;
	}

	@Override
	public List<Bankcode> findAllBankWithAds() {
		
		String sql = "SELECT BANKCODE.BANK_CODE, BANKCODE.BANK_NAME, BANKCODE.ENT_USER, BANKCODE.ENT_DATE, BANKCODE.SHORT_NAME, BANKCODE.EWD_RIT_CODE, " +
					  "ADSCODE.ADSCODE, ADSCODE.ADS_ADDR1, ADSCODE.ADS_ADDR2, ADSCODE.ENT_USER AS ENT_USER_AD, ADSCODE.ENT_DATE AS ENT_DATE_AD, " +
					  "ADSCODE.REPORT_OFFICE, ADSCODE.OBU_YN, ADSCODE.DISTRICT_CODE, ADSCODE.EWD_RIT_CODE AS EWD_RIT_CODE_AD, ADSCODE.CONTACT_NO, " +
					  "ADSCODE.EMAIL, ADSCODE.HO_YN " +
					  "FROM BANKCODE " +
					  "LEFT JOIN ADSCODE ON ADSCODE.BANK_CODE = BANKCODE.BANK_CODE";
		return jdbcTemplate.query(sql, new BankWithAdscodeExtractor());
	}

	
	
	
	
	
	
	// inline class to map Bank Information
	private static final class BankcodeMapper implements RowMapper<Bankcode> {

		public Bankcode mapRow(ResultSet rs, int rowNum) throws SQLException {

			Bankcode oBankcode = new Bankcode();
			oBankcode.setBankCode(rs.getString("BANK_CODE"));
			logger.debug("findBankByCode BANKCODE: " + rs.getString("BANK_CODE"));
			oBankcode.setBankName(rs.getString("BANK_NAME"));
			logger.trace("BANK_NAME: " + rs.getString("BANK_NAME"));
			oBankcode.setEntUser(rs.getString("ENT_USER"));
			logger.trace("ENT_USER: " + rs.getString("ENT_USER"));
			oBankcode.setEntDate(rs.getDate("ENT_DATE"));
			logger.trace("ENT_DATE: " + rs.getDate("ENT_DATE"));
			oBankcode.setShortName(rs.getString("SHORT_NAME"));
			logger.trace("SHORT_NAME: " + rs.getString("SHORT_NAME"));
			oBankcode.setEwdRitCode(rs.getBigDecimal("EWD_RIT_CODE"));
			logger.trace("EWD_RIT_CODE: " + rs.getBigDecimal("EWD_RIT_CODE"));
			return oBankcode;
		}
	}
	
	//Inline class to Extract Bank and Adcode Information
	private static final class BankWithAdscodeExtractor implements ResultSetExtractor<List<Bankcode>>{
        public List<Bankcode> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<String, Bankcode> map = new HashMap<String, Bankcode>();
            Bankcode oBankcode = null;

            while(rs.next()) {
            	String bank_code = rs.getString("BANK_CODE");
            	oBankcode = map.get(bank_code);

                if(oBankcode == null) {
                	oBankcode = new Bankcode();
        			oBankcode.setBankCode(rs.getString("BANK_CODE"));
        			logger.debug("BANKCODE: " + rs.getString("BANK_CODE"));
        			oBankcode.setBankName(rs.getString("BANK_NAME"));
        			oBankcode.setEntUser(rs.getString("ENT_USER"));
        			oBankcode.setEntDate(rs.getDate("ENT_DATE"));
        			oBankcode.setShortName(rs.getString("SHORT_NAME"));
        			oBankcode.setEwdRitCode(rs.getBigDecimal("EWD_RIT_CODE"));
        			oBankcode.setAdscodeList(new ArrayList<Adscode>());
                    map.put(bank_code, oBankcode);
                }

                String adscode = rs.getString("ADSCODE");

                if(adscode != null) {
                	Adscode oAdscode = new Adscode();
                	oAdscode.setAdscode(rs.getString("ADSCODE"));
					logger.debug("ADSCODE: " + rs.getString("ADSCODE"));
					oAdscode.setAdsAddr1(rs.getString("ADS_ADDR1"));
					oAdscode.setAdsAddr2(rs.getString("ADS_ADDR2"));
					oAdscode.setBankCode(rs.getString("BANK_CODE"));
					oAdscode.setDistrictCode(rs.getBigDecimal("DISTRICT_CODE"));
					oAdscode.setEntDate(rs.getDate("ENT_DATE_AD"));
					oAdscode.setEntUser(rs.getString("ENT_USER_AD"));
					oAdscode.setEwdRitCode(rs.getBigDecimal("EWD_RIT_CODE_AD"));
					oAdscode.setObuYn(rs.getString("OBU_YN"));
					oAdscode.setReportOffice(rs.getBigDecimal("REPORT_OFFICE"));
					oAdscode.setContactNo(rs.getString("CONTACT_NO"));
					oAdscode.setEmail(rs.getString("EMAIL"));
					oAdscode.setHoYn(rs.getString("HO_YN"));
					
					oBankcode.getAdscodeList().add(oAdscode);
                }
            }

            return new ArrayList<Bankcode> (map.values());
        }
    }
}
