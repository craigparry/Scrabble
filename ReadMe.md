Craig Parry

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

* Trying to figure out how to read my resources from the jar, but having problems at the moment. 

* 


TODO if more time: make the transcript read from a scanner in the WordSolver. 
So that we can feed it files instead of manually testing files.
Set up the logic for the GUI so that the player can make moves on the board 
and set up the color scheme for word and letter bonuses. Fix any bugs that I can 
find solutions for. 

odo: 
 io stream to read in frequencies or letter values

need to creat an io stream that reads in the string rep
of a board that can be used to test the legal moves of 
the game 


set up inital board and main game with a toString 
method so that I can test initial set up of the game and 
start preparing the logic of the game. 

test bonus representation of letters and bonus

start set up of player class: abstract and comp/human extensions
