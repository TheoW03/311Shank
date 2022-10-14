package parser.DataType;

import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.Node;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class FloatDataType extends DataType{
    private FloatNode data;

    //float
    public FloatDataType(Node data) {

    }

    @Override
    public String ToString() {
        return null;
    }

    @Override
    public void FromString(String input) {
        this.data = new FloatNode(data.getValue());
    }
}
