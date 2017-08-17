/**
 * Classe principal do programa.
 * Guarda os dados do programa e faz o controle.
 * @author Erick Costa Fuga
 * @author Karine
 */
package progsisjfx;

import java.io.IOException;
import javafx.collections.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import progsisjfx.view.InstructionsTableController;
import progsisjfx.view.MainWindowController;
import progsisjfx.view.RootLayoutController;


public class ProgSisJFX extends Application {
    
    public static final int QT_REGS = 8;
    
    private Stage primaryStage;
    private ScrollPane rootLayout;
    private ObservableList<registrador> observa_registradores;
    private List<String> code;
    private IntegerProperty programCounter; //Simulador do PC. Indexa a memória de instruções. 
    private Memory memoryData;
    
    /**
     * Construtor padrão da classe.
     */
    public ProgSisJFX(){
        observa_registradores = FXCollections.observableArrayList();
        initRegisters(observa_registradores, QT_REGS);
        programCounter = new SimpleIntegerProperty(0); //Inicializado em 0
        memoryData = new Memory();
        
    }
    
    /**
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Máquina Virtual LC-3B");
        initRootLayout();
       
        showMainWindow();
    }
    
    /**
     * Inicializa o root layout.
     */
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProgSisJFX.class.getResource("view/RootLayout.fxml"));
            rootLayout = (ScrollPane) loader.load();

            Scene scene = new Scene(rootLayout);
            
            primaryStage.setScene(scene);
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();   
        }catch(IOException e){
        }
    }
    
    /**
     * Chama a janela principal.
     */
    public void showMainWindow(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProgSisJFX.class.getResource("view/MainWindow.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            //rootLayout.setCenter(estacionamento);
            rootLayout.setContent(pane);
            
            MainWindowController controller = loader.getController();
            controller.setMainApp(this);
        }catch(IOException e){
        }
    }
    
    /**
     * Chama a janela de ajuda.
     */
    public void showHelpWindow(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProgSisJFX.class.getResource("view/InstructionsTable.fxml"));
            AnchorPane janela = (AnchorPane) loader.load();
            
            Stage visualizarVeiculoStage = new Stage();
            visualizarVeiculoStage.setTitle("Tabela de instruções");
            visualizarVeiculoStage.initModality(Modality.WINDOW_MODAL);
            visualizarVeiculoStage.initOwner(primaryStage);
            Scene scene = new Scene(janela);
            visualizarVeiculoStage.setScene(scene);
            
            InstructionsTableController controller = loader.getController();
            //controller.setVisualizarVeiculoStage(visualizarVeiculoStage);
            
            visualizarVeiculoStage.showAndWait();
        }catch(IOException e){
        }
    }

    /**
     * Get Primary Stage.
     * Getter do Stage primaryStage.
     * @return primaryStage.
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    /**
     * Método principal do programa.
     * @param args 
     */
    public static void main(String[] args) {
        List<String> teste = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        //Descomente as próximas linhas para testar o SUPER processador de macros!
/*
        teste.add("OR R6 R1 R1");
        teste.add("ADD R0 R1 R2");
        teste.add("ADD R0 R1 R3");
        teste.add("ADD R1 R1 R2");
        //teste.add("MEDIA R1 R2 R5");
        teste.add("AND R1 R1 R2");
        teste.add("MULT3 R1 R9");

        result = MacroProcessor.processMacros(teste);
        
       for(String k: result ){
           System.out.println(k);
        }
        Assembler montador = new Assembler(result);
*/
        launch(args);
        /*
        //lista de registradores
        // Use Java Collections to create the List.
        ArrayList<registrador> lista_reg = new ArrayList<>();
        //Now add observability by wrapping it with ObservableList.
        ObservableList<registrador> observa_registradores = FXCollections.observableArrayList(lista_reg);
        
        int qt_regs = 8;
        
        String pc = Integer.toString(0);
        ObservableList<String> observa_pc = FXCollections.observableArrayList(pc);
        
        //inicializa o banco de registradores
        for (int i = 0; i < qt_regs; i++) {
            registrador regs = new registrador();
            lista_reg.add(regs);
        }

        ArrayList <String> entrada =  new ArrayList<>();
        int opcode;
        boolean fim = false;
        
        //chama a função que faz leitura da entrada de texto
        entrada = arquivosTexto.LeArquivoTexto("entrada.txt");
        
        for (int i = 0; i < entrada.size(); i++) {
            pc = Integer.toString(i); //faz a alteração do pc
            //quebra a instrução
            String[] quebrainstrucao = entrada.get(i).split(" "); 
            //converte o opcode para int para facilitar na OperacoesMaquina
            opcode = Integer.parseInt(quebrainstrucao[0]);
            //chama a ula passando o opcode, o resto das instruções e os registradores
            OperacoesMaquina.trataInstrucao(opcode, quebrainstrucao[1], lista_reg);
        }*/
        
    }
    
    /**
     * Inicializa o banco de registradores.
     * @param bancoRegistradores List que representa o banco de registradores.
     * @param quantidadeRegistradores Quantidade de registradores do banco de 
     * registradores.
     */
    private void initRegisters(List<registrador> bancoRegistradores, int quantidadeRegistradores){
        for (int i = 0; i < quantidadeRegistradores; i++) {
            registrador regs = new registrador();
            bancoRegistradores.add(regs);
        }
    }
    
    
    //----------------------------Getters e Setters-----------------------------

    public ObservableList<registrador> getObserva_registradores() {
        return observa_registradores;
    }

    public void setObserva_registradores(ObservableList<registrador> observa_registradores) {
        this.observa_registradores = observa_registradores;
    }

    public List<String> getCode() {
        return code;
    }

    public void setCode(List<String> code) {
        this.code = code;
    }

    public IntegerProperty getProgramCounterProperty() {
        return programCounter;
    }

    public Memory getMemoryData() {
        return memoryData;
    }
    
    
}
