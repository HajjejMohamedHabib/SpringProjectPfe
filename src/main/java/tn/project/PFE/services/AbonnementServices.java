package tn.project.PFE.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Abonnement;
import tn.project.PFE.entities.TypeAbonnement;
import tn.project.PFE.entities.User;
import tn.project.PFE.repository.AbonnementRepository;
@Service
public class AbonnementServices implements IAbonnementServices {
  @Autowired
  AbonnementRepository ar;
	@Override
	public Abonnement ajouterAbonnement(Abonnement abonnement) {
		return ar.save(abonnement);
	}

	@Override
	public Abonnement retriveAbonnement(User vendeur) {
		return ar.retriveAbonnement(vendeur);
	}

	@Override
	public List<Abonnement> displayAbonnement() {
		return (List<Abonnement>) ar.findAll();
	}

	@Override
	public void mettreAjourDateFinAbonnement(User vendeur,Date dateFinAb,TypeAbonnement typeA) {
		ar.updateAbonnement(dateFinAb, typeA, vendeur);
		
	}

}
