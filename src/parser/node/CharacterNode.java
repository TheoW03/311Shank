package parser.node;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class CharacterNode extends Node{
    private char data;
    public CharacterNode(char data) {
        this.data =data;
    }
    public  char getData(){
        return data;
    }
    @Override
    public String ToString() {
        return String.valueOf(data);
    }
}
