package ForTestingPurposes;



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
public class ASTTest {
//    private ArrayList<String> tokens;
//    public ASTTest(ArrayList<String> tokens) {
//        this.tokens = tokens;
//    }
//    public ArrayList<Node> parserMethodTest(){
//        ArrayList<Node> nodeLis = new ArrayList<>();
//        for(int i = 0; i < tokens.size(); i++){
//            String tokenInList = matchAndRemove(tokens.get(i));
//            if(tokenInList != null){
//
//                try{
//                    float a = Float.parseFloat(tokens.get(i));
//                    FloatNode b = new FloatNode(a);
//                    nodeLis.add(b);
//
//                }catch (NumberFormatException e1){
//                    try{
//                        int a = Integer.parseInt(tokens.get(i));
//                        IntegerNode b = new IntegerNode(a);
//                        nodeLis.add(b);
//                    }catch (NumberFormatException e2){
////                        MathOpNode a = new MathOpNode(tokenInList);
//                        int in = i;
//                        Node getNode = null;
//                        while(in > 0){
//                            getNode = nodeLis.get(in);
//                            if(getNode instanceof IntegerNode || getNode instanceof FloatNode){
//                                break;
//                            }
//                            in--;
//                        }
//                        if(!(in == 0)){
//                            MathOpNode a = new MathOpNode(nodeLis.get(i), getNode, '+');
//                        }
//                        /**
//                         * pseudo code ->
//                         *
//                         * checks if Op
//                         * if its OP it will go back until instance of int Node or FLoat Node
//                         * if its EOL. it will check for an OP.
//                         *
//                         *
//                         */
//
//                    }
//                }
//            }else{
//                System.out.println("wtf");
//
//            }
//        }
//        return new ArrayList<Node>();
//    }
//    public String matchAndRemove(String token){
//        if(token.equals(tokens.get(0)))   {
//            return tokens.remove(0);
//        }
//        return null;
//    }


}
