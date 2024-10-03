package tn.project.PFE.entities;

import java.io.Serializable;

public class ModelAjoutProduit implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	private String marque;
	private float prix;
	private String description;
	private String image;
	private int nbre_piece;
	private String email_vendeur;
	private int idCat;
	private String nomProduit;
	public ModelAjoutProduit() {
		super();
	}

	public ModelAjoutProduit(String marque, float prix, String description, String image, int nbre_piece, String email_vendeur,
			int idCat,String nomProduit) {
		super();
		this.idCat = idCat;
		this.marque = marque;
		this.prix = prix;
		this.description = description;
		this.image = image;
		this.nbre_piece = nbre_piece;
		this.email_vendeur =email_vendeur;
	  this.nomProduit=nomProduit;
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

	public String getEmail_vendeur() {
		return email_vendeur;
	}

	public void setEmail_vendeur(String email_vendeur) {
		this.email_vendeur = email_vendeur;
	}

	public int getIdCat() {
		return idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

}
