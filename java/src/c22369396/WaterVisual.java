package c22369396;
import processing.core.PApplet;
import processing.core.PConstants;

import ie.tudublin.*;

public class WaterVisual {

    private static final int PI = 0;
    int numCircles = 200;
    Circle[] circles = new Circle[numCircles];
    Particle[] particles = new Particle[100];
    Wave[] waves = new Wave[4]; // Four edges of the screen
    Avatar wv;
    float angleX = 0;
    float angleY = 0;

    public WaterVisual(Avatar wv) {
        this.wv = wv;
        wv.colorMode(wv.HSB, 360, 100, 100, 100);
        for (int i = 0; i < numCircles; i++) {
            circles[i] = new Circle(wv.random(wv.width), wv.random(wv.height), wv);
        }
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(wv);
        }
        // Initialize waves for each screen edge
        waves[0] = new Wave(wv, 0); // Top
        waves[1] = new Wave(wv, 1); // Right
        waves[2] = new Wave(wv, 2); // Bottom
        waves[3] = new Wave(wv, 3); // Left
    }

    public void draw() {
        wv.background(0);
        wv.noFill();

        float audioLevel = wv.getSmoothedAmplitude() * 1000;

        for (Particle particle : particles) {
            particle.update();
            particle.display();
        }

        drawRotating3DOrb(audioLevel);

        for (Circle circle : circles) {
            circle.update(audioLevel);
            circle.display();
        }

        for (Wave wave : waves) {
            wave.update(audioLevel);
            wave.display();
        }
    }


    void drawRotating3DOrb(float audioLevel) {
        wv.pushMatrix();
        wv.translate(wv.width / 2, wv.height / 2);
        wv.rotateX(angleX);
        wv.rotateY(angleY);
        angleX += 0.05;
        angleY += 0.03;

        float orbSize = wv.map(audioLevel, 0, 1000, 100, 400);

        // Enhanced lighting effect
        wv.directionalLight(255, 255, 255, -1, -1, -1);
        wv.spotLight(255, 255, 255, wv.width / 2, wv.height, 600, 0, 0, -1, PI / 2, 2);

        wv.noStroke();
        int innerColor = wv.color(210, 100, 100);
        int outerColor = wv.color(210, 100, 100, 0);
        for (int i = 0; i <= orbSize; i += 2) {
            float inter = wv.map(i, 0, orbSize, 0, 1);
            int c = wv.lerpColor(innerColor, outerColor, inter);
            wv.fill(c);
            wv.sphere(orbSize - i);
        }

        wv.blendMode(wv.ADD);
        wv.stroke(210, 100, 100, 50);
        wv.strokeWeight(10);
        for (int i = 1; i <= 15; i++) {
            wv.sphere(orbSize + i * 2);
        }

        wv.strokeWeight(2);
        wv.stroke(180, 100, 100);
        for (int i = 0; i < 12; i++) {
            wv.pushMatrix();
            wv.rotateY(wv.radians(i * 30));
            wv.rotateX(wv.radians(45));
            wv.line(-orbSize, 0, orbSize, 0);
            wv.popMatrix();
        }
        wv.blendMode(wv.BLEND);

        wv.popMatrix();
    }

    class Circle {
        float x, y;
        float diameter;
        float maxDiameter;
        float speed;
        Avatar wv;
    
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
            float hue = wv.random(190, 250);
            int alpha = 120; // Adjusted for visible but transparent fill
            wv.stroke(hue, 100, 100, alpha);
            wv.strokeWeight(2);
            // Applying a semi-transparent fill
            wv.fill(hue, 100, 100, 80); // Fill with transparency
            wv.ellipse(x, y, diameter, diameter);
        }
    }
    

    class Particle {
        float x, y, speedY;
        float size;
        Avatar wv;
    
        Particle(Avatar wv) {
            this.wv = wv;
            respawn();
        }
    
        void update() {
            y -= speedY;
            if (y < -size) {
                respawn();
            }
        }
    
        void respawn() {
            x = wv.random(wv.width);
            y = wv.height + wv.random(50);
            speedY = wv.random(1, 3);
            size = wv.random(2, 5);
        }
    
        void display() {
            wv.noStroke();
            // Set the fill to be slightly transparent by adjusting the alpha value
            wv.fill(255, 255, 255, 127); // Alpha set to 127, making it 50% transparent
            wv.ellipse(x, y, size, size);
        }
    }
    
}

class Wave {
    Avatar wv;
    int edge;
    float amplitude = 0;
    float noiseOffset = 0; // Offset for Perlin noise

    Wave(Avatar wv, int edge) {
        this.wv = wv;
        this.edge = edge;
    }

    void update(float audioLevel) {
        // Scale the amplitude more aggressively based on the audio level
        amplitude = PApplet.map(audioLevel, 0, 1000, 0, 150) * (0.5f + wv.noise(noiseOffset));
        noiseOffset += 0.02; // Increase the rate of change in noise pattern
    }

    void display() {
        int colorValue = (int) PApplet.map(amplitude, 0, 150, 180, 360); // Adjust the color range for greater sensitivity
        wv.stroke(colorValue, 255, 255);
        wv.strokeWeight(4);
        wv.noFill();

        float baseLine; // This will determine the base line of the wave
        float waveHeight; // This is a multiplier for the wave amplitude
        wv.beginShape();

        switch (edge) {
            case 0: // Top
                baseLine = 0;
                waveHeight = -1; // Negative because we're going upwards
                for (int x = 0; x < wv.width; x += 5) {
                    float y = baseLine + waveHeight * amplitude * wv.noise(noiseOffset + x * 0.05f);
                    wv.vertex(x, y);
                }
                break;
            case 1: // Right
                baseLine = wv.width;
                waveHeight = -1; // Negative to go left
                for (int y = 0; y < wv.height; y += 5) {
                    float x = baseLine + waveHeight * amplitude * wv.noise(noiseOffset + y * 0.05f);
                    wv.vertex(x, y);
                }
                break;
            case 2: // Bottom
                baseLine = wv.height;
                waveHeight = 1; // Positive to go downwards
                for (int x = 0; x < wv.width; x += 5) {
                    float y = baseLine + waveHeight * amplitude * wv.noise(noiseOffset + x * 0.05f);
                    wv.vertex(x, y);
                }
                break;
            case 3: // Left
                baseLine = 0;
                waveHeight = 1; // Positive to go right
                for (int y = 0; y < wv.height; y += 5) {
                    float x = baseLine + waveHeight * amplitude * wv.noise(noiseOffset + y * 0.05f);
                    wv.vertex(x, y);
                }
                break;
        }

        wv.endShape();
    }
}
