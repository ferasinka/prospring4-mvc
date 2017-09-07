package com.ferasinka.prospring4mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);
	private ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		LOG.info("Listing contacts");
		
		List<Contact> contacts = contactService.findAll();
		
		uiModel.addAttribute("contacts", contacts);
		
		LOG.info("No. of contacts: " + contacts.size());
		
		return "contacts/list";
	}
	
	@Autowired
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
}
