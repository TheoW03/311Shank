package parser.DataType;

import lexer.Token;
import parser.node.BooleanNode;
import parser.node.BooleanWordNode;
import parser.node.Node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class BooleanDataType extends DataType {
    private boolean isConsant;
    private Node data;

    /**
     * figure out why out put in params is null and fix
     * add booleans
     * add boolean logic
     */
    public BooleanDataType(Node data, boolean isConsant) {
        this.data = data;
        this.isConsant = isConsant;

    }


    @Override
    public void FromString(String input) {
        if(input.equals("true")){
            this.data = new BooleanWordNode(new Token(Token.OPTokens.TRUE, "true"));
        }else{
            this.data = new BooleanWordNode(new Token(Token.OPTokens.FALSE, "false"));
        }


    }

    @Override
    public boolean checkIfCOnst() {
        return this.isConsant;
    }

    @Override
    public String ToString() {
        if (Objects.equals(data.ToString(), "0")) {
            return "false";
        } else {
            return "true";
        }
    }
}
