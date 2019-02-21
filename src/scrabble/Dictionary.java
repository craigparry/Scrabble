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
            temp.setWord();
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
        public void setWord(){
            isWord =true;
        }
        public boolean isWord(){

            return isWord;
        }
//        public List<TrieNode> getBranch(){
//            return branch;
//        }

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



        if(test.search("there",null,0,5)){ System.out.println("found");}
        if(test.search("brother",null,0,7)){ System.out.println("found");}
        if(test.search("bo",null,0,2)){ System.out.println("found");}

        if(test.search("stress",null,0,6)){ System.out.println("found");}
        if(test.search("test",null,0,4)){ System.out.println("found");}
//        System.out.println("here");
        if(test.search("aqui",null,0,4)){
            System.out.println("found");
        } else {System.out.println("Not Found");}
    }

}
