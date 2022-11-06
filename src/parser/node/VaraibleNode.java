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
    private  boolean isConstant;
    private  boolean isGlobal;
    public VaraibleNode(Token type,Node value, Token name, boolean isConstant,boolean global) {
        this.type=type;
        this.name = name;
        this.value = value;
        this.isConstant = isConstant;
        this.isGlobal = global;
        
    }
    public Node getValue(){
        return value;
    }
    public boolean isGlobal(){return isGlobal;}
    public Token getName(){
        return name;
    }
    public Token getType(){
        return type;
    }
    public boolean isConstant(){
        return  this.isConstant;
    }

    @Override
    public String ToString() {
        if(value == null){
            return "type: "+type.getTokenValue()+" name: "+name.getTokenValue() + " value: null";
        }
        return "type: "+type.getTokenValue()+" name: "+name.getTokenValue() + " value: "+value.ToString();
    }
}
