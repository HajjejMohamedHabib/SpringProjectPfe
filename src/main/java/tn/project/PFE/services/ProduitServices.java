package tn.project.PFE.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Categorie;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.repository.CategorieRepository;
import tn.project.PFE.repository.ProduitRepository;
import tn.project.PFE.repository.UserRepository;

@Service
public class ProduitServices implements IProduitServices {
    @Autowired
    ProduitRepository prodrep;
    @Autowired
    CategorieRepository catrep;
    @Autowired
    UserRepository userrep;
	@Override
	public Produit ajouterProduit(Produit produit, int categorieId,int userId) {
        Categorie cat= catrep.findById(categorieId).get();
        User user=userrep.findById(userId).get();
		produit.setCategorie(cat);
		produit.setVendeur(user);
		return prodrep.save(produit);
	}

	@Override
	public void modifiderProduit(Produit produit, int id) {
		prodrep.updateProduit(produit.getMarque(),produit.getPrix(), produit.getDescription(), produit.getImage(), produit.getNbre_piece(),produit.getNomProduit(), id);
		
	}

	@Override
	public void supprimeProduit(int id) {
		prodrep.deleteById(id);
		
	}

	@Override
	public List<Produit> displayProduits() {
		return (List<Produit>)prodrep.findAll();
	}

	@Override
	public Produit retrouveProduit(int id) {
	return prodrep.retriveProduit(id);
			
	}

	@Override
	public List<Produit> produitsSeuleCat(int idCat) {
		Categorie c=new Categorie();
		c=catrep.findById(idCat).get();
		return prodrep.produitsSeuleCat(c);
	}

	@Override
	public List<Produit> displayProduitParMarque(String marque) {
		return prodrep.displayProduitParMarque(marque);
	}

	@Override
	public List<Produit> displayProduitsParVendeur(User vendeur) {
		return prodrep.displayProduitParVendeur(vendeur);
	}


}
