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
    enum Tokens{
        TOKEN1("+"),
        TOKEN2("-"),
        TOKEN3("/"),
        TOKEN5("="),
        TOKEN4(";"),//Can I not do endOfLine for my break line bc I love java syntax
        ;
        private String s;
        Tokens(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return "Tokens{" +
                    "s='" + s + '\'' +
                    '}';
        }
    }


}
