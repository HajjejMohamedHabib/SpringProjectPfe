package tn.project.PFE.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.project.PFE.entities.SecretPaiment;

public interface SecretPaimentRepository extends CrudRepository<SecretPaiment, Integer> {
@Query("SELECT s FROM SecretPaiment s WHERE s.email=:email")
public SecretPaiment retriveSecretPaiement(@Param("email")String email);
@Query("SELECT s FROM SecretPaiment s WHERE s.email=:email AND s.cleSecret=:cle")
public SecretPaiment retriveSecretPaiementCle(@Param("email")String email,@Param("cle")long cle);
}
