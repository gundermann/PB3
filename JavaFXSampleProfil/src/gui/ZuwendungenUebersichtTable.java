package gui;

import Mappe.Vertragsblatt;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


public class ZuwendungenUebersichtTable extends HBox {

	private Vertragsblatt vertragsblatt;
	
	public ZuwendungenUebersichtTable(Vertragsblatt vertragsblatt) {
		this.vertragsblatt = vertragsblatt;
		initTable();
	}
	
	private void initTable() {
		TableView<TeilvorgaeneTableData> table = new TableView<TeilvorgaeneTableData>();
		TableColumn vorgangCol = new TableColumn("Bezugsjahr");
		vorgangCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("vorgang"));
		TableColumn statusCol = new TableColumn("Zuwendungsbetrag");
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
		
//		initLines(table);

		table.setPrefWidth(512);
//		table.prefWidthProperty().bind(((HBox) getParent()).prefWidthProperty());
		
		super.getStyleClass().add("table-hauptfenster");
		super.getChildren().add(table);
	}
}
