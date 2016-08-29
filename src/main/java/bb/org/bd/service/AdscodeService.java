package bb.org.bd.service;

import java.util.List;

import bb.org.bd.model.Adscode;

public interface AdscodeService {
	
	Adscode findByAdscode(String adscode);

	Adscode findByName(String name);

	//void saveUser(Adscode adscode);

	//void updateUser(Adscode Adscode);

	//void deleteUserById(long id);

	List<Adscode> findAllAdscode();

	//void deleteAllUsers();

	public boolean isAdscodeExist(Adscode adscode);

}
