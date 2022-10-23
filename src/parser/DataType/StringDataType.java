package parser.DataType;

import parser.node.IntegerNode;
import parser.node.Node;
import parser.node.StringNode;

import javax.xml.crypto.Data;
import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class StringDataType extends DataType {
    private Node data;
    private final boolean consnat;
    public StringDataType(Node data, boolean consnat) {
        this.data =data;
        this.consnat=consnat;
    }

    @Override
    public void FromString(String input) {
        this.data = new StringNode(input);
    }

    @Override
    public boolean checkIfCOnst() {
        return consnat;
    }


    @Override
    public String ToString() {
        if(data == null){
            return "null";
        }
        return data.ToString();
    }
}
