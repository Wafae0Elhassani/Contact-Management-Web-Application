package com.GestionContacts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GestionContacts.entity.Contact;
import com.GestionContacts.entity.Groupe;
import com.GestionContacts.repository.ContactRepository;
import com.GestionContacts.repository.GroupeRepository;

@Transactional
@Service
public class GroupeService {

	@Autowired
	private GroupeRepository rep;

	@Autowired
	private ContactRepository rep1;

	public void save(Groupe g) {

		rep.save(g);
	}

	public Groupe getContactsByGroupName(String name) {
		List<Contact> contacts = rep.findContactsByGroupName(name);
		Groupe groupe = new Groupe();
		groupe.setContacts(contacts);
		return groupe;
	}

	public List<Groupe> getAllGroups() {
		return rep.findAll();
	}

	public void deleteGroupById(Long id) {
		Groupe g = getGroupById(id);
		System.out.println(id);
	    //System.out.println(g);
		//g.getContacts().clear();
	    rep.delete(g);
	}
	
	public Groupe getGroupById(long id) {
		Groupe g = new Groupe();
		g=rep.findById(id).get();
		return g;
	}



	public void createGroupsByContactName() {
		List<Contact> contacts = rep1.findAll();
		Map<String, List<Contact>> contactsByName = new HashMap<>();

		for (Contact contact : contacts) {
			String contactName = contact.getNom();
			contactsByName.computeIfAbsent(contactName, key -> new ArrayList<>()).add(contact);
		}

		for (List<Contact> groupContacts : contactsByName.values()) {
			if (groupContacts.size() >= 2) {
				String groupName = groupContacts.get(0).getNom();
				if (rep.findByGroupName(groupName) != null) {
					continue;
				}

				Groupe group = new Groupe();
				group.setName(groupName);
				group.setContacts(groupContacts);
				rep.save(group);
			}
		}
	}

}
