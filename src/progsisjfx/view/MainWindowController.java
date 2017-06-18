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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import progsisjfx.ProgSisJFX;

public class MainWindowController {

    ProgSisJFX mainApp;
    
    @FXML
    Button loadCodeButton;
    @FXML
    Button runCodeButton;
    @FXML
    TableView<?> registersTable;
    @FXML
    TableColumn<?, ?> registerLabelColumn;
    @FXML
    TableColumn<?, ?> registerValueColumn;
    @FXML
    TableView<?> dataMemoryTable;
    @FXML
    TableColumn<?, ?> dataMemoryAddressColumn;
    @FXML
    TableColumn<?, ?> dataMemoryValueColumn;
    @FXML
    TableView<?> instructionMemoryTable;
    @FXML
    TableColumn<?, ?> instructionMemoryAddressColumn;
    @FXML
    TableColumn<?, ?> instructionMemoryValueColumn;
    @FXML
    TextArea codeArea;
    
    /**
     * Initializador do controlador.
     */
    public void initialize() {
        // TODO
    }    
    
    @FXML
    private void loadCodeButtonAction(){
        codeArea.clear();
        loadTextFile(FileExplorer.abrirArquivo(mainApp.getPrimaryStage(), ".txt"), codeArea);
    }
    
    @FXML
    private void runCodeButtonAction(){
        
    }
    
    private String loadTextFile(String filePath, TextArea textArea){
        String text = "";
        BufferedReader bfArquivo = null;
        try{
            bfArquivo = new BufferedReader(new FileReader(new File(filePath))); 
            while((text = bfArquivo.readLine()) != null){
                textArea.appendText(text);
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
    
}
