package gui;


import Mappe.Auszahlung;
import Mappe.Document;
import Mappe.Vertrag;
import javafx.beans.property.SimpleStringProperty;

public class TeilvorgaeneTableData {
	
	private SimpleStringProperty vorgang;
	private SimpleStringProperty status;
	private SimpleStringProperty zahlungsdatum;
	private SimpleStringProperty zahlungsbetrag;
	private SimpleStringProperty zuwendungssumme;

	
	

	public TeilvorgaeneTableData(Document teilvorgang) {
		if (teilvorgang instanceof Vertrag) {
			this.initTeilvorgaeneTableData((Vertrag) teilvorgang);
		}
		if (teilvorgang instanceof Auszahlung) {
			this.initTeilvorgaeneTableData((Auszahlung) teilvorgang);
		}
	}

	private void initTeilvorgaeneTableData(Auszahlung teilvorgang) {
		this.vorgang = new SimpleStringProperty(teilvorgang.getTitel());
		this.status = new SimpleStringProperty(teilvorgang.getStatus());
		this.zuwendungssumme = new SimpleStringProperty(String.valueOf(teilvorgang.getZuwendungssumme()));
		this.zahlungsbetrag = new SimpleStringProperty(String.valueOf(teilvorgang.getZahlungsbetrag()));
	}

	public void initTeilvorgaeneTableData(Vertrag teilvorgang) {
		this.vorgang = new SimpleStringProperty(teilvorgang.getTitel());
		this.status = new SimpleStringProperty(teilvorgang.getStatus());
		this.zuwendungssumme = new SimpleStringProperty(String.valueOf(teilvorgang.getZuwendung()));
	}

	public String getVorgang() {
		if(vorgang == null)
			return "";
		return vorgang.get();
	}

	public void setVorgang(SimpleStringProperty vorgang) {
		this.vorgang = vorgang;
	}

	public String getStatus() {
		if(status == null)
			return "";
		return status.get();
	}

	public void setStatus(SimpleStringProperty status) {
		this.status = status;
	}

	public String getZahlungsdatum() {
		if(zahlungsdatum == null)
			return "";
		return zahlungsdatum.get();
	}

	public void setZahlungsdatum(SimpleStringProperty zahlungsdatum) {
		this.zahlungsdatum = zahlungsdatum;
	}

	public String getZahlungsbetrag() {
		if(zahlungsbetrag == null)
			return "";
		return zahlungsbetrag.get();
	}

	public void setZahlungsbetrag(SimpleStringProperty zahlungsbetrag) {
		this.zahlungsbetrag = zahlungsbetrag;
	}

	public String getZuwendungssumme() {
		if(zuwendungssumme == null)
			return "";
		return zuwendungssumme.get();
	}

	public void setZuwendungssumme(SimpleStringProperty zuwendungssumme) {
		this.zuwendungssumme = zuwendungssumme;
	}

	
	
}
