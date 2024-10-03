package tn.project.PFE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.project.PFE.entities.Pannier;
import tn.project.PFE.entities.Pannier_Produit;
import tn.project.PFE.entities.Produit;

public interface Pannier_ProduitRepository extends CrudRepository<Pannier_Produit, Integer> {
	@Query("SELECT p FROM Pannier_Produit p WHERE p.pannier=:pannier")
	public List<Pannier_Produit> getPannierClient (@Param("pannier")Pannier pannier);
	@Query("SELECT p FROM Pannier_Produit p WHERE p.pannier=:pannier AND p.produit=:produit")
	public Pannier_Produit retrivePannier_produit (@Param("pannier")Pannier pannier,@Param("produit")Produit produit);
    @Modifying
    @Transactional
    @Query("UPDATE Pannier_Produit p SET p.quantite=p.quantite+1 WHERE p.id=:id")
    public void updateQuantitePlus(@Param("id")int id);
    @Modifying
    @Transactional
    @Query("UPDATE Pannier_Produit p SET p.quantite=p.quantite-1 WHERE p.id=:id")
    public void updateQuantiteMoins(@Param("id")int id);
}
