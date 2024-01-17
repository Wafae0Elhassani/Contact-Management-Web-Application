package com.GestionContacts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.GestionContacts.entity.Contact;
import com.GestionContacts.service.ContactService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ContactController {

	@Autowired
	private ContactService service;
	

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/addContact")
	public String add() {

		return "addContact";
	}

	@GetMapping("/Contacts")
	public ModelAndView getAllContacts() {

		List<Contact> list = service.getAllContacts();
//    	ModelAndView m=new ModelAndView();
//		m.setViewName("contacts");
//		m.addObject("contact",list);
		return new ModelAndView("contacts", "contact", list);
	}


	@PostMapping("/save")
	public String addContact(@ModelAttribute Contact c) {
		service.save(c);
		return "redirect:/Contacts";
	}

	@RequestMapping("/deleteContact/{id}")
	public String deleteContact(@PathVariable("id") long id) {
		service.deleteContactById(id);
		return "redirect:/Contacts";
	}

	@RequestMapping("/editContact/{id}")
	public String editContact(@PathVariable("id") long id, Model model) {
		Contact c = service.getContactById(id);
		model.addAttribute("contact", c);
		return "Edit";
	}

	@RequestMapping("/search")
	public ModelAndView searchBy(@RequestParam("afficher") String mode, @RequestParam("search") String searchValue) {
		List<Contact> list = new ArrayList<>();
		if (mode.equals("nom")) {
			//list = service.findBySimilarName(searchValue);
			//list= service.findByLastName(searchValue);
			list = (List<Contact>) service.findByPhoneticName(searchValue);
		} else if(mode.equals("mobile")) {
			list = (List<Contact>) service.findByNumber(searchValue);
		}else if(mode.equals("tous")) {
			list= service.getAllContacts();
		}	
	return new ModelAndView("contacts", "contact", list);
	}
	

	
}
