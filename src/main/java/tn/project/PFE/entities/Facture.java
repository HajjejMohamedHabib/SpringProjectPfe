package tn.project.PFE.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Facture implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Commande commande ;
	public Facture(int id, Commande commande) {
		super();
		this.id = id;
		this.commande = commande;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
}
