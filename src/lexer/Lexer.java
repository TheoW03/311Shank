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
        char currentChar;

        for (int i1 = 0; i1 < data.size(); i1++) { //loop
            String dataTokensLine = data.get(i1); //token
            buffer = "";
            for (int i = 0; i < dataTokensLine.length(); ++i) {
                currentChar = dataTokensLine.charAt(i); //each token
                System.out.println("char: " + currentChar);
                //ooperator.
                if (currentChar != ' ') {
                    if (state == 1) { //operator
                        if (!buffer.equals("")) {
                            tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                            buffer = "";
                        }

                        switch (currentChar) {
                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                                state = 3;
                                buffer += currentChar;

                            }
                            case '+' -> {
                                state = 2;
                                tokenDataR.add(new Token(Token.OPTokens.ADD, "+"));
                            }
                            case '-' -> {
                                state=2;
                                System.out.println("subtract");
                                System.out.println("buffer: "+buffer);
                                tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
                            }
                            case '*' -> {
                                state=2;
                                tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
                            }
                            case '/' -> {
                                state=2;
                                tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                            }
                            case ' ' -> tokenDataR.add(new Token(Token.OPTokens.ENDOFLINE, "/"));
                            case '.' -> {
                                state = 5;
                                buffer += currentChar;
                            }
                            default -> state = -1;
                        }

                        //suntraction
                    } else if (state == 2) {
                        System.out.println("statew current: "+currentChar);
                        switch (currentChar){
                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-' -> {
                                state = 3;
                                System.out.println("cond1 state2");
                                buffer += currentChar;
                                System.out.println("buffer: "+buffer);
                            }
                            case '+', '*', '/', ';' -> { //prevent -+
                                throw new UnauthTokenException("error");
                            }
                        }

                    } else if (state == 3) { //number
                        switch (currentChar) {
                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> { // is number. this loops
                                buffer += String.valueOf(currentChar);
                            }
                            case '+', '-', '*', '/', ';' -> {

                                if (!buffer.equals("")) {
                                    tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                                    buffer = "";
                                }
                                if (currentChar == '+') {
                                    tokenDataR.add(new Token(Token.OPTokens.ADD, "+"));
                                } else if (currentChar == '-') {
                                    tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
                                } else if (currentChar == '*') {
                                    tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
                                } else {
                                    tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                                }
                                state = 1;
                            }
                            case '.' -> {
                                if (state == 5 || buffer.contains(".")) {
                                    throw new UnauthTokenException("Error There 2 decimals");
                                }
                                buffer += currentChar;
                                state = 5;
                            }
                        }

                    } else if (state == 4) {

                    } else if (state == 5) { //es decimal .
                        buffer += currentChar;
                        switch (currentChar) {
                            case '+', '-', '*', '/' -> {
                                throw new UnauthTokenException("unauth exption moment");
                            }
                        }
                        state = 3;

                    } else if (state == 6) { //es Idk. State.
                    } else {
                        throw new UnauthTokenException("error");
                    }
                }
            } //eof
            if (!buffer.equals("")) {

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
