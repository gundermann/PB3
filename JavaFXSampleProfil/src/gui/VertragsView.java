package gui;

import java.util.HashMap;
import java.util.Map;

import Mappe.Document;
import Mappe.Vertrag;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.PGNode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VertragsView extends BorderPane {

	
	private String status;
	private Map<String, Button> controlbuttons = new HashMap<String, Button>();
	private Vertrag currentTV;
	
	public VertragsView(Document tv) {
		currentTV = (Vertrag) tv;
		
		
		GridPane currentStateGraph = initStates();
		this.setCenter(currentStateGraph);

		HBox controlPane = initButtons();
		initController();

		this.setBottom(controlPane);
		
		setupOrUpdateStateGraph();
	}

	private void initController() {
		controlbuttons.get("first").setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				currentTV.changeState(controlbuttons.get("first").getText());
				setupOrUpdateStateGraph();
			}
		});
		
		controlbuttons.get("sec").setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				currentTV.changeState(controlbuttons.get("sec").getText());
				setupOrUpdateStateGraph();
			}
		});
		
	}

	private int getState(){
		this.status = currentTV.getStatus();
		if(status.equals("Initialisiert")){
			return 0;
		}
		else if(status.equals("Bearbeitung beendet")){
			return 3;
		}
		else if(status.equals("Bearbeitung begonnen")){
			return 1;
		}
		else{
			return 2;
		}
	}
	
	private HBox initButtons() {
		HBox control = new HBox();
		Button btFirst = new Button();
		btFirst.getStyleClass().add("controlpanelbutton");
		Button btSecond = new Button();
		btSecond.getStyleClass().add("controlpanelbutton");
		
		controlbuttons.put("first", btFirst);
		controlbuttons.put("sec", btSecond);
		
		control.getChildren().addAll(btFirst, btSecond);
		return control;
	}

	private GridPane initStates() {
		GridPane states = new GridPane();
		
		Label initialisiert = new Label("Initialisiert");
		Label begonnen = new Label("Bearbeitung begonnen");
		Label festgelet = new Label("Mittel festgelegt");
		Label beendet = new Label("Bearbeitung beendet");
		
		states.add(initialisiert, 0, 0);
		states.add(begonnen, 0, 1);
		states.add(festgelet, 0, 2);
		states.add(beendet, 0, 3);
		
		return states;
	}

	private void setupOrUpdateStateGraph() {
		switch (getState()) {
		case 0:
			this.setInitialisiert();
			break;
		case 1:
			this.setBegonnen();
			break;
		case 2:
			this.setFestgelegt();
			break;
		case 3:
			this.setBeendet();
			break;
		default:
			break;
		}
		
	}

	private void setInitialisiert() {
		
		
		
		controlbuttons.get("first").setText("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setBegonnen() {
		
		
		controlbuttons.get("first").setText("Mittel festlegen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setFestgelegt() {
		controlbuttons.get("first").setText("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(true);
		controlbuttons.get("sec").setText("Bearbeitung beenden");
	}

	private void setBeendet() {
		controlbuttons.get("first").setText("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}
}
