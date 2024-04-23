package c22480092;
import ie.tudublin.*;

public class ShapeDraw {

        // Method to draw a hexagon at a given center 
        public static void drawHexagon(Avatar mz, float centerX, float radius) {
            mz.beginShape();// begins the shape
            for (int angle = 0; angle < 360; angle += 60) { // angle is less then a cicle so hexagon can be created 
                float x = centerX + mz.cos(mz.radians(angle)) * radius;
                float y = centerX + mz.sin(mz.radians(angle)) * radius;
                mz.vertex(x, y);
            }
            mz.endShape(mz.CLOSE); // Closeto complete the hexagon
        }
}
