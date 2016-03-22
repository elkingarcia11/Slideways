/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package slideways;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author elkingarcia
 */
public class Row implements GameConstants{
    private ArrayList<Shape> shapes;
    private Double[] historyOfMoves;
    private Integer[] historyOfCell;
    private String[] historyOfColors;
    private int PLAYER;
    private int typeOfMove;
    
    private Rectangle r;
    private Double locationX;
    private Double locationY;
    private Double gameWidth;
    private Double startpoint;
    private Boolean playerMoved;
    private String moveStatus;
    private double interval;
    private double startx;
    private double lastDifference;
    private Cell one;
    private Cell two;
    private Cell three;
    private Cell four;
    
    public Row(double startX,double startY,double gamewidth,double id){
        r = new Rectangle(startX, startY, gamewidth, gamewidth/4);
        r.setFill(Color.NAVY);
        r.setStroke(Color.SLATEGRAY);
        r.setStrokeWidth(10);
        
        double lengthofcell = gamewidth/9;
        interval = gamewidth / 4;
        double cellY = ((gamewidth/4)-lengthofcell)/2;
        
        one = new Cell(startX+(lengthofcell), startY+cellY, lengthofcell, lengthofcell, 1);//change height left+loc
        two = new Cell(startX+(lengthofcell*3), startY+cellY, lengthofcell, lengthofcell, 2);
        three = new Cell(startX+(lengthofcell*5), startY+cellY, lengthofcell, lengthofcell, 3);
        four = new Cell(startX+(lengthofcell*7), startY+cellY, lengthofcell, lengthofcell, 4);
        
        gameWidth = gamewidth;
        
        historyOfMoves = new Double[2];
        historyOfCell = new Integer[1];
        historyOfColors = new String[2];
        playerMoved = false;
        moveStatus = "-1";
        startx = startX;
        historyOfMoves[0]=startx;
        historyOfColors[0]="blue";
        typeOfMove = 0;
        
        shapes = new ArrayList();
        shapes.add(r);
        shapes.add(one.getR());
        shapes.add(two.getR());
        shapes.add(three.getR());
        shapes.add(four.getR());
    }
    
    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    
    public void findNearestInterval(double difference, double y,  double startpt, int player){
        
        if(playerMoved == false){
            PLAYER = player;
            //if mouse was moved farther than it should have itll stop at first interval to left or right otherwise differnce is below interval
            if(difference>0){
                if(difference>(interval)){
                    difference = interval;
                }
            }
            if(difference<0){
                if(difference<-1*(interval)){
                    difference = (interval) * -1;
                }
            }
            double distance = difference/interval; //finds value from 0-1 how far to the next interval you were round either to 0 to stay same or 1 to move by one
            int b = (int)Math.round(distance);
            difference=b*interval;
            
            if(startpt >= startx && startpt <= startx+gameWidth){
                if(r.getX()+difference+gameWidth>width-100){
                    difference = (width - 100) - (r.getX()+gameWidth);
                    r.setX(width-gameWidth-100);
                    lastDifference = difference;
                    one.updateShape(difference);
                    two.updateShape(difference);
                    three.updateShape(difference);
                    four.updateShape(difference);
                    if(startx==r.getX()){
                        moveStatus = "Currently Player " +player+ "'s turn!";
                    }
                    else{
                        moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        startx=r.getX();
                        playerMoved = true;
                        typeOfMove = 1;
                        historyOfMoves[1] = r.getX();
                        
                    }
                }
                else if(r.getX()+difference<100){
                    difference= r.getX()-100;
                    r.setX(100);
                    lastDifference = difference;
                    one.updateShape(difference);
                    two.updateShape(difference);
                    three.updateShape(difference);
                    four.updateShape(difference);
                    if(startx==r.getX()){
                        moveStatus = "Currently Player " +player+ "'s turn!";
                    }
                    else{
                        moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        startx=r.getX();
                        playerMoved = true;
                        typeOfMove = 1;
                        historyOfMoves[1] = r.getX();
                    }
                }
                else{
                    r.setX(r.getX()+(difference));
                    lastDifference = difference;
                    one.updateShape(difference);
                    two.updateShape(difference);
                    three.updateShape(difference);
                    four.updateShape(difference);
                    if(startx==r.getX()){
                        moveStatus = "Currently Player " +player+ "'s turn!";
                    }
                    else{
                        moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        startx=r.getX();
                        playerMoved = true;
                        typeOfMove = 1;
                        historyOfMoves[1] = r.getX();
                        
                    }
                }
            }
            else{
                moveStatus = "Make sure to drag the row you want to move";
            }
        }
        else{
            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
        }
    }
    
    public void changeColor(double x, double y, double diff, int player, String color){
        if(playerMoved==false){
            PLAYER = player;
            //if move down - and player color is red - run check to see if it's already red - if not throw warning - same for gold
            if(one.contains(x, y)||two.contains(x, y)||three.contains(x,y)||four.contains(x, y)){
                if(diff>10){
                    //up changes color red - checks if your color is red then if its already red
                    if(one.contains(x, y)){
                        if(one.getColor().contains("red")){
                            moveStatus = "It is already red. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("gold")){
                            one.turnRed();
                            moveStatus = "Warning: Your color is gold! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 1;
                            historyOfColors[1] = "RED";
                            playerMoved = true;
                        }
                        else{
                            one.turnRed();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 1;
                            historyOfColors[1] = "RED";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                    else if(two.contains(x, y)){
                        if(two.getColor().contains("red")){
                            moveStatus = "It is already red. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("gold")){
                            two.turnRed();
                            moveStatus = "Warning: Your color is gold! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 2;
                            historyOfColors[1] = "RED";
                            playerMoved = true;
                        }
                        else{
                            two.turnRed();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 2;
                            historyOfColors[1] = "RED";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                    else if(three.contains(x, y)){
                        if(three.getColor().contains("red")){
                            moveStatus = "It is already red. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("gold")){
                            three.turnRed();
                            moveStatus = "Warning: Your color is gold! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 3;
                            historyOfColors[1] = "RED";
                            playerMoved = true;
                        }
                        else{
                            three.turnRed();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 3;
                            historyOfColors[1] = "RED";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                    else if(four.contains(x, y)){
                        if(four.getColor().contains("red")){
                            moveStatus = "It is already red. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("gold")){
                            four.turnRed();
                            moveStatus = "Warning: Your color is gold! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 4;
                            historyOfColors[1] = "RED";
                            playerMoved = true;
                        }
                        else{
                            four.turnRed();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 4;
                            historyOfColors[1] = "RED";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                }
                if(diff<-10){
                    if(one.contains(x, y)){
                        if(one.getColor().contains("gold")){
                            moveStatus = "It is already gold. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("red")){
                            one.turnGold();
                            moveStatus = "Warning: Your color is red! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 1;
                            historyOfColors[1] = "GOLD";
                            playerMoved = true;
                        }
                        else{
                            one.turnGold();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 1;
                            historyOfColors[1] = "GOLD";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                    else if(two.contains(x, y)){
                        if(two.getColor().contains("gold")){
                            moveStatus = "It is already gold. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("red")){
                            two.turnGold();
                            moveStatus = "Warning: Your color is red! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 2;
                            historyOfColors[1] = "GOLD";
                            playerMoved = true;
                        }
                        else{
                            two.turnGold();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 2;
                            historyOfColors[1] = "GOLD";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                    else if(three.contains(x, y)){
                        if(three.getColor().contains("gold")){
                            moveStatus = "It is already gold. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("red")){
                            three.turnGold();
                            moveStatus = "Warning: Your color is red! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 3;
                            historyOfColors[1] = "GOLD";
                            playerMoved = true;
                        }
                        else{
                            three.turnGold();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 3;
                            historyOfColors[1] = "GOLD";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                    else if(four.contains(x, y)){
                        if(four.getColor().contains("gold")){
                            moveStatus = "It is already gold. Try Again. It is still " +player+ "'s turn!";
                            playerMoved = false;
                        }
                        else if(color.equals("red")){
                            four.turnGold();
                            moveStatus = "Warning: Your color is red! Currently Player "+player+"'s turn!";
                            typeOfMove = 2;
                            historyOfCell[0]= 4;
                            historyOfColors[1] = "GOLD";
                            playerMoved = true;
                            
                        }
                        else{
                            four.turnGold();
                            playerMoved = true;
                            typeOfMove = 2;
                            historyOfCell[0]= 4;
                            historyOfColors[1] = "GOLD";
                            moveStatus = "Player "+player+" has moved. Player "+player+" can either clear or submit move";
                        }
                    }
                }
                else{
                }
            }
            else{
            }
        }
        else{
        }
    }
    
    public void getLocation(){
    }
    
    public Boolean getPlayerMoved() {
        return playerMoved;
    }
    
    public String getMoveStatus() {
        return moveStatus;
    }
    
    public boolean doesMoveStatusExist(){
        if(moveStatus.equals("-1")){
            return false;// Move is empty
        }
        else{
            return true;
        }
    }
    public void clearMove(){
        playerMoved = false;
        
        if(typeOfMove==1){
            moveStatus = "-1";
            typeOfMove = 0;
            historyOfMoves[1] = historyOfMoves[0];
            startx = historyOfMoves[0];
            r.setX(startx);
            if(lastDifference>0){
                one.updateShape(-lastDifference);
                two.updateShape(-lastDifference);
                three.updateShape(-lastDifference);
                four.updateShape(-lastDifference);
            }
            if(lastDifference<0){
                one.updateShape(-lastDifference);
                two.updateShape(-lastDifference);
                three.updateShape(-lastDifference);
                four.updateShape(-lastDifference);
            }
            
            
        }
        if(typeOfMove==2){
            typeOfMove = 0;
            int cell = historyOfCell[0];
            historyOfCell[0]=0;
            String previousColor = historyOfColors[0];
            String newcolor = historyOfColors[1];
            historyOfColors[1] = historyOfColors[0];
            if(previousColor.equalsIgnoreCase("blue")){
                if(cell==1){
                    moveStatus = "-1";
                    one.turnBlue();
                }
                if(cell==2){
                    moveStatus = "-1";
                    two.turnBlue();
                }
                if(cell==3){
                    moveStatus = "-1";
                    three.turnBlue(); 
                }
                if(cell==4){
                    moveStatus = "-1";
                    four.turnBlue();
                }
            }
            if(previousColor.equalsIgnoreCase("red")){
                if(cell==1){
                    moveStatus = "-1";
                    one.turnRed();
                }
                if(cell==2){
                    moveStatus = "-1";
                    two.turnRed();
                }
                if(cell==3){
                    moveStatus = "-1";
                    three.turnRed();
                }
                if(cell==4){
                    moveStatus = "-1";
                    four.turnRed();
                }
            }
            if(previousColor.equalsIgnoreCase("gold")){
                if(cell==1){
                    moveStatus = "-1";
                    one.turnGold();
                }
                if(cell==2){
                    moveStatus = "-1";
                    two.turnGold();
                }
                if(cell==3){
                    moveStatus = "-1";
                    three.turnGold();
                }
                if(cell==4){
                    moveStatus = "-1";
                    four.turnGold();
                }
            }
        }
    }
    
    public void submitMove(){
        playerMoved = false;
        moveStatus = "-1";
        if(typeOfMove==1){
            typeOfMove = 0;
            historyOfMoves[0] = startx;
            historyOfMoves[1] = historyOfMoves[0];
        }
        if(typeOfMove==2){
            typeOfMove = 0;
            historyOfCell[0]=0;
            historyOfColors[0] = historyOfColors[1];
            historyOfColors[1] = historyOfColors[0];
        }
        
    }
    
}
