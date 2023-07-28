package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable{

	@FXML
	private TableView<Department> tableViewDepartment;			//tabela de departamentos
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;			//coluna Id da tabela
	
	@FXML
	private TableColumn<Department, String> tableColumnName;		//coluna Name da tabela
	
	@FXML
	private Button btNew;			//botao New
	
	@FXML
	public void onBtNewAction() {			//acao do botao New
		System.out.println("onBtNewAction");			//teste do controller
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

}
