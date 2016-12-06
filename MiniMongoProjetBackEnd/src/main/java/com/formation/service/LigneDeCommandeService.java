package com.formation.service;

import java.util.List;

import com.formation.entity.LigneDeCommande;

public interface LigneDeCommandeService {
/*public LigneDeCommande createLigneDeCommande(LigneDeCommande lignedeCommande);
	public LigneDeCommande findById(Long id_commande);
	public List getVentesParCategory(String category);
	public List getAllLignesDeCommandes();
	public List getLignesDeCommandeParTranche(double prixMin, double prixMax);
	public List getLignesDeCommandeParTheme(String theme);*/
	
	public LigneDeCommande creerLignedeCommande(LigneDeCommande lignedeCommande);
	public LigneDeCommande rechercherParId(Long id);
	public List<LigneDeCommande> rechercherToutesLesLignesDeCommande();
	public List<LigneDeCommande> rechercherLesLignesDeCommandeParCategorie(String categorie);
	public List<LigneDeCommande> rechercherLesLignesDeCommandeParTheme(String theme);
	public List<LigneDeCommande> rechercherLesLignesDeCommandeParTrancheDePrix(double prixMin, double prixMax);
	public int recupererLIdMaxDesCommandes();
}
