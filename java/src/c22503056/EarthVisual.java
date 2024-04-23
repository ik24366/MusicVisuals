package c22503056;

import ie.tudublin.Avatar;
import processing.core.PApplet;

public class EarthVisual {

    Avatar ev; // An instance of the Avatar class
    float yOffset = 0; // Offset for the heart rate monitor line
    float totalAmplitude = 0;
    

    public EarthVisual(Avatar ev) {
        this.ev = ev;
    }

    public void draw() {
        ev.background(0);
        ev.lights();
        float currentAmplitude = ev.getSmoothedAmplitude();
        yOffset = currentAmplitude * 200;
        totalAmplitude += currentAmplitude; // Accumulate amplitude over time

        drawSpiral();
        ev.pushMatrix();
        ev.translate(ev.width / 2, ev.height, 0); // Start the tree from the bottom center of the canvas
        float initialBranchLength = PApplet.map(totalAmplitude, 0, 50, 150, 300); // Scale initial branch length
        drawTree(initialBranchLength, 255); // Use dynamic initial length
        ev.popMatrix();
        drawColumns();
    }

    private void drawSpiral() {
        float step = 10; // Step size for drawing the monitor
        float xStart = 50; // X-coordinate to start drawing the line
        float xEnd = ev.width - 50; // X-coordinate to end drawing the line
        float y = ev.height / 8; // Y-coordinate for the heart rate monitor line (moved higher up)
    
        ev.colorMode(PApplet.RGB);
        ev.stroke(0, 255, 0); // Set to green color
        ev.strokeWeight(2);
    
        for (float x = xStart; x <= xEnd; x += step) {
            float sineWave = PApplet.sin(x * 0.1f + ev.frameCount * 0.1f) * yOffset; 
            ev.line(x, y + sineWave, x + step, y + PApplet.sin((x + step) * 0.1f + ev.frameCount * 0.1f) * yOffset);
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
        float columnWidth = (float) ev.width / numColumns;
    
        for (int i = 0; i < numColumns; i++) {
            float x = i * columnWidth;
            float heightMultiplier = ev.getSmoothedAmplitude() * 3500;
            float columnHeight = PApplet.sin(ev.frameCount * 0.1f + i) * heightMultiplier;
    
            // Create a gradient effect for each column
            for (float j = 0; j < columnHeight; j++) {
                // Calculate brightness based on the height
                // Darker at the bottom (base), lighter at the top (peak)
                float brightness = PApplet.map(j, 0, columnHeight, 100, 255);
                int r = (int) PApplet.map(j, 0, columnHeight, 141, 44); // Darker brown at the bottom
                int g = (int) PApplet.map(j, 0, columnHeight, 73, 28);
                int b = (int) PApplet.map(j, 0, columnHeight, 37, 19);
                ev.fill(r, g, b, brightness); // Apply color with changing brightness
    
                // Draw each layer of the gradient
                ev.rect(x, ev.height - j, columnWidth, 1);
            }
    
            drawParticles(x + columnWidth / 2, ev.height - columnHeight, heightMultiplier);
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

}