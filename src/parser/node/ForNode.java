package parser.node;

import lexer.*;
import parser.node.StatementNode.VaraibleReferenceNode;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */

public class ForNode extends Node {
    private Node incimantor;
    private Node begin;
    private Node end;
    private ArrayList<Node> statements;
    public ForNode(VaraibleReferenceNode incriminator, Node begin, Node end, ArrayList<Node> statements) {
        this.begin = begin;
        this.end = end;
        this.statements = statements;
        this.incimantor = incriminator;


    }

    /**
     * @param params2 an array
     * @return the better version of arrayToString() I wrote. to avoid It printing a meme ad
     */
    public String ArraysToString(ArrayList<Node> params2) {
        StringBuilder t = new StringBuilder("[");
        for (Node node : params2) {
            t.append(node.ToString()).append(",");
        }
        t.append("]");
        return t.toString();
    }

    @Override
    public String ToString() {
        return "For loop: "+
               " in: "+this.incimantor.ToString() +
                " begin: " + this.begin.ToString() +
                " end: "+ this.end.ToString()+
                " statements:  "+ArraysToString(this.statements);
    }
}
