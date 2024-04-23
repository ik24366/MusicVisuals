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
