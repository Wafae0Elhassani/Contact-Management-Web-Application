package com.GestionContacts.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.GestionContacts.entity.Contact;
import com.GestionContacts.entity.Groupe;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	@Query("SELECT c FROM Contact c WHERE c.nom = :nom")
	List<Contact> findByLastName(@Param("nom") String lastName);
	
	@Query(value = "SELECT * FROM contact c  WHERE levenshtein(c.nom,:nom) BETWEEN 0 AND 7", nativeQuery = true)
    List<Contact> findBySimilarName(@Param("nom") String nom);
	
	@Query("SELECT c FROM Contact c WHERE c.telPersonnel = :number OR c.telProfessionnel = :number")
	List<Contact> findByNumber(@Param("number") String number);
	
	@Query("SELECT c FROM Contact c WHERE SOUNDEX(c.nom) = SOUNDEX(:nom)")
	List<Contact> findByPhoneticName(@Param("nom") String nom);


}
