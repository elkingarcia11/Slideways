/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package slideways;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author elkingarcia
 */
public class Arena implements GameConstants{
    
    private ArrayList<Cell> rowOne;
    private ArrayList<Cell> rowTwo;
    private ArrayList<Cell> rowThree;
    private ArrayList<Cell> rowFour;
    private ArrayList<Double> rowOneLocation; //holds row and cell location
    private ArrayList<Double> rowTwoLocation;
    private ArrayList<Double> rowThreeLocation;
    private ArrayList<Double> rowFourLocation;
    private ArrayList<Shape> shapes;
    private Row one;
    private Row two;
    private Row three;
    private Row four;
    private Boolean playerMoved;
    
    
    private double pastX;
    private double pastY;
    private double starty;
    private double interval;
    private String playerOneColor;
    private String playerTwoColor;
    
    public Arena(double startX, double startY, double gameWidth){
        Rectangle background = new Rectangle(startX, startY, gameWidth, gameWidth);
        double lengthofcell = gameWidth/9;
        Rectangle playerone = new Rectangle(lengthofcell,lengthofcell,lengthofcell,lengthofcell);
        Rectangle playertwo = new Rectangle(width-(2*lengthofcell),lengthofcell,lengthofcell,lengthofcell);
        double randomNum = Math.random();
        if(randomNum>=.5){
            playerone.setFill(Color.GOLD);
            playerOneColor = "gold";
            playertwo.setFill(Color.RED);
            playerTwoColor = "red";
        }
        if(randomNum<.5){
            playerone.setFill(Color.RED);
            playerOneColor = "red";
            playertwo.setFill(Color.GOLD); 
            playerTwoColor = "gold";
        }
        background.setFill(Color.MIDNIGHTBLUE);
        
        interval = gameWidth/4;
        one = new Row(startX, startY, gameWidth, 1);
        two = new Row(startX, startY+interval, gameWidth, 2);
        three = new Row(startX, startY+(2*interval), gameWidth, 3);
        four = new Row(startX, startY+(3*interval), gameWidth, 4);
        starty = startY;
        playerMoved = false;
        
        shapes = new ArrayList();
        shapes.add(background);
        shapes.add(playerone);
        shapes.add(playertwo);
        ArrayList<Shape> shapesInOne = one.getShapes();
        for(Shape a : shapesInOne){
            shapes.add(a);
        }
        ArrayList<Shape> shapesInTwo = two.getShapes();
        for(Shape a : shapesInTwo){
            shapes.add(a);
        }
        ArrayList<Shape> shapesInThree = three.getShapes();
        for(Shape a : shapesInThree){
            shapes.add(a);
        }
        ArrayList<Shape> shapesInFour = four.getShapes();
        for(Shape a : shapesInFour){
            shapes.add(a);
        }
    }
    
    
    public List<Shape> getShapes() {
        return shapes;
    }
    //Record each rows locationX and if the x is out of range dont do anything
    
    public void findNearestInterval(double difference, double startpt, double y, int player){
        if(y>=starty && y< starty+interval){
            pastX = startpt;
            pastY = y;
            one.findNearestInterval(difference,y, startpt, player);
        }
        else if(y>=starty+interval && y<starty+(2*interval)){
            pastX = startpt;
            pastY = y;
            two.findNearestInterval(difference, y, startpt, player);
        }
        else if(y>=starty+(2*interval) && y<starty+(3*interval)){
            pastX = startpt;
            pastY = y;
            three.findNearestInterval(difference, y, startpt, player);
        }
        else if(y>=starty+(3*interval) && y < starty+(4*interval)){
            pastX = startpt;
            pastY = y;
            four.findNearestInterval(difference, y, startpt, player);
        }
    }
    
    public void changeColor(double x, double y, double diff, int player){
        //if(y<starty){
        //}
        //else 
        if(y>=starty && y< starty+interval){
            if(player==1){
                one.changeColor(x,y, diff, player, playerOneColor);
            }
            if(player==2){
                one.changeColor(x,y, diff, player, playerTwoColor);
            }
        }
        else if(y>=starty+interval && y<starty+(2*interval)){
            if(player==1){
                two.changeColor(x,y, diff, player, playerOneColor);
            }
            if(player==2){
                two.changeColor(x,y, diff, player, playerTwoColor);
            }
        }
        else if(y>=starty+(2*interval) && y<starty+(3*interval)){
            if(player==1){
                three.changeColor(x,y, diff, player, playerOneColor);
            }
            if(player==2){
                three.changeColor(x,y, diff, player, playerTwoColor);
            }
        }
        else if(y>=starty+(3*interval) && y < starty+(4*interval)){
            if(player==1){
                four.changeColor(x,y, diff, player, playerOneColor);
            }
            if(player==2){
                four.changeColor(x,y, diff, player, playerTwoColor);
            }
        }
    }
    
    public void clearMove(int player){
        if(one.getPlayerMoved()){
            one.clearMove();
        }
        if(two.getPlayerMoved()){
            two.clearMove();
        }
        if(three.getPlayerMoved()){
            three.clearMove();
        }
        if(four.getPlayerMoved()){
            four.clearMove();
        }
    }
    
    public void submitMove(){
        if(one.getPlayerMoved()){
            one.submitMove();
        }
        if(two.getPlayerMoved()){
            two.submitMove();
        }
        if(three.getPlayerMoved()){
            three.submitMove();
        }
        if(four.getPlayerMoved()){
            four.submitMove();
        }
    }
    
    public void isGameOver(){
        
    }
    
    public Boolean getPlayerMoved() {
        if(one.getPlayerMoved()||two.getPlayerMoved()||three.getPlayerMoved()||four.getPlayerMoved()){
            return true;
        }
        else{
            return false;
        }  
    }   
    
    public String getMoveStatus() {
        if(one.doesMoveStatusExist()) {
            return one.getMoveStatus();
        }
        if(two.doesMoveStatusExist()){
            return two.getMoveStatus();
        }
        if(three.doesMoveStatusExist()){
            return three.getMoveStatus();
        }
        if(four.doesMoveStatusExist()){
            return four.getMoveStatus();
        } 
        else{
            return null;
        }
    }   
}
