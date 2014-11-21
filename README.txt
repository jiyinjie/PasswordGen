UTEID: yj3343; nbs439;
FIRSTNAME: Yinjie; Nishil;
LASTNAME: Ji, Shah
CSACCOUNT: jiyinjie; nishil;
EMAIL: jiyinjie@utexas.edu; nishil94@utexas.edu;

[Program 5]
[Description]
There are two java files. In Passwords.java, we implemented the 
main method that takes in the reference filename, the number of 
passwords to generated, and the length of each password in that
order. The main method handles input validation from the reference
file like making sure all characters are made lowercase and that
special characters are ignored. The main method also initializes
a Generator object which can be found in generator.java. The 
Generator class uses a follower's table (frequency of diagrams) to
randomly generate passwords using the Math.random function. The 
main method in Passwords.java takes care of filling up the
follower's table and the beginning array. The beginning array 
holds the frequency of how often each letter is the first or 
beginning of a word. The generator object can create a password
by calling the function createPassword which takes in one int
parameter that represents the length of the password. The main 
function will generate as many passwords needed by calling
createPassword successively. 

[Finish]
We have finished all the requirements.

[Source of Reference File]
The reference file is the longest article from Wikipedia which 
can be found here: 
http://en.wikipedia.org/wiki/South_African_labour_law. The file is
340KB big. It has 6,349 lines and has approximately 54, 554 words
since there are numbers and citations that are included in this
word count but aren't actually English words.

[Test Cases]
[Input of test 1]
java Passwords reference.txt 3 9 
 
[Output of test 1]
upliondip
bawincong
thiavaden

[Input of test 2]
java Passwords reference.txt 6 10  

[Output of test 2]
cuserorong
dideisucie
untorasfra
arkismplof
plaldigncr
feressther

[Input of test 3]
java Passwords reference.txt 20 20  

[Output of test 3]
heniproumbethecompls
beeemetrectitthirint
mpalemiserthessiccit
seremareplourmpoyeme
delerearthemenyemuld
avertedeasthergelorb
satoyenfencthetoyedi
stroyegremicalactste
athonsonsunindonsers
tyefuponeiviormestin
tistipresunitrverior
sermprmarativiesonth
fonestomarepaclemioi
bjundenductindrinoye
chedandistyemeglacal
anithyexioresthegase
unevinthesontingalon
ecimptindenthempredu
ounommurantheionotha
owemprathempafericth

[Input of test 4]
java Passwords reference.txt 1 3 

[Output of test 4]
ouc
