package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onBtNewAction() {			//acao do botao New
		System.out.println("onBtNewAction");			//teste do controller
	}
	
	public void setDepartmentService(DepartmentService service) {			//injecao de dependencia, classe de servico
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializaNodes();			//metodo auxiliar
	}

	private void initializaNodes() {
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

}
