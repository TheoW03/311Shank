package parser.node;

import parser.node.Node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class RepeatNode extends Node {
    private ArrayList<Node> statement;
    private Node boolExp;
    public RepeatNode(ArrayList<Node> statemnet,Node boolExp) {
        this.boolExp = boolExp;
        this.statement = statemnet;
    }

    @Override
    public String ToString() {
        return boolExp.ToString();
    }
}
