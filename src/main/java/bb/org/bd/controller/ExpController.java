package bb.org.bd.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bb.org.bd.model.HeaderParams;
import bb.org.bd.model.exp.Exp_Info;
import bb.org.bd.service.ExpService;
import bb.org.bd.service.HeaderParamsService;

@RestController
public class ExpController {

	private static final Logger logger = Logger.getLogger(ExpController.class);

	@Autowired
	private ExpService expService;
	
	@Autowired
	private HeaderParamsService headerParamsService;
	
	@RequestMapping(value = "/ws-expFromBB", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Exp_Info> getLcs(HttpServletRequest request) {

		String dataType = "2"; //1 means LC, 2 Means Exp
		String queryType="", date="", timeFrom="", timeTo="", lc = "", exp="";
		
		try {
			queryType = request.getHeader("queryType");
			date = request.getHeader("date");
			timeFrom = request.getHeader("timeFrom");
			timeTo = request.getHeader("timeTo");
			lc = request.getHeader("lc");
			exp = request.getHeader("exp");			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Header reading problem: ", e);
		}
		
		HeaderParams headers = new HeaderParams(dataType, queryType, date, timeFrom, timeTo, lc, exp);		
		logger.info(headers);		
		headers = headerParamsService.insertHeaderParams(headers);
		
		
		Exp_Info oExp_Info = null;
		if(queryType.equals("1"))
		{
			oExp_Info = expService.findExpByExpNo(exp);
		}
		else if(queryType.equals("2"))
		{
			oExp_Info = expService.findExpByDate(date);
		}
		else if(queryType.equals("3"))
		{
			oExp_Info = expService.findExpByDateAndTimeRange(date, timeFrom, timeTo);
		}
		else
		{
			oExp_Info = null;
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("log_id", headers.getId_log().toString());

		if (oExp_Info == null) {
			logger.warn("No Exp_Info is found");
			return new ResponseEntity<Exp_Info>(responseHeaders, HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NO_CONTENT
		}
		
		return new ResponseEntity<Exp_Info>(oExp_Info, responseHeaders, HttpStatus.OK);
	}
	
}
