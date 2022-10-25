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
    private ArrayList<Node> elseIf;
    public ifNode(Node boolConditionExp, ArrayList<Node> statements, ArrayList<Node> elseIf) {
        this.boolConditionExp = boolConditionExp;
        this.statements = statements;
        this.elseIf = elseIf;
    }
    public ArrayList<Node> getStatements(){
        return statements;
    }
    public Node getBoolConditionExp(){
        return boolConditionExp;
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
        return "if "+boolConditionExp.ToString() + " statemnets: "+ArraysToString(statements) + " else: "+ArraysToString(elseIf);
    }
}
