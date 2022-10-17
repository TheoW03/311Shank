package parser;
//custom imports for I have my code in different dir's
//pls comment out if problem.

import lexer.Token;
import lexer.UnauthTokenException;
import parser.DataType.DataType;
import parser.DataType.FloatDataType;
import parser.DataType.IntDataType;
import parser.node.*;
import parser.node.FunctionCallNode.FunctionCallNode;
import parser.node.StatementNode.VaraibleReferenceNode;
import parser.node.builtInFunctionNode.*;

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

    public Interperter() {

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
                        varP.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue()));
                    } else {
                        varP.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue()));
                    }
                }
            }
        }
        interpterBlock(statements, varP);
    }

    /**
     * @param statetements
     * @param vars
     */
    public static void interpterBlock(ArrayList<Node> statetements, HashMap<String, DataType> vars) {
        for (int i = 0; i < statetements.size(); i++) {
            FunctionCallNode callNodeRef = (FunctionCallNode) statetements.get(i);
            if (callNodeRef.getName().getTokenEnum() == Token.OPTokens.GET_RANDOM) {
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams, vars);
                getRandomNode ran = new getRandomNode(null);
                ran.execute(listOfParams); //Idk if i can pass by ref but im testing it.
                //what this will do is store the params and everything inside a get random node. and add to the hasmao
            }
            if(callNodeRef.getName().getTokenEnum() == Token.OPTokens.WRITE){
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams, vars);
                WriteNode write = new WriteNode();
                ArrayList<DataType> a = listOfParams;
                write.execute(listOfParams); //Idk if i can pass by ref but im testing it.
                //what this will do is store the params and everything inside a get random node. and add to the hasmao
            }
            if(callNodeRef.getName().getTokenEnum() == Token.OPTokens.READ){
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams, vars);
                ReadNode read = new ReadNode();
                ArrayList<DataType> a = listOfParams;
                read.execute(listOfParams); //Idk if i can pass by ref but im testing it.
            }
            if(callNodeRef.getName().getTokenEnum() == Token.OPTokens.SQRT){
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams, vars);
                squareRootNode sqrt = new squareRootNode();
                ArrayList<DataType> a = listOfParams;
                sqrt.execute(listOfParams); //Idk if i can pass by ref but im testing it.
            }
            if(callNodeRef.getName().getTokenEnum() == Token.OPTokens.INT_CON_FLOAT){
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams, vars);
                IntToRealNode convert = new IntToRealNode();
                ArrayList<DataType> a = listOfParams;
                convert.execute(listOfParams); //Idk if i can pass by ref but im testing it.
                float n = Float.parseFloat(listOfParams.get(0).ToString());
                FloatDataType newnum = new FloatDataType(new FloatNode(n));
                vars.replace(params.get(0).ToString(),newnum);
            }
            if(callNodeRef.getName().getTokenEnum() == Token.OPTokens.FLOAT_CON_INT){
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams, vars);
                RealToIntNode convert = new RealToIntNode();
                ArrayList<DataType> a = listOfParams;
                convert.execute(listOfParams); //Idk if i can pass by ref but im testing it.
                float n = Float.parseFloat(listOfParams.get(0).ToString());
                int b = (int)n;
                IntDataType newnum = new IntDataType(new IntegerNode(b));
                vars.replace(params.get(0).ToString(),newnum);
//                vars.replace(listOfParams.get(0).ToString(), new IntDataType(listOfParams.get(0).));
            }
        }

    }

    public static void addToList(ArrayList<Node> params, ArrayList<DataType> p, HashMap<String, DataType> vars) {
        for (int i = 0; i < params.size(); i++) {
            //should updtae for var ref node.
            VaraibleReferenceNode f = (VaraibleReferenceNode) params.get(i);
            if (f.getName().getTokenEnum() == Token.OPTokens.NUMBER) {
                p.add(new FloatDataType(f));
            } else {
                if (vars.get(f.ToString()) == null){
                    throw new UnauthTokenException(f.ToString() +" doesnt exist as var");
                }
                p.add(vars.get(f.ToString()));
            }


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
