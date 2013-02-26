package helper;

import java.io.IOException;
import java.util.ArrayList;

import Mappe.VertragsMappe;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CommonGuiProblems {

	public static void disableFields(GridPane parent) {
		for (Node field : parent.getChildren()){
			if(field instanceof TextField){
				((TextField) field).setEditable(false);
			}
		}
	}

	public static ArrayList<String> findeVerweise(String current){
		ArrayList<String> verweise = new ArrayList<String>();
		ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();
		
		vertragsMappen = ladeMappen();
		
		for(VertragsMappe mappe : vertragsMappen){
			if(!current.contains(mappe.getAzB())){
				verweise.add(mappe.getAzB());
			}
		}
		
		return verweise;
	}
	
	public static ArrayList<VertragsMappe> ladeMappen(){
		ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();
		
		SAXLesen xmlReader = new SAXLesen();
		int i = 0;
		while(true){
			try{
				vertragsMappen.add(xmlReader.mappeLaden(new VertragsMappe(), ++i));
				
			}catch(IOException ioe){
				break;
			}
		}
		
		return vertragsMappen;
	}
}
