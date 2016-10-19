package bb.org.bd.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bb.org.bd.model.HeaderParams;
import bb.org.bd.model.LcToCustoms;
import bb.org.bd.model.lc.LC;
import bb.org.bd.service.HeaderParamsService;
import bb.org.bd.service.LcToCustomsService;

@RestController
public class LcController {

	private static final Logger logger = Logger.getLogger(LcController.class);
	//private final Logger logger = LoggerProvider.getLogger(this.getClass());

	@Autowired
	private LcToCustomsService lcToCustomsService;
	
	@Autowired
	private HeaderParamsService headerParamsService;

	@RequestMapping(value = "/ws-lcFromBBTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LcToCustoms>> getLc() {

		// List<LcToCustoms> listBanks =
		// lcToCustomsService.findLcsByTimeDate(new Date(), new Date());
		List<LcToCustoms> listBanks = lcToCustomsService.findLcsByfrequency(24, 1);

		if (listBanks == null) {
			logger.warn("No Bank is found");
			return new ResponseEntity<List<LcToCustoms>>(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NO_CONTENT
		}
		return new ResponseEntity<List<LcToCustoms>>(listBanks, HttpStatus.OK);
	}

	@RequestMapping(value = "/ws-lcFromBB", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LC> getLcs(HttpServletRequest request) {

		String dataType = "1"; //1 means LC, 2 Means Exp
		String queryType="", date="", timeFrom="", timeTo="", lc="";
		
		try {
			queryType = request.getHeader("queryType");
			date = request.getHeader("date");
			timeFrom = request.getHeader("timeFrom");
			timeTo = request.getHeader("timeTo");
			lc = request.getHeader("lc");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("Header reading problem: ", e);
		}
		
		HeaderParams headers = new HeaderParams(dataType, queryType, date, timeFrom, timeTo, lc, null);		
		logger.info("headers: " + headers);		
		headers = headerParamsService.insertHeaderParams(headers);
		
		
		LC oLC = null;
		if(queryType.equals("1"))
		{
			oLC = lcToCustomsService.findLcdByLcID(lc);
		}
		else if(queryType.equals("2"))
		{
			oLC = lcToCustomsService.findLcsByDate(date);
		}
		else if(queryType.equals("3"))
		{
			oLC = lcToCustomsService.findLcsByDateAndTimeRange(date, timeFrom, timeTo);
		}
		else
		{
			oLC = null;
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("log_id", headers.getId_log().toString());

		if (oLC == null) {
			logger.warn("No LC is found, returning HttpStatus.NO_CONTENT");
			return new ResponseEntity<LC>(responseHeaders, HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NO_CONTENT
		}
		else
		{
			logger.info("returing LCs with HttpStatus.OK");
		}
		
		return new ResponseEntity<LC>(oLC, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/ws-lcFromBB/{lcId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LcToCustoms> getBank(@PathVariable("lcId") BigInteger lcId) {

		logger.debug("Fetching Bank with id " + lcId);
		LcToCustoms oLcToCustoms = lcToCustomsService.findLcdByLcID(lcId);

		if (oLcToCustoms == null) {
			System.out.println("Bankcode with id " + lcId + " not found");
			return new ResponseEntity<LcToCustoms>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<LcToCustoms>(oLcToCustoms, HttpStatus.OK);
	}

}
