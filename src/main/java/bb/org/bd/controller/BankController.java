package bb.org.bd.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import bb.org.bd.model.Adscode;
import bb.org.bd.model.Bankcode;
import bb.org.bd.service.BankcodeService;

@RestController
public class BankController {
	
	private static final Logger logger = Logger.getLogger(BankController.class);
	
	@Autowired
	private BankcodeService bankcodeService;
	
	@RequestMapping(value = "/banks", method = RequestMethod.GET)
	public ModelAndView getBanks(ModelAndView model) throws IOException {
		
		List<Bankcode> listBanks = bankcodeService.findAllBank();
		
		if (listBanks == null)
			logger.warn("No Bank is found");

		model.addObject("listBanks", listBanks);
		model.setViewName("banks");

		return model;
	}
	
	@RequestMapping(value = "/bankswithbranches", method = RequestMethod.GET)
	public ModelAndView getBanksWithBranches(ModelAndView model) throws IOException {
		
		List<Bankcode> listBanks = bankcodeService.findAllBankWithAds();
		
		if (listBanks == null)
			logger.warn("No Bank is found");
		
		for (Bankcode bankcode : listBanks) {
			logger.debug(bankcode);
			
			for (Adscode adscode : bankcode.getAdscodeList()) {
				
				logger.debug(adscode);
				
			}
			
		}
		

		model.addObject("listBanks", listBanks);
		model.setViewName("banks");

		return model;
	}
	
	
	// -----------------WS-----------------------------------

		@RequestMapping(value = "/ws-banks", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Bankcode>> getBanks() {
			
			List<Bankcode> listBanks = bankcodeService.findAllBank();
			
			if (listBanks == null){
				logger.warn("No Bank is found");
				return new ResponseEntity<List<Bankcode>>(HttpStatus.NOT_FOUND);
				// You many decide to return HttpStatus.NO_CONTENT
			}
			return new ResponseEntity<List<Bankcode>>(listBanks, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/ws-banks/{bankCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Bankcode> getBank(@PathVariable("bankCode") String bankCode) {
			
			logger.debug("Fetching Bank with id " + bankCode);
			Bankcode oBankcode = bankcodeService.findBankByCode(bankCode);		

			if (oBankcode == null) {
				System.out.println("Bankcode with id " + bankCode + " not found");
				return new ResponseEntity<Bankcode>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Bankcode>(oBankcode, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/ws-bankswithbranches", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Bankcode>> getBanksWithAds() {
			
			List<Bankcode> listBanks = bankcodeService.findAllBankWithAds();
			
			if (listBanks == null){
				logger.warn("No Bank is found");
				return new ResponseEntity<List<Bankcode>>(HttpStatus.NOT_FOUND);
				// You many decide to return HttpStatus.NO_CONTENT
			}
			return new ResponseEntity<List<Bankcode>>(listBanks, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/ws-bankswithbranches/{bankCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Bankcode> getBankWithAds(@PathVariable("bankCode") String bankCode) {
			
			logger.debug("Fetching Bank with id " + bankCode);
			//String adscode = request.getParameter("adscode");
			Bankcode oBankcode = bankcodeService.findBankByCodeWithAds(bankCode);		

			if (oBankcode == null) {
				System.out.println("Bankcode with id " + bankCode + " not found");
				return new ResponseEntity<Bankcode>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Bankcode>(oBankcode, HttpStatus.OK);
		}

}
