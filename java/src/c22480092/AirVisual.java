package c22480092;
import ie.tudublin.*;

public class AirVisual {

    
        float spinAngle; // Used to rotate the ellipses
        float motion; // Used to create motion in the visualization
        float motionSpeed; // Used to control the speed of motion
        float speed; // Used to control the speed of motion
        
        Avatar mz; // instant of Avatar class
        Lightning lightning; // Instance of Lightning class

    
        // Constructor that takes an instance of the Combined class as a parameter
        public AirVisual(Avatar mz){
            this.mz = mz;
            this.lightning = new Lightning(mz); // Initialize the Lightning class
        }
    
        // Method that is called to draw the visualization
        public void draw() {
            {
            lightning.draw();
            mz.fill(0, 50); // Fill the background with black with 20% opacity
            mz.noStroke(); // Disable stroke
            mz.rect(0, 0, mz.width, mz.height); // Draw a rectangle that covers the entire canvas
            mz.translate(mz.width / 2, mz.height / 2); // Translate the origin to the center of the canvas
            mz.colorMode(mz.HSB, 360, 120, 240);
            
            // Loop through every sample in the sound buffer
            for (int i = 0; i < mz.mySound.bufferSize() - 1; i++) {
                float amplitude  = mz.mySound.left.level() * 40; // Get the amplitude of the left channel
                float colorValue = mz.map(mz.frameCount % 360, 0, 360, 120, 240); // Map to a range within blue shades with frame count
                float brightness = mz.map(amplitude, 0, 150, 100, 255); // Brightness based on amplitude
                mz.fill(colorValue, 100, brightness); // Set the fill color based on index and amplitude
                ShapeDraw.drawHexagon(mz, i, amplitude); // Call to static method from ShapeDrawer
                mz.rotateZ((float) (spinAngle * -mz.PI / 4 * 0.05)); // Rotate the ellipse around the z-axis
            }
    
            // Add a new motion to the visualization
            motion += 0.05; // Increment the motion variable
            float x = mz.sin(motion) * mz.width / 4; // Calculate the x-coordinate of the translation
            float y = mz.cos(motion) * mz.height / 4; // Calculate the y-coordinate of the translation
            mz.translate(x, y); // Translate the origin by the calculated amount
    
            spinAngle += 0.1; // Increment the spin variable
            motionSpeed += speed; // Increment the motion speed variable
        }
        }
}
