package scrabble;
import java.util.*;
import java.io.*;


public class Dictionary {

    /* todo :
    *read in the dictionary and then sort the words in a trie
    *
    */
    private List<TrieNode> dictionary;
    private List<String> hold;
    public Dictionary(){
        dictionary = new LinkedList<>();
        hold = new LinkedList<>();
        readDictFile();
        populate();


    }


    public void readDictFile(){
        try(
                // use this for the jar file?
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
            insert(s);
        }
    }

    public void insert(String word){
        word.toLowerCase();
        int length = word.length();

        if(dictionary.isEmpty()){
            dictionary.add(new TrieNode(word.charAt(0)));
        }

        TrieNode temp = null;
        for(TrieNode t: dictionary){
            if(word.charAt(0) == t.getLetter()){
                temp = t;
                break;
            }
        }

        if(temp == null){
            temp = new TrieNode(word.charAt(0));
            dictionary.add(temp);
        }

        int i=1;
        do{
            boolean exists = false;

            if(temp.getBranch().isEmpty()){
                temp.getBranch().add(new TrieNode(word.charAt(i)));
            }

            for(TrieNode t: temp.getBranch()){
                if(word.charAt(i) == t.getLetter()){
                    temp = t;
                    exists = true;
                    break;
                }

            }
            if(!exists){
                TrieNode hold = new TrieNode(word.charAt(i));
                temp.getBranch().add(hold);
                temp = hold;
            }
            if(i == length -1){
                temp.isWord();
            }
            i++;

        }while(i<length);
    }

    /**Searches the Trie for the word passed into the function
     * @param word
     * @return String
     */
    public boolean search(String word){
        word.toLowerCase();
        int length = word.length();


        List<TrieNode> hold = dictionary;

        boolean found = false;
        TrieNode temp = null;
        for(TrieNode t: dictionary){
            if(word.charAt(0) == t.getLetter()){
                temp = t;
            }
        }
        if(temp == null){
            return false;
        }
        int i=1;

        do{

           for(TrieNode t: temp.getBranch()){
               if(word.charAt(i) == t.getLetter()){

                   temp =t;
                   break;
               }
           }
           if(temp != null && i == length-1 && temp.isWord){
               found = true;
           }
            i++;

        }while(i<length);

        return found;
    }
    /**Reads in file from input
     *
     * @param file
     * @return void
     */
    public void readFile(String file){
        hold = new LinkedList<>();

        /*read in the file and store it in the hold list*/


    }

    public class TrieNode{
        private String word;
        private boolean isWord;
        private List<TrieNode> branch;
        private char letter;

        public TrieNode(char let){
            letter = let;
            branch = new LinkedList<>();

        }

        public void isWord(){
            isWord = true;

        }
        public List<TrieNode> getBranch(){
            return branch;
        }

        public char getLetter(){
            return letter;
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



        if(test.search("there")){ System.out.println("found");}
        if(test.search("brother")){ System.out.println("found");}
        if(test.search("bo")){ System.out.println("found");}

        if(test.search("stess")){ System.out.println("found");}
        if(test.search("test")){ System.out.println("found");}
        if(test.search("aqui")){
            System.out.println("found");
        } else {System.out.println("Not Found");}

    }

}
