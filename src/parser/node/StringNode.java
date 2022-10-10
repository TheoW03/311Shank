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
public class StringNode extends Node{
    private Token s;
    public StringNode(Token s) {
        this.s = s;
    }

    @Override
    public String ToString() {
        return s.getTokenValue();
    }
}
