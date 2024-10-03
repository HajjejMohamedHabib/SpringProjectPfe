package tn.project.PFE.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.project.PFE.entities.Categorie;

public interface CategorieRepository extends CrudRepository<Categorie,Integer> {
@Modifying
@Transactional
@Query("UPDATE Categorie c SET c.nom_categorie=:nom_categorie WHERE c.id=:id")
public void updateCategorie(@Param("nom_categorie")String nom_categorie,@Param("id")int id);
}
