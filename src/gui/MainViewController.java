package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
		loadView("/gui/DepartmentList.fxml");			//carrega a tela DepartmentList.fxml
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");		//carrega a tela About.fxml
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		//interface initializable
	}
	
	private synchronized void loadView(String absoluteName) {			//manipula a scene principal, adicionando o mainMenu e os children da nova janela (newVBox). Synchronized garante a execucao no multithread
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));		//carrega uma tela, endereco da tela recebido como parametro
			VBox newVBox = loader.load();		//tela definida como VBox
			
			Scene mainScene = Main.getMainScene();			//referencia para scene principal (MainView.fxml)
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();			//referencia para acessar o VBox dentro do ScrollPane da mainScene
			
			Node mainMenu = mainVBox.getChildren().get(0);			//referencia para acessar o children na posicao 0 da mainVBox (MenuBar da mainView)
			mainVBox.getChildren().clear();			//limpa todos os children da mainVBox
			mainVBox.getChildren().add(mainMenu);			//adiciona o mainMenu
			mainVBox.getChildren().addAll(newVBox.getChildren());			//adiciona uma colecao, todos os children do newVBox
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);		//trata a excecao mostrando um "Alerts"
		}
	}
}
