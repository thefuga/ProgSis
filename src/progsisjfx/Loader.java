/**
 * Loader.
 * Recebe o programa pronto para execução e carrega-o na memória.
 * @author Ítalo Nolasco.
 */
package progsisjfx;

import java.util.List;
import javafx.collections.ObservableList;


public abstract class Loader {
    private int codeLenght, counter, counterAux, flag, adressLenght, adressNumberOfZeros;
    private String[] line, part1, part2, part3, part4, part5, adress;
    
    /**
     * Carregador.
     * Recebe o código pronto e carraga na memória para execução.
     * @param code Código de máquina para ser carregado na memória da 
     * máquina virtual.
     */
    public void Loader(Linker code, List<String> instructionMemory){
        codeLenght = code.getLinkedCode().size();
        counter = 0;
        
        while(counter < codeLenght){
            line = code.getLinkedCode().get(counter).split(" ");
            part1 = code.getLinkedCode().get(counter+1).split(" ");
            part2 = code.getLinkedCode().get(counter+2).split(" ");
            part3 = code.getLinkedCode().get(counter+3).split(" ");
            part4 = code.getLinkedCode().get(counter+4).split(" ");
            part5 = code.getLinkedCode().get(counter+5).split(" ");

            //Instruções com 4 ou 5 segmentos(ADD e AND)
            if(line[0].equals(0001) || line[0].equals(0101)){
                if(part3[0].equals(0)){  
                    instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + part4[0] + part5[0]);
                    counter += 6;   
                }
                else{
                    adressLenght = part4[0].length();
                    adressNumberOfZeros = 5-adressLenght;
                    switch(adressNumberOfZeros){
                        case 0:
                           instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + part4[0]);
                           break;
                        case 1:
                            instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + "0" + part4[0]);
                            break;
                        case 2:
                            instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + "00" + part4[0]);
                            break;
                        case 3:
                            instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + "000" + part4[0]);
                            break;
                        case 4:
                            instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + "0000" + part4[0]);
                            break;   
                    }
                    counter += 5;
                }
            }
            //Instrução BR
            else if(line[0].equals(0000)){
                instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + part4[0]);
                counter += 5;
            }
            //Instrução JMP, JSRR, JSR E RET FALTANDO PQ N ENTENDI SE O KRISTOFER USOU O MESMO FORMATO PRA ELAS
            else if(line[0].equals(1100)){
                
            }
            //Instruções LDB, LDI, LDR, STB, STI, STR
            else if(line[0].equals(0010) || line[0].equals(1010) || line[0].equals(0110) || line[0].equals(0011) || line[0].equals(1011) || line[0].equals(0111)){
                instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0]);
                counter += 4;
            }
            //Instrução LEA
            else if(line[0].equals(1110)){
                instructionMemory.add(line[0] + " " + part1[0] + part2[0]);
                counter += 3;
            }
            //Instrução NOT
            else if(line[0].equals(1001)){
                instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0]);
                counter += 4;
            }
            //Instrução SHF
            else if(line[0].equals(1101)){
                adressLenght = part5[0].length();
                adressNumberOfZeros = 4-adressLenght;
                switch(adressNumberOfZeros){
                    case 0:
                        instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + part4[0] + part5[0]);
                        break;
                    case 1:
                         instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + part4[0] + "0" + part5[0]);
                         break;
                    case 2:
                         instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + part4[0] + "00" + part5[0]);
                         break;
                    case 3:
                         instructionMemory.add(line[0] + " " + part1[0] + part2[0] + part3[0] + part4[0] + "000" + part5[0]);
                         break;
                }    
                counter += 6;
            }
            //Instrução TRAP
            else if(line[0].equals(1111)){
                 instructionMemory.add(line[0] + " " + part1[0] + part2[0]);
                 counter += 3;
            }
        }
    }
}
