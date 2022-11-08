package parser.node.builtInFunctionNode;

import parser.DataType.DataType;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class charAt extends BuiltInFunctionNode{
    public charAt() {

    }

    @Override
    public String ToString() {
        return null;
    }

    @Override
    public void execute(ArrayList<DataType> list) {
        String og = list.get(0).ToString();
        int index = Integer.parseInt(list.get(1).ToString());
        char ans = og.charAt(index);
        list.get(2).FromString(String.valueOf(ans));


    }
}
