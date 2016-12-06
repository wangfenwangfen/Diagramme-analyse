package com.formation.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.formation.dto.CategoryMontant;
import com.formation.entity.LigneDeCommande;
import com.formation.facade.VenteFacade;
import com.formation.service.ChartService;
import com.formation.service.LigneDeCommandeService;

@ManagedBean
public class ChartView implements Serializable {

	ChartService chartService = VenteFacade.getInstance().getChartService();
	List<CategoryMontant> categories = null;
	LigneDeCommandeService chartServ2 = VenteFacade.getInstance().getLigneDeCommandeService();
	List<LigneDeCommande> lignesDeCommande = null;

	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	private DonutChartModel donut1;
	private DonutChartModel donut2;
	private LineChartModel dateModel;
	private HorizontalBarChartModel horizontalBarModel;

	@PostConstruct
	public void init() {
		categories = new ArrayList<CategoryMontant>();
		lignesDeCommande = new ArrayList<LigneDeCommande>();
		createPieModels();
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public DonutChartModel getDonut1() {
		return donut1;
	}

	public DonutChartModel getDonut2() {
		return donut2;
	}

	public LineChartModel getDateModel() {
		return dateModel;
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	private void createPieModels() {
		createPieModel1();
		createPieModel2();
		createDonut1();
		createDonut2();
		createdateModel();
		createHorizontalBarModel();
	}

	private void createHorizontalBarModel() {
		horizontalBarModel = new HorizontalBarChartModel();
		List<LigneDeCommande> listeTranche0_5 = chartServ2.rechercherLesLignesDeCommandeParTrancheDePrix(0, 5);
		List<LigneDeCommande> listeTranche6_25 = chartServ2.rechercherLesLignesDeCommandeParTrancheDePrix(6, 25);
		List<LigneDeCommande> listeTranche26_2000 = chartServ2.rechercherLesLignesDeCommandeParTrancheDePrix(26, 2000);

		ChartSeries ventes = new ChartSeries();
		ventes.setLabel("Ventes par tranche de prix");
		ventes.set("0 à 5 euros", compteurDeListe(listeTranche0_5));
		ventes.set("6 à 25 euros", compteurDeListe(listeTranche6_25));
		ventes.set("26 à 2000 euros", compteurDeListe(listeTranche26_2000));
		
		horizontalBarModel.addSeries(ventes);
		horizontalBarModel.setTitle("ventes par tranche de prix");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Tranches de prix");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("quantité");        
	}

	public int compteurDeListe(List<LigneDeCommande> listeACompter) {
		if (listeACompter.isEmpty())
			return 0;
		else
			return listeACompter.size();
	}

	private void createdateModel() {
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Ventes selon les mois");

		// ******************************************* Comptage des ventes par
		// mois
		int cptJan = 0;
		int cptFev = 0;
		int cptMar = 0;
		int cptAvr = 0;
		int cptMai = 0;
		int cptJuin = 0;
		int cptJul = 0;
		int cptAout = 0;
		int cptSep = 0;
		int cptOct = 0;
		int cptNov = 0;
		int cptDec = 0;

		lignesDeCommande = chartServ2.rechercherToutesLesLignesDeCommande();
		for (LigneDeCommande lc : lignesDeCommande) {
			switch (lc.getCreateMonth()) {
			case "jan":
				cptJan++;
				break;
			case "fev":
				cptFev++;
				break;
			case "mar":
				cptMar++;
				break;
			case "avr":
				cptAvr++;
				break;
			case "mai":
				cptMai++;
				break;
			case "juin":
				cptJuin++;
				break;
			case "juil":
				cptJul++;
				break;
			case "aou":
				cptAout++;
				break;
			case "sep":
				cptSep++;
				break;
			case "oct":
				cptOct++;
				break;
			case "nov":
				cptNov++;
				break;
			case "dec":
				cptDec++;
				break;
			}
		}
		// *************************************************** CONSTRUCTION DES
		// SERIES
		series1.set("2016-01-01", cptJan);
		series1.set("2016-02-01", cptFev);
		series1.set("2016-03-01", cptMar);
		series1.set("2016-04-01", cptAvr);
		series1.set("2016-05-01", cptMai);
		series1.set("2016-06-01", cptJuin);
		series1.set("2016-07-01", cptJul);
		series1.set("2016-08-01", cptAout);
		series1.set("2016-09-01", cptSep);
		series1.set("2016-10-01", cptOct);
		series1.set("2016-11-01", cptNov);
		series1.set("2016-12-01", cptDec);

		dateModel.addSeries(series1);

		dateModel.setTitle("Répartition des ventes dans l'année");
		dateModel.setZoom(true);
		dateModel.getAxis(AxisType.Y).setLabel("Nombre de ventes");
		DateAxis axis = new DateAxis("Mois");
		axis.setTickAngle(-50);
		// axis.setMax("2017-02-01");
		axis.setTickFormat("%b %#d, %y");

		dateModel.getAxes().put(AxisType.X, axis);
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		categories = chartService.getCategoryMontant();

		for (CategoryMontant catMont : categories) {
			pieModel1.set(catMont.getCategory(),
					(int) Double.parseDouble(catMont.getMontant()) + new Random().nextInt(1000));
		}

		// pieModel1.set("Brand 2", new Random().nextInt(325));
		// pieModel1.set("Brand 3", new Random().nextInt(702));
		// pieModel1.set("Brand 4", new Random().nextInt(421));

		pieModel1.setTitle("Simple Pie");
		pieModel1.setLegendPosition("w");
	}

	private void createPieModel2() {

		pieModel2 = new PieChartModel();

		lignesDeCommande = chartServ2.rechercherToutesLesLignesDeCommande();

		for (LigneDeCommande lc : lignesDeCommande) {
			pieModel2.set(lc.getCategory(), lc.getPrice());
		}

		pieModel2.setTitle("Recap commandes");
		pieModel2.setLegendPosition("w");
	}

	private void createDonut1() {
		donut1 = new DonutChartModel();
		lignesDeCommande = chartServ2.rechercherToutesLesLignesDeCommande();

		Map<String, Number> circle1 = new LinkedHashMap<String, Number>();

		for (LigneDeCommande lc : lignesDeCommande)
			circle1.put(lc.getCategory(), lc.getPrice());

		donut1.addCircle(circle1);

		donut1.setTitle("Recap Commandes par catégories");
		donut1.setLegendPosition("e");
		donut1.setSliceMargin(5);
		donut1.setShadow(true);
		donut1.setShowDataLabels(true);
		donut1.setDataFormat("value");
		donut1.setMouseoverHighlight(true);
	}

	private void createDonut2() {
		donut2 = new DonutChartModel();
		lignesDeCommande = chartServ2.rechercherToutesLesLignesDeCommande();

		Map<String, Number> circle1 = new LinkedHashMap<String, Number>();

		for (LigneDeCommande lc : lignesDeCommande)
			circle1.put(lc.getTheme(), lc.getPrice());

		donut2.addCircle(circle1);

		donut2.setTitle("Recap Commandes par thèmes");
		donut2.setLegendPosition("e");
		donut2.setSliceMargin(5);
		donut2.setShadow(true);
		donut2.setShowDataLabels(true);
		donut2.setDataFormat("value");
		donut2.setMouseoverHighlight(true);
	}

}