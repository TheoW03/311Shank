import lexer.Lexer;

import java.util.*;
import java.io.*;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class main {
    public static TimeElapsedRun a = new TimeElapsedRun();


    public static void main(String[] args) {
        a.getTime().start();
        LexerTestMethod();
    }
    public static void LexerTestMethod(){
        Lexer obk = new Lexer("5+-4--24;");
        ArrayList<String> tokenData = obk.lexer();
        for(int i = 0; i < tokenData.size(); i++){
            System.out.println(tokenData.get(i));
        }
        a.getTime().stop();
        System.out.println("\n");
        System.out.println("sucess");

    }
}
