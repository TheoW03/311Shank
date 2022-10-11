package parser.node.FunctionCallNode;

import lexer.Token;
import parser.node.Node;

import java.util.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class FunctionCallNode extends CallableNode {
    private Token name;
    private boolean builtIn;
    public FunctionCallNode(Token name, ArrayList<Node> params, boolean builtIn) {
        super(params);
        this.name = name;
        this.builtIn = builtIn;
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
        return "function call: "+ArraysToString(this.params) + " "+name + " "+builtIn;
    }
}
