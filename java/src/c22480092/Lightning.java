//code from i am dani Laura Sun 
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

                    // Alternating between cyan and grey
                    int hue = (j % 2 == 0) ? 180 : 0; // Cyan for even j, hue = 0 (grey) for odd j
                    int saturation = (j % 2 == 0) ? 100 : 0; // Full saturation for cyan, no saturation for grey
                    int brightness = 100; // Constant brightness for clear visibility

                    int c = p.color(hue, saturation, brightness);
                    
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
