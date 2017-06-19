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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import progsisjfx.ControlUnit;
import progsisjfx.ProgSisJFX;
import progsisjfx.arquivosTexto;
import progsisjfx.registrador;

public class MainWindowController {

    ProgSisJFX mainApp;
    
    @FXML
    Button loadCodeButton;
    @FXML
    Button runCodeButton;
    @FXML
    TableView<registrador> registersTable;
    @FXML
    TableColumn<registrador, Integer> registerLabelColumn;
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
    
    public void setMainApp(ProgSisJFX mainApp){
        this.mainApp = mainApp;
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
        mainApp.setMemoriaInstrucoes(arquivosTexto.LeArquivoTexto("entrada.txt")); //Coloca as instruções do código na estrutura que simula a memória de instruções.
        ControlUnit.startControl(mainApp.getProgramCounterProperty(), mainApp.getMemoriaInstrucoes(), mainApp.getMemoriaDados(), mainApp.getObserva_registradores());//Inicia a "unidade de controle".
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
}
