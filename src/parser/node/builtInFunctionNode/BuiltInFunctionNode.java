package parser.node.builtInFunctionNode;

import parser.DataType.DataType;
import parser.node.Node;

import javax.xml.crypto.Data;
import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public abstract class BuiltInFunctionNode extends Node {
    public abstract void execute(ArrayList<DataType> list);


}
