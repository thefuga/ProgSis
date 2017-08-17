/**
 * Montador.
 * Chamado pelo controle após o processamento das macros.
 * @author Kristofer "Cepo" Kappel
 */
package progsisjfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assembler {
    
    List<String> code = new ArrayList<String>();
    Map<String, String> instructions = new HashMap<String, String>();
    Map<String, String> registers = new HashMap<String, String>();
    
/* LER ARQUIVO GUIA PARA HUMANOS, seguindo o padrao*/
    
    /**
     * Método responsável por simular a montagem.
     * @param objCode Código objeto com todas as macros processadas.
     */
    public Assembler(List<String> objCode){
       createMaps();
       
       String newLine = "";
       
       for(String str: objCode){
           //Itera linha a linha
           String[] words = str.split(" ");
           newLine = "";
           
           for(int i = 0; i < words.length; i++){
               //Itera palavra a palavra e transforma em codigo montado
               
               for (Map.Entry<String,String> pair : instructions.entrySet()) {
                   //verifica se a palavra é uma instrução, e caso seja, substitui 
                   if (words[i].equals(pair.getKey())){
                       newLine += pair.getValue() + " ";
                   }
                }
               
               for (Map.Entry<String,String> pair : registers.entrySet()) {
                   //verifica se a palavra é um registrador, e caso seja, substitui 
                   if (words[i].equals(pair.getKey())){
                       newLine += pair.getValue();
                   }
                }
 
           }
           code.add(newLine);
        }
       /* DEBUG
        for(String k: code ){
           System.out.println(k);
        }
       */
    }
    
    public List<String> getCode(){
        return code;
    }
    
    public List<String> getUsesTable(){
        return null;
    }
    
    public List<String> getDefinitionsTable(){
        return null;
    }
    /**
     * Método que cria um "dict" com as instruções e seu respectivo 
     * valor em binário e cria um "dict" com os registradores e seu respectivo
     * valor em binário.
     * Esse método deve ser chamado uma vez no início do programa
     */
    public void createMaps(){
    //Instruções com seus respectivos códigos
        instructions.put("ADD", "0001");
        instructions.put("AND", "0101");
        instructions.put("BR", "0000");     
        instructions.put("JMP", "1100");
        instructions.put("JSR(R)", "0100");
        instructions.put("LBD", "0010");     
        instructions.put("LDW", "0110");
        instructions.put("LEA", "1110");
        instructions.put("RTI", "1000");
        instructions.put("SHF", "1101");
        instructions.put("STB", "0011");
        instructions.put("STW", "0111");
        instructions.put("TRAP", "1111");
        instructions.put("XOR", "1001");
        
    //Registradores com seus respectivos codigos
        registers.put("R0", "000");
        registers.put("R1", "001");
        registers.put("R2", "010");
        registers.put("R3", "011");
        registers.put("R4", "100");
        registers.put("R5", "101");
        registers.put("R6", "110");
        registers.put("R7", "111");
        
    }
}
