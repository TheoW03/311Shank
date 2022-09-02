package lexer;

import javax.swing.text.NumberFormatter;
import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Token {
    //state 1
    enum OPTokens{
        ADD("+","PLUS"),
        SUBTRACT("-","SUBTRACT"),
        DIVIDE("/","DIVIDE"),
        MULTIPLY("*" ,"MUTIPLY"),
        ENDOFLINE(";","EOL"),//Can I not do endOfLine for my break line bc I love java syntax

        ;
        private String s;
        private String value;
        OPTokens(String value,String s) {
            this.s = s;
            this.value=value;
        }


        @Override
        public String toString() {
            return  this.s;
        }
    }
    //state 2
    enum NumTokens{
        ZERO("0"),
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),//Can I not do endOfLine for my break line bc I love java syntax
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NEIN("9"),
        ;
        private String s;
        NumTokens(String s) {
            this.s = s;
        }
        @Override
        public String toString() {
            return s;
        }
    }
    public String compare(char token){
        return switch (token) {
            case '+' -> OPTokens.ADD.toString();
            case '-' -> OPTokens.SUBTRACT.toString();
            case '*' -> OPTokens.MULTIPLY.toString();
            case '/' -> OPTokens.DIVIDE.toString();
            case ';' -> OPTokens.ENDOFLINE.toString();
            default -> null;
        };
    }
    public int esNumbre(char token){
        try{
            int a = Integer.parseInt(String.valueOf(token));
            return a;
        }catch (Exception e){
            return 11;
        }
    }


}
