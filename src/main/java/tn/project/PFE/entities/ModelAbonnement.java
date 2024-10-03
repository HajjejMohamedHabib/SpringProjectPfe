package tn.project.PFE.entities;

import java.util.Date;

public class ModelAbonnement {
	private int id;
	private TypeAbonnement typeAbonnement;
	private Date dateFinAbonnement;
	private int vendeur;
	public ModelAbonnement() {
		super();
	}
	public ModelAbonnement(TypeAbonnement typeAbonnement, Date dateFinAbonnement, int vendeur) {
		super();
		this.typeAbonnement = typeAbonnement;
		this.dateFinAbonnement = dateFinAbonnement;
		this.vendeur = vendeur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TypeAbonnement getTypeAbonnement() {
		return typeAbonnement;
	}
	public void setTypeAbonnement(TypeAbonnement typeAbonnement) {
		this.typeAbonnement = typeAbonnement;
	}
	public Date getDateFinAbonnement() {
		return dateFinAbonnement;
	}
	public void setDateFinAbonnement(Date dateFinAbonnement) {
		this.dateFinAbonnement = dateFinAbonnement;
	}
	public int getVendeur() {
		return vendeur;
	}
	public void setVendeur(int vendeur) {
		this.vendeur = vendeur;
	}
	
}
