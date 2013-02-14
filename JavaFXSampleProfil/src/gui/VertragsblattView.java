package gui;

import Mappe.Document;
import Mappe.Vertragsblatt;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.PGNode;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VertragsblattView extends HBox {

	private Vertragsblatt vertragsblatt;
	
	public VertragsblattView(Document doc) {
		initTabView();
		vertragsblatt = (Vertragsblatt) doc;
	}

	private void initTabView() {
		 TabPane tabPane = new TabPane();
		 
		 Tab tab1 = new Tab();
		 tab1.setClosable(false);
		 tab1.setText("Vertrag");
		 tab1.setContent(initVertragView());
		 tabPane.getTabs().add(tab1);
		 
		 Tab tab2 = new Tab();
		 tab2.setClosable(false);
		 tab2.setText("Zuwendungen");
		 tab2.setContent(initZuwendungenView());
		 tabPane.getTabs().add(tab2);
		 
		 this.getChildren().add(tabPane);
	}

	private Node initZuwendungenView() {
		HBox zuwendungen = new HBox();
		Label headline = new Label("Übersicht je Jahr");
		zuwendungen.getChildren().add(headline);
		
		zuwendungen.getChildren().add(new ZuwendungenUebersichtTable(vertragsblatt));
		return zuwendungen;
	}

	private Node initVertragView() {
		return null;
	}


}
