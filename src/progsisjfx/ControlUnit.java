/**
 * Unidade de controle.
 * Simula de forma simples a unidade de controle.
 * @author Erick Costa Fuga
 */
package progsisjfx;

import java.util.List;
import javafx.beans.property.IntegerProperty;


public abstract class ControlUnit {
    static int last_used=0;
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
        
        int newPc=programCounter.get();
        int j=0;
        
        try{
            while(true){
                String[] instruction = memoryData.readInstructionMemory(programCounter.get()).split(" "); 
                System.out.println("PC: " + programCounter.get());
                int opcode=Integer.parseInt(instruction[0], 2);
                
                if(opcode==1 || opcode==5){
                    int destReg = Integer.parseInt(instruction[1].substring(0, 3), 2);
                    int operand1 = Integer.parseInt(instruction[1].substring(3, 6), 2);
                    int operand2;
                    if(instruction[1].substring(6, 7).equals("0")){
                        operand2= registers.get(Integer.parseInt(instruction[1].substring(9, 12), 2)).getRegister();
                    }
                    else{
                        operand2= Integer.parseInt(instruction[1].substring(7, 12), 2);
                        
                    }
                    if (opcode==1){
                        registers.get(destReg).setRegister( ULA.operaULA(1, registers.get(operand1).getRegister(), operand2));
                    }
                    else{
                        registers.get(destReg).setRegister( ULA.operaULA(2, registers.get(operand1).getRegister(), operand2));

                    }
                    System.out.println("opcode: " + opcode + " reg1: " + operand1 + " reg2: " + operand2);
                    last_used=registers.get(destReg).getRegister();
                }
                
                //BR
                else if(opcode==0){
                    int operand2= Integer.parseInt(instruction[1].substring(4, 12), 2);
                    int operand1=0;
                    int result = -1;
                    if(instruction[1].substring(3, 4).equals("1")){
                        operand2= operand2 * -1;
                        System.out.println("operand2: " + operand2);
                    }
                    if(instruction[1].substring(0, 1).equals("1") && last_used < 0){
                        result=ULA.operaULA(1, programCounter.get(), operand2);
                        System.out.println("result: " + result);
                    }
                    else if(instruction[1].substring(1, 2).equals("1") && last_used == 0){
                        result=ULA.operaULA(1, programCounter.get(), operand2);
                        System.out.println("result: " + result);
                    }
                    else if(instruction[1].substring(2, 3).equals("1") && last_used > 0){
                        result=ULA.operaULA(1, programCounter.get(), operand2);
                        System.out.println("result: " + result);
                    }
                    newPc= result;
                    //programCounter.set(result);
                }
                
                //LEA
                else if ( opcode==14){
                    int destReg= Integer.parseInt(instruction[1].substring(0, 3), 2);
                    int operand2= Integer.parseInt(instruction[1].substring(4, 12), 2);
                    if(instruction[1].substring(3, 4).equals("1")){
                        operand2= operand2 * -1;
                        //System.out.println("operand2: " + operand2);
                    }
                    
                    int result=ULA.operaULA(1, programCounter.get(), operand2);
                    registers.get(destReg).setRegister(result);
                    //programCounter.set(result);
                    last_used=result;
                }
                
                //JSR, JSRR, JMP e RET
                else if(opcode==12 || opcode==4){
                    int destReg = Integer.parseInt(instruction[1].substring(3, 6), 2);
                    //int operand2 = Integer.parseInt(instruction[1].substring(6,12));
                    newPc=destReg;
                    
                }
                
                //LDB
                else if(opcode==2 || opcode==6){
                    int destReg= Integer.parseInt(instruction[1].substring(0, 3), 2);
                    int operand1= Integer.parseInt(instruction[1].substring(3, 6), 2);
                    int operand2= Integer.parseInt(instruction[1].substring(7, 12), 2);
                    if(instruction[1].substring(6,7).equals("1")){
                        operand2=operand2 * -1;
                    }
                    int result = ULA.operaULA(1, registers.get(operand1).getRegister(), operand2);
                    
                    registers.get(destReg).setRegister(Integer.parseInt(memoryData.readDataMemory(result)));
                }
                
                //STB  
                else if(opcode==3 || opcode==7){
                    int destReg= Integer.parseInt(instruction[1].substring(0, 3), 2);
                    int operand1= Integer.parseInt(instruction[1].substring(3, 6), 2);
                    int operand2= Integer.parseInt(instruction[1].substring(7, 12), 2);
                    if(instruction[1].substring(6,7).equals("1")){
                        operand2=operand2 * -1;
                    }
                    
                    memoryData.writeDataMemory(ULA.operaULA(1, registers.get(operand1).getRegister(), operand2), String.valueOf(registers.get(destReg).getRegister()));
                }
                
                else if(opcode==8){
                    
                }
                
                else if(opcode==9){
                    int destReg= Integer.parseInt(instruction[1].substring(0, 3), 2);
                    int operand1= Integer.parseInt(instruction[1].substring(3, 6), 2);
                    int operand2= Integer.parseInt(instruction[1].substring(7, 12), 2);
                    if(operand2==64)
                    {
                        registers.get(destReg).setRegister(registers.get(operand1).getRegister());
                    }
                }
                else if(opcode==2 || opcode==10 ||  opcode==9  
                        || opcode==11 ){
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
                j++;
                if(j==20){
                    registers.get(-1);
                }
                programCounter.set(++newPc);
            }
            
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
