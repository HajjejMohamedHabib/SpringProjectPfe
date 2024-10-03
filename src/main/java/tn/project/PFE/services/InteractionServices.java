package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Interaction;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.repository.InteractionRepository;
@Service
public class InteractionServices implements IInteractionServices{
@Autowired
InteractionRepository ir;
	@Override
	public Interaction ajouterInteraction(Interaction i) {
		return ir.save(i);
	}

	@Override
	public List<Interaction> getAllInteractions() {
		return (List<Interaction>) ir.findAll();
	}

	@Override
	public List<Interaction> getAllInteractionProduct(Produit p) {
		return ir.getAllInteractionByProduit(p);
	}

	@Override
	public void updateInteraction(int idInter, int nbreEtoile) {
		ir.updateInteraction(nbreEtoile, idInter);
		
	}

}
