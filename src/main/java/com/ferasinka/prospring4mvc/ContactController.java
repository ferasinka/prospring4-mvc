package com.ferasinka.prospring4mvc;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);
	private ContactService contactService;
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		LOG.info("Listing contacts");
		
		List<Contact> contacts = contactService.findAll();
		
		uiModel.addAttribute("contacts", contacts);
		
		LOG.info("No. of contacts: " + contacts.size());
		
		return "contacts/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		Contact contact = contactService.findById(id);
		uiModel.addAttribute("contact", contact);
		
		return "contacts/show";
	}
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid Contact contact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		LOG.info("Updating contact");
		
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("contact_save_fail", new Object[]{}, locale)));
			uiModel.addAttribute("contact", contact);
			
			return "contacts/update";
		}
		
		uiModel.asMap().clear();
		
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("contact_save_success", new Object[]{}, locale)));
		
		contactService.save(contact);
		
		return "redirect:/contacts/" + UrlUtil.encodeUrlPathSegment(contact.getId().toString(), httpServletRequest);
	}
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("contact", contactService.findById(id));
		
		return "contacts/update";
	}
	
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid Contact contact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		LOG.info("Creating contact");
		
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("contact_save_fail", new Object[]{}, locale)));
			uiModel.addAttribute("contact", contact);
			
			return "contacts/create";
		}
		
		uiModel.asMap().clear();
		
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("contact_save_success", new Object[]{}, locale)));
		
		LOG.info("Contact id: " + contact.getId());
		
		contactService.save(contact);
		
		return "redirect:/contacts/" + UrlUtil.encodeUrlPathSegment(contact.getId().toString(), httpServletRequest);
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactGrid listGrid(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order) {
		LOG.info("Listing contacts for grid with page: {}, rows: {}", page, rows);
		LOG.info("Listing contacts for grid with sort: {}, order: {}", sortBy, order);
		
		Sort sort = null;
		String orderBy = sortBy;
		
		if (orderBy != null && orderBy.equals("birthDateString")) {
			orderBy = "birthDate";
		}
		
		if (orderBy != null && order != null) {
			if (order.equals("desc")) {
				sort = new Sort(Sort.Direction.DESC, orderBy);
			} else {
				sort = new Sort(Sort.Direction.ASC, orderBy);
			}
		}
		
		PageRequest pageRequest = null;
		
		if (sort != null) {
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page - 1, rows);
		}
		
		Page<Contact> contactPage = contactService.findAllByPage(pageRequest);
		
		ContactGrid contactGrid = new ContactGrid();
		contactGrid.setCurrentPage(contactPage.getNumber() + 1);
		contactGrid.setTotalPages(contactPage.getTotalPages());
		contactGrid.setTotalRecords(contactPage.getTotalElements());
		contactGrid.setContactData(Lists.newArrayList(contactPage.iterator()));
		
		return contactGrid;
	}
	
	@Autowired
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
