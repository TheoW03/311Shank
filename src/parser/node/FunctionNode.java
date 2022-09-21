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

    /**
     *
     * @return everything.
     * Soi libre. Adidos mochacos. gets hit by bus.
     */
    @Override
    public String ToString() {
        return "name: "+ident+" params: "+ArraysToString(params)+" constants: "+ArraysToString(varaibles);
    }
}
