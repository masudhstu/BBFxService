package bb.org.bd.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import bb.org.bd.constants.Constants;
import bb.org.bd.model.Adscode;
import bb.org.bd.service.AdscodeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdscodeController {

	private static final Logger logger = Logger.getLogger(Constants.class);

	@Autowired
	private Constants constants;

	@Autowired
	private AdscodeService adscodeService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		String allowedClasses = "";
		// Constants constants = new Constants();

		try {
			for (Object obj : constants.getAllowedClassList()) {
				allowedClasses = allowedClasses + obj.toString();
			}
		} catch (Exception ex) {
			allowedClasses = ex.toString() + "Exceptions";
		}

		// allowedClasses = "masud Rana";
		model.addAttribute("allowedClasses", allowedClasses);

		return "home_1";
	}

	@RequestMapping(value = "/adscodes")
	public ModelAndView listContact(ModelAndView model) throws IOException {
		List<Adscode> listContact = adscodeService.findAllAdscode();

		if (listContact == null)
			logger.warn("No Adscode is found");

		model.addObject("listAdscode", listContact);
		model.setViewName("home");

		return model;
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		String adscode = request.getParameter("adscode");
		Adscode oAdscode = adscodeService.findByAdscode(adscode);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("adscode", oAdscode);

		return model;
	}

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Adscode oAdscode = new Adscode();
		model.addObject("adscode", oAdscode);
		model.setViewName("ContactForm");
		return model;
	}
	
	

	// -----------------WS-----------------------------------

    //-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/ws-adscodes/add", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Adscode adscode,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating adscode " + adscode.getAdscode());
 
        /*if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        logger.debug(adscode);
        adscodeService.saveAdscode(adscode);
        
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/ws-adscodes/{adscode}").buildAndExpand(adscode.getAdscode()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/ws-adscodes", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Adscode>> listContact() {
		List<Adscode> listContact = adscodeService.findAllAdscode();

		if (listContact == null){
			logger.warn("No Adscode is found");
			return new ResponseEntity<List<Adscode>>(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NO_CONTENT
		}
		return new ResponseEntity<List<Adscode>>(listContact, HttpStatus.OK);
	}

	@RequestMapping(value = "/ws-adscodes/{adscode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Adscode> getUser(@PathVariable("adscode") String adscode) {
		
		logger.debug("Fetching Adscode with id " + adscode);
		//String adscode = request.getParameter("adscode");
		Adscode oAdscode = adscodeService.findByAdscode(adscode);		

		if (oAdscode == null) {
			System.out.println("Adscode with id " + adscode + " not found");
			return new ResponseEntity<Adscode>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Adscode>(oAdscode, HttpStatus.OK);
	}	
	
}
