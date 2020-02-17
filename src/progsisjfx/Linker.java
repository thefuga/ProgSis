/**
 * Ligador.
 * Chamado pelo controle após a montagem do programa.
 * @author Arthur "Rolha" Piccoli
 */

package progsisjfx;

import java.util.List;

public class Linker {
    private int moduleASize, moduleBSize, definitionsBSize, usesASize, usesBSize, GSTIterator;
    private List<String> code, moduleBCode;
    private List<String[]> GST, moduleBDef, moduleAUses, moduleBUses;
    private String[] codeLine, usesLine, GSTLine;

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
            codeLine = moduleBCode.get(i).split(" ");
            if("R".equals(codeLine[1])){
                //pega o binario converte pra decimal, soma com o tamanho do modulo A e converte pra binario de volta

                code.add(Integer.toBinaryString(Integer.parseInt(codeLine[0], 2) + moduleASize + 0b1000000000000000).substring(16-codeLine[0].length()) + " " + codeLine[1]);
            }
            else
                code.add(codeLine[0] + " " + codeLine[1]);
        }

        //pega a 2ª tabela e bota na tsg

        for(int i=0; i<definitionsBSize; i++){
            codeLine = moduleBDef.get(i);
            if("R".equals(codeLine[2]))
                codeLine[1] = Integer.toString(Integer.parseInt(codeLine[1]) + moduleASize);


            GST.add(codeLine);

        }

        //na tabela de usos do modulo a

        for(int i=0; i<usesASize; i++){
            //em cada linha da tabela de usos, pega a linha da ocorrencia no código

            usesLine = moduleAUses.get(i);
            codeLine = code.get(Integer.parseInt(codeLine[1])).split(" ");

            for(int j=0; j<GST.size(); j++){
                if((GST.get(j))[0].equals(usesLine[0]))
                    GSTIterator=j;
            }
            GSTLine = GST.get(GSTIterator);
            if(usesLine[2].equals("+"))
                code.set(Integer.parseInt(usesLine[1]), Integer.toBinaryString(Integer.parseInt(GSTLine[1]) + (Integer.parseInt(codeLine[0], 2)) + 0b1000000000000000).substring(16-codeLine[0].length()) + " " + GSTLine[2]);
            else
                code.set(Integer.parseInt(usesLine[1]), Integer.toBinaryString(Integer.parseInt(GSTLine[1]) - (Integer.parseInt(codeLine[0], 2)) + 0b1000000000000000).substring(16-codeLine[0].length()) + " " + GSTLine[2]);
        }

        //mesma coisa só que pro 2º módulo

        for(int i=0; i<usesBSize; i++){
            codeLine = moduleBUses.get(i);
            usesLine = code.get(Integer.parseInt(codeLine[1]) + moduleASize).split(" ");
            for(int j=0; j<GST.size(); j++){
                if((GST.get(j))[0].equals(codeLine[0]))
                    GSTIterator=j;
            }
            GSTLine = GST.get(GSTIterator);
            if(usesLine[2].equals("+"))
                code.set(Integer.parseInt(usesLine[1]), Integer.toBinaryString(Integer.parseInt(GSTLine[1]) + (Integer.parseInt(codeLine[0], 2)) + 0b1000000000000000).substring(16-codeLine[0].length()) + " " + GSTLine[2]);
            else
                code.set(Integer.parseInt(usesLine[1]), Integer.toBinaryString(Integer.parseInt(GSTLine[1]) - (Integer.parseInt(codeLine[0], 2)) + 0b1000000000000000).substring(16-codeLine[0].length()) + " " + GSTLine[2]);
        }


    }

    public List<String> getLinkedCode(){
        return code;
    }

}
