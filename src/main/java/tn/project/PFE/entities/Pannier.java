package tn.project.PFE.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pannier implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	User client;
	@OneToMany(mappedBy = "pannier")
	List<Pannier_Produit> panniers_produits;
	public Pannier() {
		super();
	}
	public Pannier(User client) {
		super();
		this.client = client;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public List<Pannier_Produit> getPanniers_produits() {
		return panniers_produits;
	}
	public void setPanniers_produits(List<Pannier_Produit> panniers_produits) {
		this.panniers_produits = panniers_produits;
	}

}
