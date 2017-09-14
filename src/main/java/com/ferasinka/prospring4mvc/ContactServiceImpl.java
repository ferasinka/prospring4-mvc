package com.ferasinka.prospring4mvc;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Service("contactService")
public class ContactServiceImpl implements ContactService {
	private ContactRepository contactRepository;
	
	@Autowired
	public void setContactRepository(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		return contactRepository.findOne(id);
	}
	
	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
	
	@Override
	public Page<Contact> findAllByPage(Pageable pageable) {
		return contactRepository.findAll(pageable);
	}
}
