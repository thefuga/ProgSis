/**
 * Main Window Controller.
 * Controlador da janela principal da interface.
 * @author Erick Costa Fuga.
 */
package progsisjfx.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import progsisjfx.ControlUnit;
import progsisjfx.ProgSisJFX;
import progsisjfx.registrador;

public class MainWindowController {

    private ProgSisJFX mainApp;
    private ObservableList<String> registersLabels;
    
    @FXML
    private Button loadCodeButton;
    @FXML
    private Button runCodeButton;
    @FXML
    private TableView<String> labelTable;
    @FXML
    private TableColumn<String, String> registerLabelColumn;
    @FXML
    private TableView<registrador> registersTable;
    @FXML
    private TableColumn<registrador, Number> registerValueColumn;
    @FXML
    private TableView<String> dataMemoryTable;
    @FXML
    private TableColumn<String, String> dataMemoryValueColumn;
    @FXML
    private TableView<String> instructionMemoryTable;
    @FXML
    private TableColumn<String, String> instructionMemoryValueColumn;
    @FXML
    private TextArea codeArea;
    @FXML
    private TextArea codeArea2;
    
    /**
     * Construtor padrão.
     */
    public MainWindowController(){
        registersLabels = FXCollections.observableArrayList();
        for(int i = 0; i < 8; i++)
            registersLabels.add("R" + String.valueOf(i));
    }
    
    /**
     * Initializador do controlador.
     */
    public void initialize() {
        instructionMemoryValueColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue()));
        dataMemoryValueColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue()));
        registerValueColumn.setCellValueFactory(value -> value.getValue().getRegisterProperty());
        registerLabelColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue()));
    }    
    
    public void setMainApp(ProgSisJFX mainApp){
        this.mainApp = mainApp;
        instructionMemoryTable.setItems(mainApp.getMemoryData().getInstructionMemory());
        dataMemoryTable.setItems(mainApp.getMemoryData().getDataMemory());
        registersTable.setItems(mainApp.getObserva_registradores());
        labelTable.setItems(registersLabels);
    }
    
    @FXML
    private void loadCodeButtonAction(){
        codeArea.clear();
        loadTextFile(FileExplorer.abrirArquivo(mainApp.getPrimaryStage(), ".txt"), codeArea);
    }
    
	@FXML
    private void loadCodeButtonAction2(){
        codeArea.clear();
        loadTextFile(FileExplorer.abrirArquivo(mainApp.getPrimaryStage(), ".txt"), codeArea2);
    }
	
    /**
     * Botão executar.
     */
    @FXML
    private void runCodeButtonAction(){
        mainApp.getMemoryData().getInstructionMemory().clear();
        mainApp.getMemoryData().getInstructionMemory().addAll(loadCodeToMemory(codeArea.getText())); 
        ControlUnit.startControl(mainApp.getProgramCounterProperty(), mainApp.getMemoryData(), mainApp.getObserva_registradores());
    }
	
	/**
     * Botão executar.
     */
    @FXML
    private void runCodeButtonAction2(){
        mainApp.getMemoryData().getInstructionMemory().clear();
        mainApp.getMemoryData().getInstructionMemory().addAll(loadCodeToMemory(codeArea.getText())); 
        ControlUnit.startControl(mainApp.getProgramCounterProperty(), mainApp.getMemoryData(), mainApp.getObserva_registradores());
    }
    
	/**
     * Botão montar.
     */
    @FXML
    private void assembleCodeButtonAction2(){
        //TODO
    }
	
	/**
     * Botão montar.
     */
    @FXML
    private void assembleCodeButtonAction2(){
        //TODO
    }
	
    private String loadTextFile(String filePath, TextArea textArea){
        String text = "";
        BufferedReader bfArquivo = null;
        try{
            bfArquivo = new BufferedReader(new FileReader(new File(filePath))); 
            while((text = bfArquivo.readLine()) != null){
                textArea.appendText(text + "\n");
            }
        }catch (FileNotFoundException e){
        }catch (IOException e){
        }finally {
            try {
                if (bfArquivo != null) {
                    bfArquivo.close();
                }
            }catch (IOException e) {
            }
        }
        return text;
    }
    
    private List<String> loadCodeToMemory(String code){
        List<String> instructionsList = new ArrayList<>();
        String text;
        BufferedReader bfArquivo = null;
        try{
            bfArquivo = new BufferedReader(new StringReader(code)); 
            while((text = bfArquivo.readLine()) != null){
                instructionsList.add(text);
            }
        }catch (FileNotFoundException e){
        }catch (IOException e){
        }finally {
            try {
                if (bfArquivo != null) {
                    bfArquivo.close();
                }
            }catch (IOException e) {
            }
        }
        return instructionsList;
    }
}
