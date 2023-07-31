package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {			//acessa o Stage aonde o controller que recebeu a acao esta (ex. o Stage de um Button)
		return (Stage) ((Node) event.getSource()).getScene().getWindow();			//downcasting para acessar o Node. Window e uma superclass de Stage (downcasting)
	}
}
