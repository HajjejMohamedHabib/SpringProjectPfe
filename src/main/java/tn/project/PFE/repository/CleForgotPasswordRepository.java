package tn.project.PFE.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.project.PFE.entities.CleForgotPassword;

public interface CleForgotPasswordRepository extends CrudRepository<CleForgotPassword, Integer> {
@Query("SELECT c FROM CleForgotPassword c WHERE c.id=:id")
public CleForgotPassword retriveCleForgotPassword(@Param("id") int id);
}
