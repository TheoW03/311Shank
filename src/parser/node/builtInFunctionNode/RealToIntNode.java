package parser.node.builtInFunctionNode;


import lexer.Token;
import lexer.UnauthTokenException;
import parser.DataType.DataType;
import parser.DataType.FloatDataType;
import parser.DataType.IntDataType;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class RealToIntNode extends BuiltInFunctionNode {
//    public RealToIntNode(Token name, ArrayList<Node> dataType) {
//
//    }

    /**
     * beta code. Its me interrpting the code form 6. Idk if its right but. Judge it.
     * @param list lis
     */
    @Override
    public void execute(ArrayList<DataType> list) {
        try {
            if(list.get(0).checkIfCOnst()){
                throw new UnauthTokenException(list.get(0).ToString()+"cant use this as return value");
            }
            float b = Float.parseFloat(list.get(0).ToString());
            int a = (int) b;
            IntegerNode newNum = new IntegerNode(a);
            list.get(0).FromString(newNum.ToString());
            System.out.println(a);
//
//           list.get(0).FromString(newNum.ToString());

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String ToString() {
        return "real to node ";//Is this supposed to return null
    }
}
