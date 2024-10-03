package tn.project.PFE.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SecretPaiment {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long cleSecret;
	private String email;
	private int idVend;
	
	public SecretPaiment() {
		super();
	}

	public SecretPaiment(long cleSecret, String email, int idVend) {
		super();
		this.cleSecret = cleSecret;
		this.email = email;
		this.idVend = idVend;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCleSecret() {
		return cleSecret;
	}

	public void setCleSecret(long cleSecret) {
		this.cleSecret = cleSecret;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdVend() {
		return idVend;
	}

	public void setIdVend(int idVend) {
		this.idVend = idVend;
	}
	
	
}
