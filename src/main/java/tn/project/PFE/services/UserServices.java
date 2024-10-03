package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.User;
import tn.project.PFE.repository.UserRepository;
@Service
public class UserServices implements IUserServices{
    @Autowired
    UserRepository userrep;
	@Override
	public User ajouterUser(User user) {
	return userrep.save(user);
	
		
	}
	@Override
	public List<User> displayUser() {
		return (List<User>) userrep.findAll();
		
	}
	@Override
	public void modifUser(int id, User user) {
		 userrep.updateUser(user.getMail(),user.getPassword(),user.getFirst_name(), user.getLast_name(),user.getAddress(), user.getPhone_number(), user.getRole(), user.getPhoto(),id);
	}
	@Override
	public User retrouveUser(int id) {
		return userrep.retrouveUser(id);
	}
	@Override
	public void bannirUser(int id) {
		userrep.bannirUser(id);
		
	}
	@Override
	public void activerUser(int id) {
		userrep.activerUser(id);
		
	}
	@Override
	public void deleteUser(int id) {
		userrep.deleteUser(id);
		
	}
	@Override
	public User retrouveUser(String email) {
		return userrep.retrouveUser(email);
	}
	@Override
	public void initPassword(String email, String password) {
		userrep.initPassword(password, email);
	}
	
	

}
