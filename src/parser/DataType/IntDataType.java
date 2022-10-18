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
    private  boolean consnat;
    public IntDataType(Node data, boolean consnat) {
        this.data = data;
        this.consnat = consnat;
    }
    @Override
    public void FromString(String input) {
        this.data = new IntegerNode(Integer.parseInt(input));
    }

    @Override
    public boolean checkIfCOnst() {
        return consnat;
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
