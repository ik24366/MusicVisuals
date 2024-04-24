package c22503056;

import ie.tudublin.Avatar;
import processing.core.PApplet;

public class EarthVisual {

    Avatar ev; // An instance of the Avatar class
    float totalAmplitude = 0;
    
    float[] particleX;
    float[] particleY;
    float[] particleSpeedY;
    float[] particleSize;
    int numBackgroundParticles = 100;

    public EarthVisual(Avatar ev) {
        this.ev = ev;

        particleX = new float[numBackgroundParticles];
        particleY = new float[numBackgroundParticles];
        particleSpeedY = new float[numBackgroundParticles];
        particleSize = new float[numBackgroundParticles];
        initializeBackgroundParticles();
    }

    private void initializeBackgroundParticles() {
        for (int i = 0; i < numBackgroundParticles; i++) {
            particleX[i] = ev.random(ev.width);
            particleY[i] = ev.random(ev.height);
            particleSpeedY[i] = ev.random(0.5f, 2.0f); // Random speed for upward movement
            particleSize[i] = ev.random(2, 5); // Random size for visual variety
        }
    }

    public void draw() {
        ev.background(0);
        ev.lights();
        drawBackgroundParticles(); // Draw the particles before other visual elements for better blending
        float currentAmplitude = (float) (ev.getSmoothedAmplitude() * 0.09); // makes amplitdude slower so tree dont go off screen
        totalAmplitude += currentAmplitude;
    
        ev.pushMatrix();
        ev.translate(ev.width / 2, ev.height, 0);
        float initialBranchLength = PApplet.map(totalAmplitude, 0, 50, 150, 300);
        drawTree(initialBranchLength, 255);
        ev.popMatrix();
        drawColumns();
    }

    private void drawTree(float h, float brightness) {
        if (h < 2) return; 

        ev.strokeWeight(Math.max(0.5f, h / 100)); 
        ev.stroke(0, 255, 0); // Set to green color for the tree
        ev.line(0, 0, 0, -h);

        ev.translate(0, -h);
        h *= 0.67;

        if (h > 5) {
            float baseAngle = PApplet.radians(20); 
            for (int i = 0; i < 2; i++) { // Two branches
                float branchAngle = (i % 2 == 0 ? 1 : -1) * baseAngle; 
                ev.pushMatrix();
                ev.rotate(branchAngle);
                drawTree(h, brightness * 0.9f); 
                ev.popMatrix();
            }
        }
    }

    private void drawColumns() {
        ev.colorMode(PApplet.RGB);
        ev.noStroke();
    
        int numColumns = 20;
        float columnWidth = ev.width / (float) numColumns;
        float depth = 50; // Depth of each column
    
        for (int i = 0; i < numColumns; i++) {
            float x = i * columnWidth;
            float heightMultiplier = ev.getSmoothedAmplitude() * 3500;
            float columnHeight = PApplet.sin(ev.frameCount * 0.1f + i) * heightMultiplier;
    
            ev.pushMatrix();  // Save the current transformation matrix
            ev.translate(x + columnWidth / 2, ev.height, -depth / 2);  // Move to the position to draw the column
            ev.rotateX(PApplet.PI/2);  // Rotate to lay the box down
    
            // Set color based on height for a gradient effect (dark bottom, light top)
            for (float j = 0; j < columnHeight; j += depth) {
                float alpha = PApplet.map(j, 0, columnHeight, 255, 100);
                ev.fill(90, 40, 10, alpha);
                // Draw small segments of the box to create gradient effect
                ev.box(columnWidth, depth, depth);  // Draw the box with width, height, and depth
                ev.translate(0, -depth, 0);  // Move up for the next segment
            }
            
            ev.popMatrix();  // Restore the original transformation matrix
        }
    }

    private void drawBackgroundParticles() {
        ev.noStroke();
        ev.fill(255, 255, 255, 150); // Semi-transparent white particles
        for (int i = 0; i < numBackgroundParticles; i++) {
            ev.ellipse(particleX[i], particleY[i], particleSize[i], particleSize[i]);
            particleY[i] -= particleSpeedY[i]; // Move particles upward
    
            // Reset particles to the bottom of the screen when they go out of view
            if (particleY[i] < 0) {
                particleY[i] = ev.height + particleSize[i];
                particleX[i] = ev.random(ev.width); // Re-randomize the x position
            }
        }
    }
    

}