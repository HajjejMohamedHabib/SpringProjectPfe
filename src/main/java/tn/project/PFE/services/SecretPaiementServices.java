package tn.project.PFE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.SecretPaiment;
import tn.project.PFE.repository.SecretPaimentRepository;

@Service
public class SecretPaiementServices implements ISecretPaimentServices {
@Autowired
SecretPaimentRepository sr;
	@Override
	public SecretPaiment ajouterSecretPaiement(SecretPaiment secPaiement) {
		return sr.save(secPaiement);
	}

	@Override
	public SecretPaiment retriveSecretPaiement(String email) {
		return sr.retriveSecretPaiement(email);
	}

	@Override
	public SecretPaiment retriveSecretPaiementCle(String email, long cle) {
		return sr.retriveSecretPaiementCle(email, cle);
	}

}
