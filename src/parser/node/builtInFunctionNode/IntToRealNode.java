package parser.node.builtInFunctionNode;

import lexer.Token;
import lexer.UnauthTokenException;
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

    /**
     *
     * @param list the list u get. another pass by ref
     *
     */
    @Override
    public void execute(ArrayList<DataType> list) {
        try {
            if(list.get(0).checkIfCOnst()){
                throw new UnauthTokenException(list.get(0).ToString()+"cant use this as return value");
            }
            int b = Integer.parseInt(list.get(0).ToString());
            float a = (float) b;
            FloatNode newNum = new FloatNode(a); //conversion
//            list.get(0).FromString(b)


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String ToString() {
        return "con1, IntToReal";
    }

}
