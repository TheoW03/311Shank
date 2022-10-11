package parser.DataType;

import parser.node.FloatNode;
import parser.node.IntegerNode;

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

    public FloatDataType(FloatNode data) {

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
