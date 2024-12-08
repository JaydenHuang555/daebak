package org.jaq.util;

public class HashTable<K, V> {
    private Node table[];
    private final int CAP = 1028;

    private final int hash(Object obj){
        return obj.hashCode() << 4;
    }

    public HashTable(){
        this.table = new Node[this.CAP];
    }

    public void put(K key, V val){
        int index = hash(key);
        if(table[index] == null) table[index] = new Node(key, val);
        else {
            Node node = table[index];
            while(node.next != null) node = node.next;
            node.next = new Node(key, val);
        }
    }

    public V get(K key){
        int index = hash(key);
        if(!contains(key)) throw new RuntimeException("error");
        Node node = table[index];
        while(node.next != null){
            if(node.key == key) return (V)node.val;
            node = node.next;
        }
        throw new ItemNotFoundException();
    }

    public V tryToGet(K key){
        try {
            return get(key);
        } catch (Exception e){
            return null;
        }
    }

    public boolean contains(K key){
        return table[hash(key)] != null;
    }

}

class Node {
    public Object key;
    public Object val;
    public Node next;
    Node(Object key, Object val){
        this.key = key;
        this.val = val;
        this.next = null;
    }
}

