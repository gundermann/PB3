package helper;

import java.util.ArrayList;

import Mappe.VertragsMappe;
import gui.VertragsMappeFXController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application{

	static ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();
	
	public static void main(String[] args) {
		vertragsMappen = CommonGuiProblems.ladeMappen();
		launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		gotoVertragsMappenGui(arg0);
	}

	private void gotoVertragsMappenGui(Stage arg0) {
		try {
			ArrayList<String> verweise = new ArrayList<String>();
			verweise = CommonGuiProblems.findeVerweise(vertragsMappen.get(0).getAzB());
			
			new VertragsMappeFXController(vertragsMappen.get(0),verweise);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	

	
}
