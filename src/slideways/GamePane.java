/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package slideways;

import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author elkingarcia
 */
public class GamePane extends Pane {
    public GamePane() {
        
    }
    
    public void setShapes(List<Shape> newShapes) {
        this.getChildren().clear();
        this.getChildren().addAll(newShapes);
    }
}