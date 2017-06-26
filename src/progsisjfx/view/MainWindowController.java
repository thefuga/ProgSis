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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import progsisjfx.ControlUnit;
import progsisjfx.ProgSisJFX;
import progsisjfx.registrador;

public class MainWindowController {

    ProgSisJFX mainApp;
    
    @FXML
    Button loadCodeButton;
    @FXML
    Button runCodeButton;
    @FXML
    TableView<registrador> registersTable;
    //@FXML
    //TableColumn<registrador, Number> registerLabelColumn;
    @FXML
    TableColumn<registrador, Number> registerValueColumn;
    @FXML
    TableView<String> dataMemoryTable;
    //@FXML
    //TableColumn<String, String> dataMemoryAddressColumn;
    @FXML
    TableColumn<String, String> dataMemoryValueColumn;
    @FXML
    TableView<String> instructionMemoryTable;
    //@FXML
    //TableColumn<String, String> instructionMemoryAddressColumn;
    @FXML
    TableColumn<String, String> instructionMemoryValueColumn;
    @FXML
    TextArea codeArea;
    
    /**
     * Initializador do controlador.
     */
    public void initialize() {
        instructionMemoryValueColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue()));
        dataMemoryValueColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue()));
        registerValueColumn.setCellValueFactory(value -> value.getValue().getRegisterProperty());
    }    
    
    public void setMainApp(ProgSisJFX mainApp){
        this.mainApp = mainApp;
        instructionMemoryTable.setItems(mainApp.getMemoryData().getInstructionMemory());
        dataMemoryTable.setItems(mainApp.getMemoryData().getDataMemory());
        registersTable.setItems(mainApp.getObserva_registradores());
    }
    
    @FXML
    private void loadCodeButtonAction(){
        codeArea.clear();
        loadTextFile(FileExplorer.abrirArquivo(mainApp.getPrimaryStage(), ".txt"), codeArea);
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
