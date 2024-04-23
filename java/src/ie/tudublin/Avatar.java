package ie.tudublin;

import ddf.minim.*;
import c22391076.*;
import c22480092.*;
import c22503056.*;
import c22369396.*;

public class Avatar extends Visual {

    Minim minim;
    int visual = 0; // Stores the currently selected visual
    int m = 0; // Stores the elapsed time in seconds
    public AudioPlayer mySound; // Minim audio player

    // Declare the visual variables without initializing them
    AirVisual mz;
    FireVisual fv;

    public void settings() {
        size(1920, 1080, P3D); // Set the size and renderer of the sketch
        fullScreen(SPAN);
    }

    public void setup() {
        colorMode(HSB); // Set the color mode of the sketch
        frameRate(60); // Set the frame rate of the sketch

        // Initialize Minim and load the audio file
        startMinim();
        loadAudio("Avatar.mp3");

        // Initialize the visuals after loading the audio
        initializeVisuals();

        // Start playing the audio
        getAudioPlayer().play();
        smooth();

        minim = new Minim(this);
        mySound = minim.loadFile("Avatar.mp3");
        mySound.play();
    }

    public void initializeVisuals() {
        // Initialize each visual and pass a reference to this sketch and the
        // AudioPlayer
        mz = new AirVisual(this);
        fv = new FireVisual(this);
    }

    public void keyPressed() {
        // Select the visual based on the key pressed
        if (keyCode == '1') {
            visual = 1;
        } else if (keyCode == '2') {
            visual = 2;
        } else if (keyCode == '3') {
            visual = 3;
        } else if (keyCode == '4') {
            visual = 4;
        } else if (keyCode == '5') { // Exit the sketch if the number 7 is pressed
            exit();
        }
    }

    public void draw() {
        System.out.println(keyCode); // Print the ASCII code of the key pressed to the console

        background(0); // Set the background color to black
        calculateFrequencyBands(); // Calculate the frequency bands of the audio
        calculateAverageAmplitude(); // Calculate the average amplitude of the audio

        // Draw the currently selected visual
        if (visual == 4) {
            mz.draw();
        } else if (visual == 2) {
            fv.draw();
            // } else if (visual == 3) {
            // .draw();
            // } else if (visual == 4) {
            // .draw();
            // } else if (visual == 5) {
            // .draw();
            // } else if (visual == 6) {
            // .draw();
            // }
        }
    }
}
