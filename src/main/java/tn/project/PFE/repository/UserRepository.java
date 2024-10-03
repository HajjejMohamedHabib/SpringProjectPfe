package tn.project.PFE.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.project.PFE.entities.Role;
import tn.project.PFE.entities.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	@Query("SELECT u FROM User u WHERE u.id=:id")
    public User retrouveUser(@Param("id")int id);
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.email=:email,u.password=:password,u.first_name=:first_name,u.last_name=:last_name,u.address=:address,u.phone_number=:phone_number,u.role=:role,u.photo=:photo  WHERE u.id=:id")
    public void updateUser(@Param("email")String email,@Param("password")String password,@Param("first_name")String first_name,@Param("last_name")String last_name,@Param("address")String address,@Param("phone_number")String phone_number,@Param("role")Role role,@Param("photo")String photo,@Param("id")int id);
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.isActif=false WHERE u.id=:id")
	public void bannirUser(@Param("id")int id);
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.isActif=true WHERE u.id=:id")
	public void activerUser(@Param("id")int id);
	@Modifying
	@Transactional
	@Query("DELETE from User u WHERE u.id=:id")
	public void deleteUser(@Param("id")int id);
	@Query("SELECT u FROM User u WHERE u.email=:email")
    public User retrouveUser(@Param("email")String email);
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.password=:password WHERE u.email=:email")
	public void initPassword(@Param("password")String password,@Param("email")String email);
}
