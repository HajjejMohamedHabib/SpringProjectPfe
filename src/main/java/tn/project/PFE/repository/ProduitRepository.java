package tn.project.PFE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.project.PFE.entities.Categorie;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;

public interface ProduitRepository extends CrudRepository<Produit, Integer> {
@Modifying
@Transactional
@Query("UPDATE Produit p SET p.marque=:marque,p.prix=:prix,p.description=:description,p.image=:image,p.nbre_piece=:nbre_piece,p.nomProduit=:nomProduit WHERE p.id=:id")
public void updateProduit(@Param("marque")String marque,@Param("prix")float prix,
		@Param("description")String description,@Param("image")String image,
		@Param("nbre_piece")int nbre_piece,@Param("nomProduit")String nomProduit,@Param("id")int id);

@Query("SELECT p FROM Produit p WHERE p.categorie=:cat")
 public List<Produit> produitsSeuleCat(@Param("cat")Categorie cat);
@Query("SELECT p FROM Produit p WHERE p.id=:id")
public Produit retriveProduit(@Param("id")int id);
@Query("SELECT p FROM Produit p WHERE p.marque=:marque")
public List<Produit> displayProduitParMarque(@Param("marque")String marque);
@Query("SELECT p FROM Produit p WHERE p.vendeur=:vendeur")
public List<Produit> displayProduitParVendeur(@Param("vendeur")User vendeur);
}