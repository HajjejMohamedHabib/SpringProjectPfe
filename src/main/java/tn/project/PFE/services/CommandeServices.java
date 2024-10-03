package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.User;
import tn.project.PFE.repository.CommandeRepository;
@Service
public class CommandeServices implements ICommandeServices {
    @Autowired
    CommandeRepository comRep;
	@Override
	public Commande ajoutCommande(Commande commande) {
		
		return comRep.save(commande);
	}
	@Override
	public Commande retriveCommande(int id) {
		return comRep.findById(id).get();
	}
	@Override
	public List<Commande> dispalyCommande() {
		return (List<Commande>) comRep.findAll();
	}
	@Override
	public void approveCommande(int id) {
		comRep.approveCommande(id);
	}
	@Override
	public List<Commande> getCommandesParClient(User client) {
		return comRep.getCommandesParClient(client);
	}

}
