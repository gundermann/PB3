package gui;

import Mappe.Vertragsblatt;
import Mappe.Zuwendung;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.PGNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class ZuwendungenDetailTable extends HBox {

	private Vertragsblatt vertragsblatt;
	private TableView<ZuwendungenDetailTableData> table ;
	
	public ZuwendungenDetailTable(){
		initTable();
	}
	
	private void initTable() {
		table = new TableView<ZuwendungenDetailTableData>();
		TableColumn pcCol = new TableColumn("PC");
		pcCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("pc"));
		TableColumn bezugsjahrCol = new TableColumn("Bezugsjahr");
		bezugsjahrCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("bezugsjahr"));
		TableColumn beihilfesatzCol = new TableColumn("Beihilfesatz");
		beihilfesatzCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("beihilfesatz"));
		TableColumn vertrageflaecheCol = new TableColumn("Vertragsfläche \ngesamt [ha]");
		vertrageflaecheCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("vertragsflaeche"));
		TableColumn zuwendungCol = new TableColumn("Zuwendungsbetrag \ngesamt [EUR]");
		zuwendungCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("zuwendungsbetrag"));
		TableColumn EuAbbCol = new TableColumn("EU-ABB");
		EuAbbCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("euabb"));
		TableColumn EuTitelCol = new TableColumn("EU-Titel");
		EuTitelCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("eutitel"));
		TableColumn anteilEUpCol = new TableColumn("Anteil EU \n[%]");
		anteilEUpCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilEUp"));
		TableColumn anteilEUCol = new TableColumn("Anteil EU \n[EUR]");
		anteilEUCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilEU"));
		TableColumn LandTitelCol = new TableColumn("Landes-\nTitel");
		LandTitelCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("landtitel"));
		TableColumn anteilLandpCol = new TableColumn("Anteil Land \n[%]");
		anteilLandpCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilLandp"));
		TableColumn anteilLandCol = new TableColumn("Anteil Land \n[EUR]");
		anteilLandCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilLand"));
		TableColumn SonstTitelCol = new TableColumn("Titel \nSonstige");
		SonstTitelCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("sonsttitel"));
		TableColumn anteilSonstpCol = new TableColumn("Anteil Sonstige \n[%]");
		anteilSonstpCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilSonstp"));
		TableColumn anteilSonstCol = new TableColumn("Anteil Sonstige \n[EUR]");
		anteilSonstCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilSonst"));
		
		table.getColumns().addAll(pcCol, bezugsjahrCol, beihilfesatzCol,vertrageflaecheCol,zuwendungCol, EuAbbCol,EuTitelCol, anteilEUpCol, anteilEUCol,
				LandTitelCol, anteilLandpCol, anteilLandCol, SonstTitelCol, anteilSonstpCol, anteilSonstCol);
		
		
		table.getColumns().get(4).setMinWidth(70);
		table.getColumns().get(4).setMinWidth(70);
		table.getColumns().get(2).setMinWidth(110);
		table.getColumns().get(3).setMinWidth(100);
		table.getColumns().get(4).setMinWidth(90);
		
		
		super.getStyleClass().add("table-hauptfenster");
		super.getChildren().add(table);
	}
	
	public void updateLines(Zuwendung zuwendung){
		ObservableList<ZuwendungenDetailTableData> vorgaenge = FXCollections.observableArrayList();
					vorgaenge.add(new ZuwendungenDetailTableData(zuwendung));
		table.setItems(vorgaenge);
	}
}
