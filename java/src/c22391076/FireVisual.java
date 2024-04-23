package c22391076;

import ie.tudublin.*;

public class FireVisual {
    float[] flames; // Array to store the heights of flames
    Avatar fv; // An instance of the Combined class (assumed to be part of the ie.tudublin
               // package)

    public FireVisual(Avatar fv) {
        this.fv = fv;
        flames = new float[fv.width]; // Initialize the array based on the width of the canvas
    }

    public void draw() {
        fv.fill(0, 20); // Fill the background with black with 20% opacity
        fv.noStroke(); // Disable stroke
        fv.rect(0, 0, fv.width, fv.height); // Draw a rectangle that covers the entire canvas
        fv.translate(fv.width / 2, fv.height / 2); // Translate the origin to the center of the canvas

        // Update and draw flames
        for (int i = 0; i < flames.length; i++) {
            // Update flame height based on audio input or other interaction
            float flameHeight = fv.random(50, 200); // Example: Use random height for now
            flames[i] = fv.lerp(flames[i], flameHeight, 0.1f); // Smoothly transition flame height

            // Draw flame
            fv.fill(255, 150, 0); // Set flame color (orange)
            fv.rect(i - fv.width / 2, fv.height / 2, 1, -flames[i]); // Draw flame
        }
    }
}
