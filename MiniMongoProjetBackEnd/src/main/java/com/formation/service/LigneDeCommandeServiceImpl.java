package com.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.dao.mongo.LigneDeCommandeDao;
import com.formation.entity.LigneDeCommande;

@Service
public class LigneDeCommandeServiceImpl implements LigneDeCommandeService {

	@Autowired
	private LigneDeCommandeDao ligneCmdDao;
	
	@Override
	public LigneDeCommande creerLignedeCommande(LigneDeCommande lignedeCommande) {
		// TODO Auto-generated method stub
		return ligneCmdDao.createLigneDeCommande(lignedeCommande);
	}

	@Override
	public LigneDeCommande rechercherParId(Long id) {
		// TODO Auto-generated method stub
		return ligneCmdDao.findById(id);
	}

	@Override
	public List<LigneDeCommande> rechercherToutesLesLignesDeCommande() {
		// TODO Auto-generated method stub
		return ligneCmdDao.getAllLignesDeCommandes();
	}

	@Override
	public List<LigneDeCommande> rechercherLesLignesDeCommandeParCategorie(String categorie) {
		// TODO Auto-generated method stub
		return ligneCmdDao.getVentesParCategory(categorie);
	}

	@Override
	public List<LigneDeCommande> rechercherLesLignesDeCommandeParTheme(String theme) {
		// TODO Auto-generated method stub
		return ligneCmdDao.getLignesDeCommandeParTheme(theme);
	}

	@Override
	public List<LigneDeCommande> rechercherLesLignesDeCommandeParTrancheDePrix(double prixMin, double prixMax) {
		// TODO Auto-generated method stub
		return ligneCmdDao.getLignesDeCommandeParTranche(prixMin, prixMax);
	}

	@Override
	public int recupererLIdMaxDesCommandes() {
		// TODO Auto-generated method stub
		return ligneCmdDao.getIdMax();
	}

}
