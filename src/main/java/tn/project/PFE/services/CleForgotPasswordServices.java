package tn.project.PFE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.CleForgotPassword;
import tn.project.PFE.repository.CleForgotPasswordRepository;

@Service
public class CleForgotPasswordServices implements ICleForgotPasswordServices {
    @Autowired
    CleForgotPasswordRepository cr;
	@Override
	public CleForgotPassword ajouterCleForgotPassword(CleForgotPassword cleF) {
		return cr.save(cleF);
	}

	@Override
	public CleForgotPassword retriveCleForgotPassword(int id) {
		return cr.retriveCleForgotPassword(id);
	}

}
