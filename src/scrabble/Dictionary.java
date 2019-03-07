/**Craig Parry This class is used for the scrabble game and is the Dictionary for
 * the Scrabble game
 * Use with:
 * Board.java, Computer.java, Letters.java, BoardTile.java, Direction.java,
 * Human.java, LetterBag.java, Player.java, MainGameLoop.java, ScrabbleGUI.java,
 * and WordSolver.java
 */

package scrabble;
import java.util.*;
import java.io.*;


public class Dictionary {

    private List<String> hold;
    private Map<Character,TrieNode> dictMap;
    public Dictionary(){
        dictMap = new HashMap<>();
        hold = new LinkedList<>();
        readDictFile();
        populate();
    }

    public Dictionary(String file){
        dictMap = new HashMap<>();
        hold = new LinkedList<>();
        readDictFile(file);

        populate();
    }

    /** Reads in the file used as the dictionary
     * @reutrn void
     */
    public void readDictFile(){
        try(
                // use this for the jar file?
                // need to create resources folder and "mark directory as resource root"
                // with the resources in the oflder to use this input stream
//            InputStream in = getClass().getResourceAsStream("dictionary.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in))){

            // use this in the mean time
            BufferedReader reader = new BufferedReader(new FileReader("sowpods.txt"))){
            String line;
                while ((line = reader.readLine()) != null){
                    hold.add(line);
                }
            } catch(IOException ex){
                System.out.println(ex.toString());
                System.out.println("Loading sowpods.txt from resources");

            try(


                    // use this for the jar file?
                    // need to create resources folder and "mark directory as resource root"
                    // with the resources in the oflder to use this input stream

            InputStream in = getClass().getClassLoader().getResourceAsStream("sowpods.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))){

                    // use this in the mean time
//                    BufferedReader reader = new BufferedReader(new FileReader("sowpods.txt"))){
                String line;
                while ((line = reader.readLine()) != null){
                    hold.add(line);
                }
            } catch(IOException ex2){
                System.out.println(ex2.toString());


            }
        }
//        System.out.println("here");
    }
    /** Reads in the file used as the dictionary specified by a string value to
     * be read in from a file
     * @reutrn void
     */
    public void readDictFile(String file){
        try(
                // use this for the jar file?
                // need to create resources folder and "mark directory as resource root"
                // with the resources in the oflder to use this input stream
//            InputStream in = getClass().getResourceAsStream("dictionary.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in))){

                // use this in the mean time
                BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                hold.add(line);
            }
        } catch(IOException ex){
            System.out.println(ex.toString());
            System.out.println("Loading " +file+" from resources");

            try(
                    // use this for the jar file?
                    // need to create resources folder and "mark directory as resource root"
                    // with the resources in the oflder to use this input stream
            InputStream in = getClass().getClassLoader().getResourceAsStream("resources/"+file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))){

                    // use this in the mean time
//                    BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line;
                while ((line = reader.readLine()) != null){
                    hold.add(line);
                }
            } catch(IOException ex2){
                System.out.println(ex2.toString());
            }
        }

    }
    /**Populates the dictiony with words from the read in file
     * @param
     * @return void
     */
    public void populate(){
        for(String s: hold){
           insert(s,null,0, s.length());
            //insert(s);
        }
    }

//    public void insert(String word){
//        word.toLowerCase();
//        int length = word.length();
//        // if the dictionary is empty make a new node
//        if(dictionary.isEmpty()){
//            dictionary.add(new TrieNode(word.charAt(0)));
//        }
//        // finds the node corresponding to the letter
//        TrieNode temp = null;
//        for(TrieNode t: dictionary){
//            if(word.charAt(0) == t.getLetter()){
//                temp = t;
//                break;
//            }
//        }
//        // creates node for new letter in the branch
//        if(temp == null){
//            temp = new TrieNode(word.charAt(0));
//            dictionary.add(temp);
//        }
//
//        // starting at 1 for the length of the word find the letters in the branches
//        int i=1;
//        do{
//            boolean exists = false;
//
//            if(temp.getBranch().isEmpty()){
//                temp.getBranch().add(new TrieNode(word.charAt(i)));
//            }
//
//            for(TrieNode t: temp.getBranch()){
//                if(word.charAt(i) == t.getLetter()){
//                    temp = t;
//                    exists = true;
//                    break;
//                }
//
//            }
//            if(!exists){
//                TrieNode hold = new TrieNode(word.charAt(i));
//                temp.getBranch().add(hold);
//                temp = hold;
//            }
//            if(i == length -1){
//                temp.isWord();
//            }
//            i++;
//
//        }while(i<length);
//    }

    /**Inserts a word into the dictionary trie structure
     *
     * @param word
     * @param node
     * @param position
     * @param length
     * @return void
     */
    public void insert(String word, TrieNode node, int position, int length){
        if(!word.equals("")){
            word.toLowerCase();
        } else return;

//        int length = word.length();
        TrieNode temp =null;
        if(node == null){
            if(length >0){
                dictMap.putIfAbsent(word.charAt(position), new TrieNode(word.charAt(position)));
                temp = dictMap.get(word.charAt(position));
            }

        } else {
            node.getMapBranch().putIfAbsent(word.charAt(position), new TrieNode(word.charAt(position)));
            temp = node.getMapBranch().get(word.charAt(position));
        }


        if(position == length-1&& temp != null){
            temp.setIsWord();
            temp.setWord(word);
            return;
        } else {
            insert(word,temp,position+1,length);
        }
    }

    /** Searches the trie for a word that has been stored in the dictionary
     * using a string literal
     *
     * @param word
     * @param node
     * @param position
     * @param length
     * @return boolean
     */
    public boolean search(String word, TrieNode node, int position, int length){
        word.toLowerCase();
        if(position == length) return false;
        TrieNode temp = null;
        if(node == null){
            if(dictMap.containsKey(word.charAt(position))){
                temp = dictMap.get(word.charAt(position));
                return search(word, temp, position+1, length);
            } else return false;

        } else{
            if(node.getMapBranch().containsKey(word.charAt(position))){
                temp = node.getMapBranch().get(word.charAt(position));
            }
        }

        if(temp == null) return false;
        if(position == length-1 && temp.isWord()){
            return true;
        } else return search(word, temp, position+1,length);
    }

    /** Searches for a word stored in the trie dictionary using a list of characters
     * to that can generate any word that could be made using those characters
     *
     * @param strList
     * @param chars
     * @param node
     * @return List<String>
     */
    public List<String> searchUnordered(List<String> strList, List<Character> chars, TrieNode node){
        //did make sure that there is a case for * or blank characters

        if(chars.isEmpty()&& node.isWord()){
            //add word to the list if no more chars to check

            strList.add(node.getWord());
            return strList;
        }


        //fixed bug where the last letter on some words is not seen therefor the
        // word is not added to the list might need to seperate the bottom half
        // into its own helper or try to debug again and watch the first iteration
        // its matching the dictionary for a list of words with no prefix

        //stopped storing the duplicates, but it still searches over those duplicates
        // need to include searching for strings with a wildcard its making
        // some duplicates that exist because it is reusing letters in the tray
        List<Character> tempList = new LinkedList<>();

        TrieNode tempNode = null;
        // iterate through the list
        for(Character i: chars){
            tempList.clear();
            tempList.addAll(chars);
            if(node == null){
                if(i == '*'){
                    String alpha ="abcdeghijklmnopqrstuvwxyz";
                    int len = alpha.length();
                    char wild;
                    for(int j =0; j< len; j++){
                        wild = alpha.charAt(j);
                        if(dictMap.containsKey(wild)){
                            Character holdC = wild;
                            tempNode = dictMap.get(holdC);
                            if(tempNode.isWord() && !strList.contains(tempNode.getWord())){
                                strList.add(tempNode.getWord());
                            }
                            tempList.remove(i);
                            searchUnordered(strList,tempList,tempNode);
                        }
                    }
                }else{
                    if(dictMap.containsKey(i)){
                        Character holdC = Character.toLowerCase(i);

                        tempNode = dictMap.get(holdC);
                        if(tempNode.isWord()&& !strList.contains(tempNode.getWord())){
                            strList.add(tempNode.getWord());
                        }
                        tempList.remove(holdC);
                        searchUnordered(strList,tempList,tempNode);
                    }
                }
            }else{
                if(i == '*') {
                    String alpha = "abcdeghijklmnopqrstuvwxyz";
                    int len = alpha.length();
                    char wild;
                    for (int j = 0; j < len; j++) {
                        wild = alpha.charAt(j);
                        //get TrieNOde that matches the character
                        if(node.getMapBranch().containsKey(wild)){
                            // hold the found letter to remove for the next level
                            // but don't lose it so it can be used for the other letters
                            // on this level
                            Character holdC = wild;
                            tempNode = node.getMapBranch().get(holdC);
                            if(tempNode.isWord() && !strList.contains(tempNode.getWord())){
                                strList.add(tempNode.getWord());
                            }
                            tempList.remove(i);
                            searchUnordered(strList,tempList,tempNode);
                        }


                    }

                }else{
                    //get TrieNOde that matches the character
                    if(node.getMapBranch().containsKey(i)){
                        // hold the found letter to remove for the next level
                        // but don't lose it so it can be used for the other letters
                        // on this level
                        Character holdC = Character.toLowerCase(i);
                        tempNode = node.getMapBranch().get(holdC);
                        if(tempNode.isWord()&& !strList.contains(tempNode.getWord())){
                            strList.add(tempNode.getWord());
                        }
                        tempList.remove(holdC);
                        searchUnordered(strList,tempList,tempNode);
                    }
                }
            }
        }
        return strList;

    }
//    public List<String> searchUnordered(String prefix, List<Character> chars){
//        List<String> hold = new LinkedList<>();
//        int i = 0;
//        int length = prefix.length();
//        TrieNode temp = null;
//        prefix.toLowerCase();
//
//        while(i<length){
//            if(temp == null){
//                if(dictMap.containsKey(prefix.charAt(i))) {
//                    temp = dictMap.get(prefix.charAt(i));
//                    i++;
//                }else{
//                    return hold;
//                }
//            } else{
//                if(temp.getMapBranch().containsKey(prefix.charAt(i))){
//                    temp = temp.getMapBranch().get(prefix.charAt(i));
//                    i++;
//                }else{
//                    return hold;
//                }
//            }
//        }
//
//        // using the temp node we just created with the prefix
//        // check the dictionary for a new words traversing each node r
//        //recursively and adding words as we as well as making sure we check each
//        // combination of characters at each level
//        //  returning a list
//
//        hold =unorderedHelper(prefix,hold,chars,temp);
//        return hold;
//    }

//    /**Searches the Trie for the word passed into the function with Linkedlist
//     * trie
//     * @param word
//     * @return String
//     */
//    public boolean search(String word){
//        word.toLowerCase();
//        int length = word.length();
//
//
//        List<TrieNode> hold = dictionary;
//
//        boolean found = false;
//        TrieNode temp = null;
//        for(TrieNode t: dictionary){
//            if(word.charAt(0) == t.getLetter()){
//                temp = t;
//            }
//        }
//        if(temp == null){
//            return false;
//        }
//        int i=1;
//
//        do{
//
//           for(TrieNode t: temp.getBranch()){
//               if(word.charAt(i) == t.getLetter()){
//
//                   temp =t;
//                   break;
//               }
//           }
//           if(temp != null && i == length-1 && temp.isWord){
//               found = true;
//           }
//            i++;
//
//        }while(i<length);
//
//        return found;
//    }

//    /**Reads in file from input
//     *
//     * @param file
//     * @return void
//     */
//    public void readFile(String file){
//        hold = new LinkedList<>();
//
//        /*read in the file and store it in the hold list*/
//
//
//    }

    /** TrieNode class used to  make the trie sturcture for storing the dictionary
     * used for the scrabble game
     *
     */
    public class TrieNode{

        private String word;
        private boolean isWord;
        private Map<Character, TrieNode> mapBranch;
        private char letter;

        public TrieNode(char let){
            letter = let;
            mapBranch = new HashMap<>();
            isWord = false;
        }

        /** sets the isWord boolean to true
         * @return void
         */
        public void setIsWord(){
            isWord =true;
        }

        /** returns the boolean isWord
         *
         * @return boolean
         */
        public boolean isWord(){

            return isWord;
        }

        /** Gets the word String member
         *
         * @return String
         */
        public String getWord() {
            return word;
        }

        /** Sets the word member to the String word
         *
         * @param word
         * @return void
         */
        public void setWord(String word) {
            this.word = word;
        }

        /** gets the ltter member of the trienode
         *
         * @return char
         */
        public char getLetter(){
            return letter;
        }

        /** gets the branch of the trienode holding a map of the next level of
         * trienodes
         *
         * @return Map<Character></>
         */
        public Map<Character, TrieNode> getMapBranch() {
            return mapBranch;
        }
    }

    public static void main(String[] args){
        Dictionary test = new Dictionary();
//        test.insert("this");
//
//        test.insert("there");
//
//        test.insert("to");
//
//        test.insert("boy");
//
//        test.insert("brother");
//
//        test.insert("boy");



//        if(test.search("there")){ System.out.println("found");}
//        if(test.search("brother")){ System.out.println("found");}
//        if(test.search("bo")){ System.out.println("found");}
//
//        if(test.search("stess")){ System.out.println("found");}
//        if(test.search("test")){ System.out.println("found");}
//        if(test.search("aqui")){
//            System.out.println("found");
//        } else {System.out.println("Not Found");}



//        if(test.search("dogies",null,0,5)){ System.out.println("found");}
//        if(test.search("brother",null,0,7)){ System.out.println("found");}
//        if(test.search("bo",null,0,2)){ System.out.println("found");}
//
//        if(test.search("stress",null,0,6)){ System.out.println("found");}
//        if(test.search("test",null,0,4)){ System.out.println("found");}
////        System.out.println("here");


        String temp = "";
        if(test.search(temp,null,0,temp.length())){
            System.out.println("found");
        } else {System.out.println("Not Found");}
    }

}
