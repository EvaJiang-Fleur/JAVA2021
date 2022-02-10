## Exceptions and Extensions
### <a href='https://web.microsoftstream.com/group/2ab518ed-5a83-4c36-bfef-c8a2bf702e79?view=videos' target='_blank'> Weekly Briefing ![](../../resources/icons/briefing.png) </a>
### Task 1: Introduction


In this workbook, we will extend the OXO game that you started last week.
The tasks in this workbook focus on handling errors and implementing extensions to the basic OXO game.

Just as a reminder:
you are encouraged to discuss assignments and possible solutions with other students. HOWEVER it is essential that you only submit your own work.

This may feel like a grey area, however if you adhere to the following advice, you should be fine:

- Never exchange code with other students (via IM/email, GIT, forums, printouts, photos or any other means)
- Although pair programming is encouraged in some circumstances, on this unit you must type your own work !
- It's OK to seek help from online sources (e.g. Stack Overflow) but don't just cut-and-paste chunks of code...
- If you don't understand what a line of code actually does, you shouldn't be submitting it !
- Don't submit anything you couldn't re-implement under exam conditions (with a good textbook !)

If you ask a question on a discussion forum, try to keep discussion at a high level (i.e. not pasting in chunks of your code). If it is unavoidable to include code, only share small snippets of the essential sections you need assistance with.

An automated checker will be used to flag any incidences of possible plagiarism in submitted code. If the markers feel that intentional plagiarism has actually taken place, marks may be deducted. In serious or extensive cases, the incident may be reported to the faculty plagiarism panel. This may result in a mark of zero for the assignment, or perhaps even the entire unit (if it is a repeat offence). Don't panic - if you stick to the above list of advice, you should remain safe !  


# 
### Task 2: Error Handling in Java
 <a href='02%20Error%20Handling%20in%20Java/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='02%20Error%20Handling%20in%20Java/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a> <a href='02%20Error%20Handling%20in%20Java/deep/segment-1.pdf' target='_blank'> ![](../../resources/icons/deep.png) </a> <a href='02%20Error%20Handling%20in%20Java/deep/segment-1.mp4' target='_blank'> ![](../../resources/icons/deep.png) </a>

It is more than possible that a user will at some point make a mistake and input a "bad" cell identifier
(for example, a cell that doesn't exist or one that has already been claimed by one of the players).
In Java handling these kinds of unintended run-time errors is achieved using a language feature called **Exceptions**.
This includes a set of special classes and an error handling mechanism that is part of the Java programming language.
View the slides and video above to gain an understanding of the concept of exceptions and to
see a demonstration of exceptions in action.

When making use of exceptions in our OXO game, we first need to appreciate the range of the different types of user
input error that are possible. These include the following:

- Already Taken: The specified cell exists, but it has already been claimed by a player
- Outside Range: The identifiers are valid characters, but they are out of range (i.e. too big or too small)
- Invalid Identifier Length: The entire identifier string is longer (or shorter) than the required two characters
- Invalid Identifier Character: The row is not a letter character or the column is not a number digit

To help you to handle using input errors in your OXO game, we have provided you with a set of exception classes that
can be used to represent each of the above. These exception classes are illustrated in the class hierarchy shown below
(note that the notations used in the diagram are from UML - something that you will be studying in the Software Engineering unit !)  


![](02%20Error%20Handling%20in%20Java/images/inheritance.jpg)

**Hints & Tips:**  
Make sure you have a solid grasp of these concepts before moving on to the next section. In the following tasks you will get the opportunity to use the exceptions illustrated above to implement error handling in your OXO game.
  


# 
### Task 3: Error Handling in Practice
 <a href='03%20Error%20Handling%20in%20Practice/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='03%20Error%20Handling%20in%20Practice/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Now that you have a high-level understanding of Java's exceptions mechanism and an appreciation of the range of input error a user might make, let us use this information to actually implement error handling in the OXO game.
Take a look at the slides and video linked to above to find out how to work with exceptions in Java.

With the knowledge gained, implement error handling in your controller class. Add appropriate input checking features to your `handleIncomingCommand` method to determine if the specified cell identifier is valid:
- If the identifier is VALID, mark the specified cell as being owned by the player.
- If the identifier is INVALID, prevent the move from taking place and instead instantiate and
"throw" the relevant exception (depending on the type of input error that has been made).

The `OXOGame` will "catch" any exceptions "thrown" and print out a relevant error message to the terminal/console as feedback to the user.

Test your program manually by playing a game and entering a range of erroneous inputs,
making sure that all errors are trapped and reported correctly.  


**Hints & Tips:**  
Note that in order to be able to access all of the OXO move exceptions introduced in the previous section, you will need to
`import edu.uob.OXOMoveException.*;` at the top of your controller class.

You may assume a maximum grid size of 9x9 (rows `a`-`i` and columns `1`-`9`)
so that a cell identifier can ONLY ever be two characters long.
Note that your code should accept upper as well as lower case row characters
(e.g. `A1` and `a1` are equivalent and both are acceptable).  


# 
### Task 4: Testing Exceptions


As we have discuss previously, automated testing is a key practice in Software Engineering.
It is time for you to start applying this practice more extensively in your own work.
To this end, you should add a set of additional test cases to your automated testing script
that will trigger (and then check) all of the different types of OXO move exception discussed in the previous section.

There are a number of different ways to do this - perhaps the simplest is to use the `assertThrows` assertion. You pass this assertion a method to call and an expected exception - if the method doesn't cause the exception, then Intellij will report that the test has failed. For example, we could check to make sure that the user input command `aa1` results in an `InvalidIdentifierLengthException` using the following code:
```java
assertThrows(InvalidIdentifierLengthException.class, ()-> controller.handleIncomingCommand("aa1"));
```

There are a couple of things to note about this code:  

Firstly, it is important that we pass in the `InvalidIdentifierLengthException` _class_ rather than an _instance_ (object) of that class.

Secondly, the expression `()->` might look a little bit strange, but it is in fact correct Java syntax !  
This element is called a **lambda** and it allows us to "bundle up" a method to pass into `assertThrows`:
- _WITHOUT_ `()->` the code above would run the `handleIncomingCommand` method first, and then pass the _result returned_ into `assertThrows`.
If an error is encountered, an exception will be thrown _before_ the `handleIncomingCommand` method returns, and as a result there will be _nothing_ to pass into `assertThrows`.
- _WITH_ `()->` the code above passes the entire `handleIncomingCommand` method into `assertThrows`, so that `assertThrows` can then call it.
If an error is encountered, an exception will be thrown _inside_ `assertThrows` so that it is able to detect and check that it is of the correct type.

A subtle, yet important distinction !  


# 
### Task 5: Test Driven Development


As we have discuss previously, Test Driven Development is a key practice in Agile development.
The remaining tasks in this workbook focus on implementing extensions to the basic OXO game.
As such, this is an ideal opportunity to practice Test-Driven Development in its truest sense
(i.e. writing tests _before_ writing the code to implement them).
For each remaining task, try to identify a comprehensive set of test cases _before_ you attempt to write any code.
You can then use these tests to support and drive your programming activity.
This will make the development process similar to the "Triangle Variant Classification" exercise you completed previously - except that this time YOU will write the test script yourselves !  


# 
### Task 6: Win Threshold


The "Win Threshold" is the number of cells in a row required to win a match (3 in a "standard" game).
It would make for a more interesting game if it were possible to change this threshold during a match.
To support this feature, the `OXOGame` class includes interaction features to allow the user to indicate the desired win threshold:
- Pressing the `+` key on the keyboard should increase the win threshold 
- Pressing the `-` key on the keyboard should decrease the win threshold 

Although the game is able to detect the pressing of the `+` and `-` keys, the internal win threshold is not updated.
The `increaseWinThreshold()` and `decreaseWinThreshold()` methods inside `OXOController` are called when the keys are pressed,
however these two methods are currently empty, so pressing the keys has no effect.
Add code to the `increaseWinThreshold()` and `decreaseWinThreshold()` methods in your `OXOController`
so that they alter the win threshold value held inside the `OXOModel` (by calling the relevant getter and setter methods).

You should make your code as robust as possible, preventing the data held in the model from getting into any undesirable states. You should also ensure that all game state is stored in the `OXOModel` and not in your `OXOController`.

Note that you are NOT responsible for checking that the win threshold is achievable
(i.e. that the board is wide or high enough to allow the win threshold to be reached).
If the user wishes to choose an impossible-to-achieve threshold, that is up to them - your controller shouldn't interfere.  


# 
### Task 7: More Players


Although two players in the traditional number in an OXO game, allowing additional players would make for a more interesting game.
As an extension to the standard game, add features to your code so that it can support any number of players.
To achieve this you will need to make changes to both the `OXOModel` as well as your `OXOController`.
You will need to use alternative player characters for the additional players - having more than one player who's letter is X
will lead to a lot of confusion !

You should NOT attempt to alter the `OXOView` or `OXOGame` to allow the _interactive_ setting of the number of players.
Instead, you should focus on testing multi-player games by creating additional test cases in your testing script.

Remember to try to ensure that your code is flexible versatile (don't hard-wire in any fixed values) !
  


# 
### Task 8: Submission


Remember that this assignment WILL be assessed and the mark will count towards your grade for this unit.
In order to submit your code, go to "Assessment, submission and feedback" section on Blackboard page for this unit.
It is essential that your code compiles and runs before you submit it
(otherwise we will not be able to run it to mark it !).
Make sure it is possible to build and run your project on the command line using the `mvnw` command.

It is VERY important that you do NOT change the name or parameters of any of the classes and methods that
you have been given. Scripts will be used to automatically test your code to make sure
it operates correctly - if you change them we won't be able to test your code !
It would also be wise to make sure the original skeleton test script still works with your program.

Note also that you should not have made any permanent changes to the
`OXOView`, `OXOGame` or `OXOPlayer` classes.
These will get replaced during the marking process, so you shouldn't be relying on any code
that you have added to them !  


# 
