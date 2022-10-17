package parser.node.builtInFunctionNode;

import lexer.Token;
import parser.DataType.DataType;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.Node;

import java.util.ArrayList;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class IntToRealNode extends BuiltInFunctionNode {

//    public IntToRealNode(Token name, ArrayList<Node> dataType) {
//
//    }
    @Override
    public void execute(ArrayList<DataType> list) {
        try {
            int b = Integer.parseInt(list.get(0).ToString());
            float a = (float) b;
            FloatNode newNum = new FloatNode(a);
//            list.get(0).FromString(b)
            System.out.println(a);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String ToString() {
        return "con1, IntToReal";
    }

}
