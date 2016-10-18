package bb.org.bd.service;

import java.util.List;

import bb.org.bd.model.HeaderParams;

public interface HeaderParamsService {
	
	HeaderParams insertHeaderParams(HeaderParams params);
	List<HeaderParams> getAllHeaderParams();

}
