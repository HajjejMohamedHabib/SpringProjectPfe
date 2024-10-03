package tn.project.PFE.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Categorie;
import tn.project.PFE.repository.CategorieRepository;
@Service
public class CategorieServices implements ICategorieServices{
    @Autowired
    CategorieRepository categrep;
	@Override
	public Categorie ajouterCategorie(Categorie categorie) {
		return categrep.save(categorie) ;
	}
	@Override
	public void supprimeCategorie(int id) {
		categrep.deleteById(id);
		
	}

	@Override
	public List<Categorie> displayCategories() {
		return (List<Categorie>) categrep.findAll();
		
	}



	@Override
	public void modifiderCategorie(Categorie categorie, int id) {
	categrep.updateCategorie(categorie.getNom_categorie(), id);
		
	}
	@Override
	public Categorie retrouveCategorie(int id) {
		return categrep.findById(id).get();
	}
	

}
