package org.jaq.util;

import org.jetbrains.annotations.NotNull;

public class StringBuilder {
    
    private char buff[];
    private int len, cap;


    private void resize(){
        char next[] = new char[cap *= 2];
        for(int i = 0; i < cap / 2; i++) next[i] = buff[i];
        buff = next;
    }

    public StringBuilder(){
        this.cap = 1 << 3;
        this.len = 0;
        this.buff = new char[this.cap];
    }

    public void append(char c){
        buff[len++] = c;
        if(len == cap) resize();
    }

    public void append(@NotNull String s){
        for(int i = 0; i < s.length(); i++) append(s.charAt(i));
    }

    public void append(@NotNull Object obj){
        append(obj.toString());
    }

    public void insert(char c, int index){
        if(index >= cap) resize();
        buff[index] = c;        
    }

    @Override
    public String toString(){
        return new String(buff);
    }

}
