/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsisjfx;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Karine
 */
public class OperacoesMaquina {
    
    OperacoesMaquina(){}    
    
    public static void trataInstrucao(int opcode, String instrucao, ArrayList<registrador> regs)
    {
        //teste
        //System.out.println(instrucao);
        //ULA        
        
        //AND     - 0101
        //BR      - 0000
       //JMP     - 1100
        //JSR(R)  - 0100
        //LBD     - 0010
        //LDW     - 0110
        //LEA     - 1110
        //RTI     - 1000
        //SHF     - 1101
        //STB     - 0011
        //STW     - 0111
        //TRAP    - 1111
        //XOR - 1001
        
        switch(opcode){
            case 1:
                //ADD                
                break;
            case 2:
                //LBD                
                break;
            case 3:
                break;
            case 4:
                break;                
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;                
            case 9:
                break;
            case 10:
                break;
        }

    
    }
}
