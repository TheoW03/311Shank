package parser.node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */

public class MathOpNode extends Node{
    private Node leftN;
    private Node rightN;
    private char OP;

    private enum Operands{
        PLUS,
        SUBTRACT,
        DIVIDE,
        MULTIPLY;
    }
    public MathOpNode(Node left, Node right, char OP) {
        this.leftN = left;
        this.rightN = right;
        this.OP = OP;
    }
    @Override
    public String ToString() {
        return leftN.ToString() + " " + rightN.ToString();
    }


}
