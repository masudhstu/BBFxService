package bb.org.bd.constants;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class Constants implements InitializingBean {

	private static final Logger logger = Logger.getLogger(Constants.class);
	private List<String> allowedClassList;

	

	public List<String> getAllowedClassList() {
		return allowedClassList;
	}



	public void setAllowedClassList(List<String> allowedClassList) {
		this.allowedClassList = allowedClassList;
	}



	@Override
	public void afterPropertiesSet() throws Exception {

		if (getAllowedClassList() == null) {
			logger.debug("Bean load finished");
		}

	}
}
