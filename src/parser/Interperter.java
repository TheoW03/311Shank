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

    public void travserse(Node root) {

        if (root == null) {
            System.out.println("j");
            return;
        }
        System.out.println(root.ToString());
        travserse(root.left);
        travserse(root.right);


    }

    /**
     * "Resolve in the interpreter is:
     * If I have an integer, convert to float and return
     * If I have a float, return.
     * If I have a math op, resolve(left) and resolve (right) then do the op and return the value"
     *
     * @param thingYouWantResolved
     * @return
     */
    public float Resolve(Node thingYouWantResolved) {
        System.out.println("traverse");
        if (thingYouWantResolved == null) {
            System.out.println("es ndad :')");
            return 0;
        }
//        Resolve(thingYouWantResolved.right);
//        Resolve(thingYouWantResolved.left);
        //if math node/.
        if (thingYouWantResolved instanceof IntegerNode) {
            return (float) ((IntegerNode) thingYouWantResolved).getIntegerANomerul();
        } else if (thingYouWantResolved instanceof FloatNode) {
            return ((FloatNode) thingYouWantResolved).getValue();
        }
        if (thingYouWantResolved instanceof MathOpNode) {
            float a;
            MathOpNode v;
            v = (MathOpNode) thingYouWantResolved;
            switch (v.getOP()) {
                case "+":
                    float addingNum1 = Resolve(thingYouWantResolved.right);
                    float addingNum2 = Resolve(thingYouWantResolved.left);
                    System.out.println("Operand1: " + addingNum1 + " OP2: " + addingNum2);
                    a = addingNum1 + addingNum2;
                    System.out.println("result: " + a);
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
        System.out.println("es cero");
        return 0;

    }
}
