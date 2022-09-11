package parser;

import lexer.Token;
import parser.node.FloatNode;
import parser.node.IntegerNode;
import parser.node.MathOpNode;
import parser.node.Node;

import java.util.*;
import java.io.*;


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
    public Interperter() {

    }
    public float Resolve(Parser thingYouWantResolved){
        System.out.println(thingYouWantResolved);

        if(thingYouWantResolved == null){
            return -1;
        }
//        System.out.println(thingYouWantResolved);
        if(thingYouWantResolved.element instanceof IntegerNode){
            System.out.println("~snap peters neck~. this " +
                    "issued has been resolved would anyone else like to join him" +
                    "(funny famuly guy)"); //it seems today all we see is violent movies and sex on TV.
            float a = (float) ((IntegerNode) thingYouWantResolved.element).getIntegerANomerul();
            return a;
        }else if(thingYouWantResolved.element  instanceof FloatNode){
            System.out.println("~snap peters neck~. this " +
                    "issued has been resolved would anyone else like to join him" +
                    "(funny famuly guy)"); //it seems today all we see is violent movies and sex on TV.
            return ((FloatNode) thingYouWantResolved.element ).getValue();
        }else{
            Resolve(thingYouWantResolved.right);
            Resolve(thingYouWantResolved.left);
            float a;
            MathOpNode v;
            v = (MathOpNode) thingYouWantResolved.element;
            switch (v.getOP()) {
                case "+":
                    a = Resolve(thingYouWantResolved.left) + Resolve(thingYouWantResolved.left);
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
                default:
                    return -2;
            }

        }





//        if(thingYouWantResolved == null){
//            Resolve(thingYouWantResolved.left);
//        }

//        System.out.println("~snap peters neck~. this " +
//                "issued has been resolved would anyone else like to join him" +
//                "(funny famuly guy)"); //it seems today all we see is violent movies and sex on T


    }
}
