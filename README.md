# Music Visualiser Project


| Name                  | Student Number    | Music Visuals | Github users|
|-----------            |-----------        |-----------	|---|
|Muhammad Zayan Zahid	|C22480092 			|Air  			|momo858|
|Sean Laureta			|C22503056			|Earth       	||	
|Sean Laureta			|C22503056			|Earth       	|seanl1213|	
|Emma Radojcic         	|C22369396      	|Water 			| Danielr147 & Emmaradojcic |
|Ismail	Khan			|C22391076			|Fire			|ik24366|

# Description of the assignment 

This audio music visualizer features four distinct elemental visuals representing *Fire, Water, Earth, and Air*. Each visual responds to a fan-made remix of the "Avatar: The Last Airbender" soundtrack by Samuel Kim Music.

## Video goes heya

# Instructions
- To run these Music Visuals, you do these following steps:
- Debug and run the Java file called Main.java.
- Press 1 = Earth, 2 = Fire, 3= Water, 4 = Air, keys to run each Music Visuals.
- By pressing the 5 key you will exit the program.


# How it works

Our music visualizer project is structured using individual files holding our respective visuals. The main Java file, Main.java, executes our code, while Avatar.java combines the individual visuals from separate files. Each file is named after our student number. Under our student number folders, we have contained each of our visuals, which makes it easier to locate each one.

We created a class called Avatar that extends the Java Visuals file. This class takes all the functions and imports we have created and applied in Visuals to be extended to our Avatar file. We then declared each variable without initializing them, assigning short names to our visuals, such as referring to the Earth Visual as "ev". In our setup, we read in our song and prepared it with different functions to be used in our visuals. We used keyboard inputs from 1 to 5 to run each of our visuals.

### Earth
![An image](images/Earth.png)

This Java program creates a visual representation of my take on the Earth aspect scene in "Avatar the Last Air Bender" using the Processing library. It incorporates elements such as a dynamic tree, columns, and background particles to generate an immersive environment. 

The program utilises arrays to store the properties of the background particles (speed, position and size). 

There are five main methods to the code: 

1. 'initializeBackgroundParticles()': 
	This method ensures variation in appearance and movement by initializing properties such as position, speed, and size for background particles.

2. 'draw ()':
	Renders the entire scene by clearing the background, setting up lighting effects, drawing background particles, calculating amplitude, drawing the tree, and rendering columns.

3. 'drawTree(float h, float brightness)':
	Creates the tree structure, adjusting branch length and angle based on amplitude and brightness. It recursively draws a tree with branches based on the given height and brightness.

4. 'drawColumns()':
	Creates columns at the bottom of the screen, with heights changing based on amplitude and utilizing color gradients for a realistic 3D appearance. This method draws architectural columns on the screen with heights determined by amplitude, creating a gradient effect.

5. 	'drawBackgroundParticles()':
	 Draws background particles that move upwards on the screen and resets them when they go out of view. These particles contribute to the atmosphere of the scene, creating an out of this world ambiance.

### Fire
![An image](images/fire.png)
1. Properties:
- amplitude: Stores the audio level.
- fv: Reference to the main program.
- fireballs: Array for simulating fireballs.
- fireParticles: Array for simulating rising fire particles.
- n1 and n2: Control variables for movement.
2. Draw Method:
- Gets audio level and sets color mode.
- Draws fireballs and fire particles.
- Iterates through audio buffer, calculating positions based on current frame and audio level.
- Draws shapes (circles and rectangles) based on audio level, using left and right channels.
- Increments control variables for movement.
3. Fireball Class:
- Represents a fireball with position, size, speed, and color.
- Updates size and position, randomizing if beyond screen.
- Displays fireball as a semi-transparent ellipse with a red stroke.
4. FireParticle Class:
- Represents a rising fire particle with position, speed, size, and color.
- Updates position by moving upwards, wrapping around if off-screen.
- Displays particle as an ellipse with a color from yellow to orange.

### Water
she? stuff
remeber to add a pic and explain your code

### Air

![An image](images/Air.png)

This Java program creates a 2D audio visualization using the Processing library and Minim audio library. The program displays hexagons that change size and color based on the audio levels of "Avatar.mp3". The code utilizes two other classes, ShapeDraw.java and Lightning.java, to visualize a hurricane of hexagons that produce lightning.

The properties of Air.java are spinAngle, motion, motionSpeed, and speed. These variables control the movement and speed of the hexagons dynamically. There is an instance of the Avatar class and Lightning class.

I have constructed the AirVisual class with an instance of the Avatar class and also created an instance of the Lightning class, passing the Avatar instance to it, which allows the Lightning class to be used in the visualization.

In the draw method, we first call the lightning method that creates lightning from the top of the screen to the bottom and draws a lightning bolt. We set up the background with a black transparent rectangle that allows better transitions through the frames.

Using a loop of the buffer size, the color is set to use HSB colors to be more vibrant. We calculate the amplitude of the left audio channel and map it to a value that determines the brightness of the color, making the visualization react to the music's volume. We call the method ShapeDraw that draws hexagons and places them using the amplitude of the left level of the song and the buffer size. 

How the method ShapeDraw draws hexagons is through a loop that iterates through angles from 0 to 360 degrees with increments of 60 degrees. Each pass adds another line hexagon. For each angle, the method calculates the x and y coordinates of a vertex. The calculations use the cosine and sine of the angle converted to radians multiplied by the radius. This determines how far from the center point (centerX) each vertex is placed, and a line is drawn to each vertex, closing the shape. The hexagons are then rotated around the z-axis. The motion variables (motion, x, y) are updated to make the hexagons move. They gradually translate across the screen in a circular path due to the sine and cosine wave functions. The spinAngle and motionSpeed are incremented to update the movement and rotation over the period of the song.






# What I am most proud of in the assignment

### Emma
### Sean
### Muhammad Z

### Ismail

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)


