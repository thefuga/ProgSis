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
    public static void startControl(IntegerProperty programCounter, Memory memoryData, List<registrador> registers){
        int last_used=0;
        int newPc=programCounter.get();
        newPc++;
        programCounter.set(newPc++);
        try{
            
            String[] instruction = memoryData.readDataMemory(programCounter.get()).split(" "); 
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
                else if(opcode==2 || opcode==6 || opcode==10){
                    result= ULA.operaULA(1, registers.get(operand1).getRegister(), operand2);
                    //String aux= dataMemory.get(result).read;
                    String aux = memoryData.readDataMemory(result);
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
                    memoryData.writeDataMemory(ULA.operaULA(1, operand1, operand2),Integer.toString(registers.get(destReg).getRegister()));
                }
            }
            else if(opcode==4){
                registers.get(7).setRegister(programCounter.get());
                if(instruction[1].substring(0,1).equals("1")){
                    int operand1 = Integer.parseInt(instruction[1].substring(1,12) + "0");
                    int operand2 = 0;
                    programCounter.set(ULA.operaULA(1, programCounter.get(), operand1));
                }
                else{
                    int operand1 = Integer.parseInt(instruction[1].substring(3, 6));
                    int operand2 = 0;
                    programCounter.set(registers.get(operand1).getRegister());
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
                if(d==0){
                    registers.get(destReg).setRegister(ULA.operaULA(5, registers.get(operand1).getRegister(), operand2));
                }
                else{
                    if(a==0){
                        registers.get(destReg).setRegister(ULA.operaULA(7, registers.get(operand1).getRegister(), operand2));
                    }
                    else{
                        registers.get(destReg).setRegister(ULA.operaULA(6, registers.get(operand1).getRegister(), operand2));
                    }
                }
            }
            else if(opcode==15){
                //int trapvect= Integer.parseInt(instruction[1].substring(4, 12));
                registers.get(7).setRegister(programCounter.get());
                String aux=instruction[1].substring(5, 12);
                for(int i=0; i< (16-aux.length()); i++){
                    aux= aux + "0";
                }
                int trapvect= Integer.parseInt(aux);
                
                programCounter.set(trapvect);
            }
            
            
        }catch(IndexOutOfBoundsException e){
        }
    }
}
