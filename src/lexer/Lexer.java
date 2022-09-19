package lexer;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 */
public class Lexer {
    private Token tokenList;
    //    private String data;
    public final String NUM_REGEX = "[1-9]*";
    private ArrayList<String> data; //data
    public HashMap<String,Token> a = new HashMap<>();

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
        ArrayList<Token> tokenDataR = new ArrayList<>();
        int lineNum = 1;
        String buffer;
        boolean stateIsWord = false;
        boolean stateIsNum = false;
        int state = 1;
        int wordState = 1;
        char currentChar;
        String wordBuffer = "";
        for (int i1 = 0; i1 < data.size(); i1++) { //loop
            String dataTokensLine = data.get(i1); //token
            buffer = "";
            for (int i = 0; i < dataTokensLine.length(); ++i) {
                currentChar = dataTokensLine.charAt(i); //each token
                if(wordState != 2){
                    stateIsWord = Pattern.matches("[a-zA-Z\"]*", String.valueOf(currentChar)); //regex moment :D
                    stateIsNum = Pattern.matches("[1-9+*)/(.-]*", String.valueOf(currentChar)); //regex moment
                }

                //ooperator.
                if (currentChar != ' ') {

                    if (stateIsNum) {
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
                                    if (i == 0) { //Idk if this is cheating on a state machine but
                                        buffer += currentChar;
                                    } else {
                                        tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
                                    }
                                    state = 2;
                                }
                                case '*' -> {
                                    state = 2;
                                    tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
                                }
                                case '/' -> {
                                    state = 2;
                                    tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                                }
                                case ' ' -> tokenDataR.add(new Token(Token.OPTokens.ENDOFLINE, ";"));
                                case '.' -> {
                                    state = 5;
                                    buffer += currentChar;
                                }
                                default -> state = -1;
                            }

                            //suntraction
                        } else if (state == 2) { //negative
                            switch (currentChar) {
                                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-' -> {
                                    state = 3;
                                    buffer += currentChar;
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
                                case '+', '-', '*', '/', ';',')','(' -> {
                                    state = 2;
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

                                }
                                case '.' -> {
                                    if (buffer.contains(".")) {
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
                    }else if(stateIsWord){
                        wordBuffer += currentChar;
                    }else{
                        throw new UnauthTokenException("error: Fatty finger moment.");
                    }
                }else{ //space clears buffer
                    if (!wordBuffer.equals("")){
                        tokenDataR.add(new Token(Token.OPTokens.WORD,wordBuffer));
                        wordBuffer = "";
                    }
                }
            } //eof
            if (!wordBuffer.equals("")){
                //check if Keyword in hasmap
                //if it is Return New Keyword token
                //else return Identifire token.
                tokenDataR.add(new Token(Token.OPTokens.WORD,wordBuffer));
                wordBuffer = "";
            }
            if (!buffer.equals("")) {

                tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                buffer = "";
            }
            tokenDataR.add(new Token(Token.OPTokens.ENDOFLINE, ";")); //end
            lineNum++;

        }
        return tokenDataR; //salida
    }
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
