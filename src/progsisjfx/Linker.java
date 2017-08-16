/**
 * Ligador.
 * Chamado pelo controle após a montagem do programa.
 * @author Arthur "Rolha" Piccoli
 */
package progsisjfx;

import java.util.List;

public class Linker {
    
    /**
     * Ligador.
     * Recebe os códigos a serem ligados e retorna o código final para ser 
     * carregado.
     */
    //PS: Isso é só uma definição meio cagada, principalmente dos parâmetros, é só pra ter uma ideia do que fazer. Fique a vontade pra mexer.
    public Linker(Assembler moduleA, Assembler moduleB){
        
    }
    
    public List<String> getGlobalSymbolsTable(){
        return null;
    }
    
    public List<String> getLinkedCode(){
        return null;
    }
    
}
