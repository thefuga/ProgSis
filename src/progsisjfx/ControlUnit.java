/**
 * Unidade de controle.
 * Simula de forma simples a unidade de controle.
 * @author Erick Costa Fuga
 */
package progsisjfx;

import java.util.List;
import javafx.beans.property.IntegerProperty;


public abstract class ControlUnit {
    /**
     * Unidade de controle.
     * Recebe tudo que pode ser necessário na instrução e repassa para a classe 
     * que faz a lógica da ula.
     * Basicamente apenas pega a instrução referente ao PC (para os casos de 
     * desvio) e manda para a ula. Faz o catch da exceção cado o PC aponte para 
     * algo inválido.
     * @param programCounter 
     * @param instructionMemory
     * @param dataMemory
     * @param registers 
     */
    public static void startControl(IntegerProperty programCounter, List<String> instructionMemory, List<String> dataMemory, List<registrador> registers){
        try{
            String[] instruction = instructionMemory.get(programCounter.get()).split(" "); 
            OperacoesMaquina.trataInstrucao(programCounter, Integer.parseInt(instruction[0]), instruction[1], registers, instructionMemory, dataMemory);
        }catch(IndexOutOfBoundsException e){
        }
    }
}
