package parser;

import lexer.Token;
import parser.node.Node;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Parser {
    ArrayList<Token> tokens;
    public Parser(ArrayList<Token> tokens) {
    }

    /**
     *
     * @return node of this
     *
     */
    public Node parserMethod(){
        for(int i = 0; i < tokens.size(); i++){
            if(this.matchAndRemove(String.valueOf(tokens.get(i))) != null){


            }
        }
        /**
         * ArrayOfTokens
         * loop through array
         *
         * For loop (psuedo code)
         *     if(conditions for an OP meant/MatchRemove is true){
         *          new MathNode((node)goes right here)
         *     }
         */
        return null;
    }
    public String matchAndRemove(String token){
        if(token.equals(tokens.get(0).getTokenName()))   {
            tokens.remove(tokens.get(0));
            return token;
        }
        return null;
    }

}
