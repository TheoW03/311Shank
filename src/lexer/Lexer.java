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
    private Token tokenList;
    public Lexer() {
        this.tokenList = new Token();
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
