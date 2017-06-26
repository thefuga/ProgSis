/**
 * TODO
 *
 * @author erickfuga
 */
package progsisjfx.view;

import javafx.fxml.FXML;
import progsisjfx.ProgSisJFX;


public class RootLayoutController {
    
    ProgSisJFX mainApp;
    
    /**
     * Inicializa o controlador.
     */
    public void initialize() {
    }
    
    /**
     * Set Main App.
     * Passa a referência do programa para o controlador.
     * @param mainApp referência do programa principal.
     */
    public void setMainApp(ProgSisJFX mainApp){
        this.mainApp = mainApp;
    }
    
    @FXML
    private void instructionsTableHelp(){
        mainApp.showHelpWindow();
    }

}
