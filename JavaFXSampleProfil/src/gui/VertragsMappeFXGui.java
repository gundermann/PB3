package gui;

import java.util.ArrayList;
import java.util.HashMap;

import Mappe.Auszahlung;
import Mappe.Document;
import Mappe.Vertrag;
import Mappe.VertragsMappe;
import Mappe.Vertragsblatt;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VertragsMappeFXGui extends Application {

	Group root;
	Scene scene;
	VertragsMappe currentMappe = new VertragsMappe();
	HashMap<String, Parent> guiElemente = new HashMap<String, Parent>();
	ArrayList<String> verweise = new ArrayList<String>();

	public VertragsMappeFXGui(VertragsMappe mappe, ArrayList<String> verweise) {
		currentMappe = mappe;
		this.verweise = verweise;
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
		guiElemente.put("Toolbar", toolbar);
		
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
		
		Image imageDrop = new Image("/Icons/TbCopy.gif");
		Image imagePrint = new Image("/Icons/TbPrint.gif");
		Image imageOrg = new Image("/Icons/TbOriginal.gif");
		Image imageGetOrg = new Image("/Icons/TbKopie.gif");
		Image imageHelp = new Image("/Icons/TbHelp.gif");
		Button btDrop = new Button(null, new ImageView(imageDrop));
		btDrop.getStyleClass().add("barbutton");
		Button btPrint = new Button(null, new ImageView(imagePrint));
		btPrint.getStyleClass().add("barbutton");
		btPrint.setDisable(true);
		Button btLossOrg = new Button(null, new ImageView(imageOrg));
		btLossOrg.getStyleClass().add("barbutton");
		Button btGetOrg = new Button(null, new ImageView(imageGetOrg));
		btGetOrg.getStyleClass().add("barbutton");
		btGetOrg.setDisable(true);
		Button btHelp = new Button(null, new ImageView(imageHelp));
		btHelp.getStyleClass().add("barbutton");
		
		toolbar.getItems().addAll(btDrop, new Separator(), btPrint, new Separator(), btLossOrg, btGetOrg, new Separator(), btHelp);
		return toolbar;
	}

	private HBox initStatusbar() {
		HBox statusbar = new HBox();
//		SplitPane split = new SplitPane();
		statusbar.getStyleClass().add("statusbar");

		Image imgAbbrechen = new Image("/Icons/TbRefresh20x20.gif");
		
		Label status = new Label(currentMappe.getStatus());
		status.setTextAlignment(TextAlignment.RIGHT);
		status.getStyleClass().add("statusLabel");
		status.setMinWidth(300);
		Label mappe = new Label(currentMappe.getFp());
		mappe.setTextAlignment(TextAlignment.RIGHT);
		mappe.getStyleClass().add("statusLabel");
		mappe.setMinWidth(300);
		Label original = new Label();
		original.setTextAlignment(TextAlignment.RIGHT);
		original.setText("Original");
		original.getStyleClass().add("statusLabel");
		original.setMinWidth(100);
		Button btAbbrechen = new Button(null, new ImageView(imgAbbrechen));
		btAbbrechen.getStyleClass().add("statusButton");
		
		guiElemente.put("StatusBarOriginalLabel", original);
		
		statusbar.getChildren().addAll(btAbbrechen, mappe, status, original);
		// split.setDividerPositions(0.3);
//		split.getItems().addAll(status, mappe, btAbbrechen);
		// split.prefWidthProperty().bind(scene.widthProperty());
//		statusbar.getChildren().add(split);
		// statusbar.prefWidthProperty().bind(scene.widthProperty());
//		statusbar.minHeight(100);
		return statusbar;
	}

	private HBox initSplitPane(Group root) {
		HBox splitPaneBereich = new HBox();
		// page 58
		// Horizontaler Split
		SplitPane horizontalerSplit = new SplitPane();
		horizontalerSplit.setDividerPositions(0.3, 0.7);

		SplitPane vertikalerSplit = new SplitPane();
		vertikalerSplit.setOrientation(Orientation.VERTICAL);

		VBox obereBereich = initDocumentBaum();
		VBox untererBereich = initVerweiseBaum();

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

		TreeView<String> documentTree = new TreeView<String>();
		TreeItem documentRoot = new TreeItem();
		documentRoot.setValue(currentMappe.getFp());
		documentTree.setRoot(documentRoot);

		appendDocuments(currentMappe, documentRoot);

		documentBox.getChildren().add(documentTree);
		guiElemente.put("Dokumente", documentTree);

		return documentBox;
	}

	private void appendDocuments(Document doc, TreeItem treeItem) {
		for (Document children : doc.getChildren()) {
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

		for (String verweis : verweise) {
			TreeItem verweiseItem = new TreeItem();
			verweiseItem.setValue(verweis);
			verweiseTree.getRoot().getChildren().add(verweiseItem);
		}

		guiElemente.put("Verweise", verweiseTree);
		
		verweiseBox.getChildren().add(verweiseTree);

		return verweiseBox;
	}

	private HBox initBearbeitungInhalt() {

		HBox inhalt = new HBox(90);

		guiElemente.put("Bearbeitungsfeld", inhalt);
		inhalt.getStyleClass().add("bearbeitungsfeld");

		return inhalt;
	}

	private MenuBar initMenu() {
		MenuBar menu = new MenuBar();

		Menu bearbeitung = new Menu("Bearbeiten");
		menu.getMenus().add(bearbeitung);

		Menu hilfe = new Menu("Hilfe");
		menu.getMenus().add(hilfe);

		menu.prefWidthProperty().bind(scene.widthProperty());

		return menu;
	}

	private HBox getBearbeitungsFeld() {
		return (HBox) guiElemente.get("Bearbeitungsfeld");
	}

	public void showMappe() {
		getBearbeitungsFeld().getChildren().clear();

		getBearbeitungsFeld().getChildren().add(new MappenView(currentMappe));

	}

	public void showDocument(Document doc) {
		if (doc instanceof Vertragsblatt) {
			showVertragsblatt(doc);
		}
		else if (doc instanceof Vertrag) {
			showVertrag(doc);
		}
		else if (doc instanceof Auszahlung) {
			showAuszahlung(doc);
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

	public TreeView getDocumentTree() {
		return (TreeView) guiElemente.get("Dokumente");
	}

	public TreeView getVerweiseTree() {
		return (TreeView) guiElemente.get("Verweise");
	}
	
	public ToolBar getToolbar(){
		return (ToolBar) guiElemente.get("Toolbar");
	}

	public VertragsMappe getCurrentMappe() {
		return currentMappe;
	}

	public Label getStatusbarOriginalLabel(){
		return (Label) guiElemente.get("StatusBarOriginalLabel");
	}
}
