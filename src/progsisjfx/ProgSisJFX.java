/**
 *
 * @author erickfuga
 */
package progsisjfx;

import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;


public class ProgSisJFX extends Application {
    
    /**
     * 
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        
    }

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
        
        //lista de registradores
        ObservableList<registrador> registradores = FXCollections.observableArrayList();
        
        ArrayList <String> entrada =  new ArrayList<>();        
        int opcode;
        boolean fim = false;
        
        //chama a função que faz leitura da entrada de texto
        entrada = arquivosTexto.LeArquivoTexto("entrada.txt");
        
        for (int i = 0; i < entrada.size(); i++) {
            
            //quebra a instrução
            String[] quebrainstrucao = entrada.get(i).split(" ");            
            //converte o opcode para int para facilitar na ULA
            opcode = Integer.parseInt(quebrainstrucao[0]);
            //chama a ula passando o opcode, o resto das instruções e os registradores
            ULA.aritmeticaULA(opcode, quebrainstrucao, registradores);
        }
        
        
    }
    
}
