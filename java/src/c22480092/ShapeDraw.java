package c22480092;
import ie.tudublin.*;

public class ShapeDraw {

        // Method to draw a hexagon at a given center 
        public static void drawHexagon(Avatar av, float centerX, float radius) {
            av.beginShape();// begins the shape
            for (int angle = 0; angle < 360; angle += 60) { // angle is less then a cicle so hexagon can be created 
                float x = centerX + av.cos(av.radians(angle)) * radius;
                float y = centerX + av.sin(av.radians(angle)) * radius;
                av.vertex(x, y);
            }
            av.endShape(av.CLOSE); // Closeto complete the hexagon
        }
}
