package gui;

import helper.CommonGuiProblems;
import helper.SAXLesen;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Mappe.Document;
import Mappe.VertragsMappe;


public class VertragsMappeFXController extends VertragsMappeFXGui{

	public VertragsMappeFXController(VertragsMappe mappe, ArrayList<String> verweise) {
		super(mappe, verweise);
		try {
			super.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initToolbarController();
		
		initDocumentTreeController();
		initVerweiseTreeController();
	}

	private void initToolbarController() {
		
		final Button btGetOrg = ((Button) super.getToolbar().getItems().get(5));
		final Button btLossOrg = ((Button) super.getToolbar().getItems().get(4));
		final Label lbOriginal = ((Label) super.getStatusbarOriginalLabel());
		
		btGetOrg.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				btLossOrg.setDisable(false);
				btGetOrg.setDisable(true);
				lbOriginal.setText("");
			}
		});
		
		btLossOrg.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				btLossOrg.setDisable(true);
				btGetOrg.setDisable(false);
				lbOriginal.setText("Original");
			}
		});
		
	}

	private void initVerweiseTreeController() {

		super.getVerweiseTree().setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0){
				String selectedDocument =  getVerweiseTree().getSelectionModel().getSelectedItem().toString();
				ladeUndOeffneEntsprechendeMappe(selectedDocument);
			}

		});
	}


	private void initDocumentTreeController() {
		
		super.getDocumentTree().setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				String selectedDocument =  getDocumentTree().getSelectionModel().getSelectedItem().toString();
				if (selectedDocument.contains(getCurrentMappe().getTitel())){
					showMappe();
				}
				else{
					changeViewToDoc(currentMappe, selectedDocument);
				}
			}
	});

}
	
	private void ladeUndOeffneEntsprechendeMappe(String selectedDocument) {
		ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();
		
		SAXLesen xmlReader = new SAXLesen();
		int i = 0;
		while(true){
			try{
				vertragsMappen.add(xmlReader.mappeLaden(new VertragsMappe(), ++i));
				
			}catch(IOException ioe){
				break;
			}
		}	
		
		for(VertragsMappe mappe : vertragsMappen){
			if(selectedDocument.contains(mappe.getAzB())){
				try {
					ArrayList<String> verweise = new ArrayList<String>();
					verweise = CommonGuiProblems.findeVerweise(selectedDocument.toString());
					
					new VertragsMappeFXController(mappe, verweise);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	private void changeViewToDoc(Document document, String selectedDocument){
	for(Document doc : document.getChildren()){
		if(selectedDocument.contains(doc.getTitel())){
			showDocument(doc);
			break;
		}
		changeViewToDoc(doc, selectedDocument);
	}
	}
}
	