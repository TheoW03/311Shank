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
public class WriteNode  extends BuiltInFunctionNode {
    public WriteNode(Token name, ArrayList<Node> dataType) {

    }






    @Override
    public void execute(ArrayList<DataType> list) {
        if(list.size() < 1){
            throw new RuntimeException("Too small");
        }
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(0).ToString());
        }
    }
    @Override
    public String ToString() {
        return "write";
    }
}
