package bb.org.bd.controller;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bb.org.bd.model.LcToCustoms;
import bb.org.bd.service.LcToCustomsService;

@RestController
public class LcToCustomsController {
	
	private static final Logger logger = Logger.getLogger(LcToCustomsController.class);

	@Autowired
	private LcToCustomsService lcToCustomsService;
	
	@RequestMapping(value = "/ws-lcFromBB", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LcToCustoms>> getLcs() {
		
		//List<LcToCustoms> listBanks = lcToCustomsService.findLcsByTimeDate(new Date(), new Date());
		List<LcToCustoms> listBanks = lcToCustomsService.findLcsByfrequency(24, 1);
		
		if (listBanks == null){
			logger.warn("No Bank is found");
			return new ResponseEntity<List<LcToCustoms>>(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NO_CONTENT
		}
		return new ResponseEntity<List<LcToCustoms>>(listBanks, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ws-lcFromBB/{lcId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LcToCustoms> getBank(@PathVariable("lcId") BigInteger lcId) {
		
		logger.debug("Fetching Bank with id " + lcId);
		LcToCustoms oLcToCustoms = lcToCustomsService.findLcdByLcID(lcId);		

		if (oLcToCustoms == null) {
			System.out.println("Bankcode with id " + lcId + " not found");
			return new ResponseEntity<LcToCustoms>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LcToCustoms>(oLcToCustoms, HttpStatus.OK);
	}

}
