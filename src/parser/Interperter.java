package parser;
//custom imports for I have my code in different dir's
//pls comment out if problem.

import jdk.jfr.DataAmount;
import lexer.Token;
import lexer.UnauthTokenException;
import parser.DataType.*;
import parser.node.*;
import parser.node.FunctionCallNode.CallableNode;
import parser.node.FunctionCallNode.FunctionCallNode;
import parser.node.StatementNode.AssignmentNode;
import parser.node.StatementNode.VaraibleReferenceNode;
import parser.node.builtInFunctionNode.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import static lexer.Token.OPTokens.GREATER_THAN;


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
    private HashMap<String, DataType> global;

    public Interperter() {
        builtIn = new HashMap<>();
        nonBuiltIns = new HashMap<>(); //im assuming its like this?
        builtIn.put(Token.OPTokens.WRITE, new WriteNode());
        builtIn.put(Token.OPTokens.READ, new ReadNode());
        builtIn.put(Token.OPTokens.FLOAT_CON_INT, new RealToIntNode());
        builtIn.put(Token.OPTokens.SQRT, new squareRootNode());
        builtIn.put(Token.OPTokens.INT_CON_FLOAT, new IntToRealNode());
        builtIn.put(Token.OPTokens.GET_RANDOM, new getRandomNode());
        builtIn.put(Token.OPTokens.SUB_STRING, new subString());
        builtIn.put(Token.OPTokens.CHAR_AT, new charAt());
        builtIn.put(Token.OPTokens.STR_LEN, new strlength());
        global = new HashMap<>();


    }

    public String resolveString(Node root, HashMap<String, DataType> vars) {
        if (root == null) {
            return "null";
        }
        //fix later
        if (root instanceof StringNode) {
            return ((StringNode) root).ToString();
        } else if (root instanceof FloatNode) {
            return Float.toString(((FloatNode) root).getValue());
        } else if (root instanceof IntegerNode) {
            return Integer.toString(((IntegerNode) root).getIntegerANomerul());
        } else if (root instanceof VaraibleReferenceNode) {
            return vars.get(root.ToString()).ToString();
        }
        if (root instanceof MathOpNode) {
            MathOpNode v;
            v = (MathOpNode) root;
            switch (v.getOP()) {
                case "+" -> {
                    return resolveString(root.left, vars) + resolveString(root.right, vars);
                }
                default -> {
                    Random r = new Random();
                    int n = r.nextInt(2);
                    if (n == 1) {
                        throw new UnauthTokenException("jS aN eLeGaNt pEaCe oF hUmAn eNgInEeRiNg. :D"); //JS is complete garbage #cancelJS

                    } else {
                        throw new UnauthTokenException("-,/,* isn't accepted tokens for String concatenation");
                    }

                }
            }
        }
        return "";
    }

    /**
     * @param root is the tree
     * @param vars varaibles
     * @return 1 or 0 or num factor
     * <p>
     * this works  just like resolve for booleans like
     * true and false or true or
     * 1 = 2 and 2 = 1 or 3 = 4
     * <p>
     * <p>
     * java moment when u have to use the wrappers :')
     */

    public Object resolveBooleanExp(Node root, HashMap<String, DataType> vars) {
        if (root == null) {
            return -1;
        }
        //checks if boolean word
        if (root instanceof IntegerNode) {
            return (float) ((IntegerNode) root).getIntegerANomerul();
        }
        if (root instanceof FloatNode) {
            return ((FloatNode) root).getValue();
        }
        if (root instanceof BooleanWordNode) {
            if (((BooleanWordNode) root).evalu()) {
                return 1.0f;
            } else {
                return 0.0f;
            }
        }
        if (root instanceof StringNode) {
            return root.ToString();
        }
        if (root instanceof MathOpNode) {
            return Resolve(root, vars);
        }
        if (root instanceof VaraibleReferenceNode) {
            if (global.get(root.ToString()) != null) {
                try {
                    return Float.parseFloat(global.get(root.ToString()).ToString());
                } catch (NumberFormatException e) {
                    if (vars.get(root.ToString()).ToString() == "false") {
                        return 1.0f;
                    } else {
                        return 0.0f;
                    }
//                    return global.get(root.ToString()).ToString();
                }
            } else {
                try {
                    return Float.parseFloat(vars.get(root.ToString()).ToString());
                } catch (NumberFormatException e) {
                    if (vars.get(root.ToString()) instanceof BooleanDataType) {
                        if (vars.get(root.ToString()).ToString() == "true") {
                            return 1.0f;
                        } else {
                            return 0.0f;
                        }

                    }
                    return vars.get(root.ToString()).ToString();
                }
            }
        }
//        resolveBooleanExp(root.left);
//        resolveBooleanExp(root.right);
        if (root instanceof BooleanNode) {
            Object left = resolveBooleanExp(root.left, vars);
            Object right = resolveBooleanExp(root.right, vars);
            switch (((BooleanNode) root).getCondition()) {
                case EQUALITY_EUQUALS -> {
                    if (left instanceof Number && right instanceof Number) {
                        if (((Number) right).floatValue() == ((Number) left).floatValue()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else if (left instanceof String && right instanceof String) {
                        if (right.equals(left)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                case GREATER_THAN -> {
                    if (left instanceof Number && right instanceof Number) {
                        if (((Number) right).floatValue() < ((Number) left).floatValue()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }

                }
                case LESS_THAN -> {
                    if (left instanceof Number && right instanceof Number) {
                        if (((Number) right).floatValue() > ((Number) left).floatValue()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                case LESS_THAN_EQAUALS -> {
                    if (left instanceof Number && right instanceof Number) {
                        if (((Number) right).floatValue() >= ((Number) left).floatValue()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                case GREATER_THAN_EQUALS -> {
                    if (left instanceof Number && right instanceof Number) {
                        if (((Number) right).floatValue() <= ((Number) left).floatValue()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                case NOT_EQUAL -> {
                    if (left instanceof Number && right instanceof Number) {
                        if (((Number) right).floatValue() != ((Number) left).floatValue()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else if (left instanceof String) {
                        if (!right.equals(left)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
                case AND -> {
                    if (((Number) right).floatValue() == 1.0f && ((Number) left).floatValue() == 1.0f) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
                case OR -> {
                    if (((Number) right).floatValue() == 1.0f || ((Number) left).floatValue() == 1.0f) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }
        return -2;
    }

    public String StringNodeEval(Node root, HashMap<String, DataType> vars) {
        if (root == null || root instanceof IntegerNode || root instanceof FloatNode) {
            System.out.println("error");
            return "-2";
        }

        switch (((BooleanNode) root).getCondition()) {
            case EQUALITY_EUQUALS -> {
//                    System.out.println(StringNodeEval(root.right, vars));
//                    System.out.println(StringNodeEval(root.left, vars));
//                    return "w";
                if (root.ToString().equals(StringNodeEval(root.left, vars))) {
                    return "1";
                } else {
                    return "0";
                }
            }
        }

//            case GREATER_THAN -> {
//                if (resolveBooleanExp(root.right, vars) < resolveBooleanExp(root.left, vars)) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//            case LESS_THAN -> {
//                if (resolveBooleanExp(root.right, vars) > resolveBooleanExp(root.left, vars)) {
//
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//            case LESS_THAN_EQAUALS -> {
//                if (resolveBooleanExp(root.right, vars) >= resolveBooleanExp(root.left, vars)) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//            case GREATER_THAN_EQUALS -> {
//                if (resolveBooleanExp(root.right, vars) <= resolveBooleanExp(root.left, vars)) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//            case NOT_EQUAL -> {
//                if (resolveBooleanExp(root.right, vars) != resolveBooleanExp(root.left, vars)) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//            case AND -> {
//                if (resolveBooleanExp(root.right, vars) == 1 && resolveBooleanExp(root.left, vars) == 1) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//            case OR -> {
//                if (resolveBooleanExp(root.right, vars) == 1 || resolveBooleanExp(root.left, vars) == 1) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }


        if (root instanceof StringNode) {
            System.out.println(root.ToString());
            return root.ToString();
        }
        System.out.println("2error");

        return "-3";

    }

    public float traverseBooleanOp(Node root) {
        if (root instanceof IntegerNode) {
            return (float) ((IntegerNode) root).getIntegerANomerul();
        }
        if (root instanceof FloatNode) {
            return (float) ((FloatNode) root).getValue();
        }
        return -1;
    }

    /**
     * @param root a
     *             just a debug method
     */

    public void travserse(Node root) {

        if (root == null) {
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
     * @return a number
     */
    public float Resolve(Node thingYouWantResolved, HashMap<String, DataType> vars) {
        if (thingYouWantResolved == null) {
            return 0;
        }
//        Resolve(thingYouWantResolved.right);
//        Resolve(thingYouWantResolved.left);
        //if math node/.
        if (thingYouWantResolved instanceof IntegerNode) {
            return (float) ((IntegerNode) thingYouWantResolved).getIntegerANomerul();
        } else if (thingYouWantResolved instanceof FloatNode) {
            return ((FloatNode) thingYouWantResolved).getValue();
        } else if (thingYouWantResolved instanceof VaraibleReferenceNode) {
            if (global.get(thingYouWantResolved.ToString()).ToString() != null) {
                return Float.parseFloat(global.get(thingYouWantResolved.ToString()).ToString());
            } else {
                return Float.parseFloat(vars.get(thingYouWantResolved.ToString()).ToString());
            }
        }
        if (thingYouWantResolved instanceof MathOpNode) {
            float a;
            MathOpNode v;
            v = (MathOpNode) thingYouWantResolved;
            switch (v.getOP()) {
                case "+":
                    float addingNum1 = (float) Resolve(thingYouWantResolved.right, vars);
                    float addingNum2 = (float) Resolve(thingYouWantResolved.left, vars);
                    a = addingNum1 + addingNum2;
                    return a;
                case "*":
                    a = (float) Resolve(thingYouWantResolved.left, vars) * Resolve(thingYouWantResolved.right, vars);
                    return a;
                case "/":
                    if (Resolve(thingYouWantResolved.right, vars) == 0) {
                        throw new InvalidMathOPException("cant devided by 0");
                    }
                    a = Resolve(thingYouWantResolved.left, vars) / Resolve(thingYouWantResolved.right, vars);
                    return a;
                case "-":
                    a = Resolve(thingYouWantResolved.left, vars) - Resolve(thingYouWantResolved.right, vars);
                    return a;
                case "mod":
                    System.out.println("works");
                    a = Resolve(thingYouWantResolved.left, vars) % Resolve(thingYouWantResolved.right, vars);
                    return a;
            }
        }
        //if float
        return 0;

    }

    /**
     * @param boolExp the node
     * @param vars    bc resolve boolean returns 1 and 0 (and I was to lazy to refactor Lol)
     *                this translates into true/false
     * @return
     */
    public boolean evauluateBool(Node boolExp, HashMap<String, DataType> vars) {
        Object r = (Number) resolveBooleanExp(boolExp, vars);
        if (((Number) r).floatValue() == 1.0f) {
            return true; //e
        } else if (((Number) r).floatValue() == 0.0f) {
            return false;
        } else {
            throw new UnauthTokenException("error");
        }
    }

    /**
     * @param function function def
     * @param vars  current context.
     */

    public void compileMethods(FunctionNode function, HashMap<String, DataType> vars, String name) {
        nonBuiltIns.put(function.getIdent(), function); //Add it to hash map
        ArrayList<Node> varaibles = function.getVaraibles(); ///works
        ArrayList<Node> params = function.getParams();
        ArrayList<Node> statements = function.getStatements();
        HashMap<String, DataType> varP = vars;
        for (int i = 0; i < params.size(); i++) {
            VaraibleNode varRef = (VaraibleNode) params.get(i);
            if (vars.get(varRef.getName().getTokenValue()) == null) {
                if (varRef.getType().getTokenEnum() == Token.OPTokens.INTEGER) {
                    if (varRef.isGlobal()) {
                        global.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue(), false));
                    } else {
                        varP.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue(), false));
                    }
                } else if (varRef.getType().getTokenEnum() == Token.OPTokens.FLOAT) {
                    if (varRef.isGlobal()) {
                        global.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue(), false));

                    } else {
                        varP.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue(), false));

                    }
//                    varP.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue(), false));
                } else if (varRef.getType().getTokenEnum() == Token.OPTokens.STRING_DT) {
                    if (varRef.isGlobal()) {
                        global.put(varRef.getName().getTokenValue(), new StringDataType(varRef.getValue(), false));

                    } else {
                        varP.put(varRef.getName().getTokenValue(), new StringDataType(varRef.getValue(), false));
                    }
                } else if (varRef.getType().getTokenEnum() == Token.OPTokens.CHARACTER) {
                    if (varRef.getValue() instanceof StringNode) {
                        throw new UnauthTokenException("to many characters");
                    }
                    if (varRef.isGlobal()) {
                        global.put(varRef.getName().getTokenValue(), new CharacterDataType(varRef.getValue(), false));

                    } else {
                        varP.put(varRef.getName().getTokenValue(), new CharacterDataType(varRef.getValue(), false));

                    }
                }
            }

        }
        for (int i = 0; i < varaibles.size(); i++) {
            if (varaibles.get(i) instanceof VaraibleNode) {
                VaraibleNode varRef = (VaraibleNode) varaibles.get(i);
                if (varRef.getType() != null) {
                    Token.OPTokens tok = ((VaraibleNode) varaibles.get(i)).getType().getTokenEnum();
                    if (tok == Token.OPTokens.INTEGER) {
                        //will put here bc i dont want togo through my spagehhti code D:
                        if (varRef.isGlobal()) {
                            global.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue(), varRef.isConstant()));
                        } else {
                            varP.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue(), varRef.isConstant()));

                        }
//                        varP.put(varRef.getName().getTokenValue(), new IntDataType(varRef.getValue(), varRef.isConstant()));
                    } else if (tok == Token.OPTokens.FLOAT) {
                        if (varRef.isGlobal()) {
                            global.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue(), varRef.isConstant()));

                        } else {
                            varP.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue(), varRef.isConstant()));
                        }
//                        varP.put(varRef.getName().getTokenValue(), new FloatDataType(varRef.getValue(), varRef.isConstant()));
                    } else if (tok == Token.OPTokens.STRING_DT) {
                        if (varRef.isGlobal()) {
                            global.put(varRef.getName().getTokenValue(), new StringDataType(varRef.getValue(), varRef.isConstant()));
                        } else {
                            varP.put(varRef.getName().getTokenValue(), new StringDataType(varRef.getValue(), varRef.isConstant()));
                        }
//                        varP.put(varRef.getName().getTokenValue(), new StringDataType(varRef.getValue(), varRef.isConstant()));
                    } else if (tok == Token.OPTokens.BOOLEAN_DT) {
                        if (varRef.isGlobal()) {
                            global.put(varRef.getName().getTokenValue(), new BooleanDataType(varRef.getValue(), varRef.isConstant()));
                        } else {
                            varP.put(varRef.getName().getTokenValue(), new BooleanDataType(varRef.getValue(), varRef.isConstant()));
                        }
//                        varP.put(varRef.getName().getTokenValue(), new BooleanDataType(varRef.getValue(), varRef.isConstant()));
                    } else if (tok == Token.OPTokens.CHAR || tok == Token.OPTokens.CHARACTER) {
                        if (varRef.isGlobal()) {
                            global.put(varRef.getName().getTokenValue(), new CharacterDataType(varRef.getValue(), varRef.isConstant()));
                        } else {
                            varP.put(varRef.getName().getTokenValue(), new CharacterDataType(varRef.getValue(), varRef.isConstant()));
                        }
                    }
                }
            }
        }
        if (nonBuiltIns.get(name) != null) {
            interpterBlock(nonBuiltIns.get(name).getStatements(), varP);
        }

    }

    /**
     * @param statetements list of statements
     * @param vars         ik u said static. but Im doing this so I can have constructor vars.
     */
    public void interpterBlock(ArrayList<Node> statetements, HashMap<String, DataType> vars) {
        //fix this up a bit
        //including making vars less public by defualt
        for (int i = 0; i < statetements.size(); i++) {
            Node nodeRef = statetements.get(i);
            if (nodeRef instanceof FunctionCallNode) {
                FunctionCallNode callNodeRef = (FunctionCallNode) statetements.get(i);
//begin
                if (builtIn.get(callNodeRef.getName().getTokenEnum()) != null) { //buuilt in
                    ArrayList<Node> params = callNodeRef.getParams();
                    ArrayList<DataType> listOfParams = new ArrayList<>();
                    addToList(params, listOfParams, vars, global);
                    builtIn.get(callNodeRef.getName().getTokenEnum()).execute(listOfParams);
                    if (callNodeRef.getName().getTokenEnum() == Token.OPTokens.INT_CON_FLOAT) { //added bc why not
                        float n = Float.parseFloat(listOfParams.get(0).ToString());
                        FloatDataType newnum = new FloatDataType(new FloatNode(n), false);
                        vars.replace(params.get(0).ToString(), newnum);
                    } else if (callNodeRef.getName().getTokenEnum() == Token.OPTokens.FLOAT_CON_INT) {
                        float n = Float.parseFloat(listOfParams.get(0).ToString());
                        int b = (int) n;
                        IntDataType newnum = new IntDataType(new IntegerNode(b), false);
                        vars.replace(params.get(0).ToString(), newnum); //replaces.
                    }
                } else if (callNodeRef.getName().getTokenEnum() == Token.OPTokens.IDENTIFIER) { //num func
                    HashMap<String, DataType> scope2 = new HashMap<>();
                    if (nonBuiltIns.get(callNodeRef.getName().getTokenValue()) != null) {
                        ArrayList<Node> params = callNodeRef.getParamss();
                        ArrayList<Node> paramsForFunc = nonBuiltIns.get(callNodeRef.getName().getTokenValue()).getParams();
//                    for(int i4 = 0; i4 < paramsForFunc.)
                        for (int i3 = 0; i3 < callNodeRef.getParamss().size(); i3++) {
                            VaraibleNode b = (VaraibleNode) paramsForFunc.get(i3);
                            if (vars.get(b.getName().getTokenValue()) == null) {
                                //checks type
                                if (((VaraibleNode) paramsForFunc.get(i3)).getType().getTokenEnum() == Token.OPTokens.INTEGER) {
                                    vars.put(((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue(), new IntDataType(null, false));
                                }
                                if (((VaraibleNode) paramsForFunc.get(i3)).getType().getTokenEnum() == Token.OPTokens.FLOAT) {
                                    vars.put(((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue(), new FloatDataType(null, false));
                                }
                                if (((VaraibleNode) paramsForFunc.get(i3)).getType().getTokenEnum() == Token.OPTokens.STRING_DT) {
                                    vars.put(((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue(), new StringDataType(null, false));
                                }
                                if (((VaraibleNode) paramsForFunc.get(i3)).getType().getTokenEnum() == Token.OPTokens.BOOLEAN_DT) {
                                    vars.put(((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue(), new BooleanDataType(null, false));
                                }
                                if (((VaraibleNode) paramsForFunc.get(i3)).getType().getTokenEnum() == Token.OPTokens.CHARACTER) {
                                    vars.put(((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue(), new CharacterDataType(null, false));
                                }
                            }
                            String k = b.getName().getTokenValue();
                            DataType log = vars.get(b.getName().getTokenValue());


                            boolean j = vars.get(b.getName().getTokenValue()) instanceof IntDataType;
                            boolean z = vars.get(b.getName().getTokenValue()) instanceof StringDataType;
                            boolean h = vars.get(b.getName().getTokenValue()) instanceof FloatDataType;

                            if (vars.get(b.getName().getTokenValue()) instanceof IntDataType) {
                                VaraibleNode a = (VaraibleNode) paramsForFunc.get(i3);
                                VaraibleReferenceNode c = (VaraibleReferenceNode) params.get(i3);
                                String m = a.getName().getTokenValue(); //the random useless vars with single lets are for the debugger are for the debugge. It lets me se the value of them just ignor ethem
                                //im not trying to toture u.
//                            String deb = a.getName().getTokenValue();
                                String va = ((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue();
                                IntDataType val;
                                //if. param is a num it
                                try {
                                    val = new IntDataType(new IntegerNode(Integer.parseInt(c.getName().getTokenValue())), false);
                                } catch (NumberFormatException e) {
                                    IntegerNode in = new IntegerNode(Integer.parseInt(vars.get(c.getName().getTokenValue()).ToString()));
                                    val = new IntDataType(in, false);
                                }
                                scope2.put(va, val);
//                                vars.replace(va, val);

                            } else if (vars.get(b.getName().getTokenValue()) instanceof FloatDataType) {
                                VaraibleReferenceNode c = (VaraibleReferenceNode) params.get(i3);
                                String va = ((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue();
                                FloatDataType val;
                                try {
                                    val = new FloatDataType(new FloatNode(Float.parseFloat(c.getName().getTokenValue())), false);
                                } catch (NumberFormatException e) {
                                    FloatNode in = new FloatNode(Float.parseFloat(vars.get(c.getName().getTokenValue()).ToString()));
                                    val = new FloatDataType(in, false);
                                }
                                scope2.put(va, val);
//                                vars.replace(va, val);
                            } else if (vars.get(b.getName().getTokenValue()) instanceof StringDataType) {
                                VaraibleReferenceNode c = (VaraibleReferenceNode) params.get(i3);
                                String va = ((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue();
                                StringDataType val = new StringDataType(new StringNode(c.getName().getTokenValue()), false);
                                scope2.put(va, val);
//                                vars.replace(va, val);
                            } else if (vars.get(b.getName().getTokenValue()) instanceof CharacterDataType) {
                                VaraibleReferenceNode c = (VaraibleReferenceNode) params.get(i3);
                                String va = ((VaraibleNode) paramsForFunc.get(i3)).getName().getTokenValue();
                                CharacterDataType val = new CharacterDataType(new CharacterNode(c.getName().getTokenValue().charAt(0)), false);
                                scope2.put(va, val);
                            }

                        }
                        compileMethods(nonBuiltIns.get(callNodeRef.getName().getTokenValue()), scope2, callNodeRef.getName().getTokenValue());
                    }

                }
                //end
            } else if (nodeRef instanceof AssignmentNode) { //assignmnet
                AssignmentNode assign = (AssignmentNode) statetements.get(i);
                if (vars.get(assign.getVarName()) != null || global.get(assign.getVarName()) != null) {
//                    if(assign.getVarName())
                    if (vars.get(assign.getVarName()) instanceof IntDataType || global.get(assign.getVarName()) instanceof IntDataType) {
                        int answer = (int) Resolve(assign.getMath(), vars);
                        if (vars.get(assign.getVarName()) == null && global.get(assign.getVarName()) == null) {
                            throw new UnauthTokenException("doesnt exist");
                        }
                        String a = assign.getVarName();
                        if (global.get(a) == null) {
                            vars.get(a).FromString(Integer.toString(answer));
                        } else {
                            global.get(a).FromString(Integer.toString(answer));
                        }
//                        Objects.requireNonNullElse(global, vars).get(assign.getVarName()).FromString(Integer.toString(answer));
//                        vars.get(assign.getVarName()).FromString(Integer.toString(answer));
                    } else if (vars.get(assign.getVarName()) instanceof FloatDataType || global.get(assign.getVarName()) instanceof FloatDataType) {
                        float b = Resolve(assign.getMath(), vars);
                        if (vars.get(assign.getVarName()) == null && global.get(assign.getVarName()) == null) {
                            throw new UnauthTokenException("doesnt exist");
                        }
                        String a = assign.getVarName();
                        if (global.get(a) == null) {
                            vars.get(a).FromString(Float.toString(b));
                        } else {
                            global.get(a).FromString(Float.toString(b));
                        }
//                        Objects.requireNonNullElse(global, vars).get(assign.getVarName()).FromString(Float.toString(b));

                    } else if (vars.get(assign.getVarName()) instanceof StringDataType || global.get(assign.getVarName()) instanceof StringDataType) {
                        String b = resolveString(assign.getMath(), vars);
                        String a = assign.getVarName();
                        if (vars.get(assign.getVarName()) == null && global.get(assign.getVarName()) == null) {
                            throw new UnauthTokenException("doesnt exist");
                        }
                        if (global.get(a) == null) {
                            vars.get(a).FromString(b);
                        } else {
                            global.get(a).FromString(b);
                        }
                    } else if (vars.get(assign.getVarName()) instanceof BooleanDataType || global.get(assign.getVarName()) instanceof BooleanDataType) {
                        Object d = resolveBooleanExp(assign.getMath(), vars);
                        float b = ((Number) d).floatValue();
                        String a = assign.getVarName();
                        if (vars.get(assign.getVarName()) == null && global.get(assign.getVarName()) == null) {
                            throw new UnauthTokenException("doesnt exist");
                        }
                        if (global.get(a) == null) {
                            vars.get(a).FromString(Float.toString(b));
                        } else {
                            global.get(a).FromString(Float.toString(b));
                        }
                    } else if (vars.get(assign.getVarName()) instanceof CharacterDataType || global.get(assign.getVarName()) instanceof CharacterDataType) {
                        String a = assign.getVarName();
                        char b = ((CharacterNode) (assign.getMath())).getData();
                        if (global.get(a) == null) {
                            vars.get(a).FromString(String.valueOf(b));
                        } else {
                            global.get(a).FromString(String.valueOf(b));
                        }
                    } else {
                        throw new UnauthTokenException("no acceptable value");
                    }
                } else {
                    throw new UnauthTokenException("var doesnt exist");
                }

            } else if (nodeRef instanceof ifNode) {
                ifNode ifState = (ifNode) statetements.get(i);
                if (evauluateBool(ifState.getBoolConditionExp(), vars)) {
                    interpterBlock(ifState.getStatements(), vars);
                } else {
                    if (ifState.getElseIf() != null) {
                        ArrayList<Node> elseIF = ifState.getElseIf();
                        int in = 0;
                        while (true) {
                            if (elseIF.size() == 0) {
                                break;
                            }
                            ElseNode elseNodeRef = (ElseNode) elseIF.get(in);
                            BooleanNode conditiob = (BooleanNode) elseNodeRef.getCondition();
                            if (conditiob == null || evauluateBool(conditiob, vars) || in > elseIF.size()) {
                                interpterBlock(elseNodeRef.getStatements(), vars);
                                break;
                            }
                            in++;
                        }
                    }


                }
                //chekcs instsnace for loop
            } else if (nodeRef instanceof ForNode) {
                ForNode forLoop = (ForNode) statetements.get(i);
                int incrimatorVal = 0;
                if (forLoop.getBegining().getName().getTokenEnum() == Token.OPTokens.NUMBER) {
                    incrimatorVal = Integer.parseInt(forLoop.getBegining().getName().getTokenValue());
                } else {
                    incrimatorVal = Integer.parseInt(vars.get(forLoop.getBegining().getName().getTokenValue()).ToString());
                }
                int endInc = 0;
                if (forLoop.getBegining().getName().getTokenEnum() == Token.OPTokens.NUMBER) {
                    endInc = Integer.parseInt(forLoop.getEnd().getName().getTokenValue());
                } else {
                    endInc = Integer.parseInt(vars.get(forLoop.getEnd().getName().getTokenValue()).ToString());
                }
                DataType incrimator = new IntDataType(new IntegerNode(incrimatorVal), false);
                vars.put(forLoop.getIncrimatorVal(), incrimator);
                for (int i0 = incrimatorVal; i0 < endInc; i0++) {
                    vars.get(forLoop.getIncrimatorVal()).FromString(Integer.toString(i0));
                    interpterBlock(forLoop.getStatements(), vars);
                }
            } else if (nodeRef instanceof WhileNode) {
                WhileNode whileState = (WhileNode) statetements.get(i);
                while (evauluateBool((BooleanNode) whileState.getBool(), vars)) {
                    interpterBlock(whileState.getStatements(), vars);
                }

            } else if (nodeRef instanceof RepeatNode) {
                RepeatNode repateState = (RepeatNode) nodeRef;
                do {
                    interpterBlock(repateState.getStatement(), vars);
                } while (!evauluateBool((BooleanNode) repateState.getBoolExp(), vars));
            }
        }
    }

    /**
     * @param params list
     * @param p      Transfer list
     * @param vars   the hash map.
     */

    private static void addToList(ArrayList<Node> params, ArrayList<DataType> p, HashMap<String, DataType> vars, HashMap<String, DataType> globals) {
        for (int i = 0; i < params.size(); i++) {
            //should updtae for var ref node.
            VaraibleReferenceNode f = (VaraibleReferenceNode) params.get(i);
            if (f.getName().getTokenEnum() == Token.OPTokens.STRING) {
                p.add(new StringDataType(f, false));
            } else if (f.getName().getTokenEnum() == Token.OPTokens.NUMBER) {
                p.add(new FloatDataType(f, false));
            } else if (f.getName().getTokenEnum() == Token.OPTokens.TRUE || f.getName().getTokenEnum() == Token.OPTokens.FALSE) {
                p.add(new BooleanDataType(f, false)); //remeber to add char.
            } else if (f.getName().getTokenEnum() == Token.OPTokens.CHAR) {
                p.add(new CharacterDataType(f, false));
            } else { //checks if Varef
                if (i == (params.size() - 1)) { //this adds it to the last thing
                    if (vars.get(f.ToString()) == null && globals.get(f.ToString()) == null) {
                        vars.put(f.ToString(), new FloatDataType(new FloatNode(0), false));
                        p.add(vars.get(f.ToString()));
                    } else {
                        DataType add = (globals.get(f.ToString()) != null) ? globals.get(f.ToString()) :
                                (vars.get(f.ToString()) != null) ? vars.get(f.ToString()) : null;
                        if (add == null) {
                            throw new UnauthTokenException("var doesnt exist");
                        }
                        p.add(add);
                    }
                } else {
                    DataType add = (globals.get(f.ToString()) != null) ? globals.get(f.ToString()) :
                            (vars.get(f.ToString()) != null) ? vars.get(f.ToString()) : null;
                    if (add == null) {
                        throw new UnauthTokenException("var doesnt exist");
                    }
                    p.add(add);
                }
            }

        }


    }


}
