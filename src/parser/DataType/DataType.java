package parser.DataType;

import parser.node.FloatNode;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public abstract class DataType {
    public abstract String ToString();
    public abstract void FromString(String input); // sets the value of the data type by parsing the string


}
