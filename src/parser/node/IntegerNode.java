package parser.node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class IntegerNode extends  Node{
    private int number;
    public Node right;
    public Node left;

    public IntegerNode(int integerANomerul) {
        this.number=integerANomerul;
    }
    public int getIntegerANomerul(){
        return number;
    }

    @Override
    public String ToString() {
        return String.valueOf(getIntegerANomerul());
    }
}
