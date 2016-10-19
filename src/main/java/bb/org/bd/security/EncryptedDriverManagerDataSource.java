package bb.org.bd.security;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class EncryptedDriverManagerDataSource extends DriverManagerDataSource {

	private static final Logger logger = Logger.getLogger(EncryptedDriverManagerDataSource.class);

	
	@Override
	public String getUrl() {
		String url = "";
		try {
			url = super.getUrl();
			//System.out.println("url: " + url);
			url = EncryptDecryptMgr.decrypt(url);
			//logger.debug("url: " + url);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Exception: ", e);
		} 
		return url;
	}
	
	@Override
	public String getUsername() {
		String username = "";
		try {
			username = super.getUsername();
			//System.out.println("username: " + username);
			username = EncryptDecryptMgr.decrypt(username);
			//logger.debug("username: " + username);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Exception: ", e);
		} 
		return username;
	}
	@Override
	public String getPassword() {
		String password = "";
		try {
			password = super.getPassword();
			password = EncryptDecryptMgr.decrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Exception: ", e);
		} 
		return password;
	}

}
