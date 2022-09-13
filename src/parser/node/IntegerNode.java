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
    private int IntegerANomerul;
    public Node right;
    public Node left;
    public IntegerNode(int integerANomerul) {
        this.IntegerANomerul=integerANomerul;
    }
    public int getIntegerANomerul(){
        return IntegerANomerul;
    }

    @Override
    public String ToString() {
        return String.valueOf(getIntegerANomerul());
    }
}
