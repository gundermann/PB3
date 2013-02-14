package gui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Mappe.Document;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TeilvorgaengeTable extends HBox {

	private SimpleStringProperty titel;
	private ArrayList<Document> teilvorgaenge;
	
	public TeilvorgaengeTable(ArrayList<Document> teilvorgaenge) {
		this.setTeilvorgaenge(teilvorgaenge);
		initTable();
	}

	private void initTable() {
		TableView<TeilvorgaeneTableData> table = new TableView<TeilvorgaeneTableData>();
		TableColumn vorgangCol = new TableColumn("Vorgang");
		vorgangCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("vorgang"));
		TableColumn statusCol = new TableColumn("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("status"));
		TableColumn zuwendungssummeCol = new TableColumn("Zuwendungs-\nsumme [EUR]");
		zuwendungssummeCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("zuwendungssumme"));
		TableColumn zahlungsbetragCol = new TableColumn("Zahlungs-\nbetrag [EUR]");
		zahlungsbetragCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("zahlungsbetrag"));
		TableColumn zahlungdatumCol = new TableColumn("Zahlungs-\ndatum");
		zahlungdatumCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("zahlungsdatum"));
		table.getColumns().addAll(vorgangCol, statusCol, zuwendungssummeCol, zahlungsbetragCol, zahlungdatumCol);
		
		
		table.getColumns().get(4).setMinWidth(70);
		table.getColumns().get(4).setMinWidth(70);
		table.getColumns().get(2).setMinWidth(110);
		table.getColumns().get(3).setMinWidth(100);
		table.getColumns().get(4).setMinWidth(90);
		
		initLines(table);

		table.setPrefWidth(512);
//		table.prefWidthProperty().bind(((HBox) getParent()).prefWidthProperty());
		
		super.getStyleClass().add("table-hauptfenster");
		super.getChildren().add(table);
	}

	private void initLines(TableView table) {
		ObservableList<TeilvorgaeneTableData> vorgaenge = FXCollections.observableArrayList();
		for(Document teilvorgang : teilvorgaenge){
			vorgaenge.add(new TeilvorgaeneTableData(teilvorgang));
			
		}
		table.setItems(vorgaenge);
	}

	public void setTeilvorgaenge(ArrayList<Document> teilvorgaenge) {
		this.teilvorgaenge = teilvorgaenge;
	}

	public ArrayList<Document> getTeilvorgaenge() {
		return teilvorgaenge;
	}

}
