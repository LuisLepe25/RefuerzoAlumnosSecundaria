/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utilities.DAO;
import Utilities.Sesion;
import Utilities.SpriteAnimation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author luisd
 */
public class AnimacionController {
    private Sesion sesion;
    private Stage stage;
    private Image image;
    private Animation animation;
    private Animation animationVillano;
    
    @FXML private AnchorPane apContenedor;
    @FXML private ImageView ivAnimacion;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;
    @FXML private ImageView ivVillano;
    @FXML private ImageView ivPrueba;
    @FXML private Button btn5;
    @FXML private Button btn6;
    @FXML private Button btn7;
    @FXML private Button btn8;
    
    /**
     * Initializes the controller class.
     * @param sesion
     */
    public void initialize(Sesion sesion) {
        this.sesion = sesion;
        this.stage = sesion.getStage();
//        image = new Image("/resources/sprites/batman/Idle.png");
//        ivAnimacion.setImage(image);
//        ivAnimacion.setViewport(new Rectangle2D(0, 0, 76, 96));
//
//        final Animation animation = new SpriteAnimation(ivAnimacion,Duration.millis(500), 3, 3, 0, 0, 76, 96);
//        animation.setCycleCount(Animation.INDEFINITE);
//        animation.play();
        //btn1.fire();
        configurarTamanos();
        
        try {
            DAO conn = new DAO();
            conn.conectar();
            conn.selectPrueba();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AnimacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void configurarTamanos(){
        ivAnimacion.setTranslateX(100);
        ivAnimacion.setTranslateY(0);
        System.out.println("Con X: "+ivAnimacion.getTranslateX()+ " Y: " + ivAnimacion.getTranslateX());
        ivAnimacion.fitHeightProperty().bind(apContenedor.heightProperty().divide(3));
        ivAnimacion.fitWidthProperty().bind(apContenedor.maxWidthProperty().divide(3));
        ivVillano.fitHeightProperty().bind(apContenedor.heightProperty().divide(3));
        ivVillano.fitWidthProperty().bind(apContenedor.maxWidthProperty().divide(3));
    }
    
    @FXML
    private void btnAccion1(ActionEvent event) {
        if(animation != null){
            animation.stop();
        }
        image = new Image("/resources/sprites/batman/Idle.png");
        ivAnimacion.setImage(image);
        ivAnimacion.setViewport(new Rectangle2D(0, 0, 76, 96));

        animation = new SpriteAnimation(ivAnimacion,Duration.millis(500), 3, 3, 0, 0, 76, 96);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    @FXML
    private void btnAccion2(ActionEvent event) {
        String strIdleFight = "/resources/sprites/batman/IdleFight.png";
        configuracionSuperHeroe(strIdleFight, 76, 96, 3, 3, Animation.INDEFINITE);
    }

    @FXML
    private void btnAccion3(ActionEvent event) {
        if(animation != null){
            animation.stop();
        }
        
        image = new Image("/resources/sprites/batman/Kick.png");
        ivAnimacion.setImage(image);
        ivAnimacion.setViewport(new Rectangle2D(0, 0, 89, 96));

        animation = new SpriteAnimation(ivAnimacion,Duration.millis(500), 6, 6, 0, 0, 89, 96);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    @FXML
    private void btnAccion4(ActionEvent event) {
        ivAnimacion.toFront();
        setAnimationDefault();
        String pathSprites = "/resources/sprites/batman/Walk.png";
        configuracionSuperHeroe(pathSprites, 87, 96, 5, 5, Animation.INDEFINITE);
        String pathVillano = "/resources/sprites/batman/Idle.png";
        configuracionSuperVillano(pathVillano, 76, 96, 500, 3, 3, Animation.INDEFINITE);
        moverSuperHeroe(3000);
    }
    
    
    @FXML
    private void btnAccion5(ActionEvent event) {
        configurarTamanos();
    }

    @FXML
    private void btnAccion6(ActionEvent event) {
        String pathSprites = "/resources/sprites/batman/Hurt.png";
        configuracionSuperVillano(pathSprites, 76, 96, 500, 3, 3, Animation.INDEFINITE);
        
    }

    @FXML
    private void btnAccion7(ActionEvent event) {
    }

    @FXML
    private void btnAccion8(ActionEvent event) {
    }
    
    private void configuracionSuperHeroe(String strImagen, int spriteSizeX, int spriteSizeY, int count, int columns ,int intCycle){
        if(animation != null){
            animation.stop();
        }
        image = new Image(strImagen);
        ivAnimacion.setImage(image);
        ivAnimacion.setViewport(new Rectangle2D(0, 0, spriteSizeX, spriteSizeY));

        animation = new SpriteAnimation(ivAnimacion,Duration.millis(500), count, columns, 0, 0, spriteSizeX, spriteSizeY);
        animation.setCycleCount(intCycle);
        animation.setAutoReverse(true);
        animation.play();
    }
    
    private void moverSuperHeroe(int milisegundos){    
        Path path = new Path();
        System.out.println("X: "+ivAnimacion.getLayoutX()+ " Y: " + ivAnimacion.getLayoutY());
        path.getElements().add (new MoveTo (ivAnimacion.getLayoutX() + 120, 120));
        path.getElements().add (new LineTo (ivAnimacion.getLayoutX() + 650, 120));
        
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(milisegundos));
        pathTransition.setNode(ivAnimacion);
        pathTransition.setPath(path);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(true);
 
        pathTransition.play();
        
        pathTransition.setOnFinished((ActionEvent event1) -> {
            System.out.println("Heroe termino recorrido");
            String strPatear = "/resources/sprites/batman/Kick.png";
            configuracionSuperHeroe(strPatear, 89, 96, 6, 6, 1);
            String pathVillanoHurt = "/resources/sprites/batman/Hurt.png";
            configuracionSuperVillano(pathVillanoHurt, 76, 96, 2000, 3, 3, 1);
        });
    }
    
    private void moverInversoSuperHeroe(int milisegundos){    
        Path path = new Path();
        System.out.println("X: "+ivAnimacion.getLayoutX()+ " Y: " + ivAnimacion.getLayoutY());
//        path.getElements().add (new MoveTo (ivAnimacion.getLayoutX() + 650, 120));
//        path.getElements().add (new LineTo (ivAnimacion.getLayoutX() + 120, 120));
        path.getElements().add (new MoveTo (ivAnimacion.getLayoutX() + 650, 120));
        path.getElements().add (new LineTo (ivAnimacion.getLayoutX() + 120, 120));
        
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(milisegundos));
        pathTransition.setNode(ivAnimacion);
        pathTransition.setPath(path);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(true);
 
        pathTransition.play();
        
        pathTransition.setOnFinished((ActionEvent event1) -> {
            System.out.println("Heroe termino recorrido de regreso");
            String strIdleFight = "/resources/sprites/batman/IdleFight.png";
            configuracionSuperHeroe(strIdleFight, 76, 96, 3, 3, Animation.INDEFINITE);
            ivAnimacion.setScaleX(-1);
        });
    }
    
    private void configuracionSuperVillano(String strImagen, int spriteSizeX, int spriteSizeY, int time, int count, int columns ,int intCycle){
        if(animationVillano != null){
            animationVillano.stop();
        }
        image = new Image(strImagen);
        ivVillano.setImage(image);
        ivVillano.setViewport(new Rectangle2D(0, 0, spriteSizeX, spriteSizeY));
        ivVillano.setScaleX(-1);
        
        animationVillano = new SpriteAnimation(ivVillano,Duration.millis(time), count, columns, 0, 0, spriteSizeX, spriteSizeY);
        animationVillano.setCycleCount(intCycle);
        animationVillano.setAutoReverse(true);
        animationVillano.play();
        animationVillano.setOnFinished((ActionEvent event1) -> {
            System.out.println("Termino el villano");
            ivAnimacion.setScaleY(-1);
            String strIdleFight = "/resources/sprites/batman/IdleFight.png";
            configuracionSuperVillano(strIdleFight, 76, 96, 500, 3, 3, Animation.INDEFINITE);
            String pathSprites = "/resources/sprites/batman/Walk.png";
            configuracionSuperHeroe(pathSprites, 87, 96, 5, 5, Animation.INDEFINITE);
            moverInversoSuperHeroe(3000);
        });
    }
    
    private void setAnimationDefault(){
        ivVillano.setScaleX(1);
        ivVillano.setScaleY(1);
        ivAnimacion.setScaleX(1);
        ivAnimacion.setScaleY(1);
    }
    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(AnimacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
