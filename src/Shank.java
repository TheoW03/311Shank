import lexer.Lexer;
import lexer.Token;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Shank {
    public static TimeElapsedRun a = new TimeElapsedRun();


    public static void main(String[] args) throws IOException {
        LexerTestMethod();
    }
    public static void LexerTestMethod() throws IOException {
        String PATH = "src/TestFile";
        ArrayList<String> a = (ArrayList<String>) Files.readAllLines(Path.of(PATH), StandardCharsets.UTF_8);
        Lexer obk = new Lexer(a);
        ArrayList<Token> tokenData = obk.lexer();
        for(int i = 0; i < tokenData.size(); i++){
            System.out.println(tokenData.get(i).toString());
        }
        System.out.println("\n");
        System.out.println("sucess");

    }
}
