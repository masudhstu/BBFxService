package bb.org.bd.security;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class EncryptedDriverManagerDataSource extends DriverManagerDataSource {

	private static final Logger logger = Logger.getLogger(EncryptedDriverManagerDataSource.class);

	
	@Override
	public String getUrl() {
		String url = super.getUrl();
		System.out.println("url: " + url);
		try {
			url = EncryptDecryptMgr.decrypt(url);
			logger.debug("url: " + url);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	@Override
	public String getUsername() {
		String username = super.getUsername();
		System.out.println("username: " + username);
		try {
			username = EncryptDecryptMgr.decrypt(username);
			logger.debug("username: " + username);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return username;
	}
	@Override
	public String getPassword() {
		String password = super.getPassword();
		try {
			password = EncryptDecryptMgr.decrypt(password);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return password;
	}

}
