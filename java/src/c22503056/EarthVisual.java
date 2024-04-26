package c22503056;

import ie.tudublin.Avatar;
import processing.core.PApplet;

public class EarthVisual {

    Avatar ev; // An instance of the Avatar class
    float totalAmplitude = 0;
    
    //Array of particles
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

    //Stars speed, size and height
    private void initializeBackgroundParticles() { 
        for (int i = 0; i < numBackgroundParticles; i++) {
            particleX[i] = ev.random(ev.width);
            particleY[i] = ev.random(ev.height);
            particleSpeedY[i] = ev.random(0.5f, 2.0f); // Random speed upwards
            particleSize[i] = ev.random(2, 5); // Random sizing
        }
    }

    //Creates visuals + calculates the amplitude of the audio input, and accumulates it over time.
    public void draw() {
        ev.background(0); 
        ev.lights(); 
        drawBackgroundParticles();
        float currentAmplitude = (float) (ev.getSmoothedAmplitude() * 0.09); // Calculate the current amplitude of the audio input
        totalAmplitude += currentAmplitude; // Accumulate the total amplitude over time
        ev.pushMatrix(); // Save the current transformation matrix
        ev.translate(ev.width / 2, ev.height, 0); //Puts tree at bottom
        float initialBranchLength = PApplet.map(totalAmplitude, 0, 50, 150, 300); // Map the total amplitude to an initial branch length
        drawTree(initialBranchLength, 255); 
        ev.popMatrix(); // Restore the original transformation matrix
        drawColumns();
    }

    //Draws the tree || growth decrease as recursion progresses 
    private void drawTree(float h, float brightness) {
        if (h < 2) return; // Stop drawing branches if height less than 2

        ev.strokeWeight(Math.max(0.5f, h / 100)); // stroke weight for the branches
        ev.stroke(0, 255, 0); 
        ev.line(0, 0, 0, -h); // Draw a line representing the current branch

        ev.translate(0, -h); // Move the origin to the end of the current branch
        h *= 0.67; // Reduce the height for the next recursive call

        if (h > 5) {
                float baseAngle = PApplet.radians(20); // Base angle for branching
                for (int i = 0; i < 2; i++) { 
                float branchAngle = (i % 2 == 0 ? 1 : -1) * baseAngle; // Calculate the angle for the current branch
                ev.pushMatrix(); 
                ev.rotate(branchAngle); // Rotate for the next branch
                drawTree(h, brightness * 0.9f); // Recursively draw the next branch
                ev.popMatrix(); 
            }
        }
    }

    //Columns - Height based on audio amplitude || bottom diff shade to make it 3D
    private void drawColumns() {
        ev.colorMode(PApplet.RGB); 
        ev.noStroke(); 

        int numColumns = 20;
        float columnWidth = ev.width / (float) numColumns; // Calculate the width of each column
        float depth = 50; 

        for (int i = 0; i < numColumns; i++) { // Iterate over each column
        float x = i * columnWidth; // Calculate the x-coordinate of the current column
        float heightMultiplier = ev.getSmoothedAmplitude() * 3500; // Calculate the height multiplier based on audio
        float columnHeight = PApplet.sin(ev.frameCount * 0.1f + i) * heightMultiplier; // Calculate the height of the current column

        ev.pushMatrix();
        ev.translate(x + columnWidth / 2, ev.height, -depth / 2); // Position to draw the column
        ev.rotateX(PApplet.PI/2); // Rotate to lay the box down

                // Set color based on height for a gradient effect (dark bottom, light top)
                for (float j = 0; j < columnHeight; j += depth) { // Iterate over each segment of the column
                float alpha = PApplet.map(j, 0, columnHeight, 255, 100); // alpha value for transparency
                ev.fill(90, 40, 10, alpha); 
                //  Create a gradient effect
                ev.box(columnWidth, depth, depth); // Draw the box with width, height, and depth
                ev.translate(0, -depth, 0); // Move up for the next segment
            }
        
            ev.popMatrix();
        }
    }

    //Draws Stars || Reset if out of view 
    private void drawBackgroundParticles() {
        ev.noStroke(); 
        ev.fill(255, 255, 255, 150); 

        for (int i = 0; i < numBackgroundParticles; i++) { // Iterate over each background particle
        ev.ellipse(particleX[i], particleY[i], particleSize[i], particleSize[i]); // Stars
        particleY[i] -= particleSpeedY[i]; // Stars go up

            // Reset particles to the bottom of the screen when they go out of view
            if (particleY[i] < 0) { 
                particleY[i] = ev.height + particleSize[i]; 
                particleX[i] = ev.random(ev.width); 
                }
        }
    
    }
}