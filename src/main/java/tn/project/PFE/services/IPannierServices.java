package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Pannier;
import tn.project.PFE.entities.Pannier_Produit;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;

public interface IPannierServices {
	public List<Pannier_Produit> getPannierClient(User client);
	public Pannier_Produit ajouterPannier(User client ,Produit produit,int quantite);
	public Pannier_Produit retrivePannierProduit(Pannier pannier,Produit produit);
	public Pannier retrivePannier(User client);
	public void deletePannierItem(int id);
	public void updateQuantitePlus(int id);
	public void updateQuantiteMoins(int id);
}
