## Briefing on DB assignment
### <a href='https://web.microsoftstream.com/group/2ab518ed-5a83-4c36-bfef-c8a2bf702e79?view=videos' target='_blank'> Weekly Briefing ![](../../resources/icons/briefing.png) </a>
### Task 1: Introduction


This workbook will lead you through the next assessed exercise,
the aim of which is to build a database server: from the ground up !
This will provide you with an ideal opportunity to explore in detail the database content
from the Software Tools unit, as well as providing you with hands-on experience of using query languages. 
Note that assignment WILL contribute to your unit mark with a weighting of 35%. 

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

An automated checker will be used to flag any incidences of possible plagiarism.
If the markers feel that intentional plagiarism has actually taken place, marks may be deducted.
In serious or extensive cases, the incident may be reported to the faculty plagiarism panel.
This may result in a mark of zero for the assignment, or perhaps even the entire unit
(if it is a repeat offence).
Don't panic - if you stick to the above list of advice, you should remain safe !

You need to be very careful with how you work. Below are the results from the plagiarism analysis of 
the OXO assignment. The majority of students are 39% match and below.
There are however a couple of clusters of students in the 40s and 50s - which is a little concerning.
You should take care not to be in these bands for the current assignment !
  


![](01%20Introduction/images/plag-results.jpg)

# 
### Task 2: Assignment Overview


In this assignment, you will build a relational database server from scratch.
This server should receive incoming requests (conforming to a standard query language)
and then interrogate and manipulate a set of stored records.
Your server will maintain persistent data as a collection of files on your filesystem.
You will not be required to implement a client application - this will be provided for you 
(to allow you to connect to your server and check that is it working correctly).

As usual, you have been provided with a <a href="resources/cw-db" target="_blank">Maven project</a>
to help get you started with the assignment.
There is a <a href="resources/cw-db/src/test/java/edu/uob/DBTests.java" target="_blank">template test script</a>
for you to use - make sure you add suitable test cases to this script to ensure that your application is
fully and systematically tested.

It is **essential** that your server is _robust_ - you should detect and trap errors effectively
and ensure that the server continues running at all times. Just imagine a world in which servers
had to be manually restarted every time something unexpected was encountered.
It's going to be very difficult for your server to pass the marking tests if it has crashed !

Note that your main class MUST be called `DBServer` and MUST include the following constructor and input handling method:

* `public DBServer(File)`
* `public String handleCommand(String)`

If you change the name of the class or either of the above methods, we won't be able to run your code !
We will be using automated marking scripts and if you server does not conform to the above,
we won't be able to test it ! In order to avoid any doubt - we have added `KEEP this signature...`
comments to the maven project template for anything you should not change.

Your submission will be assessed on the success with which it implements the described query language,
as well as the flexibility and robustness with which your server operates.
You will also be assessed using the Code Quality metrics outlined earlier in the unit.
Finally, since test-driven development is key to the processes taught on this programme, 
the extent and quality of your test cases will also be considered.  


# 
### Task 3: Persistent Storage
 <a href='03%20Persistent%20Storage/slides/segment-1.pdf' target='_blank'> ![](../../resources/icons/slides.png) </a> <a href='03%20Persistent%20Storage/video/segment-1.mp4' target='_blank'> ![](../../resources/icons/video.png) </a>

Any database system must be able to persistently store data
(otherwise it will lose everything each time it is restarted).
In this assignment, you will use your file system for this purpose.

Your database will consist of a number of tables, each containing a collection of rows
that store **records** (aka **entities**) - see the table near the end of this section for an example.
The first column in each table will contain a **primary key**
(which should always be called `id`). Relationships between entities in different tables should be
recorded using **foreign keys**. You may assume only single element keys are to be used
(i.e. you do not need to cope with "composite" keys).
Note that the `id` of an entity should NOT change at any time during the operation of the system
(once an entity has been assigned an `id` this will stay fixed for as long as that entity is kept in the database).

Each table should be stored in a separate file using TAB separated text.
A <a href="resources/people.tab" target="_blank">sample data file</a>
has been provided for you to illustrate this approach.
Note that the constructor method of the `DBServer` class takes a `File` parameter - this is a directory where your
server should store all data files. It is essential that you store ALL of your database files in that directory and nowhere outside of it.
Make sure that all files you reference in your code are _relative_ to the directory specified in the constructor - you must NOT use anything absolute (such as `/users/simon/java/db`) since this will only work on your own computer.
Note that there is further documentation in the provided test script that discusses how you can test this for yourself.

In order to load and save files to and from the file system, you will need to make use of the Java File IO API.
View the slides and video at the start of this section for an overview of these packages.
You may need to delve more deeply into the
<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/package-summary.html" target="_blank">File IO documentation</a>
in order to implement your ideas.

As a useful starting point for this assignment, your first practical task should be to create a method that reads in data from the filesystem.
Using the sample data file provided as a source of sample data, read in the rows using appropriate classes and methods from the Java File IO API.
At this stage you need only print out the entities to the screen (in the following section you will store this data in memory in a suitable data structure).
  


![](03%20Persistent%20Storage/images/table.jpg)

**Hints & Tips:**  
It is up to you if you want to store the column names in the top row of the data file (as illustrated in the sample data file) or separately in a special schema textfile. Just remember that if you store them separately it is essential to keep them synchronised and consistent.

It is not necessary to implement a type system within the database - 
you can just store everything as simple text strings.

It is _not_ your responsibility to normalise the database - this is a job for
developers who have designed the database schema and who make use of your database server.
If you don't know what normalisation is, then don't worry - you don't need to know for this assignment (although I bet you are intrigued to find out now ;o)  


# 
### Task 4: Java Data Structures


Once the data has been read in from the filesystem, you will need to store this in memory.
You will need to devise a suitable set of classes to represent this data inside your Java program.
Think carefully about the tabular nature of relational databases and then write a set of classes
that match this structure. You will need to consider a wide range of different elements of the
database, including: tables, rows, columns, keys, table names, column names, data values, ids
and relationships between entities.

Remember that this teaching block focuses on "development" (not just "coding") and as such we are
attempting to develop your analysis and design skills. This exercise is more than just implementing a
pre-defined specification - it requires you to understand the domain, be able to deconstruct
the problem and make informed design decisions to achieve a successful solution. As such,
it is not easy - you are likely to make mistakes and will need to refactor your code at different
stages over the next few weeks.

Once you have defined a collection of classes that you feel are appropriate to the problem,
use the file reading methods that you wrote in the previous section to populate instances of your classes
with data read in from the sample data file. In order to support you in building structures to store relationships,
we have provided an <a href="resources/sheds.tab" target="_blank">additional data file</a>.
The `PurchaserID` column in this new data file is a **foreign key** that provides a link to the `id` of a person
in the `people` data file. In order to fully exercise your classes as you develop your server further, you will need
to write additional data files to augment those given to you.

Once you have successfully stored the imported data in your classes, the next step is to write a
method to save these structures _back out_ to the filesystem again
(using appropriate features of the Java File API).  


**Hints & Tips:**  
In order to check that your code is successfully reading and writing data from the files,
you should alter the data _whilst it is in memory_ (i.e. _after_ you have read it in but _before_ you write it back out again).
You could for example replace the age of all people in the table with a randomly generated one. 
By changing the data in this way, you can check to make sure that the file system files are actually being over-written and updated !  


# 
### Task 5: Communication


It is not the aim of this assignment to address the topic of network or socket programming.
For this reason, the networking aspects of the server have been provided for you
in <a href="resources/cw-db/src/main/java/edu/uob/DBServer.java" target="_blank">the template server class</a>.

The database server listens on port 8888 in order to receive incoming messages. These incoming commands are then
passed to the `handleCommand` method for processing. Your task is to add to the `handleCommand` method to respond to the commands.
At this stage in the assignment, you should not attempt to interpret the content of the incoming messages.
Rather it should respond with the content of _all_ of the tables currently in your database
(irrespective of what the incoming message contained).
You should attempt to make the response as human-reader friendly as possible.

It is essential that your response is returned by the `handleCommand` method and NOT just printed out in the local console.
When we test your server during the marking process, we will be monitoring what is returned via the network.
You won't get any marks for just doing `println` messages in the terminal !

To help you ensure that your server conforms to the correct protocol, a 
<a href="resources/cw-db/src/main/java/edu/uob/DBClient.java" target="_blank">command-line client</a>
has been provided for you. You should not have to change any of the code in the client,
any features that you implement in this class will not be executed during the marking process.
The client has been provided purely to allow you to check that your server is operating correctly.
During the marking process, this client will be discarded and replaced by an automated testing component.

For the sake of simplicity, you may assume only a single client is connected at any one time.
(i.e. there is no need to handle parallel queries or deal with issues of contention).  


# 
### Task 6: Query Language


Now that you have a communication mechanism in place, we need something to transmit !
Clients will communicate with the server using a simplified version of the SQL database query language.
Your task is to write a handler for the incoming commands which will parse them, perform the specified queries and return the result to the client.
The query language we shall use for this purpose supports the following main types of query:

- USE: changes the database against which the following queries will be run
- CREATE: constructs a new database or table (depending on the provided parameters)
- INSERT: adds a new entity (row) to an existing table
- SELECT: searches for entities that match the given condition
- UPDATE: changes the existing data contained within a table
- ALTER: changes the structure (columns) of an existing table
- DELETE: removes entities that match the given condition from an existing table
- DROP: removes a specified table from a database, or removes the entire database
- JOIN: performs an **inner** join on two tables (returning all permutations of all matching entities)

A grammar that fully defines the simplified query language is provided in <a href="resources/BNF.txt" target="_blank">this BNF document</a>.
Note that your server should be able to correctly parse incoming commands irrespective of
the number of _additional_ whitespace characters between tokens. So for example:
<pre>SELECT    *  FROM     people  WHERE   Name  ==  'Steve' ;</pre>
is valid and acceptable, being equivalent to:
<pre>SELECT * FROM people WHERE Name=='Steve';</pre>

Note that you should NOT use any existing parsers or parser generators (Yacc, Lex, Antlr etc.)
The aim of this assignment is to implement the command parsing using your own code.  


**Hints & Tips:**  
SQL _keywords_ are case insensitive, so `select * from people;` is equivalent to `SELECT * FROM people;`.
Convention has it that query examples are typically shown with uppercase keywords (to differentiate them from identifiers and literals).

The range of numbers (max and min) match those supported by the standard Java int and float types.
  


# 
### Task 7: Error Handling


Your server should receive incoming messages, interrogate the data stored in the database
and finally return an appropriate response to the client. 
Your query interpreter should identify any errors in the construction of queries
(for example queries not conforming to the BNF or queries that include unknown database / table / attribute identifier).
The response returned from your server must begin with one of the following two "status" tags:

- `[OK]` for valid and successful queries, followed by the results of the query.
- `[ERROR]` if the query is invalid, followed by a suitable human-readable message that provides information about the nature of the error.

You may wish to make use of exceptions to handle errors internally within your server, however it is 
essential that your response to the client begins with the correct status tag (since these will be used by the automated
testing scripts during the marking process).

Note that because you are not required to maintain data type information for the attributes
in a table, it will not be possible for you to validate the type of incoming data.
It is therefore the responsibility of the user to ensure that only numerical data is stored in
numerical attributes and string data is stored in string attributes etc.  


# 
### Task 8: Submission


You should create a zip archive of your entire Maven project and upload this via the "Assessment, submission and feedback"
section on this unit's Blackboard page. It is essential that you ensure your code compiles and runs before you submit it.

Your submission will be assessed on the success with which it implements the described query language,
as well as the flexibility and robustness with which it operates.
Your submission will also be assessed against the Code Quality metrics outlined earlier in the unit.
Since test-driven development is key to the processes taught on this programme, the extent and quality of your test cases will also be considered.

Remember that your main class MUST be called `DBServer` and should not change the signature of the constructor or the `handleCommand` method
(or we won't be able to run your code !). We will be using automated marking scripts and if your server does not conform to the above,
we won't be able to test it !
You should ensure that the template test script that was provided at the start of this assignment is able to run with your code - 
if this script will not run successfully, then the testing script we will use during marking will also fail to run.  


# 
