## MVC and Collections
### <a href='https://web.microsoftstream.com/group/2ab518ed-5a83-4c36-bfef-c8a2bf702e79?view=videos' target='_blank'> Weekly Briefing ![](../../resources/icons/briefing.png) </a>
### Task 1: Introduction


The next two workbooks will lead you through an assessed exercise that has a weighting of 20%
(i.e. it WILL contribute to your unit mark).
The focus of this assignment is to build an interactive, graphical, playable computer game.

You are encouraged to discuss assignments and possible solutions with other students.
HOWEVER it is essential that you only submit your own work.

This may feel like a grey area, however if you adhere to the following advice, you should be fine:

- Never exchange code with other students (via IM/email, GIT, forums, printouts, photos or any other means)
- Although pair programming is encouraged in some circumstances, on this unit you must type your own work !
- It's OK to seek help from online sources (e.g. Stack Overflow) but don't just cut-and-paste chunks of code...
- If you don't understand what a line of code actually does, you shouldn't be submitting it !
- Don't submit anything you couldn't re-implement under exam conditions (with a good textbook !)

If you ask a question on a discussion forum, try to keep discussion at a high level
(i.e. not pasting in chunks of your code). If it is unavoidable to include code, only share
small snippets of the essential sections you need assistance with.

An automated checker will be used to flag any incidences of possible plagiarism in submitted code.
If the markers feel that intentional plagiarism has actually taken place, marks may be deducted.
In serious or extensive cases, the incident may be reported to the faculty plagiarism panel.
This may result in a mark of zero for the assignment, or perhaps even the entire unit
(if it is a repeat offence).
Don't panic - if you stick to the above list of advice, you should remain safe !  


# 
### Task 2: Game Overview


Your aim in this assignment is to build a digital version of the classic turn-taking game
"Noughts and Crosses" / "Tic-Tac-Toe" / "OXO". You will NOT however be required to construct the _entire_ game.
The Graphical User Interface (see screenshot below) and the core Data Model classes will be provided for you.
You will however be required to write the "Controller" that handles all of the game logic.
  


![](02%20Game%20Overview/images/game.jpg)

# 
### Task 3: Model-View-Controller
 <a href='03%20Model-View-Controller/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='03%20Model-View-Controller/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

The Model-View-Controller (MVC) "pattern" is a common structural convention that is used widely in the development of
interactive systems. To help get you started in this assignment, you have been provided with an <a href="cw-oxo/" target="_blank">MVC template</a> for the OXO project !

Pull a copy of this template from the GitHub repository and then open the project in Intellij.
Take a look at the slides and video above for an introduction to the concept of the Model-View-Controller pattern,
then use the knowledge gained to explore the OXO game template.

The `OXOModel` class contains all of the core data structures required by the game - you can use the public methods
provided by this class in order to manipulate the following internal state:

- The number of cells in a row required to win the game (3 in a standard game)
- The set of players currently playing the game (2 in a standard game !)
- The player whose turn it currently is
- The "owner" (player who has claimed) each cell in the game grid
- The winner of the game (when the game ends)
- Whether or not the game has been drawn (all cells filled, but with no winner !)

The "Rendering Logic" has already been implemented for you: any changes to the state of the `OXOModel`
will be automatically rendered in the `OXOView`. 
Your main task is to implement the "Event Handling Logic" in the `OXOCOntroller` class.
Your code will need to manipulate the state of the `OXOModel` by calling appropriate methods on it.
You will however NOT need to interface directly with the `OXOView` class.

You may alter and add to the `OXOController` and `OXOModel` classes, however 
you should NOT change the signatures of any of the _existing_ methods
(if you change the method names or parameters, you may break the marking scripts used to assess your work after submission !)
You should NOT alter the `OXOView` class at all.
The remaining tasks in this week's (and next week's) workbooks will lead you through the features that you need to implement to complete the assignment.

Finally, it is very important to remember that the `OXOModel` class should be used to maintain ALL game state.
You should definitely NOT be storing any game state in your `OXOController` class - bad things will happen if you do !  


# 
### Task 4: Responding to Input
 <a href='04%20Responding%20to%20Input/video/oxo.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

The first step in creating a fully operational game is to get your program to respond to input from the user.
Players will take it in turns to enter desired cell position into the `OXOView` GUI window as demonstrated in the video above
(note there is no audio narration in this particular video !).
`OXOView` deals with reading in keypresses from the user and will then pass the inputted command to the `OXOController`
(via a call to the `handleIncomingCommand` method).
Your task is to interpret this incoming command and update the game state accordingly.

An inputted cell position consists of the row letter and the column number of the cell a player wishes to "claim".
For example, if a user wished to claim the centre cell of a 3x3 board, they would type: `b2`.

Once one player has entered a command and the board has been updated, play should switch to the next player.
Note that the order of play should be the same as the order that players were added to the model
(i.e. the first player added to the game will take the first go, followed by the second etc.)

When updating the game state, you should set the "current player" to be the player _whose turn it is next_.
This is to ensure that the view shows graphically the player _who is about to take a turn_.

It is possible that the user may make a mistake when interacting with the system and enter a "bad" cell identifier.
Do not try to deal with these here - we will address error handling in the next workbook.
For the time being, you may assume that any cell identifiers entered by the user are always valid.

You should test your code by inputting a series of cell identifiers into the `OXOView` window and check to make sure
that the board updates as expected. Note that at this stage in the exercise, "win detection" has not yet been implemented,
so as a result the game will never end !  


# 
### Task 5: Need for Flexibility
 <a href='05%20Need%20for%20Flexibility/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='05%20Need%20for%20Flexibility/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Currently the core data structure of the `OXOModel` class is a 2D array.
This allows us to maintain a data representation of the current state of the board at any particular time.
There is however a problem in that this constrains us to a particular board size (i.e. 3x3).
It would be nice to be able to alter the size of the board _during_ a game !
For example, if during a game it became clear that there was going to be a draw
(all the blank cells were being used up and no one was going to win)
it would be nice to be able to increase the board size in order to allow play to continued
(and hopefully a winner might eventually triumph !)

Luckily there are a number of dynamic data types (such as Queues, Stacks, Lists etc.)
provided by some of the core Java libraries that allow us to store _dynamically_ sized groups of objects.
Take a look at the slides and video above to gain an understanding of these data structures and the problems
and challenges involved in using them.  


# 
### Task 6: Dynamic Board Size
 <a href='06%20Dynamic%20Board%20Size/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='06%20Dynamic%20Board%20Size/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='06%20Dynamic%20Board%20Size/deep/segment-1.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a> <a href='06%20Dynamic%20Board%20Size/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

One solution to the problems identified in the previous section is a Java mechanism called **Generics**. This mechanism allows us to designate a particular compound data structure to hold a specific object type. This allows us to make use of untyped data structures, whilst at the same time enforcing type checking at compile time. Java provides a number of very useful compound data structures in the _Collections_ package that make use of the generics. Watch the video linked to above for an introduction into the _Collections_ package.

Use this knowledge to convert the board grid data structure in the `OXOModel` class from an Array to an
<a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html" target="_blank">ArrayList</a>.

In order to fully implement dynamic board size features, you will need to:
- Change your `cells` variable from a 2D array into some `ArrayLists`
- Alter the OXOModel constructor to initialise your `ArrayLists`
- Update the cell owner "getter" and "setter" methods to operate on the `ArrayLists`
- Add two methods to `OXOModel` called `addColumn()` and `addRow()` that allow the board size to grow
- Add two methods to `OXOModel` called `removeColumn()` and `removeRow()` that allow the board size to shrink

You should make your code as robust as possible, preventing the data held in the model from getting into any undesirable states.  


**Hints & Tips:**  
In order to test out dynamic board size, the `OXOView` class contains interaction features
that allow the user to change the size of the board. If the user **left** clicks their mouse on either the grey column or row labels, that dimension will expand. If the user **right** clicks their mouse (or `control` click on a Mac) on either the grey column or row labels, that dimension will shrink. Note that clicking with the mouse will call the add/remove row/column methods of your `OXOController`, which you will need to update so that they call the relevant methods of the `OXOModel`.  


# 
### Task 7: Win detection


There is no point playing the game if nobody can actually win !
Add code to your controller that will detect when a win has been achieved.
You should check for wins in all directions (horizontally, vertically and diagonally).
Horizontal, Vertical are relatively easy (Diagonals are a bit more tricky !)

If the game reaches a situation where all cells are filled, but no player has reached the win threshold,
the game should be considered a "draw" and the model updated to reflect this (by calling the appropriate methods).

You should attempt to make your `OXOController` as flexible and versatile as possible.
It should therefore be able to perform win detection on grids of any size (not just 3x3).

When a game has been won, the game should NOT exit - this is to allow the winner to glory in their victory.
It is however important that your controller should accept no further play commands
(i.e. no additional cells can be claimed).
This is to stop the loser carrying on playing after a game has already been won !  


# 
### Task 8: Automated Testing


The problem with manual testing is that it can be a very time consuming activity to undertake.
Imagine trying to test a "draw" state for a 10x10 board - this would involve taking 100 separate turns to reach the end of the game.
Now imagine having to test this draw state a number of times - this soon becomes very tedious.

This is where automated testing can become invaluable - we can test the "model" and "controller" components of the system by replacing the graphical "view" with an automated test script. The game can be extensively tested without clicking a single button of typing a single key.

We have provide a skeleton test script called `ControllerTests` that can be found in the `src/test/java/edu/uob` folder.
You will notice that the file has only a couple of test methods in it (indicated using the `@Test` annotation).
You should populate this test script with a comprehensive set of test cases that will fully test your game.

When viewing this file, you may have noticed a new test feature not previously encountered - the `@BeforeEach` annotation
is used to mark a method which will be executed _before_ any `@Test` methods are run.
This allows us to create a "setup" method that will initialise any instances that are required to successfully run a `@Test` method.

These kinds of test scripts are an essential element of Test-Driven Development (TDD) as discussed in the Software Engineering unit.
They help you to think about what the system needs to do in advance of implementation as well as allowing you to check for correct operation, 

They are not easy to come up with - writing a good set of tests is a difficult and time consuming activity.
This is because writing test cases requires you to perform "problem analysis" in order to understand the situation
and think about all the possible states of the system (something that we did for you in the Triangles exercise !).
Such analysis is an essential developer skill, as is the ability to document the outcomes of this analysis formally as a set of test cases.

  


# 
### Task 9: Final Thoughts


Next week we will continue working on this assessed exercise, adding a range of new features.
The next workbook will be released at the usual time during the week.
In the mean time, you should try to make your set of automated test cases as comprehensive as possible.  


# 
