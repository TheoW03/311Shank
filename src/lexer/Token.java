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
    private enum OPTokens{
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
    private char token;
    private String tokenName;

    //token
    public Token(char token, String tokenName){
        this.token = token;
        this.tokenName = tokenName;
    }

    // if numbre
    public Token(String tokenName){
        this.tokenName = tokenName;
    }
    public Token(){

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
    public boolean esNumbre(char token){
        try{
            int a = Integer.parseInt(String.valueOf(token));
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public String toString(){
        return this.tokenName + " ("+String.valueOf(this.token)+")";
    }


}
