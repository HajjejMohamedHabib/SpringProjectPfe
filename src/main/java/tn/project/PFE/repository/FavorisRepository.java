package tn.project.PFE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.project.PFE.entities.Favoris;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;

public interface FavorisRepository extends CrudRepository<Favoris,Integer>{
@Query("SELECT f FROM Favoris f WHERE f.client=:client AND f.produit=:produit")
public Favoris retrouveFavoris(@Param("client")User client,@Param("produit")Produit produit);
@Query("SELECT f FROM Favoris f WHERE f.client=:client")
public List<Favoris> getFavoris(@Param("client")User client);
}

