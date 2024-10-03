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
import tn.project.PFE.services.ICommandeItemServices;
import tn.project.PFE.services.ICommandeServices;
import tn.project.PFE.services.IPaiementServices;

@RestController
@CrossOrigin
public class StatistiqueVendeurController {
	@Autowired
	IPaiementServices ps;
	@Autowired
	ICommandeServices cs;
	@Autowired
	ICommandeItemServices cis;
	public List<ModelCommandeItem> getProduitPlusVendusVendeur(int idVend){
		List<CommandeItem> listci= new ArrayList<CommandeItem>();
		List<ModelCommandeItem> listProduitVendus=new ArrayList<ModelCommandeItem>();
		try {
			listci=cis.getListItems();
			for(CommandeItem ci:listci) {
				if(ps.retrivePaiement(ci.getCommande())!=null && ci.getProduit().getVendeur().getId()==idVend) {
					ModelCommandeItem modelci= new ModelCommandeItem();
					modelci.setProduit(ci.getProduit().getId());
					modelci.setQuantite(ci.getQuantite());
					modelci.setVendeur(ci.getProduit().getVendeur().getId());
					modelci.setNomProduit(ci.getProduit().getNomProduit());
					modelci.setPrix(ci.getProduit().getPrix());
					listProduitVendus.add(modelci);
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listProduitVendus;
	}
	@GetMapping("/produitsPlusVendusVendeur/{idVend}")
	public List<ModelCommandeItem> getProduitsPlusVendus(@PathVariable("idVend")int idVend){
		List<ModelCommandeItem> lmci=new ArrayList<ModelCommandeItem>();
		List<ModelCommandeItem> lmcif=new ArrayList<ModelCommandeItem>();
		
		try {
		lmci=getProduitPlusVendusVendeur(idVend);
		for(ModelCommandeItem mci:lmci) {
			boolean existe=false;
			for(ModelCommandeItem mcif:lmcif) {
				if(mci.getProduit()==mcif.getProduit()) {
					mcif.setQuantite(mcif.getQuantite()+mci.getQuantite());
					mcif.setNomProduit(mci.getNomProduit());
					mcif.setPrix(mci.getPrix());
					existe=true;
				}
			}
			if(existe==false) {
				lmcif.add(mci);
			}
		}}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lmcif;
	}
	@GetMapping("produitsPlusVendusMois/{idVend}/{mois}")
	public List<ModelCommandeItem> produitsPlusVendusMois(@PathVariable("idVend")int idVend,@PathVariable("mois")int mois){
		List<CommandeItem> lci=new ArrayList<CommandeItem>();
		List<ModelCommandeItem> lmci=new ArrayList<ModelCommandeItem>();
		// lmcim:list de model commande item par mois
		List<ModelCommandeItem> lmcim=new ArrayList<ModelCommandeItem>();
		// lmcim:list de model commande item par mois vendus
			List<ModelCommandeItem> lmcimv=new ArrayList<ModelCommandeItem>();
		List<ModelCommandeItem> lmcimvf=new ArrayList<ModelCommandeItem>();
		Commande commande=new Commande();
		try {
			lci=cis.getListItems();
			for(CommandeItem ci:lci) {
				ModelCommandeItem mci=new ModelCommandeItem();
				mci.setCommande(ci.getCommande().getId());
				mci.setProduit(ci.getProduit().getId());
				mci.setQuantite(ci.getQuantite());
				mci.setVendeur(ci.getProduit().getVendeur().getId());
				mci.setNomProduit(ci.getProduit().getNomProduit());
				mci.setPrix(ci.getProduit().getPrix());
				LocalDate localDate = ci.getCommande().getDate_commande().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int month = localDate.getMonthValue();
				mci.setMois(month);
				lmci.add(mci);
			}
			for(ModelCommandeItem mcim:lmci) {
				if(mcim.getMois()==mois && mcim.getVendeur()==idVend) {
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
				for(ModelCommandeItem mcif:lmcimvf) {
					if(mci.getProduit()==mcif.getProduit()) {
						mcif.setQuantite(mcif.getQuantite()+mci.getQuantite());
						existe=true;
					}
				}
				if(existe==false) {
				ModelCommandeItem mvpv=new ModelCommandeItem();
				mvpv.setProduit(mci.getProduit());
				mvpv.setQuantite(mci.getQuantite());
				mvpv.setNomProduit(mci.getNomProduit());
				mvpv.setPrix(mci.getPrix());
				lmcimvf.add(mvpv);
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lmcimvf;
	}
	@GetMapping("/quantiteTotalVendusVendeur/{idVend}")
	public float quantiteTotalVendusMois(@PathVariable("idVend")int idVend) {
		List<CommandeItem> lci=new ArrayList<CommandeItem>();
		float qtv=0;
		try {
		lci=cis.getListItems();
		for(CommandeItem ci:lci) {
			if(ps.retrivePaiement(ci.getCommande())!=null && ci.getProduit().getVendeur().getId()==idVend) {
				qtv=qtv+ci.getQuantite();
			}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return qtv;
	}
	@GetMapping("/quantiteTotalVendusVendeurMois/{idVend}/{mois}")
	public float quantiteTotalVendusVendeurMois(@PathVariable("idVend")int idVend,@PathVariable("mois")int mois) {
		List<CommandeItem> lci=new ArrayList<CommandeItem>();
		float qtv=0;
		try {
		lci=cis.getListItems();
		for(CommandeItem ci:lci) {
			LocalDate localDate = ci.getCommande().getDate_commande().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int month = localDate.getMonthValue();
			if(ps.retrivePaiement(ci.getCommande())!=null && ci.getProduit().getVendeur().getId()==idVend && month==mois) {
				qtv=qtv+ci.getQuantite();
			}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return qtv;
	}
}
