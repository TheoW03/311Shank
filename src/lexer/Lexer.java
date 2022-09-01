package lexer;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Lexer {
    private Token tokenList;
    private String data;
    public Lexer(String data) {
        this.tokenList = new Token();
        this.data=data;

    }

    /**
     *
     * @return array list of chars
     *
     */
    public ArrayList<String> lexer() {
        ArrayList<String> tokenData =  new ArrayList<String>();
        Token.OPTokens [] opTokens = Token.OPTokens.values();
        EnumSet<Token.NumTokens> numTokens = EnumSet.allOf(Token.NumTokens.class);
//        System.out.println(opTokens.);
        for(int i = 0;i < this.data.length();i++){
//            boolean errorState = false;
//            char token = this.data.charAt(i);
//
//            if(opTokens(String.valueOf(token))){
//                if(!this.number(token)){
//                    errorState=true;
//                }
//            }else if(numTokens.contains(String.valueOf(token))){ //num token
//                tokenData.add(Token.OPTokens.valueOf(String.valueOf(token)).toString()+" "+token);
//            }else{
//                errorState = true;
//            }
//            if(errorState){
//                throw new RuntimeException("Error token "+token+" doesnt work");
//            }
        }
        System.out.println("sucess");
        return tokenData;
    }
//    public boolean operator(char token){
//
//        return false;
//    }
    public boolean number(char token){
        return false;
    }

    /**
     *
     * @return string stuff
     *
     * yk you been coding C# for too long when toString looks wierd
     * its ToString in C# T~T
     */
    @Override
    public String toString(){
        return "Lexxer";
    }
}
