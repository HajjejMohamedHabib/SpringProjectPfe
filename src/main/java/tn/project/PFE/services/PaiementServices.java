package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.Paiement;
import tn.project.PFE.repository.PaiementRepository;
@Service
public class PaiementServices implements IPaiementServices {
    @Autowired
    PaiementRepository pr;
	@Override
	public Paiement ajouterPaiement(Paiement paiement) {
		return pr.save(paiement);
	}
	@Override
	public List<Paiement> getPaiement() {
		return (List<Paiement>) pr.findAll();
	}
	@Override
	public Paiement retrivePaiement(Commande commande) {
		return pr.retrivePaiement(commande);
	}

}
