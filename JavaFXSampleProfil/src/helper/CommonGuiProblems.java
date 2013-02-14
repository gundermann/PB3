package helper;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CommonGuiProblems {

	public static void disableFields(GridPane parent) {
		for (Node field : parent.getChildren()){
			if(field instanceof TextField){
				((TextField) field).setEditable(false);
			}
		}
		
	}
}
