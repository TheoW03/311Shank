package parser.node.builtInFunctionNode;

import lexer.Token;
import parser.DataType.DataType;
import parser.node.Node;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class getRandomNode extends BuiltInFunctionNode {
    public getRandomNode() {

    }

    /**
     *
     * @param list list
     *             the 1st node I perfectedx LOl
     */
    @Override
    public void execute(ArrayList<DataType> list) {
        Random r = new Random();
        try{
            int min = Integer.parseInt(list.get(0).ToString());
            int b = r.nextInt(min)+1; //may change to be 1 more param
            if(list.size() >= 2){
                if(list.get(1) != null){
                    list.get(1).FromString(Integer.toString(b));
                }
                System.out.println(b);

            list.get(1).ToString();
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("You didnt input a num for params");
        }

    }
    @Override
    public String ToString() {
        return "Random";
    }


}
