package c22503056;

import ie.tudublin.Avatar;
import processing.core.PApplet;

public class EarthVisual {

    Avatar ev; // An instance of the Avatar class
    float yOffset = 0; // Offset for the heart rate monitor line

    // Constructor that takes an instance of the Avatar class as a parameter
    public EarthVisual(Avatar ev) {
        this.ev = ev;
    }

    // Method that is called to draw the visualization
    public void draw() {
        ev.background(0); // Fill the background with black
        ev.lights();
        // Update the yOffset based on the music amplitude
        yOffset = ev.getSmoothedAmplitude() * 200;

        // Draw the heart rate monitor line
        drawSpiral();

        ev.pushMatrix(); // Save current transformation state
        ev.translate(ev.width / 2, ev.height / 2, 0); // Translate to the center of the canvas

        // Rotate the tree based on frame count
        ev.rotateY(ev.frameCount * 0.01f);

        // Draw the tree-like object (recursive tree)
        drawTree(100, 255); // Start with a trunk length of 100 pixels

        ev.popMatrix(); // Restore previous transformation state

        drawColumns();
    }

    // Drawing the spiral
    private void drawSpiral() {
        float step = 10; // Step size for drawing the monitor
        float xStart = 50; // X-coordinate to start drawing the line
        float xEnd = ev.width - 50; // X-coordinate to end drawing the line
        float y = ev.height / 8; // Y-coordinate for the heart rate monitor line (moved higher up)
    
        ev.colorMode(PApplet.RGB);
        ev.stroke(0, 255, 0); // Green color
        ev.strokeWeight(1);
    
        // Draw the spiral
        for (float x = xStart; x <= xEnd; x += step) {
            float sineWave = PApplet.sin(x * 0.1f + ev.frameCount * 0.1f); // Calculate a sine wave
            float yCoord = y + sineWave * yOffset; // Adjust the y-coordinate based on the sine wave and yOffset
            ev.line(x, yCoord, x + step, yCoord); // Draw a line segment
        }
    }

    // Rotating Tree
    private void drawTree(float h, float brightness) {
        // Calculate the branch length based on the smoothed amplitude
        float branchLength = PApplet.map(ev.getSmoothedAmplitude(), 0, 0.5f, h * 0.5f, h * 1.5f);

        ev.colorMode(PApplet.HSB);
        float hue = PApplet.map(h, 10, 100, 30, 90); // Map height to hue value
        ev.stroke(hue, 255, brightness); // Set stroke color based on hue and brightness
        ev.strokeWeight(5); // Set stroke weight for branches

        // Draw the current branch
        ev.line(0, 0, 0, -branchLength); // Draw a line representing the branch

        // Move to the end of the current branch
        ev.translate(0, -branchLength);

        // Reduce the length of the branch
        h *= 0.7;

        // Recursively draw smaller branches until the length is too small
        if (h > 10) {
            // Draw a branch to the right
            ev.pushMatrix(); // Save current transformation state
            ev.rotate(PApplet.radians(45)); // Rotate to the right
            drawTree(h, brightness - 20); // Recursively draw smaller branches with reduced brightness
            ev.popMatrix(); // Restore previous transformation state

            // Draw a branch to the left
            ev.pushMatrix(); // Save current transformation state
            ev.rotate(-PApplet.radians(45)); // Rotate to the left
            drawTree(h, brightness - 20); // Recursively draw smaller branches with reduced brightness
            ev.popMatrix(); // Restore previous transformation state
        }
    }

    //Rockslide
    private void drawColumns() {
        ev.colorMode(PApplet.RGB);
        ev.noStroke(); // No stroke for the columns
    
        // Number of columns
        int numColumns = 20;
        // Width of each column
        float columnWidth = (float) ev.width / numColumns;
    
        // Height multiplier based on music amplitude
        float heightMultiplier = ev.getSmoothedAmplitude() * 500;
    
        // Draw each column
        for (int i = 0; i < numColumns; i++) {
            float x = i * columnWidth;
            float columnHeight = PApplet.sin(ev.frameCount * 0.1f + i) * heightMultiplier;
            ev.fill(139,69,19); // Brown color for columns
            ev.rect(x, ev.height - columnHeight, columnWidth, columnHeight); // Draw column
        }
    }
}