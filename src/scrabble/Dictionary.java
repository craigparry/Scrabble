package scrabble;
import java.util.*;
import java.io.*;


public class Dictionary {

    /* todo :
    *read in the dictionary and then sort the words in a trie
    *
    */
//    private List<TrieNode> dictionary;
    private List<String> hold;
    private Map<Character,TrieNode> dictMap;
    public Dictionary(){
        dictMap = new HashMap<>();
//        dictionary = new LinkedList<>();
        hold = new LinkedList<>();
        readDictFile();
        populate();


    }


    public void readDictFile(){
        try(
                // use this for the jar file?
                // need to create resources folder and "mark directory as resource root"
                // with the resources in the oflder to use this input stream
//            InputStream in = getClass().getResourceAsStream("dictionary.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in))){

            // use this in the mean time
            BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"))){
            String line;
                while ((line = reader.readLine()) != null){
                    hold.add(line);
                }
            } catch(IOException ex){
                System.out.println(ex.toString());
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

    public void insert(String word, TrieNode node, int position, int length){
        word.toLowerCase();
//        int length = word.length();
        TrieNode temp;
        if(node == null){
            dictMap.putIfAbsent(word.charAt(position), new TrieNode(word.charAt(position)));
            temp = dictMap.get(word.charAt(position));
        } else {
            node.getMapBranch().putIfAbsent(word.charAt(position), new TrieNode(word.charAt(position)));
            temp = node.getMapBranch().get(word.charAt(position));
        }


        if(position == length-1){
            temp.setIsWord();
            temp.setWord(word);
            return;
        } else {
            insert(word,temp,position+1,length);
        }



    }
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

    public List<String> unorderedHelper(String prefix, List<String> strList, List<Character> chars, TrieNode node){
        //todo make sure that there is a case for * or blank characters

//        if(node == null){
//
//
//        }
        if(chars.isEmpty()&& node.isWord()){
            //add word to the list if no more chars to check

            strList.add(node.getWord());
            return strList;
        }

        //fixed bug where the last letter on some words is not seen therefor the word is not added to the list
        // might need to seperate the bottom half into its own helper or try to debug again and watch the first iteration
        // its matching the dictionary for a list of words with no prefix

        //todo, need to include searching for strings with a wildcard its making some duplicates that
        // exist because it is reusing letters in the tray
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
                            unorderedHelper(prefix,strList,tempList,tempNode);
                        }
                    }
                }else{
                    if(dictMap.containsKey(i)){
                        Character holdC = i;
                        tempNode = dictMap.get(holdC);
                        if(tempNode.isWord()&& !strList.contains(tempNode.getWord())){
                            strList.add(tempNode.getWord());
                        }
                        tempList.remove(holdC);
                        unorderedHelper(prefix,strList,tempList,tempNode);
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
                            unorderedHelper(prefix,strList,tempList,tempNode);
                        }


                    }

                }else{
                    //get TrieNOde that matches the character
                    if(node.getMapBranch().containsKey(i)){
                        // hold the found letter to remove for the next level
                        // but don't lose it so it can be used for the other letters
                        // on this level
                        Character holdC = i;
                        tempNode = node.getMapBranch().get(holdC);
                        if(tempNode.isWord()&& !strList.contains(tempNode.getWord())){
                            strList.add(tempNode.getWord());
                        }
                        tempList.remove(holdC);
                        unorderedHelper(prefix,strList,tempList,tempNode);
                    }
                }


            }
        }

        return strList;

    }
    public List<String> searchUnordered(String prefix, List<Character> chars){
        List<String> hold = new LinkedList<>();
        int i = 0;
        int length = prefix.length();
        TrieNode temp = null;
        prefix.toLowerCase();

        while(i<length){
            if(temp == null){
                if(dictMap.containsKey(prefix.charAt(i))) {
                    temp = dictMap.get(prefix.charAt(i));
                    i++;
                }else{
                    return hold;
                }
            } else{
                if(temp.getMapBranch().containsKey(prefix.charAt(i))){
                    temp = temp.getMapBranch().get(prefix.charAt(i));
                    i++;
                }else{
                    return hold;
                }
            }
        }

        // using the temp node we just created with the prefix
        // check the dictionary for a new words traversing each node r
        //recursively and adding words as we as well as making sure we check each
        // combination of characters at each level
        //  returning a list

        hold =unorderedHelper(prefix,hold,chars,temp);
        return hold;
    }

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

    public class TrieNode{

        private String word;
        private boolean isWord;
//        private List<TrieNode> branch;
        private Map<Character, TrieNode> mapBranch;
        private char letter;

        public TrieNode(char let){
            letter = let;
            mapBranch = new HashMap<>();
//            branch = new LinkedList<>();
            isWord = false;
        }
        public void setIsWord(){
            isWord =true;
        }
        public boolean isWord(){

            return isWord;
        }
//        public List<TrieNode> getBranch(){
//            return branch;
//        }
        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public char getLetter(){
            return letter;
        }

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
//        do,dog,dogs, dogie ,dogies ,doge ,doges ,dos
//                dose
//        doe
//                does
//        dig
//                digs
//        die
//                dies
//        de
//                des
//        dei
//                go
//        god
//                gods
//        goes
//                gid
//        gids
//                gie
//        gied
//                gies
//        ged
//                geds
//        geoid
//                geoids
//        od
//                ods
//        ode
//                odes
//        os
//                ose
//        oe
//                oes
//        so
//                sod
//        si
//                side
//        seg
//                sego
//        sei
//                id
//        ids
//                ides,is,ego,egos,egis,es,eidos





        String temp = "";
        if(test.search(temp,null,0,temp.length())){
            System.out.println("found");
        } else {System.out.println("Not Found");}
    }

}
