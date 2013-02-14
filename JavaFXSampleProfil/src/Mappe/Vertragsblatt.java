package Mappe;

import java.util.ArrayList;

public class Vertragsblatt extends Vertrag {
	
	private String vertragsbeginn;
	private int laufzeit;
	private String vertragsabschluss;
	private ArrayList<Zuwendung> zuwendungen = new ArrayList<Zuwendung>();
	
	
	public String getTitel(){
		return "Vertragsblatt";
	}
	public Zuwendung getLastZuwendung(){
		return zuwendungen.size() == 0 ? null : zuwendungen.get(zuwendungen.size()-1);
	}
	public ArrayList<Zuwendung> getZuwendungen() {
		return zuwendungen;
	}
	public void setZuwendungen(ArrayList<Zuwendung> zuwendungen) {
		this.zuwendungen = zuwendungen;
	}
	public String getVertragsbeginn() {
		return vertragsbeginn;
	}
	public void setVertragsbeginn(String vertragsbeginn) {
		this.vertragsbeginn = vertragsbeginn;
	}
	public int getLaufzeit() {
		return laufzeit;
	}
	public void setLaufzeit(int laufzeit) {
		this.laufzeit = laufzeit;
	}
	public String getVertragsabschluss() {
		return vertragsabschluss;
	}
	public void setVertragsabschluss(String vertragsabschluss) {
		this.vertragsabschluss = vertragsabschluss;
	}
	
	
	

}
