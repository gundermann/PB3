package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import Mappe.Document;
import Mappe.VertragsMappe;


public class VertragsMappeFXController extends VertragsMappeFXGui{

	public VertragsMappeFXController(VertragsMappe mappe, Stage primaryStage) {
		super(mappe);
		try {
			super.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initDocumentTreeController();
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
	