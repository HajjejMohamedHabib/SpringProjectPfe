package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.User;

public interface IUserServices {
	public User ajouterUser(User user);
	public List<User> displayUser();
	public void modifUser(int id,User user);
	public User retrouveUser(int id);
	public void bannirUser(int id);
	public void activerUser(int id);
	public void deleteUser(int id);
	public User retrouveUser(String email);
	public void initPassword(String email,String password);


}
