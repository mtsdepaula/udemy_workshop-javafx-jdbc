package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{

	private DepartmentService service;			//dependencia, classe de servico
	
	@FXML
	private TableView<Department> tableViewDepartment;			//tabela de departamentos
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;			//coluna Id da tabela
	
	@FXML
	private TableColumn<Department, String> tableColumnName;		//coluna Name da tabela
	
	@FXML
	private Button btNew;			//botao New
	
	private ObservableList<Department> obsList;			//atributo para exibir a lista de departamentos
	
	@FXML
	public void onBtNewAction(ActionEvent event) {			//botao New, agora recebe um evento de referencia
		Stage parentStage = Utils.currentStage(event);			//acessa o Stage do botao New
		createDialogForm("/gui/DepartmentForm.fxml", parentStage);			//cria a janela de dialogo
	}
	
	public void setDepartmentService(DepartmentService service) {			//injecao de dependencia, classe de servico
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();			//metodo auxiliar
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));			//padrao javaFX para iniciar o comportamento das colunas id e name
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();			//referencia do stage
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());			//faz com que a tabela de departamentos tenha mesma altura da janela (stage)
	}
	
	public void updateTabbleView() {			//acessa o servico, carrega os departamentos e associa a obsList
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);			//exibe a lista de departamentos na tabela
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {			//cria uma janela de dialogo em determinado Stage
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));		//carrega uma tela, endereco da tela recebido como parametro
			Pane pane = loader.load();			//carrega a view em um Painel
			
			Stage dialogStage = new Stage();			//para carregar uma janela de dialogo modal na frente da janela existente e necessario instanciar um novo Stage
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));			//define o painel como elemento raiz da Scene
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);			//define o Stage pai desta janela, ou seja, a tela anterior a janela de dialogo
			dialogStage.initModality(Modality.WINDOW_MODAL);			//define a janela como Modal
			dialogStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
