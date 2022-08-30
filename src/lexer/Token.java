package lexer;

import java.util.*;
import java.io.*;


/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Token {
    enum OPTokens{
        ADD("+"),
        SUBTRACT("-"),
        DIVIDE("/"),
        MULTIPLY("*"),
        TOKEN4(";"),//Can I not do endOfLine for my break line bc I love java syntax
        ;
        private String s;
        OPTokens(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
    }
    enum NumTokens{
        ZERO("0"),
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),//Can I not do endOfLine for my break line bc I love java syntax
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NEIN("9"),
        ;
        private String s;
        NumTokens(String s) {
            this.s = s;
        }
        @Override
        public String toString() {
            return s;
        }
    }


}
