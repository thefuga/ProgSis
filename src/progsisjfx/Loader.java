/**
 * Loader.
 * Recebe o programa pronto para execução e carrega-o na memória.
 * @author Ítalo Nolasco.
 */
package progsisjfx;

import java.util.List;
import javafx.collections.ObservableList;


public abstract class Loader {
    private int codeLenght, counter, counterAux, instructionInt, flag;
    private String[] lineInstruction, opcode1, opcode2, opcode3;;
    private String instructionString;
    
    /**
     * Carregador.
     * Recebe o código pronto e carraga na memória para execução.
     * @param code Código de máquina para ser carregado na memória da 
     * máquina virtual.
     */
    public void Loader(Linker code, List<String> instructionMemory){
        codeLenght = code.getLinkedCode().size();
        counter = 0;
        
        while(counter < codeLenght){
            lineInstruction = code.getLinkedCode().get(counter).split(" ");
            instructionInt = Integer.parseInt(lineInstruction[0], 2);
            instructionString = Integer.toBinaryString(instructionInt);

            if(instructionInt == 1 || instructionInt == 5 || instructionInt == 2 || instructionInt == 10 || instructionInt == 6 || instructionInt == 13 || instructionInt == 3 || instructionInt == 11 || instructionInt == 7){
                opcode1 = code.getLinkedCode().get(counter+1).split(" ");
                opcode2 = code.getLinkedCode().get(counter+2).split(" ");
                opcode3 = code.getLinkedCode().get(counter+3).split(" ");
                instructionMemory.add(instructionString + " " + opcode1[0] + " " + opcode2[0] + " " + opcode3[0]);
                counter += 4;
            }
                
            else if(instructionInt == 0 || instructionInt == 12 || instructionInt == 4){
                opcode1 = code.getLinkedCode().get(counter+1).split(" ");
                instructionMemory.add(instructionString + " " + opcode1[0]);
                counter += 2;
            }
                            
            else if(instructionInt == 14 || instructionInt == 9){
                opcode1 = code.getLinkedCode().get(counter+1).split(" ");
                opcode2 = code.getLinkedCode().get(counter+2).split(" ");
                instructionMemory.add(instructionString + " " + opcode1[0] + " " + opcode2[0]);
                counter += 3; 
            }
                
        }
    }
}
