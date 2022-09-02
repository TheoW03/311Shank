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
        String numbre = "";
        boolean esDeciamal = false;
        boolean esMathmatica = false;
        String OPperLine = "";
        for(int i = 0;i < this.data.length();i++){
            char token = this.data.charAt(i);
            if(token == ';'){
                if(!numbre.equals("")){
                    tokenData.add(numbre);
                    numbre = "";
                }
                OPperLine = "";
                lineNum++;
            }
            boolean errorState = false;
            if(token != ' '){
                if(tokenList.compare(token) != null) {
                    if(token == '-'){
                        if(numbre.equals("-")){
                            tokenData.add(tokenList.compare(token));
                            continue;
                        }
                        if(!numbre.equals("")){
                            tokenData.add(numbre);
                            numbre = "";
                        }
                        numbre += String.valueOf(token);

                    }else{
                        if(!numbre.equals("")){
                            tokenData.add(numbre);
                            numbre = "";
                        }
                        if(OPperLine.equals("")){
                            OPperLine = tokenList.compare(token);
                        }
                        if(OPperLine.equals(tokenList.compare(token))){
                            tokenData.add(tokenList.compare(token));
                        }else{
                           errorState = true;
                        }

                    }

                }else if(tokenList.esNumbre(token)){
                    numbre += String.valueOf(token);
                }else if(token == '.'){
                    //play with later
                    if(esDeciamal){
                        errorState = true;
                    }
                    if(data.charAt(i-1) != token && i >= 1 && tokenList.esNumbre(data.charAt(i-1))){
                        numbre += String.valueOf(token);
                        esDeciamal = true;
                    }else{
                        errorState=true;
                    }

                }else{
                    errorState = true;
                }
            }else{
                continue;
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
