import lexer.Lexer;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Files;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class main {
    public static TimeElapsedRun a = new TimeElapsedRun();


    public static void main(String[] args) throws IOException {
        LexerTestMethod();
    }
    public static void LexerTestMethod() throws IOException {
//        Lexer obk = new Lexer("5+-4+-24;");
        String PATH = "src/TestFile";
//        Path path = Paths.get("TestFile");
        ArrayList<String> a = (ArrayList<String>) Files.readAllLines(Path.of(PATH), StandardCharsets.UTF_8);
        Lexer obk = new Lexer(a);
        ArrayList<String> tokenData = obk.lexer();
        for(int i = 0; i < tokenData.size(); i++){
            System.out.println(tokenData.get(i));
        }
//        a.getTime().stop();
        System.out.println("\n");
        System.out.println("sucess");

    }
}
