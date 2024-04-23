package c22480092;
import ie.tudublin.*;
import processing.core.PApplet;

public class Lightning {
    private Avatar p; // Reference to the Processing sketch
    private float lightningProbability = 0.1f; // Probability of lightning occurrence

    // Constructor
    public Lightning(Avatar p) {
        this.p = p;
    }

    // Method to draw lightning
    public void draw() {
        if (p.random(1) < lightningProbability) {
            for (int i = 0; i < 5; i++) {
                float startX = p.random(p.width);
                float endX = p.random(p.width);
                float startY = 0;
                float endY = p.height - 50;

                p.noFill();
                p.beginShape();
                p.vertex(startX, startY);
                for (int j = 0; j < 50; j++) {
                    float x = PApplet.lerp(startX, endX, j / 30.0f);
                    float y = PApplet.lerp(startY, endY, j / 30.0f) + p.random(-50, 50);
                    float thickness = 10 * PApplet.pow(PApplet.sin(j / 30.0f * PApplet.PI), 2);

                    int c = p.color(PApplet.map(j, 0, 30, 255, 0), PApplet.map(j, 0, 30, 0, 255), PApplet.map(j, 0, 30, 128, 255));
                    
                    p.stroke(c, 255 - j * 5);
                    p.strokeWeight(thickness);
                    p.vertex(x, y);
                }
                p.vertex(endX, endY);
                p.endShape();
            }
        }
    }
}
