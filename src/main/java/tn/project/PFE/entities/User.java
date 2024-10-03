package tn.project.PFE.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public  class User implements Serializable  {
private static final long serialVersionUID = -1396669830860400871L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String first_name;
private String last_name;
private String email;
private String phone_number;
private String password;
private String address;
private Role role;
private boolean isActif;
private String photo;
//***********//
@OneToMany(mappedBy = "client",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
private List<Commande> commandes=new ArrayList<>();
@OneToMany(mappedBy = "client",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
private List<Favoris> favoris=new ArrayList<>();
@OneToMany(mappedBy = "client",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
private List<Interaction> interactions =new ArrayList<>();
@OneToMany(mappedBy = "vendeur",cascade = {CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
private List<Produit> produits=new ArrayList<>();
//*********//
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User( String first_name, String last_name, String mail, String phone_number, String password,
		String address, Role role,boolean isActif,String photo) {
	super();
	
	this.first_name = first_name;
	this.last_name = last_name;
	this.email = mail;
	this.phone_number = phone_number;
	this.password = password;
	this.address = address;
	this.role = role;
	this.isActif=isActif;
	this.photo=photo;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getMail() {
	return email;
}
public void setMail(String mail) {
	this.email = mail;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
	
}
public boolean isActif() {
	return isActif;
}
public void setActif(boolean isActif) {
	this.isActif = isActif;
}

public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
//******************//
public List<Commande> getCommandes() {
	return commandes;
}

public void setCommandes(List<Commande> commandes) {
	this.commandes = commandes;
}

public List<Favoris> getFavoris() {
	return favoris;
}

public void setFavoris(List<Favoris> favoris) {
	this.favoris = favoris;
}

public List<Interaction> getInteractions() {
	return interactions;
}

public void setInteractions(List<Interaction> interactions) {
	this.interactions = interactions;
}
//**************//
public List<Produit> getProduits() {
	return produits;
}

public void setProduits(List<Produit> produits) {
	this.produits = produits;
}

}
