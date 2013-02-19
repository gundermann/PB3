package gui;

import java.awt.event.TextEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.scene.layout.region.StrokeBorder.LayeredBorderStyleConverter;

import Mappe.Auszahlung;
import Mappe.Document;
import Mappe.Vertrag;
import Mappe.VertragsMappe;
import Mappe.Vertragsblatt;
import helper.SAXLesen;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VertragsMappeFXGui extends Application{

	
	Group root;
	Scene scene;
	VertragsMappe currentMappe = new VertragsMappe();
	HashMap guiElemente = new HashMap();
	
	
	public VertragsMappeFXGui(VertragsMappe mappe){
		currentMappe = mappe;
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new Group();
		scene = new Scene(root, 800, 500);

		BorderPane borderPane = new BorderPane();
		HBox splitPane = initSplitPane(root);

		GridPane menuPane = new GridPane();
		MenuBar menu = initMenu();
		ToolBar toolbar = initToolbar();
		menuPane.add(menu, 0, 0);
		menuPane.add(toolbar, 0, 1);
		
		HBox statusbar = initStatusbar();
		statusbar.prefWidthProperty().bind(scene.widthProperty());
		
		borderPane.setTop(menuPane);
		borderPane.setCenter(splitPane);
		borderPane.setBottom(statusbar);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		
		root.getChildren().add(borderPane);

		String css = "layout.css";
		ObservableList<String> cssStyle = loadSkin(css);
		scene.getStylesheets().clear();
		scene.getStylesheets().addAll(cssStyle);
		
		primaryStage.setTitle("JavaFXSamlpe - Profil");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		showMappe();
	}

	protected final ObservableList<String> loadSkin(String cssFileName) {
		ObservableList<String> cssStyle = FXCollections.observableArrayList();
		cssStyle.addAll(getClass().getResource(cssFileName).toExternalForm());
		return cssStyle;
		}
	
	private ToolBar initToolbar() {
		ToolBar toolbar = new ToolBar();
		toolbar.prefWidthProperty().bind(scene.widthProperty());
		return toolbar;
	}

	private HBox initStatusbar() {
		HBox statusbar = new HBox();
		SplitPane split = new SplitPane();
		
		Label status = new Label(currentMappe.getStatus());
		Label mappe = new Label(currentMappe.getFp());
		Button btAbbrechen = new Button("Abbrechen");
		
//		split.setDividerPositions(0.3);
		split.getItems().addAll(status, mappe, btAbbrechen);
//		split.prefWidthProperty().bind(scene.widthProperty());
		statusbar.getChildren().add(split);
//		statusbar.prefWidthProperty().bind(scene.widthProperty());
		statusbar.minHeight(100);
		return statusbar;
	}

	private HBox initSplitPane(Group root) {
		HBox splitPaneBereich = new HBox();
		//page 58
		//Horizontaler Split
		SplitPane horizontalerSplit = new SplitPane();
		horizontalerSplit.setDividerPositions(0.3, 0.7);
		
		SplitPane vertikalerSplit = new SplitPane();
		vertikalerSplit.setOrientation(Orientation.VERTICAL);

		VBox obereBereich = initDocumentBaum();
		VBox untererBereich= initVerweiseBaum();

		vertikalerSplit.getItems().add(obereBereich);
		vertikalerSplit.getItems().add(untererBereich);

		BorderPane right = new BorderPane();
		HBox rightArea = initBearbeitungInhalt();
		right.setCenter(rightArea);
		VBox leftArea = new VBox();
		leftArea.getChildren().add(vertikalerSplit);
		
		horizontalerSplit.getItems().addAll(leftArea, right);
		horizontalerSplit.prefWidthProperty().bind(scene.widthProperty());
		
		splitPaneBereich.getChildren().add(horizontalerSplit);
		return splitPaneBereich;
	}
	
	private VBox initDocumentBaum() {
		VBox documentBox = new VBox();
		
		TreeView documentTree = new TreeView();
		TreeItem documentRoot = new TreeItem();
		documentRoot.setValue(currentMappe.getFp());
		documentTree.setRoot(documentRoot);
		
		appendDocuments(currentMappe, documentRoot);
		
		documentBox.getChildren().add(documentTree);
		guiElemente.put("Dokumente", documentTree);
		
		return documentBox;
	}

	private void appendDocuments(Document doc, TreeItem treeItem){
		for(Document children : doc.getChildren()){
			TreeItem documentItem = new TreeItem();
			documentItem.setValue(children.getTitel());
			treeItem.getChildren().add(documentItem);
			appendDocuments(children, documentItem);
		}
		
	}
	
	private VBox initVerweiseBaum() {
		VBox verweiseBox = new VBox();
		TreeView verweiseTree = new TreeView();
		TreeItem verweiseRoot = new TreeItem();
		verweiseRoot.setValue("Verweise");
		verweiseTree.setRoot(verweiseRoot);
		
		for(int i=0; i<10;i++){
			TreeItem verweiseItem = new TreeItem();
			verweiseItem.setValue(i);
			verweiseTree.getRoot().getChildren().add(verweiseItem);
		}
		
		verweiseBox.getChildren().add(verweiseTree);
		
		return verweiseBox;
	}

	private HBox initBearbeitungInhalt() {
		
		HBox inhalt = new HBox(90);
		
		guiElemente.put("Bearbeitungsfeld", inhalt);
		inhalt.getStyleClass().add("bearbeitungsfeld");
		
		return inhalt;
	}

	private MenuBar initMenu(){
		MenuBar menu = new MenuBar();
		
		Menu bearbeitung = new Menu("Bearbeiten");
		menu.getMenus().add(bearbeitung);
		
		Menu hilfe = new Menu("Hilfe");
		menu.getMenus().add(hilfe);
		
		menu.prefWidthProperty().bind(scene.widthProperty());
		
		return menu;
	}
	
		
	private HBox getBearbeitungsFeld(){
		return (HBox) guiElemente.get("Bearbeitungsfeld");
	}

	public void showMappe(){
		HBox inhalt = getBearbeitungsFeld();
		getBearbeitungsFeld().getChildren().clear();
		
		getBearbeitungsFeld().getChildren().add(new MappenView(currentMappe));
		
	}
	
	public void showDocument(Document doc) {
		if(doc instanceof Vertrag){
			showVertrag(doc);
		}
		if(doc instanceof Auszahlung){
			showAuszahlung(doc);
		}
		if(doc instanceof Vertragsblatt){
			showVertragsblatt(doc);
		}
	}

	private void showVertragsblatt(Document doc) {
		getBearbeitungsFeld().getChildren().clear();
		VertragsblattView view = new VertragsblattView(doc, currentMappe);
		getBearbeitungsFeld().getChildren().add(view);
	}


	private void showAuszahlung(Document doc) {
		getBearbeitungsFeld().getChildren().clear();
		getBearbeitungsFeld().getChildren().add(new AuszahlungsView(doc));
	}


	private void showVertrag(Document doc) {
		getBearbeitungsFeld().getChildren().clear();
		getBearbeitungsFeld().getChildren().add(new VertragsView(doc));
	}


	public TreeView getDocumentTree(){
		return (TreeView) guiElemente.get("Dokumente");
	}


	public VertragsMappe getCurrentMappe() {
		return currentMappe;
	}
	

}
