package c22480092;
import ie.tudublin.*;

public class AirVisual {

    
        float spinAngle; // Used to rotate the hexagon
        float motion; // Used to create motion in the visualization
        float motionSpeed; // Used to control the speed of motion
        float speed; // Used to control the speed of motion
        
        Avatar av; // instant of Avatar class
        Lightning lightning; // Instance of Lightning class

    
        // Constructor that takes an instance of the Avater class as a parameter
        public AirVisual(Avatar av){
            this.av = av;
            this.lightning = new Lightning(av); // Initialize the Lightning class
        }
    
        // Method that is called to draw the visualization
        public void draw() {
            {
            lightning.draw();
            av.fill(0, 50); // Fill the background with black with 20% opacity
            av.noStroke(); // Disable stroke
            av.rect(0, 0, av.width, av.height); // Draw a rectangle that covers the entire canvas
            av.translate(av.width / 2, av.height / 2); // Translate the origin to the center of the canvas
            av.colorMode(av.HSB, 360, 120, 240);
            
            // Loop through every sample in the sound buffer
            for (int i = 0; i < av.mySound.bufferSize() - 1; i++) {
                float amplitude  = av.mySound.left.level() * 40; // Get the amplitude of the left channel
                float colorValue = av.map(av.frameCount % 360, 0, 360, 120, 240); // Map to a range within blue shades with frame count
                float brightness = av.map(amplitude, 0, 150, 100, 255); // Brightness based on amplitude
                av.fill(colorValue, 100, brightness); // Set the fill color based on index and amplitude
                ShapeDraw.drawHexagon(av, i, amplitude); // Call to static method from ShapeDrawer
                av.rotateZ((float) (spinAngle * -av.PI / 4 * 0.05)); // Rotate the hexagon around the z-axis
            }
    
            // Add a new motion to the visualization
            motion += 0.05; // Increment the motion variable
            float x = av.sin(motion) * av.width / 4; // Calculate the x-coordinate of the translation
            float y = av.cos(motion) * av.height / 4; // Calculate the y-coordinate of the translation
            av.translate(x, y); // Translate the origin by the calculated amount
    
            spinAngle += 0.1; // Increment the spin variable
            motionSpeed += speed; // Increment the motion speed variable
        }
        }
}
