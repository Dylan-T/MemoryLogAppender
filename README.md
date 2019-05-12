# SWEN 301 Assignment 2

## a) Running Tests
To run all the tests type "mvn clean test" or run them through an IDE

## b) Test Coverage

Test Coverage:

|Class|Method|Line|
|---|---|---|
|MemAppender|100% (8/8)|93% (28/30)|
|T1Layout|80% (4/5)|86% (20/23)|
|T2Layout|80% (4/5)|92% (23/25) |

My tests have almost complete coverage with only some part missed. The missed methods in T1Layout & T2Layout is activateOptions(), I have missed testing this method because it is an empty method.
This method was left empty because it is not required and the PatternLayout also left it empty.
The other missed lines in my code is mostly the catch blocks in my code. These are being missed because the catch blocks cannot be reached.

##c T1Layout (FreeMarker) vs T2Layout (Velocity)
An evaluation which of the layouts, T1Layout or T2Layout, you recommend for use. Base your decision on your experience (ease of use), technical aspects (e.g. performance as shown in the stress tests, stability, number and size of direct and indirect dependencies), and social aspects (size and activity of developer community, license, support like mailing lists and stackoverflow topics, usage by others, … ) 

Performance of stress tests:
T1 Total logs: 33763162
T2 Total logs: 51675322

Ease of use:
T2layout is easier to use as the patterns variables can be written as $variable whereas T1Layout requires them to be written as ${variable}.

Conclusion:
Overall I would recommend T2Layout. I would recommend it because it performs better, it is easier to use and the velocity template engine appears to be more commonly used and have more support.
