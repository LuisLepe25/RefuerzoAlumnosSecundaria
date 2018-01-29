/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import controllers.AnimacionController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author luisd
 */
public class Sesion {
    private Stage stage;
    
    public Sesion(Stage stage){
        this.stage = stage;
    }
    
    public void cargarVistaAnimacion(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Animacion.fxml"));     
            
            Parent root = (Parent)fxmlLoader.load();          
            AnimacionController controller = fxmlLoader.<AnimacionController>getController();
            controller.initialize(this);
            
            Scene scene = new Scene(root);
            getStage().setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
