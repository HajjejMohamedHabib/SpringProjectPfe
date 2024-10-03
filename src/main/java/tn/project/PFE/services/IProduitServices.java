package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;

public interface IProduitServices {
	public Produit ajouterProduit(Produit produit,int categorieId,int userId);
	public void modifiderProduit(Produit produit, int id);
	public void supprimeProduit(int id);
	public List<Produit> displayProduits();
	public Produit retrouveProduit(int id);
	public List<Produit> produitsSeuleCat(int idCat);
	public List<Produit> displayProduitParMarque(String marque);
	public List<Produit> displayProduitsParVendeur(User vendeur);

}
