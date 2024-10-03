package tn.project.PFE.entities;

import java.util.Date;

public class ModelInteraction {
	private int id;
	private int nbreEtoile;
	private Date date_interaction;
	private int client;
	private int produit;
	public ModelInteraction() {
		super();
	}
	public ModelInteraction(int nbreEtoile, Date date_interaction, int client, int produit) {
		super();
		this.nbreEtoile = nbreEtoile;
		this.date_interaction = date_interaction;
		this.client = client;
		this.produit = produit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNbreEtoile() {
		return nbreEtoile;
	}
	public void setNbreEtoile(int nbreEtoile) {
		this.nbreEtoile = nbreEtoile;
	}
	public Date getDate_interaction() {
		return date_interaction;
	}
	public void setDate_interaction(Date date_interaction) {
		this.date_interaction = date_interaction;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public int getProduit() {
		return produit;
	}
	public void setProduit(int produit) {
		this.produit = produit;
	}

}
