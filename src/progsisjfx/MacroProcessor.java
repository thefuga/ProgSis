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
    static List<String> MacroMedia = new ArrayList<String>();
    static List<String> MacroMult = new ArrayList<String>();
    static List<String> MacroOR = new ArrayList<String>();

    /**
     * Processador de macros.
     * Recebe o código que pode ou não conter macros a serem processadas. O
     * processador de macros deve verificar se existem macros e, caso existam,
     * chama o método expandMacro para a expansao.
     * @param code Código contendo (ou não) macros a serem processadas.
     * @return Lista de Strings com o código com todas as macros processadas.
     */

    public static List<String> processMacros(List<String> code){
        DefineMacros(); //ESSA FUNCAO DEVE SER CHAMADA NO MAIN

        List<String> newCode = new ArrayList<String>();
        List<String> arguments = new ArrayList<String>(); //cada .add contem um argumento


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
                     case "MULT3": //2 argumentos

                         flag = true;
                         for (int j = 0; j < 2; j++){
                             i++;
                             arguments.add(words[i]);
                         }
                         for (String str : expandMacro(arguments, 1)){
                             newCode.add(str);
                         }
                         break;
                     case "OR": //3 argumentos

                         flag = true;
                         for (int j = 0; j < 3; j++){
                             i++;
                             arguments.add(words[i]);
                         }
                         for (String str : expandMacro(arguments, 2)){
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

    /**
     * Expansão de Macros
     * Recebe o os argumentos que devem ser substituidos e o número da macro
     * a qual se referem.
     * Macro 0 = MEDIA; Macro 1 = MULT3; Macro 2 = OR
     * @param arguments que serão substituidos na expansao
     * @param macro número referente a macro que será expandida
     * @return Lista de Strings com o código referente a expansão
     */

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
                            line += arguments.get(1) + " ";
                            break;
                        case "ARG2":
                             line += arguments.get(2) + " ";
                             break;
                        case "RESULT":
                             line += arguments.get(0) + " ";
                             break;
                        default:
                            line += words[j] + " ";
                    }
                }
                expansion.add(line);
            }
        }
        else if(macro == 1){

            for(int i = 1; i < MacroMult.size() ; i++){
                String[] words = MacroMult.get(i).split(" ");
                line = "";

                for(int j = 0; j < words.length; j++){
                    switch(words[j]){
                        case "ARG1":
                            line += arguments.get(1) + " ";
                            break;
                        case "RESULT":
                             line += arguments.get(0) + " ";
                             break;
                        default:
                            line += words[j] + " ";
                    }
                }
                expansion.add(line);
            }
        }
        //or

        else if(macro == 2){

            for(int i = 1; i < MacroOR.size() ; i++){
                String[] words = MacroOR.get(i).split(" ");
                line = "";

                for(int j = 0; j < words.length; j++){
                    switch(words[j]){
                        case "ARG1":
                            line += arguments.get(1) + " ";
                            break;
                        case "ARG2":
                             line += arguments.get(2) + " ";
                             break;
                        case "RESULT":
                             line += arguments.get(0) + " ";
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

    /**
     * Definição de Macros
     * Nesse método são criadas as macros que seram usadas na expansão
     */

    private static void DefineMacros(){
        /* MEDIA MACRO &RESULT &ARG1 &ARG2
	ADD &RESULT &ARG1 &ARG2
	RSHF &RESULT &RESULT #2
	ENDM */

        MacroMedia.add("MEDIA MACRO RESULT ARG1 ARG2");
        MacroMedia.add("ADD RESULT ARG1 ARG2");
        MacroMedia.add("RSHFA RESULT RESULT 2");

        /* MULT3 MACRO &RESULT &ARG1
	ADD &RESULT &ARG1 &ARG1
	ADD &RESULT &RESULT &ARG1
	ENDM */

        MacroMult.add("MULT3 MACRO RESULT ARG1");
        MacroMult.add("ADD RESULT ARG1 ARG1");
        MacroMult.add("ADD RESULT RESULT ARG1");

        /* OR MACRO &ARG1, &RESULT
	NOT &ARG1 &ARG1
	NOT &ARG2 &ARG2
	ENDM */

        MacroOR.add("OR MACRO RESULT ARG1 ARG2");
        MacroOR.add("NOT ARG1 ARG1");
        MacroOR.add("NOT ARG2 ARG2");
        MacroOR.add("AND RESULT ARG1 ARG2");
        MacroOR.add("NOT RESULT RESULT");

    }

}
