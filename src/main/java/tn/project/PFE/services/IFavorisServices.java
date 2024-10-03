package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Favoris;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;

public interface IFavorisServices {
public Favoris ajouterFavoris(Favoris favoris);
public List<Favoris> displayFavoris(User client);
public void supprimeFavoris(int id);
public Favoris retrouveFavoris(User client,Produit produit);
}
