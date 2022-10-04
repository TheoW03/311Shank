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
    private ArrayList<String> data; //data
    public static HashMap<String, Token> keyWords = new HashMap<>();

    /**
     * @param data Mss
     */
    public Lexer(ArrayList<String> data) {
        this.data = data;
        //im just going to add all possible key words so I dont have to worry
        keyWords.put("integer", new Token(Token.OPTokens.INTEGER, "integer"));
        keyWords.put("float", new Token(Token.OPTokens.FLOAT, "float"));
        keyWords.put("define", new Token(Token.OPTokens.DEFINE, "define"));
        keyWords.put("constants", new Token(Token.OPTokens.CONSTANTS, "constants"));
        keyWords.put("end", new Token(Token.OPTokens.END, "end"));
        keyWords.put("begin", new Token(Token.OPTokens.BEGIN, "begin"));
        keyWords.put("write", new Token(Token.OPTokens.KEY_WORD, "write"));
        keyWords.put("variables", new Token(Token.OPTokens.VARAIBLES, "variables"));
        keyWords.put(":=", new Token(Token.OPTokens.EQUALS, "="));
        keyWords.put("=", new Token(Token.OPTokens.EQUALITY_EUQUALS, "==")); //this is for the time comes.
        keyWords.put("<", new Token(Token.OPTokens.LESS_THAN, "<")); //this is for the time comes.
        keyWords.put(">", new Token(Token.OPTokens.GREATER_THAN, ">")); //this is for the time comes.
        keyWords.put("<=", new Token(Token.OPTokens.LESS_THAN_EQAUALS, "<=")); //this is for the time comes.
        keyWords.put(">=", new Token(Token.OPTokens.GREATER_THAN_EQUALS, ">=")); //this is for the time comes.
        keyWords.put("mod", new Token(Token.OPTokens.MOD, "mod")); //this is for the time comes.
        //begin if
        keyWords.put("if", new Token(Token.OPTokens.IF, "if")); //this is for the time comes.
        keyWords.put("then", new Token(Token.OPTokens.THEN, "then"));
        keyWords.put("elseif", new Token(Token.OPTokens.ELSE_IF, "elseif"));
        keyWords.put("else", new Token(Token.OPTokens.ELSE, "else"));
        //end
        //begin for
        keyWords.put("for", new Token(Token.OPTokens.FOR, "for")); //this is for the time comes.
        keyWords.put("from", new Token(Token.OPTokens.FROM, "from"));
        keyWords.put("to", new Token(Token.OPTokens.TO, "to"));
        keyWords.put("var", new Token(Token.OPTokens.VAR, "var"));
        //begin while
        keyWords.put("while", new Token(Token.OPTokens.WHILE, "while"));
        keyWords.put("repeat", new Token(Token.OPTokens.REPEAT, "repeat"));
        keyWords.put("until", new Token(Token.OPTokens.UNTIL, "until"));
        //ens
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
    /**
     * @return I might just make a state for comments
     */
    public ArrayList<Token> lexer() {
        ArrayList<Token> tokenDataR = new ArrayList<>();
        int lineNum = 1;
        String buffer;
        boolean stateIsNum = false;
        int state = 1;
        char currentChar;
        String wordBuffer = "";
        char esMutiple = '\0';
        int wordState = 1;
        boolean stateIsComment = false;
        for (int i1 = 0; i1 < data.size(); i1++) { //loop
            String dataTokensLine = data.get(i1); //token
            //this is an easter egg.
//            if(dataTokensLine.contains("'1'-1") || dataTokensLine.contains("1-'1'")){
//                throw new UnauthTokenException("shank >>>> java script");
//            }
            buffer = ""; //\\
            for (int i = 0; i < dataTokensLine.length(); ++i) {
                currentChar = dataTokensLine.charAt(i); //each token
                char a = currentChar;
                //ignore comments
                if (currentChar == '(' && dataTokensLine.charAt(i + 1) == '*') {
                    stateIsComment = true;
                }
                if (stateIsComment) {
                    System.out.println("comment: " + currentChar);
                    if (currentChar == ')' && esMutiple == '*') {
                        stateIsComment = false;
                        esMutiple = '\0'; //33333 came useful :Relieved:
                    }
                    esMutiple = currentChar;
                    continue;
                }
                //ooperator.
                if (currentChar != ' ' && currentChar != '\0' && currentChar != '\t') {

                    switch (currentChar) {
                        case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '*', '/', ')', '(', '.', '-' -> { //can I regex
                        }
                        default -> {
                            stateIsNum = false;
                        }
                    }
                    if (stateIsNum) {
                        if (!wordBuffer.equals("")) {
                            if (keyWords.get(wordBuffer) != null) { //get out of word state
                                tokenDataR.add(keyWords.get(wordBuffer));
                            } else {
                                tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                            }
                            wordBuffer = "";
                        }
                        if (state == 1) { //operator
                            //removes word buffer.
                            if (buffer.equals("-")) {
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
                                    if (buffer.equals("")) { //Very wierd tbh Idek how this works
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
                                case '(' -> {
                                    if (dataTokensLine.charAt(i1 + 1) == '*') {
                                        stateIsComment = true;
                                        System.out.println("e");
                                        continue;
                                    }
                                    tokenDataR.add(new Token(Token.OPTokens.LParan, "("));
                                }
                                case ')' -> {
                                    tokenDataR.add(new Token(Token.OPTokens.RParan, ")"));
                                }
                                default -> state = -1;
                            }

                            //suntraction
                        } else if (state == 2) { //negative

                            switch (currentChar) {
                                case '-' -> {
                                    if (buffer.contains("-")) {
                                        throw new UnauthTokenException("line " + lineNum + " at char " + i + " token '"
                                                + currentChar + "' is unauthorized");

                                    } else {
                                        state = 3;
                                        buffer += currentChar;
                                    }
                                }
                                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                                    state = 3;
                                    buffer += currentChar;
                                }
                                case '+', '*', '/', ';' -> { //prevent -+
                                    throw new UnauthTokenException("line " + lineNum + " at char " + i + " token '"
                                            + currentChar + "' is unauthorized");

                                }
                                case '(' -> {
                                    if (dataTokensLine.charAt(i1 + 1) == '*') {
                                        stateIsComment = true;
                                        System.out.println("e");
                                        continue;
                                    }
                                    state = 1;
                                    tokenDataR.add(new Token(Token.OPTokens.LParan, "("));
                                }

                            }

                        } else if (state == 3) { //number
                            switch (currentChar) {
                                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> { // is number. this loops
                                    buffer += String.valueOf(currentChar);
                                }
                                case '+', '-', '*', '/', ';', '(', ')' -> {
                                    state = 2;
                                    if (buffer.equals("-")) {
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
                                    } else if (currentChar == '/') {
                                        tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                                    } else if (currentChar == '(') {
                                        if (dataTokensLine.charAt(i1 + 1) == '*') {
                                            stateIsComment = true;
                                            continue;
                                        }
                                        tokenDataR.add(new Token(Token.OPTokens.LParan, "("));
                                    } else {
                                        state = 1;
                                        tokenDataR.add(new Token(Token.OPTokens.RParan, ")"));
                                    }

                                }
                                case '.' -> {
                                    if (buffer.contains(".")) {
                                        throw new UnauthTokenException("line " + lineNum + " at char "
                                                + i + " token '" + currentChar + "' is unauthorized");

                                    }
                                    buffer += currentChar;
                                    state = 5;
                                }
                            }

                        } else if (state == 4) {

                        } else if (state == 5) {
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
                    } else { //clear
                        if(currentChar == ' '){ //space bug in java?
                            continue;
                        }
                        if (!buffer.equals("")) {
                            tokenDataR.add(new Token(Token.OPTokens.NUMBER, buffer));
                            buffer = "";

                        } //removes word buffer.
                        int a1 = wordState;
                        char b1 = currentChar;
                        if (wordState == 1) {
                            switch (currentChar) {
                                case ',', ':' -> {
                                    if (!wordBuffer.equals("")) {
                                        if (keyWords.get(wordBuffer) != null) {
                                            tokenDataR.add(keyWords.get(wordBuffer));

                                        } else {
                                            tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                                        }
                                    }
                                    wordBuffer = "";
                                    wordBuffer += currentChar;
                                    wordState = 2;
                                    stateIsNum = true;
                                }
                                case '=', '<', '>' -> {
                                    if (!wordBuffer.equals("")) {
                                        if (keyWords.get(wordBuffer) != null) {
                                            tokenDataR.add(keyWords.get(wordBuffer));

                                        } else {
                                            tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                                        }
                                        wordBuffer = "";
                                    }
                                    stateIsNum = true;
                                    wordBuffer += currentChar;
                                    wordState = 3;
                                }
                                case '(', ')' -> {
                                    if (!wordBuffer.equals("")) {
                                        if (keyWords.get(wordBuffer) != null) {
                                            tokenDataR.add(keyWords.get(wordBuffer));

                                        } else {
                                            tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                                        }
                                        wordBuffer = "";
                                    }
                                    if (currentChar == '(') {
                                        if (dataTokensLine.charAt(i1 + 1) == '*') {
                                            stateIsComment = true;
                                            System.out.println("e");
                                            continue;
                                        }
                                        tokenDataR.add(new Token(Token.OPTokens.LParan, "("));
                                    } else {
                                        tokenDataR.add(new Token(Token.OPTokens.RParan, ")"));
                                    }
                                    stateIsNum = true;
                                }
                                case '+', '-', '*', '/' -> {
                                    if (!wordBuffer.equals("")) {
                                        if (keyWords.get(wordBuffer) != null) {
                                            tokenDataR.add(keyWords.get(wordBuffer));

                                        } else {
                                            tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                                        }
                                        wordBuffer = "";
                                    }
                                    switch (currentChar) {
                                        case '+' -> {
                                            tokenDataR.add(new Token(Token.OPTokens.ADD, "+"));
                                        }
                                        case '-' -> {
                                            tokenDataR.add(new Token(Token.OPTokens.SUBTRACT, "-"));
                                        }
                                        case '*' -> {
                                            tokenDataR.add(new Token(Token.OPTokens.DIVIDE, "/"));
                                        }
                                        case '/' -> {
                                            tokenDataR.add(new Token(Token.OPTokens.MULTIPLY, "*"));
                                        }
                                    }
                                    stateIsNum = true;
                                }
                                default -> {
                                    wordBuffer += currentChar;
                                }
                            }
                        } else if (wordState == 2) {

                            if (wordBuffer.equals(",") || wordBuffer.equals(":")) { //destoryes buffer
                                if (currentChar == '=') {
                                    wordBuffer += currentChar; //add to this
                                    wordState = 3;
                                    stateIsNum = true;
                                } else {
                                    wordBuffer = "";
                                    wordBuffer += currentChar; //add to this
                                    wordState = 1;
                                }
                            } else {
                                wordBuffer += currentChar; //add to this
                                wordState = 1;
                            }

                        } else if (wordState == 3) { //==
                            switch (currentChar) {
                                case '<', '=', '>' -> {

                                    wordBuffer += currentChar;
                                    wordState = 1;
                                    stateIsNum = true;
                                    if (!wordBuffer.equals("")) {
                                        if (keyWords.get(wordBuffer) != null) {
                                            tokenDataR.add(keyWords.get(wordBuffer));

                                        } else {
                                            tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                                        }
                                        wordBuffer = "";
                                    }
                                }
                                default -> {
                                    if (!wordBuffer.equals("")) {
                                        if (keyWords.get(wordBuffer) != null) {
                                            tokenDataR.add(keyWords.get(wordBuffer));

                                        } else {
                                            tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                                        }
                                        wordBuffer = "";
                                    }
                                    wordBuffer += currentChar;
                                    wordState = 1;
                                    stateIsNum = true;
                                }

                            }

                        } else if (wordState == 4) {
                            state = 1;
                        } else {
                            state = 1;
                        }
                    }
                } else { //space clears buffer
                    if (!wordBuffer.equals("")) {
                        if (keyWords.get(wordBuffer) != null) {
                            tokenDataR.add(keyWords.get(wordBuffer));
                        } else {
                            tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
                        }
                        wordBuffer = "";
                        stateIsNum = true;
                    }
                }
            } //eol
            if (!wordBuffer.equals("")) {
                if (keyWords.get(wordBuffer) != null) {
                    tokenDataR.add(keyWords.get(wordBuffer));
                } else {
                    tokenDataR.add(new Token(Token.OPTokens.IDENTIFIER, wordBuffer));
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
