package org.jaq.util;

public class InvalidIndexException extends RuntimeException {
    public InvalidIndexException(int index){
        super(String.format("index %d is an invalid index", index));
    }
}