package tn.project.PFE.entities;

import java.io.Serializable;

public class ProductModel implements Serializable{
	private static final long serialVersionUID = -1396669830860400871L;
	private int id;
	private String marque;
	private float prix;
	private String nomProduit;
	private String description;
	private String image;
	private int nbre_piece;
	private int idVend;
	private int Cat;
	private String nom_vendeur;
	private String nom_category;
	public ProductModel() {
		super();
	}

	public ProductModel(int id, String marque, float prix, String description, String image, int nbre_piece, int idVend,
			int Cat ,String nom_vendeur,String nom_categorie,String nomProduit) {
		super();
		this.id = id;
		this.marque = marque;
		this.prix = prix;
		this.description = description;
		this.image = image;
		this.nbre_piece = nbre_piece;
		this.idVend = idVend;
		this.Cat = Cat;
		this.nom_vendeur=nom_vendeur;
		this.nom_category=nom_categorie;
		this.nomProduit=nomProduit;
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

	public int getIdVend() {
		return idVend;
	}

	public void setIdVend(int idVend) {
		this.idVend = idVend;
	}

	public int getCat() {
		return Cat;
	}

	public void setCat(int Cat) {
		this.Cat = Cat;
	}

	public String getNom_vendeur() {
		return nom_vendeur;
	}

	public void setNom_vendeur(String nom_vendeur) {
		this.nom_vendeur = nom_vendeur;
	}

	public String getNom_category() {
		return nom_category;
	}

	public void setNom_category(String nom_category) {
		this.nom_category = nom_category;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	
	
}
