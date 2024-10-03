package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Pannier;
import tn.project.PFE.entities.Pannier_Produit;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.repository.PannierRepository;
import tn.project.PFE.repository.Pannier_ProduitRepository;

@Service
public class PannierServices implements IPannierServices {
@Autowired
PannierRepository pannierRep;
@Autowired
Pannier_ProduitRepository pannier_produitRep;
	@Override
	public List<Pannier_Produit> getPannierClient(User client) {
		Pannier pannier=new Pannier();
				pannier=pannierRep.getPannier(client);
		return pannier_produitRep.getPannierClient(pannier);
	}

	@Override
	public Pannier_Produit ajouterPannier(User client, Produit produit,int quantite) {
		Pannier p=new Pannier();
		p=pannierRep.getPannier(client);
		if(p==null) {
		Pannier pannier= pannierRep.save(new Pannier(client));
		return pannier_produitRep.save(new Pannier_Produit(produit,pannier,quantite));}
		else {
			return pannier_produitRep.save(new Pannier_Produit(produit,p,quantite));
		}
	}

	@Override
	public Pannier_Produit retrivePannierProduit(Pannier pannier, Produit produit) {
		return pannier_produitRep.retrivePannier_produit(pannier, produit);
	}

	@Override
	public Pannier retrivePannier(User client) {
		Pannier p=new Pannier();
		try {
		p=pannierRep.getPannier(client);
	}
		catch (Exception e) {
		System.out.println(e.getMessage());
		
		}
		return p;
	}

	@Override
	public void deletePannierItem(int id) {
		pannier_produitRep.deleteById(id);
		
	}

	@Override
	public void updateQuantitePlus(int id) {
		 pannier_produitRep.updateQuantitePlus(id);
	}

	@Override
	public void updateQuantiteMoins(int id) {
		pannier_produitRep.updateQuantiteMoins(id);
		
	}

}
