package ForTestingPurposes;

import parser.Parser;
import parser.node.Node;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class main {
    private static final String REGEX = "[1-9]*";

    private static final String INPUT = "1234589";

    public static void main( String args[] ) {
        System.out.println("Current REGEX is: "+REGEX);
        System.out.println("Current INPUT is: "+INPUT);
//        stateIsNum = Pattern.matches("[1-9+*/-]*",String.valueOf(currentChar));

        System.out.println("matches(): "+Pattern.matches("[1-9+*/.-]*","."));
    }
}
