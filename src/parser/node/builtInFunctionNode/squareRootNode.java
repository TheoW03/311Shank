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
public class squareRootNode  extends BuiltInFunctionNode {
    public squareRootNode() {

    }

    /**
     *
     * @param list
     */
    @Override
    public void execute(ArrayList<DataType> list) {
        double a = Math.sqrt(Double.parseDouble(list.get(0).ToString()));
        if(list.size() >= 2){
            list.get(1).FromString(String.valueOf(a));
        }

    }
    @Override
    public String ToString() {
        return "sqrt";
    }




}
