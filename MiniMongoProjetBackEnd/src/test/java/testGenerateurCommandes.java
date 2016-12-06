import java.util.Timer;
import java.util.TimerTask;

import com.formation.entity.LigneDeCommande;
import com.formation.facade.VenteFacade;
import com.formation.service.LigneDeCommandeService;
import com.formation.test.JeuDeTestCmd;


public class testGenerateurCommandes {

	public static void main(String[] args) {
		
		LigneDeCommandeService lignedeCommandeServ = VenteFacade.getInstance().getLigneDeCommandeService();
		 
		JeuDeTestCmd j = new JeuDeTestCmd();
		
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				//int rand = (int) Math.round((2*Math.random()));
				//LigneDeCommande ligneDeCommande = new LigneDeCommande();
				LigneDeCommande ligneDeCommande = j.genLigneCmd();
				System.out.println(ligneDeCommande);
				/*ligneDeCommande.setCategory(j.genLigneCmd().getCategory());
				ligneDeCommande.setTheme(j.genLigneCmd().getTheme());
				ligneDeCommande.setProduct(j.genLigneCmd().getProduct());
				ligneDeCommande.setCreateYear(j.genLigneCmd().getCreateYear());
				ligneDeCommande.setCreateMonth(j.genLigneCmd().getCreateMonth());
				ligneDeCommande.setPrice(j.genLigneCmd().getPrice());
				ligneDeCommande.setQuantity(j.genLigneCmd().getQuantity());
				//ligneDeCommande = */
				//System.out.println(ligneDeCommande);
				//On fera l'écriture en base plus tard
				lignedeCommandeServ.creerLignedeCommande(ligneDeCommande);
			}
		};
		
		//Lancer le timer
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 500);
		
	}
	
	
}
