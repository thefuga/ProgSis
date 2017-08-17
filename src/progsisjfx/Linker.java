/**
 * Ligador.
 * Chamado pelo controle após a montagem do programa.
 * @author Arthur "Rolha" Piccoli
 */
package progsisjfx;

import java.util.List;

public class Linker {
    private int moduleASize, moduleBSize, definitionsBSize, usesASize, usesBSize, GSTSize, iAux;
    private List<String> code, moduleBCode;
    private List<String[]> GST, moduleBDef, moduleAUses, moduleBUses;
    private String[] linha, linhaAux, linhaAux2;
    private String aux;
    
    /**
     * Ligador.
     * Recebe os códigos a serem ligados e retorna o código final para ser 
     * carregado.
     * @param moduleA
     * @param moduleB
     */
    public Linker(Assembler moduleA, Assembler moduleB){
        moduleASize=moduleA.getCode().size();
        moduleBSize=moduleB.getCode().size();
        
        definitionsBSize=moduleB.getDefinitionsTable().size();
        code = moduleA.getCode();
        GST = moduleA.getDefinitionsTable();
        moduleBDef = moduleB.getDefinitionsTable();
        moduleBCode = moduleB.getCode();
        moduleAUses = moduleA.getUsesTable();
        moduleBUses = moduleB.getUsesTable();
        usesASize = moduleA.getUsesTable().size();
        usesBSize = moduleB.getUsesTable().size();
        
        //pega o 2º modulo e coloca no código linkado
        for(int i=0; i<moduleBSize; i++){
            linha = moduleBCode.get(i).split(" ");
            if("R".equals(linha[1])){
                //pega o binario converte pra decimal, soma com o tamanho do modulo A e converte pra binario de volta
                code.add(Integer.toBinaryString(Integer.parseInt(linha[0], 2) + moduleASize) + " " + linha[1]);
            }
            else
                code.add(linha[0] + " " + linha[1]);
        }
        
        //pega a 2ª tabela e bota na tsg
        for(int i=0; i<definitionsBSize; i++){           
            linha = moduleBDef.get(i);
            if("R".equals(linha[2])){
                code.add(linha[0] + " " + (Integer.parseInt(linha[1]) + moduleASize) + " " + linha[2]);
            }
            else
                code.add(linha[0] + " " + linha[1] + " " + linha[2]);
            
        }
        
        
        for(int i=0; i<usesASize; i++){ 
        linha = moduleAUses.get(i);
        linhaAux = code.get(Integer.parseInt(linha[1])).split(" ");
        for(int j=0; j<GST.size(); j++){
            if((GST.get(j))[0].equals(linha[0]))
                iAux=j;
        }
        linhaAux2 = GST.get(iAux);
        if(linha[2].equals("+"))
        code.set(Integer.parseInt(linha[1]), (Integer.toBinaryString(Integer.parseInt(linhaAux[0], 2) + Integer.parseInt(linhaAux2[1]))) + " " + linhaAux2[2]);
        else
         code.set(Integer.parseInt(linha[1]), Integer.toBinaryString(Integer.parseInt(linhaAux2[1]) - (Integer.parseInt(linhaAux[0], 2))) + " " + linhaAux2[2]);
           
        //tenho q pegar o valor do label na tsg e somar com oq ta no codigo já
                
        }
        
        for(int i=0; i<usesBSize; i++){ 
        linha = moduleBUses.get(i);
        linhaAux = code.get(Integer.parseInt(linha[1]) + moduleASize).split(" ");
        for(int j=0; j<GST.size(); j++){
            if((GST.get(j))[0].equals(linha[0]))
                iAux=j;
        }
        linhaAux2 = GST.get(iAux);
        if(linha[2].equals("+"))
        code.set(Integer.parseInt(linha[1]), (Integer.toBinaryString(Integer.parseInt(linhaAux[0], 2) + Integer.parseInt(linhaAux2[1]))) + " " + linhaAux2[2]);
        else
         code.set(Integer.parseInt(linha[1]), Integer.toBinaryString(Integer.parseInt(linhaAux2[1]) - (Integer.parseInt(linhaAux[0], 2))) + " " + linhaAux2[2]);
           
        //tenho q pegar o valor do label na tsg e somar com oq ta no codigo já
                
        }
        /* ideia
        GST.get(0);
        GST.set(0, " ");
        */
    }
    
    public List<String> getLinkedCode(){
        return code;
    }
    
}
