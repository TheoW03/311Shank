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
    public static void main(String[] args) {
        LexerTestMethod();
    }
    public static void LexerTestMethod(){
        Lexer obk = new Lexer("+++++---1112322a");
        ArrayList<String> a = obk.lexer();
        for(int i = 0; i < a.size(); i++){
            System.out.println(a.get(i));
        }
    }
}
