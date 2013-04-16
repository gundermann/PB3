package gui;

import helper.CommonGuiProblems;

import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout.Alignment;

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
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.text.TextAlignment;

public class VertragsView extends BorderPane {

	
	private String status;
	private Map<String, Button> controlbuttons = new HashMap<String, Button>();
	private Vertrag currentTV;
	private GridPane grid;
	
	public VertragsView(Document tv) {
		currentTV = (Vertrag) tv;
		
		
		grid = initStates();
		this.setCenter(grid);

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
		initialisiert.setId("state");
		initialisiert.setPrefWidth(350);
		Label begonnen = new Label("Bearbeitung begonnen");
		begonnen.setId("state");
		begonnen.setPrefWidth(350);
		Label festgelet = new Label("Mittel festgelegt");
		festgelet.setId("settedState");
		festgelet.setPrefWidth(350);
		Label beendet = new Label("Bearbeitung beendet");
		beendet.setId("state");
		beendet.setPrefWidth(350);
		
		states.add(initialisiert, 0, 0, 2, 1);
		states.add(CommonGuiProblems.ArrowDown(50, 350), 0, 1, 1, 1);
		states.add(begonnen, 0, 2, 2, 1);
		states.add(CommonGuiProblems.ArrowJumpUp(50, 1), 3, 2, 1, 5);
		states.add(CommonGuiProblems.ArrowDown(50, 350), 0, 3, 1, 1);
		states.add(CommonGuiProblems.ArrowUp(50, 350), 1, 3, 1, 1);
		states.add(festgelet, 0, 4, 2, 1);
		states.add(CommonGuiProblems.ArrowDown(50, 350), 0, 5, 1, 1);
		states.add(beendet, 0, 6, 2, 1);
		
		
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
	
	private void changeStateID(String state){
		for (Node label : grid.getChildren()){
			if(label instanceof Label){
			if(((Label) label).getText().equals(state)){
				label.setId("settedState");
			}
			else{
				label.setId("state");
			}
			}
		}
	}

	private void setInitialisiert() {
		changeStateID("Initialisiert");
		
		
		controlbuttons.get("first").setText("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setBegonnen() {
		changeStateID("Bearbeitung begonnen");
		
		controlbuttons.get("first").setText("Mittel festlegen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setFestgelegt() {
		changeStateID("Mittel festgelegt");
		controlbuttons.get("first").setText("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(true);
		controlbuttons.get("sec").setText("Bearbeitung beenden");
	}

	private void setBeendet() {
		changeStateID("Bearbeitung beendet");
		controlbuttons.get("first").setText("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}
}
