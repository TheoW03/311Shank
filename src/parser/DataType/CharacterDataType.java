package parser.DataType;

import lexer.UnauthTokenException;
import parser.node.CharacterNode;
import parser.node.Node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class CharacterDataType extends DataType{
    private Node data;
    private boolean isConstant;
    public CharacterDataType(Node data, boolean isConstant) {
        this.data = data;
        this.isConstant = isConstant;
    }

    @Override
    public String ToString() {
        return data.ToString();
    }

    @Override
    public void FromString(String input) {
        if(input.length() > 1){
            throw new UnauthTokenException("to big");
        }
        this.data = new CharacterNode(input.charAt(0));
    }

    @Override
    public boolean checkIfCOnst() {
        return isConstant;
    }
}
