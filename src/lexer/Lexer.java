package lexer;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Lexer {
    public Lexer() {
        Token a =new Token();
        final Token.Tokens token1 = Token.Tokens.TOKEN1;
        System.out.println(token1.toString());

    }

    /**
     *
     * @return array list of chars
     *
     */
    public ArrayList<String> lexer() {
        return new ArrayList<String>();
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
