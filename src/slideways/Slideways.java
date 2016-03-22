/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package slideways;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author elkingarcia
 */
public class Slideways extends Application implements GameConstants {
    private double locationx;
    private double locationy;
    private double differenceX;
    private double differenceY;
    private int player;
    private ArrayList<Shape> listOfShapes;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        double widthlimit = (width-200); // total width allocated for game
        double gameWidth = ((widthlimit)/10)*4; // width of game or of each row
        double startX = 100+ ((widthlimit/10)*3); //start x pt
        double startY = (height - gameWidth)/2; // start y pt
        player = 1; //if 1 its player 1's turn. if 2 its player 2's turn
        BorderPane borderPane = new BorderPane(); // initialize game environment and panes
        GamePane root = new GamePane();
        Arena main = new Arena(startX, startY, gameWidth);
        
        Button clear = new Button("Undo Move!"); //create buttons
        Button submit = new Button("Submit Move!");
        Text playerTurn = new Text("Currently Player 1's turn! Warning: Once submitted, the move cannot be undone.");
        Text playerOne = new Text("Player 1");
        Text playerTwo = new Text("Player 2");
        ButtonBar buttonBar = new ButtonBar();
        
        playerOne.setTextAlignment(TextAlignment.CENTER);
        
        playerOne.relocate((gameWidth/9)-(gameWidth/(9*2)), (gameWidth/9)-25);
        playerTwo.relocate(width-(2*gameWidth/9)-(gameWidth/18), (gameWidth/9)-25);
        playerTurn.relocate(startX, (height-startY+25));
        root.setShapes(main.getShapes());  // add buttons to pane and rows/cells to game pane
        root.getChildren().add(playerTurn);
        root.getChildren().add(playerOne);
        root.getChildren().add(playerTwo);
        buttonBar.getButtons().addAll(clear, submit);
        borderPane.setCenter(root);
        borderPane.setBottom(buttonBar);
        
        Scene scene = new Scene(borderPane, width, height);
        
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent evt) -> {
            locationx = evt.getX();
            locationy = evt.getY();
        });
        
        root.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent evt) -> {
            differenceX = evt.getX()-locationx;
            differenceY = evt.getY()-locationy;
            
            /*//if moves to the right, move by one interval
            if(differenceX > 0){
            differenceX = gameWidth/4;
            }
            //if moves to the left, move by one interval
            if(differenceX<0){
            differenceX = -1*gameWidth/4;
            }*/
            //    main.updateShape(differenceX, evt.getY(), locationx);
        });
        
        root.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent evt) -> {
            String status = null;
            if(main.getPlayerMoved()==true){
                status = "Player " +player+" has moved. Player " +player+" can either undo or submit move";  
            }else{
                if(Math.abs(differenceY)>Math.abs(differenceX)){
                    main.changeColor(locationx, locationy, differenceY, player);
                }
                else{
                    main.findNearestInterval(differenceX, locationx, locationy, player);
                }
                if(main.getMoveStatus().equals(null)){
                        status="Currently Player "+player+"'s turn! Warning: Once submitted, the move cannot be undone.";
                }
                else{
                    status = main.getMoveStatus();
                }
            }
            playerTurn.setText(status);
        });
        root.requestFocus();
        
        clear.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                main.clearMove(player);
                String status = "Currently Player "+player+"'s turn! Warning: Once submitted, the move cannot be undone.";
               
                playerTurn.setText(status);
            };
        });
        
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                String status = null;
                if(main.getPlayerMoved()){
                    if(player == 1){
                        player = 2;
                    }
                    else{
                        player = 1 ;
                    }
                    main.submitMove();
                    status = "Currently Player "+player+"'s turn! Warning: Once submitted, the move cannot be undone.";
                }
                else{
                    status = "Player "+player+" still hasn't moved. "+ "Currently Player "+player+"'s turn!";
                }
                
                playerTurn.setText(status);
            };
        });
        
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
