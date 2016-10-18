package bb.org.bd.service.implement;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import bb.org.bd.model.HeaderParams;
import bb.org.bd.service.HeaderParamsService;

@Repository("headerParamsService")
public class HeaderParamsServiceImpl implements HeaderParamsService {

	private static final Logger logger = Logger.getLogger(HeaderParamsServiceImpl.class);
	private static final String CREATE_SQL = "INSERT INTO CUSTOMS_FX_API_CALL_LOG "
			+ " (ID_LOG,DATA_TYPE,QUERY_TYPE,DATE_OF_DATA,TIME_FROM,TIME_TO,LC_ID,EXP_NO,ENTRY_DATE,ENTRY_USER) "
			+ " VALUES (CUSTOMS_FX_API_CALL_LOG_SEQ.NEXTVAL, :DATA_TYPE, :QUERY_TYPE, :DATE_OF_DATA, :TIME_FROM, :TIME_TO, :LC_ID, :EXP_NO, :ENTRY_DATE, :ENTRY_USER)";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public HeaderParams insertHeaderParams(HeaderParams params) {
		
		try {
			logger.debug("inserting Header Params........");
			MapSqlParameterSource parameters = new MapSqlParameterSource();        
			parameters.addValue("DATA_TYPE", params.getQueryType());
			parameters.addValue("QUERY_TYPE", params.getDataType());
			parameters.addValue("DATE_OF_DATA", params.getDate());
			parameters.addValue("TIME_FROM", params.getTimeFrom());
			parameters.addValue("TIME_TO", params.getTimeTo());
			parameters.addValue("LC_ID", params.getLc());
			parameters.addValue("EXP_NO", params.getExp());
			parameters.addValue("ENTRY_DATE", new Date());
			parameters.addValue("ENTRY_USER", "CUSTOMS-WEB");
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			//int nums = jdbcTemplate.update(CREATE_SQL, parameters,	keyHolder, new String[]{"ID_LOG"});
			//logger.info("Number of row inserted: " + nums);
			
			BigInteger generatedIdLog = BigInteger.valueOf(keyHolder.getKey().longValue());
			params.setId_log(generatedIdLog);
			logger.debug("ID_LOG: " + generatedIdLog);
			//Map<String, Object> returnParams = keyHolder.getKeys();
			//logger.debug("ID_LOG: " + returnParams.get("ID_LOG"));
			//logger.debug("ENTRY_DATE: " + returnParams.get("ENTRY_DATE"));
		} 
		catch (Exception e) {
			logger.warn("Exception: ", e);
			e.printStackTrace();
		}

        //params.s
		return params;
	}

	@Override
	public List<HeaderParams> getAllHeaderParams() {
		// TODO Auto-generated method stub
		return null;
	}

}
