/**
 *
 * @author erickfuga
 */
package progsisjfx;

import java.awt.List;
import javafx.collections.*;
import java.util.ArrayList;
import java.util.Observable;
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
      //  launch(args);
        
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
        }       
        
    }
    
}
