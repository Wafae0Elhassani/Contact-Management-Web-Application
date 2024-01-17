package com.GestionContacts.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Contact")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String telPersonnel;
	private String telProfessionnel;
	private String genre;
	 
	
	
	public Contact() {
		
	}

	
	  public Contact(Long id, String nom, String prenom, String telPersonnel,
	  String telProfessionnel, String genre) { super(); this.id = id; this.nom =
	  nom; this.prenom = prenom; this.telPersonnel = telPersonnel;
	  this.telProfessionnel = telProfessionnel; this.genre = genre; }
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelPersonnel() {
		return telPersonnel;
	}
	public void setTelPersonnel(String telPersonnel) {
		this.telPersonnel = telPersonnel;
	}
	public String getTelProfessionnel() {
		return telProfessionnel;
	}
	public void setTelProfessionnel(String telProfessionnel) {
		this.telProfessionnel = telProfessionnel;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	
	
}

