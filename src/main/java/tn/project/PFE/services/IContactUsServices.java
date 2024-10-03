package tn.project.PFE.services;

import java.util.List;

import tn.project.PFE.entities.ContactUs;

public interface IContactUsServices {
public ContactUs ajouterContact(ContactUs contact);
public List<ContactUs> getContact();
}
