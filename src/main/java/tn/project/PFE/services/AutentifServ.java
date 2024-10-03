package tn.project.PFE.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.project.PFE.repository.UserRepository;
@Service
public class AutentifServ implements UserDetailsService {
	@Autowired
	UserRepository authentif;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		tn.project.PFE.entities.User user=authentif.retrouveUser(email);
		System.out.print(user);
		 List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		 list.add(new SimpleGrantedAuthority(user.getRole()+""));
         // User user1=new User(user.getUsername(),user.getPassword(),new ArrayList<>());
		//return (User)user1;
		
		return new User(user.getMail(),user.getPassword(),
				list);
		
	}

}
