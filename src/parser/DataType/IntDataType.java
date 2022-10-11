package parser.DataType;

import parser.node.IntegerNode;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class IntDataType extends DataType{
    private IntegerNode data;

    public IntDataType(IntegerNode data) {

    }

    @Override
    public String ToString() {
        return null;
    }

    @Override
    public void FromString(String input) {
        this.data = new IntegerNode(data.getIntegerANomerul());

    }
}
