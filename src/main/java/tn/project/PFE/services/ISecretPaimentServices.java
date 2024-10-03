package tn.project.PFE.services;

import tn.project.PFE.entities.SecretPaiment;

public interface ISecretPaimentServices {
public SecretPaiment ajouterSecretPaiement(SecretPaiment secPaiement);
public SecretPaiment retriveSecretPaiement(String email);
public SecretPaiment retriveSecretPaiementCle(String email,long cle);
}
