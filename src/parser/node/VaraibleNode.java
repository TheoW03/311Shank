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
public class VaraibleNode extends Node{
    private Token name;
    private Node type;
    public VaraibleNode(Node type, Token name, boolean isConstant) {
        this.type=type;
        this.name = name;
        
    }
    public VaraibleNode(Token name){
        this.name = name;
        this.type = null;
    }

    @Override
    public String ToString() {
        return name.getTokenValue() + " "+type;
    }
}
