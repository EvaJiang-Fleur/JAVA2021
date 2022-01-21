## Introduction to Objects
### <a href='https://web.microsoftstream.com/group/2ab518ed-5a83-4c36-bfef-c8a2bf702e79?view=videos' target='_blank'> Weekly Briefing ![](../../resources/icons/briefing.png) </a>
### Task 1: Introduction
 <a href='01%20Introduction/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='01%20Introduction/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Welcome to this first practical workbook for the Object Oriented Programming with Java unit.
The aim of these workbooks is to present key theoretical concepts that underlie the unit and 
give you the opportunity to apply these in practical exercises.

Where you see the blue "Slides" and "Video" buttons at the top of a section, 
you should view these _before_ attempting the practical activities described in that section. 
You should do this now for this current task - this will present an introduction to
Java and provide you with a high-level understanding of the nature of Object Orientation.
Note that it is easier to "clone" these workbooks and view them locally on your own computer
(rather than trying to browse them online via the GitHub website).

One of the reasons we focus on Java in this unit is that it is a very popular and marketable
programming language. Before moving on to the main tasks in this workbook, you might like to watch 
<a href="https://www.youtube.com/watch?v=Og847HVwRSI" target="_blank">this animation</a>
to see how Java's popularity has changed over the years.
  


# 
### Task 2: Key Concepts
 <a href='02%20Key%20Concepts/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='02%20Key%20Concepts/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

In the previous teaching block, you learnt to program in C (to some extent ;o). In this unit however, you will be using **Java** and as such the _organisation_ of your code _should_ be very different. There are a number of key concepts that are fundamental to **Object Oriented** code. Before we progress to any hands-on practical exercises, it is important that you have an appreciation of these concepts. View the slides and video above to gain an understanding of these characteristics of Object Oriented code. At this stage, we only introduce the concepts at a very high level - we will revisit them again in later sections of this workbook (and indeed, in later workbooks).  


# 
### Task 3: Hello World
 <a href='03%20Hello%20World/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='03%20Hello%20World/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

The first program that you write in _most_ languages is "Hello World". Take a look at the slides and video above that describe "Hello World" in Java. 
Create a new Intellij project and then copy and paste the code from the above slide into the `main` method of your project. 
A number of video tutorials have been provided to walk you through installing Intellij and creating a new blank project.
Some of these steps are platform specific, so we have provided separate videos for 
<a href="https://mediasite.bris.ac.uk/Mediasite/Play/eb4047f525c642de8b4bef98c006c2c21d" target="_blank">Ubuntu</a>, 
<a href="https://mediasite.bris.ac.uk/Mediasite/Play/da355bec145b4c7fa2940738340a454a1d" target="_blank">Windows</a> and
<a href="https://mediasite.bris.ac.uk/Mediasite/Play/8822c0d46676424497d55a11ac01f8e21d" target="_blank">Mac OSX</a>.

Make sure your code compiles and runs correctly before moving on to the next task in this workbook.  


**Hints & Tips:**  
Note: In the videos we illustrate the installation process with Java 11 - it is probably wise to use the most recent version of java (17).  

Note: The lab machines already have Intellij installed, so you can skip the initial download and installation steps
(although it is still worth updating Java to version 17 during project setup !).  


# 
### Task 4: Build Systems
 <a href='04%20Build%20Systems/deep/maven.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a>

Now that you have some experience of creating Intellij projects, it is worth spending a bit of time exploring the related topic of *build systems*.
Introducing this concept very early on in the unit may seem a bit odd, but they are a core part of most major software projects (be they Open Source projects or in-house proprietary development work). Build systems are so important that in many big projects there is a dedicated engineer who handles build and release related tasks (look up "DevOps" if you don't believe us !).

You may have used *GNU Make* previously on this degree programme: this software saves you from having to type out the compile/link commands every time you want to build your software. `Make` takes care of running all the required build commands (as specified in the `Makefile`) and intelligently works out what needs to be done (by checking the modification times of all the files involved).

`Make` however isn't commonly used for Java development. This is partly due to the fact that building Java projects usually involve much more complicated activities than just running a set of commands. In addition to this, Java is all about creating cross-platform software - so we need to avoid the use of platform specific commands often used by `make`. For these reasons, we will instead be making use of the **Maven** build system. Take a look at the PDF document linked to above if you'd like to find out more about Maven.

In future tasks and workbooks, you will be given templates in the form of Maven projects. These contain all of the rules and dependencies required to build a project. One of the main benefits is that these projects can be imported directly into Intellij (or any other IDE for that matter). This saving us a lot of time installing required libraries and setting up the build environment.  


# 
### Task 5: Classes and Objects
 <a href='05%20Classes%20and%20Objects/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='05%20Classes%20and%20Objects/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

**Classes** and **Objects** are fundamental building blocks of Object Oriented code.
View the slides and video above to see an overview of these constructs.  

At the end of the video a question is posed - see if you can work out the answer to this problem
(Note that the green "substring" at the bottom of the last slide _isn't_ the solution, but was a _link to the solution_ for use during the lecture).
Once you have an idea of what the output should be, hover over this <img src="answer.jpg" title="The substring is: 01" style="vertical-align:bottom" />
button to reveal the actual solution.

Once you are happy with the concepts presented in the lecture, attempt the following practical activity to test your knowledge:
- Add a new Java Class file to your project: Select `New > Class File` using the popup menu of the `Main` file (found in the `src` folder)
- Name your class `Triangle`: we will use this class will represent a two dimensional triangular shape !
- Add a single constructor method: that takes three integer parameters (the length of each side of the triangle)
- Add a method called `getLongestSide`: that returns the length of the longest side of the triangle

Inside your project's `main` method, use the following code to create a new instance of the `Triangle` class and print out its longest side:

``` java
Triangle testTriangle = new Triangle(5, 7, 9);
int longestSide = testTriangle.getLongestSide();
System.out.println("The longest side of the triangle is " + longestSide);
```

Create a number of different instances of `Triangle`, each with different length sides
and check that your `getLongestSide` method operates as expected for each instance.

Notice how in the above code you can "glue" separate Strings together in Java by using the `+` concatenation operator.
Java will even let you concatenate different types together (in the above example, a `String` and an `int`).

Now use this knowledge to add a `toString` method to your Triangle class that returns a String describing the Triangle
(e.g. "This is a Triangle with sides of length 4, 5, 7")
  


# 
### Task 6: Inheritance and Polymorphism
 <a href='06%20Inheritance%20and%20Polymorphism/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='06%20Inheritance%20and%20Polymorphism/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='06%20Inheritance%20and%20Polymorphism/deep/segment-1.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a> <a href='06%20Inheritance%20and%20Polymorphism/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

View the slides and video above to gain an understanding of the concept of **Inheritance**. Then attempt the following task which will add to your existing project. Note that the "PRO" slides and video aren't _required_ to complete this exercise, but provide some deeper and more complex information regarding the concept of inheritance for those of you who are interested (the code fragments from the "PRO" slides can be found in <a href="pro-code.zip" target="_blank">this zipfile</a>).

There are many different types of shape in the world (in addition to just triangles !). We have created a <a href="Intellij Template/" target="_blank">Maven template project</a> for you that contains a simple hierarchy of shapes including a <a href="Intellij Template/cw-shapes/src/main/java/edu/uob/Circle.java" target="_blank">Circle</a> and a <a href="Intellij Template/cw-shapes/src/main/java/edu/uob/Rectangle.java" target="_blank">Rectangle</a>. As you can see by reviewing the code for these classes, both share the same super class called
<a href="Intellij Template/cw-shapes/src/main/java/edu/uob/TwoDimensionalShape.java" target="_blank">TwoDimensionalShape</a> (from which all 2D shapes will inherit).

Download the <a href="Intellij Template/" target="_blank">Maven template project</a> and open it in Intellij. You will see that there is already a basic `Triangle` class file inside the `src` folder (you might need to hunt for it !). Once you have found the file, paste in the code that you wrote in the previous task. Then integrate the `Triangle` class into the class hierarchy by using the `extends` keyword at an appropriate place in the `Triangle` file to link it to the `TwoDimensionalShape` class.

The slides and video linked to above above also discussed the notion of **Polymorphism**. Let us explore this concept in your code: inside the project's `main` method, create a variable that can hold an instance of the `TwoDimensionalShape` class. Try to fill this variable with each type of shape in turn (`Circle`, `Rectangle` and `Triangle`). Each time, print the content of the variable - just to show what type it currently holds.  


**Hints & Tips:**  
If you try to print out an instance of a class by passing it to `println`, it will call the `toString` method of that object in order to get a printable string. Lucky you wrote just such a method for your `Triangle` class in the previous task of this workbook !  


# 
### Task 7: Encapsulation
 <a href='07%20Encapsulation/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='07%20Encapsulation/slides/segment-2.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='07%20Encapsulation/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='07%20Encapsulation/video/segment-2.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

As you will see from the slides and video linked to above, **Encapsulation** is another key concept in Object Oriented programming. Let us explore this notion of encapsulation in more depth by adding some internal state to our project. In addition to a number of edges, all shapes also have a colour. Add a new variable to the `TwoDimensionalShape` class that allows the shape's colour to be stored.

To help you in this task, we have provided you with a <a href="Intellij Template/cw-shapes/src/main/java/edu/uob/Colour.java" target="_blank">Colour</a> type. This file is already part of the project (so you won't need to add it manually) and can be used in the following way:

```java
Colour firstColour = Colour.RED;
Colour secondColour = Colour.YELLOW;
Colour thirdColour = Colour.WHITE;
```

By implementing this variable as `private` you successfully encapsulate the colour of the shape inside the object.  


# 
### Task 8: Controlling Access
 <a href='08%20Controlling%20Access/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='08%20Controlling%20Access/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='08%20Controlling%20Access/deep/segment-1.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a> <a href='08%20Controlling%20Access/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

It is no good being able to store the colour of a shape if there is no way to access it ! After all, how would we draw a shape if there was no way to find out what colour it was ? Take a look at the slides and video above to find out why we need to be careful when providing access to encapsulated data. Using this knowledge, add `setColour` and `getColour` methods to the `TwoDimensionalShape` class.

As programmers, we have a lot of control over the access to our variables, methods and classes. Check out the "PRO" slides and video above for more details on the various options available.
  


# 
### Task 9: Building in the Terminal


In this workbook, we have spent the majority of the time exploring the use of Intellij.
At this stage however, it is worth spending a little bit of time taking a look at how we can build and run
Maven projects on the command line. This is important because the assessed exercises later on in the
unit will be marked on the command line and you need to be able to make sure that your code will actually build and run.

On your command line, change into the project root folder (the directory where the `pom.xml` file resides) and type one of the following commands (depending on your platform):

    Linux/OSX:   ./mvnw clean compile
    Windows:     mvnw clean compile

**Note**: On some unix systems, you _might_ need to make `mvnw` executable first
(i.e. by running `chmod +x mvnw`)

The command is doing 2 separate things in order:

1. `clean` makes sure that everything will be freshly built by cleaning out (deleting) all previously generated files
2. `compile` compiles and builds all files and resources (stopping if any build error occurs)

Note that you can learn more about these and other build tasks in the <a href="https://maven.apache.org/guides/getting-started/index.html">Maven documentation</a>.

The first time you run this command, it might take a while to complete so be patient. This is because Maven may need to download project dependencies from the web (obviously if you haven't got a working internet connection Maven may well fail at this point !)
If successful, the output should looks something like this:

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 42 s
    [INFO] Finished at: 2022-01-01T00:00:00Z
    [INFO] Final Memory: 42M/420M
    [INFO] ------------------------------------------------------------------------

If you get a `BUILD FAILURE`, it means the project failed to complete some of the specified tasks.
If this is the case, it might make sense to go back into IntelliJ and make sure no code issues are detected
(i.e. no code is highlighted in red and the project can still be built and run).
Fix any problems in IntelliJ and then return to the command line.

Once you have successfully built your code on the command line, you can run your program using:

    Linux/OSX:   ./mvnw exec:java
    Windows:     mvnw exec:java

Note that this will run the `main` method from the `Shapes` class - if you want to run the main method of _another_ class,
you can edit the project's `pom.xml` file and change the `mainClass` to be the desired class.
  


# 
### Task 10: Final Thoughts


It is important to note that the `StyledString` examples shown in the lecture slides and discussed in the videos are just a _theoretical_ example. We have targeted the `String` class because this is consistent with your current level of knowledge and understanding of the Java language. In reality Java does not let us manipulate the styling of the `String` class in this way and we would have to resort to other, more sophisticated classes to achieve the desire results.
  


# 
