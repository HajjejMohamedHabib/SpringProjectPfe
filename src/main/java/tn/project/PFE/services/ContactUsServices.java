package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.ContactUs;
import tn.project.PFE.repository.ContactUsRepository;

@Service
public class ContactUsServices implements IContactUsServices {
@Autowired
ContactUsRepository contRep;
	@Override
	public ContactUs ajouterContact(ContactUs contact) {
		return contRep.save(contact);
	
	}

	@Override
	public List<ContactUs> getContact() {
		return (List<ContactUs>) contRep.findAll();
	}

}
