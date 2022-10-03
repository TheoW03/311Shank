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

    /**
     *
     * @return what it is
     */
    @Override
    public String ToString() {
        return var.ToString() + " "+math.ToString();
    }
}
