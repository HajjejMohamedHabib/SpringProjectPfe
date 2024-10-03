package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.User;

public interface ICommandeServices {
public Commande ajoutCommande(Commande commande);
public Commande retriveCommande(int id);
public List<Commande> dispalyCommande ();
public void approveCommande(int id);
public List<Commande> getCommandesParClient(User client);
}
