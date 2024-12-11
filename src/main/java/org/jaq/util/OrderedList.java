package org.jaq.util;

public class OrderedList<T> {
    private Object subList[];

    private int size, cap;

    public OrderedList(){
        this.cap = 1 << 3;
        this.subList = new Object[this.cap];
        this.size = 0;
    }

    public void add(T item){
        subList[size++] = item;
        if(size == cap) {
            Object next[] = new Object[cap *= 2];
            for(int i = 0; i < cap / 2; i++) next[i] = subList[i];
            subList = next;
        }
    }
    @SuppressWarnings("unchecked")
    public T get(int index){
        if(index > size || index < 0) throw new InvalidIndexException(index);
        return (T)subList[index];
    }

    public int getSize(){
        return size;
    }


    public boolean equals(OrderedList<T> other) {
        if(other.getSize() != getSize()) return false;
        for(int i = 0; i < other.getSize(); i++){
            if(other.get(i) != subList[i]) return false;
        }
        return true;
    }

    public String toString(){
        return null;
    }

}