package bb.org.bd.service.implement;

import java.math.BigInteger;
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

import bb.org.bd.constants.Queries;
import bb.org.bd.model.LcToCustoms;
import bb.org.bd.model.lc.LC;
import bb.org.bd.model.lc.LC_COMMODITY;
import bb.org.bd.model.lc.LC_INFO;
import bb.org.bd.service.LcToCustomsService;
import bb.org.bd.utils.DateFormater;
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
	public LC findLcdByLcID(String lc) {
		String sql = String.format(Queries.findLcdByLcID, lc);
		logger.info("findLcdByLcID: " + sql);

		/*List<LC> oLC = this.jdbcTemplate.query(sql, new Lc_To_Customs());
		logger.info("oLC Size: " + oLC.size() + oLC.toString());
		logger.info("LC_INFO size: " + oLC.get(0).getLc_info().size());
		logger.info("Commodity size: " + oLC.get(0).getLc_info().get(0).getLc_detail().getLc_commodity().size());

		return oLC.get(0);*/
		
		return getValidatedLC(sql);
	}

	@Override
	public LC findLcsByDate(String date) {
		String sql = String.format(Queries.findLcsByDate, date, date, date);
		logger.info("findLcsByDate: " + sql);

		return getValidatedLC(sql);
	}

	@Override
	public LC findLcsByDateAndTimeRange(String date, String timeFrom, String timeTo) {

		String fullDateFrom = date + " " + timeFrom;
		String fullDateTo = date + " " + timeTo;
		String sql = String.format(Queries.findLcsByDateAndTimeRange, fullDateFrom, fullDateTo);
		logger.info("findLcsByDate: " + sql);

		return getValidatedLC(sql);
	}

	@Override
	public LcToCustoms findLcdByLcID(BigInteger lcId) {
		// String string = String.format("A String %s %2d", aStringVar,
		// anIntVar);

		String sql = String.format(Queries.findLcdByLcID_Vua, lcId);
		logger.info("findByLcID: " + sql);

		List<LcToCustoms> oLcToCustoms = this.jdbcTemplate.query(sql, new LcMapper());
		// logger.info("findAllBank return size: " + listLcToCustoms.size());
		return oLcToCustoms.get(0);

	}

	@Override
	public List<LC_INFO> findLcInfossByDate(String date) {
		String sql = String.format(Queries.findLcsByDate, date, date, date);
		logger.info("findLcInfossByDate: " + sql);

		List<LC_INFO> oLC = this.jdbcTemplate.query(sql, new LC_to_Custom_extractor());
		logger.info("LC_INFO list size : " + oLC.size() + oLC.toString());
		logger.info("LC_INFO list data : " + oLC.toString());

		// SimpleJdbcInsert abc;

		return oLC;
	}

	@Override
	public List<LcToCustoms> findLcsByfrequency(int frequency, int part) {
		Miscellaneous.getTimeRange(frequency, part);
		return null;
	}

	private LC getValidatedLC(String sql) {
		
		try {
			List<LC> oLC = this.jdbcTemplate.query(sql, new Lc_To_Customs());
			logger.info("oLC Size: " + oLC.size());

			if (oLC.size() == 0) {
				logger.warn("No LC found");
				return null;
			} else {
				logger.info("LC Found, LC_INFO size: " + oLC.get(0).getLc_info().size());
				return oLC.get(0);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.warn("DataAccessException: ", e);
		}
		
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

	// inline class to map Bank Information
	private static final class Lc_To_Customs implements RowMapper<LC> {

		public LC mapRow(ResultSet rs, int rowNum) throws SQLException {
			/*
			 * int size=0; while (rs.next()) { size++; }
			 * logger.debug("starting with total rows: " + size);
			 */

			Map<String, LC_INFO> mapLC_info = new HashMap<String, LC_INFO>();
			LC_INFO oLC_INFO = null;
			logger.debug("before rs.next......., rowNum: " + rowNum);
			logger.debug("rs.getLong: " + rs.getLong("LC_ID"));

			do {

				rowNum++;
				logger.debug("inside: while body, rowNum: " + rowNum);
				try {
					String lc_id = String.valueOf(rs.getLong("LC_ID"));
					logger.debug("lc_id: " + lc_id);
					oLC_INFO = mapLC_info.get(lc_id);

					if (oLC_INFO == null) {

						logger.debug("LC info is null!");
						oLC_INFO = new LC_INFO();
						oLC_INFO.setLC_ID(rs.getString("LC_ID"));
						oLC_INFO.setLC_DATE(DateFormater.formateDate(rs.getDate("LC_DATE")));
						oLC_INFO.setLC_EXPIRY_DATE(DateFormater.formateDate(rs.getDate("LC_EXPIRY_DATE")));
						oLC_INFO.setLAST_SHIPMENT_DATE(DateFormater.formateDate(rs.getDate("LAST_SHIPMENT_DATE")));
						oLC_INFO.setUPDATE_STATUS(rs.getString("UPDATE_STATUS"));
						oLC_INFO.setUPDATE_DATE(DateFormater.formateDate(rs.getDate("UPDATE_DATE")));
						oLC_INFO.setLC_AMOUNT(rs.getString("LC_AMOUNT"));
						oLC_INFO.setCURRENCY(rs.getString("CURRENCY"));
						oLC_INFO.setCOUNTRY_CODE_IMP(rs.getString("COUNTRY_CODE_IMP"));
						oLC_INFO.setBIN(rs.getString("BIN"));
						oLC_INFO.setIMPORTERNAME(rs.getString("IMPORTERNAME"));
						oLC_INFO.setBENEFICIARY(rs.getString("BENEFICIARY"));

						logger.debug(oLC_INFO);
						mapLC_info.put(lc_id, oLC_INFO);
					} else {
						logger.debug("LC info is not null");
					}

					// List<LC_COMMODITY> listCommodity = new
					// ArrayList<LC_COMMODITY>();

					LC_COMMODITY oLC_COMMODITY = null;
					// LC_DETAIL oLC_DETAIL = new LC_DETAIL();

					String commodity_code = rs.getString("COMMODITY_CODE");
					logger.debug("COMMODITY_CODE: " + commodity_code);

					if (commodity_code != null) {

						oLC_COMMODITY = new LC_COMMODITY();
						oLC_COMMODITY.setCOMMODITY_CODE(rs.getString("COMMODITY_CODE"));
						oLC_COMMODITY.setUNIT_CODE(rs.getString("UNIT_CODE"));
						oLC_COMMODITY.setUNIT_PRICE(rs.getString("UNIT_PRICE"));
						oLC_COMMODITY.setQUANTITY(rs.getString("QUANTITY"));
						oLC_COMMODITY.setCOUNTRY_CODE_ORG(rs.getString("COUNTRY_CODE_ORG"));
						oLC_COMMODITY.setDESCRIPTION(rs.getString("DESCRIPTION"));

						// oLC_DETAIL.setLc_commodity(oLC_COMMODITY);
						// oLC_DETAIL.getLc_commodity().add(oLC_COMMODITY);
						logger.debug("LC_COMMODITY " + oLC_COMMODITY);
						oLC_INFO.getLc_detail().getLc_commodity().add(oLC_COMMODITY);
						// oLC_INFO.getLc_details().add(oLC_COMMODITY);

						logger.debug("Ah!, Finished one adding");
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Exception: ", e);
				}

			} while (rs.next());

			// return new ArrayList<LC_INFO> (mapLC_info.values());

			//logger.debug("LC_INFO List : " + new ArrayList<LC_INFO>(mapLC_info.values()));

			LC oLC = new LC();
			oLC.setLc_info(new ArrayList<LC_INFO>(mapLC_info.values()));

			return oLC;

		}
	}

	// Inline class to Extract Bank and Adcode Information
	private static final class LC_to_Custom_extractor implements ResultSetExtractor<List<LC_INFO>> {
		public List<LC_INFO> extractData(ResultSet rs) throws SQLException, DataAccessException {

			/*
			 * int size=0; while (rs.next()) { size++; }
			 * logger.debug("starting with total rows: " + size);
			 */

			Map<String, LC_INFO> mapLC_info = new HashMap<String, LC_INFO>();
			LC_INFO oLC_INFO = null;
			logger.debug("starting ");

			while (rs.next()) {
				logger.debug("inside: rs.next() ");
				try {
					String lc_id = String.valueOf(rs.getLong("LC_ID"));
					logger.debug("lc_id: " + lc_id);
					oLC_INFO = mapLC_info.get(lc_id);

					if (oLC_INFO == null) {

						oLC_INFO = new LC_INFO();
						oLC_INFO.setLC_ID(rs.getString("LC_ID"));
						oLC_INFO.setLC_DATE(rs.getString("LC_DATE"));
						oLC_INFO.setLC_EXPIRY_DATE(rs.getString("LC_EXPIRY_DATE"));

						logger.debug(oLC_INFO);
						mapLC_info.put(lc_id, oLC_INFO);
					}

					// List<LC_COMMODITY> listCommodity = new
					// ArrayList<LC_COMMODITY>();

					LC_COMMODITY oLC_COMMODITY = null;
					// LC_DETAIL oLC_DETAIL = new LC_DETAIL();

					String commodity_code = rs.getString("COMMODITY_CODE");

					if (commodity_code != null) {
						oLC_COMMODITY = new LC_COMMODITY();

						oLC_COMMODITY.setCOMMODITY_CODE(rs.getString("COMMODITY_CODE"));
						oLC_COMMODITY.setCOUNTRY_CODE_ORG(rs.getString("COUNTRY_CODE_ORG"));
						oLC_COMMODITY.setDESCRIPTION(rs.getString("DESCRIPTION"));

						logger.debug(oLC_COMMODITY);

						// oLC_DETAIL.setLc_commodity(oLC_COMMODITY);
						// oLC_DETAIL.getLc_commodity().add(oLC_COMMODITY);
						oLC_INFO.getLc_detail().getLc_commodity().add(oLC_COMMODITY);
						// oLC_INFO.getLc_details().add(oLC_COMMODITY);

						// oLC_INFO.setLc_detail(oLC_DETAIL);

						// oLC_DETAIL = oLC_INFO.getLc_detail();
						// oLC_DETAIL.getLc_commodity().add(oLC_COMMODITY);

						// listCommodity.add(oLC_COMMODITY);
						// oLC_DETAIL.setLc_commodity(listCommodity);

						logger.debug("Ah!, Finished one adding");
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Exception: ", e);
				}

			}

			return new ArrayList<LC_INFO>(mapLC_info.values());
		}
	}

}
