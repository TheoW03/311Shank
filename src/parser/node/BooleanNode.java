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
public class BooleanNode extends  Node{
    private Token condition;
    public BooleanNode(Node left, Node right, Token condition) {
        this.left = left;
        this.right = right;
        this.condition = condition;
    }
    public Token.OPTokens getCondition(){
        return condition.getTokenEnum();
    }

    @Override
    public String ToString() {
        return left.ToString() + " "+ right.ToString() + " "+condition.getTokenValue();
    }
}
