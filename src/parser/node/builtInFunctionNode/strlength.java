package parser.node.builtInFunctionNode;

import parser.DataType.DataType;

import java.util.ArrayList;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class strlength extends BuiltInFunctionNode{
    public strlength() {

    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    @Override
    public String ToString() {
        return null;
    }

    @Override
    public void execute(ArrayList<DataType> list) {
        int length = list.get(0).ToString().length();
        list.get(1).FromString(String.valueOf(length));
    }
}
