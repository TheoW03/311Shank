package parser;

import lexer.Token;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.MathOpNode;
import parser.node.Node;

import java.lang.reflect.Array;
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
        ArrayList<Node> nodeLis = new ArrayList<Node>();
        for(int i = 0; i < tokens.size(); i++){
            String tokenInList = matchAndRemove(tokens.get(i).getTokenName());
            if(tokenInList != null){
                try{
                    float a = Float.parseFloat(tokens.get(i).getTokenName());
                    FloatNode b = new FloatNode(a);
                    nodeLis.add(b);

                }catch (NumberFormatException e1){
                    try{
                        int a = Integer.parseInt(tokens.get(i).getTokenName());
                        IntegerNode b = new IntegerNode(a);
                        nodeLis.add(b);
                    }catch (NumberFormatException e2){
//                        MathOpNode a = new MathOpNode(tokenInList);
                        int in = i;
                        Node getNode = null;
                        while(in > 0){
                            getNode = nodeLis.get(in);
                            if(getNode instanceof IntegerNode || getNode instanceof FloatNode){
                                break;
                            }
                            in--;
                        }
                        if(in == 0 ){
                            MathOpNode a = new MathOpNode(tokens.get(i).toString());
                            nodeLis.add(a);
                        }
                        /**
                         * pseudo code ->
                         *
                         * checks if Op
                         * if its OP it will go back until instance of int Node or FLoat Node
                         * if its EOL. it will check for an OP.
                         *
                         *
                         */

                    }
                }
            }else{

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
