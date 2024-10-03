package tn.project.PFE.entities;

public class ModelPannier_Produit {
	private int id;
	private int produit;
	private int pannier;
	private int idClient;
	private String description;
	private String marque;
	private float prix;
	private String image;
	private int vendeur;
	private int idPannierProduit;
	private int quantite;
	public ModelPannier_Produit() {
		super();
	}
	public ModelPannier_Produit(int produit, int pannier,int idClient) {
		super();
		this.produit = produit;
		this.pannier = pannier;
		this.idClient=idClient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduit() {
		return produit;
	}
	public void setProduit(int produit) {
		this.produit = produit;
	}
	public int getPannier() {
		return pannier;
	}
	public void setPannier(int pannier) {
		this.pannier = pannier;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getVendeur() {
		return vendeur;
	}
	public void setVendeur(int vendeur) {
		this.vendeur = vendeur;
	}
	public int getIdPannierProduit() {
		return idPannierProduit;
	}
	public void setIdPannierProduit(int idPannierProduit) {
		this.idPannierProduit = idPannierProduit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	 
}
