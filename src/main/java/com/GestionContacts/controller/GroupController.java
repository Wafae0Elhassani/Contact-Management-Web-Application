package com.GestionContacts.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.GestionContacts.entity.Contact;
import com.GestionContacts.entity.Groupe;
import com.GestionContacts.service.ContactService;
import com.GestionContacts.service.GroupeService;

@Controller
public class GroupController {
	
	@Autowired
	private GroupeService service;
	
	@Autowired
	private ContactService contactService;
	
	
	@GetMapping("/addGroup")
	public ModelAndView addGroup() {
		
		List<Contact> list = contactService.getAllContacts();
		return new ModelAndView("addGroupForm", "contact", list);
		
	}
	
	
	  @GetMapping({"/Groupes","/Refresh"}) public ModelAndView Groupes() { 
		  service.createGroupsByContactName();
		  List<Groupe> list =service.getAllGroups(); return new ModelAndView("groupes", "groupe", list); }
	 
	
	
	@PostMapping("/saveGroup")
	public String addGroup(@RequestParam("name") String groupName, @RequestParam("selectedContacts") List<Long> selectedContacts) {
		List<Contact> listContacts = new ArrayList<>();
		for (Long id : selectedContacts) {
	        Contact contact = contactService.getContactById(id);
	        listContacts.add(contact);
	    }
		Groupe group = new Groupe();
	    group.setName(groupName);
	    group.setContacts(listContacts);
		service.save(group);
		return "redirect:/Groupes";
	}
	
	  @RequestMapping("/Search") public ModelAndView
	  getGroupByName(@RequestParam("afficher") String name){ Groupe g=new Groupe();
	  g = service.getContactsByGroupName(name); List<Contact> liste
	  =g.getContacts(); service.getAllGroups();return new ModelAndView("groupes", "contact", liste); }
	 


	@RequestMapping("/deleteGroup/{id}")
	public ModelAndView deleteGroupByName(@PathVariable("id")long id) {
		service.deleteGroupById(id);
	      return  new ModelAndView("redirect:/deleteGroup");
	}
	
	
	 @GetMapping("/deleteGroup") public ModelAndView DeleteG() { 
		  List<Groupe> list =service.getAllGroups(); return new ModelAndView("groupe", "groupe", list); }
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
