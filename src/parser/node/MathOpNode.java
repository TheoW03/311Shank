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
    private String OP;
    private String endOfLine;

    private enum Operands{
        PLUS("+"),
        SUBTRACT("-"),
        DIVIDE("/"),
        MULTIPLY("*");
        String LaOperand;
        Operands(String LaOperand){
            this.LaOperand = LaOperand;
        }
    }
    public MathOpNode(String op){
        this.OP = op;
    }
    public String getOP(){
        return OP;
    }
    @Override
    public String ToString() {
        return endOfLine;
    }


}
