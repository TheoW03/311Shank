package parser.node.builtInFunctionNode;


import lexer.Token;
import parser.DataType.DataType;
import parser.node.Node;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class ReadNode  extends BuiltInFunctionNode {
    public ReadNode() {

    }
    @Override
    public void execute(ArrayList<DataType> list) {
        Scanner input = new Scanner(System.in);
        String inp = input.next();
        list.get(0).FromString(inp);
    }
    @Override
    public String ToString() {
        return "Read";
    }
}
