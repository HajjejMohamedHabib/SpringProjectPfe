package tn.project.PFE.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commande implements Serializable {
	private static final long serialVersionUID = -1396669830860400871L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean etat;
	 @Temporal(TemporalType.TIMESTAMP)
	private Date date_commande;
	private String first_name;
	private String last_name;
    private String city;
    private String country;
    private Long post_code;
    private String email;
    private String address;
    private String phone_number;
    private String company_name;
    @OneToMany(mappedBy = "commande",cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    private List<CommandeItem> commandes;
    @ManyToOne
    private User client;
    public Commande() {
    	
    }
	public Commande(Date date_commande, String first_name,String last_name,String city,String country
			,String address,String email, Long post_code, User client,String company_name,String phone_number) {
		super();
		this.date_commande=date_commande;
		this.first_name=first_name;
		this.last_name=last_name;
		this.city=city;
		this.country=country;
		this.address=address;
		this.email=email;
		this.post_code=post_code;
		this.client=client;
		this.company_name=company_name;
		this.phone_number=phone_number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	public Date getDate_commande() {
		return date_commande;
	}
	public void setDate_commande(Date date_commande) {
		this.date_commande = date_commande;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getPost_code() {
		return post_code;
	}
	public void setPost_code(Long post_code) {
		this.post_code = post_code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public List<CommandeItem> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<CommandeItem> commandes) {
		this.commandes = commandes;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	
		
}
