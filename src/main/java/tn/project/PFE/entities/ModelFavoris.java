package tn.project.PFE.entities;

public class ModelFavoris {
	private int id;
	private String libellé;
	private int client;
	private int produit;
	private String description;
	private float prix;
	private String marque;
	private String image;
	private String nomProduit;
	private int nbre_piece;
	public ModelFavoris() {
		super();
	}
	public ModelFavoris(int id, String libellé, int client, int produit) {
		super();
		this.id = id;
		this.libellé = libellé;
		this.client = client;
		this.produit = produit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibellé() {
		return libellé;
	}
	public void setLibellé(String libellé) {
		this.libellé = libellé;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public int getNbre_piece() {
		return nbre_piece;
	}
	public void setNbre_piece(int nbre_piece) {
		this.nbre_piece = nbre_piece;
	}
	
	
}
