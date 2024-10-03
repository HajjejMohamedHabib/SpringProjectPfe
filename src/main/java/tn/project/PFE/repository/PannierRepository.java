package tn.project.PFE.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.project.PFE.entities.Pannier;
import tn.project.PFE.entities.User;

public interface PannierRepository extends CrudRepository<Pannier, Integer>{
@Query("SELECT p FROM Pannier p where p.client=:client")
public Pannier getPannier(@Param("client")User client);
}
