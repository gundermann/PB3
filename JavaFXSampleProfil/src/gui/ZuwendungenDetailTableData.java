package gui;

import javafx.beans.property.SimpleStringProperty;
import Mappe.Zuwendung;

public class ZuwendungenDetailTableData {

	public ZuwendungenDetailTableData(Zuwendung zuwendung) {
		bezugsjahr = new SimpleStringProperty(String.valueOf(zuwendung.getBezugsJahr()));
		zuwendungsbetrag =  new SimpleStringProperty(String.valueOf(zuwendung.getZuwendungsbetrag()));
		vertragsflaeche =  new SimpleStringProperty(String.valueOf(zuwendung.getVertragsflaeche()));
		anteilEU =  new SimpleStringProperty(String.valueOf(zuwendung.getAnteilEU()));
		anteilLand =  new SimpleStringProperty(String.valueOf(zuwendung.getAnteilLand()));
		anteilSonst =  new SimpleStringProperty(String.valueOf(zuwendung.getAnteilSonst()));
		pc = new SimpleStringProperty(String.valueOf(zuwendung.getPC()));
		beihilfesatz = new SimpleStringProperty(String.valueOf(zuwendung.getBeihilfesatz()));
		euabb = new SimpleStringProperty(zuwendung.getEUABB());
		eutitel = new SimpleStringProperty(zuwendung.getEUTitel());
		anteilEUp = new SimpleStringProperty(String.valueOf(zuwendung.getAnteilEU()/zuwendung.getZuwendungsbetrag() * 100));
		landtitel = new SimpleStringProperty(zuwendung.getLandestitel());
		anteilLandp = new SimpleStringProperty(String.valueOf(zuwendung.getAnteilLand()/zuwendung.getZuwendungsbetrag() * 100));
		sonsttitel = new SimpleStringProperty(zuwendung.getTitelSonst());
		anteilSonstp = new SimpleStringProperty(String.valueOf(zuwendung.getAnteilSonst()/zuwendung.getZuwendungsbetrag() *100));
	}
	
	private SimpleStringProperty pc;
	private SimpleStringProperty bezugsjahr;
	private SimpleStringProperty zuwendungsbetrag;
	private SimpleStringProperty vertragsflaeche;
	private SimpleStringProperty beihilfesatz;
	private SimpleStringProperty euabb;
	private SimpleStringProperty eutitel;
	private SimpleStringProperty anteilEUp;
	private SimpleStringProperty landtitel;
	private SimpleStringProperty anteilLandp;
	private SimpleStringProperty sonsttitel;
	private SimpleStringProperty anteilSonstp;
	private SimpleStringProperty anteilEU;
	private SimpleStringProperty anteilLand;
	private SimpleStringProperty anteilSonst;
	
	
	
	
	
	public String getAnteilEUp() {
		return anteilEUp.get();
	}
	public String getSonsttitel() {
		return sonsttitel.get();
	}
	public String getLandestitel() {
		return landtitel.get();
	}
	public String getEutitel() {
		return eutitel.get();
	}
	public String getEuabb() {
		return euabb.get();
	}
	public String getBeihilfesatz() {
		return beihilfesatz.get();
	}
	public String getPc() {
		return pc.get();
	}
	public String getBezugsjahr() {
		return bezugsjahr.get();
	}
	public String getZuwendungsbetrag() {
		return zuwendungsbetrag.get();
	}
	public String getVertragsflaeche() {
		return vertragsflaeche.get();
	}
	public String getAnteilEU() {
		return anteilEU.get();
	}
	public String getAnteilLand() {
		return anteilLand.get();
	}
	public String getAnteilLandp() {
		return anteilLandp.get();
	}
	public String getAnteilSonstp() {
		return anteilSonstp.get();
	}
	public String getAnteilSonst() {
		return anteilSonst.get();
	}

}
