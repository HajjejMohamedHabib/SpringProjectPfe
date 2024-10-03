package tn.project.PFE.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Favoris implements Serializable{
	private static final long serialVersionUID = -1396669830860400871L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String libellé;
@ManyToOne
private User client;
@ManyToOne
private Produit produit;
public Favoris() {}
public Favoris(String libellé, User client, Produit produit) {
	super();
	this.libellé = libellé;
	this.client = client;
	this.produit = produit;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLibellé() {
	return libellé;
}
public void setLibellé(String libellé) {
	this.libellé = libellé;
}
public User getClient() {
	return client;
}
public void setClient(User client) {
	this.client = client;
}
public Produit getProduit() {
	return produit;
}
public void setProduit(Produit produit) {
	this.produit = produit;
}



}
