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
    public FunctionNode(String ident, ArrayList<Node> params, ArrayList<Node> varaibles,ArrayList<Node> statements) {
        this.ident = ident;
        this.params = params;
        this.varaibles = varaibles;
        this.statements = statements;

    }
    public String getName(){
        return ident;
    }
    public ArrayList<Node> getStatements() {
        return statements;
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
     * the better version of arrayToString() I wrote. to avoid It printing a meme ad
     */
    public String ArraysToString(ArrayList<Node> params2) {
        StringBuilder t = new StringBuilder("[");
        for (Node node : params2) {
            t.append(node.ToString()).append(",");
        }
        t.append("]");
        return t.toString();
    }

    /**
     * (all of them are.
     * This returns name
     * @return formated strings for everything. stcictly for debugging pruposes
     */
    public String getIdent(){
        return this.ident;
    }

    /**
     *
     * @return CLean up my ToSTreing a little more
     */
    public String params(){
        return ArraysToString(params);
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
