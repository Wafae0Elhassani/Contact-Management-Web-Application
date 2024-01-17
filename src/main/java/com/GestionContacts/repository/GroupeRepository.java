package com.GestionContacts.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.GestionContacts.entity.Contact;
import com.GestionContacts.entity.Groupe;


@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long>{
	
	@Query("SELECT g.contacts FROM Groupe g WHERE g.name = :name")
	List<Contact> findContactsByGroupName(@Param("name") String name);
	
	@Modifying
	@Query("DELETE FROM Groupe g WHERE g.name = :name")
	void deleteByName(@Param("name") String name);
	
	@Query("SELECT g FROM Groupe g WHERE g.name = :name")
	Groupe findByGroupName(@Param("name") String name);
	

}
