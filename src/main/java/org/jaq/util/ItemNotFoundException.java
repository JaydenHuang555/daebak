package org.jaq.util;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(){
        super("unable to find item in table");
    }

}