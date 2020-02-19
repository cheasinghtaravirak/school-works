/**
 * class MyHashTable. A simple HashTable. Handle collision by chain
 * 
 * @author Singhtaravirak Chea
 * @version Mar 11, 2019
 */
import java.util.ArrayList;

public class MyHashTable<K extends Comparable<K>, V>
{
    private ArrayList<MyHashEntry<K, V>> table;
    private int count; // how many elements in table
    
    /**
     * Constructor. Constructor an empty MyHashTable with given number of Buckets
     * @param tableSize The number of Buckets of the table
     */
    public MyHashTable(int tableSize){
    	this.table = new ArrayList<MyHashEntry<K, V>>(tableSize); 
    	//initialize all table elements to null 
    	for (int i = 0; i<tableSize; i++) {
    		table.add(null); 
    	}

    }
    
    /**
     * constructor. Construct an empty MyHashTable with capacity 10 buckets
     */
    public MyHashTable(){
    	this.table = new ArrayList<MyHashEntry<K, V>>(10);
    }
    
    /**
     * get the number of elements in the table
     * @return the number of elements in the table
     */
    public int size(){
       return table.size(); 
    }
    
    //hash function 
    public int hash(K key) {
    	return Integer.parseInt(key.toString()) % table.size();
    }
    
    /**
     * clear the table
     */
    public void clear(){
    	this.table.clear(); 
    }
    
    /**
     * get the value with given key.
     * @param key The given key
     * @return the value that have the key matches the given key. If no such a value, return null
     */
    public V get(K key){
       for (int i = 0; i < this.table.size(); i++) {
    	   if(this.table.get(i).getKey() == key) {
    		   return this.table.get(i).getValue();
    	   }
       }
       return null; 
    }
    
    /**
     * insert (key, value) pair into the table
     * @param key The key of the pair
     * @param value The value of the pair
     */

    
    public void insert(K key, V value) {
        MyHashEntry<K, V> entry = new MyHashEntry(key, value); 
//        int bucket = Integer.parseInt((entry.getKey()).toString()) % table.size();
        int bucket = hash(entry.getKey());  
        MyHashEntry<K, V> existing = table.get(bucket); 
        if (existing == null) {
            table.set(bucket, entry);
            count++;
        } else {
            // compare the keys see if key already exists
            while (existing.next != null) {
                if (existing.getKey().equals(key)) {
                    existing.setValue(value); 
                    return;
                }
                existing = existing.next;
            }
            if (existing.getKey().equals(key)) {
                existing.setValue(value);
            } else {
                existing.next = entry;
                count++;
            }
        }
    } 
    /**
     * remove the value with given key from the table
     * @param key The given key
     * @return the value whose key matches the given key. If no such value, null is returned
     */
    public V remove(K key){
        for(int i = 0; i < table.size(); i++) {
        	if(table.get(i) != null && table.get(i).getKey().equals(key)) {
        		V removedValue = table.get(i).getValue(); 
        		table.remove(i); 
        		return removedValue; 
        	}
        }
        return null; 
    }
    
    /**
     * check if the table is empty,i.e. no entry
     * @return true if the table holds no elements; false otherwise
     */
    public boolean isEmpty(){
        if(this.table.size() == 0) {
        	return true; 
        } else {
        	return false; 
        }
    }
    
    /**
     * return a String representation of the table
     * @return a String representation of the table
     */
    public String toString(){
    	
    	String str = "";
    	for(int i = 0; i<table.size(); i++) {
    		if(table.get(i) != null) {
    			String myStr = "Bucket " + i + " -> " + table.get(i).getValue().toString() + "\n"; 
    			str += myStr; 
    		} else if(table.get(i) == null) {
    			String myStr = "Bucket " + i + " -> " + null + "\n"; 
    			str += myStr;
    		}
    	}
    	return str; 
    	
    }
}
