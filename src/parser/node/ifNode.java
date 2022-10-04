package parser.node;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class ifNode extends  Node{
    private Node boolConditionExp;
    private ArrayList<Node> statements;
    public ifNode(Node boolConditionExp, ArrayList<Node> statements) {
        this.boolConditionExp = boolConditionExp;
        this.statements = statements;
    }
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
        return boolConditionExp.ToString() + " statemnets: "+ArraysToString(statements);
    }
}
