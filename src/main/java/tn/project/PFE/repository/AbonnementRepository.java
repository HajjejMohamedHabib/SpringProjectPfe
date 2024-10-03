package tn.project.PFE.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.project.PFE.entities.Abonnement;
import tn.project.PFE.entities.TypeAbonnement;
import tn.project.PFE.entities.User;

public interface AbonnementRepository extends CrudRepository<Abonnement, Integer> {
@Query("SELECT a FROM Abonnement a WHERE a.vendeur=:vendeur")
public Abonnement retriveAbonnement(@Param("vendeur")User vendeur);
@Modifying
@Transactional
@Query("UPDATE Abonnement a SET a.dateFinAbonnement=:dateFinAb,a.typeAbonnement=:typeA WHERE a.vendeur=:vendeur")
public void updateAbonnement(@Param("dateFinAb")Date dateFinAb,@Param("typeA")TypeAbonnement typeA,@Param("vendeur")User vendeur);
}
