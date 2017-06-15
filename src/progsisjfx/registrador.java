/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsisjfx;

/**
 *
 * @author Karine
 */
public class registrador {
    //coloquei o registrador de instrução como estático porque ele sempre será único
    private static int instructionregister;
    private int register;
    
    //essa classe não tem nada demais só criei ela para encapsular melhor as coisas porque o Erick falou
    public registrador()
    {
        this.instructionregister = 0;
        this.register = 0;
    }

    public static int getInstructionregister() {
        return instructionregister;
    }

    public static void setInstructionregister(int instructionregister) {
        registrador.instructionregister = instructionregister;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }
    
    
}
