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
    private Token type;
    private Node value;

    public VaraibleNode(Token type,Node value, Token name, boolean isConstant,boolean global) {
        this.type=type;
        this.name = name;
        this.value = value;
        
    }
    public VaraibleNode(Token name){
        this.name = name;
        this.type = null;
    }

    @Override
    public String ToString() {
        return "type: "+type.getTokenValue()+" name: "+name.getTokenValue() + " type : "+type;
    }
}
