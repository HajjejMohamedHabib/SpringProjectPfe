package tn.project.PFE.entities;

public class ModelPaiement {
	private int id;
	private int commande;
	
	public ModelPaiement() {
		
	}

	public ModelPaiement(int commande) {
		this.commande = commande;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommande() {
		return commande;
	}

	public void setCommande(int commande) {
		this.commande = commande;
	}
	
}
