package bb.org.bd.service.implement;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bb.org.bd.model.LcToCustoms;
import bb.org.bd.service.LcToCustomsService;
import bb.org.bd.utils.Miscellaneous;

@Repository("lcToCustomsService")
public class LcToCustomsServiceImp implements LcToCustomsService {

	private static final Logger logger = Logger.getLogger(LcToCustomsServiceImp.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public LcToCustoms findLcdByLcID(BigInteger lcId) {
		String sql = "SELECT  ROW_NUMBER() OVER ( ORDER BY A.LC_ID) AS SL_NO, A.* " + " FROM( "
				+ "     SELECT LC_ID,LC_DATE,NVL(LC_EXPIRY_DATE, ADD_MONTHS(LC_DATE,18))  LC_EXPIRY_DATE ,NVL(LAST_SHIPMENT_DATE, "
				+ "     ADD_MONTHS(LC_DATE,18)) LAST_SHIPMENT_DATE ,UPDATE_STATUS  ,UPDATEDATE,LC_AMOUNT, "
				+ "     CASE  CURRENCY_CODE WHEN '098' THEN '001' WHEN '047' THEN '086' ELSE CURRENCY_CODE END   CURRENCY_CODE,COUNTRY_CODE , "
				+ "     CASE  T1.BIN1 WHEN 0 THEN T2.BIN  ELSE T1.BIN1 END BIN, "
				+ "     STRING_SHAPE(IMP_NAME,3) IMP_NAME ,  NVL(STRING_SHAPE(BENEFICIARY_NAME,3),'-') BENEFICIARY_NAME "
				+ "     FROM( " + "         SELECT T1.* , T2.IMP_REGNO " + "         FROM( "
				+ "             SELECT A1.*, NVL(TO_CHAR(A1.UPD_DATE,'yyyy-mm-dd'), TO_CHAR(A1.LC_DATE,'yyyy-mm-dd')) UPDATEDATE, NVL(A1.BIN,0) BIN1 FROM LC_INFO A1 "
				+ "             WHERE LC_ID = " + lcId + " AND FINAL_YN ='Y' AND UPDATE_STATUS >= 0 "
				+ "             ) T1 LEFT JOIN  LCAF T2 ON T1.LCAF_ID =T2.LCAF_ID "
				+ "         ) T1 LEFT JOIN (SELECT IMP_REGNO ,NVL(BIN,0) BIN, IMP_NAME   FROM IMPORTER) T2 ON T1.IMP_REGNO =  T2.IMP_REGNO "
				+ "     ) A";

		logger.info("findByLcID: " + sql);

		List<LcToCustoms> oLcToCustoms = this.jdbcTemplate.query(sql, new LcMapper());
		//logger.info("findAllBank return size: " + listLcToCustoms.size());
		return oLcToCustoms.get(0);

	}

	@Override
	public List<LcToCustoms> findLcsByTimeDuration(Date timeFrom, Date timeTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LcToCustoms> findLcsByTimeDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<LcToCustoms> findLcsByfrequency(int frequency, int part) {
		Miscellaneous.getTimeRange(frequency, part);
		return null;
	}

	// inline class to map Bank Information
	private static final class LcMapper implements RowMapper<LcToCustoms> {

		public LcToCustoms mapRow(ResultSet rs, int rowNum) throws SQLException {

			LcToCustoms oLcToCustoms = new LcToCustoms();
			oLcToCustoms.setSLNo(BigInteger.valueOf(rs.getLong("SL_NO")));
			logger.debug("LcMapper SLNO: " + rs.getString("SL_NO"));
			oLcToCustoms.setLcId(BigInteger.valueOf(rs.getLong("LC_ID")));
			logger.debug("LcMapper LC_ID: " + rs.getString("LC_ID"));
			
			oLcToCustoms.setLcDate(rs.getDate("LC_DATE"));
			oLcToCustoms.setLcDate(rs.getDate("LC_EXPIRY_DATE"));
			oLcToCustoms.setLcDate(rs.getDate("LAST_SHIPMENT_DATE"));
			
			oLcToCustoms.setUpdateStatus(rs.getInt("UPDATE_STATUS"));
			
			oLcToCustoms.setUpdatedate(rs.getString("UPDATEDATE"));
			oLcToCustoms.setLcAmount(rs.getBigDecimal("LC_AMOUNT"));
			oLcToCustoms.setCurrencyCode(rs.getString("CURRENCY_CODE"));
			oLcToCustoms.setCountryCode(rs.getString("COUNTRY_CODE"));
			oLcToCustoms.setBin(BigInteger.valueOf(rs.getLong("BIN")));
			oLcToCustoms.setImpName(rs.getString("IMP_NAME"));
			oLcToCustoms.setBeneficiaryName(rs.getString("BENEFICIARY_NAME"));
			
			return oLcToCustoms;
		}
	}



}
