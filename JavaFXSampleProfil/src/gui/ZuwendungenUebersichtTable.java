package gui;

import Mappe.Document;
import Mappe.Vertragsblatt;
import Mappe.Zuwendung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


public class ZuwendungenUebersichtTable extends HBox {

	private Vertragsblatt vertragsblatt;
	private TableView<ZuwendungenDetailTableData> table ;
	private ZuwendungenDetailTable details;
	
	public ZuwendungenUebersichtTable(Vertragsblatt vertragsblatt, ZuwendungenDetailTable details) {
		this.vertragsblatt = vertragsblatt;
		this.details = details;
		initTable();
	}
	
	private void initTable() {
		table = new TableView<ZuwendungenDetailTableData>();
		TableColumn bezugsjahrCol = new TableColumn("Bezugsjahr");
		bezugsjahrCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("bezugsjahr"));
		TableColumn zuwendungCol = new TableColumn("Zuwendungsbetrag \ngesamt [EUR]");
		zuwendungCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("zuwendungsbetrag"));
		TableColumn vertrageflaecheCol = new TableColumn("Vertragsfläche \ngesamt [ha]");
		vertrageflaecheCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("vertragsflaeche"));
		TableColumn anteilEUCol = new TableColumn("Anteil EU \n[EUR]");
		anteilEUCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilEU"));
		TableColumn anteilLandCol = new TableColumn("Anteil Land \n[EUR]");
		anteilLandCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilLand"));
		TableColumn anteilSonstCol = new TableColumn("Anteil Sonstige \n[EUR]");
		anteilSonstCol.setCellValueFactory(new PropertyValueFactory<ZuwendungenDetailTableData, String>("anteilSonst"));
		table.getColumns().addAll(bezugsjahrCol, zuwendungCol, vertrageflaecheCol, anteilEUCol, anteilSonstCol);
		
		
		table.getColumns().get(4).setMinWidth(70);
		table.getColumns().get(4).setMinWidth(70);
		table.getColumns().get(2).setMinWidth(110);
		table.getColumns().get(3).setMinWidth(100);
		table.getColumns().get(4).setMinWidth(90);
		
		initLines(table);
		
		table.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				details.updateLines(vertragsblatt.getZuwendungen().get(table.getSelectionModel().getSelectedIndex()));
			}
		});

		
		super.getStyleClass().add("table-hauptfenster");
		super.getChildren().add(table);
	}
	
	private void initLines(TableView table) {
		ObservableList<ZuwendungenTableData> vorgaenge = FXCollections.observableArrayList();
		for(Zuwendung zuwendung : vertragsblatt.getZuwendungen()){
			vorgaenge.add(new ZuwendungenTableData(zuwendung));
			
		}
		table.setItems(vorgaenge);
	}
	

}
