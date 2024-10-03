package tn.project.PFE.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Abonnement implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private TypeAbonnement typeAbonnement;
	private Date dateFinAbonnement;
	@OneToOne
	private User vendeur;
	
	public Abonnement() {
		super();
	}
	public Abonnement( TypeAbonnement typeAbonnement, Date dateFinAbonnement, User vendeur) {
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
	public User getVendeur() {
		return vendeur;
	}
	public void setVendeur(User vendeur) {
		this.vendeur = vendeur;
	}
	
	
}
