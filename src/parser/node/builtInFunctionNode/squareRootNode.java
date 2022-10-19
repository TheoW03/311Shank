package parser.node.builtInFunctionNode;


import lexer.Token;
import lexer.UnauthTokenException;
import parser.DataType.DataType;
import parser.node.Node;

import java.util.ArrayList;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class squareRootNode  extends BuiltInFunctionNode {
    public squareRootNode() {

    }

    /**
     *
     * @param list params for mnath.sqrt
     */
    @Override
    public void execute(ArrayList<DataType> list) {
        double a = Math.sqrt(Double.parseDouble(list.get(0).ToString()));
        if(list.size() >= 2){
            if(list.get(1).checkIfCOnst()){
                throw new UnauthTokenException(list.get(1).ToString()+" cant use this as return value");
            }
            list.get(1).FromString(String.valueOf(a));
        }

    }
    @Override
    public String ToString() {
        return "sqrt";
    }




}
