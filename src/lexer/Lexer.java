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
    public HashMap<String,Token> keyWords = new HashMap<>();

    /**
     * @param data Mss
     */
    public Lexer(ArrayList<String> data) {
        this.data = data;
        //im just going to add all possible key words so I dont have to worry

        keyWords.put("integer",new Token(Token.OPTokens.KEY_WORD,"integer"));
        keyWords.put("define",new Token(Token.OPTokens.KEY_WORD,"define"));
        keyWords.put("constants",new Token(Token.OPTokens.KEY_WORD,"constants"));
        keyWords.put("end",new Token(Token.OPTokens.KEY_WORD,"end"));
        keyWords.put("begin",new Token(Token.OPTokens.KEY_WORD,"begin"));
        keyWords.put("write",new Token(Token.OPTokens.KEY_WORD,"write"));

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
                stateIsWord = Pattern.matches("[a-zA-Z=\",]*", String.valueOf(currentChar)); //regex moment :D
                stateIsNum = Pattern.matches("[1-9+*)/(.-]*", String.valueOf(currentChar)); //regex moment
                //ooperator.
                if (currentChar != ' ') {
                    if (stateIsNum) {
                        if (state == 1) { //operator
                            if(buffer.equals("-")){
                                throw new UnauthTokenException("error");
                            }
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
                                    if(buffer.equals("")){ //Very wierd tbh Idek how this works
                                        buffer += currentChar;
                                    }else{
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
                                case '(' -> {
                                    tokenDataR.add(new Token(Token.OPTokens.LParan, "("));
                                }
                                case ')' ->{
                                    tokenDataR.add(new Token(Token.OPTokens.RParan, ")"));
                                }
                                default -> state = -1;
                            }

                            //suntraction
                        } else if (state == 2) { //negative

                            switch (currentChar) {
                                case '-' -> {
                                    if(buffer.contains("-")){
                                        throw new UnauthTokenException("line "+lineNum+" at char "+i+" token '"+currentChar+"' is unauthorized");

                                    }else{
                                        state = 3;
                                        buffer += currentChar;
                                    }
                                }
                                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                                    state = 3;
                                    buffer += currentChar;
                                }
                                case '+', '*', '/', ';' -> { //prevent -+
                                    throw new UnauthTokenException("line "+lineNum+" at char "+i+" token '"+currentChar+"' is unauthorized");

                                }
                                case '('->{
                                    state =1;
                                    tokenDataR.add(new Token(Token.OPTokens.LParan,"("));
                                }

                            }

                        } else if (state == 3) { //number
                            switch (currentChar) {
                                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> { // is number. this loops
                                    buffer += String.valueOf(currentChar);
                                }
                                case '+', '-', '*', '/', ';','(',')' -> {
                                    state=2;
                                    if(buffer.equals("-")){
                                        throw new UnauthTokenException("error");
                                    }

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
                                    } else if(currentChar == '/') {
                                        tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                                    }else if(currentChar == '('){
                                        tokenDataR.add(new Token(Token.OPTokens.LParan, "("));
                                    }else{
                                        state=1;
                                        tokenDataR.add(new Token(Token.OPTokens.RParan, ")"));
                                    }

                                }
                                case '.' -> {
                                    if (buffer.contains(".")) {
                                        throw new UnauthTokenException("line "+lineNum+" at char "+i+" token '"+currentChar+"' is unauthorized");

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
                        Random r = new Random();
                        int a = r.nextInt(100);
                        if(a == 80){ //funny easter egg.
                            throw new UnauthTokenException("error: Fatty finger moment."); //easter egg.
                        }else{
                            throw new UnauthTokenException("error");
                        }

                    }
                }else{ //space clears buffer
                    if (!wordBuffer.equals("")){
                        if(keyWords.get(wordBuffer) != null){
                            tokenDataR.add(keyWords.get(wordBuffer));
                        }else{
                            tokenDataR.add(new Token( Token.OPTokens.IDENTIFIER,wordBuffer));
                        }
                        wordBuffer = "";
                    }
                }
            } //eof
            if (!wordBuffer.equals("")){
                //check if Keyword in hasmap
                //if it is Return New Keyword token
                //else return Identifire token.
                if(keyWords.get(wordBuffer) != null){
                    tokenDataR.add(keyWords.get(wordBuffer));
                }else{
                    tokenDataR.add(new Token( Token.OPTokens.IDENTIFIER,wordBuffer));
                }
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
     *
     * @param word a
     * @return
     * im adding this to prevent spaghetti Code.
     */
    public Token whatWordIsIt(String word){
        return null;
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
