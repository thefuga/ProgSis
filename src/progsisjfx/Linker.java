/**
 * Ligador.
 * Chamado pelo controle após a montagem do programa.
 * @author Arthur "Rolha" Piccoli
 */
package progsisjfx;

import java.util.List;

public abstract class Linker {
    
    /**
     * Ligador.
     * Recebe os códigos a serem ligados e retorna o código final para ser 
     * carregado.
     * @param codesToLink Lista contendo possíveis códigos e bibliotecas que 
     * devem ser ligadas.
     * @return Lista de String com o código pronto para o carregamento.
     */
    //PS: Isso é só uma definição meio cagada, principalmente dos parâmetros, é só pra ter uma ideia do que fazer. Fique a vontade pra mexer.
    public static List<String> linker(List<List<String>> codesToLink){
        return null;
    }
}
