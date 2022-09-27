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
    private VaraibleReferenceNode var;
    private Node math;
    public AssignmentNode(VaraibleReferenceNode var, Node math){
        this.var = var;
        this.math = math;
    }
    @Override
    public String ToString() {
        return var.ToString() + " "+math.ToString();
    }
}
