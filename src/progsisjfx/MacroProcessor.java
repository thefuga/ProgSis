/**
 * Processador de macros.
 * Chamado pelo controle antes do montador.
 * @author Renata Junges
 * @author Karine Pestana
 */
package progsisjfx;

import java.util.ArrayList;
import java.util.List;


public class MacroProcessor {
    
    /**
     * Processador de macros.
     * Recebe o código que pode ou não conter macros a serem processadas. O 
     * processador de macros deve verificar se existem macros e, caso existam, 
     * processa-las (igual na prova).
     * @param code Código contendo (ou não) macros a serem processadas.
     * @return Lista de Strings com o código com todas as macros processadas.
     */
    static List<String> MacroMedia = new ArrayList<String>();
    static List<String> MacroMult = new ArrayList<String>();
        
    public static List<String> processMacros(List<String> code){
       //1010 e 1011 -> MACROS

        DefineMacros();
        List<String> newCode = new ArrayList<String>();
        List<String> arguments = new ArrayList<String>(); //Só palavras, sem LINHAS DE STRING. UMA STRING SÓ

        String codeLine = "";
        boolean flag = false;
        
        for (String s : code){
            flag = false;
            codeLine = "";
            //passa por cada linha de code
            String[] words = s.split(" ");
            arguments.clear();
             for (int i = 0; i < words.length ; i++) {

                 switch (words[i]){
                     case "MEDIA": //3 argumentos
                         flag = true;
                         for(int j = 0; j < 3; j++){
                             i++;
                             arguments.add(words[i]);
                         }
                         for (String str : expandMacro(arguments, 0)){
                             newCode.add(str);
                         }
                         break;
                     case "MULT3":
                         flag = true;
                         for (int j = 0; j < 2; j++){
                             i++;
                             arguments.add(words[i]);
                         }
                         for (String str : expandMacro(arguments, 1)){
                             newCode.add(str);
                         }                         
                         break;
                     default:
                         codeLine += words[i] + " ";
                }

            }
             if(!flag)  newCode.add(codeLine);            
        }
        
        return newCode;
    }
    
    //Cada macro tem um número. MEDIA = 0; MULT3 = 1
    public static List<String> expandMacro(List<String> arguments, int macro){
        
        List<String> expansion = new ArrayList<String>();
        String line = "";
        
        if(macro == 0){

            for(int i = 1; i < MacroMedia.size() ; i++){
                String[] words = MacroMedia.get(i).split(" ");
                line = "";
                
                for(int j = 0; j < words.length; j++){
                    switch(words[j]){
                        case "ARG1":
                            line += arguments.get(0) + " ";
                            break;
                        case "ARG2":
                             line += arguments.get(1) + " ";
                             break;
                        case "RESULT":
                             line += arguments.get(2) + " ";
                             break;
                        default:
                            line += words[j] + " ";
                    }
                }
                expansion.add(line);
            }
        }
        if(macro == 1){

            for(int i = 1; i < MacroMult.size() ; i++){
                String[] words = MacroMult.get(i).split(" ");
                line = "";
                
                for(int j = 0; j < words.length; j++){
                    switch(words[j]){
                        case "ARG1":
                            line += arguments.get(0) + " ";
                            break;
                        case "RESULT":
                             line += arguments.get(1) + " ";
                             break;
                        default:
                            line += words[j] + " ";
                    }
                }
                expansion.add(line);
            }
        }
                
        return expansion;
    }
    
    private static void DefineMacros(){
        /* MEDIA MACRO &ARG1, &ARG2, &RESULT
	ADD &RESULT &ARG1 &ARG2
	RSHF &RESULT &RESULT #2
	ENDM */
        MacroMedia.add("MEDIA MACRO ARG1 ARG2 RESULT");
        MacroMedia.add("ADD RESULT ARG1 ARG2");
        MacroMedia.add("RSHF RESULT RESULT");
        
        /* MULT3 MACRO &ARG1, &RESULT
	ADD &RESULT &ARG1 &ARG1
	ADD &RESULT &RESULT &ARG1
	ENDM */
        MacroMult.add("MULT3 MACRO ARG1 RESULT");
        MacroMult.add("ADD RESULT ARG1 ARG1");
        MacroMult.add("ADD RESULT RESULT ARG1");
    }

    
    
}
