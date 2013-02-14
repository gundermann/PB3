package Mappe;

import java.awt.List;
import java.util.ArrayList;

public class Vertrag extends Document{
	
	public int jahr;
	public String status;
	public float zuwendung;
	private ArrayList<Document> documents = new ArrayList<Document>();
	
	
	public ArrayList<Document> getChildren() {
		return documents;
	}
	
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	public int getJahr() {
		return jahr;
	}
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getZuwendung() {
		return zuwendung;
	}
	public void setZuwendung(float zuwendung) {
		this.zuwendung = zuwendung;
	}
	
	

}
