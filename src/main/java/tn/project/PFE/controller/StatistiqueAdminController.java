package tn.project.PFE.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.CommandeItem;
import tn.project.PFE.entities.ModelCommandeItem;
import tn.project.PFE.entities.ModelVendeurPlusVentes;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.ICommandeItemServices;
import tn.project.PFE.services.ICommandeServices;
import tn.project.PFE.services.IPaiementServices;
import tn.project.PFE.services.IProduitServices;
import tn.project.PFE.services.IUserServices;

@RestController
@CrossOrigin
public class StatistiqueAdminController {
@Autowired
IPaiementServices ps;
@Autowired
ICommandeServices cs;
@Autowired
ICommandeItemServices cis;
@Autowired
IProduitServices prods;
@Autowired
IUserServices us;
//@GetMapping("/produitPlusVendus")
public List<ModelCommandeItem> getProduitPlusVendus(){
	List<CommandeItem> listci= new ArrayList<CommandeItem>();
	List<ModelCommandeItem> listProduitVendus=new ArrayList<ModelCommandeItem>();
	try {
		listci=cis.getListItems();
		for(CommandeItem ci:listci) {
			if(ps.retrivePaiement(ci.getCommande())!=null) {
				ModelCommandeItem modelci= new ModelCommandeItem();
				modelci.setProduit(ci.getProduit().getId());
				modelci.setQuantite(ci.getQuantite());
				modelci.setVendeur(ci.getProduit().getVendeur().getId());
				listProduitVendus.add(modelci);
			}
		}
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return listProduitVendus;
}
public List<ModelCommandeItem> getProduitPlusVendusMois(int mois){
	List<CommandeItem> listci= new ArrayList<CommandeItem>();
	List<ModelCommandeItem> listProduitVendus=new ArrayList<ModelCommandeItem>();
	try {
		listci=cis.getListItems();
		for(CommandeItem ci:listci) {
			LocalDate localDate = ci.getCommande().getDate_commande().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int month = localDate.getMonthValue();
			if(ps.retrivePaiement(ci.getCommande())!=null && month==mois) {
				ModelCommandeItem modelci= new ModelCommandeItem();
				modelci.setProduit(ci.getProduit().getId());
				modelci.setQuantite(ci.getQuantite());
				modelci.setVendeur(ci.getProduit().getVendeur().getId());
				listProduitVendus.add(modelci);
			}
		}
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return listProduitVendus;
}
@GetMapping("/produitsPlusVendus")
public List<ModelCommandeItem> getProduitsPlusVendus(){
	List<ModelCommandeItem> lmci=new ArrayList<ModelCommandeItem>();
	List<ModelCommandeItem> lmcif=new ArrayList<ModelCommandeItem>();
	
	try {
	lmci=getProduitPlusVendus();
	for(ModelCommandeItem mci:lmci) {
		boolean existe=false;
		for(ModelCommandeItem mcif:lmcif) {
			if(mci.getProduit()==mcif.getProduit()) {
				mcif.setQuantite((mcif.getQuantite())+((mci.getQuantite() *100)/quantiteTotalVendus()));
				Produit p=new Produit();
				p=prods.retrouveProduit(mcif.getProduit());
				mcif.setNomProduit(p.getNomProduit());
				existe=true;
			}
		}
		if(existe==false) {
			Produit p=new Produit();
			p=prods.retrouveProduit(mci.getProduit());
			mci.setNomProduit(p.getNomProduit());
			mci.setQuantite((mci.getQuantite()* 100)/quantiteTotalVendus());
			lmcif.add(mci);
		}
	}}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return lmcif;
}
@GetMapping("/produitsPlusVendusMois/{mois}")
public List<ModelCommandeItem> getProduitsPlusVendusMois(@PathVariable("mois")int mois){
	List<ModelCommandeItem> lmci=new ArrayList<ModelCommandeItem>();
	List<ModelCommandeItem> lmcif=new ArrayList<ModelCommandeItem>();
	
	try {
	lmci=getProduitPlusVendusMois(mois);
	for(ModelCommandeItem mci:lmci) {
		boolean existe=false;
		for(ModelCommandeItem mcif:lmcif) {
			if(mci.getProduit()==mcif.getProduit()) {
				mcif.setQuantite((mcif.getQuantite())+((mci.getQuantite() *100)/quantiteTotalVendusMois(mois)));
				Produit p=new Produit();
				p=prods.retrouveProduit(mcif.getProduit());
				mcif.setNomProduit(p.getNomProduit());
				existe=true;
			}
		}
		if(existe==false) {
			Produit p=new Produit();
			p=prods.retrouveProduit(mci.getProduit());
			mci.setNomProduit(p.getNomProduit());
			mci.setQuantite((mci.getQuantite()* 100)/quantiteTotalVendusMois(mois));
			lmcif.add(mci);
		}
	}}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return lmcif;
}
@GetMapping("/vendeurPlusVentes")
public List<ModelVendeurPlusVentes> vendeurPlusVentes(){
	List<ModelCommandeItem> lmci=new ArrayList<ModelCommandeItem>();
	List<ModelVendeurPlusVentes> lmcif=new ArrayList<ModelVendeurPlusVentes>();
	try {
	lmci=getProduitsPlusVendus();
	for(ModelCommandeItem mci:lmci) {
		boolean existe=false;
		for(ModelVendeurPlusVentes mcif:lmcif) {
			if(mci.getVendeur()==mcif.getVendeur()) {
				mcif.setQuantite(mcif.getQuantite()+mci.getQuantite());
				User vendeur=new User();
				vendeur=us.retrouveUser(mcif.getVendeur());
				mcif.setNomVendeur(vendeur.getFirst_name()+' '+vendeur.getLast_name());
				existe=true;
			}
		}
		if(existe==false) {
			ModelVendeurPlusVentes mvpv=new ModelVendeurPlusVentes();
			mvpv.setVendeur(mci.getVendeur());
			mvpv.setQuantite(mci.getQuantite());
			User vendeur=new User();
			vendeur=us.retrouveUser(mci.getVendeur());
			mvpv.setNomVendeur(vendeur.getFirst_name()+' '+vendeur.getLast_name());
			lmcif.add(mvpv);
		}
	}}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return lmcif;
}
@GetMapping("/vendeurPlusVentesMois/{mois}")
public List<ModelVendeurPlusVentes> vendeurPlusVentesMois(@PathVariable("mois")int mois){
	List<CommandeItem> lci=new ArrayList<CommandeItem>();
	List<ModelCommandeItem> lmci=new ArrayList<ModelCommandeItem>();
	// lmcim:list de model commande item par mois
	List<ModelCommandeItem> lmcim=new ArrayList<ModelCommandeItem>();
	// lmcim:list de model commande item par mois vendus
		List<ModelCommandeItem> lmcimv=new ArrayList<ModelCommandeItem>();
	List<ModelVendeurPlusVentes> lmvpv=new ArrayList<ModelVendeurPlusVentes>();
	Commande commande=new Commande();
	try {
		lci=cis.getListItems();
		for(CommandeItem ci:lci) {
			ModelCommandeItem mci=new ModelCommandeItem();
			mci.setCommande(ci.getCommande().getId());
			mci.setProduit(ci.getProduit().getId());
			mci.setQuantite(ci.getQuantite());
			mci.setVendeur(ci.getProduit().getVendeur().getId());
			LocalDate localDate = ci.getCommande().getDate_commande().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int month = localDate.getMonthValue();
			mci.setMois(month);
			lmci.add(mci);
		}
		for(ModelCommandeItem mcim:lmci) {
			if(mcim.getMois()==mois) {
				lmcim.add(mcim);
			}
		}
		for(ModelCommandeItem mci:lmcim) {
		commande=cs.retriveCommande(mci.getCommande());
		if(ps.retrivePaiement(commande)!=null) {
			lmcimv.add(mci);
		}
		}
		for(ModelCommandeItem mci:lmcimv) {
			boolean existe=false;
			for(ModelVendeurPlusVentes mvpv:lmvpv) {
				if(mci.getVendeur()==mvpv.getVendeur()) {
					mvpv.setQuantite(mvpv.getQuantite()+((mci.getQuantite()*100)/quantiteTotalVendusMois(mois)));
					User vendeur=new User();
					vendeur=us.retrouveUser(mvpv.getVendeur());
					mvpv.setNomVendeur(vendeur.getFirst_name()+' '+vendeur.getLast_name());
					existe=true;
				}
			}
			if(existe==false) {
			ModelVendeurPlusVentes mvpv=new ModelVendeurPlusVentes();
			mvpv.setVendeur(mci.getVendeur());
			mvpv.setQuantite((mci.getQuantite()*100)/quantiteTotalVendusMois(mois));
			User vendeur=new User();
			vendeur=us.retrouveUser(mvpv.getVendeur());
			mvpv.setNomVendeur(vendeur.getFirst_name()+' '+vendeur.getLast_name());
			lmvpv.add(mvpv);
			}
		}
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return lmvpv;
}
@GetMapping("/quantiteTotalVendus")
public float quantiteTotalVendus() {
	List<CommandeItem> lci=new ArrayList<CommandeItem>();
	float qtv=0;
	try {
	lci=cis.getListItems();
	for(CommandeItem ci:lci) {
		if(ps.retrivePaiement(ci.getCommande())!=null) {
			qtv=qtv+ci.getQuantite();
		}
		}
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return qtv;
}
//@GetMapping("/quantiteTotalVendusMois/{mois}")
public float quantiteTotalVendusMois(int mois) {
	List<CommandeItem> lci=new ArrayList<CommandeItem>();
	float qtv=0;
	try {
	lci=cis.getListItems();
	for(CommandeItem ci:lci) {
		LocalDate localDate = ci.getCommande().getDate_commande().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		if(ps.retrivePaiement(ci.getCommande())!=null && month==mois) {
			qtv=qtv+ci.getQuantite();
		}
		}
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return qtv;
}
@GetMapping("/quantiteTotalProduits")
public int quantiteTotalProduit() {
	List<Produit> p=new ArrayList<Produit>();
	p=prods.displayProduits();
	return p.size();
}
@GetMapping("/totalVendus")
public float totalVendus() {
	List<CommandeItem> lci=new ArrayList<CommandeItem>();
	float ptv=0;
	try {
	lci=cis.getListItems();
	for(CommandeItem ci:lci) {
		if(ps.retrivePaiement(ci.getCommande())!=null) {
			Produit p =new Produit();
			p=prods.retrouveProduit(ci.getProduit().getId());
			ptv=ptv+ci.getQuantite()*p.getPrix();
		}
		}
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return ptv;
}
@GetMapping("/totalVendus/{mois}")
public float totalVendusMois(@PathVariable("mois")int mois) {
	List<CommandeItem> lci=new ArrayList<CommandeItem>();
	float ptv=0;
	try {
	lci=cis.getListItems();
	for(CommandeItem ci:lci) {
		LocalDate localDate = ci.getCommande().getDate_commande().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		if(ps.retrivePaiement(ci.getCommande())!=null && month==mois) {
			Produit p =new Produit();
			p=prods.retrouveProduit(ci.getProduit().getId());
			ptv=ptv+ci.getQuantite()*p.getPrix();
		}
		}
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return ptv;
}
}
