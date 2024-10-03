package tn.project.PFE.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pannier_Produit implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Produit produit;
	private int quantite;
	@ManyToOne
	private Pannier pannier;
	public Pannier_Produit() {
		super();
	}
	public Pannier_Produit(Produit produit, Pannier pannier,int quantite) {
		super();
		this.produit = produit;
		this.pannier = pannier;
		this.quantite=quantite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Pannier getPannier() {
		return pannier;
	}
	public void setPannier(Pannier pannier) {
		this.pannier = pannier;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
}
