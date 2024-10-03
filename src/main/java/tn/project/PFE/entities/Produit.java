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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produit implements Serializable{
	private static final long serialVersionUID = -1396669830860400871L;
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String marque;
private String nomProduit;
private float prix;
private String description;
private String image;
private int nbre_piece;
@ManyToOne(fetch = FetchType.LAZY)
private User vendeur;
@OneToMany(mappedBy = "produit",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
private List<Favoris> favoris =new ArrayList<>();
@OneToMany(mappedBy = "produit",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
private List<Interaction> interactions =new ArrayList<>();
@ManyToOne
private Categorie categorie;
@OneToMany(mappedBy = "produit")
List<Pannier_Produit> panniers_produits;
public Produit(int id,String marque, float prix, String description, String image, int nbre_piece, User vendeur,
		Categorie categorie,String nomProduit) {
	super();
	this.id = id;
	this.marque = marque;
	this.prix = prix;
	this.description = description;
	this.image = image;
	this.nbre_piece = nbre_piece;
	this.vendeur = vendeur;
	this.categorie = categorie;
	this.nomProduit=nomProduit;
	
}
public Produit() {
}

public Produit(String marque, String nomProduit, float prix, String description, String image, int nbre_piece) {
	super();
	this.marque = marque;
	this.nomProduit = nomProduit;
	this.prix = prix;
	this.description = description;
	this.image = image;
	this.nbre_piece = nbre_piece;
}
public Produit( User vendeur,
		Categorie categorie) {
	super();
	this.vendeur = vendeur;
	this.categorie = categorie;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMarque() {
	return marque;
}
public void setMarque(String marque) {
	this.marque = marque;
}
public float getPrix() {
	return prix;
}
public void setPrix(float prix) {
	this.prix = prix;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getNbre_piece() {
	return nbre_piece;
}
public void setNbre_piece(int nbre_piece) {
	this.nbre_piece = nbre_piece;
}
public User getVendeur() {
	return vendeur;
}
public void setVendeur(User vendeur) {
	this.vendeur = vendeur;
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
public Categorie getCategorie() {
	return categorie;
}
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}
public List<Pannier_Produit> getPanniers_produits() {
	return panniers_produits;
}
public void setPanniers_produits(List<Pannier_Produit> panniers_produits) {
	this.panniers_produits = panniers_produits;
}
public String getNomProduit() {
	return nomProduit;
}
public void setNomProduit(String nomProduit) {
	this.nomProduit = nomProduit;
}

}
