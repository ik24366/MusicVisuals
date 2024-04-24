# Music Visualiser Project


| Name                  | Student Number    | Music Visuals | Github users|
|-----------            |-----------        |-----------	|---|
|Muhammad Zayan Zahid	|C22480092 			|Air  			|momo858|
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
your stuff
remeber to add a pic and explain your code

![An image](images/Earth.png)

### Fire
![An image](images/fire.png)
1. Properties:
- amplitude: Stores the audio level.
- fv: Reference to the main program.
- fireballs: Array for simulating fireballs.
- fireParticles: Array for simulating rising fire particles.
- n1 and n2: Control variables for movement.
2.Constructor:
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

This java Program creates 2D audio visualization using the Processing library and Minim audio library. The program displays Hexagons that change size and color based on the audio levels of Avater.mp3, The code uses two other classes ShapeDraw.java and Lightning.java to visualise a hurican of hexagons that produce lightning.
The properties of Air.java are spinAngle a variable used to control the rotation angle, motion a float variable used to add dynamic motion, motionSpeed used to contole the speed of the motion 


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


