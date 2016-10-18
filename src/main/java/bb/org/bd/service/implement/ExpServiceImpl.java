package bb.org.bd.service.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bb.org.bd.constants.Queries;
import bb.org.bd.model.exp.Exp_Detail;
import bb.org.bd.model.exp.Exp_Info;
import bb.org.bd.model.lc.LC;
import bb.org.bd.service.ExpService;

@Repository("expService")
public class ExpServiceImpl implements ExpService {

	private static final Logger logger = Logger.getLogger(ExpServiceImpl.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Exp_Info findExpByExpNo(String ExpNo) {
		String sql = String.format(Queries.findExpByExpNo, ExpNo);
		logger.info("findExpByExpNo: " + sql);

		/*List<Exp_Info> list_Exp_Info = this.jdbcTemplate.query(sql, new ExpMapper());
		logger.info("Exp_Detail size: " + list_Exp_Info.get(0).getExp_detail().size());
		return list_Exp_Info.get(0);*/
		
		return getValidatedExps(sql);
	}

	@Override
	public Exp_Info findExpByDate(String date) {
		String sql = String.format(Queries.findExpByDate, date, date, date);
		logger.info("findExpByDate: " + sql);

		/*List<Exp_Info> list_Exp_Info = this.jdbcTemplate.query(sql, new ExpMapper());
		logger.info("Exp_Detail size: " + list_Exp_Info.get(0).getExp_detail().size());
		return list_Exp_Info.get(0);*/
		
		return getValidatedExps(sql);
	}

	@Override
	public Exp_Info findExpByDateAndTimeRange(String date, String timeFrom, String timeTo) {
		
		String fullDateFrom = date + " " + timeFrom;
		String fullDateTo   = date + " " + timeTo;
		String sql = String.format(Queries.findExpByDateAndTimeRange, fullDateFrom, fullDateTo);
		logger.info("findExpByDateAndTimeRange: " + sql);

		/*List<Exp_Info> list_Exp_Info = this.jdbcTemplate.query(sql, new ExpMapper());
		logger.info("Exp_Detail size: " + list_Exp_Info.get(0).getExp_detail().size());
		return list_Exp_Info.get(0);*/
		
		return getValidatedExps(sql);
	}
	
	private Exp_Info getValidatedExps(String sql) {
		
		try {
			List<Exp_Info> oExp_Infos = this.jdbcTemplate.query(sql, new ExpMapper());		
			logger.info("oExp_Infos Size: " + oExp_Infos.size());

			if (oExp_Infos.size() == 0) {
				logger.warn("No Exp found");
				return null;
			} else {
				logger.info("EXP Found, Exp_detail size: " + oExp_Infos.get(0).getExp_detail().size());
				return oExp_Infos.get(0);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.warn("DataAccessException: ", e);
		}	
		
		return null;
	}

	// inline class to map EXP_DETAILS
	private static final class ExpMapper implements RowMapper<Exp_Info> {

		@Override
		public Exp_Info mapRow(ResultSet rs, int rowNum) throws SQLException {

			Exp_Detail oExp_Detail = null;
			List<Exp_Detail> list_Exp_Detail = new ArrayList<Exp_Detail>();

			logger.debug("before rs.next.......");
			
			/*while (rs.next()) {
				
				try {
					logger.debug("inside rs.next.......");
					oExp_Detail = new Exp_Detail();
					oExp_Detail.setEXP_NUMBER(rs.getString("EXP_NUMBER"));
					logger.debug("EXP_NUMBER: " + rs.getString("EXP_NUMBER"));
					oExp_Detail.setAMOUNT(rs.getString("AMOUNT"));
					oExp_Detail.setCURRENCY_CODE(rs.getString("CURRENCY_CODE"));
					oExp_Detail.setCOUNTRY_CODE(rs.getString("COUNTRY_CODE"));
					oExp_Detail.setCOMMODITY_CODE(rs.getString("COMMODITY_CODE"));
					oExp_Detail.setBIN(rs.getString("BIN"));
					oExp_Detail.setERC(rs.getString("ERC"));
					oExp_Detail.setEXPORTER_NAME(rs.getString("EXPORTER_NAME"));
					oExp_Detail.setEXPORTER_ADDRESS(rs.getString("EXPORTER_ADDRESS"));
					oExp_Detail.setUPDATE_STATUS(rs.getString("UPDATE_STATUS"));
					oExp_Detail.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
					
					logger.debug(oExp_Detail);
					list_Exp_Detail.add(oExp_Detail);

				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Exception: ", e);
				}
			}*/
			
			
			do{
				try {
					
					rowNum++;
					logger.debug("inside rs.next......, rowNum: " + rowNum);
					oExp_Detail = new Exp_Detail();
					oExp_Detail.setEXP_NUMBER(rs.getString("EXP_NUMBER"));
					logger.debug("EXP_NUMBER: " + rs.getString("EXP_NUMBER"));
					oExp_Detail.setAMOUNT(rs.getString("AMOUNT"));
					oExp_Detail.setCURRENCY_CODE(rs.getString("CURRENCY_CODE"));
					oExp_Detail.setCOUNTRY_CODE(rs.getString("COUNTRY_CODE"));
					oExp_Detail.setCOMMODITY_CODE(rs.getString("COMMODITY_CODE"));
					oExp_Detail.setBIN(rs.getString("BIN"));
					oExp_Detail.setERC(rs.getString("ERC"));
					oExp_Detail.setEXPORTER_NAME(rs.getString("EXPORTER_NAME"));
					oExp_Detail.setEXPORTER_ADDRESS(rs.getString("EXPORTER_ADDRESS"));
					oExp_Detail.setUPDATE_STATUS(rs.getString("UPDATE_STATUS"));
					oExp_Detail.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
					
					logger.debug(oExp_Detail);
					list_Exp_Detail.add(oExp_Detail);

				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Exception: ", e);
				}
				
			}while(rs.next());
			

			Exp_Info oExp_Info = new Exp_Info();
			oExp_Info.setExp_detail(list_Exp_Detail);
			return oExp_Info;
		}

	}

}
