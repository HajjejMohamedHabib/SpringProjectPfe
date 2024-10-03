package tn.project.PFE.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CommandeItem implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float quantite;
	@ManyToOne
	private Commande commande;
	@OneToOne
	private Produit produit;
	public CommandeItem() {
		super();
	}
	public CommandeItem(int id, float quantite, Commande commande, Produit produit) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.commande = commande;
		this.produit = produit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getQuantite() {
		return quantite;
	}
	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}
