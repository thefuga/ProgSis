package progsisjfx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arthur
 */
public abstract class ULA {
    
    ULA(){}
    
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
        }
        return -1;
    }
}
