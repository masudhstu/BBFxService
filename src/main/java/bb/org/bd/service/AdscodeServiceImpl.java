package bb.org.bd.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import bb.org.bd.model.Adscode;

public class AdscodeServiceImpl implements AdscodeService {

	private static final Logger logger = Logger.getLogger(AdscodeServiceImpl.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Adscode findByAdscode(String adscode) {
		String sql = "SELECT * FROM ADSCODE WHERE adscode=" + adscode;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Adscode>() {

			@Override
			public Adscode extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Adscode oAdscode = new Adscode();
					oAdscode.setAdscode(rs.getString("ADSCODE"));
					logger.debug("findAllAdscode ADSCODE: " + rs.getString("ADSCODE"));
					oAdscode.setAdsAddr1(rs.getString("ADS_ADDR1"));
					oAdscode.setAdsAddr2(rs.getString("ADS_ADDR2"));
					oAdscode.setBankCode(rs.getString("BANK_CODE"));
					oAdscode.setDistrictCode(rs.getBigDecimal("DISTRICT_CODE"));
					oAdscode.setEntDate(rs.getDate("ENT_DATE"));
					oAdscode.setEntUser(rs.getString("ENT_USER"));
					oAdscode.setEwdRitCode(rs.getBigDecimal("EWD_RIT_CODE"));
					oAdscode.setObuYn(rs.getString("OBU_YN"));
					oAdscode.setReportOffice(rs.getBigDecimal("REPORT_OFFICE"));

					return oAdscode;
				}

				return null;
			}

		});
	}

	@Override
	public Adscode findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adscode> findAllAdscode() {
		String sql = "SELECT * FROM ADSCODE WHERE ROWNUM < 101";

		logger.info("findAllAdscode: " + sql);
		
		List<Adscode> listAdscode = this.jdbcTemplate.query( sql, new ActorMapper());
		logger.info("findAllAdscode return size: " + listAdscode.size());
		return listAdscode;

		/*List<Adscode> listAdscode = jdbcTemplate.query(sql, new RowMapper<Adscode>() {

			@Override
			public Adscode mapRow(ResultSet rs, int rowNum) throws SQLException {
				Adscode oAdscode = new Adscode();
				logger.debug("findAllAdscode ADSCODE: " + rs.getString("ADSCODE"));
				oAdscode.setAdscode(rs.getString("ADSCODE"));
				oAdscode.setAdsAddr1(rs.getString("ADS_ADDR1"));
				oAdscode.setAdsAddr2(rs.getString("ADS_ADDR2"));
				oAdscode.setBankCode(rs.getString("BANK_CODE"));
				oAdscode.setDistrictCode(rs.getBigDecimal("DISTRICT_CODE"));
				oAdscode.setEntDate(rs.getDate("ENT_DATE"));
				oAdscode.setEntUser(rs.getString("ENT_USER"));
				oAdscode.setEwdRitCode(rs.getBigDecimal("EWD_RIT_CODE"));
				oAdscode.setObuYn(rs.getString("OBU_YN"));
				oAdscode.setReportOffice(rs.getBigDecimal("REPORT_OFFICE"));
				
				
				return oAdscode;
			}

		});

		logger.info("findAllAdscode return size: " + listAdscode.size());
		return listAdscode;*/
	}

	@Override
	public boolean isAdscodeExist(Adscode adscode) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//inline class
	private static final class ActorMapper implements RowMapper<Adscode> {

	    public Adscode mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	
	    	Adscode oAdscode = new Adscode();
			logger.debug("findAllAdscode ADSCODE: " + rs.getString("ADSCODE"));
			oAdscode.setAdscode(rs.getString("ADSCODE"));
			oAdscode.setAdsAddr1(rs.getString("ADS_ADDR1"));
			oAdscode.setAdsAddr2(rs.getString("ADS_ADDR2"));
			oAdscode.setBankCode(rs.getString("BANK_CODE"));
			oAdscode.setDistrictCode(rs.getBigDecimal("DISTRICT_CODE"));
			oAdscode.setEntDate(rs.getDate("ENT_DATE"));
			oAdscode.setEntUser(rs.getString("ENT_USER"));
			oAdscode.setEwdRitCode(rs.getBigDecimal("EWD_RIT_CODE"));
			oAdscode.setObuYn(rs.getString("OBU_YN"));
			oAdscode.setReportOffice(rs.getBigDecimal("REPORT_OFFICE"));
			
			return oAdscode;
	    }
	}

}
