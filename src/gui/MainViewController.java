package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;			//controles do menu
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {			//controlador de evento padrao: action
		System.out.println("onMenuItemSellerAction");			//teste do controlador
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {			//carrega a tela DepartmentList.fxml; funcao lambda para acessar o controlador e carregar a lista de departamentos.
			controller.setDepartmentService(new DepartmentService());
			controller.updateTabbleView();
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});		//carrega a tela About.fxml; funcao lambda vazia
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		//interface initializable
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {			//manipula a scene principal, adicionando o mainMenu e os children da nova janela (newVBox). Synchronized garante a execucao no multithread. Consumer (Generics) permite o uso de expressoes lambda
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));		//carrega uma tela, endereco da tela recebido como parametro
			VBox newVBox = loader.load();		//tela definida como VBox
			
			Scene mainScene = Main.getMainScene();			//referencia para scene principal (MainView.fxml)
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();			//referencia para acessar o VBox dentro do ScrollPane da mainScene
			
			Node mainMenu = mainVBox.getChildren().get(0);			//referencia para acessar o children na posicao 0 da mainVBox (MenuBar da mainView)
			mainVBox.getChildren().clear();			//limpa todos os children da mainVBox
			mainVBox.getChildren().add(mainMenu);			//adiciona o mainMenu
			mainVBox.getChildren().addAll(newVBox.getChildren());			//adiciona uma colecao, todos os children do newVBox
			
			T controller = loader.getController();			//referencia ao controller com Generics
			initializingAction.accept(controller);			//executa a funcao lambda
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);		//trata a excecao mostrando um "Alerts"
		}
	}
}
