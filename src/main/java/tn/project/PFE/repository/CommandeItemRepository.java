package tn.project.PFE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.CommandeItem;
import tn.project.PFE.entities.Produit;

public interface CommandeItemRepository extends CrudRepository<CommandeItem, Integer>{
@Query("SELECT ci FROM CommandeItem ci WHERE ci.commande=:commande")
public List<CommandeItem> dispalyCommandeItem(@Param("commande")Commande commande);
@Query("SELECT ci FROM CommandeItem ci WHERE ci.produit=:produit AND ci.commande=:commande")
public CommandeItem dispalyCommandeItemParProduit(@Param("produit")Produit produit,@Param("commande")Commande commande);
}
