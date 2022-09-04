package lexer;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 */
public class Lexer {
    private Token tokenList;
    //    private String data;
    private ArrayList<String> data;

    public Lexer(ArrayList<String> data) {
        this.tokenList = new Token();
        this.data = data;
    }

    /**
     * uses states
     * es nombre
     * es OP
     *  subract, addition
     * decimal
     * Error
     *
     * it compiles. trust me bro 😉
     * @return array list of chars
     *
     *
     */
    public ArrayList<Token> lexer() {
//        ArrayList<String> tokenData =  new ArrayList<String>();
        ArrayList<Token> tokenDataR = new ArrayList<>();
        int lineNum = 1;
        String numbre = "";
        boolean esDeciamal = false;
        int checkIfOPRepeated = 0;
        int checkIfNOpISRpeated = 0;
        boolean esMathmatica = false;
        String OPperLine = "";
        for(int i1 = 0; i1 < data.size(); i1++){ //loop
            String dataTokensLine = data.get(i1); //token
            int i = 0; //i
            while(i < dataTokensLine.length()){
                boolean errorState = false;
                char token = dataTokensLine.charAt(i);
                if(token != ' '){ //ignore space
                    //es Mathmatica state
                    if(tokenList.compare(token) != null) {
                        //subract
                        if(token == '-'){
                            if(checkIfNOpISRpeated >= 2){ //if 2---3
                                errorState=true;
                            }
                            if(!numbre.equals("")){ //break numbre
                                tokenDataR.add(new Token('n',numbre));
                                numbre = "";
                            }
                            if(numbre.equals("-") || checkIfNOpISRpeated == 0){ //if Its 2-3
                                checkIfNOpISRpeated++;
                                tokenDataR.add(new Token(token,tokenList.compare(token)));
                                i++;
                                continue;
                            }

                            numbre += String.valueOf(token); //adds
                            checkIfNOpISRpeated++;

                        }else{ //if +*/
                            checkIfOPRepeated++;
                            if(checkIfOPRepeated >= 2){ //checks for 2***3 or 2++4
                                errorState=true;
                            }
                            checkIfNOpISRpeated++;
                            if(!numbre.equals("")){ //check if being used in eq
                                tokenDataR.add(new Token('n',numbre));
                                numbre = "";
                            }
                            if(OPperLine.equals("")){  //checks for 2+*3
                                OPperLine = tokenList.compare(token);
                            }
                            if(OPperLine.equals(tokenList.compare(token)) ){ //adds to list
                                tokenDataR.add(new Token(token,tokenList.compare(token)));
                            }else{
                                errorState = true;
                            }

                        }

                    }else if(tokenList.esNumbre(token)){ //123
                        checkIfNOpISRpeated = 0;
                        checkIfOPRepeated = 0;
                        numbre += String.valueOf(token);
                    }else if(token == '.'){ //decimal
                        checkIfNOpISRpeated = 0;
                        checkIfOPRepeated = 0;
                        //play with later
                        if(esDeciamal){ ///1.2.34.
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
                if(errorState){ //error throws exception
                    throw new UnauthTokenException("line "+lineNum+" at char "+i+" token '"+token+"' is unauthorized");
                }
                i++;
            }
            if(!numbre.equals("")){
                tokenDataR.add(new Token('n',numbre));
                numbre = "";
            }
            esDeciamal = false;
            tokenDataR.add(new Token(';',"ENDOFLINE")); //end
            OPperLine = "";
            lineNum++;

        }
        return tokenDataR; //salida
    }
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
