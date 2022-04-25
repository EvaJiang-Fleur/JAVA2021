## Briefing on STAG assignment
### <a href='https://web.microsoftstream.com/group/2ab518ed-5a83-4c36-bfef-c8a2bf702e79?view=videos' target='_blank'> Weekly Briefing ![](../../resources/icons/briefing.png) </a>
### Task 1: Introduction


This workbook introduces the final assignment that is worth the remaining 45% of the assessment for this unit. The focus of this assignment is the construction of a general-purpose socket-server game-engine for text adventure games. A typical game of this genre is illustrated in the screenshot below. The following sections of this workbook explain the construction of this Simple Text Adventure Game (STAG) in more detail and provide a breakdown of the features you are required to implement.

<a href="http://www.web-adventures.org/cgi-bin/webfrotz?s=ZorkDungeon&n=4185" target="_blank">
<img src="resources/zork.jpg">
</a>
  


# 
### Task 2: Game Engine
 <a href='02%20Game%20Engine/video/adventure.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Your aim is to build a game engine server that communicates with one or more game clients.
Your server will listen for incoming connections from clients (in a similar way to the DB exercise).
When a connection has been made, your server will receive an incoming command, process the actions
that have been requested, make changes to any game state that is required and then finally send back a
suitable response to the client. View the video linked to above for a demonstration of a typical example
of communication from the perspective of the client (note that there is no audio track in this video).

The basic networking operation is provided for you in the 
<a href="resources/cw-stag.zip" target="_blank">maven template</a>.
As with the DB exercise, you do not need to write the client since this is provided for you.
It is again essential that you do NOT change the code in the client.
The interactive client will be replaced by an automated test client when marking your work.

The aim of this assignment is to build a versatile game engine that it is able to play _any_ text adventure game
(providing that it conforms to certain rules). To support this versatility, two configuration files:
**entities** and **actions** are used to describe the various "things" that are present in the game,
their structural layout and dynamic behaviours.
These two configuration files are passed into the game server when it is instantiated like so:

``` java
public GameServer(File entitiesFile, File actionsFile)
```

The server will load the game scenario in from the two configuration files, thus allowing a range of different games to be played.
During the marking process, we will use some custom game files in order to explore the full range of functionality in your code.
It is therefore essential that your game engine allows these files to be passed in and then reads and interprets their content
(otherwise we won't be able to test your code).  


# 
### Task 3: Basic Commands


In order to communicate with the server, we need an agreed language
(otherwise the user won't know what to type to interact with the game !)
There are a number of standard "built-in" gameplay commands that your game engine should respond to:

- "inventory" (or "inv" for short): lists all of the artefacts currently being carried by the player
- "get": picks up a specified artefact from the current location and adds it into player's inventory
- "drop": puts down an artefact from player's inventory and places it into the current location
- "goto": moves the player to a new location (if there is a path to that location)
- "look": describes the entities in the current location and lists the paths to other locations

It is essential that you conform to this standard set of commands, otherwise it won't be possible to play your game
(and your game engine will fail some of the marking tests !)

In addition to these standard "built-in" commands, it is possible to customise a game with a number of additional **actions**.
These will be introduced in more detail in a later section of this workbook.

The skeleton 
<a href="resources/cw-stag/src/main/java/edu/uob/GameServer.java" target="_blank">GameServer</a> class
(that you have been given as part of the template project) includes all the code required to deal with network communication.
All you will need to do is to complete the command handler that interprets the incoming command string,
makes changes to the game state and then return a suitable text response to send back to the client.


  


# 
### Task 4: Game Entities


Game **entities** are a fundamental building block of any text adventure game.
Entities represent a range of different "things" that exist within a game.
There are a number of different types of entity, including the following:

- Locations: Rooms or places within the game
- Artefacts: Physical things within the game that can be collected by the player
- Furniture: Physical things that are an integral part of a location
(these can NOT be collected by the player)
- Characters: The various creatures or people involved in game
- Players: A special kind of character that represents the user in the game

It is worth noting that **locations** are complex constructs and as such
have various different attributes in their own right. These attributes include:
- Paths to other locations (note: it is possible for paths to be one-way !)
- Characters that are currently at a location
- Artefacts that are currently present in a location
- Furniture that belongs in a location

All entities are stored in one of the game configuration files using a language called DOT
(a language we have already encountered when drawing graphviz diagrams in the POSE unit !).
DOT can be used to express the structure of a graph (which is basically what a text adventure game fundamentally is !)

The big benefit of using DOT files to store game entities is that we can render them graphically -
so we can SEE the structure of the game configuration.
The image below illustrates the structure of an example entities file.
As you can see, each location is represented by a rectangular box containing a number of different entities
(each type of entity being represented by a different shape). The paths between locations are
presented in the form of directed arrows.  


![](04%20Game%20Entities/images/basic-entities.png)

# 
### Task 5: Loading Entities


We have provided some example entity files for you to use in your project.
Firstly there is a <a href="resources/cw-stag/config/basic-entities.dot" target="_blank">basic entities file</a>
to help get you started in constructing your game engine.
We have also provided an <a href="resources/cw-stag/config/extended-entities.dot" target="_blank">extended entities file</a>
that can be used for more extensive testing during the later stages of your work.

You already have much experience of writing parsers from previous exercises (both on this unit and others).
We don't really want to have to cover old ground, so for this assignment you will NOT be required to build your own parser.
Instead, you are able to use existing parsing libraries for reading in the configuration files.
There is considerable educational value in learning to use existing libraries and frameworks in this way.

With this in mind, you should use the
<a href="http://www.alexander-merz.com/graphviz/doc/com/alexmerz/graphviz/Parser.html" target="_blank">JPGD parser</a>
in order to parse the entity DOT files and extract a set of 
<a href="http://www.alexander-merz.com/graphviz/doc/com/alexmerz/graphviz/objects/package-summary.html" target="_blank">GraphViz Objects</a>
which can then be manipulated by your Java code. See the "Hints and Tips" section of this task for an example of how to use these classes.

Once you have read in the entities from the DOT file, you will need to store them in a suitable datastructure so that you server can access them.
We have provided you with an abstract `GameEntity` class in the maven project that you should use to represent entities within your game.
You should write a number of concrete subclasses that inherit from this abstract class to represent specific types of entity.

All entities will need at least a name and a description, some may need additional attributes as well.
To make things easier, entity names cannot contain spaces (the DOT parser doesn't like it if they do !).
It is also worth mentioning that entity names defined in the configuration files will be unique.
You won't have to deal with two things called `door` (although your might see a `blue-door` and a `red-door`).
As such, you can safely use entity names as unique identifiers.  


**Hints & Tips:**  
Every game has a "special" location that is the starting point for an adventure.
The start location can be called anything we like, however it will _always_ be the **first** location
that appears in the "entities" file.

There is another special location called `storeroom` that can be found in the entities file.
This location does not appear in the game world (there will be no paths to/from it).
The `storeroom` is a container for all of the entities that have no initial location in the game.
Everything needs to exist somewhere in the game structure (so that they can be defined in the entities file).
These entities will not enter the game until an action places them into another location within the game.

Note that the locations will _always_ be **first** in the entities file and the paths section will _always_
appear **after** them (the parser doesn't like it if we switch this ordering !)

To ensure that the basic entities file is in the correct location in the project folder and that it is a valid DOT file, we have provided a
<a href="resources/cw-stag/src/test/java/edu/uob/EntitiesFileTests.java" target="_blank">JUnit test class</a> for you to use.
Not only will these tests verify the basic entities file, but they also provides a clear illustration of the use of the JPGD library
for parsing DOT files.

A `.jar` file containing the JPGD library can be found in the `libs` folder of the maven template.
This _should_ already be part of the project dependencies, but it is useful to know where the library resides
(in case you have add it manually to you Intellij project).  


# 
### Task 6: Game Actions


In addition to the standard "built-in" commands (e.g. `get`, `goto`, `look` etc.), your game engine should also
respond to any of a number of game-specific commands (as specified in the "actions" file).
Each of these **actions** will have the following elements:

- A set of possible **trigger** words/phrases (ANY of which can be used to initiate the action)
- A set of **subject** entities that are acted upon (ALL of which need to be present to perform the action)
- A set of **consumed** entities that are all removed ("eaten up") by the action
- A set of **produced** entities that are all created ("generated") by the action
- A **narration** that provides a human-readable explanation of what happened when the action was performed

Note that "being present" requires the entity to _either_ be in the inventory of the player invoking the action
_or_ for that entity to be in the room/location where the action is being performed. This feature is
intended to be a shortcut (so that a player can use an entity in their location without having to explicitly pick it up first).

It is worth noting that action triggers are NOT unique - for example there may be multiple "open" actions that
act on different entities. Note that trigger phrases cannot (and will not) contain the names of entities,
since this would make incoming commands far too difficult to parse. Just consider the challenge of trying to
interpret the command: `lock lock with key`.

Upon receiving a trigger word or phrase, your server should attempt to find an appropriate matching action.
Note that the action is only valid if ALL **subject** entities are available to the player.
If a valid action is found, your server must then undertake the relevant additions/removals (production/consumption).

When an entity is _produced_, it should be moved from its current location in the game (which might be in the `storeroom`)
to the location in which the action was trigged. When an entity is _consumed_ it should be removed from its current location
added into the `storeroom` location. The exception to the above are **locations** where the _paths_ between locations
should be added or removed when a location is _produced_ or _consumed_ (the location itself should always remain in the game).

  


# 
### Task 7: Loading Actions


We have provided some example action files for you to use in your project.
Firstly there is a <a href="resources/cw-stag/config/basic-actions.xml" target="_blank">basic actions file</a>
to help get you started in constructing your game engine.
We have also provided an <a href="resources/cw-stag/config/extended-actions.xml" target="_blank">extended actions file</a>
that can be used for more extensive testing during the later stages of your work.

Both of these documents are written in eXtensible Markup Language (XML).
In order to successfully parse these XML files, you should use the Java API for XML Processing (JAXP).
In particular, you should make use of the
<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.xml/javax/xml/parsers/DocumentBuilder.html" target="_blank">DocumentBuilder</a>
class as well as various classes from the 
<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.xml/org/w3c/dom/package-summary.html" target="_blank">org.w3c.dom</a> package.
See the "Hints and Tips" section of this task for an example of how to use these classes.

Once loaded in from the XML file, you should store the actions in a suitable data structure so that your server can access them quickly and easily.
Your first thought might be to use an array or `ArrayList` for this purpose.
This approach would however require your server to search through all actions each time a command was received from the user.
In the interests of efficiency (and to provide you with broader experience of using the classes in the Java `Collections` package)
you should use the following data structure to store all of your actions:

```java
TreeMap<String,HashSet<GameAction>> actions = new TreeMap<String, HashSet<GameAction>>();
```

The data structure described in the above code is illustrated in the diagram shown below. The
<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeMap.html" target="_blank">TreeMap</a>
provides a fast and efficient lookup mechanism - using a `String` key (the trigger keyword/phrase) to map to a 
<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashSet.html" target="_blank">HashSet</a>
of matching actions. Remember that a particular trigger keyword/phrase may be used
in more than one action, so we need to map to a _composite_ data structure (rather than just a single `GameAction` element).
It is useful for us to make use a _set_ (rather than, for example, an `ArrayList`)
since this allows us to ensure that there are no duplicate actions being stored in the data structure (all elements of a set will be unique).
  


![](07%20Loading%20Actions/images/TreeMap-HashSet.jpg)

**Hints & Tips:**  
To ensure that the basic actions file is in the correct location in the project folder and that it is a valid XML file, we have provided a
<a href="resources/cw-stag/src/test/java/edu/uob/ActionsFileTests.java" target="_blank">JUnit test class</a> for you to use.
Not only will these tests verify the basic actions file, but they also provides a clear illustration of the use of the JAXP library
for parsing XML files.  


# 
### Task 8: Command Flexibility


Interpreting the input from users in this assignment is very different from dealing with queries in the Database exercise.
The simplified SQL that we used for the last assignment was limited, rigid and constrained.
As such, it was possible to write a reasonably concise BNF grammar to define the entire language.
The same is not true for natural language, which is very complex, permits much variation and is used diversely (depending on the speaker/writer).
The best we can hope do is to try to make our command interpreter as flexible and versatile as possible - in order to cope with most eventualities.

In order to support variability, your interpreter should be able to cope with _additional_ "decorative" words being inserted into a command
(sometimes referred to as "syntactic sugar"). For example the basic command `chop tree with axe` 
might well be entered by the user as `chop down the tree using the axe`.
Both versions are equivalent and should be accepted as such by your command interpreter.

To further support flexible natural language communication, your server should be able to operate with shortened, "partial" commands.
It is convenient for the user to be able to omit _some_ of the subjects from a command, whilst still providing enough information for
the correct action to be identified.
For example, the command `unlock trapdoor with key` could alternatively be entered as _either_ `unlock trapdoor` _or_ `unlock with key` -
both of which provide enough detail for an action match to be attempted.
As a BARE MINIMUM, a valid action command MUST contain a trigger word/phrase and AT LEAST ONE subject.
An action on its own (without a subject) is unlikely to provide enough information to enable a single matching action to be found. 

The above "fuzzy" matching of actions is however risky - there may be situations where _more that one_ action matches a particular command.
If a particular command is ambiguous (i.e. there is _more than one_ **valid** and **performable** action possible - given the current state of the game) then NO action should be performed and a suitable warning message sent back to the user
(e.g. "there is more than one thing you can 'open' here - which one do you want ?")
  


# 
### Task 9: Multiple Players


If you are feeling ambitious, extend your game so that it is able to operate with more than just a single player.
In order to support this feature, incoming command messages begin with the username of the player who issued that command.
For example, a "full" incoming message might therefore take the form of:
```
Simon: open door with key
```

Where everything _before_ the first `:` is the player's name and everything _after_ is the command itself.
Valid player names can consist of uppercase and lowercase letters, spaces, apostrophes and hyphens.
Note that there is no "formal" player registration process - when the server encounters a command from
a previously unseen user, a new player should be created and placed in the start location of the game.

Note that there is no need for your server to implement any form of authentication - 
you can assume that the client handles this responsibility.
Your server should simply trust any commands that purport to come from a particular user.

It is essential that when an incoming message is received, the command is applied to the _correct_ player.
To achieve this, you will need to maintain _some_ elements of game state _separately_ for each individual player.
For example, each player may be in a different location in the game and will carry their own inventory of items.

One final thing to remember is that you should include _other_ players in your description of a location
when a "look" command is issued by a user. There is no point having multiple players in the game
if they can't see each other !  


# 
### Task 10: Player Health


As an extension to the basic game, you might like to add a "health level" feature.
Each player should start with a health level of 3. Consumption of "Poisons & Potions" or interaction with beneficial
or dangerous characters will increase or decrease a player's health by one point. You will see in the 
<a href="resources/cw-stag/config/extended-actions.xml" target="_blank">extended actions file</a>
the use of the `health` keyword in the `produced` and `consumed` fields.
These indicate actions which increase and decrease your health by one unit.

When a player's health runs out (i.e. when it becomes zero) they should lose all of the items in their inventory
(which are dropped in the location where they ran out of health)
They should then be transported to the start location of the game and their health level restored to the full level (i.e. 3).
You might also like to send the user a suitable message, for example:
`you died and lost all of your items, you must return to the start of the game`

In order to fully support these features in your game engine, you should implement a new `health` built-in command, so that the player
can keep track of their health level.
Upon receiving a `health` command from a user, the server should report back the player's current health level (as a number).

  


# 
### Task 11: Marking


A special set of custom game description files will be used to assess your game during the marking process.
It is therefore essential your code is able to parse files in the same format as the examples provided !
The configuration files will be passed as parameters - you should not interfere with file path
(don't add any additional folders names to the paths !).

Scripts will be used to automatically test your game engine to make sure it is operating correctly.
It is therefore essential that you adhere to the gameplay commands detailed in this workbook !
You should also ensure that you do not change the name of your main class - it must be called `GameServer`.
If you change the name of the class (or the `handleCommand` method) the marking script will not be able to test your code !

This assignment is an opportunity to explore Java in detail and there are a range of alternative avenues you might take when implementing this project.
You might for example like to consider employing a range of design patterns (as described in the weekly workbooks).
The Java Core API and Collections package also contains various useful data structures you might like to explore.

Note that the "quality" of your code will be taken into account when assessing your work.
The code quality metrics outlined earlier in this unit will be used to derive your final mark.
It is important therefore that you adhere to the structure and style guidelines outlined in the "code quality" workbook.
It is essential that you take heed of the quality feedback you have received for previous exercises,
since this will help you improve your work - not just for this exercise, but in the long term.  


# 
### Task 12: Plagiarism


You are encouraged to discuss assignments and possible solutions with other students.
HOWEVER it is essential that you only submit your own work.
This may feel like a grey area, however if you adhere to the following advice, you should be fine:

- Never exchange code with other students (via IM/email, USB stick, GIT, printouts or photos !)
- Although pair programming is encouraged on some units, on this unit you must type your own work !
- It's OK to seek help from online sources (e.g. Stack Overflow) but don't just cut-and-paste chunks of code
- If you don't understand what a line of code actually does, you shouldn't be submitting it !
- Don't submit anything you couldn't re-implement under exam conditions (with a good textbook !)

An automated checker will be used to flag any incidences of possible plagiarism
(both within this year's cohort as well as previous years submissions).
If the markers feel that intentional plagiarism has actually taken place, marks may be deducted.
In serious or extensive cases, the incident may be reported to the faculty plagiarism panel.
This may result in a mark of zero for the assignment, or perhaps even the entire unit
(if it is a repeat offence).
Don't panic - if you stick to the above list of advice, you should remain safe !  


# 
