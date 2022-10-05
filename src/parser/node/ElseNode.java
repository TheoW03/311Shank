package parser.node;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class ElseNode extends Node {
    private Node condition;
    private ArrayList<Node> statements;
    public ElseNode(Node condition, ArrayList<Node> statements) {
        this.condition = condition;
        this.statements = statements;
    }
    public ElseNode(ArrayList<Node> statements) {
        this.statements = statements;

    }
    /**
     *
     * @param params2 arrayList
     * @return String reop.
     * The reason why I have tis is to dispaly the statement method bc Java.Util.ArrayList.ToString sucks.
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
        if(condition == null){
            return ArraysToString(statements);
        }
        return "statements: "+ArraysToString(statements) +" condition: "+condition.ToString();
    }
}
