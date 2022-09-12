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
//pls accept my set up. I like my code organized in Folders. if its not I will not feel happy
//and using zip bc I hate playing. FIND THE FUCKING JAVA INTELLIJ directory on my computer. becaue inetellIJ
//puts your work in obsecure folders. which is a pain to get to.

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
        //if math node/.
        if(thingYouWantResolved instanceof MathOpNode){
            float a;
            MathOpNode v;
            v = (MathOpNode) thingYouWantResolved;
            switch (v.getOP()) {
                case "+":
                    a = Resolve(thingYouWantResolved.left) + Resolve(thingYouWantResolved.right);
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
