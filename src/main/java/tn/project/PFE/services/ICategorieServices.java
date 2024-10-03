package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Categorie;

public interface ICategorieServices {
	public Categorie ajouterCategorie(Categorie categorie);
	public void modifiderCategorie(Categorie categorie, int id);
	public void supprimeCategorie(int id);
	public List<Categorie> displayCategories();
	public Categorie retrouveCategorie(int id);

}
