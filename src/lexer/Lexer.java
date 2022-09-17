package lexer;

import java.util.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 */
public class Lexer {
    private Token tokenList;
    //    private String data;
    private ArrayList<String> data; //data

    /**
     * @param data Mss
     */
    public Lexer(ArrayList<String> data) {
        this.data = data;
    }

    /**
     * uses states
     * es nombre
     * es OP
     * subract, addition
     * decimal
     * Error
     * <p>
     * it compiles. trust me bro 😉
     *
     * @return array list of chars
     */
    public ArrayList<Token> lexer() {
//        ArrayList<String> tokenData =  new ArrayList<String>();
        ArrayList<Token> tokenDataR = new ArrayList<>();
        int lineNum = 1;
        String numbre = "";
        boolean esDeciamal = false;
        int checkIfOPRepeated = 0;
        int checkIfNOpISRpeated = 0;
        String buffer;
        String OPperLine = "";
        int state = 1;

        for (int i1 = 0; i1 < data.size(); i1++) { //loop
            String dataTokensLine = data.get(i1); //token
            int i = 0; //i
            buffer = "";
            while (i < dataTokensLine.length()) {
                char currentChar = dataTokensLine.charAt(i); //each token
                //ooperator.
                if (currentChar != ' ') {
                    if (state == 1) {
                        switch (currentChar) {
                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                                state = 3;
                                buffer += currentChar;
                                System.out.println("buffer: " + buffer);
                            }
                            case '+' -> {

                                System.out.println("plus: state1: ite" + i);
                                state = 2;
                                tokenDataR.add(new Token(Token.OPTokens.ADD, "+"));
                            }
                            case '-' -> {
                                if (!buffer.equals("")) {
                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                    buffer = "";
                                }
                                state = 2;
                                tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
                            }
                            case '*' -> {
                                if (!buffer.equals("")) {
                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                    buffer = "";
                                }
                                tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
                            }
                            case '/' -> {
                                if (!buffer.equals("")) {
                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                    buffer = "";
                                }
                                tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                            }
                            case ' ' -> tokenDataR.add(new Token(Token.OPTokens.ENDOFLINE, "/"));
                        }
                        //Idk
                    } else if (state == 2) {

                    } else if (state == 3) { //Es nomeral.
                        switch (currentChar) {
                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                                buffer += String.valueOf(currentChar);
                                System.out.println("cond1 state: " + state);
                            }
                            case '+', '-', '*', '/', ';' -> {
                                if (currentChar == '+') {
                                    if (!buffer.equals("")) {
                                        tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                        buffer = "";
                                    }
                                    tokenDataR.add(new Token(Token.OPTokens.ADD, "+"));
                                } else if (currentChar == '-') {
                                    if (!buffer.equals("")) {
                                        tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                        buffer = "";
                                    }
                                    tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
                                } else if (currentChar == '*') {
                                    if (!buffer.equals("")) {
                                        tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                        buffer = "";
                                    }
                                    tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
                                } else {
                                    if (!buffer.equals("")) {
                                        tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                        buffer = "";
                                    }
                                    tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                                }
                                state = 1;
                                System.out.println("cond2 state: " + state);
                                System.out.println("char?: " + currentChar);
                            }
                            case '.' -> {
                                state = 5;
                                System.out.println("cond3 state: " + state);
                            }
                        }

                    } else if (state == 4) {

                    } else if (state == 5) { //es floatar.
                        System.out.println("es decimal");

                        buffer += ".";
                        System.out.println(buffer);
                        state = 3;
                    } else if (state == 6) {
                    } else {
                        throw new UnauthTokenException("error");
                    }
                }
//                switch (token){
//                    case '0':
//
//                }

//                if (number(token) != 11) {
//                    if (!buffer.equals("")) {
//                        tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
//                        buffer = "";
//                    }
//                    state = 1;
//                } else if (number(token) == -1) {
//                    state = 5;
//                } else {
//                    state = 3;
//
//                }else{
//
//                }
                System.out.println(i + " current char: " + currentChar);
//                switch (state) {
//                    case 1: //es Operator
//                        System.out.println("state: "+state);
//                        switch (currentChar) {
//                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
//                                state = 3;
//                                buffer += currentChar;
//                                System.out.println("buffer: "+buffer);
//                                break;
//                            case '+':
//                                if(!buffer.equals("")){
//                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER,buffer));
//                                    buffer = "";
//                                }
//                                System.out.println("plus: state1: ite"+i);
//                                state = 2;
//                                tokenDataR.add(new Token(Token.OPTokens.ADD, "+"));
//                                break;
//                            case '-':
//                                if(!buffer.equals("")){
//                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER,buffer));
//                                    buffer = "";
//                                }
//                                state = 2;
//                                tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
//                                break;
//                            case '*':
//                                if(!buffer.equals("")){
//                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER,buffer));
//                                    buffer = "";
//                                }
//                                tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
//                                break;
//                            case '/':
//                                if(!buffer.equals("")){
//                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER,buffer));
//                                    buffer = "";
//                                }
//                                tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
//                                break;
//                            case' ':
//                                tokenDataR.add(new Token(Token.OPTokens.ENDOFLINE, "/"));
//                                break;
//                        }
//                        break;
//                    case 2: //es negative or addition.
//                        System.out.println("state: "+state);
//                        switch (currentChar){
//                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
//                                state = 3;
//                                System.out.println("cond1 state: "+state);
////                                buffer +="-" + currentChar;
//                            case '+':
//                                tokenDataR.add(new Token(Token.OPTokens.ADD,"+"));
//                                System.out.println("cond2 state: "+state);
//                                break;
//                            case '-':
//                                tokenDataR.add(new Token(Token.OPTokens.SUBTRACT,"-"));
//                                System.out.println("cond3 state: "+state);
//                                break;
//                        }
//                        break;
//                    case 3: //nomeral.
//                        System.out.println("state: "+state);
//                        System.out.println("buffer: "+buffer);
//                        switch (currentChar) {
//                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
//                                buffer += String.valueOf(currentChar);
//                                System.out.println("cond1 state: "+state);
//                                break;
//                            case '+', '-','*','/',';':
//                                state = 1;
//                                System.out.println("cond2 state: "+state);
//                                System.out.println("char?: "+currentChar);
//                                break;
//                            case '.':
//                                state = 5;
//                                System.out.println("cond3 state: "+state);
//                                break;
//                        }
//                    case 5:
//                        buffer += String.valueOf(currentChar);
//                        state = 2;
//                        break;
//                    //decimal
//                    default:
//                        System.out.println("state: error"+state);
//                        throw new UnauthTokenException("line " + lineNum + " at char " + i +
//                                " token '" + currentChar +
//                                " is unauthorized");
//
//                }
//
//                if(token != ' '){ //ignore space
//                    //es Mathmatica state
//                    if(tokenList.compare(token) != null) {
//                        //subract
//                        if(token == '-'){
//                            if(checkIfNOpISRpeated >= 2){ //if 2---3
//                                errorState=true;
//                            }
//                            //somehow works. I made this really cheeky :')
//                            if(!numbre.equals("")){ //break numbre
//                                tokenDataR.add(new Token(Token.OPTokens.NUMBER, numbre));
//                                numbre = "";
//                            }
//                            if(checkIfNOpISRpeated == 0){ //if Its 2-3
//                                checkIfNOpISRpeated++;
//                                tokenDataR.add(new Token(token,tokenList.compare(token)));
//                                i++;
//                                continue;
//                            }
//
//                            numbre += String.valueOf(token); //adds
//                            checkIfNOpISRpeated++;
//
//                        }else{ //if +*/
//                            if(token == '('){
//                                tokenDataR.add(new Token(token,tokenList.compare(token)));
//                                i++;
//                                continue;
//                            }
//
//                            if(checkIfOPRepeated >= 2 && token != ')'){ //checks for 2***3 or 2++4
//                                errorState=true;
//                            }
//                            checkIfOPRepeated++;
//                            checkIfNOpISRpeated++;
//                            if(!numbre.equals("")){ //check if being used in eq
//                                tokenDataR.add(new Token(Token.OPTokens.NUMBER, numbre));
//                                numbre = "";
//                            }
//                            if(OPperLine.equals("") &&  token != ')'){  //checks for 2+*3
//                                OPperLine = tokenList.compare(token);
//                            }
//                            if(token == ')'){
//                                tokenDataR.add(new Token(token,tokenList.compare(token)));
//                                i++;
//                                continue;
//                            }
//                            if(OPperLine.equals(tokenList.compare(token) ) ){ //adds to list
//                                tokenDataR.add(new Token(token,tokenList.compare(token)));
//                            }else{
//                                errorState = true;
//                            }
//
//                        }
//
//                    }else if(tokenList.esNumbre(token)){ //123
//                        checkIfNOpISRpeated = 0;
//                        checkIfOPRepeated = 0;
//                        OPperLine = "";
//                        numbre += String.valueOf(token);
//                    }else if(token == '.'){ //decimal
//                        checkIfNOpISRpeated = 0;
//                        checkIfOPRepeated = 0;
//                        //play with later
//                        if(esDeciamal){ ///1.2.34.
//                            errorState = true;
//                        }
//                        if(dataTokensLine.charAt(i-1) != token && i >= 1 && tokenList.esNumbre(dataTokensLine.charAt(i-1))){
//                            numbre += String.valueOf(token);
//                            esDeciamal = true;
//                        }else{
//                            errorState=true;
//                        }
//
//                    }else{
//                        errorState = true;
//                    }
//
//                }else{
//                    i++;
//                    continue;
//                }
//                if(errorState){ //error throws exception
//                    throw new UnauthTokenException("line "+lineNum+" at char "+i+" token '"+token+"' is unauthorized");
//                }
                i++;
            }
            if (!buffer.equals("")) {
                System.out.println(buffer);
                tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                buffer = "";
            }
            tokenDataR.add(new Token(Token.OPTokens.ENDOFLINE, ";")); //end
            lineNum++;

        }
        return tokenDataR; //salida
    }

//    public int number(char token) {
//        try {
//
//            return Integer.parseInt(String.valueOf(token));
//        } catch (Exception e) {
//            if (token == '.') {
//                return -1;
//            }
//            return 11;
//        }
//    }

    /**
     * @return string stuff
     * <p>
     * yk you been coding C# for too long when toString looks wierd
     * its ToString in C# T~T
     */
    @Override
    public String toString() {
        return "Lexxer";
    }
}
