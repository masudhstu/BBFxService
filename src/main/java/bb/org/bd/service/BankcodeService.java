package bb.org.bd.service;

import java.util.List;

import bb.org.bd.model.Bankcode;

public interface BankcodeService {
	
	Bankcode findBankByCode(String bankCode);
	Bankcode findBankByCodeWithAds(String bankCode);
	List<Bankcode> findAllBank();
	List<Bankcode> findAllBankWithAds();
}
