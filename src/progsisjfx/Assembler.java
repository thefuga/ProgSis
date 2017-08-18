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
    
    List<String> code = new ArrayList<>();
    Map<String, String> instructions = new HashMap<>();
    Map<String, String> registers = new HashMap<>();
    List<String> pseudoinsts=new ArrayList<>();
    List<String[]> defTable= new ArrayList<>();
    List<String[]> symbolTable= new ArrayList<>();
    List<String[]> useTable= new ArrayList<>();
    
/* LER ARQUIVO GUIA PARA HUMANOS, seguindo o padrao*/
    
    /**
     * Método responsável por simular a montagem.
     * @param objCode Código objeto com todas as macros processadas.
     */
    public Assembler(List<String> objCode){
       createMaps();
       
       
       String newLine = "";

       for(String str: objCode){
           boolean flagReserved=false;
           String lastWord= "";
           //Itera linha a linha
           String[] words = str.split(" ");
           newLine = "";
           
           for(int i = 0; i < words.length; i++){
               //Itera palavra a palavra e transforma em codigo montado
               
               for (Map.Entry<String,String> pair : instructions.entrySet()) {
                   //verifica se a palavra é uma instrução, e caso seja, substitui 
                   if (words[i].equals(pair.getKey())){
                       
                       //ADD E AND
                       if(words[i].equals("ADD") || words[i].equals("AND") ){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            boolean reg = false;
                            i++;
                            //primeiro registrador (DESTINO)
                             for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                  //verifica se a palavra é um registrador, e caso seja, substitui 
                                  if (words[i].equals(pairR.getKey())){
                                      code.add(pairR.getValue() + " A");
                                  }
                              }
                             i++;
                             //segundo registrador (primeiro registrador)
                                 for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                   //verifica se a palavra é um registrador, e caso seja, substitui 
                                   if (words[i].equals(pairR.getKey())){
                                       code.add(pairR.getValue() + " A");
                                   }
                               }
                                 i++;
                               //terceiro registrador ou nao (caso seja imediato)
                              for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                   //verifica se a palavra é um registrador, e caso seja, substitui 
                                   if (words[i].equals(pairR.getKey())){
                                       reg = true;
                                       code.add("1 A");
                                       code.add(pairR.getValue() + " A");
                                   }
                               }

                              if(!reg){
                                  //endereço imediato
                                  code.add("0 A");
                                  code.add(bin(Integer.parseInt(words[i])) + " A");

                              }
                        }
                       
                       //NOT
                       if(words[i].equals("NOT")){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            boolean reg = false;
                            i++;
                            //primeiro registrador (DESTINO)
                             for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                  //verifica se a palavra é um registrador, e caso seja, substitui 
                                  if (words[i].equals(pairR.getKey())){
                                      code.add(pairR.getValue() + " A");
                                  }
                              }
                             i++;
                             //segundo registrador (primeiro registrador)
                                 for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                   //verifica se a palavra é um registrador, e caso seja, substitui 
                                   if (words[i].equals(pairR.getKey())){
                                       code.add(pairR.getValue() + " A");
                                   }
                               }
                                 code.add("111111 A");
                        }
                       
                       //LSHF
                        if(words[i].equals("LSHF")){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            boolean reg = false;
                            i++;
                            //primeiro registrador (DESTINO)
                             for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                  //verifica se a palavra é um registrador, e caso seja, substitui 
                                  if (words[i].equals(pairR.getKey())){
                                      code.add(pairR.getValue() + " A");
                                  }
                              }
                             i++;
                             //segundo registrador (primeiro registrador)
                                 for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                   //verifica se a palavra é um registrador, e caso seja, substitui 
                                   if (words[i].equals(pairR.getKey())){
                                       code.add(pairR.getValue() + " A");
                                   }
                               }
                                 code.add("0 A");
                                 code.add("0 A");
                                 i++;
                                code.add(bin(Integer.parseInt(words[i])) + " A");
                        }
                        
                        //RSHFA
                        if(words[i].equals("RSHFA")){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            boolean reg = false;
                            i++;
                            //primeiro registrador (DESTINO)
                             for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                  //verifica se a palavra é um registrador, e caso seja, substitui 
                                  if (words[i].equals(pairR.getKey())){
                                      code.add(pairR.getValue() + " A");
                                  }
                              }
                             i++;
                             //segundo registrador (primeiro registrador)
                                 for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                   //verifica se a palavra é um registrador, e caso seja, substitui 
                                   if (words[i].equals(pairR.getKey())){
                                       code.add(pairR.getValue() + " A");
                                   }
                               }
                                 code.add("1 A");
                                 code.add("1 A");
                                 i++;
                                 code.add(bin(Integer.parseInt(words[i])) + " A");
                        }
                        
                        //RSHFL
                        if(words[i].equals("RSHFL")){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            boolean reg = false;
                            i++;
                            //primeiro registrador (DESTINO)
                             for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                  //verifica se a palavra é um registrador, e caso seja, substitui 
                                  if (words[i].equals(pairR.getKey())){
                                      code.add(pairR.getValue() + " A");
                                  }
                              }
                             i++;
                             //segundo registrador (primeiro registrador)
                                 for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                   //verifica se a palavra é um registrador, e caso seja, substitui 
                                   if (words[i].equals(pairR.getKey())){
                                       code.add(pairR.getValue() + " A");
                                   }
                               }
                                 code.add("0 A");
                                 code.add("1 A");
                                 i++;
                                code.add(bin(Integer.parseInt(words[i])) + " A");
                        }
                      
                        //BR
                         if(words[i].equals("BR")){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            boolean reg = false;
                            i++;
                            code.add("0 A");
                            code.add("0 A");
                            code.add("0 A");
                            code.add(bin(Integer.parseInt(words[i])) + " A");
                        }
                         
                        //LEA
                        if(words[i].equals("LEA")){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            boolean reg = false;
                            i++;
                            //primeiro registrador (DESTINO)
                             for (Map.Entry<String,String> pairR : registers.entrySet()) {
                                  //verifica se a palavra é um registrador, e caso seja, substitui 
                                  if (words[i].equals(pairR.getKey())){
                                      code.add(pairR.getValue() + " A");
                                  }
                              }
                             i++;
                             code.add(bin(Integer.parseInt(words[i])) + " A");
                        }  
                        
                        //LDB e LDI
                        if(words[i].equals("LDB") || words[i].equals("LDI") ||
                                words[i].equals("STB") || words[i].equals("STI")){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            i++;
                            for (Map.Entry<String,String> pairR : registers.entrySet()){
                                //verifica se a palavra é um registrador, e caso seja, substitui 
                                if (words[i].equals(pairR.getKey())){
                                    code.add(pairR.getValue() + " A");
                                }
                            }
                            i++;
                            for (Map.Entry<String,String> pairR : registers.entrySet()){
                                //verifica se a palavra é um registrador, e caso seja, substitui 
                                if (words[i].equals(pairR.getKey())){
                                    code.add(pairR.getValue() + " A");
                                }
                            }
                            i++;
                            code.add(bin(Integer.parseInt(words[i])) + " A");                            
                        }
                        
                        //JSR, JSRR, JMP e RET
                        if(words[i].equals("JSR") || words[i].equals("JMP") || words[i].equals("RET") || words[i].equals("JSRR") ){
                            code.add(pair.getValue() + " A");
                            flagReserved=true;
                            i++;
                            code.add("0 A");
                            code.add("0 A");
                            code.add("0 A");
                            for (Map.Entry<String,String> pairR : registers.entrySet()){
                                //verifica se a palavra é um registrador, e caso seja, substitui 
                                if (words[i].equals(pairR.getKey())){
                                    code.add(pairR.getValue() + " A");
                                }
                            }
                            i++;
                            code.add("0 A");
                            code.add("0 A");
                            code.add("0 A");
                            code.add("0 A");
                            code.add("0 A");
                            code.add("0 A");                                                        
                        }                                                     
                   }
                   else if(words[i].equals("EXTDEF")){
                    i++;
                    int j=0;
                    String[] tempArray= new String[3];
                    tempArray[0]= words[i];
                    tempArray[1]= String.valueOf(i);
                    tempArray[2]= "R";
                    
                    
                    defTable.add(tempArray);
                    }
                
                    else if(words[i+1].equals("CONST")){
                        String[] tempArray= new String[3];
                        tempArray[0]=words[i];
                        tempArray[1]=String.valueOf(i);
                        tempArray[2]= "R";
                        
                        symbolTable.add(tempArray);
                    }
                    
                    else if(words[i+1].equals("EXTR")){
                        String[] tempArray= new String[3];
                        tempArray[0]= words[i];
                        tempArray[1]= String.valueOf(i);
                        tempArray[2]= "+";
                        
                        useTable.add(tempArray);
                    }
                }
                
                
               
              /*
               
                if (!pseudoinsts.contains(words[i]) && !flagReserved){
                    //Nunca vai entrar nesse loop
                    if(words[i].equals("EXTDEF")){
                        symbolTable.add(lastWord);
                    }
                }else{
                    lastWord=words[i];
                }
                    */
               
               //if (flag==1){
                   
               //}
 
           }
         //  code.add(newLine);
        }
       // DEBUG
        for(String k: code ){
           System.out.println(k);
        }
       
    }
    
    private String bin(int numero) {
	int d = numero;
	StringBuffer binario = new StringBuffer(); // guarda os dados
	while (d > 0) {
		int b = d % 2;
		binario.append(b);
		d = d >> 1; // é a divisão que você deseja
	}
                
        String str = binario.reverse().toString();
        return str;
    }
    
    public List<String> getCode(){
        return code;
    }
    
    public List<String[]> getUsesTable(){
        return useTable;
    }
    
    public List<String[]> getDefinitionsTable(){
        return defTable;
    }
    
    public List<String[]> getSymbolTable(){
        return symbolTable;
    }
    
    /**
     * Método que cria um "dict" com as instruções e seu respectivo 
     * valor em binário e cria um "dict" com os registradores e seu respectivo
     * valor em binário.
     * Esse método deve ser chamado uma vez no início do programa
     */
    private void createMaps(){
    //Instruções com seus respectivos códigos
        instructions.put("BR", "0000");  
        instructions.put("ADD", "0001");
        instructions.put("LBD", "0010");
        instructions.put("STB", "0011");
        instructions.put("JSR(R)", "0100");
        instructions.put("AND", "0101");
        instructions.put("LDW", "0110");
        instructions.put("STW", "0111");
        instructions.put("RTI", "1000");
        instructions.put("NOT", "1001");
        instructions.put("LDI", "1010");
        instructions.put("STI", "1011");   
        instructions.put("JMP", "1100");
        instructions.put("LSHF", "1101");
        instructions.put("RSHFL", "1101");
        instructions.put("RSHFA", "1101");
        instructions.put("LEA", "1110");
        instructions.put("TRAP", "1111");
        
        pseudoinsts.add("CONST");
        pseudoinsts.add("MCDEF");
        pseudoinsts.add("MCEND");
        pseudoinsts.add("EXTR");
        pseudoinsts.add("EXTDEF");
        
        
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
