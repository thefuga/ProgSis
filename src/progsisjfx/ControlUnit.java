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
        int last_used=0;
        try{
            
            String[] instruction = instructionMemory.get(programCounter.get()).split(" "); 
            int opcode=Integer.parseInt(instruction[0]);
         
            System.out.println(opcode);
            if(opcode==1 || opcode==5){
                int destReg = Integer.parseInt(instruction[1].substring(0, 3));
                int operand1 = Integer.parseInt(instruction[1].substring(3, 6));
                int operand2;
                if(instruction[1].substring(6, 7).equals("0")){
                    operand2= Integer.parseInt(instruction[1].substring(9, 12));
                    
                }
                else{
                    operand2= Integer.parseInt(instruction[1].substring(7, 12));
                    
                }
                if (opcode==1){
                    registers.get(destReg).setRegister( ULA.operaULA(1, registers.get(operand1).getRegister(), registers.get(operand2).getRegister()));
                }
                else{
                    registers.get(destReg).setRegister( ULA.operaULA(2, registers.get(operand1).getRegister(), operand2));
                    
                }
                last_used=registers.get(destReg).getRegister();
            }
            
            else if (opcode==0 || opcode==14){
                
                int destReg= Integer.parseInt(instruction[1].substring(0, 3));
                int operand2= Integer.parseInt(instruction[1].substring(3, 12));
                int operand1=0;
                int result = -1;
                
                if(opcode==0)
                {
                    operand2 = Integer.parseInt(instruction[1].substring(3, 12) + "0");
                    if(instruction[1].substring(0, 1).equals("1") && last_used < 0){
                        result=ULA.operaULA(opcode, programCounter.get(), operand2);
                    }
                    else if(instruction[1].substring(1, 2).equals("1") && last_used == 0){
                        result=ULA.operaULA(opcode, programCounter.get(), operand2);
                    }
                    else if(instruction[1].substring(2, 3).equals("1") && last_used > 0){
                        result=ULA.operaULA(opcode, programCounter.get(), operand2);
                    }
                    programCounter.set(result);
                }
                else if (opcode==14){
                    result=ULA.operaULA(opcode, programCounter.get(), operand2);
                    registers.get(destReg).setRegister(result);
                    programCounter.set(result);
                }
        
            }
            else if(opcode==12 || opcode==2 || opcode==10 || opcode==6 || opcode==9 || opcode == 3 
                    || opcode==11 || opcode==7){
                int destReg = Integer.parseInt(instruction[1].substring(0, 3));
                int result=0;
                int operand1 = Integer.parseInt(instruction[1].substring(3, 6));
                int operand2 = Integer.parseInt(instruction[1].substring(6,12));
                
                if(opcode==12){
                    programCounter.set(registers.get(operand1).getRegister());
                }
                else if(opcode==2 || opcode==10 || opcode==6){
                    result= ULA.operaULA(1, registers.get(operand1).getRegister(), operand2);
                    String aux= dataMemory.get(result).readMemory();
                    int missingBits= 16 - aux.length();
                    for(int i=0; i< missingBits; i++){
                        aux = aux + "0";
                    }
                    registers.get(destReg).setRegister(Integer.parseInt(aux));
                }
                else if(opcode==9){
                    registers.get(destReg).setRegister(ULA.operaULA(3, registers.get(operand1).getRegister(), operand2));
                }
                else if(opcode==3 || opcode==7 || opcode==11){
                    dataMemory.get(ULA.operaULA(1, operand1, operand2)).setMemory(registers.get(destReg).getRegister());
                }
            }
            else if(opcode==4){
                if(instruction[1].substring(0,1).equals("1")){
                    int operand1 = Integer.parseInt(instruction[1].substring(1,12));
                    int operand2 = 0;
                }
                else{
                    int operand1 = Integer.parseInt(instruction[1].substring(3, 6));
                    int operand2 = 0;
                }
            }
            else if (opcode == 8){
                
            }
            else if (opcode == 13){
                int destReg= Integer.parseInt(instruction[1].substring(0, 3));
                int operand1= Integer.parseInt(instruction[1].substring(3, 6));
                int a= Integer.parseInt(instruction[1].substring(6, 7));
                int d= Integer.parseInt(instruction[1].substring(7, 8));
                int operand2= Integer.parseInt(instruction[1].substring(8, 12));
            }
            else if(opcode==15){
                int trapvect= Integer.parseInt(instruction[1].substring(4, 12));
            }
            
                OperacoesMaquina.trataInstrucao(programCounter, Integer.parseInt(instruction[0]), instruction[1], registers, instructionMemory, dataMemory);
        }catch(IndexOutOfBoundsException e){
        }
    }
}
