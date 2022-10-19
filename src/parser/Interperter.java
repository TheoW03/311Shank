package parser;
//custom imports for I have my code in different dir's
//pls comment out if problem.

import lexer.Token;
import lexer.UnauthTokenException;
import parser.DataType.DataType;
import parser.DataType.FloatDataType;
import parser.DataType.IntDataType;
import parser.node.*;
import parser.node.FunctionCallNode.CallableNode;
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
    private HashMap<Token.OPTokens, BuiltInFunctionNode> builtIn;
    private HashMap<String, FunctionNode> nonBuiltIns;
    private HashMap<String, CallableNode> a;

    public Interperter() {
        builtIn = new HashMap<>();
        nonBuiltIns = new HashMap<>(); //im assuming its like this?
        builtIn.put(Token.OPTokens.WRITE, new WriteNode());
        builtIn.put(Token.OPTokens.READ, new ReadNode());
        builtIn.put(Token.OPTokens.FLOAT_CON_INT, new RealToIntNode());
        builtIn.put(Token.OPTokens.SQRT, new squareRootNode());
        builtIn.put(Token.OPTokens.INT_CON_FLOAT, new IntToRealNode());
        builtIn.put(Token.OPTokens.GET_RANDOM, new getRandomNode());


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
     * @param thingYouWantResolved m
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

    public void compileMethods(FunctionNode function, HashMap<String, DataType> vars) {
        nonBuiltIns.put(function.getIdent(), function);
        ArrayList<Node> varaibles = function.getVaraibles();
        ArrayList<Node> params = function.getParams();
        ArrayList<Node> statements = function.getStatements();
        HashMap<String, DataType> varP = vars;
        for (int i = 0; i < params.size(); i++) {
            VaraibleNode varRef = (VaraibleNode) params.get(i);
            if (vars.get(varRef.getName().getTokenValue()) == null) {
                if (varRef.getType() != null) {
                    if (varRef.getType().getTokenEnum() == Token.OPTokens.INTEGER) {
                        varP.put(varRef.getName().getTokenValue(), new IntDataType(null, false));
                    } else {
                        varP.put(varRef.getName().getTokenValue(), new FloatDataType(null, false));
                    }
                }
            }

        }
        for (int i = 0; i < varaibles.size(); i++) {
            if (varaibles.get(i) instanceof VaraibleNode) {
                VaraibleNode varRef = (VaraibleNode) varaibles.get(i);
                if (varRef.getType() != null) {
//                    if (varP.get(varRef.getName().getTokenValue()) != null) {
//                        throw new UnauthTokenException("var " + varRef.getName().getTokenValue() + " already declared");
//                    }
                    if (((VaraibleNode) varaibles.get(i)).getType().getTokenEnum() == Token.OPTokens.INTEGER) {
                        varP.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue(), varRef.isConstant()));
                    } else {
                        varP.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue(), varRef.isConstant()));
                    }
                }
            }
        }
        interpterBlock(statements, varP);
    }

    /**
     * @param statetements m
     * @param vars         ,
     */
    public void interpterBlock(ArrayList<Node> statetements, HashMap<String, DataType> vars) {
        for (int i = 0; i < statetements.size(); i++) {
            FunctionCallNode callNodeRef = (FunctionCallNode) statetements.get(i);
            if (builtIn.get(callNodeRef.getName().getTokenEnum()) != null) {
                ArrayList<Node> params = callNodeRef.getParams();
                ArrayList<DataType> listOfParams = new ArrayList<>();
                addToList(params, listOfParams, vars);
                builtIn.get(callNodeRef.getName().getTokenEnum()).execute(listOfParams);
                if (callNodeRef.getName().getTokenEnum() == Token.OPTokens.INT_CON_FLOAT) {
                    float n = Float.parseFloat(listOfParams.get(0).ToString());
                    FloatDataType newnum = new FloatDataType(new FloatNode(n), false);
                    vars.replace(params.get(0).ToString(), newnum);
                } else if (callNodeRef.getName().getTokenEnum() == Token.OPTokens.FLOAT_CON_INT) {
                    float n = Float.parseFloat(listOfParams.get(0).ToString());
                    int b = (int) n;
                    IntDataType newnum = new IntDataType(new IntegerNode(b), false);
                    vars.replace(params.get(0).ToString(), newnum);
                }
            } else if (callNodeRef.getName().getTokenEnum() == Token.OPTokens.IDENTIFIER) {
                if (nonBuiltIns.get(callNodeRef.getName().getTokenValue()) != null) {
//                    ArrayList<DataType> p = new ArrayList<>();
//                    addToList(callNodeRef.getParams(), p, vars);
//                    compileUserDefined(callNodeRef,nonBuiltIns.get(callNodeRef.getName().getTokenValue()),vars );
                    ArrayList<Node> params = callNodeRef.getParamss();
                    ArrayList<Node> paramsForFunc = nonBuiltIns.get(callNodeRef.getName().getTokenValue()).getParams();
                    for (int i3 = 0; i3 < callNodeRef.getParamss().size(); i3++) {
                        if (params.get(i) instanceof IntegerNode) {
                            VaraibleNode a = (VaraibleNode) paramsForFunc.get(i);
                            VaraibleReferenceNode c = (VaraibleReferenceNode) params.get(i);
                            String m = a.getName().getTokenValue();
//                            VaraibleNode a = ( VaraibleNode) paramsForFunc.get(i);
                            String deb = a.getName().getTokenValue();
                            String va = ((VaraibleNode) paramsForFunc.get(i)).getName().getTokenValue();
                            IntDataType val;
                            try{
                                val = new IntDataType(new IntegerNode(Integer.parseInt(c.getName().getTokenValue())), false);
                            }catch (NumberFormatException e){
                                IntegerNode in = new IntegerNode(Integer.parseInt(vars.get(c.getName().getTokenValue()).ToString()));
                                val = new IntDataType(in, false);
                            }

                            vars.replace(va, val);
                        } else {
                            VaraibleNode a = (VaraibleNode) paramsForFunc.get(i3);
                            VaraibleReferenceNode c = (VaraibleReferenceNode) params.get(i3);
                            String m = a.getName().getTokenValue();
//                            VaraibleNode a = ( VaraibleNode) paramsForFunc.get(i);
                            String deb = a.getName().getTokenValue();
                            String va = ((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue();
                            FloatDataType val;
                            try{
                                val = new FloatDataType(new IntegerNode(Integer.parseInt(c.getName().getTokenValue())), false);
                            }catch (NumberFormatException e){
                                FloatNode in = new FloatNode(Float.parseFloat(vars.get(c.getName().getTokenValue()).ToString()));
                                val = new FloatDataType(in, false);
                            }

                            vars.replace(va, val);
                        }
                    }
                    compileMethods(nonBuiltIns.get(callNodeRef.getName().getTokenValue()), vars);
                }

            }
        }
    }

    public static void addToList(ArrayList<Node> params, ArrayList<DataType> p, HashMap<String, DataType> vars) {
        for (int i = 0; i < params.size(); i++) {
            //should updtae for var ref node.
            VaraibleReferenceNode f = (VaraibleReferenceNode) params.get(i);
            if (f.getName().getTokenEnum() == Token.OPTokens.NUMBER) {
                p.add(new FloatDataType(f, false));
            } else {
                if (i == (params.size() - 1)) {
                    if (vars.get(f.ToString()) == null) {
                        vars.put(f.ToString(), new FloatDataType(new FloatNode(0), false));
                        p.add(vars.get(f.ToString()));
                    } else {
                        p.add(vars.get(f.ToString()));
                    }
                } else {
                    if (vars.get(f.ToString()) == null) {
                        throw new UnauthTokenException(f.ToString() + " doesnt exist as var");
                    }
                    p.add(vars.get(f.ToString()));
                }
//                   throw new UnauthTokenException(f.ToString() +" doesnt exist as var");
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
