package parser.node.FunctionCallNode;

import parser.node.Node;

import java.util.ArrayList;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */

//change to abstratc.
public abstract class CallableNode extends Node {
    ArrayList<Node> params;

    public CallableNode(ArrayList<Node> params) {
        this.params = params;
    }

    public abstract String ToString();
}
