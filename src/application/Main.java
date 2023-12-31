package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene mainScene;			//declarado para ser referenciado em outra class
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));		//instancia objeto FXMLLoader, como parametro a tela MainView
			ScrollPane scrollPane = loader.load();		//carrega a view
			
			scrollPane.setFitToHeight(true);		//ajusta a scrollPane
			scrollPane.setFitToWidth(true);
			
			mainScene = new Scene(scrollPane);		//cena principal, passando a view como argumento
			primaryStage.setScene(mainScene);		//set a cena no palco principal
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();		//mostra o palco
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {		//get padrao atributo privado
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
