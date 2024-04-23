package c22503056;

import ie.tudublin.Avatar;
import processing.core.PApplet;

public class EarthVisual {

    Avatar ev; // An instance of the Avatar class
    float yOffset = 0; // Offset for the heart rate monitor line
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
        float currentAmplitude = ev.getSmoothedAmplitude();
        yOffset = currentAmplitude * 200;
        totalAmplitude += currentAmplitude;
    
        drawSpiral();
        ev.pushMatrix();
        ev.translate(ev.width / 2, ev.height, 0);
        float initialBranchLength = PApplet.map(totalAmplitude, 0, 50, 150, 300);
        drawTree(initialBranchLength, 255);
        ev.popMatrix();
        drawColumns();
    }
    

    private void drawSpiral() {
        float step = 10; // Step size for drawing the monitor
        float xStart = 50; // X-coordinate to start drawing the line
        float xEnd = ev.width - 50; // X-coordinate to end drawing the line
        float y = ev.height / 4; // Y-coordinate for the heart rate monitor line

        ev.colorMode(PApplet.RGB);
        ev.stroke(0, 255, 0); // Green color
        // Set stroke weight to make the line thinner
        ev.strokeWeight(1);

        // Draw the heart rate monitor line
        for (float x = xStart; x <= xEnd; x += step) {
            float sineWave = PApplet.sin(x * 0.1f + ev.frameCount * 0.1f); // Calculate a sine wave
            float yCoord = y + sineWave * yOffset; // Adjust the y-coordinate based on the sine wave and yOffset
            ev.line(x, yCoord, x + step, yCoord); // Draw a line segment
        }
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
                ev.fill(139, 69, 19, alpha);
                // Draw small segments of the box to create gradient effect
                ev.box(columnWidth, depth, depth);  // Draw the box with width, height, and depth
                ev.translate(0, -depth, 0);  // Move up for the next segment
            }
            
            ev.popMatrix();  // Restore the original transformation matrix
        }
    }
    
    


    private void drawParticles(float x, float y, float amplitude) {
        int numParticles = (int) (3 * amplitude); // Reduce particle count but increase size
        for (int i = 0; i < numParticles; i++) {
            float angle = ev.random(PApplet.TWO_PI);
            float particleDistance = ev.random(10, 40); // Larger particles
            float px = x + particleDistance * PApplet.cos(angle);
            float py = y - particleDistance * PApplet.sin(angle); // Particles shoot upwards
            float particleSize = ev.random(3, 7); // Larger particle size for rock effect
            ev.fill(34,59,5); // Rock color
            ev.ellipse(px, py, particleSize, particleSize);
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