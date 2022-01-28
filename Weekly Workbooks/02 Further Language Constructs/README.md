## Further Language Constructs
### <a href='https://web.microsoftstream.com/group/2ab518ed-5a83-4c36-bfef-c8a2bf702e79?view=videos' target='_blank'> Weekly Briefing ![](../../resources/icons/briefing.png) </a>
### Task 1: Introduction


The aim of this workbook is to introduce further concepts and constructs from the Java programming language.
In order to support this, we will continue working with the "triangle" exercise from last week,
which you will extend and enhance with additional features and functionality.
As before, each section in this workbook provides some teaching materials that introduces a concept,
as well as an activity to undertake that will allow you to apply your freshly-gained knowledge. 
  


# 
### Task 2: Abstract
 <a href='02%20Abstract/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='02%20Abstract/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

An inspection of the code inside the `TwoDimensionalShape` class (found in `src/main/java/` folder of the Maven project)
will reveal that the class has two **abstract** methods.
These are methods _without_ implementations - they have signatures ("prototypes"), but no internal code.
View the slides and video lecture above to find out more about the notion of **abstract** and the reasons why we might want to use this feature of Java.

Now, inside your `Triangle` class, write complete `calculateArea` and `calculatePerimeterLength` methods so that each performs a suitable
calculation and returns an appropriate result.
These two methods will **override** the abstract (blank) methods defined in the `TwoDimensionalShape` class.

The reason why these two methods were originally defined as **abstract** in the `TwoDimensionalShape` class is as follows...
All shapes _will_ have an area and perimeter length, however: each shape will _calculate_ these differently depending of the shape being represented
(Circles this is done using their radius and Pi, with Rectangles this is down using their width and height etc.)
As such, we can't write anything _generally useful_ inside the `TwoDimensionalShape` class - we have to leave
everything down to the subclasses. That said, it is still worth defining that all shapes have area and perimeter calculations
(even if they have to be left blank in the `TwoDimensionalShape` class).

Once you have implemented area and perimeter calculation methods for your `Triangle` class, create a few
`Triangles` with different length sides and call your freshly created methods to make sure they work correctly.
  


**Hints & Tips:**  
Calculating the perimeter length of a triangle is easy.  
In order to calculate the area you might like to use
<a href="https://www.mathsisfun.com/geometry/herons-formula.html" target="_blank">Herons Formula</a>.  


# 
### Task 3: Enumerations
 <a href='03%20Enumerations/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='03%20Enumerations/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='03%20Enumerations/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

Some shapes (such as Circles) come in only one variant - a Circle is a Circle !
Other shapes however (Triangles for example) can come in range of different variants (Isosceles, Right-angle, Equilateral etc).
We _could_ create a range of subclasses of the `Triangle` class to represent each of these...
However this might be seen as overkill, since the various sub-classes wouldn't have any additional attributes or methods,
so we could end up with a lot of almost empty files (causing unnecessary clutter in our file system !)

Instead, we will use this opportunity to explore another feature of Java - **enumerations**.
In Java there is an enumeration mechanism (very much like that in C).
This allows us to declare a predefined list of values that a variable can hold.
Take a look at the slides and videos above (from our guest presenter !) to find out more details regarding enumerations,
then attempt the task described below.

With the knowledge you have gained from these slides and videos, add to your `Triangle` Class to make it "self-aware":
so that instances of the class will _know_ what kind of triangle they are.
As a programmer, we aren't going to actually _tell_ the triangle what variant it is, the triangle will work it out for itself
(based on the length of the sides passed into the constructor).
Add some code to your `Triangle` constructor method so that it works out what kind it is and stores the result in a private variable.
We have provided you with an enumeration called `TriangleVariant` (found in the `src/main/java/` folder)
that can be used to differentiate between the various triangle variants.

You will get the opportunity to fully and systematically test your variant detection code in a later section.
For the time being, just try to make sure your code works with a few different test triangles.
  


**Hints & Tips:**  
Perhaps the simplest way to approach this task is to create a multi-branch IF statement that will classify the triangle. The order of the branches of the IF statements will be very important: you should check for specific/special cases at the top, with more general cases at the bottom (you'll soon understand why later !)

For some triangle variants, you might find it useful to make use of the `getLongestSide` method your wrote previously !
  


# 
### Task 4: Interfaces
 <a href='04%20Interfaces/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='04%20Interfaces/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

As we have discussed previously, some shapes have a number of variants,
whilst others (such as Circles) have only one. 
It would be useful to have some kind of "marker" that indicates
whether or not a particular shape has multiple variants.

One way to do this is using a Java **Interface** - this is a mechanism, independent of the class hierarchy,
that allows us to "mark" classes as having particular characteristics. You can think of an Interface as a "contract"
that the class "conforms to" - guaranteeing to provide certain properties and features.
Take a look at the slides and video above for more detailed information about interfaces.

As an illustration of interfaces in action, we have provided a `MultiVariantShape` interface in the Maven
project that can be used to indicate that a shape has more than one variant.
We can mark our `Triangle` class as a shape which has multiple variants using this interface like so:
``` java
class Triangle extends TwoDimensionalShape implements MultiVariantShape
```
The use of the keyword `implements` indicates that this class conforms to the properties and features of the `MultiVariantShape` interface.

Elsewhere in our code, we can test to see if a particular object conforms to an interface by using the `instanceof` keyword
(I know that this is not a particularly suitable name, but it is reused from another part of Java).
We can test any shape object to see if it implements the `MultiVariantShape` interface using the following code:
``` java
if(shape instanceof MultiVariantShape) System.out.println("This shape has multiple variants")
else System.out.println("This shape has only one variant")
```

You may have noticed that the `MultiVariantShape` interface defines a method called `getVariant`
that should return the particular variant of a shape. This allows code outside of the class to interrogate an instance
in order to find out which variant it is. You will also notice that in the interface definition, their is no code that
provides an implementation of this method - it your job as the programmer's to write this !
Let's do this now by adding a method to your `Triangle` class called `getVariant` that returns the `TriangleVariant` that you
calculated and stored in the previous task.
  


# 
### Task 5: Systematic Testing


You have by now implemented a fair bit of functionality in your code.
However, the chances are that it is not _totally_ fault free ;o)
It is a good idea for us to take a pause from adding new features for a moment
and fix any problems or issues that may currently exist in our code.

We have provided a <a href="Test Script/TriangleTests.java" target="_blank">triangle test file</a>
that contains a variety of test cases to help you comprehensively and systematically test your code.
This test file makes use of a testing framework called **JUnit** (which you will learn more about in one of your other units !)

You should drop this file into the `src/test/java/edu/uob` folder in your project (so that Intellij can find it !).
Then right-click on the file in the Intellij project explorer and select `Run 'TriangleTests'` in the popup menu in order to run the tests.
Alternatively, you can run individual test methods by opening the `TriangleTests` file in the Intellij editor and then
clicking on the green "play" button to the left of the method name.
Once the tests have finished running, you should see a report detail which have passed and which have failed.

When testing your Triangle code, you should focus on each type of triangle in turn (first Equilateral, then Isosceles, then Scalene etc.).
Make sure your code passes all of the tests for that variant before moving on to the next.
The final group of tests (to do with overflow) are more difficult, so you should leave those until the end.
Note that these final tests may require you to refactor your code a fair bit (depending on how you wrote it in the first place) !  


# 
### Task 6: Testing in the Terminal


In some situations (e.g. during the coursework marking process) it is useful to be able to run the test cases from the command line
(rather than having to start up Intellij). In order to run all of the unit tests present within a Maven project,
change into the project root folder in your command prompt and type:

    Linux/OSX:   ./mvnw clean test
    Windows:     mvnw clean test

If all unit tests have been passed, you should get a `[INFO] BUILD SUCCESS` message at the end of the output.

If on the other hand some tests were failed, the output should look something like the following:

    Tests run: ?, Failures: ?, Errors: ?, Skipped: ?

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD FAILURE
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 42 s
    [INFO] Finished at: 1970-01-01T00:00:00Z
    [INFO] Final Memory: 42M/420M
    [INFO] ------------------------------------------------------------------------
    [ERROR] Failed to execute goal test (default-test): There are test failures.

  


# 
### Task 7: Multiple Inheritance (optional)
 <a href='07%20Multiple%20Inheritance%20%28optional%29/deep/segment-1.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a> <a href='07%20Multiple%20Inheritance%20%28optional%29/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

Just as an aside, let us think about _multiple_ inheritance.
In some situations, it might be useful for a class to have multiple parents/superclasses.
This would allow it to derive methods and attributes from numerous different sources.
This is however dangerous - take a look at the slides and video above to find out why !

Java's solution to this is to only allow single inheritance, but to augment this with interfaces.
Although a bit of a compromise, this mechanism allows us to gain _some_ of the benefits of multiple inheritance, whilst avoiding many of the main pitfalls.  


# 
### Task 8: Arrays
 <a href='08%20Arrays/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='08%20Arrays/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Common to most programming languages, Java provides an **array** data structure (similar in many ways to that found in C). In Java, arrays can hold primitives (e.g. `int`, `float`, `char`, `boolean` etc)
but ALSO complex Objects (such as `Strings`, `Robots` or even `Triangles`) !

Take a look at the slides and video above for more information on using arrays in Java.
Then (in your main method) create an array of size 100 that can hold `TwoDimensionalShapes`.
Fill this array with randomly chosen shapes (`Circles`, `Rectangles`, `Triangles`).

Once you have filled the array with 100 shapes, loop through it and print out the details of each shape in turn.
At the end of your main method, print out a summary of how many `Triangles` were in the array.
In order to do this, you will need to make use of an integer counter variable to keep a tally of the number of triangles encountered.
You might also like to make use the `instanceof` operator to check the type of each instance in the array !

  


**Hints & Tips:**  
In order to fill your array with a random selection of shapes, you may like to make use of the Java `random` method:

`Math.random()`

This will return a randomly selected double precision floating point number (between 0.0 and 1.0).
You will need to write an IF statement and use some cunning maths to look at this number and decide which shape to create.  


# 
### Task 9: Class Variables and Methods
 <a href='09%20Class%20Variables%20and%20Methods/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='09%20Class%20Variables%20and%20Methods/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

In the previous task, we counted the number of `Triangles` in the array by looping through it.
But there is an alternative way to achieve the same objective (without putting code inside the loop).
We could maintain a counter of how many `Triangles` have been created _inside_ the Triangle class
and increment this each time a Triangle is instantiated.
This would encapsulate the "population" count inside the class itself - which is nice.

But there is a problem this with approach - we only want _one_ counter for the whole program
(not a counter inside _each and every_ `Triangle` instance). For this reason, we can't just use a "normal" variable.
View the slides and audio narration above to gain an understanding of the difference
between **instance** and **class** variables. Then add an integer **class** variable to your `Triangle` class to
keep track of how many `Triangles` have been created (don't forget to increment this in the constructor !).
Also add a `getPopulation` method to your `Triangle` class that returns the current value of this counter variable,
so that it can be accessed from outside the class.

Update your main method, so that it prints out the number of `Triangles` that exist using
this class variable population counter _as well as_ the previous counter from inside the loop
(just to make sure both counters agree !)

Just as we can create **class variables** that are associated with the class (rather than each instance),
we can also write **class methods** which are similarly associated with the class (rather than the instances).
You have already encountered just such a class method (namely the `random` method of the `Math` class).
Have a think about the reasons why you might wish to write a class method, rather than an instance method.
Considering the `Triangle` class - might some of its methods be better suited to being class methods,
rather than instance methods ?
  


# 
### Task 10: Casting


Let's imagine that we wanted to find out the variant of a `Triangle` that is stored in the array.
We would need to be very careful about how we went about interrogating the elements of that array.
We can't call `getVariant` on just any element, since not all objects will actually _be_ a `Triangle`
(it might be a `Circle` for example, which doesn't support the `getVariant` method !)
Java will in fact block us from trying to call `getVariant` on a `TwoDimentionalShape`
(try it and see !).

To solve this problem, Java allows us **cast** objects from one class into a more specific class.
For example, we can "down-cast" (or "narrow-cast") any `TwoDimentionalShape` into one of its sub-classes.
We can then quite happily call methods of that subclass on the instance.
For example, in the code fragment below we extract the first element from the array and convert it into a `Triangle`:

``` java
TwoDimentionalShape[] shapes = new TwoDimentionalShape[100];
...
TwoDimentionalShape firstShape = shapes[0];
// Down-cast the shape into a triangle
Triangle firstTriangle = (Triangle)firstShape;
TriangleVariant variant = firstTriangle.getVariant();
```

The down-casting code above asks Java to "trust" that the `firstShape` is a `Triangle`.
As a consequence of this, we can then treat this object as though it were a `Triangle` and thus call
any method of the `Triangle` class on it.

This is fine, _provided_ that the shape at element 0 of the array _is actually_ a `Triangle`.  
But what happens if it _isn't_ ?  
What if it is a `Circle` instead ?  
Will Java let us convert a `Circle` into a `Triangle` in this way ?

Why not try it and see what happens !
  


# 
