package parser;

import lexer.Token;
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
    public void Resolve(Node thingYouWantResolved){
        if(thingYouWantResolved == null){
            System.out.println("~snap peters neck~. this " +
                    "issued has been resolved would anyone else like to join him" +
                    "(funny famuly guy)"); //it seems today all we see is violent movies and sex on TV.
            return;
        }

        Resolve(thingYouWantResolved.left);
        Resolve(thingYouWantResolved.right);
    }
}
