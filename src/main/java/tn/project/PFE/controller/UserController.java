package tn.project.PFE.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.project.PFE.config.WebSecurityConfig;
import tn.project.PFE.entities.JwtResponse;
import tn.project.PFE.entities.ModelUser;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.UserServices;
@RestController 
@CrossOrigin
public class UserController {
@Autowired
UserServices userserv;
@Autowired
ServletContext servletContext;
@Autowired
 WebSecurityConfig webSecConfig;
@PostMapping(value = "/ajouter")
public User ajouterUser(@RequestParam String user,@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
	User user1=new User();
	user1=new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(user, User.class);
	 String filename=file.getOriginalFilename();
		String newfilename=FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		File serverfile=new File(servletContext.getRealPath("/images/"+File.separator+newfilename));
		System.out.println(newfilename);
		 
		try {
			FileUtils.writeByteArrayToFile(serverfile,file.getBytes());
		}catch(Exception e) {
			System.out.println("sssssssssss");
			e.printStackTrace();
		}
		user1.setPhoto(newfilename);
		user1.setPassword(webSecConfig.passwordEncoder().encode(user1.getPassword()));

	 return userserv.ajouterUser(user1);
}
@PostMapping(value = "/test")
public String test() {
	return "hello";
}
@GetMapping("/displayUser")
public List<User> displayUser() {
	List<User> users=new ArrayList<User>();
	for(User user: userserv.displayUser()) {
		User u=new User();
		u.setId(user.getId());
		u.setMail(user.getMail());
		u.setFirst_name(user.getFirst_name());
		u.setLast_name(user.getLast_name());
		u.setPhone_number(user.getPhone_number());
		u.setAddress(user.getAddress());
		u.setRole(user.getRole());
		u.setActif(user.isActif());
		u.setPassword(user.getPassword());
		u.setPhoto(user.getPhoto());
		users.add(u);
	}
	return users;
}
@PutMapping("/modifUser/{id}")
public ModelUser updateUSer(@PathVariable("id") int id,@RequestParam("user") String user,@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
	User user1=new User();
	ModelUser mu=new ModelUser();
	user1=new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(user, User.class);
	System.out.println(user1.getPhoto()+"this is photo");
	if(file.getSize()!=0) { 
	String filename=file.getOriginalFilename();
		String newfilename=FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		File serverfile=new File(servletContext.getRealPath("/images/"+File.separator+newfilename));
		System.out.println(newfilename);
	
		try {
			FileUtils.writeByteArrayToFile(serverfile,file.getBytes());
			user1.setPhoto(newfilename);
			user1.setPassword(webSecConfig.passwordEncoder().encode(user1.getPassword()));
		userserv.modifUser(id, user1);
		System.out.println("file not null");
		}catch(Exception e) {
			e.printStackTrace();
		}}else {
	try {
		user1.setPassword(webSecConfig.passwordEncoder().encode(user1.getPassword()));
		userserv.modifUser(id, user1);
		System.out.println("file null");
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}}
	mu.setId(user1.getId());
	mu.setFirst_name(user1.getFirst_name());
	mu.setEmail(user1.getMail());
	mu.setAddress(user1.getAddress());
	mu.setActif(user1.isActif());
	mu.setLast_name(user1.getLast_name());
	mu.setPassword(user1.getPassword());
	mu.setPhone_number(user1.getPhone_number());
	mu.setPhoto(user1.getPhoto());
	mu.setRole(user1.getRole());
  return mu;
}
@GetMapping("/retrouveUser/{id}")
public User retrouveUser(@PathVariable("id")int id) {
	User u=new User();
	User u1=new User();
	u= userserv.retrouveUser(id);
	u1.setId(u.getId());
	u1.setMail(u.getMail());
	u1.setFirst_name(u.getFirst_name());
	u1.setLast_name(u.getLast_name());
	u1.setPhone_number(u.getPhone_number());
	u1.setAddress(u.getAddress());
	u1.setRole(u.getRole());
	u1.setActif(u.isActif());
	u1.setPassword(u.getPassword());
	u1.setPhoto(u.getPhoto());
	return u1;
}
@GetMapping("/retrouveAdmin")
public User retrouveAdmin() {
	User u=new User();
	User u1=new User();
	u= userserv.retrouveUser(2);
	u1.setMail(u.getMail());
	u1.setPhone_number(u.getPhone_number());
	u1.setAddress(u.getAddress());
	return u1;
}
@PutMapping("/bannirUser/{id}")
public User bannirUSer(@PathVariable("id") int id) {
   userserv.bannirUser(id);
   return userserv.retrouveUser(id);
}
@PutMapping("/activerUser/{id}")
public User activerUSer(@PathVariable("id") int id) {
   userserv.activerUser(id);
   return userserv.retrouveUser(id);
}
@DeleteMapping("/deleteUser/{id}")
public String deleteUser(@PathVariable("id")int id) {
	
	userserv.deleteUser(id);
	return "user supprimé";
	
}
@GetMapping("/retrouveUserEmail/{email}")
public ModelUser retrouveUserEmail(@PathVariable("email")String email) {
	User u=new User();
	ModelUser mu=new ModelUser();
	try {
	u= userserv.retrouveUser(email);
	mu.setId(u.getId());
	mu.setFirst_name(u.getFirst_name());
	mu.setEmail(u.getMail());
	mu.setAddress(u.getAddress());
	mu.setActif(u.isActif());
	mu.setLast_name(u.getLast_name());
	mu.setPassword(u.getPassword());
	mu.setPhone_number(u.getPhone_number());
	mu.setPhoto(u.getPhoto());
	mu.setRole(u.getRole());
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return mu;
}
@PostMapping("/uploadPhoto")
	public void uploadPhoto(@RequestParam("file") MultipartFile file) {
	String filename=file.getOriginalFilename();
	String newfilename=FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
	File serverfile=new File(servletContext.getRealPath("/images/"+File.separator+newfilename));
	System.out.println(newfilename);
	try {
		FileUtils.writeByteArrayToFile(serverfile,file.getBytes());
	}catch(Exception e) {
		e.printStackTrace();
	}  
  } 
@GetMapping("/retrivephoto/{id}")
public byte[] getImage(@PathVariable("id") int id) throws IOException  {
	   User u=new User();
	   u=userserv.retrouveUser(id); 
	   if(new File(servletContext.getRealPath("/images/")+u.getPhoto()).exists()) {
	   return Files.readAllBytes(Paths.get(servletContext.getRealPath("/images/")+u.getPhoto()));}
return null;
}
@PutMapping("/initPassword/{email}")
public String initPassword(@RequestParam("password")String password,@PathVariable("email")String email) {
	String msg="erreur au niveau de maj";
	try {
		password=webSecConfig.passwordEncoder().encode(password);
		userserv.initPassword(email, password);
		msg="maj fait avec succes";
	}
	catch (Exception e) {
	System.out.println(e.getMessage());
	}
	return msg;
}
}