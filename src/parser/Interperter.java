package parser;
//custom imports for I have my code in different dir's
//pls comment out if problem.

import lexer.Token;
import parser.DataType.DataType;
import parser.DataType.FloatDataType;
import parser.DataType.IntDataType;
import parser.node.*;
import parser.node.FunctionCallNode.FunctionCallNode;
import parser.node.StatementNode.StatementNode;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;


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
        System.out.println(thingYouWantResolved);
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
            System.out.println("e");
            float a;
            MathOpNode v;
            v = (MathOpNode) thingYouWantResolved;
            System.out.println("a: " + v.getOP());
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
        System.out.println(thingYouWantResolved.ToString());
        return 0;

    }

    public void compileMethods(FunctionNode function) {
        ArrayList<Node> varaibles = function.getVaraibles();
        ArrayList<Node> params = function.getParams();
        ArrayList<Node> statements = function.getStatements();
        HashMap<String, DataType> varP = new HashMap<>();
        for (int i = 0; i < varaibles.size(); i++) {
            if (varaibles.get(i) instanceof VaraibleNode) {
                VaraibleNode varRef = (VaraibleNode) varaibles.get(i);
                if (varRef.getType() != null) {
                    if (((VaraibleNode) varaibles.get(i)).getType().getTokenEnum() == Token.OPTokens.INTEGER) {
                        varP.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue()));
                    }else{
                        varP.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue()));
                    }
                }
            }
        }
        interpterBlock(statements,varP);
    }
    public static void interpterBlock(ArrayList<Node> statetements, HashMap<String, DataType> vars) {

    }
    //    public static void CompileThis(FunctionNode function){
//
//        HashMap<String,DataType> function = new HashMap<>();
//        if(funct.checkIfDefined()){
//            for(int i = 0; i < param.size(); i++){
//                function.put(funct.toString(),param.get(i));
//            }
//
//            //do code if its a built in
//        }else {
//
//        }
//
//
//    }

}
