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
        int lineNum = 1;
//        System.out.println(opTokens.);
        String arToString = Arrays.toString(opTokens);
        for(int i = 0;i < this.data.length();i++){
            char token = this.data.charAt(i);
            if(token == ';'){
                lineNum++;
            }
            boolean errorState = false;
            if(token != ' '){
                if(tokenList.compare(token) != null){
                    tokenData.add(tokenList.compare(token));
                }else if(tokenList.esNumbre(token) < 11){
                    tokenData.add("number"+token);
                }else if(token == '.'){
                    if(data.charAt(i-1) != token && i > 1 && tokenList.esNumbre(data.charAt(i-1)) < 11){
                        tokenData.add("DECIMAL");
                    }else{
                        errorState=true;
                    }

                }else{
                    errorState = true;
                }
            }else{
                tokenData.add("SPACE");
            }

            if(errorState){
                throw new UnauthTokenException("line "+lineNum+" at char "+i+" token '"+token+"' is unauthorized");
            }
        }


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
