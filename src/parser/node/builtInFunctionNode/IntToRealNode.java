package parser.node.builtInFunctionNode;

import lexer.Token;
import parser.DataType.DataType;
import parser.node.Node;

import java.util.ArrayList;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class IntToRealNode extends BuiltInFunctionNode {

    public IntToRealNode(Token name, ArrayList<Node> dataType) {

    }
    @Override
    public void execute(ArrayList<DataType> list) {


    }
    @Override
    public String ToString() {
        return "con1, IntToReal";
    }

}
