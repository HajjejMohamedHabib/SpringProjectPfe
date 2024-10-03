package tn.project.PFE.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
@Entity
public class Interaction implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int nbreEtoile;
	private Date date_interaction;
	@ManyToOne
	private User client;
	@ManyToOne
	private Produit produit;
	public Interaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Interaction(Date date_interaction,int nbreEtoile, User client, Produit produit) {
		super();
		this.id = id;
		this.date_interaction = date_interaction;
		this.nbreEtoile=nbreEtoile;
		this.client = client;
		this.produit = produit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_interaction() {
		return date_interaction;
	}
	public void setDate_interaction(Date date_interaction) {
		this.date_interaction = date_interaction;
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
	public int getNbreEtoile() {
		return nbreEtoile;
	}
	public void setNbreEtoile(int nbreEtoile) {
		this.nbreEtoile = nbreEtoile;
	}
	
	 
}
