package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.Paiement;

public interface IPaiementServices {
public Paiement ajouterPaiement(Paiement paiement);
public List<Paiement> getPaiement();
public Paiement retrivePaiement(Commande commande);
}
