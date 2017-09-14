package com.ferasinka.prospring4mvc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {
	List<Contact> findAll();
	
	Contact findById(Long id);
	
	Contact save(Contact contact);
	
	Page<Contact> findAllByPage(Pageable pageable);
}
