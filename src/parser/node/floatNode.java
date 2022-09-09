package parser.node;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class floatNode extends Node{
    private float value;
    public floatNode(float value) {
this.value = value;
    }
    public float getValue(){
        return this.value;
    }
    @Override
    public String ToString() {
        return null;
    }
}
