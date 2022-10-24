package parser.node.StatementNode;

import parser.node.Node;
import parser.node.StatementNode.StatementNode;
import parser.node.VaraibleNode;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc j
 */
public class AssignmentNode extends StatementNode {
    private Node var;
    private Node math;
    public AssignmentNode(Node var, Node math){
        this.var = var;
        this.math = math;
    }
    public Node getVar(){
        return var;

    }
    public Node getMath(){
        return math;

    }
    public String getVarName(){
        return ((VaraibleReferenceNode) var).getName().getTokenValue();
    }

    /**
     *
     * @return what it is
     */
    @Override
    public String ToString() {
        return var.ToString() + " "+math.ToString();
    }
}
