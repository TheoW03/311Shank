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
                System.out.println("top");
                currentChar = dataTokensLine.charAt(i); //each token
                System.out.println("current: "+currentChar);
                //ooperator.
                if (currentChar != ' ') {
                    if (state == 1) {
                        if (!buffer.equals("")) {
                            tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                            buffer = "";
                        }
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

                                state = 2;
                                tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
                            }
                            case '*' -> {
                                tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
                            }
                            case '/' -> {
                                tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                            }
                            case ' ' -> tokenDataR.add(new Token(Token.OPTokens.ENDOFLINE, "/"));
                            default -> state = -1;
                        }
                        //Idk
                    } else if (state == 2) {

                    } else if (state == 3) { //Es nomeral.
                        System.out.println("buffer (num): " + buffer + " index: " + i);
                        switch (currentChar) {
                            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> { // is number. this loops
                                buffer += String.valueOf(currentChar);
                                System.out.println("cond1 state: " + state);
                                System.out.println("buffer en state 3: " + buffer);
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
                                System.out.println("cond2 state: " + state);
                                System.out.println("char?: " + currentChar);
                            }
                            case '.' -> {
                                if (state == 5 || buffer.contains(".")) {
                                    throw new UnauthTokenException("Error There 2 decimals");
                                }
                                buffer += currentChar;
                                System.out.println("cond3 state: " + state);
                            }
                        }

                    } else if (state == 4) {

                    } else if (state == 5) { //es floatar.
                        buffer += currentChar;
                        state = 3;
                        System.out.println("buffer (Dec): " + buffer + " index: " + i);
                        System.out.println("state: " + state);

                    } else if (state == 6) {
                    } else {
                        throw new UnauthTokenException("error");
                    }
                } else {
                    System.out.println("space");
                }
                System.out.println("next char");
            } //eof
            if (!buffer.equals("")) {
                System.out.println("end of loop");
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
