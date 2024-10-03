package tn.project.PFE.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.Paiement;

public interface PaiementRepository extends CrudRepository<Paiement, Integer>{
@Query("SELECT p FROM Paiement p WHERE p.commande=:commande")
public Paiement retrivePaiement(@Param("commande")Commande commande);
}
