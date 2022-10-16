package parser.DataType;

import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.Node;
import parser.node.StatementNode.VaraibleReferenceNode;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class IntDataType extends DataType{
    private Node data;
    public IntDataType(Node data) {
        this.data = data;

    }
    @Override
    public void FromString(String input) {
        this.data = new FloatNode(Integer.parseInt(data.ToString()));
    }

    @Override
    public String ToString() {
        return data.ToString();
    }
//    private IntegerNode data;
//
//    public IntDataType(IntegerNode data) {
//        this.data = data;
//    }
//    @Override
//    public void FromString(String input) {
//        this.data = new IntegerNode(data.getIntegerANomerul());
//
//    }
//    @Override
//    public String ToString() {
//        return Integer.toString(data.getIntegerANomerul());
//    }


}
