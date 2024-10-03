package tn.project.PFE.entities;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken ;
	private String username;
	private String first_name;
	private String last_name;
	private String phone_number;
	private String address;
	private int id;
	private Collection<? extends GrantedAuthority> autorities;
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public JwtResponse(String jwttoken, String username, Collection<? extends GrantedAuthority> autorities,
			String first_name,String last_name,String phone_number,String address,int id) {
		this.jwttoken = jwttoken;
		this.username = username;
		this.autorities = autorities;
		this.first_name=first_name;
		this.last_name=last_name;
		this.phone_number=phone_number;
		this.address=address;
		this.id=id;
		
	}
    
	public String getUsername() {
		return username;
	}

	public Collection<? extends GrantedAuthority> getAutorities() {
		return autorities;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
