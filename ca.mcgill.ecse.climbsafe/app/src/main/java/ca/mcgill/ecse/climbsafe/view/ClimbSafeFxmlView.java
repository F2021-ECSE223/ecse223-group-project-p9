package ca.mcgill.ecse.climbsafe.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ClimbSafeFxmlView extends Application {

	public static final EventType<Event> REFRESH_EVENT = new EventType<>("REFRESH");
	private static ClimbSafeFxmlView instance;
	private List<Node> refreshableNodes = new ArrayList<>();
	private int a = 0;
	private Stage p9climbSafe = null;

	@Override
	public void start(Stage primaryStage) {
		instance = this;
		try {
			var root = (TabPane) FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			//      root.setStyle(ClimbSafeApplication.DARK_MODE ? "-fx-base: rgba(20, 20, 20, 255);" : "");
			var scene = new Scene(root);
			p9climbSafe = primaryStage;
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(800);
			primaryStage.setMinHeight(600);
			primaryStage.setTitle("P9 ClimbSafe");
			primaryStage.show();
			refresh();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stylingSet() throws IOException {

		Stage p9climbSafe2 = new Stage();

//		TabPane s = null;
//		try {
//			s = (TabPane) FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		var root = (TabPane) FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		p9climbSafe.close();
		var scene = new Scene(root);
		p9climbSafe2.setScene(scene);
		//rimaryStage.setScene(scene);
		p9climbSafe2.setMinWidth(800);
		p9climbSafe2.setMinHeight(600);
		p9climbSafe2.setTitle("P9 ClimbSafe");
		p9climbSafe2.show();
		refresh();

		//	  scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		//  	    @Override
		//  	    public void handle(MouseEvent mouseEvent) {
		//  	        System.out.println("mouse click detected! " + a);
		//  	        a++;
		//  	        if(a == 6) {
		//  	        	TabPane s = null;
		//					try {
		//						s = (TabPane) FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		//					} catch (IOException e) {
		//						e.printStackTrace();
		//					}
		//  	        	var scene = new Scene(s);
		//  	        	p9climbSafe.setScene(scene);
		//  	            
		//  	        }
		//  	    }
		//  	    
		//  	});
	}

	// Register the node for receiving refresh events
	public void registerRefreshEvent(Node node) {
		refreshableNodes.add(node);
	}

	// Register multiple nodes for receiving refresh events
	public void registerRefreshEvent(Node... nodes) {
		for (var node: nodes) {
			refreshableNodes.add(node);
		}
	}

	// remove the node from receiving refresh events
	public void removeRefreshableNode(Node node) {
		refreshableNodes.remove(node);
	}

	// fire the refresh event to all registered nodes
	public void refresh() {
		for (Node node : refreshableNodes) {
			node.fireEvent(new Event(REFRESH_EVENT));
		}
	}

	public static ClimbSafeFxmlView getInstance() {
		return instance;
	}

}
