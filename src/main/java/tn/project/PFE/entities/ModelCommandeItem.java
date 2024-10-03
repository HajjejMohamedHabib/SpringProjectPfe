package tn.project.PFE.entities;

public class ModelCommandeItem {
	private int id;
	private float quantite;
	private int commande;
	private int produit;
	private float prix;
	private String nomProduit;
	private String marque;
	private String description;
	private int vendeur;
	private int mois;
	private String nomVendeur;
	public ModelCommandeItem() {
		super();
	}

	public ModelCommandeItem(float quantite, int produit) {
		super();
		this.quantite = quantite;
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

	public int getCommande() {
		return commande;
	}

	public void setCommande(int commande) {
		this.commande = commande;
	}

	public int getProduit() {
		return produit;
	}

	public void setProduit(int produit) {
		this.produit = produit;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVendeur() {
		return vendeur;
	}

	public void setVendeur(int vendeur) {
		this.vendeur = vendeur;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public String getNomVendeur() {
		return nomVendeur;
	}

	public void setNomVendeur(String nomVendeur) {
		this.nomVendeur = nomVendeur;
	}
	
}
