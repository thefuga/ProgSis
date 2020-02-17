/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package progsisjfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Karine
 */

public class registrador {
    //coloquei o registrador de instrução como estático porque ele sempre será único

    private static int instructionregister;
    private IntegerProperty register;

    //essa classe não tem nada demais só criei ela para encapsular melhor as coisas porque o Erick falou

    public registrador()
    {
        instructionregister = 0;
        register = new SimpleIntegerProperty(0);
    }

    public static int getInstructionregister() {
        return instructionregister;
    }

    public static void setInstructionregister(int instructionregister) {
        registrador.instructionregister = instructionregister;
    }

    public int getRegister() {
        return register.get();
    }

    public void setRegister(int register) {
        this.register.set(register);
    }

    public IntegerProperty getRegisterProperty(){
        return register;
    }
}
