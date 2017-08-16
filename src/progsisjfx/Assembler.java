/**
 * Montador.
 * Chamado pelo controle após o processamento das macros.
 * @author Kristofer "Cepo" Kappel
 */
package progsisjfx;

import java.util.List;

public abstract class Assembler {
    
    /**
     * Método responsável por simular a montagem.
     * @param objCode Código objeto com todas as macros processadas.
     * @return Lista de Strings do programa montado.
     */
    public static List<String> assemble(List<String> objCode){
        return null;
    }
    
    public List<String> getCode(){
        return null;
    }
    
    public List<String> getUsesTable(){
        return null;
    }
    
    public List<String> getDefinitionsTable(){
        return null;
    }
}
