package parser.node;

import lexer.Token;
import lexer.UnauthTokenException;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class BooleanWordNode extends Node{
    private Token condition;
    public BooleanWordNode(Token conditon) {
        this.condition = conditon;
    }
    public boolean evalu(){
        if(condition.getTokenEnum() == Token.OPTokens.TRUE){
            return true;
        }
        if(condition.getTokenEnum() == Token.OPTokens.FALSE){
            return false;
        }else {
            throw new UnauthTokenException("not true pr false");
        }
    }
    @Override
    public String ToString() {
        return condition.getTokenValue();
    }
}
