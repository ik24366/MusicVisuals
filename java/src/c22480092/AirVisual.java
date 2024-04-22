package c22480092;
import ie.tudublin.*;

public class AirVisual {

    
        float n; // Used to rotate the ellipses
        float n2; // Used to create motion in the visualization
        float n3; // Used to control the speed of motion
        float speed2; // Used to control the speed of motion
        
        Avatar mz; // An instance of the Combined class (assumed to be part of the ie.tudublin package)
    
        // Constructor that takes an instance of the Combined class as a parameter
        public AirVisual(Avatar mz){
            this.mz = mz;
        }
    
        // Method that is called to draw the visualization
        public void draw() {
            mz.fill(0, 20); // Fill the background with black with 20% opacity
            mz.noStroke(); // Disable stroke
            mz.rect(0, 0, mz.width, mz.height); // Draw a rectangle that covers the entire canvas
            mz.translate(mz.width / 2, mz.height / 2); // Translate the origin to the center of the canvas
    
        }
    
    


}
