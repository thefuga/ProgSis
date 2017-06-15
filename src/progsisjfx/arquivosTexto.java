/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsisjfx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karine
 */
public class arquivosTexto {
    
    public arquivosTexto(){}
    
    //gente usei essa função minha que estava pronta da minha cache de AOC2, mas se precisar podem alterar
    //karine aqui
    public static ArrayList <String> LeArquivoTexto(String nomeArq){
                ArrayList <String> leitura =  new ArrayList<>();
               BufferedReader buffRead;
                try{  
                   buffRead = new BufferedReader(new FileReader(nomeArq));
                    String linha = "";
                    
                    while((linha = buffRead.readLine()) != null){
                        //adiciona no array
                        leitura.add(linha);                    
                    }                   
                    buffRead.close();
                }
                catch(IOException ex){
                    System.out.println("Não foi possível ler arquivo \n");
                    Logger.getLogger(arquivosTexto.class.getName()).log(Level.SEVERE, null, ex);
                }                
                return leitura;
    }
}
