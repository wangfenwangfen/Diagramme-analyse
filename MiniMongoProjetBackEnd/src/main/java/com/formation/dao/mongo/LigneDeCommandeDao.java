package com.formation.dao.mongo;


import java.util.List;

import com.formation.entity.LigneDeCommande;

public interface LigneDeCommandeDao {

	public LigneDeCommande createLigneDeCommande(LigneDeCommande lignedeCommande);
	public LigneDeCommande findById(Long id_commande);
	public List getVentesParCategory(String category);
	public List getAllLignesDeCommandes();
	public List getLignesDeCommandeParTranche(double prixMin, double prixMax);
	public List getLignesDeCommandeParTheme(String theme);
	public int getIdMax();
}
