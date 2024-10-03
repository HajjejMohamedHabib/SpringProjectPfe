package tn.project.PFE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.User;

public interface CommandeRepository extends CrudRepository<Commande,Integer>{
	@Modifying
	@Transactional
	@Query("UPDATE Commande c SET c.etat=true WHERE c.id=:id")
	public void approveCommande(@Param("id")int id);
	@Query("SELECT c FROM Commande c WHERE c.client=:client")
	public List<Commande> getCommandesParClient(@Param("client")User client);
}
