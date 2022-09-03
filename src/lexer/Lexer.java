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
//    private String data;
    private ArrayList<String> data;
    public Lexer(ArrayList<String> data) {
        this.tokenList = new Token();
        this.data = data;
//        this.data=data;
    }

    /**
     *
     * @return array list of chars
     *
     */
    public ArrayList<Token> lexer() {
        ArrayList<String> tokenData =  new ArrayList<String>();
        ArrayList<Token> tokenDataR = new ArrayList<>();
//        Token.OPTokens [] opTokens = Token.OPTokens.values();
//        EnumSet<Token.NumTokens> numTokens = EnumSet.allOf(Token.NumTokens.class);
        int lineNum = 1;
//        System.out.println(opTokens.);
//        String arToString = Arrays.toString(opTokens);
        String numbre = "";
        boolean esDeciamal = false;
        boolean esMathmatica = false;
        String OPperLine = "";
        for(int i1 = 0; i1 < data.size(); i1++){
            String dataTokensLine = data.get(i1);
            int i = 0;
            while(i < dataTokensLine.length()){
                boolean errorState = false;
                char token = dataTokensLine.charAt(i);
                if(token != ' '){
                    if(tokenList.compare(token) != null) {
                        if(token == '-'){
                            if(numbre.equals("-")){
                                tokenDataR.add(new Token(token,tokenList.compare(token)));
//                                tokenData.add(tokenList.compare(token));
                                i++;
                                continue;
                            }
                            if(!numbre.equals("")){
                                tokenDataR.add(new Token('n',numbre));
//                                tokenData.add(numbre);
                                numbre = "";
                            }
                            numbre += String.valueOf(token);

                        }else{
                            if(!numbre.equals("")){
                                tokenDataR.add(new Token('n',numbre));
//                                tokenData.add(numbre);
                                numbre = "";
                            }
                            if(OPperLine.equals("")){
                                OPperLine = tokenList.compare(token);
                            }
                            if(OPperLine.equals(tokenList.compare(token))){
                                tokenDataR.add(new Token(token,tokenList.compare(token)));
//                                tokenData.add(tokenList.compare(token));
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
                        if(dataTokensLine.charAt(i-1) != token && i >= 1 && tokenList.esNumbre(dataTokensLine.charAt(i-1))){
                            numbre += String.valueOf(token);
                            esDeciamal = true;
                        }else{
                            errorState=true;
                        }

                    }else{
                        errorState = true;
                    }
                }else{
                    i++;
                    continue;
                }
                if(errorState){
                    throw new UnauthTokenException("line "+lineNum+" at char "+i+" token '"+token+"' is unauthorized");
                }
                i++;
            }
//            tokenData.add(numbre);
            tokenDataR.add(new Token('n',numbre));
            numbre = "";
            OPperLine = "";
            lineNum++;

        }
        return tokenDataR;
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
