package parser.node;

import lexer.Token;

import java.util.*;
import java.io.*;
import java.util.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class FunctionNode extends Node{
    private ArrayList<Node> params,varaibles;
    private String ident;
    public FunctionNode(String ident, ArrayList<Node> Params, ArrayList<Node> varaibles) {
        this.ident = ident;
        this.params = Params;
        this.varaibles = varaibles;

    }

    @Override
    public String ToString() {
        return "name: "+ident+" params: "+Arrays.toString(params.toArray())+" constants: "+Arrays.toString(varaibles.toArray());
    }
}
