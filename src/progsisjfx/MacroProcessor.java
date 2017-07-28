/**
 * Processador de macros.
 * Chamado pelo controle antes do montador.
 * @author Renata Junges
 * @author Karine Pestana
 */
package progsisjfx;

import java.util.List;


public abstract class MacroProcessor {
    
    /**
     * Processador de macros.
     * Recebe o código que pode ou não conter macros a serem processadas. O 
     * processador de macros deve verificar se existem macros e, caso existam, 
     * processa-las (igual na prova).
     * @param code Código contendo (ou não) macros a serem processadas.
     * @return Lista de Strings com o código com todas as macros processadas.
     */
    public static List<String> processMacros(List<String> code){
        return null;
    }
}
