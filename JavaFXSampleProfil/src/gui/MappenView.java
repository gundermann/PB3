package gui;

import helper.CommonGuiProblems;

import Mappe.VertragsMappe;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.PGNode;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MappenView extends HBox {

	VertragsMappe currentMappe = new VertragsMappe();
	
public MappenView(VertragsMappe mappe){
	this.currentMappe = mappe;
	GridPane grid =  new GridPane();

	HBox uebersicht = new HBox();
	GridPane innerGrid = new GridPane();
	Label lbazA = new Label("AktenzeichenA:");
	Label lbazB = new Label("AktenzeichenB:");
	Label lbamt = new Label("Amt:");
	Label lbfp = new Label("F�rderprogramm:");
	Label lbeuc = new Label("EU-Code");
	Label lberstauszahlung = new Label("Erstauszahlungsjahr:");
	Label lbstatus = new Label("Status:");
	TextField azA = new TextField(currentMappe.getAzA());
	TextField azB = new TextField(currentMappe.getAzB());
	TextField amt = new TextField(currentMappe.getAmt());
	TextField fp = new TextField(currentMappe.getFp());
	TextField euc = new TextField(currentMappe.getEuCode());
	TextField erstauszahlung = new TextField(currentMappe.getErstzahlungsjahr());
	TextField status = new TextField(currentMappe.getStatus());
	
	//Why this way? Dynamic?
	SimpleStringProperty SSPazA = new SimpleStringProperty(currentMappe.getAzA());
	
	azA.setPrefWidth(150);
	azA.textProperty().bindBidirectional(SSPazA);
	
	innerGrid.add(lbazA, 0, 0);
	innerGrid.add(azA, 1, 0);
	innerGrid.add(lbazB, 0, 1);
	innerGrid.add(azB, 1, 1);
	innerGrid.add(lbamt, 0, 2);
	innerGrid.add(amt, 1, 2);
	innerGrid.add(lbfp, 0, 3);
	innerGrid.add(fp, 1, 3);
	innerGrid.add(lbeuc, 0, 4);
	innerGrid.add(euc, 1, 4);
	innerGrid.add(lberstauszahlung, 0, 5);
	innerGrid.add(erstauszahlung, 1, 5);
	innerGrid.add(lbstatus, 0, 6);
	innerGrid.add(status, 1, 6);
	
	
	uebersicht.getChildren().addAll(innerGrid);
	
	CommonGuiProblems.disableFields(innerGrid);
	
	GridPane border = new GridPane();
	Label teilvorgaengeHeader = new Label("Teilvorg�nge");
	HBox tableTeilvorgaenge = new TeilvorgaengeTable(currentMappe.getTeilvorgaenge());
//	tableTeilvorgaenge.prefWidth(scene.getWidth()*0.6);
	border.add(teilvorgaengeHeader, 0, 0);
	border.add(tableTeilvorgaenge, 0, 1);
	
	grid.add(uebersicht, 0, 0);
	grid.add(border, 0, 1);
	this.getChildren().add(grid);
}
}
