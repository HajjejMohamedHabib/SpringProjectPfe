package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Interaction;
import tn.project.PFE.entities.Produit;

public interface IInteractionServices {
public Interaction ajouterInteraction(Interaction i);
public List<Interaction> getAllInteractions();
public List<Interaction> getAllInteractionProduct(Produit produit);
public void updateInteraction(int idInter,int nbreEtoile);
}
