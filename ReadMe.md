Craig Parry

WordSolver: run the WordSolver jar file with a command line
argument to specify the dictionary file, otherwise it will use 
the sowpods.txt dictionary stored in the jar. The wordsolver uses
standard input to read in the board configuration and can handle a 
file that holds multiple configurations. So the file can look like: 

board size
board conifguration
player tray

"2
.. ..
.. .. 
words

3
.. .. ..
.. .. ..
.. .. .. 
words*"

repeat. 

The left dot of each place repesents the word multiplier if 
a digit. The right is the letter bonus. A lowercase letter is 
a regular tile and a uppercase letter is a wildcard tile. 

Scrabble: 

Data structure for Dictionary: 
I used a Trie for word lookup. It consists of Trie nodes 
that use maps as their branches holding more Trie nodes. 
The Trie nodes are identified by their character value 
when looked up in the maps


Project Structure: 



GUI- The GUI at this point is not hooked up to the logic of the 
game and does not have the word or letter bonus colors added.
It has a 15x15 board and a tray for the plaers letters, along with 
a pass and a play button. I will try to get to this before monday,
hopefully. I will make the player click on their tile and then on the 
board to move tiles to a copy of the board that I can test for legal 
moves, then update the real board and the gui after I test and play their 
word and after the computer moves. 

Bugs: 
* My word search is mostly working at this moment, but there is a certain case 
that runs slower than the others when testing from Brookes example file. It solves 
in 12 seconds instead of about 3 or 4 seconds for the others. Hopefully thats just 
the case for boards that are mostly blank and have lots of connecting plays. At least
that is where the bug lies if I don't happen to find it. I tried minimizing the amount of 
dictionary lookups, but that didn't seem to help. I think its still something in the isLegal
function that is slowing it down.


* 


TODO if more time: 
Set up the logic for the GUI so that the player can make moves on the board 
and set up the color scheme for word and letter bonuses. Fix any bugs that I can 
find solutions for. Set up the colors for bonuses. 
If I had more time I would attach action listeners to each tile 
objected created to represent the pieces on the board. If they are stored on the board the
click is deactivated. If it is in the tray then you can click and then move the piece to the 
board and click play when they are ready to be checked for the a legal move and scored. 
The moved pieces would be need to be stored in a list with their positions so that it would 
be easier to test the word when played and removed when not valid. 

io stream to read in frequencies or letter values


