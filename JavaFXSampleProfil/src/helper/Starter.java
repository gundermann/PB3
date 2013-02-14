package helper;

import java.util.ArrayList;

import Mappe.VertragsMappe;
import gui.VertragsMappeFXController;
import gui.VertragsMappeFXGui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application{

	static ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();
	
	public static void main(String[] args) {
		SAXLesen xmlReader = new SAXLesen();
		vertragsMappen.add(xmlReader.mappeLaden(new VertragsMappe()));
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		gotoVertragsMappenGui(arg0);
	}

	private void gotoVertragsMappenGui(Stage arg0) {
		try {
			new VertragsMappeFXController(vertragsMappen.get(0), new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	

	
}
