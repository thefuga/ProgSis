package progsisjfx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Unidade Logica e Aritmetica
 * @author arthur piccoli
 */
public abstract class ULA {
    
    ULA(){}
    
    /**
     * Faz as operações basicas da maquina.
     * @param opcode Seleciona a operacao a ser executada
     * @param op1 Operando 1
     * @param op2 Operando 2
     * @return 
     */
    public static int operaULA(int opcode, int op1, int op2){
        switch(opcode){
            case 1: //ADD
                return op1 + op2; 
            case 2: //AND
                return op1 & op2;
            case 3: //NOT
                return ~op1;
            case 4: //XOR
                return op1 ^ op2;   
            case 5: //Signed left shift
                return op1 << op2;     
            case 6: //Signed right shift
                return op1 >> op2;     
            case 7: //Unsigned right shift
                return op1 >>> op2;    
        }
        return -1;
    }
}
