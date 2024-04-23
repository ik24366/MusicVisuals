package c22369396;

import ie.tudublin.*;

public class WaterVisual {

    int numCircles = 200; // Number of ripple circles
    Circle[] circles = new Circle[numCircles];
    Avatar wv; // An instance of the Avatar class from the processing library
    float angleX = 0; // Initial angle for the X-axis rotation
    float angleY = 0; // Initial angle for the Y-axis rotation

    public WaterVisual(Avatar wv) {
        this.wv = wv;
        wv.colorMode(wv.HSB, 360, 100, 100, 100);
        for (int i = 0; i < numCircles; i++) {
            circles[i] = new Circle(wv.random(wv.width), wv.random(wv.height), wv);
        }
    }

    public void draw() {
        wv.background(0); // Set background to black
        wv.noFill();

        float audioLevel = wv.getSmoothedAmplitude() * 1000; // Scale factor for more sensitivity

        drawRotating3DOrb(audioLevel);

        for (Circle circle : circles) {
            circle.update(audioLevel);
            circle.display();
        }
    }

    void drawRotating3DOrb(float audioLevel) {
        wv.pushMatrix();
        wv.translate(wv.width / 2, wv.height / 2); // Move to the center
        wv.rotateX(angleX); // Apply rotation on X-axis
        wv.rotateY(angleY); // Apply rotation on Y-axis
        angleX += 0.05; // Increment angle for rotation over time on X-axis
        angleY += 0.03; // Increment angle for rotation over time on Y-axis
    
        // Calculate orb size based on audio level
        float orbSize = wv.map(audioLevel, 0, 1000, 100, 400); // Making the orb bigger
    
        wv.lights();
        wv.noStroke();
        wv.fill(210, 100, 100); // More saturated neon blue fill
        wv.sphere(orbSize);
    
        // Increase the glow effect
        wv.stroke(210, 100, 100, 50); // Glowing blue stroke
        wv.strokeWeight(10); // Wider stroke for a visible glow effect
        for (int i = 1; i <= 15; i++) {
            wv.sphere(orbSize + i * 2); // Creating a gradient glow effect
        }
    
        // Draw energy lines on the orb
        wv.strokeWeight(2);
        wv.stroke(180, 100, 100); // Slightly different blue for visibility
        for (int i = 0; i < 12; i++) { // Draw multiple lines
            wv.pushMatrix();
            wv.rotateY(wv.radians(i * 30)); // Rotate each line around the Y-axis
            wv.rotateX(wv.radians(45)); // Slight tilt
            wv.line(-orbSize, 0, orbSize, 0); // Draw line through the sphere's diameter
            wv.popMatrix();
        }
    
        wv.popMatrix();
    }
    

    class Circle {
        float x, y; // Center position of the circle
        float diameter; // Initial diameter of the circle
        float maxDiameter; // Maximum diameter based on audio level
        float speed; // Speed at which the circle expands
        Avatar wv; // Reference to main visual class for drawing

        Circle(float x, float y, Avatar wv) {
            this.x = x;
            this.y = y;
            this.wv = wv;
            this.diameter = 0;
            this.maxDiameter = wv.random(100, 300);
            this.speed = wv.random(1, 5);
        }

        void update(float audioLevel) {
            diameter += speed;
            if (diameter > maxDiameter || diameter > wv.width / 10) {
                diameter = 0;
                maxDiameter = audioLevel + wv.random(50, 200);
                speed = wv.random(1, 5);
            }
        }

        void display() {
            float hue = wv.random(190, 250); // Variability in the blue spectrum
            wv.stroke(hue, 100, 100); // Varied bright blue color
            wv.strokeWeight(2);
            wv.noFill();
            wv.ellipse(x, y, diameter, diameter);
        }
    }
}
