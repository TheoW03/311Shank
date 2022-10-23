package parser.node;

import parser.node.StatementNode.StatementNode;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class WhileNode extends Node{
    private Node bool;
    private ArrayList<Node> statements;
    public WhileNode(Node bool,ArrayList<Node> statements) {
        this.statements = statements;
        this.bool = bool;

    }

    @Override
    public String ToString() {
        return bool.ToString();
    }
}
