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
            int opcode=Integer.parseInt(instruction[0]);
         
            System.out.println(opcode);
            if(opcode==1 || opcode==5){
                int destReg = Integer.parseInt(instruction[1].substring(0, 3));
                int operand1 = Integer.parseInt(instruction[1].substring(3, 6));
                int operand2;
                if(instruction[1].substring(6, 7)=="0"){
                    operand2= Integer.parseInt(instruction[1].substring(9, 12));
                    registers.get(destReg).setRegister( ULA.operaULA(opcode, registers.get(operand1).getRegister(), registers.get(operand2).getRegister()));
                }
                else{
                    operand2= Integer.parseInt(instruction[1].substring(7, 12));
                    registers.get(destReg).setRegister( ULA.operaULA(opcode, registers.get(operand1).getRegister(), operand2));
                }
                
            }
            else if (opcode==0 || opcode==14){
                
                int destReg= Integer.parseInt(instruction[1].substring(0, 3));
                int operand2= Integer.parseInt(instruction[1].substring(3, 12));
                int operand1=0;
               
                int result=ULA.operaULA(opcode, operand1, operand2);
                programCounter.set(result);
                if(opcode==14)
                    registers.get(destReg).setRegister(result);
            }
            else if(opcode==12){
                int destReg = Integer.parseInt(instruction[1].substring(0, 3));
                int operand1 = Integer.parseInt(instruction[1].substring(3, 6));
                int operand2 = 0;
                
            }
                OperacoesMaquina.trataInstrucao(programCounter, Integer.parseInt(instruction[0]), instruction[1], registers, instructionMemory, dataMemory);
        }catch(IndexOutOfBoundsException e){
        }
    }
}
