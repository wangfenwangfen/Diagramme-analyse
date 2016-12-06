package com.formation.test;

import com.formation.entity.LigneDeCommande;
import com.formation.facade.VenteFacade;
import com.formation.service.LigneDeCommandeService;

public class JeuDeTestCmd {

	public LigneDeCommande genLigneCmd() {
		LigneDeCommandeService lignedeCommandeServ = VenteFacade.getInstance().getLigneDeCommandeService();
		
		LigneDeCommande ligneDeCmd = new LigneDeCommande();
		ligneDeCmd.setId((lignedeCommandeServ.recupererLIdMaxDesCommandes()+1));
		
		String categories[] = {"cinema", "Concert", "Sport"};
		int randCat = (int) Math.round((2*Math.random()));
		ligneDeCmd.setCategory(categories[randCat]);
		
		String theme[] = {"Comedie","Sciencefiction","PopRock","Classique","Rap","Football","Handball"};
		int randTheme = (int) Math.round((6*Math.random()));
		ligneDeCmd.setTheme(theme[randTheme]);
		
		String product[] = {"Evenement 1", "Evenement 2", "Evenement 3", "Evenement 4", "Evenement 5"};
		int randProd = (int) Math.round((4*Math.random()));
		ligneDeCmd.setProduct(product[randProd]);
		
		int years[] = {2014, 2015, 2016};
		int randYr = (int) Math.round((2*Math.random()));
		ligneDeCmd.setCreateYear(years[randYr]);
		
		String months[] = {"jan","fev","mar","avr","mai","juin","juil","aou","sep","oct","nov","dec"};
		int randMonth = (int) Math.round((11*Math.random()));
		ligneDeCmd.setCreateMonth(months[randMonth]);
		
		double price[] = {12.0, 25.0, 54.9, 150.0};
		int randPrice = (int) Math.round((3*Math.random()));
		ligneDeCmd.setPrice(price[randPrice]);
		
		int quantity[] = {5, 1, 2};
		int randQte = (int) Math.round((2*Math.random()));
		ligneDeCmd.setQuantity(quantity[randQte]);
		
		//System.out.println(ligneDeCmd);
		return ligneDeCmd;
	}
}
