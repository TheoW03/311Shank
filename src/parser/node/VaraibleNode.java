package parser.node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class VaraibleNode extends Node{
    private String name;
    private Node type;
    public VaraibleNode(Node type,String name) {
        this.type=type;
        this.name = name;
        
    }
    public VaraibleNode(String name){
        this.name = name;
        this.type = null;
    }

    @Override
    public String ToString() {
        return null;
    }
}
