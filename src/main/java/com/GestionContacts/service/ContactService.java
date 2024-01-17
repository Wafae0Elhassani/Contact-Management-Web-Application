package com.GestionContacts.service;

import java.util.List;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GestionContacts.entity.Contact;
import com.GestionContacts.entity.Groupe;
import com.GestionContacts.repository.ContactRepository;
import com.GestionContacts.repository.GroupeRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository rep;

	@Autowired
	private GroupeRepository rep1;

	public void save(Contact c) {

		rep.save(c);
	}

	public List<Contact> getAllContacts() {

		Sort sortByLastName = Sort.by(Sort.Direction.ASC, "nom");
		return rep.findAll(sortByLastName);
	}


	public void deleteContactById(Long id) {
		Contact contact = rep.findById(id).get();
		List<Groupe> groupes = rep1.findAll();
			for (Groupe groupe : groupes) {
			   if (groupe.getContacts().contains(contact)) {
					groupe.getContacts().remove(contact);
					rep1.save(groupe);
				}
			}
			rep.deleteById(id);
		}

	public Contact getContactById(long id) {
		return rep.findById(id).get();
	}

	public List<Contact> findByLastName(String LastName) {

		return rep.findByLastName(LastName);
	}

	public List<Contact> findBySimilarName(String nom) {

		return rep.findBySimilarName(nom);
	}

	public List<Contact> findByNumber(String num) {

		return rep.findByNumber(num);

	}

	public List<Contact> findByPhoneticName(String nom) {

		return rep.findByPhoneticName(nom);

	}

}
