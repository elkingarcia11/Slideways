
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slideways.GameConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elkingarcia
 */
public class Test extends Application implements GameConstants {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Text slideways = new Text(10, 50, "Slideways!");
        Button playLocally = new Button("Play Locally!");
        Button playOnline = new Button("Play Online!");
        Button howToPlay = new Button("How to Play");
        Button contact = new Button ("Contact Us");
        ButtonBar buttonBar1 = new ButtonBar();
        ButtonBar buttonBar2 = new ButtonBar();
        
        buttonBar1.getButtons().addAll(playLocally, playOnline);
        buttonBar2.getButtons().addAll(howToPlay, contact);
        
        BorderPane.setAlignment(buttonBar1, Pos.CENTER);
        BorderPane.setMargin(buttonBar1, new Insets(12,12,12,12));
        
        BorderPane.setMargin(buttonBar2, new Insets(12,12,12,12));
        
        borderPane.setTop(slideways);
        borderPane.setCenter(buttonBar1);
        borderPane.setBottom(buttonBar2);
        
        
        Scene scene = new Scene(borderPane, width, height);
        primaryStage.setTitle("Slideways");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest((event)->System.exit(0));
        primaryStage.show();
    }
            /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
}
