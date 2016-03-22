/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package slideways;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author elkingarcia
 */
public class Cell {
    private Rectangle r;
    private double x;
    private double y;
    private double width1;
    private double height1;
    private int id;
    private String color;
    
    
    
    Cell(double startX, double startY, double Width, double Height, int ID) {
        id = ID;
        x = startX;
        y = startY;
        width1 = Width;
        height1 = Height;
        r = new Rectangle(x, y, width1, height1);
        r.setFill(Color.NAVY);
        r.setStroke(Color.GOLD);
        color = "blue";
        
    }
    
    public Shape getR() {
        return r;
    }
    
    public void updateShape(double X){
        r.setX(x+X);
        x=r.getX();
    }
    
    public boolean contains(double X, double Y){
        if(X>=r.getX() && X <= r.getX()+width1){
            if(Y>=r.getY() && Y<= r.getY()+height1){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    public void turnRed(){
        r.setFill(Color.RED);
        color = "red";
    }
    
    public void turnGold(){
        r.setFill(Color.GOLD);
        color = "gold";
    }

    public void turnBlue(){
        r.setFill(Color.NAVY);
        color = "navy";
    }
    
    public String getColor() {
        return color;
    }
}
