/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author italo
 */

package progsisjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Memory {  
    ObservableList<String> dataMemory;
    ObservableList<String> instructionMemory;
    
    /**
     * Méotodo construtor.
     * Inicializa memórai de dados e instruções.
     */
    public Memory(){
        dataMemory = FXCollections.observableArrayList();
        instructionMemory = FXCollections.observableArrayList();
        
        for(int i = 0; i < 65536; i++){ //2^16
            dataMemory.add("0");            
        }
    }
    
    /**
     * 
     * @param index
     * @return Elemento da memória referente ao índice paassado por parâmetro.
     */
    public String readDataMemory(int index){
        return dataMemory.get(index);
    }
   
    /**
     * 
     * @param index
     * @return Elemento da memória referente ao índice paassado por parâmetro.
     */
    public String readInstructionMemory(int index){
        return instructionMemory.get(index);
    }
    /**
     * Escreve o elemento na posição index na memória de dados.
     * @param index
     * @param element 
     */
    public void writeDataMemory(int index, String element){
        dataMemory.add(index, element);
    }
    
    /**
     * Escreve o elemento na posição index na memória de instruçõess
     * @param index
     * @param element 
     */
    public void writeInstructionMemory(int index, String element){
        instructionMemory.add(index, element);
    }
    
    /**
     * 
     * @return a lista da memória de dados 
     */
    public ObservableList getDataMemory(){
        return dataMemory;
    }
    
    /**
     * 
     * @return a lista de memória de instruções 
     */
    public ObservableList getInstructionMemory(){
        return instructionMemory;
    }
     
    
}
