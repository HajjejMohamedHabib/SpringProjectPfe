package tn.project.PFE.services;

import tn.project.PFE.entities.CleForgotPassword;

public interface ICleForgotPasswordServices {
public CleForgotPassword ajouterCleForgotPassword(CleForgotPassword cleF);
public CleForgotPassword retriveCleForgotPassword (int id);
}
