/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorraid;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Diego Iturriaga
 */
public class Main extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.showAndWait();
        System.exit(0);//To change body of generated methods, choose Tools | Templates.
    }
    
}
