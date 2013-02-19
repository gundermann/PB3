package gui;


import helper.CommonGuiProblems;
import Mappe.Document;
import Mappe.VertragsMappe;
import Mappe.Vertragsblatt;


import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VertragsblattView extends HBox {

	private Vertragsblatt vertragsblatt;
	private VertragsMappe vertragsMappe;
	
	public VertragsblattView(Document blatt, Document mappe) {
		vertragsblatt = (Vertragsblatt) blatt;
		vertragsMappe = (VertragsMappe) mappe;
		initTabView();
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
		GridPane vertrag = new GridPane();
		
		BorderPane vertragspartner = new BorderPane();
		Label partnerHead = new Label("Vertragspartner");
		
		GridPane partnerGrid = new GridPane();
		partnerGrid.setHgap(10);
	    partnerGrid.setVgap(10);
		partnerGrid.setPadding(new Insets(15, 15, 15, 15));
		
		Label bnrzd = new Label("BNRZD:");
		TextField tfbnrzd = new TextField();
		Label asNummer = new Label("AS-Nummer:");
		TextField tfAsNummer = new TextField();
		Label bezeichnung = new Label("Name/Bezeichung:");
		TextField tfbezeicnung = new TextField();
		
		tfbnrzd.setText(vertragsMappe.getBnrzd());
		tfAsNummer.setText(vertragsMappe.getAzA());
		tfbezeicnung.setText(vertragsMappe.getTitel());
		
		partnerGrid.add(bnrzd, 0, 0);
		partnerGrid.add(tfbnrzd, 1, 0, 4, 1);
		partnerGrid.add(asNummer, 5, 0);
		partnerGrid.add(tfAsNummer, 6, 0, 9, 1);
		partnerGrid.add(bezeichnung, 0, 1);
		partnerGrid.add(tfbezeicnung, 1, 1, 14, 1);
		
 		vertragspartner.setTop(partnerHead);
 		vertragspartner.setCenter(partnerGrid);
 		
		
		BorderPane vertragsinformationen = new BorderPane();
		Label infoHead = new Label("Vertragsinformationen");
		GridPane infoGrid = new GridPane();
		infoGrid.setHgap(10);
	    infoGrid.setVgap(10);
		infoGrid.setPadding(new Insets(15, 15, 15, 15));
		
		Label vertragsnummer = new Label("Vertragsnummer:");
		TextField tfvertragsnummer = new TextField();
		Label vertragsmuster = new Label("Vertragsmuster:");
		TextField tfvertragsmuster = new TextField();
		Label vertragsbeginn = new Label("Vertragsbeginn:");
		TextField tfvertragsbeginn = new TextField();
		Label vertragslaufzeit = new Label("Vertragslaufzeit:");
		TextField tfvertragslaufzeit = new TextField();
		Label vertragsabschluss = new Label("Vertragsabschluss:");
		TextField tfvertragsabschluss = new TextField();
		
		tfvertragsnummer.setText(vertragsMappe.getAzA());
		tfvertragsbeginn.setText(vertragsblatt.getVertragsbeginn());
		tfvertragsmuster.setText("Weide - Wirtschaft");
		tfvertragslaufzeit.setText(String.valueOf(vertragsblatt.getLaufzeit()));
		tfvertragsabschluss.setText(vertragsblatt.getVertragsabschluss());
		
		infoGrid.add(vertragsnummer, 0, 0);
		infoGrid.add(tfvertragsnummer, 1, 0, 5, 1);
		infoGrid.add(vertragsmuster, 0, 1);
		infoGrid.add(tfvertragsmuster, 1, 1, 5, 1);
		infoGrid.add(vertragsbeginn, 7, 0);
		infoGrid.add(tfvertragsbeginn, 8, 0, 5, 1);
		infoGrid.add(vertragslaufzeit, 7, 1);
		infoGrid.add(tfvertragslaufzeit, 8, 1, 5, 1);
		infoGrid.add(vertragsabschluss, 7, 2);
		infoGrid.add(tfvertragsabschluss, 8, 2, 5, 1);
		
		vertragsinformationen.setTop(infoHead);
		vertragsinformationen.setCenter(infoGrid);
		
		CommonGuiProblems.disableFields(infoGrid);
		CommonGuiProblems.disableFields(partnerGrid);
		
		vertrag.add(vertragspartner, 0, 0);
		vertrag.add(vertragsinformationen, 0, 1);
		
		return vertrag;
	}


}
