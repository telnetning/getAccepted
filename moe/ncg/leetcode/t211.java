package moe.ncg.leetcode;

/*
 * Trie 的节点到用的时候才分配
 * 因为Trie树比较稀疏，分配过早会
 * 占用大量空间
 *
 * 另外，该题可以直接用 HashMap 解决。
 * 
 */
import java.util.Arrays;

public class t211 {
    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();                   
        //wd.addWord("adc");
        //下面是测试数据
        wd.addWord("at");
        wd.addWord("and");
        wd.addWord("an");
        wd.addWord("add");
        System.out.println(wd.search("a"));
        System.out.println(wd.search(".at"));
        //System.out.println(wd.search(""));
        //wd.addWord("");
        wd.addWord("bat");
        System.out.println(wd.search(".at"));
        System.out.println(wd.search("an."));
        System.out.println(wd.search("a.d."));
        System.out.println(wd.search("b."));
        System.out.println(wd.search("a.d"));
        System.out.println(wd.search("."));
    } 
}

//构造的数据结构
class WordDictionary {
    Node root = new Node();

    public WordDictionary() {
    }

    public void addWord(String word) {
        char[] wordArr = word.toCharArray();    

        if(wordArr.length == 0) return;
        
        Node ptr = root;
        for(int i = 0; i < wordArr.length; i++) {
            if(ptr.arr[wordArr[i] - 'a'] == null) {
                ptr.arr[wordArr[i] - 'a'] = new Node(wordArr[i]); 
            } 

            ptr = ptr.arr[wordArr[i] - 'a'];
        }
        ptr.asEnd = true;
    }
    
    
    public boolean search(String word) {
        return search(word.toCharArray(), root);
    }
    
    private boolean search(char[] word, Node ptr) {
        if(word[0] == '.') {
            if(word.length == 1) {
                for(int i = 0; i < 26; i++) {
                    if(ptr.arr[i] != null && ptr.arr[i].asEnd)  return true;
                }     
                return false; 
            }

            for(int i = 0; i < 26; i++) {
                if(ptr.arr[i] != null) {
                    if(search(Arrays.copyOfRange(word, 1, word.length), ptr.arr[i])) return true;
                }
            }
            return false;

        } else {

            if(ptr.arr[word[0] - 'a'] == null) return false;
            if(word.length == 1) return ptr.arr[word[0] - 'a'].asEnd ? true : false;
            return search(Arrays.copyOfRange(word, 1, word.length), ptr.arr[word[0] - 'a']);

        }
    }
}

class Node {
    Node[] arr;
    char val;
    boolean asEnd = false; 

    public Node() {
        this.arr = new Node[26]; 
    }

    public Node(char val) {
        this.val = val; 
        this.arr = new Node[26];
    }
}
