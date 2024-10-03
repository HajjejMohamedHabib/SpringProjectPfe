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
public class Categorie implements Serializable{
private static final long serialVersionUID = -1396669830860400871L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String nom_categorie;
private int nbre_piece;
@OneToMany(mappedBy = "categorie",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
private List<Produit> produits=new ArrayList<>();
public Categorie() {
	
}
public Categorie(int id,String nom_categorie,int nbre_piece) {
	this.id = id;
	this.nom_categorie = nom_categorie;
	this.nbre_piece = nbre_piece;
}
public Categorie(int id, String nom_categorie, int nbre_piece, List<Produit> produits) {
	super();
	this.id = id;
	this.nom_categorie = nom_categorie;
	this.nbre_piece = nbre_piece;
	this.produits = produits;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom_categorie() {
	return nom_categorie;
}
public void setNom_categorie(String nom_categorie) {
	this.nom_categorie = nom_categorie;
}
public int getNbre_piece() {
	return nbre_piece;
}
public void setNbre_piece(int nbre_piece) {
	this.nbre_piece = nbre_piece;
}
public List<Produit> getProduits() {
	return produits;
}
public void setProduits(List<Produit> produits) {
	this.produits = produits;
}


}
