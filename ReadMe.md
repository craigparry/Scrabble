Craig Parry

WordSolver: run the WordSolver jar file with a command line
argument to specify the dictionary file, otherwise it will use 
the sowpods.txt dictionary stored in the jar. The WordSolver uses
standard input to read in the board configuration and can handle a 
file that holds multiple configurations. So the file can look like: 

board size
board configuration
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

The left dot of each place represents the word multiplier if 
a digit. The right is the letter bonus. A lowercase letter is 
a regular tile and a uppercase letter is a wildcard tile. 

Scrabble: 

Data structure for Dictionary: 
I used a Trie for word lookup. It consists of Trie nodes 
that use maps as their branches holding more Trie nodes. 
The Trie nodes are identified by their character value 
when looked up in the maps


Project Structure: I used a ArrayList of ArrayList containing 
BoardTiles for my game board and in hindsight I think that look 
up would have actually been faster if I had used a 2D array 
and accessed by pointer instead of using the built in get method 
with ArrayList. I currently have a case that takes a lot longer than
others to search for a legal and highest scoring play for the computer
and I think that there are excess method calls due to the data structure 
that I picked for the board. 

My isMy computer player searches each row and column to find the 
played letters on the tray that it wants to create a move at. Then uses
those letters and the tray to generate every possible combination from 
the dictionary. The searches for legal moves and keeps the highest scoring 
word.  



GUI- The GUI at this point is not hooked up to the logic of the 
game and does not have the word or letter bonus colors added.
It has a 15x15 board and a tray for the players letters, along with 
a pass and a play button. I will try to get to this before monday,
hopefully. I will make the player click on their tile and then on the 
board to move tiles to a copy of the board that I can test for legal 
moves, then update the real board and the gui after I test and play their 
word and after the computer moves. 

Bugs: 
* My word search is mostly working at this moment, but there is a certain case 
that runs slower than the others when testing from Brooke's example file. It solves 
in 12 seconds instead of about 3 or 4 seconds for the others. Hopefully that's just 
the case for boards that are mostly blank and have lots of connecting plays. At least
that is where the bug lies if I don't happen to find it. I tried minimizing the amount of 
dictionary lookups, but that didn't seem to help. I think its still something in the isLegal
function that is slowing it down.


* There is a case where words being played vertically are not being allowed. 
It doesn't always happen, but it happens when connecting on both sides of a horizontal word. 
Wasn't able to track it down tonight. 


TODO if more time: 
Need to make the rules so that the first play has to be at the center of the board.
 
I would add the end game logic and fix my bugs. I need to add the conditions for when the 
bag runs out and the computer player has no move and the human player passes their turn. 
Adding the remaining tiles scores to the other players tray if a player plays all of their tiles 
and subtract that value from the score of the player who had tiles remaining on their tray.

set up the new game button to work. 

just ran out of time on some of these, midterms kept me busy 

io stream to read in frequencies or letter values


