package helper;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import Mappe.VertragsMappe;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class CommonGuiProblems {

	public static void disableFields(GridPane parent) {
		for (Node field : parent.getChildren()){
			if(field instanceof TextField){
				((TextField) field).setEditable(false);
			}
		}
	}

	public static ArrayList<String> findeVerweise(String current){
		ArrayList<String> verweise = new ArrayList<String>();
		ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();
		
		vertragsMappen = ladeMappen();
		
		for(VertragsMappe mappe : vertragsMappen){
			if(!current.contains(mappe.getAzB())){
				verweise.add(mappe.getAzB());
			}
		}
		
		return verweise;
	}
	
	public static ArrayList<VertragsMappe> ladeMappen(){
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
		
		return vertragsMappen;
	}
	
	public static Path ArrowDown(double parentHeight, double parentWidth){
		Path path = new Path();
		
		MoveTo mover = new MoveTo();
		mover.setX(parentWidth/2);
		mover.setY(0);
		
		LineTo lineTo1 = new LineTo();
		lineTo1.setX(parentWidth/2);
		lineTo1.setY(parentHeight);
		LineTo lineTo2 = new LineTo();
		lineTo2.setX(parentWidth/2-20);
		lineTo2.setY(parentHeight-20);
		LineTo lineTo3 = new LineTo();
		lineTo3.setX(parentWidth/2);
		lineTo3.setY(parentHeight);
		LineTo lineTo4 = new LineTo();
		lineTo4.setX(parentWidth/2+20);
		lineTo4.setY(parentHeight-20);
		
		path.getElements().add(mover);
		path.getElements().add(lineTo1);
		path.getElements().add(lineTo2);
		path.getElements().add(lineTo3);
		path.getElements().add(lineTo4);
		
		return path;
	}
	
	public static Path ArrowUp(double parentHeight, double parentWidth){
		Path path = new Path();
		
		MoveTo mover = new MoveTo();
		mover.setX(parentWidth/2);
		mover.setY(parentHeight);
		
		LineTo lineTo1 = new LineTo();
		lineTo1.setX(parentWidth/2);
		lineTo1.setY(0);
		LineTo lineTo2 = new LineTo();
		lineTo2.setX(parentWidth/2-20);
		lineTo2.setY(20);
		LineTo lineTo3 = new LineTo();
		lineTo3.setX(parentWidth/2);
		lineTo3.setY(0);
		LineTo lineTo4 = new LineTo();
		lineTo4.setX(parentWidth/2+20);
		lineTo4.setY(20);
		
		path.getElements().add(mover);
		path.getElements().add(lineTo1);
		path.getElements().add(lineTo2);
		path.getElements().add(lineTo3);
		path.getElements().add(lineTo4);
		
		return path;
	}

	public static Path ArrowJumpUp(int parentHeight, int elementsToSkip) {
		Path path = new Path();
		
		double bottomYPosition = (parentHeight*elementsToSkip*2)+(2.5*parentHeight);
		
		MoveTo mover = new MoveTo();
		mover.setX(0);
		mover.setY(parentHeight/2);
		
		LineTo lineTo1 = new LineTo();
		lineTo1.setX(20);
		lineTo1.setY((parentHeight/2)-20);
		LineTo lineTo2 = new LineTo();
		lineTo2.setX(0);
		lineTo2.setY(parentHeight/2);
		LineTo lineTo3 = new LineTo();
		lineTo3.setX(20);
		lineTo3.setY((parentHeight/2)+20);
		LineTo lineTo4 = new LineTo();
		lineTo4.setX(0);
		lineTo4.setY(parentHeight/2);
		LineTo lineTo5 = new LineTo();
		lineTo5.setX(50);
		lineTo5.setY(parentHeight/2);
		LineTo lineTo6 = new LineTo();
		lineTo6.setX(50);
		lineTo6.setY(bottomYPosition);
		LineTo lineTo7 = new LineTo();
		lineTo7.setX(0);
		lineTo7.setY(bottomYPosition);
		
		path.getElements().add(mover);
		path.getElements().add(lineTo1);
		path.getElements().add(lineTo2);
		path.getElements().add(lineTo3);
		path.getElements().add(lineTo4);
		path.getElements().add(lineTo5);
		path.getElements().add(lineTo6);
		path.getElements().add(lineTo7);
		
		return path;
	}
}
