package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Favoris;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.repository.FavorisRepository;

@Service
public class FavorisServices implements IFavorisServices {
@Autowired
FavorisRepository favRep;
	@Override
	public Favoris ajouterFavoris(Favoris favoris) {
		
		return favRep.save(favoris);
	}

	@Override
	public List<Favoris> displayFavoris(User client) {
		return favRep.getFavoris(client);
	}

	@Override
	public void supprimeFavoris(int id) {
		favRep.
		deleteById(id);
		
	}

	@Override
	public Favoris retrouveFavoris(User client, Produit produit) {
		Favoris f=new Favoris();
		 f=favRep.retrouveFavoris(client, produit);
		 return f;
	}

}
