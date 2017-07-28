/**
 * Loader.
 * Recebe o programa pronto para execução e carrega-o na memória.
 * @author Ítalo "Champignon" Nolasco.
 */
package progsisjfx;

import java.util.List;


public abstract class Loader {
    /**
     * Carregador.
     * Recebe o código pronto e carraga na memória para execução.
     * @param machineCode Código de máquina para ser carregado na memória da 
     * máquina virutal.
     */
    public void load(List<String> machineCode){
        
    }
}
