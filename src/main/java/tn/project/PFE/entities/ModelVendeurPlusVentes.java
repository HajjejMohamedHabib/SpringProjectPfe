package tn.project.PFE.entities;

public class ModelVendeurPlusVentes {
private int vendeur;
private float quantite;
private String nomVendeur;
public ModelVendeurPlusVentes() {
	super();
}

public String getNomVendeur() {
	return nomVendeur;
}

public void setNomVendeur(String nomVendeur) {
	this.nomVendeur = nomVendeur;
}

public int getVendeur() {
	return vendeur;
}
public void setVendeur(int vendeur) {
	this.vendeur = vendeur;
}
public float getQuantite() {
	return quantite;
}
public void setQuantite(float quantite) {
	this.quantite = quantite;
}

}
