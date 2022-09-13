package parser;
//custom imports for I have my code in different dir's
//pls comment out if problem.
import lexer.Token;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.MathOpNode;
import parser.node.Node;

import java.util.ArrayList;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */

public class Interperter {
    private ArrayList<Token> ListToCompare;

    /**
     * "Resolve in the interpreter is:
     * If I have an integer, convert to float and return
     * If I have a float, return.
     * If I have a math op, resolve(left) and resolve (right) then do the op and return the value"
     * @param thingYouWantResolved
     * @return
     */
    public float Resolve(Node thingYouWantResolved){

        if(thingYouWantResolved == null){
            return -1;
        }
        Resolve(thingYouWantResolved.right);
        Resolve(thingYouWantResolved.left);
        //if math node/.
        if(thingYouWantResolved instanceof MathOpNode){
            float a;
            MathOpNode v;
            v = (MathOpNode) thingYouWantResolved;
            switch (v.getOP2()) {
                case "+":
                    a = Resolve(thingYouWantResolved.right) + Resolve(thingYouWantResolved.left);
                    return a;
                case "*":
                    a = Resolve(thingYouWantResolved.left) * Resolve(thingYouWantResolved.right);
                    return a;
                case "/":
                    if (Resolve(thingYouWantResolved.right) == 0) {
                        throw new InvalidMathOPException("cant devided by 0");
                    }
                    a = Resolve(thingYouWantResolved.left) / Resolve(thingYouWantResolved.right);
                    return a;
                case "-":
                    a = Resolve(thingYouWantResolved.left) - Resolve(thingYouWantResolved.right);
                    return a;
            }
        }
        //if float
        if(thingYouWantResolved instanceof IntegerNode){
            return (float) ((IntegerNode) thingYouWantResolved).getIntegerANomerul();
        }else if(thingYouWantResolved instanceof FloatNode){
            return  ((FloatNode) thingYouWantResolved).getValue();
        }
        return 0;

    }
}
