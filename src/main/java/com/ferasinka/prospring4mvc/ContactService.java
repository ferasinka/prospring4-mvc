package com.ferasinka.prospring4mvc;

import java.util.List;

public interface ContactService {
	List<Contact> findAll();
	
	Contact findById(Long id);
	
	Contact save(Contact contact);
}
