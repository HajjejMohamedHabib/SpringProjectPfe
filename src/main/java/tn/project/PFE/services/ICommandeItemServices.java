package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.CommandeItem;
import tn.project.PFE.entities.Produit;

public interface ICommandeItemServices {
public CommandeItem ajoutCommandeItem(CommandeItem commandeItem);
public List<CommandeItem> displayCommandeItem(Commande commande);
public CommandeItem retriveCommandeItem(int id);
public CommandeItem retriveCommandeItemParProduit(Produit produit,Commande commande);
public List<CommandeItem> getListItems();
}
