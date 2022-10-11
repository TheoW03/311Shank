package parser.node.builtInFunctionNode;


import lexer.Token;
import parser.DataType.DataType;
import parser.node.FloatNode;
import parser.node.Node;

import java.util.ArrayList;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class RealToIntNode extends BuiltInFunctionNode {
    public RealToIntNode(Token name, ArrayList<Node> dataType) {

    }

    /**
     * beta code. Its me interrpting the code form 6. Idk if its right but. Judge it.
     * @param list lis
     */
    @Override
    public void execute(ArrayList<DataType> list) {
        try {
            FloatNode a = new FloatNode(Float.parseFloat(list.get(0).ToString()));
            list.get(0).FromString(a.ToString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String ToString() {
        return "real to node ";//Is this supposed to return null
    }
}
