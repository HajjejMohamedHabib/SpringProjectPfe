package tn.project.PFE.services;

import java.util.Date;
import java.util.List;

import tn.project.PFE.entities.Abonnement;
import tn.project.PFE.entities.TypeAbonnement;
import tn.project.PFE.entities.User;

public interface IAbonnementServices {
public Abonnement ajouterAbonnement(Abonnement abonnement);
public Abonnement retriveAbonnement(User vendeur);
public List<Abonnement> displayAbonnement();
public void mettreAjourDateFinAbonnement(User vendeur ,Date dateFinAb,TypeAbonnement typeA);
}
