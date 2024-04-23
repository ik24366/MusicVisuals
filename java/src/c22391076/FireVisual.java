package c22391076;

import ie.tudublin.*;

public class FireVisual {

    float amplitude;
    Avatar fv;
    Fireball[] fireballs; // Array to store fireball objects
    float n1;
    float n2;

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
        float amplitude = fv.mySound.mix.level(); // Get the audio level
        fv.colorMode(fv.HSB, 360, 100, 100); // Set the color mode

        // Set default fill color to black
        fv.fill(0);

        // Use the audio level to change the background color
        // fv.background(fv.map(amplitude, 0, 1, 0, 360), 100, 50);

        // Translate to the center of the screen
        fv.translate(fv.width / 2, fv.height / 2);

        // Loop through each sample in the audio buffer
        for (int i = 0; i < fv.mySound.bufferSize() - 1; i++) {
            // Calculate angles and positions based on the current frame and audio level
            float angle = fv.sin(i + n1) * 50;
            float angle2 = fv.sin(i + n2) * 500;

            float x = fv.sin(fv.radians(i)) * (angle2 + 30);
            float y = fv.cos(fv.radians(i)) * (angle2 + 30);

            float x3 = fv.sin(fv.radians(i)) * (500 / angle);
            float y3 = fv.cos(fv.radians(i)) * (500 / angle);

            // Draw circles and rectangles with colors based on the audio level
            fv.fill(fv.map(amplitude, 0, 1, 0, 360), 100, 100);
            fv.ellipse(x, y, fv.mySound.left.get(i) * 10, fv.mySound.left.get(i) * 10);

            fv.fill(fv.map(amplitude, 0, 1, 0, 360), 50, 100);
            fv.rect(x3, y3, fv.mySound.left.get(i) * 20, fv.mySound.left.get(i) * 10);

            fv.fill(fv.map(amplitude, 0, 1, 0, 360), 100, 100);
            fv.rect(x, y, fv.mySound.right.get(i) * 10, fv.mySound.left.get(i) * 10);

            fv.fill(fv.map(amplitude, 0, 1, 0, 360), 100, 100);
            fv.rect(x3, y3, fv.mySound.right.get(i) * 10, fv.mySound.right.get(i) * 20);
        }

        // Increment the movement variables for the next frame
        n1 += 0.008;
        n2 += 0.04;

        // Draw fire in the center
        drawFire();

        // Draw fireball visual
        drawFireballVisual();
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

    public void drawFireballVisual() {
        // Center of the screen
        float centerX = fv.width / 2;
        float centerY = fv.height / 2;

        // Calculate size based on audio level
        float size = 50 + amplitude * 200;
        // Calculate rotation speed based on audio level
        float rotationSpeed = (float) (amplitude * 0.1);

        // Draw flashy fireball
        fv.noStroke();
        for (int i = 0; i < 360; i += 20) {
            float angle = fv.radians(i);
            float radius = size * 0.5f + amplitude * 100;
            float x = centerX + fv.sin(angle) * radius;
            float y = centerY + fv.cos(angle) * radius;

            // Adjust color based on audio level
            float hue = 30 + amplitude * 60;
            fv.fill(hue, 100, 100);
            fv.ellipse(x, y, size * 0.2f, size * 0.2f);
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
