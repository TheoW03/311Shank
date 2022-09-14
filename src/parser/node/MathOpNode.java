package parser.node;

import lexer.Token;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */

public class MathOpNode extends Node{
//    public Node left;
//    public Node right;
    private Token OP;
    private String OP2;
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
    public MathOpNode(Node left, Node right, Token token) {
        this.left = left;
        this.right = right;
        this.OP = token;

    }
//    public MathOpNode(Node left, Node right){
//        this.left = left;
//        this.right = right;
//    }

    public MathOpNode(String OP2){
        this.OP2 = OP2;

    }
    public MathOpNode(Node left,  Token OP){
        this.left = left;
        this.OP = OP;
    }

    public String  getOP() {
        return OP.getTokenAsString();
    }

    //parenthisis
    public MathOpNode(MathOpNode exp){

    }
    public String getOP2(){
        return OP2;
    }
    @Override
    public String ToString() {
        if(OP2 == null){
            return left.ToString() +" "+right.ToString() + " " + OP;
        }
        return OP2;
    }


}
