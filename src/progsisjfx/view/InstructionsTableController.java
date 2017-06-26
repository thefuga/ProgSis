/**
 * Controlador da janela com a tabela de instruções.
 * @author Erick Costa Fuga
 */
package progsisjfx.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class InstructionsTableController {

    @FXML
    private ImageView instructionImage;

    /**
     * Inicializa o controlador.
     */
    public void initialize() {
        instructionImage.setImage(new Image("file:src/progsisjfx/view/Instructions.jpg"));
    }
}
