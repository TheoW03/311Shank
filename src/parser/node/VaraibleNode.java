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
    @Override
    public String ToString() {
        if(value == null){
            return "type: "+type.getTokenValue()+" name: "+name.getTokenValue() + " value: null";
        }
        return "type: "+type.getTokenValue()+" name: "+name.getTokenValue() + " value: "+value.ToString();
    }
}
