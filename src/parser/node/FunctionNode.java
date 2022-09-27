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
    private ArrayList<Node> params,varaibles,statements;
    private String ident;
    public FunctionNode(String ident, ArrayList<Node> Params, ArrayList<Node> varaibles,ArrayList<Node> statements) {
        this.ident = ident;
        this.params = Params;
        this.varaibles = varaibles;
        this.statements = statements;

    }

    /**
     *
     * @return gets the stuff.
     */
    public ArrayList<Node> getVaraibles(){
        return varaibles;
    }
    public ArrayList<Node> getParams(){
        return params;
    }

    /**
     *
     * @param params2 an array
     * @return
     * the better version of arrayToString()
     */
    public String ArraysToString(ArrayList<Node> params2) {
        StringBuilder t = new StringBuilder("[");
        for(int i = 0; i < params2.size(); i++){
            t.append(params2.get(i).ToString()).append(",");
        }
        t.append("]");
        return t.toString();
    }
    public String getIdent(){
        return this.ident;
    }
    public String vars(){
        return ArraysToString(varaibles);
    }
    public String statemnets(){
        return ArraysToString(statements);
    }

    /**
     *
     * @return everything.
     * Soi libre. Adidos mochacos. gets hit by bus.
     */
    @Override
    public String ToString() {
        return "name: "+ident+" params: "+ArraysToString(params)+" constants/vars: "+ArraysToString(varaibles)
                +"statements: "+ArraysToString(statements);
    }
}
