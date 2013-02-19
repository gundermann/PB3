package gui;

import Mappe.Zuwendung;
import javafx.beans.property.SimpleStringProperty;

public class ZuwendungenTableData  {

	
	public ZuwendungenTableData(Zuwendung zuwendung) {
		bezugsjahr = new SimpleStringProperty(String.valueOf(zuwendung.getBezugsJahr()));
		zuwendungsbetrag =  new SimpleStringProperty(String.valueOf(zuwendung.getZuwendungsbetrag()));
		vertragsflaeche =  new SimpleStringProperty(String.valueOf(zuwendung.getVertragsflaeche()));
		anteilEU =  new SimpleStringProperty(String.valueOf(zuwendung.getAnteilEU()));
		anteilLand =  new SimpleStringProperty(String.valueOf(zuwendung.getAnteilLand()));
		anteilSonst =  new SimpleStringProperty(String.valueOf(zuwendung.getAnteilSonst()));
	
	}
	private SimpleStringProperty bezugsjahr;
	private SimpleStringProperty zuwendungsbetrag;
	private SimpleStringProperty vertragsflaeche;
	private SimpleStringProperty anteilEU;
	private SimpleStringProperty anteilLand;
	private SimpleStringProperty anteilSonst;
	
	
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
	public String getAnteilSonst() {
		return anteilSonst.get();
	}
	
	
	
}
