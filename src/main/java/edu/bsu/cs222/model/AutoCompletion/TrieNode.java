package edu.bsu.cs222.model.AutoCompletion;
import edu.bsu.cs222.model.NationalPokedex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TrieNode { 
    char data;
    LinkedList<TrieNode> children;
    TrieNode parent;
    boolean isEnd;
    public TrieNode(char c){
        data = c;
        children = new LinkedList<TrieNode>();
        isEnd = false;
    }
    public TrieNode getChild(char c){
        if (children != null)
            for (TrieNode eachChild : children)
                if (eachChild.data == c)
                    return eachChild;
        return null;
    }
    protected List<String> getWords(){
        List<String> list = new ArrayList<String>();
        if (isEnd){
            list.add(toString());
        }
        if (children != null){
            for (int i =0; i <children.size(); i++){
                if (children.get(i) != null){
                    list.addAll(children.get(i).getWords());
                }
            }
        }
        return list;
    }
    public String toString(){
        if (parent == null){
            return "";
        }else {
            return parent.toString()+ new String(new char[] {data});
        }
    }
    public static class Trie {
        private TrieNode root;

        public Trie(){
            root = new TrieNode(' ');
        }
        public void insert(String word){
            if (search(word)== true)
                return;

            TrieNode current = root;
            TrieNode pre;
            for (char ch: word.toCharArray()){
                pre = current;
                TrieNode child = current.getChild(ch);
                if (child != null){
                    current = child;
                    child.parent = pre;
                }else{
                    current.children.add(new TrieNode(ch));
                    current = current.getChild(ch);
                    current.parent = pre;
                }
            }
            current.isEnd = true;
        }
        public boolean search(String word){
            TrieNode current = root;
            for(char ch : word.toCharArray()){
                if (current.getChild(ch)== null)
                    return false;
                else{
                    current = current.getChild(ch);

                }
            }
            if (current.isEnd == true){
                return true;
            }
            return false;
        }

    }

    public class AutoComplete {
        public List<String>AutoComplete(String prefix){
            TrieNode root = null;
            TrieNode lastNode = root;
            for (int i =0; i< prefix.length(); i++){
                lastNode = lastNode.getChild(prefix.charAt(i));
                if (lastNode == null)
                    return new ArrayList<String>();
            }
            return lastNode.getWords();
        }
        public class AutocompletewithTire{
            public static void main (String[] args){
                Trie t = new Trie();
                String NationalPokedex = new String();
                t.insert(NationalPokedex);

            }
        }
    }
}
//---------------------------------------------------------------------------------
package edu.bsu.cs222.view;


        import edu.bsu.cs222.model.NationalPokedex;

        import javax.swing.*;
        import java.util.HashMap;
        import java.util.LinkedList;
        import java.util.List;


public class AutoCompletion {
    private class Node{
        String prefix;
        HashMap<Character, Node> children;
        boolean isWord;

        private Node(String prefix){
            this.prefix = prefix;
            this.children = new HashMap<Character, Node>();
        }
    }
    private Node trie;
    private List<String> pokemonName;
    private final NationalPokedex Name = new NationalPokedex(pokemonName);
    public AutoCompletion(String[] Name){
        trie = new Node("");
        for (String s : Name) insertWord(s);
    }
    private void insertWord(String s){
        Node curr = trie;
        for (int i = 0; i< s.length(); i++){
            if (!curr.children.containsKey(s.charAt(i))){
                curr.children.put(s.charAt(i),
                        new Node(s.substring(0, i + 1)));
            }
            curr = curr.children.get(s.charAt(i));
            if (i == s.length()-1 )curr.isWord = true;
        }
    }
    public List<String> getWordsForPrefix(String pre){
        List<String> results = new LinkedList<String>();
        Node curr = trie;
        for (char c : pre.toCharArray()){
            if (curr.children.containsKey(c)){
                curr = curr.children.get(c);
            } else {
                return results;
            }
        }
        findAllChildWords(curr, results);
        return results;
    }
    private void findAllChildWords(Node n, List<String> results){
        if (n.isWord) results.add(n.prefix);
        for (Character c : n.children.keySet()){
            findAllChildWords(n.children.get(c), results);
        }
    }
}
