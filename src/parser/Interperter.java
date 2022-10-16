package parser;
//custom imports for I have my code in different dir's
//pls comment out if problem.

import lexer.Token;
import parser.DataType.DataType;
import parser.DataType.FloatDataType;
import parser.DataType.IntDataType;
import parser.node.*;
import parser.node.FunctionCallNode.FunctionCallNode;
import parser.node.StatementNode.VaraibleReferenceNode;
import parser.node.builtInFunctionNode.BuiltInFunctionNode;
import parser.node.builtInFunctionNode.getRandomNode;

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
    private HashMap<String, BuiltInFunctionNode> builtIn;
    public Interperter(){

    }

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
                        varP.put(varRef.getName().getTokenValue(), new IntDataType((IntegerNode) varRef.getValue()));
                    }else{
                        varP.put(varRef.getName().getTokenValue(), new FloatDataType((FloatNode) varRef.getValue()));
                    }
                }
            }
        }
        interpterBlock(statements,varP);
    }

    /**
     *
     *
     * @param statetements
     * @param vars
     */
    public static void interpterBlock(ArrayList<Node> statetements, HashMap<String, DataType> vars) {
        for(int i = 0; i < statetements.size();i++){
            FunctionCallNode callNodeRef = (FunctionCallNode) statetements.get(i);
            if(callNodeRef.getName().getTokenEnum() == Token.OPTokens.GET_RANDOM){
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams);
                getRandomNode ran = new getRandomNode(null);
                ran.execute(listOfParams); //Idk if i can pass by ref but im testing it.

                //what this will do is store the params and everything inside a get random node. and add to the hasmao
            }
        }

    }
    public static void addToList(ArrayList<Node> params, ArrayList<DataType> p){
        for (int i = 0; i < params.size(); i++){
            VaraibleReferenceNode f = (VaraibleReferenceNode) params.get(i);
            p.add(new FloatDataType(f));
        }
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
