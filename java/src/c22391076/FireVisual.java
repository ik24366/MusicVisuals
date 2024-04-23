package c22391076;

import ddf.minim.*;
import ie.tudublin.*;

public class FireVisual {

    float amplitude;
    Avatar fv;
    Fireball[] fireballs; // Array to store fireball objects

    public FireVisual(Avatar fv) {
        this.fv = fv;
        fireballs = new Fireball[20]; // Create 20 fireballs
        for (int i = 0; i < fireballs.length; i++) {
            fireballs[i] = new Fireball();
        }
    }

    public void draw() {
        amplitude = fv.mySound.mix.level();
        fv.colorMode(fv.HSB, 360, 100, 100);
        fv.background(0); // Dark background to enhance fire effect

        // Draw fireballs
        for (int i = 0; i < fireballs.length; i++) {
            fireballs[i].update();
            fireballs[i].display();
        }

        // Draw fire in the center
        drawFire();

        // Draw fireball visual
        drawFireballVisual();

        // Draw signal-like effect
        drawSignalEffect();
    }

    private void drawFire() {
        // Center of the screen
        float centerX = fv.width / 2;
        float centerY = fv.height / 2;

        // Base size and intensity of the fire
        float baseSize = 100;
        float baseIntensity = 60;

        // Draw fire particles
        fv.noStroke();
        for (int i = 0; i < 360; i += 10) {
            float angle = fv.radians(i);
            float radius = baseSize + amplitude * 200;
            float x = centerX + fv.sin(angle) * radius;
            float y = centerY + fv.cos(angle) * radius;

            // Adjust color based on audio level
            float hue = 30 + amplitude * 60;
            fv.fill(hue, 100, baseIntensity + amplitude * 40);
            fv.ellipse(x, y, 10, 10);
        }
    }

    private void drawFireballVisual() {
        // Center of the screen
        float centerX = fv.width / 2;
        float centerY = fv.height / 2;

        // Number of blobs
        int numBlobs = 10;

        // Base size and intensity of the blobs
        float baseSize = 50;
        float baseIntensity = 60;

        // Distance between ellipses
        float distance = fv.width / numBlobs;

        // Draw blobs
        fv.noStroke();
        for (int i = 0; i < numBlobs; i++) {
            // Calculate position
            float x = centerX + (i - numBlobs / 2) * distance;
            float y = centerY;

            // Adjust size based on audio level
            float size = baseSize + amplitude * 200;
            // Adjust color based on audio level
            float hue = 30 + amplitude * 60;
            fv.fill(hue, 100, baseIntensity + amplitude * 40);
            // Draw blob
            fv.ellipse(x, y, size / 2, size * 2);

            // Draw additional ellipses going down till the edge of the screen
            for (float dy = size * 2; y + dy < fv.height; dy += size * 2) {
                fv.ellipse(x, y + dy, size / 2, size * 2);
            }
        }
    }

    private void drawSignalEffect() {
        // Center of the screen
        float centerX = fv.width / 2;
        float centerY = fv.height / 2;

        // Number of segments
        int numSegments = 100;

        // Segment size
        float segmentSize = 20;

        // Amplitude multiplier
        float ampMultiplier = 5;

        fv.strokeWeight(2);
        fv.noFill();
        fv.stroke(0, 100, 100); // Red color
        for (int i = 0; i < numSegments; i++) {
            float x1 = centerX - segmentSize / 2 + i * segmentSize;
            float y1 = centerY + amplitude * ampMultiplier;
            float x2 = x1 + segmentSize;
            float y2 = centerY + amplitude * ampMultiplier;

            fv.line(x1, y1, x2, y2);
        }
    }

    // Inner class representing a fireball
    class Fireball {
        float x, y; // Position
        float size; // Size
        float speed; // Expansion speed
        int hue; // Color

        Fireball() {
            // Random initial position within the screen
            x = fv.random(fv.width);
            y = fv.random(fv.height);
            // Random initial size
            size = fv.random(20, 50);
            // Random expansion speed (faster than before)
            speed = fv.random(5, 10);
            // Random color
            hue = (int) fv.random(20, 70);
        }

        void update() {
            // Increase size based on expansion speed
            size += speed;
            // Randomly reposition if fireball expands beyond the screen
            if (size > fv.width || size > fv.height) {
                x = fv.random(fv.width);
                y = fv.random(fv.height);
                size = fv.random(20, 50);
            }
        }

        void display() {
            // Draw fireball
            fv.fill(hue, 100, 255, 50); // Semi-transparent
            fv.ellipse(x, y, size, size);
        }
    }
}
