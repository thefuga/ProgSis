/**
 * Ligador.
 * Chamado pelo controle após a montagem do programa.
 * @author Arthur "Rolha" Piccoli
 */
package progsisjfx;

import java.util.List;

public class Linker {
    private int moduleALenght, moduleBLenght, defBLenght;
    private List<String> GST, code, moduleBCode, moduleBDef;
    private String[] linha;
    
               private int aux;
    /**
     * Ligador.
     * Recebe os códigos a serem ligados e retorna o código final para ser 
     * carregado.
     * @param moduleA
     * @param moduleB
     */
    public Linker(Assembler moduleA, Assembler moduleB){
        moduleALenght=moduleA.getCode().size();
        moduleBLenght=moduleB.getCode().size();
        defBLenght=moduleB.getDefinitionsTable().size();
        code = moduleA.getCode();
        GST = moduleA.getDefinitionsTable();
        moduleBDef = moduleB.getDefinitionsTable();
        moduleBCode = moduleB.getCode();
        for(int i=0; i<moduleBLenght; i++){
            linha = moduleBCode.remove(i).split(" ");
            if("R".equals(linha[1])){
                aux = Integer.parseInt(linha[0]) + moduleALenght;
                code.add(aux + " " + linha[1]);
            }
            else
                code.add(linha[0] + " " + linha[1]);
        }
        
        for(int i=0;i<defBLenght;i++){
            
            linha = moduleBDef.remove(i).split(" ");
            if("R".equals(linha[2])){
                aux = Integer.parseInt(linha[1]) + moduleALenght;
                code.add(linha[0] + " " + aux + " " + linha[2]);
            }
            else
                code.add(linha[0] + " " + linha[1] + " " + linha[2]);
            
        }
        /* ideia
        GST.get(0);
        GST.set(0, " ");
        */
    }
    
    public List<String> getGlobalSymbolsTable(){
        return GST;
    }
    
    public List<String> getLinkedCode(){
        return code;
    }
    
}
