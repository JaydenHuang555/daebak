package org.jaq.util;

public final class Stack<T> {

    StackNode head, tail;

    Stack(){
        this.head = null;
        this.tail = null;
    }

    public void push(T val){
        StackNode node = new StackNode(val);
        if(head == null){
            head = node;
            tail = head;
        }
        else {
            tail.next = node;
            tail.next.prev = tail;
            tail = tail.next;
        }
    }
    @SuppressWarnings("unchecked")
    public T peek(){
        return (T)tail.val;
    }
    @SuppressWarnings("unchecked")
    public T pop(){
        T val = peek();
        if(tail == head) head = null;
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return val;
    }

    public boolean isEmpty(){
        return head == null;
    }

}

final class StackNode {
    Object val;
    StackNode prev, next;

    StackNode(Object val){
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

