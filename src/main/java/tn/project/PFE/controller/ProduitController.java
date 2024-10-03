package tn.project.PFE.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.project.PFE.entities.CommandeItem;
import tn.project.PFE.entities.ModelAjoutProduit;
import tn.project.PFE.entities.ModelCommandeItem;
import tn.project.PFE.entities.ProductModel;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.ICommandeItemServices;
import tn.project.PFE.services.IPaiementServices;
import tn.project.PFE.services.ProduitServices;
import tn.project.PFE.services.UserServices;

@JsonIgnoreProperties
@RestController
@CrossOrigin("http://localhost:4200")
public class ProduitController {
	@Autowired
	ProduitServices prodserv;
	@Autowired
	UserServices userserv;
	@Autowired
	 ServletContext servletContext;
	@Autowired
	IPaiementServices ps;
	@Autowired
	ICommandeItemServices cis;
	@PostMapping("/ajoutProduit/{idCat}/{idVend}")
	public ProductModel ajouterProduit(@RequestBody String produitt,@RequestParam("file") MultipartFile file,@PathVariable("idCat")int idCat,@PathVariable("idVend")int idVend) throws JsonMappingException, JsonProcessingException {
	Produit produit=new ObjectMapper().readValue(produitt, Produit.class);
		String filename=file.getOriginalFilename();
	String newfilename=FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
	File serverfile=new File(servletContext.getRealPath("/images/"+File.separator+newfilename));
	System.out.println(newfilename);
	try {
		FileUtils.writeByteArrayToFile(serverfile,file.getBytes());
	}catch(Exception e) {
		e.printStackTrace();
	}
	produit.setImage(newfilename);
		prodserv.ajouterProduit(produit,idCat,idVend);
	ProductModel p=new ProductModel();
	p.setId(produit.getId());
	p.setMarque(produit.getMarque());
	p.setPrix(produit.getPrix());
    p.setDescription(produit.getDescription());
    p.setImage(produit.getImage());
    p.setNbre_piece(produit.getNbre_piece());
    p.setNomProduit(produit.getNomProduit());
    p.setIdVend(produit.getVendeur().getId());
    p.setCat(produit.getCategorie().getId());
    return p;
	}
   @GetMapping("/displayProduit")
   public List<ProductModel> dispalyProduits(){
	   List<Produit> produits;
	   String first_name;
	   String last_name;
	   List<ProductModel> ps=new ArrayList<ProductModel>();
//	   try {
	  produits= prodserv.displayProduits();
	  if(produits!=null) {
		  System.out.println("not empty");
		  for (Produit p:produits) {
			  ProductModel product=new ProductModel();
			  product.setId(p.getId());
			  product.setMarque(p.getMarque());
			  product.setDescription(p.getDescription());
			  product.setPrix(p.getPrix());
			  product.setImage(p.getImage());
			  product.setNbre_piece(p.getNbre_piece());
			  product.setIdVend(p.getVendeur().getId());
			  product.setCat(p.getCategorie().getId());
			  product.setNomProduit(p.getNomProduit());
			  if(p.getVendeur().getFirst_name()==null) {
				  first_name="";
			  }else {first_name=p.getVendeur().getFirst_name();}
			  if(p.getVendeur().getLast_name()==null) {
				  last_name="";
			  }else {last_name=p.getVendeur().getLast_name();}
			  product.setNom_vendeur(first_name.concat(" ").concat(last_name));
			  product.setNom_category(p.getCategorie().getNom_categorie());
			  ps.add(product);
		  }
	  }
	 
	    
	//    wsTemplate.convertAndSend("/socket/meetingStarts","hello Socket");
	//   wsTemplate.convertAndSend("/topic/progress", "Hello world");
	  return ps;
   }
   @PutMapping("/updateProduit/{id}")
   public ProductModel updateProduit(@RequestParam("produit") String produit,@RequestParam("file") MultipartFile file,
		   @PathVariable("id")int id) throws JsonMappingException, JsonProcessingException {
	   Produit p=new Produit();
	   ProductModel mp=new ProductModel();
	   p=new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(produit, Produit.class);
		System.out.println(p.getImage()+"this is image");
		if(file.getSize()!=0) { 
		String filename=file.getOriginalFilename();
			String newfilename=FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
			File serverfile=new File(servletContext.getRealPath("/images/"+File.separator+newfilename));
			System.out.println(newfilename);
			try {
				FileUtils.writeByteArrayToFile(serverfile,file.getBytes());
				p.setImage(newfilename);
			prodserv.modifiderProduit(p, id);
			System.out.println("file not null");
			mp.setId(p.getId());
			mp.setDescription(p.getDescription());
			mp.setImage(p.getImage());
			mp.setMarque(p.getMarque());
			mp.setNbre_piece(p.getNbre_piece());
			mp.setNomProduit(p.getNomProduit());
			mp.setPrix(p.getPrix());
			}catch(Exception e) {
				e.printStackTrace();
			}}else {
		try {
			prodserv.modifiderProduit(p, id);
			System.out.println("file null");
			mp.setId(p.getId());
			mp.setDescription(p.getDescription());
			mp.setImage(p.getImage());
			mp.setMarque(p.getMarque());
			mp.setNbre_piece(p.getNbre_piece());
			mp.setNomProduit(p.getNomProduit());
			mp.setPrix(p.getPrix());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}}
	  return mp;
	}
   @DeleteMapping("/deleteProduit/{id}")
   public String deleteProduit(@PathVariable("id")int id) {
	   String msg="";
	   try {
	   prodserv.supprimeProduit(id);
	   msg="produit supprimé";
	   }
	   catch (Exception e) {
		System.out.println(e.getMessage());
	}
	   return msg;
   }
   @GetMapping("/retrouveProduit/{id}")
   public ProductModel retrouveProduit(@PathVariable("id")int id){
	   Produit p=new Produit();
	   ProductModel pm=new ProductModel();
	   p=prodserv.retrouveProduit(id);
	   try {
		pm.setId(id);
	    pm.setMarque(p.getMarque());
		pm.setPrix(p.getPrix());
		pm.setDescription(p.getDescription());
	    pm.setImage(p.getImage());
	    pm.setNbre_piece(p.getNbre_piece());
		pm.setCat(p.getCategorie().getId());
	    pm.setIdVend(p.getVendeur().getId());
	    pm.setNomProduit(p.getNomProduit());
	    pm.setNbre_piece(p.getNbre_piece());
	      }
	   catch (Exception e) {
		System.out.println(e.getMessage());
	}
	   return pm;
   }
   @GetMapping("/produitsSeuleCat/{idCat}")
   public List<ProductModel> produitsSeuleCat(@PathVariable("idCat")int idCat){
	   List<ProductModel> ps=new ArrayList<ProductModel>();
	   String first_name;
	   String last_name;
	   for(Produit produit:prodserv.produitsSeuleCat(idCat)) {
		   ProductModel product=new ProductModel();
			  product.setId(produit.getId());
			  product.setMarque(produit.getMarque());
			  product.setDescription(produit.getDescription());
			  product.setImage(produit.getImage());
			  product.setNbre_piece(produit.getNbre_piece());
			  product.setIdVend(produit.getVendeur().getId());
			  product.setCat(produit.getCategorie().getId());
			  product.setNomProduit(produit.getNomProduit());
			  product.setPrix(produit.getPrix());
			  if(produit.getVendeur().getFirst_name()==null) {
				  first_name="";
			  }else {first_name=produit.getVendeur().getFirst_name();}
			  if(produit.getVendeur().getLast_name()==null) {
				  last_name="";
			  }else {last_name=produit.getVendeur().getLast_name();}
			  product.setNom_vendeur(first_name.concat(" ").concat(last_name));
			  product.setNom_category(produit.getCategorie().getNom_categorie());
			  ps.add(product);  
	   }
	   return ps;
   }
   @GetMapping("/produitsParCatParVendeur/{idCat}/{idVend}")
   public List<ProductModel> produitsParCatParVendeur(@PathVariable("idCat")int idCat,@PathVariable("idVend")int idVend){
	   List<ProductModel> ps=new ArrayList<ProductModel>();
	   String first_name;
	   String last_name;
	   List<Produit> listP=new ArrayList<Produit>();
	   listP=prodserv.produitsSeuleCat(idCat);
	   for(Produit produit:listP) {
		   if(produit.getVendeur().getId()==idVend) {
		   ProductModel product=new ProductModel();
			  product.setId(produit.getId());
			  product.setMarque(produit.getMarque());
			  product.setDescription(produit.getDescription());
			  product.setImage(produit.getImage());
			  product.setNbre_piece(produit.getNbre_piece());
			  product.setIdVend(produit.getVendeur().getId());
			  product.setCat(produit.getCategorie().getId());
			  product.setNomProduit(produit.getNomProduit());
			  product.setPrix(produit.getPrix());
			  if(produit.getVendeur().getFirst_name()==null) {
				  first_name="";
			  }else {first_name=produit.getVendeur().getFirst_name();}
			  if(produit.getVendeur().getLast_name()==null) {
				  last_name="";
			  }else {last_name=produit.getVendeur().getLast_name();}
			  product.setNom_vendeur(first_name.concat(" ").concat(last_name));
			  product.setNom_category(produit.getCategorie().getNom_categorie());
			  ps.add(product);  
	   }}
	   return ps;
   }
   @GetMapping("/displayProduitParMarque/{marque}")
   public List<ProductModel> displayProduitParMarque(@PathVariable("marque")String marque){
	   List<ProductModel> productModel=new ArrayList<ProductModel>();
	   List<Produit> produits= new ArrayList<Produit>();
	   try {
	   produits=prodserv.displayProduitParMarque(marque);
	   for(Produit p:produits) {
		ProductModel pm=new ProductModel();
		pm.setId(p.getId());
		pm.setMarque(p.getMarque());
		pm.setDescription(p.getDescription());
		pm.setImage(p.getImage());
		pm.setNbre_piece(p.getNbre_piece());
		pm.setIdVend(p.getVendeur().getId());
		pm.setCat(p.getCategorie().getId());
		pm.setNomProduit(p.getNomProduit());
		pm.setPrix(p.getPrix());
		productModel.add(pm);
	   }
	   }
	   catch (Exception e) {
		System.out.println(e.getMessage());
	}
	   return productModel;
   }
   @GetMapping("/displayProduitParVendeur/{idVendeur}")
   public List<ProductModel> displayProduitParVendeur(@PathVariable("idVendeur")int idVendeur){
	   List<ProductModel> productModel=new ArrayList<ProductModel>();
	   User vendeur=new User();
	   List<Produit> produits= new ArrayList<Produit>();
	   try {
		   vendeur=userserv.retrouveUser(idVendeur);
	   produits=prodserv.displayProduitsParVendeur(vendeur);
	   for(Produit p:produits) {
		ProductModel pm=new ProductModel();
		pm.setId(p.getId());
		pm.setMarque(p.getMarque());
		pm.setDescription(p.getDescription());
		pm.setImage(p.getImage());
		pm.setNbre_piece(p.getNbre_piece());
		pm.setIdVend(p.getVendeur().getId());
		pm.setCat(p.getCategorie().getId());
		pm.setNomProduit(p.getNomProduit());
		pm.setPrix(p.getPrix());
		productModel.add(pm);
	   }
	   }
	   catch (Exception e) {
		System.out.println(e.getMessage());
	}
	   return productModel;
   }
   @PostMapping("/addProduit")
	public ProductModel addProduit(@RequestParam("MAproduitt") String MAproduitt,@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
	   ModelAjoutProduit MAproduit=new ModelAjoutProduit();
	   if(MAproduitt !=null) {
	   MAproduit=new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(MAproduitt, ModelAjoutProduit.class);
	   }
	   Produit produit=new Produit();
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
	produit.setImage(newfilename);
	 
	   int idVend;
	   idVend=userserv.retrouveUser(MAproduit.getEmail_vendeur()).getId();
	   produit.setMarque(MAproduit.getMarque());
	   produit.setPrix(MAproduit.getPrix());
	   produit.setDescription(MAproduit.getDescription());
	   produit.setNbre_piece(MAproduit.getNbre_piece());
	   produit.setNomProduit(MAproduit.getNomProduit());
	prodserv.ajouterProduit(produit,MAproduit.getIdCat(),idVend);
	ProductModel p=new ProductModel();
	p.setId(produit.getId());
	p.setMarque(produit.getMarque());
	p.setPrix(produit.getPrix());
   p.setDescription(produit.getDescription());
   p.setImage(produit.getImage());
   p.setNbre_piece(produit.getNbre_piece());
   p.setIdVend(produit.getVendeur().getId());
   p.setCat(produit.getCategorie().getId());
   p.setNomProduit(produit.getNomProduit());
   return p;
	}
   @PostMapping("/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) {
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
   @GetMapping("/retriveImage/{id}")
   public byte[] getImage(@PathVariable("id") int id) throws IOException  {
	   Produit p=new Produit();
	   p=prodserv.retrouveProduit(id); 
	   if(new File(servletContext.getRealPath("/images/")+p.getImage()).exists()) {
	   return Files.readAllBytes(Paths.get(servletContext.getRealPath("/images/")+p.getImage()));}
  return null;
   }
   public List<ProductModel> produitsCat(int idCat){
	   List<ProductModel> ps=new ArrayList<ProductModel>();
	   String first_name;
	   String last_name;
	   for(Produit produit:prodserv.produitsSeuleCat(idCat)) {
		   ProductModel product=new ProductModel();
			  product.setId(produit.getId());
			  product.setMarque(produit.getMarque());
			  product.setDescription(produit.getDescription());
			  product.setImage(produit.getImage());
			  product.setNbre_piece(produit.getNbre_piece());
			  product.setIdVend(produit.getVendeur().getId());
			  product.setCat(produit.getCategorie().getId());
			  product.setNomProduit(produit.getNomProduit());
			  product.setPrix(produit.getPrix());
			  if(produit.getVendeur().getFirst_name()==null) {
				  first_name="";
			  }else {first_name=produit.getVendeur().getFirst_name();}
			  if(produit.getVendeur().getLast_name()==null) {
				  last_name="";
			  }else {last_name=produit.getVendeur().getLast_name();}
			  product.setNom_vendeur(first_name.concat(" ").concat(last_name));
			  product.setNom_category(produit.getCategorie().getNom_categorie());
			  ps.add(product);  
	   }
	   return ps;
   }
   @GetMapping("/produitParCateg/{idCat}")
   public List<ProductModel> produitsParCateg(@PathVariable("idCat")int idCat){
	   List<ProductModel> lpm=new ArrayList<ProductModel>(produitsCat(idCat));
	     List<ProductModel> lmpr=new ArrayList<ProductModel>();
	     for(ProductModel pm:lpm) {
	    	 boolean existe=false;
	    	 for(ProductModel pmr:lmpr) {
	    		 if(pm.getMarque().equals(pmr.getMarque()))
	    		 {
	    			 existe=true;
	    			 System.out.println(pm.getMarque());
	    		 }
	    	 }
	    	 if(existe==false) {
	    		 lmpr.add(pm);
	    	 }
	     }
	     return lmpr;
   }
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
   @GetMapping("/listProduitsPlusVendus")
   public List<ProductModel> getProduitsPlusVendus(){
   	List<ModelCommandeItem> lmci=new ArrayList<ModelCommandeItem>();
   	List<ModelCommandeItem> lmcif=new ArrayList<ModelCommandeItem>();
   	List<ProductModel> lpm=new ArrayList<ProductModel>();
   	try {
   	lmci=getProduitPlusVendus();
   	for(ModelCommandeItem mci:lmci) {
   		boolean existe=false;
   		for(ModelCommandeItem mcif:lmcif) {
   			if(mci.getProduit()==mcif.getProduit()) {
   				mcif.setQuantite(mcif.getQuantite()+mci.getQuantite());
   				Produit p=new Produit();
   				p=prodserv.retrouveProduit(mcif.getProduit());
   				mcif.setNomProduit(p.getNomProduit());
   				existe=true;
   			}
   		}
   		if(existe==false) {
   			Produit p=new Produit();
   			p=prodserv.retrouveProduit(mci.getProduit());
   			mci.setNomProduit(p.getNomProduit());
   			mci.setQuantite(mci.getQuantite());
   			mci.setProduit(p.getId());
   			lmcif.add(mci);
   		}
   	}
   lmcif.sort(Comparator.comparing(ModelCommandeItem::getQuantite).reversed());
   if(lmcif.size()>=8) {
	   int i=1;
	   List<ModelCommandeItem> inter=new ArrayList<ModelCommandeItem>();
	  for(ModelCommandeItem mci:lmcif) {
	   if(i<=8) {
		 inter.add(mci);
		 i++;
	   }else {break;}
	   
	  }
	  lmcif=new ArrayList<ModelCommandeItem>();
	  for(ModelCommandeItem mci:inter) {
		  lmcif.add(mci);
	  }
   }
    for(ModelCommandeItem mci:lmcif) {
    	 String first_name;
  	   String last_name;
    	Produit p =new Produit();
    	ProductModel product= new ProductModel();
    	p=prodserv.retrouveProduit(mci.getProduit());
    	 product.setId(p.getId());
		  product.setMarque(p.getMarque());
		  product.setDescription(p.getDescription());
		  product.setPrix(p.getPrix());
		  product.setImage(p.getImage());
		  product.setNbre_piece(p.getNbre_piece());
		  product.setIdVend(p.getVendeur().getId());
		  product.setCat(p.getCategorie().getId());
		  product.setNomProduit(p.getNomProduit());
		  if(p.getVendeur().getFirst_name()==null) {
			  first_name="";
		  }else {first_name=p.getVendeur().getFirst_name();}
		  if(p.getVendeur().getLast_name()==null) {
			  last_name="";
		  }else {last_name=p.getVendeur().getLast_name();}
		  product.setNom_vendeur(first_name.concat(" ").concat(last_name));
		  product.setNom_category(p.getCategorie().getNom_categorie());
		  lpm.add(product);
    }
   	
   	}catch (Exception e) {
   		System.out.println(e.getMessage());
   	}
   	return lpm;
   }
}
