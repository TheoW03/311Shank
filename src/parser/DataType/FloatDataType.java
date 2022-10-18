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
public class FloatDataType extends DataType {
    private Node data;
    private boolean constant;

    //float
    public FloatDataType(Node data, boolean constant) {
        this.data =  data;
        this.constant = constant;

    }

    //    public FloatDataType(VaraibleReferenceNode data) {
//        this.data = data;
//
//    }
    @Override
    public void FromString(String input) {

        this.data = new FloatNode(Float.parseFloat(input));
    }

    @Override
    public boolean checkIfCOnst() {
        return constant;
    }

    public Node getData(){
        return (FloatNode) data;
    }

    @Override
    public String ToString() {
        return data.ToString();
    }


}
