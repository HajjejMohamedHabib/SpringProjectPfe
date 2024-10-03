package tn.project.PFE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.project.PFE.entities.Interaction;
import tn.project.PFE.entities.Produit;

public interface InteractionRepository extends CrudRepository<Interaction,Integer> {
@Query("SELECT i FROM Interaction i where i.produit=:produit")
public List<Interaction> getAllInteractionByProduit(@Param("produit") Produit produit);
@Modifying
@Transactional
@Query("UPDATE Interaction i SET i.nbreEtoile=:nbreEtoile WHERE i.id=:id")
public void updateInteraction(@Param("nbreEtoile")int nbreEtoile,@Param("id")int id);
}
