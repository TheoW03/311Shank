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
public class subString extends BuiltInFunctionNode{
    public subString() {

    }

    @Override
    public void execute(ArrayList<DataType> list) {
        String a = list.get(2).ToString();
        int beginning = Integer.parseInt(list.get(0).ToString());
        int end = Integer.parseInt(list.get(1).ToString());
        list.get(2).FromString(a.substring(beginning,end));

    }
    @Override
    public String ToString() {
        return null;
    }


}
