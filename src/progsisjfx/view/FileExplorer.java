/**
 * Explorador de Arquivos
 * Classe responsável por exibir um explorador de arquivos para salvar e abrir
 * arquivos e retornar seus endereços
 * @author Erick Costa Fuga.
 */

package progsisjfx.view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Erick
 */

public abstract class FileExplorer {


    /**
     * Método abre um FileChooser e retorna um endereço para abrir um arquivo.
     * @param primaryStage onde o FileChooser será aberto.
     * @param extensao do arquivo que será aberto.
     * @return endereço do arquivo a ser aberto.
     */

    public static String abrirArquivo(Stage primaryStage, String extensao){
        try{
            return getEndereco(primaryStage, extensao).showOpenDialog(primaryStage).getAbsolutePath();
        }catch(RuntimeException e){
            return null;
        }
    }


    /**
     * Método abre um FileChooser e retorna um endereço para salvar um arquivo.
     * @param primaryStage onde o FileChooser será aberto.
     * @param extensao do arquivo que será salvo.
     * @return endereço do arquivo a ser salvo.
     */

    public static String salvarArquivo(Stage primaryStage, String extensao){
        try{
            return getEndereco(primaryStage, extensao).showSaveDialog(primaryStage).getAbsolutePath();
        }catch(RuntimeException e){
            return null;
        }
    }

    /**
     * Método que abre o FileChooser na interface gráfica.
     * @param primaryStage onde o FileChooser será aberto.
     * @param extensao do arquivo a ser salvo ou aberto.
     * @return fileChooser cujo endereço será retornado posteriormente.
     */

    private static FileChooser getEndereco(Stage primaryStage, String extensao){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter ("Arquivos" + " " + extensao, "*" + extensao);
        fileChooser.getExtensionFilters().add(filtro);

        return fileChooser;
    }
}
