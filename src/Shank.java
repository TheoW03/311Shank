import lexer.Lexer;
import lexer.Token;
import parser.Interperter;
import parser.Parser;
import parser.node.Node;

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
//compiles. trust me bro ;)
public class Shank {
    public static void main(String[] args) throws IOException {
        LexerTestMethod();
        System.out.println("\n");
        System.out.println("sucess");
    }

    /**
     *
     * @throws IOException file
     * method to run becvause I didnt want to overload main
     */
    public static void LexerTestMethod() throws IOException {
        String PATH = "src/TestFile";
        ArrayList<String> a = (ArrayList<String>) Files.readAllLines(Path.of(PATH), StandardCharsets.UTF_8);
        Lexer obk = new Lexer(a);
        ArrayList<Token> tokenData = obk.lexer();
        for(int i = 0; i < tokenData.size(); i++){
            System.out.println(tokenData.get(i).toString());
        }
        ParserTestMethod(tokenData);


    }
    public static void ParserTestMethod(ArrayList<Token> a){
        Parser p = new Parser(a);

        Interperter i = new Interperter();
        System.out.println();
        System.out.println(i.Resolve(p.parserMethod()));
//        System.out.println(i.Resolve());
//        Parser c = new Parser(a);
////       ArrayList<Node> b = c.parserMethod();
//       for(int i = 0; i < b.size(); i++){
//           System.out.println(b.get(i).ToString());
//       }

    }
}
